
### Cuestionario de Investigación y Desarrollo (10 Preguntas)

1.  **Justificación Arquitectónica:** Cuando se diseña una aplicación de servidor de alto rendimiento y con múltiples funcionalidades, ¿cuál es la principal ventaja de elegir una **Arquitectura basada en Microservicios** sobre una **Arquitectura Monolítica**, y cómo esta elección refuerza el **Principio de Responsabilidad Única (SRP)** de SOLID en el diseño de los componentes?

2.  **Trade-off de Asincronía:** Una aplicación necesita realizar numerosas llamadas a una API externa, lo que implica una alta latencia de E/S. Justifique la necesidad de utilizar un paradigma **asíncrono** (como `CompletableFuture` o RxJava) en lugar de un enfoque síncrono. ¿Qué recurso del sistema se busca liberar para mejorar la escalabilidad y el rendimiento?

3.  **Flujos Reactivos:** En el contexto de la Programación Reactiva (RxJava o Project Reactor), defina la diferencia fundamental entre un **Flujo Frío (*Cold Stream*)** y un **Flujo Caliente (*Hot Stream*)**. Si estuviera desarrollando un sistema de notificaciones en tiempo real donde los suscriptores tardíos deben perder los mensajes pasados, ¿cuál de los dos tipos de flujo sería apropiado implementar y por qué?

4.  **Manejo de Errores Predictivo:** Explique la filosofía del **Railway Oriented Programming (ROP)** que utiliza estructuras como `Either<L, R>` de Vavr, en contraste con el manejo de errores mediante **Excepciones no Comprobadas (`RuntimeException`)**. ¿Cómo logra ROP mantener explícito el "camino de error" sin interrumpir el flujo de control del programa?

5.  **Modernización de Acceso a Datos:** En un proyecto que actualmente utiliza la API estándar y bloqueante **JDBC** para acceder a bases de datos relacionales, ¿cuál es el argumento principal para migrar a una biblioteca de abstracción como **JDBI**? Mencione qué componente de JDBI se utiliza para resolver el "desfase objeto-relacional" entre las filas SQL y los objetos Java.

6.  **Principios de Diseño y Mutabilidad:** Analice la diferencia entre una **Clase estándar** de Java (potencialmente mutable) y un **Record** (inmutable). En el contexto de la **Programación Funcional**, ¿por qué se recomienda utilizar **Records** para modelar los datos transferidos a través de APIs (POJOs), y cómo se relaciona esto con el principio de **Inmutabilidad**?

7.  **Testing y Aislamiento:** Al escribir pruebas unitarias utilizando **JUnit**, ¿por qué es indispensable recurrir a un *framework* de **Mocking** como **Mockito**? Describa cómo el uso de objetos simulados (*mocks*) permite a un desarrollador adherirse al patrón **AAA (Arrange, Act, Assert)** y garantizar el **aislamiento** de la unidad de código bajo prueba.

8.  **Optimización de Despliegue:** Una aplicación Java genera un archivo `.jar` de 50MB. Explique el beneficio de utilizar un **Dockerfile multi-etapa** para la dockerización y el despliegue de esta aplicación en comparación con un Dockerfile simple. ¿Qué recurso se reduce significativamente en el contenedor de producción final gracias a este enfoque?

9.  **Arquitectura de Comunicación:** Compare el modelo de comunicación de una API **RESTful** (basada en el protocolo HTTP no orientado a la conexión) con la tecnología **Websocket**. Si el requerimiento de I+D es crear un servicio que transmita datos en tiempo real de forma continua (sin que el cliente lo solicite constantemente), ¿cuál de las dos APIs sería técnicamente necesaria?

10. **Seguridad y Estado en HTTP:** Un **JSON Web Token (JWT)** es fundamental para la seguridad en APIs REST. ¿Cuál es el propósito principal de la **Firma (*Signature*)** en un JWT y cómo contribuye el token en su conjunto a que la arquitectura RESTful sea **sin estado (*stateless*)** y escalable, eliminando la necesidad de almacenar sesiones en el servidor?