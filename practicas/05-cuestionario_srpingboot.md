
### Preguntas de Investigación y Desarrollo (I+D)

- [Preguntas de Investigación y Desarrollo (I+D)](#preguntas-de-investigación-y-desarrollo-id)
  - [I. Arquitectura y Diseño de Servicios (Preguntas 1-8)](#i-arquitectura-y-diseño-de-servicios-preguntas-1-8)
  - [II. Persistencia Avanzada (JPA y MongoDB) (Preguntas 9-14)](#ii-persistencia-avanzada-jpa-y-mongodb-preguntas-9-14)
  - [III. Implementación y Configuración Avanzada (Preguntas 15-20)](#iii-implementación-y-configuración-avanzada-preguntas-15-20)
  - [IV. Arquitecturas Modernas (GraphQL y WebSockets) (Preguntas 21-25)](#iv-arquitecturas-modernas-graphql-y-websockets-preguntas-21-25)


#### I. Arquitectura y Diseño de Servicios (Preguntas 1-8)

1.  **Comparativa de Servicios Orientados a la Conexión vs. Sin Conexión:** Justifique, desde una perspectiva de I+D, por qué el protocolo HTTP es inherentemente sin estado y cómo esta característica simplifica la implementación del servidor y facilita la escalabilidad, a pesar de las desventajas que presenta la falta de fiabilidad y control de flujo en la transferencia de datos.
2.  **Decisión Arquitectónica (REST vs. SOAP):** Un nuevo proyecto requiere una alta fiabilidad, seguridad integrada y soporte para transacciones complejas. Argumente por qué SOAP podría ser considerado, citando sus ventajas sobre REST en estos aspectos específicos, a pesar de que SOAP es generalmente más lento y verboso.
3.  **Diseño de API RESTful:** Explique los principios de la **Interfaz Uniforme** en REST. ¿Por qué se recomienda en I+D utilizar sustantivos en plural para los *endpoints* (ej. `/productos`) y qué implicaciones tiene esta convención para el uso de los métodos HTTP (GET, POST, PUT, DELETE)?
4.  **Gestión de Respuestas HTTP:** En el diseño de una API REST, justifique la elección del código de estado **204 No Content** para la operación DELETE, en lugar del 200 OK. ¿Qué implicaciones tiene esta elección para la eficiencia de la red?
5.  **Análisis de Seguridad y Protocolos (WebSockets):** Describa el protocolo WebSockets y el problema de rendimiento y latencia que resuelve en comparación con las técnicas anteriores como el *polling* o *long polling*. ¿Por qué WebSockets es fundamental para aplicaciones que requieren comunicación bidireccional y en tiempo real?
6.  **Principios de Spring:** Explique la relación entre la **Inversión de Control (IoC)** y la **Inyección de Dependencias (DI)** en el contexto de Spring Framework. ¿Cómo facilita la DI la capacidad del equipo de I+D para realizar pruebas unitarias en un componente?
7.  **Versionado de APIs:** Argumente por qué el versionado de una API (ej. `/v1/users`) es una práctica crucial en el desarrollo. ¿Qué riesgo principal intenta mitigar el versionado al realizar cambios en la API?
8.  **Patrón DTO y Seguridad:** Explique el rol del patrón DTO (Data Transfer Object) en la arquitectura de un servicio. ¿Cómo el uso de DTOs contribuye a la modularidad, facilita el ensamblaje de objetos y proporciona una capa de seguridad al evitar la exposición de las entidades de la base de datos?

#### II. Persistencia Avanzada (JPA y MongoDB) (Preguntas 9-14)

9.  **Identificadores (UUID vs. Autoincrementales):** En un escenario de desarrollo donde se requiere que los objetos sean creados y relacionados en la lógica de negocio o en pruebas unitarias **sin depender de la persistencia inmediata** en la base de datos, justifique la elección del identificador **UUID** sobre el Autoincremental, destacando las implicaciones en la responsabilidad de generación.
10. **Borrado Lógico vs. Físico:** El equipo debe implementar una estrategia de eliminación de datos que mantenga la integridad referencial y permita la auditoría y recuperación. Justifique la implementación del **Borrado Lógico** sobre el Borrado Físico para este requisito, detallando sus ventajas.
11. **Diseño de Relaciones Bidireccionales JPA:** Describa el problema de la **recursión infinita** que surge al serializar relaciones bidireccionales en JPA a JSON. Mencione al menos una estrategia de Jackson (`@JsonIgnoreProperties`, `@JsonBackReference`, etc.) para resolver este problema sin afectar la persistencia de las entidades.
12. **Mapeo de Herencia en JPA:** Si se requiere que cada clase en una jerarquía de herencia se mapee a su propia tabla y que estas tablas se unan mediante claves foráneas para mantener la integridad relacional, ¿qué estrategia de herencia JPA (`@Inheritance`) debe seleccionarse?
13. **Diseño NoSQL (Documentos Embebidos vs. Referencias):** Al trabajar con Spring Data MongoDB, justifique la decisión de utilizar **documentos embebidos** para modelar una relación "Uno a Muchos" simple (ej. un libro y sus comentarios). ¿Qué ventaja de rendimiento se busca con esta técnica en comparación con el uso de referencias?
14. **Pruebas de Repositorios MongoDB:** Explique las diferencias metodológicas entre el uso de **Flapdoodle (MongoDB embebido)** y **TestContainers** para el testeo de repositorios MongoDB. ¿En qué escenarios de I+D se preferiría usar TestContainers?

#### III. Implementación y Configuración Avanzada (Preguntas 15-20)

15. **Cache en Servicios:** Describa la funcionalidad de las anotaciones `@Cacheable` y `@CachePut` en Spring. ¿Por qué es fundamental que la clase principal esté anotada con `@EnableCaching` para que estas funcionen?
16. **Criterios de Búsqueda Dinámicos JPA:** Para permitir que la capa de servicio reciba parámetros de filtrado opcionales y construya una consulta dinámica que combine múltiples criterios (ej. nombre LIKE 'x' AND precio < 'y'), ¿qué interfaz de Spring Data JPA debe extender el repositorio, y cuál es el rol de la clase `Specification`?
17. **Inicialización de Componentes:** Describa dos métodos de inicialización en Spring Boot (`CommandLineRunner` y `@PostConstruct`). Si un servicio de almacenamiento necesita crear directorios o borrar datos antiguos *después* de que todas las dependencias (como `StorageService`) hayan sido inyectadas por Spring, ¿cuál de las dos técnicas garantiza este orden y por qué?
18. **Gestión de Perfiles:** Explique cómo el uso de perfiles de Spring Boot (`application-dev.properties`, `application-prod.properties`) facilita el despliegue y el testeo en diferentes entornos (I+D, Producción). ¿Cómo se activa un perfil específico al ejecutar la aplicación?
19. **Seguridad y CORS:** Explique la función del mecanismo de seguridad CORS (Cross-Origin Resource Sharing) en el contexto de una aplicación de *frontend* que consume una API *backend* en un dominio diferente. ¿Qué riesgo de seguridad se mitiga al configurar correctamente CORS?
20. **Integridad de Datos y Validación:** El equipo de desarrollo utiliza DTOs de entrada. ¿Cómo se utiliza la anotación `@Valid` en el controlador en conjunto con las restricciones de **Jakarta Bean Validation** (ej. `@NotBlank`, `@Size`) para asegurar la integridad de los datos antes de que lleguen a la capa de servicio?

#### IV. Arquitecturas Modernas (GraphQL y WebSockets) (Preguntas 21-25)

21. **GraphQL y *Overfetching*:** Defina el problema de *overfetching* en las APIs REST. ¿Cómo resuelve GraphQL este problema y qué ventaja principal ofrece esto en el rendimiento de aplicaciones con recursos limitados (ej. aplicaciones móviles)?
22. **Modelo de Tipado GraphQL:** GraphQL se basa en un esquema con tipado fuerte. Explique el significado de la sintaxis `[Producto!]!` en un esquema GraphQL y qué implica en términos de obligatoriedad de la lista y de sus elementos.
23. **Operaciones GraphQL:** Describa las tres categorías principales de operaciones soportadas por GraphQL (`Query`, `Mutation`, `Subscription`). ¿Cuál de ellas está destinada a modificar los datos del lado del servidor?
24. **Suscripciones GraphQL y Real Time:** Las suscripciones de GraphQL permiten recibir notificaciones en tiempo real (push de eventos). ¿Qué protocolo subyacente de comunicación debe estar configurado para que estas suscripciones puedan funcionar y entregar la información al cliente?
25. **Seguridad de la Comunicación (SSL/TSL):** En entornos de producción, se requiere que toda la comunicación sea cifrada. Explique brevemente cómo se configura Spring Boot para utilizar SSL/TSL (HTTPS) en lugar de HTTP, mencionando la necesidad de certificados y claves.