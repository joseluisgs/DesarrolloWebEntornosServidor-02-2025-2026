- [5. Fundamentos de la programación en Java](#5-fundamentos-de-la-programación-en-java)
  - [5.1. Sintaxis básica, tipos de datos y estructuras de control](#51-sintaxis-básica-tipos-de-datos-y-estructuras-de-control)
  - [5.2. Arrays: Tipos compuestos](#52-arrays-tipos-compuestos)
  - [5.3. Modificadores de acceso: `public`, `private` y `protected`](#53-modificadores-de-acceso-public-private-y-protected)
  - [5.4. Modificadores de comportamiento: `static` y `final`](#54-modificadores-de-comportamiento-static-y-final)

# 5. Fundamentos de la programación en Java

## 5.1. Sintaxis básica, tipos de datos y estructuras de control

- **Tipos de datos**: Java es un lenguaje fuertemente tipado.
  - **Tipos primitivos**: Almacenan valores simples y **no pueden ser nulos**. Son 8 en total, como `int` (enteros), `double` (decimales), `boolean` (lógico) y `char` (un solo carácter).
    ```java
    int edad = 30;
    double precio = 19.99;
    ```
  - **Clases envolventes (Wrapper Classes)**: Clases que representan los tipos primitivos y **sí pueden ser nulos**. Por ejemplo, `Integer` para `int`. Son útiles en colecciones.
    ```java
    Integer edadNula = null;
    Double altura = 1.75;
    ```
  - **Tipos de referencia**: Almacenan la dirección de un objeto en memoria. El más común es **`String`**, que es una clase y **puede ser nulo**.
    ```java
    String nombre = "Juan";
    String apellido = null;
    ```
- **Estructuras de control**: Dirigen el flujo de tu código.
  - **Condicional `if-else`**:
    ```java
    int nota = 7;
    if (nota >= 5) {
        System.out.println("Aprobado");
    } else {
        System.out.println("Suspendido");
    }
    // Salida: Aprobado
    ```
  - **Condicional `switch`**: Evalúa una expresión y ejecuta el bloque de código del `case` que coincide.
    ```java
    int dia = 3;
    String nombreDia;
    switch (dia) {
        case 1:
            nombreDia = "Lunes";
            break;
        case 2:
            nombreDia = "Martes";
            break;
        case 3:
            nombreDia = "Miércoles";
            break;
        default:
            nombreDia = "Día no válido";
    }
    System.out.println(nombreDia); // Salida: Miércoles
    ```
  - **Bucle `for`**:
    ```java
    for (int i = 0; i < 3; i++) {
        System.out.println("Iteración número: " + i);
    }
    // Salida: Iteración número: 0, Iteración número: 1, Iteración número: 2
    ```
  - **Bucle `for-each`**: Ideal para iterar sobre arrays y colecciones de forma simple.
    ```java
    String[] nombres = {"Ana", "Pedro", "Luis"};
    for (String nombre : nombres) {
        System.out.println("Hola, " + nombre);
    }
    // Salida: Hola, Ana; Hola, Pedro; Hola, Luis
    ```
  - **Bucle `while`**:
    ```java
    int contador = 0;
    while (contador < 2) {
        System.out.println("Bucle while, iteración: " + contador);
        contador++;
    }
    // Salida: Bucle while, iteración: 0; Bucle while, iteración: 1
    ```
  - **Bucle `do-while`**: Similar al `while`, pero garantiza que el bloque de código se ejecuta al menos una vez, ya que la condición se evalúa al final del bucle.
    ```java
    int contador = 5;
    do {
        System.out.println("El contador es: " + contador);
        contador++;
    } while (contador < 3);
    // Salida: El contador es: 5
    ```

## 2.2. Arrays: Tipos compuestos

Los arrays te permiten almacenar múltiples valores del mismo tipo. Una vez creados, su tamaño es fijo.

- **Arrays unidimensionales**: Listas simples de elementos.
  ```java
  // Creación e inicialización
  int[] numeros = new int[3];
  numeros[0] = 10;
  // Recorrido
  String[] frutas = {"Manzana", "Naranja"};
  System.out.println(frutas[0]); // Salida: Manzana
  ```
- **Arrays bidimensionales**: Arrays de arrays, útiles para matrices o tablas.
  ```java
  // Representa una matriz 2x3
  int[][] matriz = {
      {1, 2, 3},
      {4, 5, 6}
  };
  // Acceder a un elemento: Fila 1, Columna 2
  System.out.println(matriz[0][1]); // Salida: 2
  ```

## 2.3. Modificadores de acceso: `public`, `private` y `protected`

Estos modificadores controlan la **visibilidad** de los miembros de una clase, un concepto fundamental para la **encapsulación**.

- `public`: Accesible desde cualquier lugar.
- `private`: Solo accesible dentro de la misma clase.
- `protected`: Accesible en la misma clase, en el mismo paquete y en subclases.

💡 **Tip del Examinador**: Pregunta frecuente en examen: "Explica los modificadores de acceso en Java". Respuesta esperada: public (todos), private (solo clase), protected (clase + paquete + herencia).

📝 **Nota del Profesor**: La encapsulación es uno de los pilares de la POO. Siempre que sea posible, usad `private` con getters y setters en lugar de hacer los atributos públicos.

## 2.4. Modificadores de comportamiento: `static` y `final`

- **`static`**: El miembro pertenece a la **clase**, no a una instancia individual. Hay una única copia compartida por todos los objetos.
  ```java
  public class Contador {
      public static int totalObjetos = 0;
      public Contador() {
          totalObjetos++;
      }
  }
  Contador c1 = new Contador();
  Contador c2 = new Contador();
  System.out.println(Contador.totalObjetos); // Salida: 2
  ```
- **`final`**: Indica que un miembro no puede ser modificado.
  - En variables, crea una **constante**.
  - En métodos, impide que sea sobrescrito por subclases.
  - En clases, impide que sea heredada.

💡 **Tip del Examinador**: Una pregunta clásica es "¿Qué pasa si declaras un método como static?" Significa que se puede llamar sin crear una instancia de la clase (pertenece a la clase, no al objeto).
