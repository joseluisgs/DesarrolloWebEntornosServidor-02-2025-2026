- [14. HTTP y REST](#14-http-y-rest)
  - [14.1. Protocolo HTTP](#141-protocolo-http)
    - [14.1.1. Verbos HTTP](#1411-verbos-http)
    - [14.1.2. Códigos de Respuesta más usuales HTTP](#1412-códigos-de-respuesta-más-usuales-http)
    - [14.1.3. Qué es una API REST y su Importancia](#1413-qué-es-una-api-rest-y-su-importancia)
  - [14.2. JWT](#142-jwt)
    - [14.2.1. Necesidad en HTTP](#1421-necesidad-en-http)
    - [14.2.2. Partes](#1422-partes)
      - [14.2.2.1. Estructura de un JWT](#14221-estructura-de-un-jwt)
      - [14.2.2.2. 1. Header (Encabezado)](#14222-1-header-encabezado)
        - [14.2.2.2.1. Propósito](#142221-propósito)
        - [14.2.2.2.2. Ejemplo](#142222-ejemplo)
      - [14.2.2.3. 2. Payload (Carga Útil)](#14223-2-payload-carga-útil)
        - [14.2.2.3.1. Propósito](#142231-propósito)
        - [14.2.2.3.2. Ejemplo](#142232-ejemplo)
      - [14.2.2.4. 3. Signature (Firma)](#14224-3-signature-firma)
        - [14.2.2.4.1. Propósito](#142241-propósito)
        - [14.2.2.4.2. Proceso](#142242-proceso)
      - [14.2.2.5. Ejemplo completo del JWT](#14225-ejemplo-completo-del-jwt)
        - [14.2.2.5.1. Header](#142251-header)
        - [14.2.2.5.2. Payload](#142252-payload)
        - [14.2.2.5.3. Clave Secreta](#142253-clave-secreta)
        - [14.2.2.5.4. Signature](#142254-signature)
      - [14.2.2.6. Token Completo](#14226-token-completo)
  - [14.3. Retrofit](#143-retrofit)
    - [14.3.1. Configuración en Gradle](#1431-configuración-en-gradle)
    - [14.3.2. Crear Modelo de Datos](#1432-crear-modelo-de-datos)
    - [14.3.3. Definir la Interfaz de API](#1433-definir-la-interfaz-de-api)
    - [14.3.4. Configurar Retrofit](#1434-configurar-retrofit)
    - [14.3.5. Implementar el Repositorio](#1435-implementar-el-repositorio)
    - [14.3.6. Main](#1436-main)
  - [14.4. Ktor y Ktorfit](#144-ktor-y-ktorfit)
    - [14.4.1. Crear el Proyecto en Kotlin](#1441-crear-el-proyecto-en-kotlin)
    - [14.4.2. Configuración en Gradle](#1442-configuración-en-gradle)
    - [14.4.3. Crear Modelo de Datos](#1443-crear-modelo-de-datos)
    - [14.4.4. Definir la Interfaz de API](#1444-definir-la-interfaz-de-api)
    - [14.4.5. Configurar Ktor Client con Ktorfit](#1445-configurar-ktor-client-con-ktorfit)
    - [14.4.6. Implementar el Repositorio](#1446-implementar-el-repositorio)
    - [14.4.7. Main](#1447-main)

# 14. HTTP y REST

> 📝 **Nota del Profesor**: HTTP es el fundamento de la web moderna. Entiende bien los verbos y códigos de estado antes de pasar a frameworks.

## 14.1. Protocolo HTTP
HTTP (HyperText Transfer Protocol) es uno de los protocolos más utilizados en la web. Es un protocolo no orientado a la conexión, lo que significa que cada solicitud de cliente al servidor es independiente y no requiere una conexión continua.

Importancia:
- Arquitecturas Distribuidas: HTTP es fundamental en arquitecturas distribuidas como microservicios, donde diferentes servicios se comunican entre sí.
- Creación de API: Es la base para la creación de APIs REST (Representational State Transfer), que es una forma de diseñar sistemas de software escalables y fáciles de mantener.

### 14.1.1. Verbos HTTP
- GET: Obtener datos del servidor.
- POST: Enviar datos al servidor.
- PUT: Actualizar datos existentes en el servidor.
- DELETE: Eliminar datos del servidor.
- PATCH: Actualizar parcialmente un recurso en el servidor.

> 💡 **Tip del Examinador**: En el examen prático, usa POST para crear, PUT para替换 completo, PATCH para parcial, y DELETE para eliminar.

### 14.1.2. Códigos de Respuesta más usuales HTTP
- 200 OK: La solicitud ha tenido éxito.
- 201 Created: La solicitud ha tenido éxito y se ha creado un nuevo recurso.
- 204 No Content: La solicitud ha tenido éxito, pero no hay contenido para enviar.
- 400 Bad Request: La solicitud es incorrecta.
- 401 Unauthorized: Autenticación necesaria.
- 403 Forbidden: El servidor comprendió la solicitud, pero se niega a cumplirla.
- 404 Not Found: El servidor no puede encontrar el recurso solicitado.
- 500 Internal Server Error: El servidor encuentra una situación que no sabe cómo manejar.

### 14.1.3. Qué es una API REST y su Importancia
Una API REST (Representational State Transfer) es una interfaz que permite la comunicación entre sistemas mediante el protocolo HTTP.

> 📝 **Nota del Profesor**: REST no es solo usar JSON sobre HTTP. Sigue los principios de recursos, stateless y caché para ser verdaderamente RESTful.

Importancia:
- Escalabilidad: Las APIs REST son fácilmente escalables debido a su naturaleza estateless (sin estado).
- Flexibilidad: Permiten cualquier formato de datos (JSON, XML, etc.).
- Simplicidad: Son fáciles de usar y entender, lo que acelera el desarrollo y mantenimiento.

![apirest](../images/apirest.png)

```mermaid
flowchart LR
    Cliente[Cliente] -->|GET /users| API[API REST]
    API -->|200 OK JSON| Cliente
    Cliente -->|POST /users| API
    API -->|201 Created| Cliente
    Cliente -->|PUT /users/1| API
    API -->|200 OK| Cliente
    Cliente -->|DELETE /users/1| API
    -->|204 No Content| Cliente
```

## 14.2. JWT
JWT (JSON Web Token) es un estándar para la creación de tokens de acceso. Se utiliza principalmente para autenticación y autorización.

Funcionamiento: 
- Autenticación: El usuario se autentica ingresando sus credenciales.
- Generación: El servidor genera un JWT y lo devuelve al cliente.
- Autorización: El cliente envía el JWT en las solicitudes subsecuentes para acceder a recursos protegidos.
- Verificación: El servidor verifica el JWT antes de conceder acceso.

## Necesidad en HTTP
- Seguridad: JWT asegura que solo las solicitudes autenticadas puedan acceder a recursos protegidos.
- Eficiencia: Reduce la sobrecarga del servidor ya que no requiere almacenar sesiones en el servidor.
- Escalabilidad: Facilita la expansión del sistema sin preocuparse de la gestión de sesiones.

![token01](../images/jwt01.jpg)

## Partes
Un JWT (JSON Web Token) está compuesto por tres partes principales, y cada una está separada por un punto (`.`):

1. **Header** (Encabezado)
2. **Payload** (Carga Útil)
3. **Signature** (Firma)

![token02](../images/jwt02.png)


### Estructura de un JWT

Un JWT completo podría verse algo así:
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

### 1. Header (Encabezado)

#### Propósito
El encabezado típicamente consiste en dos partes: el tipo de token (que es JWT) y el algoritmo de firma utilizado (por ejemplo, HMAC SHA256 o RSA).

#### Ejemplo
```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

Este JSON es luego codificado en Base64Url para formar la primera parte del JWT.

### 2. Payload (Carga Útil)

#### Propósito
El payload contiene las reclamaciones (claims). Las reclamaciones son declaraciones sobre una entidad (normalmente, el usuario) y datos adicionales. Hay tres tipos de reclamos:

- **Restringidos (Registered Claims)**: Un conjunto de claims predeterminadas que no son obligatorias pero se recomiendan, como `iss` (emisor), `exp` (expiración), `sub` (sujeto), y `aud` (audiencia).
- **Públicos (Public Claims)**: Para información personalizada y debe evitar colisiones de nombre (name collision) utilizando URLs o URNs como nombres.
- **Privados (Private Claims)**: Para compartir información entre partes que han acordado utilizar esos claims y no están registrados.

#### Ejemplo
```json
{
  "sub": "1234567890",
  "name": "John Doe",
  "admin": true
}
```

Este JSON también es codificado en Base64Url para formar la segunda parte del JWT.

### 3. Signature (Firma)

#### Propósito
Para crear la firma, se toma el header y el payload, se codifican en Base64Url, y se unen con un punto (`.`). Luego, esta cadena se firma utilizando el algoritmo especificado en el Header y una clave secreta.

#### Proceso
```
HMACSHA256(
  base64UrlEncode(header) + "." + base64UrlEncode(payload),
  secret)
```

La firma asegura que el mensaje no ha sido alterado, y en algunos casos, puede verificar también el remitente del mensaje.

### Ejemplo completo del JWT

#### Header
```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

Codificado en Base64Url:
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9
```

#### Payload
```json
{
  "sub": "1234567890",
  "name": "John Doe",
  "admin": true
}
```

Codificado en Base64Url:
```
eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ
```

#### Clave Secreta
```
your-256-bit-secret
```

#### Signature
Utilizando HMAC SHA256 y la clave secreta:
```
SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

### Token Completo
Finalmente, el JWT completo será:
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

---

Este token se puede enviar como parte de una autorización en una cabecera (header) HTTP para autenticar y autorizar las solicitudes.

```http
GET /resource HTTP/1.1
Host: api.example.com
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```
## 14.3. Retrofit
Retrofit es una biblioteca de tipo cliente HTTP para Java y Android desarrollada por Square. Facilita la interacción con servicios web al convertir automáticamente las respuestas HTTP en objetos Java mediante el uso de convertidores de JSON, XML o cualquier otro formato.

Cuando trabajas con APIs RESTful, Retrofit permite definir la interfaz de las solicitudes HTTP y manejar las respuestas de manera muy eficiente. Es especialmente útil cuando necesitas enviar y recibir datos en formato JSON.

Lo siento por las confusiones. Vamos a crear un ejemplo más completo que incluya un repositorio con operaciones CRUD remotas utilizando Retrofit en un entorno de Java por consola.

## Configuración en Gradle

Asegúrate de que tu `build.gradle` tenga las dependencias correctas:

```groovy
plugins {
    id 'java'
}

group 'com.example'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.google.code.gson:gson:2.8.7'
}
```

## Crear Modelo de Datos

Primero, necesitamos un modelo de datos que representará nuestros objetos. Vamos a crear la clase `User`:

```java
// User.java
public class User {
    private int id;
    private String name;
    private String email;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and setters

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + ", email='" + email + '\'' + '}';
    }
}
```

## Definir la Interfaz de API

Luego, definimos la interfaz de Retrofit para las operaciones CRUD remotas:

```java
// UserService.java
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface UserService {

    @GET("users")
    Call<List<User>> getUsers();

    @GET("users/{id}")
    Call<User> getUserById(@Path("id") int id);

     @POST("users")
    Call<User> createUser(@Header("Authorization") String token, @Body User user);

    @PUT("users/{id}")
    Call<User> updateUser(@Path("id") int id, @Body User user);

    @DELETE("users/{id}")
    Call<Void> deleteUser(@Path("id") int id);
}
```

## Configurar Retrofit

Configurar Retrofit para crear una instancia de `UserService`:

```java
// RetrofitClient.java
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://api.example.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static UserService getUserService() {
        return getClient().create(UserService.class);
    }
}
```

## Implementar el Repositorio

Implementa el repositorio que usará `UserService` para las operaciones CRUD:

```java
// UserRepository.java
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class UserRepository {

    private UserService userService;

    public UserRepository() {
        this.userService = RetrofitClient.getUserService();
    }

    public void getUsers() {
        Call<List<User>> call = userService.getUsers();
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> users = response.body();
                    System.out.println("Users: " + users);
                } else {
                    System.err.println("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getUserById(int id) {
        Call<User> call = userService.getUserById(id);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    System.out.println("User: " + user);
                } else {
                    System.err.println("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void createUser(String token, User user) {
        Call<User> call = userService.createUser(token, user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User createdUser = response.body();
                    System.out.println("Created User: " + createdUser);
                } else {
                    System.err.println("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void updateUser(int id, User user) {
        Call<User> call = userService.updateUser(id, user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User updatedUser = response.body();
                    System.out.println("Updated User: " + updatedUser);
                } else {
                    System.err.println("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void deleteUser(int id) {
        Call<Void> call = userService.deleteUser(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    System.out.println("User deleted successfully");
                } else {
                    System.err.println("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
```

## Main

Finalmente, usa el repositorio en tu aplicación principal de consola:

```java
// Main.java
public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        // Crear un nuevo usuario
        User newUser = new User("John Doe", "john.doe@example.com");
       userRepository.createUser(token, newUser);

        // Obtener todos los usuarios
        userRepository.getUsers();

        // Obtener un usuario por ID
        userRepository.getUserById(1);

        // Actualizar un usuario
        User updatedUser = new User("Jane Doe", "jane.doe@example.com");
        userRepository.updateUser(1, updatedUser);

        // Eliminar un usuario
        userRepository.deleteUser(1);
    }
}
```


## 14.4. Ktor y Ktorfit

**Ktor** es un framework asíncrono para construir aplicaciones web y APIs en Kotlin, desarrollado por JetBrains. Ktor es altamente configurable y modular, permitiendo a los desarrolladores crear aplicaciones web fácilmente personalizables utilizando Kotlin.

**Ktorfit** es una biblioteca para Kotlin que combina la potencia de Ktor con la simplicidad de Retrofit. Facilita las llamadas HTTP utilizando Ktor y mapea las respuestas directamente a objetos de datos Kotlin, similar a Retrofit.

## Crear el Proyecto en Kotlin

Vamos a crear un proyecto en Kotlin que siga la misma estructura y funcionalidad que el ejemplo anterior en Java pero utilizando Ktor y Ktorfit.

## Configuración en Gradle

Primero, asegura que tu `build.gradle.kts` tiene las dependencias necesarias.

```kotlin
plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-core:1.6.7")
    implementation("io.ktor:ktor-client-cio:1.6.7")
    implementation("io.ktor:ktor-client-content-negotiation:1.6.7")
    implementation("io.ktor:ktor-serialization-gson:1.6.7")
    implementation("de.jensklingenberg.ktorfit:ktorfit:1.0.0") // Ktorfit dependency (fictitious as of now)
}

application {
    mainClass.set("MainKt")
}
```

## Crear Modelo de Datos

Creamos los modelos de datos `User`.

```kotlin
// User.kt
data class User(
    val id: Int? = null,
    val name: String,
    val email: String
)
```

## Definir la Interfaz de API

Definimos una interfaz para las operaciones CRUD usando Ktorfit.

```kotlin
// UserService.kt
import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.Path
import de.jensklingenberg.ktorfit.http.Header

interface UserService {
    
    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User

    @POST("users")
    suspend fun createUser(@Header("Authorization") token: String, @Body user: User): User

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: User): User

    @DELETE("users/{id}")
    suspend fun deleteUser(@Path("id") id: Int)
}
```

## Configurar Ktor Client con Ktorfit

Configura el cliente de Ktor y Ktorfit en Kotlin.

```kotlin
// RetrofitClient.kt
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.gson.*
import de.jensklingenberg.ktorfit.Ktorfit

object RetrofitClient {
    private const val BASE_URL = "https://api.example.com/"

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    val ktorfit = Ktorfit.Builder()
        .baseUrl(BASE_URL)
        .httpClient(client)
        .build()
        
    val userService: UserService = ktorfit.create()
}
```

## Implementar el Repositorio

El repositorio usará `UserService` para operaciones CRUD.

```kotlin
// UserRepository.kt
class UserRepository(private val userService: UserService) {

    suspend fun getUsers() {
        try {
            val users = userService.getUsers()
            println("Users: $users")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun getUserById(id: Int) {
        try {
            val user = userService.getUserById(id)
            println("User: $user")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun createUser(token: String, user: User) {
        try {
            val createdUser = userService.createUser(token, user)
            println("Created User: $createdUser")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun updateUser(id: Int, user: User) {
        try {
            val updatedUser = userService.updateUser(id, user)
            println("Updated User: $updatedUser")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun deleteUser(id: Int) {
        try {
            userService.deleteUser(id)
            println("User deleted successfully")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
```

## Main

Finalmente, utiliza el repositorio en tu aplicación principal de consola.

```kotlin
// Main.kt
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val userService = RetrofitClient.userService
    val userRepository = UserRepository(userService)
    val token = "Bearer tu_token_aqui"  // Reemplaza con el token real

    // Crear un nuevo usuario
    val newUser = User(name = "John Doe", email = "john.doe@example.com")
    userRepository.createUser(token, newUser)

    // Obtener todos los usuarios
    userRepository.getUsers()

    // Obtener un usuario por ID
    userRepository.getUserById(1)

    // Actualizar un usuario
    val updatedUser = User(name = "Jane Doe", email = "jane.doe@example.com")
    userRepository.updateUser(1, updatedUser)

    // Eliminar un usuario
    userRepository.deleteUser(1)
}
```
