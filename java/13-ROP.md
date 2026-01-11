- [13. Railway Oriented Programming (ROP)](#13-railway-oriented-programming-rop)
  - [13.1. Estructuras de Datos: Either y Result](#131-estructuras-de-datos-either-y-result)
  - [13.2. Librerías](#132-librerías)
  - [13.3. Ventajas](#133-ventajas)
  - [13.4. Happy Path](#134-happy-path)
  - [13.5. Más sobre Excepciones o Result](#135-más-sobre-excepciones-o-result)
    - [13.5.1. Ejemplo con Excepciones](#1351-ejemplo-con-excepciones)
      - [13.5.1.1. Validación de la Solicitud](#13511-validación-de-la-solicitud)
      - [13.5.1.2. Transformación de Datos](#13512-transformación-de-datos)
      - [13.5.1.3. Almacenamiento en la Base de Datos](#13513-almacenamiento-en-la-base-de-datos)
      - [13.5.1.4. Encadenando Operaciones con Excepciones](#13514-encadenando-operaciones-con-excepciones)
    - [13.5.2. Análisis del Enfoque con Excepciones](#1352-análisis-del-enfoque-con-excepciones)
    - [13.5.3. Ejemplo con Either](#1353-ejemplo-con-either)
      - [13.5.3.1. Validación de la Solicitud](#13531-validación-de-la-solicitud)
      - [13.5.3.2. Transformación de Datos](#13532-transformación-de-datos)
      - [13.5.3.3. Almacenamiento en la Base de Datos](#13533-almacenamiento-en-la-base-de-datos)
      - [13.5.3.4. Encadenando Operaciones con Either](#13534-encadenando-operaciones-con-either)
    - [13.5.4. Análisis del Enfoque con Either](#1354-análisis-del-enfoque-con-either)
    - [13.5.5. Comparación y Conclusión](#1355-comparación-y-conclusión)

# 13. Railway Oriented Programming (ROP)

> 📝 **Nota del Profesor**: ROP es una alternativa elegante al manejo tradicional de excepciones. Hace el flujo de errores explícito y predecible.

Railway Oriented Programming es un estilo de programación que se centra en manejar flujos de datos que pueden tener éxito o fallar en cualquier punto. La idea principal es modelar las operaciones como si fueran vías de tren: una vía representa el camino del éxito y otra el camino del fallo.

![rop](../images/rop.webp)

```mermaid
flowchart LR
    Input[Entrada] --> Val1[Validar]
    Val1 -->|Éxito| Trans[Transformar]
    Val1 -->|Error| Error1[Error]
    Trans -->|Éxito| Guardar[Guardar BBDD]
    Trans -->|Error| Error2[Error]
    Guardar -->|Éxito| Salida[Salida]
    Guardar -->|Error| Error3[Error]
    
    Error1 --> Error1
    Error2 --> Error1
    Error3 --> Error1
    
    style Val1 fill:#74c0fc
    style Trans fill:#74c0fc
    style Guardar fill:#74c0fc
```

## 13.1. Estructuras de Datos: Either y Result
En ROP, utilizamos estructuras de datos como Either<L, R> o Result<T, Err> para representar estos dos posibles estados.

- Either<L, R>: Either es una estructura de datos que puede contener un valor de dos tipos posibles: L (normalmente usado para errores) o R (normalmente usado para éxitos).
- Result<T, Err>: Result es otra estructura que se utiliza para representar operaciones que pueden fallar. En Kotlin, la librería estándar no tiene Result con dos tipos, pero puedes usar una librería externa o definir la tuya propia.

## 13.2. Librerías
- Arrow: https://arrow-kt.io/ (https://www.baeldung.com/kotlin/arrow)
- Kotlin Result: https://github.com/michaelbull/kotlin-result (https://github.com/michaelbull/kotlin-result/wiki)
- VAVR: https://www.vavr.io/ (https://www.baeldung.com/vavr-either)

## 13.3. Ventajas
Ventajas sobre el uso de Excepciones
- Claridad y Mantenibilidad: El flujo de datos es explícito. No hay necesidad de capturar excepciones en cada nivel.
- Composición: Es más fácil componer funciones que pueden fallar.
- Control de Errores: Los errores se manejan en el tipo del dato, no en el flujo de control.

## 13.4. Happy Path
En ROP, el "happy path" es el flujo de ejecución donde todo funciona correctamente. El objetivo es mantener este flujo limpio y legible, mientras que los errores se manejan de manera explícita y predecible.

> 💡 **Tip del Examinador**: El "happy path" debe ser legible verticalmente. Los errores se gestionan en los márgenes, no en el flujo principal.

Ejemplo: Procesamiento de una Solicitud de Usuario

Supongamos que estamos desarrollando una aplicación que recibe solicitudes de los usuarios. Este proceso incluye varias etapas: validación de la solicitud, transformación de datos, y almacenamiento en una base de datos. Cada una de estas etapas puede fallar.

Primero, definimos nuestras funciones que representan cada etapa del proceso:

Validación de la Solicitud:

```kotlin
fun validateRequest(request: String): Either<String, String> {
    return if (request.isNotEmpty()) {
        Either.Right(request)
    } else {
        Either.Left("Request cannot be empty")
    }
}
```	
Transformación de Datos:

```kotlin	
fun transformData(data: String): Either<String, Int> {
    return try {
        Either.Right(data.toInt())
    } catch (e: NumberFormatException) {
        Either.Left("Invalid number format")
    }
}

```	
Almacenamiento en la Base de Datos:

```kotlin	
fun saveToDatabase(number: Int): Either<String, Boolean> {
    return if (number > 0) {
        Either.Right(true)
    } else {
        Either.Left("Number must be positive")
    }
}
```	

Encadenando Operaciones

Ahora, queremos encadenar estas operaciones de manera que si alguna falla, el flujo se detenga y el error se propague. Utilizando flatMap, podemos mantener el "happy path" limpio y manejable:

```kotlin
fun processRequest(request: String): Either<String, Boolean> {
    return validateRequest(request)
        .flatMap { validRequest -> transformData(validRequest) }
        .flatMap { number -> saveToDatabase(number) }
}
````

````kotlin
fun main() {
    val result = processRequest("123")
    when (result) {
        is Either.Right -> println("Success: ${result.value}")
        is Either.Left -> println("Error: ${result.value}")
    }
}
```
En este ejemplo, processRequest intenta validar la solicitud, transformar los datos y guardarlos en la base de datos. Si alguna de estas operaciones falla, el error se propaga y se detiene el flujo.


### Ventajas sobre el Uso de Excepciones

- Flujo de Control Predecible:Con excepciones, el flujo de control puede ser difícil de seguir, ya que las excepciones pueden lanzarse desde cualquier punto y deben ser capturadas en otro lugar.
Con Either o Result, el flujo de control es explícito y fácil de seguir. Sabes exactamente dónde y cómo se manejan los errores.

- Composición de Funciones:Las funciones que utilizan excepciones son difíciles de componer porque debes capturar y manejar las excepciones en cada nivel.
Las funciones que devuelven Either o Result son fáciles de componer utilizando flatMap, lo que permite encadenar operaciones de manera limpia.

- Errores como Valores: Las excepciones son una forma de control de flujo que puede ser difícil de manejar y probar.
Con Either o Result, los errores se tratan como valores, lo que facilita el manejo y las pruebas.
Happy Path y Manejo de Errores


Ejemplo: Registro de Usuarios
Supongamos que tenemos un proceso de registro de usuarios que incluye verificar la disponibilidad del nombre de usuario, validar la contraseña y guardar los datos del usuario.

Verificar Disponibilidad del Nombre de Usuario:

````kotlin
fun checkUsernameAvailability(username: String): Either<String, String> {
    return if (username != "takenUsername") {
        Either.Right(username)
    } else {
        Either.Left("Username is already taken")
    }
}
````	
Validar Contraseña:

````kotlin
fun validatePassword(password: String): Either<String, String> {
    return if (password.length >= 8) {
        Either.Right(password)
    } else {
        Either.Left("Password must be at least 8 characters long")
    }
}
````

Guardar Usuario en la Base de Datos:

````kotlin
fun saveUser(username: String, password: String): Either<String, Boolean> {
    // Simulamos el guardado en la base de datos
    return Either.Right(true)
}
````	

Encadenando el Proceso de Registro
````kotlin
fun registerUser(username: String, password: String): Either<String, Boolean> {
    return checkUsernameAvailability(username)
        .flatMap { availableUsername -> validatePassword(password).map { availableUsername to it } }
        .flatMap { (validUsername, validPassword) -> saveUser(validUsername, validPassword) }
}
````
````kotlin	
fun main() {
    val registrationResult = registerUser("newUser", "securePassword")
    when (registrationResult) {
        is Either.Right -> println("Registration successful")
        is Either.Left -> println("Registration failed: ${registrationResult.value}")
    }
}
````	
En este ejemplo, registerUser encadena las operaciones de verificación de nombre de usuario, validación de contraseña y guardado del usuario. Si alguna de estas operaciones falla, el flujo se detiene y el error se propaga.


## 13.5. Más sobre Excepciones o Result

### 13.5.1. Ejemplo con Excepciones

#### 13.5.1.1. Validación de la Solicitud
```kotlin
fun validateRequest(request: String): String {
    if (request.isNotEmpty()) {
        return request
    } else {
        throw IllegalArgumentException("Request cannot be empty")
    }
}
```

#### 13.5.1.2. Transformación de Datos
```kotlin
fun transformData(data: String): Int {
    return try {
        data.toInt()
    } catch (e: NumberFormatException) {
        throw IllegalArgumentException("Invalid number format")
    }
}
```

#### 13.5.1.3. Almacenamiento en la Base de Datos
```kotlin
fun saveToDatabase(number: Int): Boolean {
    if (number > 0) {
        return true
    } else {
        throw IllegalArgumentException("Number must be positive")
    }
}
```

#### 13.5.1.4. Encadenando Operaciones con Excepciones
```kotlin
fun processRequest(request: String): Boolean {
    return try {
        val validRequest = validateRequest(request)
        val number = transformData(validRequest)
        saveToDatabase(number)
    } catch (e: IllegalArgumentException) {
        println("Error: ${e.message}")
        false
    }
}

fun main() {
    val result = processRequest("123")
    println("Result: $result")
}
```

### 13.5.2. Análisis del Enfoque con Excepciones

> ⚠️ **Advertencia de Seguridad**: Las excepciones pueden ser capturadas y manipuladas. No uses excepciones para control de flujo en código de producción.

- **Manejo Implícito**: El manejo de errores está disperso en el código y es menos explícito.
- **Acoplamiento**: El control del flujo y el manejo de errores están estrechamente acoplados, lo que hace el código más difícil de mantener.
- **Legibilidad**: Cada función debe conocer el contexto de cómo se manejarán las excepciones, lo que puede hacer el código menos legible.

### 13.5.3. Ejemplo con Either

Ahora implementemos el mismo ejemplo utilizando `Either<L, R>`.

#### 13.5.1.1. Validación de la Solicitud
```kotlin
fun validateRequest(request: String): Either<String, String> {
    return if (request.isNotEmpty()) {
        Either.Right(request)
    } else {
        Either.Left("Request cannot be empty")
    }
}
```

#### 13.5.1.2. Transformación de Datos
```kotlin
fun transformData(data: String): Either<String, Int> {
    return try {
        Either.Right(data.toInt())
    } catch (e: NumberFormatException) {
        Either.Left("Invalid number format")
    }
}
```

#### 13.5.1.3. Almacenamiento en la Base de Datos
```kotlin
fun saveToDatabase(number: Int): Either<String, Boolean> {
    return if (number > 0) {
        Either.Right(true)
    } else {
        Either.Left("Number must be positive")
    }
}
```

#### Encadenando Operaciones con Either
```kotlin
fun processRequest(request: String): Either<String, Boolean> {
    return validateRequest(request)
        .flatMap { validRequest -> transformData(validRequest) }
        .flatMap { number -> saveToDatabase(number) }
}

fun main() {
    val result = processRequest("123")
    when (result) {
        is Either.Right -> println("Success: ${result.value}")
        is Either.Left -> println("Error: ${result.value}")
    }
}
```

### Análisis del Enfoque con Either

> 📝 **Nota del Profesor**: Either hace que el manejo de errores sea parte del tipo de datos, no del control de flujo. Esto hace el código más testable y mantenible.

- **Manejo Explícito**: El manejo de errores es explícito y parte del flujo de datos, lo que facilita el seguimiento.
- **Desacoplamiento**: El manejo de errores y el control del flujo están desacoplados, haciendo el código más fácil de mantener.
- **Legibilidad**: Cada función se centra solo en su lógica, sin preocuparse por el manejo de excepciones. El encadenamiento con `flatMap` mantiene el "happy path" limpio y legible.

### Comparación y Conclusión

1. **Control del Flujo con Excepciones**:
    - En el ejemplo con excepciones, el flujo del programa puede interrumpirse en cualquier punto con una excepción, y debemos asegurarnos de capturar todas las excepciones en un bloque `try-catch`.
    - La lógica de manejo de errores se mezcla con la lógica principal, dificultando tanto la lectura como el mantenimiento.

2. **Control del Flujo con Either**:
    - En el ejemplo con `Either`, el flujo de datos y los errores se manejan de manera explícita y predecible.
    - El "happy path" se mantiene limpio, y los errores se propagan automáticamente a través del encadenamiento de operaciones, mejorando la claridad y la mantenibilidad del código.

Al comparar ambos enfoques, es evidente que `Either` proporciona un mejor manejo de errores, facilitando la composición de funciones y manteniendo el flujo de control claro y predecible. Esto hace que el código sea más robusto y fácil de seguir.