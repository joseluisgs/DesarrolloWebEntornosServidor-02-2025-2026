# Manual de Programación en Java 2º DAW

---

### Introducción

Bienvenido a un viaje a través de los cimientos del desarrollo de software moderno con Java. En estas guía, no solo exploraremos los conceptos teóricos, sino que también nos ensuciamos las manos con el código y las herramientas que te permitirán cerrar la brecha entre una idea y una aplicación funcional en el mundo real.

Este manual cubre **programación Java aplicada al desarrollo de sofware**. Desde las bases de la gestión de proyectos hasta las sutilezas de los paradigmas de programación más avanzados POO y Funcional, cada capítulo está diseñado para guiarte en la creación de software robusto, eficiente y listo para producción. A través de la práctica con **JUnit**, la automatización con **Gradle** y la inmersión en la programación **reactiva** o consultas de apis externas y bases de datos, descubrirás cómo la calidad del código, el rendimiento y la estabilidad son los pilares de cualquier aplicación exitosa. Prepárate para dominar el arte de construir, probar y lanzar tus propias creaciones al mundo.

---

- [Manual de Programación en Java 2º DAW](#manual-de-programación-en-java-2º-daw)
    - [Introducción](#introducción)
    - [1. Introducción al lenguaje de programación Java](#1-introducción-al-lenguaje-de-programación-java)
      - [1.1. ¿Qué es Java y por qué usarlo?](#11-qué-es-java-y-por-qué-usarlo)
      - [1.2. El proceso de compilación y ejecución: El papel de la JVM](#12-el-proceso-de-compilación-y-ejecución-el-papel-de-la-jvm)
    - [2. Fundamentos de la programación en Java](#2-fundamentos-de-la-programación-en-java)
      - [2.1. Sintaxis básica, tipos de datos y estructuras de control](#21-sintaxis-básica-tipos-de-datos-y-estructuras-de-control)
      - [2.2. Arrays: Tipos compuestos](#22-arrays-tipos-compuestos)
      - [2.3. Modificadores de acceso: `public`, `private` y `protected`](#23-modificadores-de-acceso-public-private-y-protected)
      - [2.4. Modificadores de comportamiento: `static` y `final`](#24-modificadores-de-comportamiento-static-y-final)
    - [3. Programación orientada a objetos (POO)](#3-programación-orientada-a-objetos-poo)
      - [3.1. Clases, objetos, atributos y métodos](#31-clases-objetos-atributos-y-métodos)
      - [3.2. Constructores, getters y setters](#32-constructores-getters-y-setters)
      - [3.2. Principios de la POO](#32-principios-de-la-poo)
      - [3.3. Interfaces y clases abstractas](#33-interfaces-y-clases-abstractas)
      - [3.4. Clases `abstract` y `sealed class`](#34-clases-abstract-y-sealed-class)
      - [3.5. Clases vs. `Records`](#35-clases-vs-records)
      - [3.6. El uso de **`Optional`**](#36-el-uso-de-optional)
    - [3.7. El uso de genéricos](#37-el-uso-de-genéricos)
      - [¿Por qué son necesarios los genéricos?](#por-qué-son-necesarios-los-genéricos)
      - [Clases y métodos genéricos](#clases-y-métodos-genéricos)
      - [Restricciones de tipos (`Bounded Type Parameters`)](#restricciones-de-tipos-bounded-type-parameters)
    - [4. Manejo de errores y excepciones](#4-manejo-de-errores-y-excepciones)
      - [4.1. Tipos de excepciones: `checked` vs. `unchecked`](#41-tipos-de-excepciones-checked-vs-unchecked)
      - [4.2. Manejo de excepciones: `try-catch-finally`](#42-manejo-de-excepciones-try-catch-finally)
      - [4.3. El uso de `try-with-resources`](#43-el-uso-de-try-with-resources)
      - [4.4. Creación de excepciones personalizadas](#44-creación-de-excepciones-personalizadas)
    - [5. Programación funcional](#5-programación-funcional)
      - [5.1. Funciones de primera clase y funciones de orden superior](#51-funciones-de-primera-clase-y-funciones-de-orden-superior)
      - [5.2. `Lambdas`, interfaces funcionales y `Function`](#52-lambdas-interfaces-funcionales-y-function)
      - [5.3. Inmutabilidad y ausencia de efectos secundarios](#53-inmutabilidad-y-ausencia-de-efectos-secundarios)
      - [5.4. Composición de funciones](#54-composición-de-funciones)
    - [6. Colecciones y Streams](#6-colecciones-y-streams)
      - [6.1. Estructuras de datos: Listas, conjuntos y mapas](#61-estructuras-de-datos-listas-conjuntos-y-mapas)
      - [6.2. Streams: Procesamiento de datos de forma declarativa](#62-streams-procesamiento-de-datos-de-forma-declarativa)
        - [Operaciones comunes con Streams](#operaciones-comunes-con-streams)
      - [6.3. `Parallel Streams`](#63-parallel-streams)
    - [6.4. Analogía con SQL](#64-analogía-con-sql)
    - [7. E/S (Entrada/Salida) y manipulación de ficheros](#7-es-entradasalida-y-manipulación-de-ficheros)
      - [7.1. Entrada y salida por consola](#71-entrada-y-salida-por-consola)
      - [7.2. Lectura y escritura de ficheros de texto](#72-lectura-y-escritura-de-ficheros-de-texto)
        - [API Clásica (`java.io`) con `try-with-resources`](#api-clásica-javaio-con-try-with-resources)
        - [API Moderna (`java.nio.file`) con Streams](#api-moderna-javaniofile-con-streams)
      - [7.3. Manejo de datos JSON con bibliotecas como Jackson](#73-manejo-de-datos-json-con-bibliotecas-como-jackson)
    - [8. Bases de datos](#8-bases-de-datos)
      - [8.1. JDBC: Conexión y operaciones con bases de datos](#81-jdbc-conexión-y-operaciones-con-bases-de-datos)
      - [8.2. Ejecución de consultas SQL: CRUD con JDBC](#82-ejecución-de-consultas-sql-crud-con-jdbc)
      - [8.3. JDBI: Uso de una biblioteca de abstracción sobre JDBC](#83-jdbi-uso-de-una-biblioteca-de-abstracción-sobre-jdbc)
        - [8.3.1. Creación de un DAO con una interfaz y `SQL Objects`](#831-creación-de-un-dao-con-una-interfaz-y-sql-objects)
        - [8.3.2. Implementación de un CRUD con JDBI](#832-implementación-de-un-crud-con-jdbi)
    - [8.4. MongoDB: Manejo de bases de datos NoSQL con API Tipada](#84-mongodb-manejo-de-bases-de-datos-nosql-con-api-tipada)
      - [Conexión con API Tipada y Configuración](#conexión-con-api-tipada-y-configuración)
      - [CRUD con API Tipada](#crud-con-api-tipada)
    - [9. Concurrencia y programación asíncrona](#9-concurrencia-y-programación-asíncrona)
      - [9.1. Introducción a hilos (threads)](#91-introducción-a-hilos-threads)
      - [9.2. Manejo de `Pool`s de hilos](#92-manejo-de-pools-de-hilos)
      - [9.3. `CompletableFuture`](#93-completablefuture)
    - [10. Programación Reactiva con RxJava](#10-programación-reactiva-con-rxjava)
      - [10.1. Introducción a la programación reactiva y RxJava](#101-introducción-a-la-programación-reactiva-y-rxjava)
      - [10.2. Componentes principales de RxJava](#102-componentes-principales-de-rxjava)
        - [Ejemplos de Flujos Fríos y Calientes](#ejemplos-de-flujos-fríos-y-calientes)
          - [Ejemplo 1: Flujo frío (lectura asíncrona de fichero)](#ejemplo-1-flujo-frío-lectura-asíncrona-de-fichero)
          - [Ejemplo 2: Flujo caliente (sistema de notificaciones)](#ejemplo-2-flujo-caliente-sistema-de-notificaciones)
      - [10.4. Tipos especializados de `Observable`](#104-tipos-especializados-de-observable)
      - [10.5. La contrapresión (backpressure): `Flowable` en detalle](#105-la-contrapresión-backpressure-flowable-en-detalle)
      - [10.6. Operadores: Transformación, filtrado y combinación](#106-operadores-transformación-filtrado-y-combinación)
      - [10.7. Casos de uso y mejores prácticas](#107-casos-de-uso-y-mejores-prácticas)
    - [11. Railway Oriented Programming](#11-railway-oriented-programming)
      - [11.1. El problema de la gestión de errores con excepciones](#111-el-problema-de-la-gestión-de-errores-con-excepciones)
      - [11.2. Introducción a Railway Oriented Programming](#112-introducción-a-railway-oriented-programming)
      - [11.3. La clase `Either` de Vavr](#113-la-clase-either-de-vavr)
      - [11.4. Encadenamiento de operaciones y manejo de errores](#114-encadenamiento-de-operaciones-y-manejo-de-errores)
      - [11.5. Más operadores clave de Vavr `Either`](#115-más-operadores-clave-de-vavr-either)
      - [11.6. Beneficios y casos de uso en aplicaciones Java](#116-beneficios-y-casos-de-uso-en-aplicaciones-java)
    - [12. Retrofit: Clientes REST con Java](#12-retrofit-clientes-rest-con-java)
      - [12.1. Configuración: Clases de cliente y modelos de datos](#121-configuración-clases-de-cliente-y-modelos-de-datos)
      - [12.2. Operaciones CRUD con una API REST](#122-operaciones-crud-con-una-api-rest)
        - [12.2.1. CREATE: Creación de un producto (`@POST`)](#1221-create-creación-de-un-producto-post)
        - [12.2.2. READ: Lectura de productos (`@GET`)](#1222-read-lectura-de-productos-get)
        - [12.2.3. UPDATE: Actualización de un producto (`@PUT`)](#1223-update-actualización-de-un-producto-put)
        - [12.2.4. DELETE: Eliminación de un producto (`@DELETE`)](#1224-delete-eliminación-de-un-producto-delete)
      - [12.3. Manejo de respuestas y errores](#123-manejo-de-respuestas-y-errores)
      - [12.4. Alternativas a `Call`: Programación asíncrona con Retrofit](#124-alternativas-a-call-programación-asíncrona-con-retrofit)
        - [12.4.1. Uso de `CompletableFuture` (Java 8+)](#1241-uso-de-completablefuture-java-8)
        - [12.4.2. Uso con RxJava y programación reactiva](#1242-uso-con-rxjava-y-programación-reactiva)
    - [13. Pruebas y herramientas](#13-pruebas-y-herramientas)
      - [13.1. Pruebas unitarias](#131-pruebas-unitarias)
        - [13.1.1. JUnit para pruebas unitarias](#1311-junit-para-pruebas-unitarias)
        - [13.1.2. Mockito para la creación de objetos simulados (mocks)](#1312-mockito-para-la-creación-de-objetos-simulados-mocks)
    - [13.2. Gestión de proyectos y documentación](#132-gestión-de-proyectos-y-documentación)
        - [13.2.1. Gradle para la gestión de proyectos y dependencias](#1321-gradle-para-la-gestión-de-proyectos-y-dependencias)
        - [13.2.2. Javadocs para la documentación del código](#1322-javadocs-para-la-documentación-del-código)
    - [14. Dockerización de aplicaciones de servidor](#14-dockerización-de-aplicaciones-de-servidor)
      - [14.1. ¿Qué es Docker?](#141-qué-es-docker)
      - [14.2. Docker Compose](#142-docker-compose)
      - [14.3. Despliegue de aplicaciones Java (JVM)](#143-despliegue-de-aplicaciones-java-jvm)
        - [14.3.1. Despliegue simple](#1431-despliegue-simple)
        - [14.3.2. Despliegue con un Dockerfile multi-etapa](#1432-despliegue-con-un-dockerfile-multi-etapa)
      - [14.4. TestContainers y Docker](#144-testcontainers-y-docker)


---

### 1\. Introducción al lenguaje de programación Java

#### 1.1. ¿Qué es Java y por qué usarlo?

Java es un lenguaje de programación de propósito general, orientado a objetos y diseñado para ser portable, lo que significa que el código escrito en Java puede ejecutarse en cualquier plataforma que disponga de una **Java Virtual Machine (JVM)**. Sus principales características son:

- **Orientado a objetos**: Todo en Java es un objeto, lo que facilita el desarrollo modular y reutilizable.
- **Multiplataforma**: La filosofía "**escribe una vez, ejecuta en cualquier lugar**" permite que las aplicaciones Java funcionen en distintos sistemas operativos (Windows, macOS, Linux) sin necesidad de recompilaciones.
- **Seguro**: La JVM ofrece un entorno controlado para evitar que el código malicioso acceda a recursos del sistema.
- **Robusto**: Tiene un fuerte sistema de tipado y un manejo de memoria automático (recolector de basura), lo que reduce la posibilidad de errores.
- **Gran comunidad y ecosistema**: Existe una enorme cantidad de librerías, frameworks y herramientas que aceleran el desarrollo.

#### 1.2. El proceso de compilación y ejecución: El papel de la JVM

Comprender este proceso es fundamental para entender cómo funciona Java. A diferencia de otros lenguajes que se compilan directamente a código máquina, Java utiliza un paso intermedio.

1.  **Compilación**: El código fuente, escrito en un archivo con extensión `.java`, es procesado por el **compilador de Java (javac)**. Este paso verifica la sintaxis y transforma el código legible para humanos en un formato intermedio llamado **bytecode**, almacenado en un archivo con extensión `.class`. Este bytecode no es específico de una máquina, lo que le da a Java su portabilidad.

    **Ejemplo**:
    Supongamos que tienes un archivo `HolaMundo.java`:

    ```java
    public class HolaMundo {
        public static void main(String[] args) {
            System.out.println("Hola, mundo!");
        }
    }
    ```

    Para compilarlo, usas el comando:

    ```bash
    javac HolaMundo.java
    ```

    Esto creará el archivo `HolaMundo.class`.

2.  **Ejecución**: El archivo `.class` (bytecode) es interpretado y ejecutado por la **Java Virtual Machine (JVM)**. La JVM es el corazón del ecosistema Java. Cada sistema operativo tiene su propia versión de JVM, que es la encargada de traducir el bytecode a código máquina específico del sistema en tiempo real. Esto permite que el mismo archivo `.class` se ejecute en cualquier computadora con una JVM instalada.

    **Ejemplo**:
    Para ejecutar el archivo `.class`, usas el comando:

    ```bash
    java HolaMundo
    ```

    La JVM cargará el `bytecode` y lo ejecutará, mostrando la salida por consola:

    ```
    Hola, mundo!
    ```

En resumen, el proceso de dos pasos de Java (compilación a bytecode y luego ejecución por la JVM) es lo que le otorga su independencia de plataforma, haciéndolo un lenguaje robusto y versátil para el desarrollo.

---

### 2\. Fundamentos de la programación en Java

#### 2.1. Sintaxis básica, tipos de datos y estructuras de control

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

#### 2.2. Arrays: Tipos compuestos

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

#### 2.3. Modificadores de acceso: `public`, `private` y `protected`

Estos modificadores controlan la **visibilidad** de los miembros de una clase, un concepto fundamental para la **encapsulación**.

- `public`: Accesible desde cualquier lugar.
- `private`: Solo accesible dentro de la misma clase.
- `protected`: Accesible en la misma clase, en el mismo paquete y en subclases.

#### 2.4. Modificadores de comportamiento: `static` y `final`

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

---

### 3\. Programación orientada a objetos (POO)

La POO es un paradigma de programación que utiliza el concepto de "objetos" para modelar el mundo real. En lugar de escribir una serie de instrucciones secuenciales, se organizan los datos y el comportamiento en unidades lógicas.

A continuación, se completa el apartado 3.1 con los detalles sobre constructores, *getters* y *setters*.

-----

#### 3.1. Clases, objetos, atributos y métodos

  - **Clase**: Es el "plano" o la plantilla a partir de la cual se crean los objetos. Define la estructura de los objetos, incluyendo sus **atributos** (las características o datos) y **métodos** (el comportamiento o las acciones). No puedes usar una clase directamente, debes crear una instancia de ella.
  - **Objeto**: Es una instancia de una clase. Cada objeto tiene su propia copia de los atributos definidos en la clase. Es la entidad real con la que interactúas en el programa.
  - **Atributo**: Una variable que pertenece a una clase y define una de sus propiedades. Por ejemplo, en una clase `Coche`, los atributos podrían ser `marca`, `modelo` y `color`.
  - **Método**: Una función que pertenece a una clase. Define el comportamiento de un objeto. Siguiendo con la clase `Coche`, los métodos podrían ser `acelerar()`, `frenar()` o `girar()`.

#### 3.2. Constructores, getters y setters

Estos tres componentes son la base para el manejo y la **encapsulación** de los datos de un objeto, un pilar fundamental de la Programación Orientada a Objetos.

  * **Constructores**: Son métodos especiales que se ejecutan cuando se crea un nuevo objeto de una clase. Su propósito principal es **inicializar los atributos** del objeto. Una clase puede tener múltiples constructores con diferentes parámetros (lo que se conoce como **sobrecarga de constructores**), lo que permite crear objetos de diversas maneras. Si no defines un constructor, Java crea uno por defecto sin parámetros.

  * ***Getters***: También conocidos como **métodos de acceso**, son funciones que se usan para **leer o obtener el valor** de un atributo. Siguen la convención de nombrar el método como `getNombreDelAtributo()`. Su uso principal es permitir el acceso a atributos que están declarados como `private`, garantizando un acceso controlado a la información.

  * ***Setters***: Conocidos como **métodos de mutación**, son funciones que se usan para **establecer o modificar el valor** de un atributo. La convención de nombres es `setNombreDelAtributo()`. Los *setters* son clave para la encapsulación, ya que pueden incluir lógica de validación para asegurar que el nuevo valor es válido antes de asignarlo, lo que previene que los datos del objeto queden en un estado inconsistente.

**Ejemplo completo:**

A continuación, se muestra una versión mejorada de la clase `Coche` con la implementación completa de constructores, *getters* y *setters* para garantizar un diseño robusto y seguro.

```java
// Clase
public class Coche {
    // Atributos privados para aplicar la encapsulación
    private String marca;
    private String modelo;
    private int velocidad;

    // Constructor con parámetros para inicializar el objeto al crearlo
    public Coche(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidad = 0; // Velocidad inicial por defecto
    }

    // Sobrecarga de constructor: para crear un objeto sin especificar marca/modelo
    public Coche() {
        this("Desconocida", "Desconocido");
    }

    // Getters para acceder a los atributos
    public String getMarca() {
        return this.marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public int getVelocidad() {
        return this.velocidad;
    }

    // Setters (o métodos de mutación)
    public void setVelocidad(int nuevaVelocidad) {
        // Lógica de validación
        if (nuevaVelocidad >= 0) {
            this.velocidad = nuevaVelocidad;
        }
    }

    // Métodos con lógica de negocio
    public void acelerar(int incremento) {
        this.velocidad += incremento;
        System.out.println("El coche " + this.marca + " ha acelerado a " + this.velocidad + " km/h.");
    }

    public void frenar(int decremento) {
        this.velocidad -= decremento;
        if (this.velocidad < 0) this.velocidad = 0;
        System.out.println("El coche " + this.marca + " ha frenado a " + this.velocidad + " km/h.");
    }
}

// Creación y uso de objetos en otra clase
public class Concesionario {
    public static void main(String[] args) {
        // Crear un objeto (instancia de la clase Coche) usando el constructor
        Coche miCoche = new Coche("Ford", "Focus");

        // Usar los getters para obtener el estado del objeto
        System.out.println("Mi coche es un " + miCoche.getMarca() + " " + miCoche.getModelo());
        
        // Usar los métodos para cambiar el estado del objeto
        miCoche.acelerar(50);
        miCoche.frenar(10);
        
        // Usar un setter para cambiar la velocidad directamente
        miCoche.setVelocidad(100);
        System.out.println("Velocidad actual: " + miCoche.getVelocidad());
    }
}
```

#### 3.2. Principios de la POO

Estos cuatro pilares son fundamentales para diseñar software de forma eficiente, escalable y mantenible.

- **3.2.1. Encapsulamiento**: Es el principio de agrupar los datos (atributos) y los métodos que operan sobre esos datos en una sola unidad (la clase), ocultando la implementación interna. Esto se logra principalmente con los modificadores de acceso (`private`, `public`, `protected`) y los métodos **"getters" y "setters"**. Ocultar los datos a través de la encapsulación se conoce como **ocultación de información**.

  **Ejemplo**:

  ```java
  public class CuentaBancaria {
      private double saldo; // El saldo es privado, no accesible directamente

      public CuentaBancaria(double saldoInicial) {
          this.saldo = saldoInicial;
      }

      // Getter para acceder al saldo de forma controlada
      public double getSaldo() {
          return this.saldo;
      }

      // Setter para modificar el saldo de forma segura
      public void depositar(double monto) {
          if (monto > 0) {
              this.saldo += monto;
          }
      }
  }
  ```

  En este ejemplo, no puedes modificar el `saldo` directamente desde fuera de la clase; debes usar el método `depositar()`, que incluye una lógica de validación.

- **3.2.2. Herencia**: Permite a una nueva clase (subclase o clase hija) heredar los atributos y métodos de una clase existente (superclase o clase padre). La palabra clave para esto es `extends`. La herencia promueve la reutilización de código. Una clase solo puede heredar de una única superclase en Java (herencia simple).

  **Ejemplo**:

  ```java
  public class Vehiculo {
      protected int ruedas;
      public void pitar() {
          System.out.println("¡Pi-pi!");
      }
  }

  // La clase Coche hereda de Vehiculo
  public class Coche extends Vehiculo {
      public Coche() {
          this.ruedas = 4;
      }
  }
  ```

- **3.2.3. Polimorfismo**: Significa "muchas formas". Permite que objetos de diferentes clases, que están relacionadas por herencia, sean tratados como objetos de una superclase común. Esto se logra mediante la **sobrescritura de métodos (`@Override`)** o la **sobrecarga de métodos**.

  **Ejemplo de Polimorfismo por sobrescritura**:

  ```java
  public class Animal {
      public void sonido() {
          System.out.println("El animal hace un sonido.");
      }
  }
  public class Gato extends Animal {
      @Override
      public void sonido() {
          System.out.println("El gato maúlla.");
      }
  }
  public class Perro extends Animal {
      @Override
      public void sonido() {
          System.out.println("El perro ladra.");
      }
  }

  // Uso polimórfico
  public class Main {
      public static void main(String[] args) {
          Animal miAnimal = new Gato();
          miAnimal.sonido(); // Llama al método de Gato, Salida: El gato maúlla.

          miAnimal = new Perro();
          miAnimal.sonido(); // Llama al método de Perro, Salida: El perro ladra.
      }
  }
  ```

- **3.2.4. Abstracción**: Se centra en mostrar solo los detalles esenciales del objeto al usuario, ocultando la complejidad de la implementación interna. Se logra a través de **clases abstractas** e **interfaces**.

#### 3.3. Interfaces y clases abstractas

Ambos son mecanismos clave para lograr la **abstracción**.

- **Clases abstractas**: Son clases que no se pueden instanciar directamente y pueden contener métodos `abstract` (sin implementación) y métodos concretos (con implementación). Una subclase que hereda de una clase abstracta debe proporcionar una implementación para todos los métodos abstractos, a menos que también sea abstracta.

  **Ejemplo**:

  ```java
  public abstract class FiguraGeometrica {
      public abstract double calcularArea(); // Método abstracto sin cuerpo
      public void mostrarMensaje() { // Método concreto
          System.out.println("Esto es una figura geométrica.");
      }
  }
  public class Circulo extends FiguraGeometrica {
      private double radio;
      public Circulo(double radio) { this.radio = radio; }
      @Override
      public double calcularArea() {
          return Math.PI * radio * radio;
      }
  }
  ```

- **Interfaces**: Son un "contrato" que una clase debe cumplir. Contienen solo métodos abstractos (implícitamente) y constantes. Una clase puede `implements` (implementar) múltiples interfaces, lo que permite a Java superar la limitación de la herencia simple.

  **Ejemplo**:

  ```java
  public interface Volador {
      void despegar();
      void aterrizar();
  }
  public class Avion implements Volador {
      @Override
      public void despegar() {
          System.out.println("El avión está despegando.");
      }
      @Override
      public void aterrizar() {
          System.out.println("El avión está aterrizando.");
      }
  }
  ```

  Una clase implementa una interfaz para garantizar que tendrá el comportamiento definido por la misma.

#### 3.4. Clases `abstract` y `sealed class`

- **Clases `abstract`**: Ya se han explicado, su propósito es ser heredadas para que las subclases implementen sus métodos abstractos.

- **Clases `sealed`**: (Añadidas en Java 17) Son clases que permiten controlar qué otras clases pueden heredar de ellas. Se usa la palabra clave `sealed` seguida de `permits` para enumerar las clases que tienen permiso para ser subclases. Esto ofrece un control granular sobre la jerarquía de herencia.

  **Ejemplo**:

  ```java
  public sealed class Forma permits Circulo, Cuadrado {
      // ...
  }
  final class Circulo extends Forma {
      // ...
  }
  final class Cuadrado extends Forma {
      // ...
  }
  // La siguiente clase no puede compilarse, ya que no está permitida.
  // public class Triangulo extends Forma { ... }
  ```

#### 3.5. Clases vs. `Records`

- **Clases**: Son la plantilla estándar para crear objetos, con atributos mutables, constructores, "getters", "setters" y cualquier lógica de negocio. Requieren que el programador escriba mucho código repetitivo.

- **`Records`**: (Añadidos en Java 16) Son un tipo de clase especial, inmutable y concisa, diseñada para ser un simple contenedor de datos. El compilador de Java genera automáticamente el constructor, los "getters" (conocidos como métodos de acceso), los métodos `equals()`, `hashCode()` y `toString()`. Son ideales para modelos de datos sencillos.

  **Ejemplo de clase `Record` vs. una clase normal**:

  ```java
  // Clase tradicional (código extenso)
  public class PersonaClasica {
      private final String nombre;
      private final int edad;

      public PersonaClasica(String nombre, int edad) {
          this.nombre = nombre;
          this.edad = edad;
      }

      public String getNombre() { return nombre; }
      public int getEdad() { return edad; }
      // y más métodos...
  }

  // Record (código conciso)
  public record PersonaRecord(String nombre, int edad) {
      // El compilador genera automáticamente los métodos nombre(), edad(), equals(), hashCode(), toString(), etc.
  }
  ```

  Como ves, el `Record` es perfecto para datos inmutables y reduce la cantidad de código.

#### 3.6. El uso de **`Optional`**

`Optional` es una clase (añadida en Java 8) que actúa como un contenedor para un valor que **puede o no estar presente**. Su propósito principal es evitar los temidos `NullPointerException` al manejar valores nulos, obligando al programador a considerar el caso en que un valor no exista.

**Ejemplo sin `Optional` (propenso a errores):**

```java
String nombre = getNombreDeUsuario(); // Podría devolver null
if (nombre != null) {
    System.out.println(nombre.toUpperCase());
}
```

**Ejemplo con `Optional` (más seguro):**

```java
// Suponiendo que este método devuelve un Optional<String>
Optional<String> nombreOpcional = getNombreDeUsuarioSeguro();

// Se puede usar el método isPresent() para verificar
if (nombreOpcional.isPresent()) {
    System.out.println(nombreOpcional.get().toUpperCase());
}

// O usar el método orElse(), que es más idiomático
String nombreFinal = nombreOpcional.orElse("Invitado");
System.out.println(nombreFinal.toUpperCase());
```

`Optional` mejora la claridad del código y lo hace más seguro al eliminar la necesidad de comprobaciones `if (valor != null)` en todo el programa.

### 3.7. El uso de genéricos

Los **genéricos** son una de las características más importantes de Java, introducida para permitirte escribir código más flexible y reutilizable. Te permiten definir clases, interfaces y métodos con un tipo de dato "paramétrico" o "variable", que se especifica en el momento de su uso. La principal ventaja es que proporcionan una **seguridad de tipos** en tiempo de compilación, evitando errores de `ClassCastException` en tiempo de ejecución.

#### ¿Por qué son necesarios los genéricos?

Antes de los genéricos, las colecciones como `ArrayList` almacenaban objetos del tipo `Object`. Esto significaba que podías mezclar diferentes tipos de datos, y luego tenías que hacer un _casting_ explícito para recuperar el tipo correcto.

**Sin genéricos:**

```java
List lista = new ArrayList();
lista.add("Hola");
lista.add(123); // Esto está permitido y es un error de diseño

// En tiempo de ejecución, esto lanza una ClassCastException
String s = (String) lista.get(1);
```

Con los genéricos, el compilador se asegura de que la colección solo contenga el tipo de dato que has especificado.

**Con genéricos:**

```java
List<String> listaString = new ArrayList<String>();
listaString.add("Hola");
// listaString.add(123); // Error de compilación: no puedes añadir un Integer
String s = listaString.get(0); // No se necesita casting
```

#### Clases y métodos genéricos

Puedes crear tus propias clases y métodos genéricos, lo cual es fundamental para crear bibliotecas y _frameworks_ robustos.

**Ejemplo de clase genérica:**

Imagina que quieres una clase `Caja` que pueda contener cualquier tipo de objeto.

```java
// La clase Caja usa el tipo T como un parámetro genérico
public class Caja<T> {
    private T contenido;

    public Caja(T contenido) {
        this.contenido = contenido;
    }

    public T getContenido() {
        return contenido;
    }
}
```

**Uso de la clase genérica:**

```java
// Creamos una caja para String
Caja<String> cajaDeTexto = new Caja<>("Un mensaje");
String mensaje = cajaDeTexto.getContenido(); // El tipo es String, sin casting

// Creamos una caja para Integer
Caja<Integer> cajaDeNumero = new Caja<>(100);
int numero = cajaDeNumero.getContenido(); // El tipo es Integer, sin casting
```

**Métodos genéricos:**

También puedes definir métodos genéricos de forma independiente a la clase en la que se encuentran. La sintaxis para un método genérico es colocar la declaración del tipo (`<T>`) antes del tipo de retorno.

**Ejemplo de método genérico:**

```java
public class Utilidades {
    // Método genérico que imprime un array de cualquier tipo
    public static <E> void imprimirArray(E[] array) {
        for (E elemento : array) {
            System.out.print(elemento + " ");
        }
        System.out.println();
    }
}

// Uso del método genérico
Integer[] numeros = {1, 2, 3};
String[] nombres = {"Ana", "Luis", "Carlos"};

Utilidades.imprimirArray(numeros); // Funciona con Integer
Utilidades.imprimirArray(nombres); // Funciona con String
```

#### Restricciones de tipos (`Bounded Type Parameters`)

A veces, necesitas restringir los tipos que pueden ser usados como parámetros genéricos. Por ejemplo, en un método que suma números, solo tiene sentido que el tipo genérico sea un `Number` o una subclase. Para esto se usa la palabra clave `extends`.

**Ejemplo de restricción:**

```java
// El tipo T debe ser Number o una subclase de Number
public <T extends Number> T sumarNumeros(T a, T b) {
    // Lógica para sumar...
    return a; // Solo un ejemplo
}
```

### 4\. Manejo de errores y excepciones

En Java, una **excepción** es un evento que ocurre durante la ejecución de un programa y que interrumpe el flujo normal de las instrucciones. El manejo adecuado de estas excepciones es crucial para crear aplicaciones robustas y fiables.

#### 4.1. Tipos de excepciones: `checked` vs. `unchecked`

Las excepciones en Java se dividen en dos categorías principales.

- **Excepciones `checked` (comprobadas)**:

  - El compilador **obliga** a que estas excepciones sean manejadas, ya sea con un bloque `try-catch` o declarándolas con la palabra clave `throws` en la firma del método.
  - Generalmente, son excepciones que un programador puede prever y manejar, como un archivo que no se encuentra (`FileNotFoundException`) o un problema de red (`IOException`).
  - **Ejemplo**:

    ```java
    import java.io.FileReader;
    import java.io.IOException;

    public class LectorArchivo {
        public void leer() throws IOException {
            // El compilador exige manejar esta excepción
            FileReader archivo = new FileReader("mi_archivo.txt");
        }
    }
    ```

- **Excepciones `unchecked` (no comprobadas)**:

  - El compilador **no obliga** a manejarlas. Son subtipos de `RuntimeException`.
  - Generalmente, son causadas por errores de lógica o de programación que podrían haberse evitado.
  - **Ejemplos comunes**: `NullPointerException` (acceder a un objeto nulo) o `ArrayIndexOutOfBoundsException` (acceder a un índice de array fuera de los límites).
  - **Ejemplo**:
    ```java
    int[] numeros = {1, 2, 3};
    // No se requiere try-catch para esta excepción
    System.out.println(numeros[5]); // Lanza un ArrayIndexOutOfBoundsException
    ```

#### 4.2. Manejo de excepciones: `try-catch-finally`

La estructura `try-catch-finally` es la forma más común de manejar excepciones.

- **`try`**: Encierra el código que podría lanzar una excepción.
- **`catch`**: Si una excepción ocurre en el bloque `try`, el control pasa al bloque `catch`. Aquí se especifica el tipo de excepción que se va a manejar.
- **`finally`**: Opcional, pero si está presente, el código en este bloque se ejecuta siempre, haya o no ocurrido una excepción. Es útil para limpiar recursos, como cerrar un archivo o una conexión a la base de datos.

**Ejemplo completo:**

```java
public class ManejoExcepciones {
    public static void main(String[] args) {
        try {
            int resultado = 10 / 0; // Esto lanzará un ArithmeticException
            System.out.println(resultado);
        } catch (ArithmeticException e) {
            System.err.println("Error: No se puede dividir entre cero.");
        } finally {
            System.out.println("Este bloque siempre se ejecuta.");
        }

        // Se pueden usar múltiples catch para diferentes tipos de excepciones
        try {
            String texto = null;
            System.out.println(texto.length());
        } catch (NullPointerException e) {
            System.err.println("Error: El texto es nulo.");
        } catch (Exception e) {
            // Un catch genérico para cualquier otra excepción
            System.err.println("Ocurrió un error inesperado.");
        }
    }
}
```

#### 4.3. El uso de `try-with-resources`

Añadido en Java 7, esta estructura es una mejora del `try-catch` tradicional. Su objetivo es asegurar que los recursos que implementan la interfaz `AutoCloseable` (como archivos o conexiones a bases de datos) se cierran automáticamente, incluso si ocurre una excepción.

**Ejemplo sin `try-with-resources` (requiere `finally`):**

```java
// Código propenso a errores si no se cierra correctamente el recurso
FileReader reader = null;
try {
    reader = new FileReader("mi_archivo.txt");
    // ... leer del archivo
} catch (IOException e) {
    System.err.println("Error de E/S.");
} finally {
    if (reader != null) {
        try {
            reader.close(); // Se debe cerrar explícitamente
        } catch (IOException e) {
            System.err.println("Error al cerrar el archivo.");
        }
    }
}
```

**Ejemplo con `try-with-resources` (más limpio y seguro):**

```java
// El recurso se declara dentro del paréntesis y se cierra automáticamente
try (FileReader reader = new FileReader("mi_archivo.txt")) {
    // ... leer del archivo
    System.out.println("Archivo leído exitosamente.");
} catch (IOException e) {
    System.err.println("Error de E/S: " + e.getMessage());
}
// El reader se cierra automáticamente, sin necesidad de un bloque finally
```

#### 4.4. Creación de excepciones personalizadas

Puedes crear tus propias clases de excepción para manejar errores específicos de tu aplicación. Esto mejora la legibilidad del código y permite a otros desarrolladores entender mejor qué tipo de problema ha ocurrido.

- Para crear una excepción **`checked`** personalizada, la clase debe heredar de `Exception`.
- Para crear una excepción **`unchecked`** personalizada, la clase debe heredar de `RuntimeException`.

**Ejemplo de excepción personalizada (`checked`):**

```java
public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}

public class CuentaBancaria {
    private double saldo;

    public void retirar(double monto) throws SaldoInsuficienteException {
        if (monto > saldo) {
            // Lanza la excepción personalizada
            throw new SaldoInsuficienteException("No hay suficiente saldo para el retiro.");
        }
        this.saldo -= monto;
        System.out.println("Retiro exitoso. Saldo actual: " + this.saldo);
    }
}

// Uso en otra clase
public class Cajero {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria(100);
        try {
            cuenta.retirar(150);
        } catch (SaldoInsuficienteException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
```

### 5\. Programación funcional

La **programación funcional** es un estilo de programación que trata la computación como la evaluación de **funciones matemáticas** y evita el estado mutable y los datos cambiantes. En Java, se implementa a través de las interfaces funcionales y las expresiones lambda. El objetivo es crear un código más predecible, seguro y fácil de probar al reducir los efectos secundarios.

#### 5.1. Funciones de primera clase y funciones de orden superior

En la programación funcional, las funciones son "ciudadanos de primera clase". Esto significa que pueden:

- Asignarse a variables.
- Pasarse como argumentos a otras funciones.
- Devolverse como el resultado de una función.

Esto nos lleva a las **funciones de orden superior**, que son las que operan sobre otras funciones. El método `stream().filter()` es un claro ejemplo de función de orden superior, ya que toma una función (una lambda) como argumento para decidir qué elementos filtrar.

- **Ejemplo de una función de orden superior personalizada:**

  ```java
  import java.util.function.Predicate;

  // Predicate<T> es una interfaz funcional que toma un T y devuelve un boolean
  // Esta función de orden superior devuelve una Predicate.
  public static Predicate<Integer> esMayorQue(int numero) {
      return n -> n > numero;
  }

  // Uso de la función de orden superior
  Predicate<Integer> mayorQueDiez = esMayorQue(10);
  System.out.println(mayorQueDiez.test(15)); // Salida: true
  System.out.println(mayorQueDiez.test(5));  // Salida: false
  ```

En este ejemplo, `esMayorQue` es una función de orden superior porque devuelve una función (`Predicate`).

#### 5.2. `Lambdas`, interfaces funcionales y `Function`

Una **expresión lambda** es la sintaxis de Java para crear una función anónima. La clave para usarlas es una **interfaz funcional**, es decir, una interfaz con un solo método abstracto. La anotación `@FunctionalInterface` se usa para marcar estas interfaces.

- **Interfaz funcional personalizada:**
  ```java
  @FunctionalInterface
  interface Operacion {
      int aplicar(int a, int b);
  }
  ```
- **Ejemplo de uso de la interfaz con una lambda:**

  ```java
  Operacion suma = (a, b) -> a + b;
  System.out.println(suma.aplicar(5, 3)); // Salida: 8

  Operacion resta = (a, b) -> a - b;
  System.out.println(resta.aplicar(5, 3)); // Salida: 2
  ```

Java ya incluye muchas interfaces funcionales predefinidas como **`Function<T, R>`** (transforma T en R), **`Predicate<T>`** (evalúa un booleano), **`Consumer<T>`** (ejecuta una acción sin devolver nada) y **`Supplier<T>`** (suministra un valor).

#### 5.3. Inmutabilidad y ausencia de efectos secundarios

Un principio fundamental de la programación funcional es la **inmutabilidad**. Esto significa que los datos no cambian de valor una vez creados. En lugar de modificar una variable, se crea una nueva con el resultado. Este enfoque reduce errores y hace que el código sea más seguro en entornos concurrentes.

- **Código imperativo (mutable):**
  ```java
  // La lista original se modifica
  List<String> nombres = new ArrayList<>(Arrays.asList("Ana", "Juan"));
  nombres.add("Pedro");
  ```
- **Código funcional (inmutable):**
  ```java
  // Se crea una nueva lista sin modificar la original
  List<String> nombresInmutables = List.of("Ana", "Juan");
  List<String> nuevosNombres = Stream.concat(nombresInmutables.stream(), Stream.of("Pedro")).collect(Collectors.toList());
  ```

Relacionado con esto, una **función pura** es una que, dadas las mismas entradas, siempre produce la misma salida y no causa efectos secundarios (no modifica variables globales, no hace operaciones de E/S, etc.). La programación funcional anima a escribir funciones puras, lo que facilita la depuración y el testeo.

#### 5.4. Composición de funciones

La programación funcional te permite encadenar múltiples operaciones para lograr un resultado complejo de manera concisa. La sintaxis de los **Streams** es el ejemplo perfecto de esto. Cada operación intermedia devuelve un nuevo `Stream`, permitiendo la **composición de funciones** hasta que una operación terminal produce un resultado final.

- **Ejemplo de composición:**
  ```java
  lista.stream()
      .filter(p -> p.categoria.equals("Electrónica")) // Filtra productos
      .map(p -> p.nombre) // Mapea a nombres
      .sorted() // Ordena los nombres
      .forEach(System.out::println); // Imprime cada nombre
  ```

En este caso, estás componiendo `filter`, `map`, `sorted` y `forEach` en una sola expresión fluida.

Genial, aquí tienes el desarrollo del nuevo punto 6 de la guía, que combina el uso de colecciones y streams. Este es un tema crucial para manipular datos de manera eficiente en Java.

### 6\. Colecciones y Streams

El **Framework de Colecciones** de Java proporciona estructuras de datos dinámicas para agrupar objetos. Los **Streams** de Java 8, por su parte, te permiten procesar esas colecciones de manera declarativa, concisa y expresiva, aprovechando los conceptos de la programación funcional que hemos visto.

#### 6.1. Estructuras de datos: Listas, conjuntos y mapas

Las colecciones son la base para almacenar y organizar datos. A diferencia de los arrays, su tamaño puede cambiar dinámicamente. Las tres interfaces principales son:

- **`List`**: Una colección **ordenada** que permite **elementos duplicados**. Se accede a los elementos por su índice.

  - **Cuándo usarla**: Cuando el orden de los elementos es importante y los duplicados son aceptables (ej., una lista de la compra, el historial de navegación).
  - **Implementaciones comunes**:
    - **`ArrayList`**: Ideal para el acceso rápido a los elementos por índice (`get()`). Es muy eficiente en la lectura.
    - **`LinkedList`**: Eficiente para insertar o eliminar elementos en el medio de la lista.

  <!-- end list -->

  ```java
  List<String> frutas = new ArrayList<>();
  frutas.add("Manzana");
  frutas.add("Naranja");
  frutas.add("Manzana"); // Duplicado permitido
  System.out.println(frutas); // Salida: [Manzana, Naranja, Manzana]
  ```

- **`Set`**: Una colección que **no permite elementos duplicados** y no garantiza un orden específico.

  - **Cuándo usarla**: Cuando necesitas una colección de elementos únicos (ej., los nombres de usuario únicos en una base de datos, las palabras únicas de un texto).
  - **Implementaciones comunes**:
    - **`HashSet`**: Ofrece un rendimiento muy alto para la mayoría de las operaciones (agregar, eliminar, buscar). No garantiza el orden de los elementos.
    - **`TreeSet`**: Mantiene los elementos ordenados de forma natural (por ejemplo, alfabéticamente para Strings o numéricamente para enteros).

  <!-- end list -->

  ```java
  Set<String> nombresUnicos = new HashSet<>();
  nombresUnicos.add("Ana");
  nombresUnicos.add("Juan");
  nombresUnicos.add("Ana"); // Ignorado por ser un duplicado
  System.out.println(nombresUnicos); // Salida: [Ana, Juan] (el orden puede variar)
  ```

- **`Map`**: Una estructura que almacena pares de **clave-valor**, donde cada clave es única.

  - **Cuándo usarla**: Cuando necesitas buscar un valor a partir de una clave única (ej., el DNI de una persona para obtener sus datos, el código de un producto para ver su precio).
  - **Implementaciones comunes**:
    - **`HashMap`**: La implementación más utilizada. Ofrece un rendimiento muy rápido para buscar, insertar y eliminar, pero no garantiza el orden.
    - **`TreeMap`**: Mantiene las claves ordenadas de forma natural.

  <!-- end list -->

  ```java
  Map<String, Integer> notas = new HashMap<>();
  notas.put("Juan", 9);
  notas.put("Ana", 10);
  System.out.println(notas.get("Juan")); // Salida: 9
  ```

####  6.2. Streams: Procesamiento de datos de forma declarativa

Un **Stream** no es una estructura de datos que almacene elementos, sino una **secuencia de elementos** que se extraen de una fuente (como una colección) para ser procesados. La principal ventaja es que te permite escribir un código **declarativo**, que se enfoca en el "qué" se quiere lograr, en lugar del "cómo" (el enfoque imperativo de los bucles `for`). Los Streams promueven la inmutabilidad de los datos originales.

El ciclo de vida de un Stream se divide en tres partes:

1.  **Fuente**: La colección, array o fuente de datos de donde proviene el Stream. Se crea con `.stream()`.
2.  **Operaciones intermedias**: Se encadenan para transformar el Stream. No ejecutan la operación; la preparan.
3.  **Operación terminal**: Cierra el Stream y produce un resultado o un efecto.

A continuación, se describen las operaciones más importantes con un ejemplo práctico con una lista de productos.

```java
class Producto {
    String nombre;
    double precio;
    String categoria;
    public Producto(String nombre, double precio, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }
}
List<Producto> productos = Arrays.asList(
    new Producto("Laptop", 1200.0, "Electrónica"),
    new Producto("Ratón", 25.0, "Electrónica"),
    new Producto("Libro", 15.0, "Hogar")
);
```

##### Operaciones comunes con Streams

- **`filter()`**: Selecciona elementos que cumplen una condición.

  ```java
  // Filtra los productos de la categoría "Electrónica"
  List<Producto> electronica = productos.stream()
      .filter(p -> p.categoria.equals("Electrónica"))
      .collect(Collectors.toList());
  System.out.println(electronica.size()); // Salida: 2
  ```

- **`map()`**: Transforma cada elemento del Stream en uno nuevo.

  ```java
  // Obtiene solo los nombres de los productos
  List<String> nombres = productos.stream()
      .map(p -> p.nombre)
      .collect(Collectors.toList());
  System.out.println(nombres); // Salida: [Laptop, Ratón, Libro]
  ```

- **`forEach()`**: Realiza una acción en cada elemento. Es una operación terminal.

  ```java
  // Imprime los productos con un precio superior a 100
  productos.stream()
      .filter(p -> p.precio > 100)
      .forEach(p -> System.out.println(p.nombre));
  // Salida: Laptop
  ```

- **`sorted()`**: Ordena los elementos.

  ```java
  List<String> nombresOrdenados = productos.stream()
      .map(p -> p.nombre)
      .sorted()
      .collect(Collectors.toList());
  System.out.println(nombresOrdenados); // Salida: [Laptop, Libro, Ratón]
  ```

- **`groupingBy()`**: Agrupa elementos en un `Map` por una propiedad común. Es ideal para análisis de datos.

  ```java
  Map<String, List<Producto>> productosPorCategoria = productos.stream()
      .collect(Collectors.groupingBy(p -> p.categoria));
  System.out.println(productosPorCategoria.get("Electrónica").size()); // Salida: 2
  ```

- **`reduce()`**: Combina los elementos de un Stream en un único resultado. Es la operación más versátil.

  ```java
  Optional<Double> precioTotal = productos.stream()
      .map(p -> p.precio)
      .reduce((acumulador, precio) -> acumulador + precio);
  System.out.println(precioTotal.get()); // Salida: 1240.0
  ```

  También puedes usar operaciones más específicas como `.sum()`, `.count()` y `.average()`, que son atajos a `reduce`.

####  6.3. `Parallel Streams`

Una de las mayores ventajas de los Streams es la facilidad para procesar colecciones de forma paralela. Simplemente cambiando `stream()` por **`parallelStream()`**, Java divide la colección en múltiples subtareas que se ejecutan simultáneamente en los diferentes núcleos del procesador. Esto mejora el rendimiento en operaciones que son costosas y no dependen del orden.

```java
// Procesamiento paralelo para grandes colecciones
productos.parallelStream()
    .filter(p -> p.precio > 100)
    .forEach(p -> System.out.println("Procesando: " + p.nombre));
// El orden de impresión no está garantizado, pero es más rápido.
```

### 6.4. Analogía con SQL

La filosofía de los Streams es muy similar a la de las consultas **SQL**. Este es un ejemplo para ilustrarlo:

| **Stream API (Java)** | **SQL**                 | **Descripción**                                 |
| :-------------------- | :---------------------- | :---------------------------------------------- |
| `productos.stream()`  | `FROM productos`        | Selecciona la fuente de datos.                  |
| `.filter(...)`        | `WHERE ...`             | Filtra los registros que cumplen una condición. |
| `.map(...)`           | `SELECT ...`            | Transforma los datos seleccionados.             |
| `.groupingBy(...)`    | `GROUP BY ...`          | Agrupa los resultados por una columna.          |
| `.count()` / `.sum()` | `COUNT(*)` / `SUM(...)` | Funciones de agregación.                        |

El uso de Streams te permite trabajar con colecciones de una forma **declarativa**, lo que significa que tu código es más legible y se parece a una descripción de lo que quieres, en lugar de una secuencia de pasos. Este es un cambio fundamental de los bucles tradicionales (imperativos) y un pilar del desarrollo moderno en Java.

---

### 7\. E/S (Entrada/Salida) y manipulación de ficheros

La entrada y salida, o E/S, es fundamental para que tu aplicación interactúe con el mundo exterior, ya sea a través de la consola o del sistema de archivos.

####  7.1. Entrada y salida por consola

Para mostrar información en la consola, se usa la clase `System`.

- `System.out.println()`: Imprime una línea de texto con un salto de línea.
- `System.out.print()`: Imprime texto sin un salto de línea.
- `System.err.println()`: Para mensajes de error, imprimiéndolos en la consola de errores.

Para crear mensajes de salida, puedes usar varios métodos:

1.  **Concatenación con `+`**: El método más simple, aunque puede ser menos legible.
    ```java
    String nombre = "Ana";
    int edad = 25;
    System.out.println("Hola, mi nombre es " + nombre + " y tengo " + edad + " años.");
    ```
2.  **`String.format()`**: Permite un formato de cadena similar a C con especificadores como `%s` para cadenas y `%d` para enteros.
    ```java
    String mensaje = String.format("Hola, mi nombre es %s y tengo %d años.", nombre, edad);
    System.out.println(mensaje);
    ```
3.  **Plantillas de texto (Text Blocks)**: Desde Java 15, puedes usar bloques de texto (`"""`) para crear cadenas multilínea sin caracteres de escape.
    ```java
    String json = """
        {
            "nombre": "Ana",
            "edad": 25
        }
    """;
    System.out.println(json);
    ```
4.  **String Templates (Vista Previa)**: A partir de Java 21, las plantillas de cadena ofrecen una forma más legible de combinar texto y expresiones.
    ```java
    String nombre = "Ana";
    int edad = 25;
    String info = STR."Hola, mi nombre es \{nombre} y tengo \{edad} años.";
    System.out.println(info);
    ```

Para la entrada de datos por consola, se utiliza la clase **`Scanner`**.

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
System.out.print("Introduce tu nombre: ");
String nombreUsuario = scanner.nextLine();
System.out.println("Tu nombre es: " + nombreUsuario);
scanner.close();
```

####  7.2. Lectura y escritura de ficheros de texto

Para manipular archivos de texto, Java ofrece APIs clásicas (`java.io`) y modernas (`java.nio.file`).

##### API Clásica (`java.io`) con `try-with-resources`

Esta API es la base histórica de Java para E/S. El uso del bloque **`try-with-resources`** es crucial para garantizar que los recursos como los archivos se cierren automáticamente, incluso si ocurre una excepción.

- **Escritura con `BufferedWriter`**: Utiliza un buffer para escribir de manera más eficiente, reduciendo las operaciones de disco.

  ```java
  import java.io.BufferedWriter;
  import java.io.FileWriter;
  import java.io.IOException;

  try (BufferedWriter writer = new BufferedWriter(new FileWriter("saludo.txt"))) {
      writer.write("Hola, este es un mensaje.");
      writer.newLine();
      writer.write("Escrito desde Java.");
      System.out.println("Archivo 'saludo.txt' creado con éxito.");
  } catch (IOException e) {
      System.err.println("Error al escribir en el archivo: " + e.getMessage());
  }
  ```

- **Lectura con `BufferedReader`**: Permite leer líneas completas de forma eficiente.

  ```java
  import java.io.BufferedReader;
  import java.io.FileReader;
  import java.io.IOException;

  try (BufferedReader reader = new BufferedReader(new FileReader("saludo.txt"))) {
      String linea;
      System.out.println("Contenido del archivo:");
      while ((linea = reader.readLine()) != null) {
          System.out.println(linea);
      }
  } catch (IOException e) {
      System.err.println("Error al leer el archivo: " + e.getMessage());
  }
  ```

##### API Moderna (`java.nio.file`) con Streams

La API NIO.2 (`Path`, `Paths`, `Files`) es más robusta, eficiente y se integra mejor con las funcionalidades de Java 8 como los Streams.

- **Escritura con `Files.writeString`**: Un método conciso para escribir todo el contenido de una cadena en un archivo.

  ```java
  import java.io.IOException;
  import java.nio.file.Files;
  import java.nio.file.Path;
  import java.nio.file.Paths;

  Path rutaArchivo = Paths.get("saludo-nio.txt");
  String contenido = "Hola, este es un mensaje.\nEscrito con NIO.2 desde Java.";
  try {
      Files.writeString(rutaArchivo, contenido);
      System.out.println("Archivo 'saludo-nio.txt' creado con éxito.");
  } catch (IOException e) {
      System.err.println("Error al escribir en el archivo: " + e.getMessage());
  }
  ```

- **Lectura con `Files.lines` y Streams**: La forma más moderna de leer archivos, ya que procesa el contenido línea por línea sin cargar todo el archivo en memoria, ideal para archivos grandes.

  ```java
  import java.io.IOException;
  import java.nio.file.Files;
  import java.nio.file.Path;
  import java.nio.file.Paths;

  Path rutaArchivo = Paths.get("saludo-nio.txt");
  try {
      System.out.println("Contenido del archivo (usando Streams):");
      Files.lines(rutaArchivo)
          .forEach(System.out::println);
  } catch (IOException e) {
      System.err.println("Error al leer el archivo: " + e.getMessage());
  }
  ```

####  7.3. Manejo de datos JSON con bibliotecas como Jackson

El formato **JSON** es el estándar para el intercambio de datos. Java no lo soporta de forma nativa robusta, por lo que se usan bibliotecas como **Jackson** para convertir objetos de Java a JSON (serialización) y viceversa (deserialización).

- **Serialización (objeto a JSON)**:

  ```java
  import com.fasterxml.jackson.databind.ObjectMapper;
  import java.util.List;
  import java.util.Arrays;

  class Producto {
      public String nombre;
      public double precio;
      public Producto(String nombre, double precio) {
          this.nombre = nombre;
          this.precio = precio;
      }
  }
  public class SerializarProductos {
      public static void main(String[] args) {
          ObjectMapper mapper = new ObjectMapper();
          List<Producto> productos = Arrays.asList(
              new Producto("Teclado", 75.0),
              new Producto("Ratón", 25.0)
          );
          try {
              String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productos);
              System.out.println(json);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
  }
  ```

- **Deserialización (JSON a objeto)**:

  ```java
  import com.fasterxml.jackson.core.type.TypeReference;
  import com.fasterxml.jackson.databind.ObjectMapper;
  import java.util.List;

  public class DeserializarProductos {
      public static void main(String[] args) {
          String jsonEntrada = "[{\"nombre\":\"Teclado\",\"precio\":75.0},{\"nombre\":\"Ratón\",\"precio\":25.0}]";
          ObjectMapper mapper = new ObjectMapper();
          try {
              List<Producto> productos = mapper.readValue(jsonEntrada, new TypeReference<List<Producto>>() {});
              System.out.println("Primer producto: " + productos.get(0).nombre); // Salida: Teclado
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
  }
  ```

---

### 8\. Bases de datos

El almacenamiento de datos es un pilar fundamental en la mayoría de las aplicaciones. La **Java Database Connectivity (JDBC)** es la API estándar para interactuar con bases de datos relacionales. Su uso directo puede ser verboso, por lo que bibliotecas de abstracción como **JDBI** simplifican enormemente el proceso, permitiendo un código más limpio y legible.

#### 8.1. JDBC: Conexión y operaciones con bases de datos

JDBC proporciona un conjunto de interfaces para conectar a una base de datos, enviar consultas SQL y procesar los resultados. Para usarlo, necesitas un **driver JDBC** (por ejemplo, el conector de MySQL).

El flujo de trabajo es:

1.  **Conexión**: Obtener un objeto `Connection`.
2.  **Sentencia**: Crear un objeto `PreparedStatement` para ejecutar la consulta.
3.  **Ejecución**: Lanzar la consulta.
4.  **Procesamiento**: Iterar sobre el `ResultSet` para obtener los datos.
5.  **Cierre de recursos**: Cerrar todos los recursos, idealmente con **`try-with-resources`** para garantizar que se cierren automáticamente.

#### 8.2. Ejecución de consultas SQL: CRUD con JDBC

A continuación, se muestra una implementación de un **DAO** (Data Access Object) para una clase `Producto`, utilizando JDBC y los métodos CRUD estándar.

**Clase `Producto`:**

```java
public class Producto {
    private int id;
    private String nombre;
    private double precio;

    // Constructores, getters y setters...
}
```

**Métodos CRUD con JDBC:**

```java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDaoJdbc {
    private final String url = "jdbc:mysql://localhost:3306/tienda_db";
    private final String user = "root";
    private final String password = "mysecretpassword";

    // 1. CREATE (SAVE)
    public void save(Producto producto) {
        String sql = "INSERT INTO productos (nombre, precio) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.executeUpdate();

            // Opcional: obtener el ID generado si es una inserción
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    producto.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. READ (findAll)
    public List<Producto> findAll() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT id, nombre, precio FROM productos";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    // 3. READ (findById)
    public Producto findById(int id) {
        String sql = "SELECT id, nombre, precio FROM productos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Producto(rs.getInt("id"), rs.getString("nombre"), rs.getDouble("precio"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra
    }

    // 4. UPDATE
    public void update(Producto producto) {
        String sql = "UPDATE productos SET nombre = ?, precio = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 5. DELETE
    public void delete(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

Este enfoque con JDBC funciona, pero requiere escribir mucho código repetitivo de manejo de conexiones, sentencias y excepciones. Es aquí donde JDBI marca la diferencia.

#### 8.3. JDBI: Uso de una biblioteca de abstracción sobre JDBC

**JDBI** es una biblioteca ligera que simplifica JDBC. Su principal característica es la **API Declarativa (SQL Objects)**, que te permite definir un **DAO** usando una simple **interfaz** y anotaciones. JDBI genera la implementación automáticamente en tiempo de ejecución.

##### 8.3.1. Creación de un DAO con una interfaz y `SQL Objects`

Para que JDBI sepa cómo mapear un `ResultSet` a un objeto `Producto`, necesitas crear un `RowMapper`.

**`ProductoMapper` (Mapeador de fila):**

```java
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoMapper implements RowMapper<Producto> {
    @Override
    public Producto map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Producto(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getDouble("precio")
        );
    }
}
```

Ahora, la **interfaz del DAO** se ve mucho más limpia y se adhiere perfectamente al patrón DAO.

**`ProductoDao` (Interfaz DAO):**

```java
import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import java.util.Optional;
import java.util.List;

// JDBI usará este mapeador para convertir las filas a objetos Producto
@RegisterRowMapper(ProductoMapper.class)
public interface ProductoDao {

    // 1. CREATE (SAVE)
    @SqlUpdate("INSERT INTO productos (nombre, precio) VALUES (:nombre, :precio)")
    void save(@BindBean Producto producto);

    // 2. READ (findAll)
    @SqlQuery("SELECT id, nombre, precio FROM productos")
    List<Producto> findAll();

    // 3. READ (findById)
    @SqlQuery("SELECT id, nombre, precio FROM productos WHERE id = :id")
    Optional<Producto> findById(@Bind("id") int id);

    // 4. UPDATE
    @SqlUpdate("UPDATE productos SET nombre = :nombre, precio = :precio WHERE id = :id")
    void update(@BindBean Producto producto);

    // 5. DELETE
    @SqlUpdate("DELETE FROM productos WHERE id = :id")
    void delete(@Bind("id") int id);
}
```

Observa el uso de las anotaciones:

- `@SqlUpdate` y `@SqlQuery` definen la sentencia SQL.
- `@BindBean` le dice a JDBI que use las propiedades del objeto `producto` para enlazar los parámetros de la consulta (los nombres deben coincidir: `:nombre` se enlaza con `producto.getNombre()`).
- `@Bind` se usa para enlazar parámetros individuales.
- `@RegisterRowMapper` asocia el `RowMapper` con el DAO.
- `Optional` en `findById` es una buena práctica para manejar el caso de que no se encuentre el elemento.

##### 8.3.2. Implementación de un CRUD con JDBI

El código principal de tu aplicación ahora se vuelve mucho más simple y enfocado en la lógica de negocio, no en la de acceso a datos.

```java
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.Handle;
import java.util.List;
import java.util.Optional;

public class AppJdbi {
    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:mysql://localhost:3306/tienda_db", "root", "mysecretpassword");

        try (Handle handle = jdbi.open()) {
            ProductoDao productoDao = handle.attach(ProductoDao.class);

            // CRUD con JDBI
            // SAVE (CREATE)
            Producto teclado = new Producto("Teclado", 75.0);
            productoDao.save(teclado);
            System.out.println("Producto guardado: " + teclado.getNombre());

            // FIND ALL
            List<Producto> productos = productoDao.findAll();
            productos.forEach(p -> System.out.println("Encontrado: " + p.getNombre()));

            // FIND BY ID
            Optional<Producto> productoEncontrado = productoDao.findById(teclado.getId());
            productoEncontrado.ifPresent(p -> System.out.println("Encontrado por ID: " + p.getNombre()));

            // UPDATE
            Producto raton = new Producto("Ratón inalámbrico", 35.0);
            raton.setId(1); // Asumiendo que el ID 1 ya existe
            productoDao.update(raton);
            System.out.println("Producto actualizado: " + raton.getNombre());

            // DELETE
            productoDao.delete(teclado.getId());
            System.out.println("Producto eliminado con ID: " + teclado.getId());
        }
    }
}
```

Como puedes ver, el código con JDBI es significativamente más corto, legible y menos propenso a errores. El manejo de la conexión y las sentencias queda abstracto, permitiéndote concentrarte en las operaciones de datos.

Perfecto. Aquí tienes solo el punto de MongoDB, pero con la adición de usar una API tipada, lo cual es una práctica moderna y preferida sobre el manejo de documentos genéricos.

### 8.4. MongoDB: Manejo de bases de datos NoSQL con API Tipada

**MongoDB** es una base de datos **NoSQL** orientada a documentos, que almacena los datos en documentos de formato **BSON** (una versión binaria de JSON) dentro de colecciones. Su modelo flexible y escalable la hace ideal para datos semiestructurados o no estructurados.

El driver de Java para MongoDB te permite trabajar de dos maneras:

1.  **API de documentos**: Usando la clase `Document`, que es un mapa genérico. Este es el enfoque que vimos anteriormente.
2.  **API tipada**: Usando clases de Java (como `Producto`) para mapear directamente los documentos de la colección. Este enfoque es preferible porque es más seguro, legible y previene errores en tiempo de ejecución.

Para usar la API tipada, se necesita un `CodecRegistry` que sepa cómo serializar y deserializar tus clases a documentos BSON. La forma más sencilla es usar la clase `PojoCodecProvider`.

#### Conexión con API Tipada y Configuración

Primero, debes configurar el `CodecRegistry` y luego usarlo para crear una conexión con `MongoClientSettings`.

```java
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import java.util.List;
import java.util.ArrayList;
import com.mongodb.client.model.Filters;

// La clase Producto debe tener getters, setters y un constructor sin argumentos para que el mapeo funcione.
public class Producto {
    private String id; // Opcional, MongoDB usará _id
    private String nombre;
    private double precio;

    // Constructores, getters y setters...
}

public class MongoDbTypingExample {
    private static MongoCollection<Producto> getCollection() {
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);

        String uri = "mongodb://localhost:27017";
        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(codecRegistry)
                .build();

        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase("tienda_db");
        return database.getCollection("productos", Producto.class);
    }
```

- La clave aquí es el **`CodecRegistry`**, que le enseña al driver cómo convertir entre la clase `Producto` y los documentos de MongoDB.

#### CRUD con API Tipada

Una vez que la colección se ha tipado con `Producto.class`, las operaciones CRUD se vuelven mucho más limpias, ya que trabajas directamente con objetos Java en lugar de con documentos genéricos.

**1. CREATE (Insertar datos)**
Simplemente pasas el objeto `Producto` al método `insertOne()`.

```java
public void save(Producto producto) {
    MongoCollection<Producto> collection = getCollection();
    collection.insertOne(producto);
    System.out.println("Documento insertado con _id");
}
```

**2. READ (Leer datos)**
El método `find()` ahora devuelve directamente objetos `Producto`.

```java
// 2.1. findAll
public List<Producto> findAll() {
    MongoCollection<Producto> collection = getCollection();
    List<Producto> productos = new ArrayList<>();
    collection.find().into(productos);
    return productos;
}

// 2.2. findById
public Producto findById(String id) {
    MongoCollection<Producto> collection = getCollection();
    return collection.find(Filters.eq("_id", new ObjectId(id))).first();
}
```

- Las consultas se construyen de forma más segura usando la clase `Filters`. `Filters.eq("_id", ...)` es el equivalente a `WHERE _id = ...` en SQL.

**3. UPDATE (Actualizar datos)**
Para actualizar, se usan los mismos filtros, pero los cambios se aplican con operadores de actualización.

```java
import com.mongodb.client.model.Updates;

public void update(String id, Producto producto) {
    MongoCollection<Producto> collection = getCollection();
    collection.updateOne(Filters.eq("_id", new ObjectId(id)),
                         Updates.combine(Updates.set("nombre", producto.getNombre()),
                                         Updates.set("precio", producto.getPrecio())));
    System.out.println("Documento actualizado.");
}
```

- El uso de `Updates.combine` y `Updates.set` es el enfoque preferido para construir actualizaciones seguras.

**4. DELETE (Eliminar datos)**
El método `deleteOne()` se usa con un filtro para identificar el documento a eliminar.

```java
public void delete(String id) {
    MongoCollection<Producto> collection = getCollection();
    collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
    System.out.println("Documento eliminado.");
}
```

El uso de la **API tipada** de MongoDB simplifica enormemente el código de tu aplicación, lo hace más legible y menos propenso a errores de mapeo, ya que la responsabilidad de la conversión recae en el driver.

### 9\. Concurrencia y programación asíncrona

La **programación asíncrona** es un paradigma en el que una tarea se ejecuta en segundo plano sin bloquear el flujo principal de ejecución. Esto significa que tu programa puede iniciar una operación (por ejemplo, una llamada a un servicio web o una lectura de archivo pesada) y continuar con otras tareas sin esperar a que la operación termine. Es fundamental para construir aplicaciones **responsivas** que no se "cuelguen" mientras esperan.

La necesidad de la asincronía surge en escenarios de **E/S (Entrada/Salida) intensiva**, donde el tiempo de espera de una operación es mucho mayor que el tiempo de procesamiento. Si cada petición de un servidor web bloquea un hilo mientras espera una respuesta de la base de datos, el rendimiento se degrada rápidamente. La asincronía y la concurrencia resuelven este problema permitiendo que el hilo continúe su trabajo mientras se realiza la operación de E/S.

#### 9.1. Introducción a hilos (threads)

Un **hilo** (o `thread`) es la unidad más pequeña de procesamiento que puede ser ejecutada por un planificador de tareas. Cada hilo tiene su propio flujo de ejecución, pero todos los hilos de un mismo proceso comparten la misma memoria. La **concurrencia** es la capacidad de un programa para ejecutar múltiples hilos simultáneamente (en máquinas con múltiples núcleos de CPU) o de forma entrelazada (en máquinas con un solo núcleo).

- **Creación y ejecución de hilos:**
  Existen dos formas principales de crear un hilo en Java:

  1.  Implementando la interfaz **`Runnable`**.
  2.  Heredando de la clase **`Thread`**.

  La forma preferida es implementar `Runnable` porque permite heredar de otras clases y es más flexible con las expresiones lambda.

  **Ejemplo con `Runnable`:**

  ```java
  public class TareaPesada implements Runnable {
      @Override
      public void run() {
          System.out.println("Inicio de la tarea pesada en el hilo: " + Thread.currentThread().getName());
          try {
              Thread.sleep(2000); // Simula una operación larga de 2 segundos
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          System.out.println("Fin de la tarea pesada en el hilo: " + Thread.currentThread().getName());
      }
  }

  // En el método main:
  public static void main(String[] args) {
      System.out.println("Inicio del hilo principal.");
      Thread hilo1 = new Thread(new TareaPesada());
      hilo1.start(); // Inicia el hilo en segundo plano

      // El hilo principal no se bloquea y continúa su ejecución
      System.out.println("El hilo principal continúa su trabajo.");
  }
  ```

  Al ejecutar esto, verás que el mensaje "El hilo principal continúa su trabajo" se imprime inmediatamente, incluso antes de que termine la tarea del `hilo1`. Esto demuestra el comportamiento asíncrono.

#### 9.2. Manejo de `Pool`s de hilos

Crear y destruir hilos constantemente es costoso en términos de recursos. Un **`Pool` de hilos** es una colección de hilos que están listos para ser usados y reutilizados para ejecutar tareas. Esto reduce la sobrecarga de la creación de hilos y permite gestionar de forma más eficiente los recursos del sistema.

La clase **`ExecutorService`** es la interfaz principal para gestionar `pools` de hilos en Java.

- **Tipos de `pools` de hilos:**

  - `newFixedThreadPool(int n)`: Crea un `pool` con un número fijo de hilos. Si hay más tareas que hilos, las tareas esperan en una cola.
  - `newCachedThreadPool()`: Crea un `pool` que reutiliza hilos existentes o crea nuevos según sea necesario. Es útil para tareas de corta duración.

- **Ejemplo de uso de un `ExecutorService`:**

  ```java
  import java.util.concurrent.ExecutorService;
  import java.util.concurrent.Executors;

  public class ExecutorExample {
      public static void main(String[] args) {
          // Crea un pool con 2 hilos fijos
          ExecutorService executor = Executors.newFixedThreadPool(2);

          // Envía 5 tareas al pool
          for (int i = 0; i < 5; i++) {
              final int tareaId = i;
              executor.submit(() -> {
                  System.out.println("Ejecutando la tarea " + tareaId + " en el hilo " + Thread.currentThread().getName());
              });
          }

          // Es importante cerrar el pool al terminar
          executor.shutdown();
      }
  }
  ```

  Al ejecutar este código, verás que solo 2 hilos (`pool-1-thread-1`, `pool-1-thread-2`) se usan para ejecutar las 5 tareas, demostrando la reutilización del `pool`.

#### 9.3. `CompletableFuture`

Mientras que los hilos son la base de la concurrencia, **`CompletableFuture`** es una herramienta de alto nivel para la **programación asíncrona**. Su propósito es simplificar la gestión de tareas que se completarán en el futuro. Te permite encadenar múltiples tareas, manejar errores y combinar resultados de forma mucho más limpia que con los hilos directos.

- **Hilo vs. `CompletableFuture`:**

  - Un **hilo** es una unidad de ejecución de bajo nivel. Lo usas cuando necesitas controlar manualmente la ejecución de una tarea. Es la base sobre la que se construyen los modelos asíncronos.
  - Un **`CompletableFuture`** es una abstracción. **No es un hilo en sí mismo**, sino un contenedor para el resultado de una tarea que se ejecuta de forma asíncrona en un `pool` de hilos gestionado. Te permite escribir código que se ejecuta **"cuando"** la tarea termina, sin tener que gestionar los hilos directamente.

- **Ejemplo de uso de `CompletableFuture`:**

  ```java
  import java.util.concurrent.CompletableFuture;
  import java.util.concurrent.ExecutionException;

  public class CompletableFutureExample {
      public static void main(String[] args) throws InterruptedException, ExecutionException {
          System.out.println("Inicio del programa.");

          // 1. Iniciar una tarea asíncrona
          CompletableFuture<String> futuro = CompletableFuture.supplyAsync(() -> {
              System.out.println("Obteniendo datos de forma asíncrona...");
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) { }
              return "Datos de la API";
          });

          // 2. Encadenar una operación que se ejecuta cuando la tarea anterior termina
          CompletableFuture<Void> futuroProcesado = futuro.thenAccept(resultado -> {
              System.out.println("Datos obtenidos y procesados: " + resultado);
          });

          // 3. El hilo principal no se bloquea y puede hacer otras cosas
          System.out.println("El hilo principal continúa su trabajo sin esperar.");

          // 4. Esperar a que la última operación termine (opcional)
          futuroProcesado.join();
          System.out.println("Fin del programa.");
      }
  }
  ```

  Este ejemplo muestra el poder de `CompletableFuture`: el hilo principal inicia la tarea, continúa su ejecución y solo después, de forma asíncrona, se ejecuta la acción de procesar los datos. Esto es ideal para aplicaciones que necesitan hacer varias cosas a la vez sin bloquearse.


### 10\. Programación Reactiva con RxJava

La **programación reactiva** es un paradigma de programación asíncrona que se basa en el concepto de "flujos de datos". En este modelo, las aplicaciones reaccionan a los datos a medida que se emiten, en lugar de solicitarlos de forma explícita. Es ideal para manejar eventos, cambios en la UI, respuestas de red o cualquier fuente de datos que llegue de manera asíncrona. **RxJava** es una de las implementaciones más populares de este paradigma en el ecosistema Java.

#### 10.1. Introducción a la programación reactiva y RxJava

La programación tradicional (imperativa) se basa en la petición-respuesta. Por ejemplo, cuando llamas a un método, este devuelve un valor y el flujo se detiene hasta que lo hace. La programación reactiva invierte este modelo: un "productor" emite datos de manera asíncrona y un "consumidor" los procesa a medida que llegan, sin bloquear el hilo principal. Esta arquitectura, basada en el **Patrón Observador** extendido, permite construir sistemas más escalables, resilientes y eficientes.

**Diferencia clave entre flujos "fríos" y "calientes":**
Esta distinción es fundamental para entender cómo se comportan los `Observable`s.

- **Flujos "Cold" (fríos)**: Son como una película en streaming. El trabajo no comienza hasta que alguien le da a "play". Cada suscriptor tiene su propia instancia de la fuente de datos y recibe la secuencia completa desde el principio, de manera independiente a otros suscriptores. Si hay dos observadores, cada uno tendrá su propia "copia" del flujo de datos. La mayoría de los `Observable`s creados con operadores de fábrica (como `Observable.just()` o `Observable.fromCallable()`) son fríos.
- **Flujos "Hot" (calientes)**: Son como una transmisión en directo por televisión. La emisión de datos ya ha comenzado antes de que alguien sintonice. Todos los suscriptores reciben los mismos datos que se emiten a partir de su momento de suscripción, pero pierden la "historia" que ya se ha emitido. Si un observador se une tarde, no verá los datos que ya se han emitido.

#### 10.2. Componentes principales de RxJava

Los flujos reactivos en RxJava se construyen con tres componentes principales:

- **`Observable` (Productor)**: Es la fuente de datos. Emite una secuencia de elementos, notificaciones de error (`onError`) o una señal de que ha terminado (`onComplete`). Se suscribe un `Observer` para recibir los datos. Un `Observable` puede emitir cero, uno o múltiples elementos.
- **`Observer` (Consumidor)**: Es el componente que recibe y reacciona a los elementos emitidos por el `Observable`. Implementa los tres métodos clave de una suscripción:
  - `onNext(T value)`: Se llama cada vez que el `Observable` emite un nuevo elemento.
  - `onError(Throwable error)`: Se llama si el `Observable` encuentra un error. Esto detiene el flujo de datos.
  - `onComplete()`: Se llama cuando el `Observable` ha terminado de emitir elementos.
- **`Scheduler` (Planificador)**: Controla en qué hilo de ejecución se llevan a cabo las operaciones del flujo reactivo. Esto es crucial para la programación asíncrona, ya que te permite mover las tareas pesadas a un hilo en segundo plano y luego volver al hilo principal para actualizar la interfaz de usuario.
  - `Schedulers.io()`: Para operaciones de E/S (lectura de archivos, red).
  - `Schedulers.computation()`: Para cálculos intensivos.
  - `AndroidSchedulers.mainThread()`: Para actualizar la UI en Android.
- **`Subject`**: Es una clase especial que actúa a la vez como **`Observer` y `Observable`**. Puede recibir elementos y retransmitirlos a sus propios suscriptores. Esto lo convierte en un "puente" entre código no reactivo y un flujo reactivo. Los `Subject` son esenciales para eventos que ocurren en tiempo real y que múltiples observadores necesitan escuchar.
  - **`PublishSubject`**: Emite solo los datos que se producen después de que un `Observer` se suscribe. Los datos que se emitieron antes se pierden.
  - **`BehaviorSubject`**: Emite el último valor que se emitió antes de la suscripción y luego todos los nuevos valores.
  - **`ReplaySubject`**: Emite **todos** los valores (pasados y futuros) a un nuevo suscriptor.

##### Ejemplos de Flujos Fríos y Calientes

###### Ejemplo 1: Flujo frío (lectura asíncrona de fichero)

Imagina que una clase lee un fichero línea a línea de forma asíncrona. La lectura no comenzará hasta que alguien se suscriba.

**Clase `LectorFichero`:**

```java
import io.reactivex.Observable;
import java.io.BufferedReader;
import java.io.FileReader;

public class LectorFichero {
    public static Observable<String> leer(String nombreArchivo) {
        // La fuente de datos es un Observable
        return Observable.fromCallable(() -> {
            BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo));
            try {
                // Leer cada línea y emitirla
                return reader.lines().toList();
            } finally {
                reader.close();
            }
        }).flatMapIterable(list -> list);
    }
}

// Clase de Consumidor
public class ConsumidorFrio {
    public static void main(String[] args) {
        // El fichero "datos.txt" tiene que existir
        // Contenido de datos.txt:
        // linea 1
        // otra linea
        // a-linea 3

        // CADA OBSERVADOR OBTENDRÁ LOS DATOS DESDE EL PRINCIPIO

        // Primer consumidor
        LectorFichero.leer("datos.txt")
            .filter(linea -> linea.startsWith("a"))
            .map(String::toUpperCase)
            .subscribe(
                linea -> System.out.println("Consumidor 1: " + linea),
                Throwable::printStackTrace,
                () -> System.out.println("Consumidor 1: Fin del flujo")
            );

        // Segundo consumidor
        LectorFichero.leer("datos.txt")
            .filter(linea -> linea.startsWith("a"))
            .map(String::toUpperCase)
            .subscribe(
                linea -> System.out.println("Consumidor 2: " + linea),
                Throwable::printStackTrace,
                () -> System.out.println("Consumidor 2: Fin del flujo")
            );
    }
}
```

**Salida esperada:**

```
Consumidor 1: A-LINEA 3
Consumidor 1: Fin del flujo
Consumidor 2: A-LINEA 3
Consumidor 2: Fin del flujo
```

En este ejemplo, ambos consumidores obtienen los mismos datos, cada uno leyendo el archivo de manera independiente, porque es un flujo frío.

###### Ejemplo 2: Flujo caliente (sistema de notificaciones)

Un `Subject` es la forma más común de crear un flujo caliente. Imagina un sistema de notificaciones donde los usuarios se conectan en diferentes momentos. Si alguien se conecta ahora, ha perdido la historia anterior, por eso se considera un flujo caliente.

```java
import io.reactivex.subjects.PublishSubject;
import java.util.concurrent.TimeUnit;

public class SistemaNotificaciones {
    public static void main(String[] args) throws InterruptedException {
        // El sujeto es la fuente de datos "caliente"
        PublishSubject<String> notificaciones = PublishSubject.create();

        // Primer suscriptor se une ahora
        notificaciones.subscribe(s -> System.out.println("Usuario A recibe: " + s));

        // El emisor empieza a enviar notificaciones
        System.out.println("Enviando Notificación 1...");
        notificaciones.onNext("Notificación 1");

        // Simula un retraso antes de que otro usuario se conecte
        TimeUnit.SECONDS.sleep(1);

        // Segundo suscriptor se une tarde
        System.out.println("El usuario B se ha conectado ahora.");
        notificaciones.subscribe(s -> System.out.println("Usuario B recibe: " + s));

        // El emisor envía más notificaciones
        System.out.println("Enviando Notificación 2...");
        notificaciones.onNext("Notificación 2");

        notificaciones.onComplete();
    }
}
```

**Salida esperada:**

```
Enviando Notificación 1...
Usuario A recibe: Notificación 1
El usuario B se ha conectado ahora.
Enviando Notificación 2...
Usuario A recibe: Notificación 2
Usuario B recibe: Notificación 2
```

Como puedes ver, el **`Usuario B` ha perdido la `Notificación 1`** porque ya se había emitido cuando se suscribió. Esto demuestra perfectamente el comportamiento de un flujo caliente.

#### 10.4. Tipos especializados de `Observable`

RxJava ofrece variantes de `Observable` optimizadas para casos de uso específicos:

- **`Flowable`**: A diferencia de `Observable`, `Flowable` está diseñado para manejar la **contrapresión**. Se usa para flujos que emiten una gran cantidad de datos y un `Observer` podría no ser capaz de procesarlos lo suficientemente rápido. `Flowable` permite al `Observer` controlar la velocidad de emisión del productor.
- **`Single`**: Emite un solo valor o un error. Es perfecto para operaciones que devuelven un único resultado, como una llamada a una API REST que devuelve un objeto.
- **`Maybe`**: Emite un valor, un error, o simplemente completa sin emitir nada. Es útil para operaciones que pueden o no devolver un resultado.
- **`Completable`**: Solo emite una señal de finalización (`onComplete`) o un error. Es ideal para operaciones que no devuelven ningún dato, como una actualización en una base de datos.

**Ejemplo:**

```java
// Single: para una operación que devuelve un solo resultado
Single.just("Hola mundo").subscribe(System.out::println);

// Completable: para una operación que no devuelve nada
Completable.fromAction(() -> System.out.println("Tarea completada"))
    .subscribe(() -> System.out.println("Finalizado"));
```

#### 10.5. La contrapresión (backpressure): `Flowable` en detalle

La **contrapresión** es el mecanismo para gestionar flujos donde el emisor ("productor") genera más datos de los que el receptor ("consumidor") puede procesar. Si el productor es mucho más rápido, el buffer de datos del consumidor se desbordará, lo que puede llevar a un `OutOfMemoryError`.

Aquí es donde entra **`Flowable`**. Permite al `Observer` decirle al `Flowable` cuántos elementos es capaz de recibir. El `Flowable` no emitirá más elementos de los solicitados, evitando el desbordamiento. Esta es una diferencia fundamental con `Observable`, que no tiene un mecanismo de contrapresión incorporado.

#### 10.6. Operadores: Transformación, filtrado y combinación

Los **operadores** son la parte más poderosa de RxJava. Son métodos que se encadenan para manipular y transformar los datos de un flujo.

- **Transformación:**
  - `map()`: Transforma cada elemento en el flujo a otro tipo.
  - `flatMap()`: Transforma cada elemento en un nuevo `Observable` y aplana los resultados en un solo flujo. Es crucial para encadenar llamadas asíncronas.
- **Filtrado:**
  - `filter()`: Emite solo los elementos que cumplen una condición.
  - `take(n)`: Emite solo los primeros `n` elementos.
- **Combinación:**
  - `zip()`: Combina las emisiones de múltiples `Observable`s.
  - `merge()`: Combina las emisiones de múltiples `Observable`s en un solo flujo.

#### 10.7. Casos de uso y mejores prácticas

RxJava es especialmente útil en:

- **Servicios web asíncronos**: Realizar múltiples llamadas a la API de forma concurrente sin bloquear el hilo principal.
- **Eventos de interfaz de usuario**: Reaccionar a eventos del usuario (clics, pulsaciones de teclas) y manejar la lógica de negocio de forma asíncrona.
- **Procesamiento de datos en tiempo real**: Analizar datos que llegan de forma continua desde fuentes como un socket web.

El uso de **`Schedulers`** es una de las mejores prácticas más importantes. Siempre utiliza un `Scheduler` para mover las operaciones pesadas de E/S o de cómputo a un hilo en segundo plano, liberando el hilo principal para mantener la aplicación responsiva.

---

### 11\. Railway Oriented Programming

#### 11.1. El problema de la gestión de errores con excepciones

Las **excepciones** son el mecanismo estándar de Java para manejar situaciones excepcionales, como la falta de memoria o un disco inaccesible. Sin embargo, a menudo se usan para gestionar errores de negocio o de validación, lo que tiene varios inconvenientes:

1.  **Interrumpen el flujo normal**: Las excepciones rompen el control de flujo, haciendo que el código sea más difícil de seguir y de razonar.
2.  **Verbosidad del `try-catch`**: El manejo de excepciones puede llenar el código de bloques `try-catch`, haciéndolo más difícil de leer.
3.  **No fuerzan el manejo del error**: Las excepciones no comprobadas (`RuntimeException`) pueden propagarse por la aplicación sin ser capturadas, llevando a `bugs` en producción.

#### 11.2. Introducción a Railway Oriented Programming

Esta técnica ve el flujo de un programa como una vía de tren con dos raíles: uno para el **camino de éxito** y otro para el **camino de error**. Si una operación tiene éxito, la ejecución se mantiene en el carril de éxito. Si una operación falla, el flujo salta al carril de error y se salta las operaciones restantes, llevando el error al final de la cadena.

La clave de este enfoque es que el **camino de error es un flujo de datos válido y esperado**, eliminando la necesidad de `excepciones` para errores comunes de negocio.

#### 11.3. La clase `Either` de Vavr

Para implementar este modelo en Java, utilizamos la clase **`Either<L, R>`** de la librería Vavr. `Either` es un tipo de dato que representa uno de dos posibles valores. Por convención, se usa:

- **`Left<L>`**: Para el camino de error. Contiene el tipo de error (por ejemplo, un `String` o una clase de error).
- **`Right<R>`**: Para el camino de éxito. Contiene el tipo del valor esperado.

El uso de `Either` hace que el manejo de errores sea explícito y predecible, ya que un método que devuelve un `Either` te obliga a considerar ambos posibles resultados.

#### 11.4. Encadenamiento de operaciones y manejo de errores

El poder de este modelo reside en la capacidad de encadenar operaciones usando **`flatMap`**. Si una de las operaciones falla, el `Either` contendrá el error en su lado `Left` y las siguientes operaciones se ignorarán, propagando el error automáticamente.

**Ejemplo de funciones que devuelven `Either`:**

```java
import io.vavr.control.Either;
import static io.vavr.control.Either.left;
import static io.vavr.control.Either.right;

public class ProcesoDatos {
    // 1. Validar la entrada
    public static Either<String, String> validateInput(String input) {
        if (input == null || input.isEmpty()) {
            return left("La entrada no puede estar vacía.");
        }
        return right(input);
    }

    // 2. Parsear a un número
    public static Either<String, Integer> parseToInt(String s) {
        try {
            return right(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return left("No es un número válido.");
        }
    }

    // 3. Multiplicar por dos
    public static Either<String, Integer> multiplyByTwo(Integer number) {
        return right(number * 2);
    }
}
```

**Encadenando los casos de éxito y error:**

```java
// Encadenamiento de operaciones de éxito
Either<String, Integer> resultadoExito = ProcesoDatos.validateInput("10")
    .flatMap(ProcesoDatos::parseToInt)
    .flatMap(ProcesoDatos::multiplyByTwo);

// Encadenamiento de operaciones de error (falla en el primer paso)
Either<String, Integer> resultadoError = ProcesoDatos.validateInput("")
    .flatMap(ProcesoDatos::parseToInt)
    .flatMap(ProcesoDatos::multiplyByTwo);

// Manejo del resultado final con isRight() e isLeft()
if (resultadoExito.isRight()) {
    System.out.println("El resultado es: " + resultadoExito.get()); // Salida: 20
}

if (resultadoError.isLeft()) {
    System.out.println("Error: " + resultadoError.getLeft()); // Salida: La entrada no puede estar vacía.
}
```

#### 11.5. Más operadores clave de Vavr `Either`

Además de `flatMap`, Vavr ofrece otros operadores útiles para manejar el flujo en ambos carriles:

- **`map()`**: Si el `Either` es un `Right`, aplica una función al valor y devuelve un nuevo `Right`. Si es un `Left`, lo devuelve sin cambios. Es ideal para transformar el valor del éxito sin afectar el error.

  ```java
  // Transforma el valor del éxito de Integer a String
  Either<String, String> resultadoMapeado = resultadoExito.map(Object::toString);
  System.out.println(resultadoMapeado); // Salida: Right(20)

  // Un map en un error no tiene efecto
  Either<String, String> errorMapeado = resultadoError.map(Object::toString);
  System.out.println(errorMapeado); // Salida: Left(La entrada no puede estar vacía.)
  ```

- **`bimap()`**: Permite aplicar una función a un `Left` y otra a un `Right`. Te da la flexibilidad de transformar el error o el éxito.

  ```java
  Either<String, String> resultadoBiMapeado = resultadoExito.bimap(
      error -> "Error inesperado",
      valor -> "El valor final es: " + valor
  );
  System.out.println(resultadoBiMapeado); // Salida: Right(El valor final es: 20)
  ```

- **`fold()`**: Es una operación terminal que te permite "fusionar" los dos posibles caminos en un único valor final. Recibe dos funciones: una para el caso `Left` y otra para el caso `Right`.

  ```java
  String mensajeFinal = resultadoExito.fold(
      error -> "El flujo ha fallado con el error: " + error,
      valor -> "El proceso ha terminado correctamente con el valor: " + valor
  );
  System.out.println(mensajeFinal); // Salida: El proceso ha terminado correctamente...
  ```

#### 11.6. Beneficios y casos de uso en aplicaciones Java

- **Mayor legibilidad y claridad**: El código se lee como una secuencia de pasos. El camino de error está integrado en el flujo, lo que elimina el anidamiento de `try-catch`.
- **Manejo de errores explícito**: A diferencia de las excepciones no comprobadas, `Either` obliga al programador a considerar los dos posibles resultados (`Left` o `Right`).
- **Facilita las pruebas unitarias**: Es mucho más fácil probar funciones que devuelven un resultado predecible en lugar de lanzar una excepción.

Esta técnica es muy útil en servicios de `backend` y `APIs` donde se realizan múltiples pasos de validación y procesamiento de datos. Es un complemento, no un reemplazo, de las excepciones, que siguen siendo la mejor opción para situaciones verdaderamente excepcionales que no se pueden prever.

---

### 12\. Retrofit: Clientes REST con Java

Retrofit es una biblioteca de Java y Android desarrollada por Square que simplifica la comunicación con servicios web RESTful. Su principal ventaja es que te permite definir la API como una **interfaz de Java** y usar **anotaciones** para describir las peticiones HTTP. Retrofit se encarga de todo el trabajo pesado: la serialización (convertir objetos a JSON) y la deserialización (convertir JSON a objetos), el manejo de la red y la gestión de hilos.

#### 12.1. Configuración: Clases de cliente y modelos de datos

Antes de usar Retrofit, necesitas definir dos elementos clave:

1.  **Un modelo de datos (POJO)**: Una clase de Java que represente la estructura de los datos que vas a enviar o recibir.
2.  **Una interfaz de servicio**: Una interfaz que defina los métodos para cada *endpoint* de la API, usando anotaciones de Retrofit.

Para este ejemplo, asumiremos una API simple con un *endpoint* `/productos` y la siguiente clase de modelo:

**`Producto.java`**

```java
public class Producto {
    private int id;
    private String nombre;
    private double precio;

    // Getters, setters y constructores
}
```

Ahora, definimos la interfaz de la API. Las anotaciones como `@GET`, `@POST`, `@PUT`, y `@DELETE` indican el tipo de petición.

**`ProductoApi.java`**

```java
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import java.util.List;

public interface ProductoApi {
    @POST("productos")
    Call<Producto> crearProducto(@Body Producto producto);

    @GET("productos")
    Call<List<Producto>> obtenerTodosLosProductos();

    @GET("productos/{id}")
    Call<Producto> obtenerProductoPorId(@Path("id") int id);

    @PUT("productos/{id}")
    Call<Producto> actualizarProducto(@Path("id") int id, @Body Producto producto);

    @DELETE("productos/{id}")
    Call<Void> eliminarProducto(@Path("id") int id);
}
```

Observa las anotaciones:

  * `@Body`: Indica que el objeto `producto` será serializado y enviado en el cuerpo de la petición.
  * `@Path("id")`: Inserta el valor del parámetro `id` en la URL de la petición.

Para usar esta interfaz, necesitas crear una instancia de Retrofit.

**`RetrofitClient.java`**

```java
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://localhost:8080/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) // O mejor usar Jackson
                    .build();
        }
        return retrofit;
    }
}
```

Aquí se usa **`GsonConverterFactory`** (o Jackson) para que Retrofit sepa cómo convertir JSON a objetos y viceversa.

#### 12.2. Operaciones CRUD con una API REST

Una vez configurado todo, realizar una operación CRUD es tan simple como llamar a los métodos de la interfaz `ProductoApi`.

##### 12.2.1. CREATE: Creación de un producto (`@POST`)

Para crear un nuevo producto, se usa el método `crearProducto` de la interfaz. Retrofit se encarga de enviar una petición `POST` con los datos del producto en el cuerpo.

```java
public void crearProducto() {
    ProductoApi api = RetrofitClient.getRetrofitInstance().create(ProductoApi.class);
    Producto nuevoProducto = new Producto("Laptop", 1200.0);

    // Retrofit hace la llamada de forma asíncrona por defecto
    Call<Producto> call = api.crearProducto(nuevoProducto);

    // Se ejecuta la llamada de forma asíncrona
    call.enqueue(new Callback<Producto>() {
        @Override
        public void onResponse(Call<Producto> call, Response<Producto> response) {
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("Producto creado con éxito: " + response.body().getNombre());
            } else {
                System.out.println("Error al crear el producto: " + response.code());
            }
        }

        @Override
        public void onFailure(Call<Producto> call, Throwable t) {
            System.err.println("Error de red: " + t.getMessage());
        }
    });
}
```

##### 12.2.2. READ: Lectura de productos (`@GET`)

Para leer todos los productos, se invoca el método `obtenerTodosLosProductos`.

```java
public void obtenerProductos() {
    ProductoApi api = RetrofitClient.getRetrofitInstance().create(ProductoApi.class);
    Call<List<Producto>> call = api.obtenerTodosLosProductos();

    call.enqueue(new Callback<List<Producto>>() {
        @Override
        public void onResponse(Call<List<Producto>> call, Response<List<Producto>> response) {
            if (response.isSuccessful() && response.body() != null) {
                List<Producto> productos = response.body();
                productos.forEach(p -> System.out.println("ID: " + p.getId() + ", Nombre: " + p.getNombre()));
            }
        }

        @Override
        public void onFailure(Call<List<Producto>> call, Throwable t) {
            System.err.println("Error de red: " + t.getMessage());
        }
    });
}

public void obtenerProductoPorId(int id) {
    ProductoApi api = RetrofitClient.getRetrofitInstance().create(ProductoApi.class);
    Call<Producto> call = api.obtenerProductoPorId(id);

    call.enqueue(new Callback<Producto>() {
        @Override
        public void onResponse(Call<Producto> call, Response<Producto> response) {
            if (response.isSuccessful() && response.body() != null) {
                Producto producto = response.body();
                System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre());
            } else {
                System.out.println("Producto no encontrado: " + response.code());
            }
        }

        @Override
        public void onFailure(Call<Producto> call, Throwable t) {
            System.err.println("Error de red: " + t.getMessage());
        }
    });

```

##### 12.2.3. UPDATE: Actualización de un producto (`@PUT`)

La actualización es similar a la creación, pero se utiliza `@PUT` y se incluye el ID del producto en la URL.

```java
public void actualizarProducto(int id) {
    ProductoApi api = RetrofitClient.getRetrofitInstance().create(ProductoApi.class);
    Producto productoActualizado = new Producto("Laptop Pro", 1500.0);

    Call<Producto> call = api.actualizarProducto(id, productoActualizado);

    call.enqueue(new Callback<Producto>() {
        @Override
        public void onResponse(Call<Producto> call, Response<Producto> response) {
            if (response.isSuccessful()) {
                System.out.println("Producto actualizado con éxito.");
            } else {
                System.out.println("Error al actualizar el producto: " + response.code());
            }
        }

        @Override
        public void onFailure(Call<Producto> call, Throwable t) {
            System.err.println("Error de red: " + t.getMessage());
        }
    });
}
```

##### 12.2.4. DELETE: Eliminación de un producto (`@DELETE`)

Para eliminar un producto, se usa el método `eliminarProducto`. La respuesta esperada a menudo no tiene cuerpo, por lo que el tipo de retorno es `Call<Void>`.

```java
public void eliminarProducto(int id) {
    ProductoApi api = RetrofitClient.getRetrofitInstance().create(ProductoApi.class);
    Call<Void> call = api.eliminarProducto(id);

    call.enqueue(new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if (response.isSuccessful()) {
                System.out.println("Producto eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el producto: " + response.code());
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            System.err.println("Error de red: " + t.getMessage());
        }
    });
}
```

#### 12.3. Manejo de respuestas y errores

El método `enqueue` de Retrofit maneja las peticiones de forma asíncrona y requiere la implementación de una interfaz `Callback` con dos métodos:

  * `onResponse`: Se llama cuando la respuesta del servidor se recibe, independientemente de si la petición fue exitosa (`200 OK`) o un error (`404 Not Found`, `500 Internal Server Error`). Es crucial usar `response.isSuccessful()` para verificar el código de estado HTTP.
  * `onFailure`: Se llama si ocurre un error de red (por ejemplo, el servidor no está disponible).

Este enfoque separa la lógica de negocio de la lógica de red, haciendo tu código más limpio y modular.


Sí, por supuesto. Retrofit es muy flexible y permite usar otros paradigmas de programación asíncrona además de la clase `Call` para gestionar las respuestas de la API. Esto es posible gracias a los **adaptadores de llamadas** (*Call Adapters*).

El uso de **`CompletableFuture`** o librerías de programación reactiva como **RxJava** es una práctica muy común para integrar las llamadas de red en flujos de datos más complejos o para trabajar con código más moderno y expresivo.


#### 12.4. Alternativas a `Call`: Programación asíncrona con Retrofit

##### 12.4.1. Uso de `CompletableFuture` (Java 8+)

A partir de Java 8, `CompletableFuture` es una excelente opción para manejar resultados de forma asíncrona de manera nativa. Para que Retrofit funcione con `CompletableFuture`, necesitas añadir un adaptador a tu cliente Retrofit.

**Paso 1: Añadir la dependencia del adaptador**
En tu archivo `build.gradle`, añade la siguiente dependencia:

```groovy
implementation 'com.squareup.retrofit2:retrofit-java8-completablefuture:2.9.0'
```

**Paso 2: Actualizar el cliente Retrofit**
Añade el adaptador a tu instancia de `Retrofit`.

```java
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.java8.Java8CallAdapterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://localhost:8080/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    // Añade el adaptador para CompletableFuture
                    .addCallAdapterFactory(Java8CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
```

**Paso 3: Modificar la interfaz de servicio**
Ahora, en lugar de devolver `Call<Producto>`, tus métodos pueden devolver `CompletableFuture<Producto>`.

```java
public interface ProductoApi {
    @POST("productos")
    CompletableFuture<Producto> crearProducto(@Body Producto producto);

    @GET("productos")
    CompletableFuture<List<Producto>> obtenerTodosLosProductos();

    // ... y el resto de operaciones CRUD
}
```

**Paso 4: Consumir la respuesta**
El manejo del flujo se vuelve mucho más limpio y se integra perfectamente con el paradigma de `CompletableFuture`.

```java
public void crearProductoAsync() {
    ProductoApi api = RetrofitClient.getRetrofitInstance().create(ProductoApi.class);
    Producto nuevoProducto = new Producto("Laptop", 1200.0);

    api.crearProducto(nuevoProducto)
        .thenAccept(productoCreado -> {
            System.out.println("Producto creado con éxito: " + productoCreado.getNombre());
        })
        .exceptionally(ex -> {
            System.err.println("Error al crear el producto: " + ex.getMessage());
            return null;
        });
}
```

##### 12.4.2. Uso con RxJava y programación reactiva

Para aplicaciones que utilizan programación reactiva, Retrofit ofrece adaptadores para RxJava (1, 2 y 3). El uso de RxJava permite una composición de operaciones asíncronas mucho más potente y declarativa.

**Paso 1: Añadir la dependencia del adaptador**
Para RxJava 3, por ejemplo, se necesita el siguiente adaptador:

```groovy
implementation 'com.squareup.retrofit2:adapter-rxjava3:2.9.0'
```

**Paso 2: Actualizar el cliente Retrofit**
Se añade el adaptador a la instancia de Retrofit.

```java
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;

public class RetrofitClient {
    // ...
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    // Añade el adaptador para RxJava 3
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
```

**Paso 3: Modificar la interfaz de servicio**
Los métodos ahora devuelven tipos de RxJava como `Single` o `Observable`.

```java
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.Observable;

public interface ProductoApi {
    @POST("productos")
    Single<Producto> crearProducto(@Body Producto producto);

    @GET("productos")
    Observable<List<Producto>> obtenerTodosLosProductos();

    // ...
}
```

**Paso 4: Consumir el flujo reactivo**
El consumo de la respuesta se realiza a través de suscripciones, lo que te permite aplicar operadores reactivos (`map`, `filter`, `doOnNext`, etc.) para transformar y encadenar las llamadas de forma elegante.

```java
import io.reactivex.rxjava3.schedulers.Schedulers;

public void obtenerProductosRxJava() {
    ProductoApi api = RetrofitClient.getRetrofitInstance().create(ProductoApi.class);

    api.obtenerTodosLosProductos()
        .subscribeOn(Schedulers.io()) // Se ejecuta en un hilo de E/S
        .observeOn(Schedulers.single()) // El resultado se observa en otro hilo
        .subscribe(
            productos -> productos.forEach(p -> System.out.println(p.getNombre())),
            throwable -> System.err.println("Error: " + throwable.getMessage())
        );
}
```

**Conclusión:**
Si bien `Call` es el método por defecto, los adaptadores te dan la flexibilidad de usar el paradigma asíncrono que mejor se adapte a tu proyecto, ya sea con las funcionalidades nativas de Java o con librerías de terceros.

### 13\. Pruebas y herramientas

Las pruebas son esenciales en el desarrollo de software para asegurar que el código se comporta como se espera. Las **pruebas unitarias** verifican el comportamiento de las unidades de código más pequeñas, como las clases o los métodos.

#### 13.1. Pruebas unitarias

Una buena práctica en el desarrollo de pruebas es seguir el patrón **AAA (Arrange, Act, Assert)**. Este patrón divide cada prueba en tres partes claras y distintas:

- **Arrange**: Prepara los objetos y configura el entorno para la prueba.
- **Act**: Ejecuta el método o la acción que se va a probar.
- **Assert**: Verifica que el resultado de la acción es el esperado.

##### 13.1.1. JUnit para pruebas unitarias

**JUnit** es el _framework_ más popular en Java para escribir y ejecutar pruebas unitarias. Permite anotar métodos de prueba con `@Test` y proporciona un conjunto de métodos de aserción para validar los resultados.

**Aserciones comunes de JUnit:**

- `assertEquals(expected, actual)`: Verifica que dos valores son iguales.
- `assertTrue(condition)`: Verifica que una condición es verdadera.
- `assertFalse(condition)`: Verifica que una condición es falsa.
- `assertNull(object)`: Verifica que un objeto es nulo.
- `assertNotNull(object)`: Verifica que un objeto no es nulo.
- `assertThrows(Exception.class, () -> ...)`: Verifica que un método lanza una excepción específica.

**Ejemplo de prueba con JUnit y el patrón A-A-A:**

Imaginemos una clase `Calculadora` muy simple.

```java
public class Calculadora {
    public int sumar(int a, int b) {
        return a + b;
    }
}
```

La prueba unitaria para el método `sumar` sería:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraTest {
    @Test
    void testSumar() {
        // Arrange
        Calculadora calculadora = new Calculadora();
        int numero1 = 5;
        int numero2 = 3;
        int resultadoEsperado = 8;

        // Act
        int resultadoActual = calculadora.sumar(numero1, numero2);

        // Assert
        assertEquals(resultadoEsperado, resultadoActual, "La suma debería ser 8");
    }
}
```

##### 13.1.2. Mockito para la creación de objetos simulados (mocks)

Cuando una clase a probar depende de otras, es difícil aislar su comportamiento. **Mockito** es un _framework_ de _mocking_ que permite crear **objetos simulados (mocks)** para simular el comportamiento de las dependencias. Esto aísla el código bajo prueba y garantiza que las pruebas unitarias sean verdaderamente "unitarias".

**Ejemplo sin anotaciones:**

Imaginemos un `ServicioEmail` que depende de un `RepositorioUsuario`. No queremos probar el repositorio, sino el servicio.

```java
// Clase a simular
public class RepositorioUsuario {
    public boolean existeUsuario(String email) {
        // Lógica de acceso a base de datos real
        return false;
    }
}

// Clase a probar
public class ServicioEmail {
    private RepositorioUsuario repositorio;

    public ServicioEmail(RepositorioUsuario repositorio) {
        this.repositorio = repositorio;
    }

    public boolean enviarEmail(String email) {
        if (repositorio.existeUsuario(email)) {
            // Lógica de envío de email
            return true;
        }
        return false;
    }
}
```

La prueba con Mockito se vería así:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ServicioEmailTest {
    @Test
    void testEnviarEmail_UsuarioExistente() {
        // Arrange
        // 1. Crear el mock del RepositorioUsuario
        RepositorioUsuario mockRepositorio = mock(RepositorioUsuario.class);
        // 2. Definir el comportamiento del mock (cuando se llama, devuelve true)
        when(mockRepositorio.existeUsuario("test@example.com")).thenReturn(true);
        // 3. Crear la instancia de la clase a probar con el mock
        ServicioEmail servicioEmail = new ServicioEmail(mockRepositorio);

        // Act
        boolean resultado = servicioEmail.enviarEmail("test@example.com");

        // Assert
        assertTrue(resultado, "El email debería ser enviado");
        // Verificar que el método del mock fue llamado exactamente una vez
        verify(mockRepositorio, times(1)).existeUsuario("test@example.com");
    }
}
```

El método `verify(mock, times(1)).method()` es crucial para asegurar que el código bajo prueba interactuó con su dependencia como se esperaba.

**Ejemplo con anotaciones `@Mock` y `@InjectMocks`:**

Este enfoque es más limpio y se usa en la mayoría de los casos. Mockito se encarga de instanciar los mocks y de inyectarlos en la clase a probar.

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

// Se necesita esta extensión para habilitar las anotaciones
@ExtendWith(MockitoExtension.class)
class ServicioEmailConAnotacionesTest {
    // Mockea la dependencia
    @Mock
    private RepositorioUsuario repositorio;

    // Crea la instancia de la clase a probar e inyecta los mocks
    @InjectMocks
    private ServicioEmail servicioEmail;

    @Test
    void testEnviarEmail_UsuarioNoExistente() {
        // Arrange
        // El mock ya está creado por @Mock
        when(repositorio.existeUsuario("nuevo@example.com")).thenReturn(false);

        // Act
        boolean resultado = servicioEmail.enviarEmail("nuevo@example.com");

        // Assert
        assertFalse(resultado, "El email no debería ser enviado");
        verify(repositorio).existeUsuario("nuevo@example.com"); // Verifica por defecto 1 vez
    }
}
```

### 13.2. Gestión de proyectos y documentación

##### 13.2.1. Gradle para la gestión de proyectos y dependencias

**Gradle** es una herramienta de automatización de construcción de proyectos. Su función principal es descargar las librerías necesarias (dependencias), compilar el código, ejecutar las pruebas y empaquetar la aplicación. Usa un lenguaje declarativo (Groovy o Kotlin DSL) en el archivo `build.gradle` que es más flexible que el XML de su antecesor, Maven.

**Ejemplo de archivo `build.gradle`:**

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
    // Dependencias de prueba
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.8.1'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.8.1'

    // Dependencia de mocking
    testImplementation 'org.mockito:mockito-core:4.8.0'
    testImplementation 'org.mockito:mockito-junit-jupiter:4.8.0'
}

test {
    useJUnitPlatform()
}
```

En este archivo, `repositories` define dónde buscar las dependencias (el repositorio central de Maven), y `dependencies` lista las bibliotecas necesarias. `testImplementation` y `testRuntimeOnly` son configuraciones de alcance que indican que esas librerías solo son necesarias para el código de prueba.

##### 13.2.2. Javadocs para la documentación del código

**Javadocs** es una herramienta de Oracle que genera documentación HTML a partir de comentarios especiales en el código fuente de Java. Es una práctica fundamental para describir el propósito de las clases, interfaces, métodos y atributos de un proyecto, facilitando su uso a otros desarrolladores.

**Ejemplo de Javadocs:**

```java
/**
 * Clase que simula una calculadora simple para fines de prueba.
 * Esta clase no maneja excepciones y asume entradas válidas.
 */
public class Calculadora {

    /**
     * Suma dos números enteros.
     * @param a El primer número a sumar.
     * @param b El segundo número a sumar.
     * @return El resultado de la suma de a y b.
     */
    public int sumar(int a, int b) {
        return a + b;
    }
}
```

La documentación se genera ejecutando la tarea `javadoc` de Gradle, que crea un conjunto de archivos HTML listos para ser publicados y consultados por el equipo de desarrollo.

---
### 14\. Dockerización de aplicaciones de servidor

El **despliegue de aplicaciones** es el proceso de poner una aplicación en funcionamiento para que los usuarios puedan interactuar con ella. En la actualidad, este proceso se ha simplificado y estandarizado gracias a tecnologías de contenedores como **Docker**.

#### 14.1. ¿Qué es Docker?

**Docker** es una plataforma de código abierto que permite automatizar el despliegue de aplicaciones dentro de contenedores. Un **contenedor** es una unidad de software ligera y portátil que empaqueta todo lo necesario para ejecutar una aplicación: código, dependencias, librerías y configuraciones.

La clave para entender Docker es la distinción entre una **imagen** y un **contenedor**.

  * Una **imagen** es una plantilla de solo lectura que contiene las instrucciones para crear un contenedor, incluyendo el sistema operativo base, las librerías y las dependencias de la aplicación.
  * Un **contenedor** es una instancia en ejecución de una imagen, un entorno aislado donde la aplicación se ejecuta.

#### 14.2. Docker Compose

**Docker Compose** es una herramienta que simplifica la administración de aplicaciones que consisten en múltiples contenedores. En lugar de ejecutar cada contenedor individualmente, puedes definir la configuración de todos los servicios, redes y volúmenes en un solo archivo YAML y administrarlos con un solo comando.

Es especialmente útil para aplicaciones que dependen de varios servicios, como una aplicación Java que necesita una base de datos y un servidor de caché.

**Ejemplo de `docker-compose.yml` para una aplicación:**

```yaml
version: '3.8'
services:
  mi-aplicacion:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
```

Este archivo define un servicio llamado `mi-aplicacion` que se construirá a partir del `Dockerfile` en el directorio actual y mapeará el puerto 8080 del contenedor al puerto 8080 del host.


#### 14.3. Despliegue de aplicaciones Java (JVM)

Para desplegar una aplicación Java en un contenedor, necesitas un **Dockerfile** que especifique cómo se construirá la imagen y cómo se ejecutará tu archivo `.jar`.

##### 14.3.1. Despliegue simple

El enfoque más directo es crear una imagen a partir de una base de Java y copiar tu archivo `.jar` pre-compilado en ella.

```dockerfile
# Usa una imagen base de Java 11 (puedes cambiar la versión)
FROM openjdk:11-jdk

# Copia el archivo .jar de tu aplicación al contenedor
COPY target/mi-aplicacion.jar mi-aplicacion.jar

# Define el comando para ejecutar tu aplicación
ENTRYPOINT ["java","-jar","/mi-aplicacion.jar"]
```

Para construir la imagen:

```bash
docker build -t mi-aplicacion .
```

Para ejecutar el contenedor:

```bash
docker run -p 8080:8080 mi-aplicacion
```

##### 14.3.2. Despliegue con un Dockerfile multi-etapa

Un enfoque más eficiente y profesional es usar un **Dockerfile multi-etapa**. Esto te permite compilar y construir el `.jar` dentro de un primer contenedor (la etapa de compilación) y luego copiar solo el `.jar` resultante a un segundo contenedor más ligero (la etapa de ejecución). Esto reduce significativamente el tamaño de la imagen final.

**Ejemplo con Gradle:**

```dockerfile
# Etapa de compilación, usa una imagen específica de Gradle con JDK
FROM gradle:jdk17 AS build

# Directorio de trabajo
WORKDIR /app

# Copia los archivos del proyecto para compilar
COPY build.gradle.kts .
COPY gradlew .
COPY gradle gradle
COPY src src

# Compila y construye el proyecto, evitando los tests
RUN ./gradlew build -x test

# Etapa de ejecución, usa una imagen más ligera solo con el JRE
FROM openjdk:17-jre-slim

# Directorio de trabajo
WORKDIR /app

# Copia el .jar de la etapa de compilación a la etapa de ejecución
COPY --from=build /app/build/libs/*.jar /app/my-app.jar

# Expone el puerto que la aplicación usa
EXPOSE 8080
# Ejecuta el jar
ENTRYPOINT ["java","-jar","/app/my-app.jar"]
```

#### 14.4. TestContainers y Docker

Al usar un Dockerfile multi-etapa, puedes integrar tus pruebas de integración con **TestContainers** en la etapa de compilación. Esto te permite ejecutar tus tests contra servicios reales (como bases de datos) dentro de un entorno controlado, asegurando que tu código funciona correctamente antes de generar la imagen final para producción.

El principal desafío es que el contenedor de compilación necesita comunicarse con el demonio de Docker del *host* para poder crear los contenedores de TestContainers. Para lograrlo, se usa la dirección especial `host.docker.internal` que Docker asigna a la máquina *host*.

**Configuración para TestContainers en el Dockerfile:**
Se puede usar un argumento de construcción (`ARG`) para establecer una variable de entorno `DOCKER_HOST` que apunte al host de Docker.

```dockerfile
# ... (etapa de compilación)
ARG DOCKER_HOST_ARG=tcp://host.docker.internal:2375
ENV DOCKER_HOST=$DOCKER_HOST_ARG

# Compila y construye el proyecto, aquí es donde se ejecutarían los tests de TestContainers
RUN ./gradlew build
# ... (etapa de ejecución)
```
