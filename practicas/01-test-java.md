### Cuestionario Tipo Test de Java (100 Preguntas)

- [Cuestionario Tipo Test de Java (100 Preguntas)](#cuestionario-tipo-test-de-java-100-preguntas)
  - [I. Aspectos Fundamentales, POO y Estructuras del Lenguaje](#i-aspectos-fundamentales-poo-y-estructuras-del-lenguaje)
  - [II. Colecciones y Streams (Funcional)](#ii-colecciones-y-streams-funcional)
  - [III. E/S y Ficheros](#iii-es-y-ficheros)
  - [IV. Bases de Datos](#iv-bases-de-datos)
  - [V. Concurrencia y Asincronía](#v-concurrencia-y-asincronía)
  - [VI. Programación Reactiva (RxJava)](#vi-programación-reactiva-rxjava)
  - [VII. Railway Oriented Programming (ROP)](#vii-railway-oriented-programming-rop)
  - [VIII. HTTP REST y JWT](#viii-http-rest-y-jwt)
  - [IX. Testing, Mocking y Herramientas](#ix-testing-mocking-y-herramientas)


#### I. Aspectos Fundamentales, POO y Estructuras del Lenguaje

1.  ¿Cuál es la función principal de la **Java Virtual Machine (JVM)** en el proceso de ejecución de un programa Java?
    A. Compilar el código fuente (`.java`) a bytecode.
    B. Traducir el bytecode (`.class`) a código máquina específico del sistema en tiempo real.
    C. Gestionar la interfaz de usuario.
    D. Ejecutar el código SQL en la base de datos.

2.  En el proceso de compilación, ¿qué formato intermedio almacena el `javac` en un archivo con extensión `.class`?
    A. Código fuente.
    B. Código máquina.
    C. Bytecode.
    D. Código binario nativo.

3.  ¿Cuál de los siguientes es un tipo de dato primitivo en Java que **no puede ser nulo**?
    A. `String`.
    B. `Integer` (Clase Envolvente).
    C. `int`.
    D. `Optional`.

4.  ¿Qué modificador de acceso restringe la visibilidad de un miembro a **solo dentro de la misma clase**?
    A. `public`.
    B. `protected`.
    C. `private`.
    D. `static`.

5.  Si se aplica el modificador `final` a una **clase**, ¿cuál es la restricción que se impone?
    A. El objeto no puede ser modificado (inmutabilidad).
    B. Impide que sea heredada por otras clases.
    C. Todos sus métodos deben ser abstractos.
    D. Solo puede tener una instancia (Singleton).

6.  ¿Cuál es el propósito principal de los **constructores** en una clase?
    A. Leer los valores de los atributos.
    B. Devolver un valor.
    C. Inicializar los atributos de un objeto al momento de su creación.
    D. Sobrescribir los métodos de la superclase.

7.  El principio de **Encapsulamiento** en POO se logra al agrupar datos y métodos y ocultar la implementación interna, principalmente mediante:
    A. El uso de `extends`.
    B. La sobrecarga de constructores.
    C. Modificadores de acceso (`private`) y métodos *getters* y *setters*.
    D. El uso de la interfaz `Runnable`.

8.  ¿Qué significa el principio de **Polimorfismo** ("muchas formas")?
    A. Que una clase debe tener una sola responsabilidad.
    B. Que los objetos de diferentes clases relacionadas por herencia pueden ser tratados como objetos de una superclase común.
    C. Que el código está cerrado a modificación y abierto a extensión.
    D. La ocultación de la implementación interna de una clase.

9.  Una **interfaz** es un "contrato" en Java. ¿Qué debe hacer una clase que implementa una interfaz?
    A. Heredar de otra clase abstracta.
    B. Ser declarada como `sealed`.
    C. Proporcionar una implementación para todos sus métodos abstractos.
    D. Utilizar el patrón *Builder*.

10. ¿Qué representa una clase **Record** (Java 16)?
    A. Un objeto que puede ser nulo.
    B. Un tipo de clase especial, inmutable y concisa, diseñada para ser un simple contenedor de datos.
    C. Una función de orden superior.
    D. Una implementación de `List`.

11. ¿Cuál es el propósito principal de la clase **`Optional`** (Java 8)?
    A. Gestionar la concurrencia.
    B. Prevenir los errores de `ClassCastException`.
    C. Actuar como un contenedor para un valor que puede o no estar presente, evitando `NullPointerException`.
    D. Forzar el uso de `try-catch`.

12. ¿Qué excepción lanza el método `get()` de `Optional` si el valor está ausente?
    A. `NullPointerException`.
    B. `IOException`.
    C. `NoSuchElementException`.
    D. `RuntimeException`.

13. ¿Qué garantiza el bucle **`do-while`**?
    A. Que la condición se evalúa al principio.
    B. Que solo se usa para colecciones.
    C. Que el bloque de código se ejecuta al menos una vez.
    D. Que se ejecuta de forma asíncrona.

14. En Java, ¿qué estructura se usa para asegurar que los recursos que implementan `AutoCloseable` (archivos, conexiones DB) se cierran automáticamente?
    A. El bloque `finally`.
    B. El bloque `catch`.
    C. `try-with-resources`.
    D. `ExecutorService`.

15. ¿Qué tipo de excepción **obliga** al programador a manejarla con `try-catch` o declararla con `throws`?
    A. Excepciones `unchecked` (no comprobadas).
    B. `Error`.
    C. Excepciones `checked` (comprobadas).
    D. `NullPointerException`.

16. Para crear una excepción personalizada que **no** sea obligatorio manejar, ¿de qué clase debe heredar?
    A. `Exception`.
    B. `Throwable`.
    C. `RuntimeException`.
    D. `Error`.

17. ¿Cuál es la principal ventaja de usar **genéricos** en Java?
    A. Permitir la herencia múltiple de clases.
    B. Proporcionar seguridad de tipos en tiempo de compilación, evitando errores de `ClassCastException` en tiempo de ejecución.
    C. Asegurar que los datos sean mutables.
    D. Forzar la implementación de `Callable`.

18. En una restricción de tipo genérico, ¿qué indica la sintaxis `<T extends Number>`?
    A. Que T es un supertipo de `Number`.
    B. Que T es un subtipo de `Number` o `Number` mismo.
    C. Que T es el tipo exacto `Number`.
    D. Que T debe implementar la interfaz `List`.

19. ¿Dónde se debe colocar la declaración del tipo genérico (`<T>`) en la sintaxis de un **método genérico**?
    A. Después del nombre del método.
    B. Antes del tipo de retorno.
    C. Dentro de la lista de parámetros.
    D. En la declaración de la clase.

20. ¿Qué principio SOLID establece que una clase debe tener **una, y solo una, razón para cambiar**?
    A. Principio Abierto/Cerrado (OCP).
    B. Principio de Sustitución de Liskov (LSP).
    C. Principio de Responsabilidad Única (SRP).
    D. Principio de Inversión de Dependencias (DIP).

21. ¿Qué principio SOLID promueve que los módulos de alto nivel no deben depender de los módulos de bajo nivel, sino que ambos deben depender de abstracciones?
    A. Principio de Sustitución de Liskov (LSP).
    B. Principio de Segregación de Interfaces (ISP).
    C. Principio de Inversión de Dependencias (DIP).
    D. Principio Abierto/Cerrado (OCP).

22. En POO, ¿cuál es el propósito de los **getters**?
    A. Modificar el valor de un atributo.
    B. Inicializar los atributos del objeto.
    C. Leer u obtener el valor de un atributo.
    D. Implementar la lógica de negocio.

23. ¿Cuál es la clase envolvente (*Wrapper Class*) para el tipo primitivo `int`?
    A. `Int`.
    B. `Integer`.
    C. `Double`.
    D. `Number`.

24. ¿Cuál de los siguientes es un patrón de **comportamiento**?
    A. Singleton.
    B. Adapter.
    C. Observer.
    D. Factory Method.

25. ¿Qué patrón arquitectónico divide la aplicación en capas (presentación, negocio, datos) mejorando la modularidad?
    A. Microservicios.
    B. Monolítica.
    C. Arquitectura de capas.
    D. Singleton.

#### II. Colecciones y Streams (Funcional)

26. ¿Qué interfaz de colección **no permite elementos duplicados** y es ideal para manejar un conjunto de valores únicos?
    A. `List`.
    B. `Map`.
    C. `Set`.
    D. `ArrayList`.

27. ¿Qué implementación de `List` es ideal para el acceso rápido por índice (`get()`)?
    A. `LinkedList`.
    B. `TreeSet`.
    C. `HashMap`.
    D. `ArrayList`.

28. ¿Qué estructura de datos almacena pares de **clave-valor** donde cada clave debe ser única?
    A. `List`.
    B. `Set`.
    C. `Map`.
    D. `Array`.

29. Un Tipo de Dato Abstracto (TDA) es una estructura de datos que define un conjunto de operaciones y propiedades, pero ¿qué oculta?
    A. La interfaz.
    B. La implementación interna.
    C. Las funciones lambda.
    D. Los *getters* y *setters*.

30. En la API Streams, ¿qué tipo de operación **no ejecuta** la lógica inmediatamente, sino que la prepara (evaluación perezosa)?
    A. Operación terminal.
    B. Operación de fuente.
    C. Operación intermedia.
    D. Operación de reducción.

31. ¿Qué operación de Stream se utiliza para transformar cada elemento del flujo en un nuevo valor?
    A. `filter()`.
    B. `reduce()`.
    C. `map()`.
    D. `count()`.

32. ¿Qué operación de Stream es análoga a la cláusula `WHERE` en SQL?
    A. `map(...)`.
    B. `filter(...)`.
    C. `groupingBy(...)`.
    D. `reduce()`.

33. ¿Cuál es el propósito del método **`parallelStream()`**?
    A. Forzar la ejecución secuencial.
    B. Dividir la colección en múltiples subtareas que se ejecutan simultáneamente en los diferentes núcleos del procesador.
    C. Asegurar la inmutabilidad de los datos originales.
    D. Reemplazar la necesidad de `ExecutorService`.

34. ¿Qué es una **expresión lambda** en Java?
    A. Un método de clase abstracta.
    B. La sintaxis de Java para crear una función anónima.
    C. Un tipo de excepción no comprobada.
    D. El tipo de retorno de un `CompletableFuture`.

35. Una **función pura** en programación funcional se caracteriza por que, dadas las mismas entradas, siempre produce la misma salida y:
    A. Lanza una excepción.
    B. Modifica variables globales.
    C. No causa efectos secundarios (como modificar variables globales o E/S).
    D. Siempre devuelve un `Optional`.

36. ¿Qué operación terminal de Stream se utiliza para combinar los elementos en un único resultado acumulado?
    A. `map()`.
    B. `collect()`.
    C. `reduce()`.
    D. `forEach()`.

37. ¿Cuál es un ejemplo de **operación de cortocircuito** en Streams?
    A. `map()`.
    B. `filter()`.
    C. `anyMatch()`.
    D. `sorted()`.

38. ¿Cuál es una de las interfaces funcionales predefinidas de Java utilizada para **evaluar un booleano**?
    A. `Consumer<T>`.
    B. `Function<T, R>`.
    C. `Predicate<T>`.
    D. `Supplier<T>`.

39. ¿Qué se promueve con el enfoque de la inmutabilidad en la programación funcional?
    A. Modificar las variables en su lugar.
    B. Crear una nueva variable con el resultado en lugar de modificar la original.
    C. Aumentar el uso de excepciones no comprobadas.
    D. Uso exclusivo de `ArrayList`.

40. El uso de Streams permite escribir código **declarativo**, lo que significa que el código se enfoca en:
    A. El "cómo" se ejecutan los pasos (imperativo).
    B. El "qué" se quiere lograr.
    C. La inyección de dependencias.
    D. El manejo de hilos.

#### III. E/S y Ficheros

41. ¿Cuál es el formato de intercambio de datos más utilizado que se basa en pares clave-valor y es legible por humanos?
    A. XML.
    B. BSON.
    C. CSV.
    D. JSON.

42. Para manipular archivos de texto, ¿qué API de Java se recomienda por su integración con los Streams?
    A. La API Clásica (`java.io`).
    B. La API Moderna (`java.nio.file`).
    C. JDBC.
    D. Scanner.

43. En el manejo de ficheros, ¿qué método de la API Moderna se utiliza para procesar el contenido línea por línea sin cargar todo el archivo en memoria?
    A. `BufferedReader.readLine()`.
    B. `Files.writeString`.
    C. `Files.lines` y Streams.
    D. `Scanner.nextLine()`.

44. ¿Qué biblioteca se utiliza comúnmente en Java para la **serialización** (objeto a JSON) y **deserialización** (JSON a objeto)?
    A. JUnit.
    B. JDBI.
    C. Jackson o GSON.
    D. Retrofit.

45. ¿Cuál es el formato de intercambio de datos que representa información en forma tabular, con campos separados por un delimitador (ej. coma)?
    A. JSON.
    B. XML.
    C. JWT.
    D. CSV.

46. ¿Qué sintaxis introducida en Java 15+ se utiliza para crear cadenas multilínea sin usar caracteres de escape?
    A. Concatenación con `+`.
    B. `String.format()`.
    C. Plantillas de texto (usando `"""`).
    D. Uso de `StringBuilder`.

47. ¿Qué clase de Java se utiliza típicamente para la entrada de datos por consola?
    A. `System.out`.
    B. `Files`.
    C. `Scanner`.
    D. `BufferedReader`.

#### IV. Bases de Datos

48. ¿Qué significa el acrónimo CRUD?
    A. Compile, Read, Update, Deploy.
    B. Connect, Run, Use, Database.
    C. Create, Read, Update, Delete.
    D. Change, Run, Update, Disconnect.

49. ¿Cuál es la principal ventaja de usar **`PreparedStatement`** en JDBC?
    A. Solo se utiliza para operaciones `SELECT`.
    B. Es más lento pero más legible.
    C. Ayuda a prevenir ataques de inyección SQL y es más eficiente al reutilizar la sentencia precompilada.
    D. Permite realizar consultas reactivas.

50. El patrón **DAO (Data Access Object)** se utiliza para:
    A. Gestionar la lógica de negocio.
    B. Abstraer el acceso a los datos y proporcionar una interfaz común para interactuar con la base de datos.
    C. Simular objetos para pruebas.
    D. Definir la arquitectura de microservicios.

51. La biblioteca **JDBI** simplifica JDBC al permitir definir el DAO como una interfaz usando anotaciones como:
    A. `@Test`.
    B. `@GET`.
    C. `@SqlUpdate` y `@SqlQuery`.
    D. `@Override`.

52. En JDBI, ¿qué clase o interfaz se requiere para mapear las columnas de un `ResultSet` de SQL a un objeto Java (POJO)?
    A. `PreparedStatement`.
    B. `Optional`.
    C. `RowMapper`.
    D. `CodecRegistry`.

53. La anotación `@BindBean` en JDBI se utiliza para:
    A. Registrar el `RowMapper`.
    B. Le dice a JDBI que use las propiedades del objeto para enlazar los parámetros de la consulta.
    C. Ejecutar la consulta.
    D. Gestionar la conexión a la base de datos.

54. MongoDB almacena sus datos en documentos de formato **BSON**. ¿Qué es BSON?
    A. Un formato de texto puro.
    B. Una versión binaria de JSON.
    C. Un formato relacional.
    D. Un tipo de consulta SQL.

55. Al usar la **API Tipada** de MongoDB, ¿qué componente es necesario para que el *driver* sepa cómo serializar y deserializar tus clases Java a documentos BSON?
    A. `RowMapper`.
    B. `PreparedStatement`.
    C. `CodecRegistry`.
    D. `ExecutorService`.

56. ¿Qué biblioteca proporciona un *driver* reactivo para bases de datos en aplicaciones Java, permitiendo trabajar de forma asíncrona y no bloqueante?
    A. JDBC.
    B. JDBI.
    C. R2DBC.
    D. Retrofit.

57. ¿Qué clase del driver de MongoDB se utiliza para construir consultas de forma segura (equivalente al `WHERE` en SQL)?
    A. `Document`.
    B. `Filters`.
    C. `Updates`.
    D. `CodecRegistry`.

58. La API **JDBC (Java Database Connectivity)** es el estándar en Java para interactuar con qué tipo de bases de datos?
    A. Bases de datos NoSQL orientadas a documentos.
    B. Bases de datos reactivas.
    C. Bases de datos relacionales.
    D. Ficheros de texto.

59. El `try-with-resources` es una buena práctica en bases de datos para asegurar el cierre de qué recurso?
    A. La clase DAO.
    B. El `RowMapper`.
    C. La `Connection` a la base de datos.
    D. El `CodecRegistry`.

60. ¿Cuál es el principal desafío que el patrón DAO busca mitigar entre el modelo de datos de una base de datos relacional y el modelo de objetos de un lenguaje POO?
    A. El problema de la inyección SQL.
    B. El desfase objeto-relacional.
    C. La falta de un `RowMapper`.
    D. La necesidad de un `ExecutorService`.

#### V. Concurrencia y Asincronía

61. ¿Qué es la **Programación Asíncrona**?
    A. La ejecución de tareas de manera secuencial, una después de la otra.
    B. Un paradigma que permite que las tareas se ejecuten en segundo plano sin bloquear el flujo principal.
    C. El uso exclusivo de hilos.
    D. La capacidad de un programa para ejecutar múltiples hilos.

62. ¿En qué escenario surge principalmente la necesidad de utilizar la asincronía y la concurrencia?
    A. En la manipulación de tipos primitivos.
    B. En escenarios de **E/S (Entrada/Salida) intensiva** (red, archivos, base de datos).
    C. Cuando se usan `Records`.
    D. Al implementar interfaces funcionales.

63. ¿Cuál es la forma **preferida** de crear un hilo en Java, que permite flexibilidad con las expresiones lambda y herencia de otras clases?
    A. Heredando de la clase `Thread`.
    B. Implementando la interfaz `Callable`.
    C. Implementando la interfaz `Runnable`.
    D. Usando `System.out.println`.

64. ¿Qué clase de Java se utiliza como interfaz principal para gestionar un **Pool de Hilos** (colección de hilos listos para ser reutilizados)?
    A. `CompletableFuture`.
    B. `Thread`.
    C. `ExecutorService`.
    D. `Optional`.

65. La clase **`CompletableFuture`** (Java 8+) es una abstracción para el manejo asíncrono. ¿Cuál es su naturaleza fundamental?
    A. Es un hilo de ejecución de bajo nivel.
    B. Es un contenedor para el resultado de una tarea que se ejecuta asíncronamente en un pool de hilos.
    C. Es un tipo de `Subject` reactivo.
    D. Es un tipo de colección concurrente.

66. ¿Qué ocurre cuando el código llama a `Future.get()` si la tarea asíncrona aún no ha finalizado?
    A. Continúa la ejecución del código inmediatamente.
    B. El método `get()` **bloquea el hilo actual** hasta que la tarea termine y el resultado esté disponible.
    C. Se lanza una excepción `InterruptedException`.
    D. La tarea se mueve a otro `ExecutorService`.

67. ¿Qué es una **Sección Crítica** en concurrencia?
    A. Un método que lanza una excepción `checked`.
    B. Un bloque de código que accede a un recurso compartido y que debe ser ejecutada de forma exclusiva por un solo hilo a la vez.
    C. Un `try-with-resources`.
    D. Un patrón de diseño de creación.

68. ¿Qué ventaja ofrece la interfaz **`Lock`** (ej. `ReentrantLock`) sobre la palabra clave `synchronized` para la sincronización?
    A. `synchronized` es más rápido.
    B. El `Lock` proporciona una forma más flexible de sincronización al permitir adquirir y liberar explícitamente los bloqueos.
    C. `Lock` solo funciona con `Callable`.
    D. `synchronized` previene ataques de inyección SQL.

69. ¿Qué colección está diseñada para ser utilizada en entornos multi-hilo, proporcionando acceso concurrente seguro y eficiente a los datos?
    A. `ArrayList`.
    B. `HashMap`.
    C. `ConcurrentHashMap`.
    D. `TreeSet`.

70. ¿Qué método de la clase `Thread` se utiliza para hacer que el hilo actual espere a que otro hilo haya terminado su ejecución?
    A. `notify()`.
    B. `wait()`.
    C. `join()`.
    D. `shutdown()`.

#### VI. Programación Reactiva (RxJava)

71. La Programación Reactiva se basa en el concepto de:
    A. Ejecución secuencial y sincrónica.
    B. La gestión de **flujos de datos asincrónicos** y la propagación de cambios.
    C. El patrón *Builder*.
    D. La inmutabilidad de los tipos primitivos.

72. En RxJava, ¿qué componente es la **fuente de datos** que emite una secuencia de elementos y notificaciones de error o finalización?
    A. `Observer`.
    B. `Scheduler`.
    C. `Observable`.
    D. `Completable`.

73. ¿Qué significa que un flujo reactivo sea **"Cold" (frío)**?
    A. La emisión de datos ya ha comenzado antes de cualquier suscripción.
    B. El trabajo no comienza hasta que alguien se suscribe, y cada suscriptor recibe su propia secuencia completa de datos.
    C. El flujo utiliza un `Subject`.
    D. El flujo está optimizado para cálculos intensivos.

74. ¿Qué tipo especializado de `Observable` en RxJava se utiliza para operaciones que garantizan emitir **un solo valor** o un error?
    A. `Flowable`.
    B. `Completable`.
    C. `Single`.
    D. `Maybe`.

75. ¿Qué operador reactivo es crucial para **encadenar llamadas asíncronas** al transformar un elemento en un nuevo `Observable` y luego aplanar los resultados en un solo flujo?
    A. `map()`.
    B. `filter()`.
    C. `zip()`.
    D. `flatMap()`.

76. En RxJava, ¿qué componente controla en qué **hilo de ejecución** se llevan a cabo las operaciones del flujo, siendo vital para mover tareas pesadas a un segundo plano?
    A. `Observer`.
    B. `Subject`.
    C. `Scheduler`.
    D. `Flowable`.

77. ¿Cuál es el propósito de `Schedulers.io()`?
    A. Ejecutar cálculos intensivos (`computation`).
    B. Actualizar la interfaz de usuario.
    C. Realizar operaciones de Entrada/Salida (lectura de archivos, red).
    D. Bloquear el hilo principal.

78. ¿Qué tipo de flujo en RxJava está diseñado específicamente para manejar el mecanismo de **Contrapresión (*Backpressure*)**?
    A. `Observable`.
    B. `Single`.
    C. `Flowable`.
    D. `Completable`.

79. En Project Reactor, ¿qué tipo de secuencia representa una secuencia de 0 a **N** elementos?
    A. `Mono`.
    B. `Single`.
    C. `Flux`.
    D. `Completable`.

80. En RxJava, ¿qué componente actúa a la vez como **Observer y Observable** (un "puente") y se utiliza para modelar flujos calientes (ej. sistema de notificaciones)?
    A. `Single`.
    B. `Subject`.
    C. `Flowable`.
    D. `CompletableFuture`.

#### VII. Railway Oriented Programming (ROP)

81. ¿Cuál es el problema que ROP busca mitigar en el manejo de errores de negocio?
    A. La imposibilidad de usar `try-catch`.
    B. La interrupción del flujo normal de ejecución y la verbosidad del `try-catch`.
    C. La inmutabilidad de los datos.
    D. La necesidad de un `CompletableFuture`.

82. Para implementar ROP en Java, ¿qué clase de la librería **Vavr** se utiliza, representando dos posibles valores (Left para error, Right para éxito)?
    A. `Optional<T>`.
    B. `CompletableFuture`.
    C. `Either<L, R>`.
    D. `Flowable`.

83. En el encadenamiento de operaciones ROP con `Either`, ¿qué operador permite que si ocurre un fallo (Left), el error se propague automáticamente y se salten las operaciones restantes?
    A. `map()`.
    B. `fold()`.
    C. `flatMap()`.
    D. `bimap()`.

84. ¿Qué operador terminal de Vavr (`Either`) recibe dos funciones (una para `Left` y otra para `Right`) para "fusionar" los dos posibles caminos en un único valor final?
    A. `map()`.
    B. `fold()`.
    C. `get()`.
    D. `orElse()`.

85. ¿Cuál es un beneficio clave de ROP en aplicaciones Java?
    A. Garantiza la ejecución secuencial en un solo hilo.
    B. El manejo de errores es explícito y parte del flujo de datos, mejorando la legibilidad.
    C. Reemplaza todas las excepciones.
    D. Permite la mutabilidad de los datos.

#### VIII. HTTP REST y JWT

86. ¿Cuál es el protocolo fundamental y no orientado a la conexión que se utiliza para la creación de APIs REST?
    A. TCP/IP.
    B. JDBC.
    C. HTTP (HyperText Transfer Protocol).
    D. WebSocket.

87. ¿Qué verbo HTTP se utiliza para **actualizar parcialmente** un recurso en el servidor?
    A. `PUT`.
    B. `POST`.
    C. `GET`.
    D. `PATCH`.

88. ¿Qué código de respuesta HTTP indica que la solicitud ha tenido éxito y **se ha creado un nuevo recurso**?
    A. 200 OK.
    B. 204 No Content.
    C. 201 Created.
    D. 404 Not Found.

89. **Retrofit** permite definir la API como una **interfaz de Java** utilizando:
    A. JDBC.
    B. Anotaciones HTTP (`@GET`, `@POST`, etc.).
    C. El patrón DAO.
    D. `try-catch`.

90. ¿Qué anotación de Retrofit se utiliza en el parámetro de un método para enviar el objeto serializado en el **cuerpo de la petición**?
    A. `@Path`.
    B. `@Query`.
    C. `@Body`.
    D. `@FormUrlEncoded`.

91. Para que Retrofit devuelva tipos asíncronos modernos como `CompletableFuture` o `Single` (RxJava), ¿qué componente se debe añadir al cliente Retrofit?
    A. Un `GsonConverterFactory`.
    B. Un adaptador de llamadas (*Call Adapter*).
    C. Un `CodecRegistry`.
    D. El `ExecutorService`.

92. Un JSON Web Token (JWT) se compone de tres partes. ¿Cuál de ellas contiene las **reclamaciones** (*claims*) sobre la entidad (ej. el usuario) y los datos adicionales?
    A. Header (Encabezado).
    B. Signature (Firma).
    C. Payload (Carga Útil).
    D. Clave Secreta.

93. ¿Cuál es el propósito de la **Firma** en un JWT?
    A. Incluir el algoritmo de cifrado.
    B. Codificar los *claims* en Base64Url.
    C. Asegurar que el mensaje no ha sido alterado.
    D. Definir el tipo de token.

94. En una API REST, si el servidor encuentra una situación que no sabe cómo manejar, ¿qué código de error HTTP debe devolver?
    A. 400 Bad Request.
    B. 404 Not Found.
    C. 500 Internal Server Error.
    D. 401 Unauthorized.

95. ¿Qué biblioteca permite la comunicación **bidireccional y en tiempo real** entre cliente y servidor a través de una conexión persistente, a diferencia de REST?
    A. API GraphQL.
    B. API RESTful.
    C. API Websocket.
    D. HTTP.

#### IX. Testing, Mocking y Herramientas

96. ¿Cuál es el *framework* más popular en Java para escribir y ejecutar **pruebas unitarias**?
    A. Mockito.
    B. Gradle.
    C. Javadocs.
    D. JUnit.

97. ¿Qué patrón de estructura de pruebas unitarias divide cada prueba en preparación, acción y verificación del resultado?
    A. SRP (Single Responsibility Principle).
    B. ROP (Railway Oriented Programming).
    C. CRUD (Create, Read, Update, Delete).
    D. AAA (Arrange, Act, Assert).

98. ¿Qué *framework* se utiliza para crear **objetos simulados (*mocks*)** para aislar el código bajo prueba de sus dependencias, asegurando que las pruebas sean verdaderamente unitarias?
    A. JUnit.
    B. Gradle.
    C. Mockito.
    D. Retrofit.

99. En Mockito, ¿qué método se utiliza para **verificar** que el código bajo prueba interactuó con su dependencia (*mock*) llamando a un método específico?
    A. `when()`.
    B. `assertEquals()`.
    C. `assertThrows()`.
    D. `verify()`.

100. ¿Qué herramienta de automatización de construcción se utiliza para gestionar dependencias (librerías), compilar código y ejecutar pruebas en Java, siendo más flexible que Maven?
    A. Docker.
    B. Javadocs.
    C. Gradle.
    D. TestContainers.