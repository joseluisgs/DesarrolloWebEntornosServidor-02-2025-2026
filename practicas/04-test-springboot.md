## Cuestionario Spring Boot y Servicios Web (100 Preguntas)

- [Cuestionario Spring Boot y Servicios Web (100 Preguntas)](#cuestionario-spring-boot-y-servicios-web-100-preguntas)
  - [I. Servicios Web y Arquitecturas REST (12 Preguntas)](#i-servicios-web-y-arquitecturas-rest-12-preguntas)
  - [II. Spring Core, IoC y Spring Web (13 Preguntas)](#ii-spring-core-ioc-y-spring-web-13-preguntas)
  - [III. Servicios, DTOs, Mapeo, Validación y Cache (10 Preguntas)](#iii-servicios-dtos-mapeo-validación-y-cache-10-preguntas)
  - [IV. Testing en Spring Boot (10 Preguntas)](#iv-testing-en-spring-boot-10-preguntas)
  - [V. Spring Data JPA y Persistencia (15 Preguntas)](#v-spring-data-jpa-y-persistencia-15-preguntas)
  - [VI. Spring Data MongoDB y NoSQL (10 Preguntas)](#vi-spring-data-mongodb-y-nosql-10-preguntas)
  - [VII. WebSockets (5 Preguntas)](#vii-websockets-5-preguntas)
  - [VIII. GraphQL (5 Preguntas)](#viii-graphql-5-preguntas)
  - [IX. Temas Avanzados y Utilities (5 Preguntas)](#ix-temas-avanzados-y-utilities-5-preguntas)
  - [X. Spring Security y JWT (15 Preguntas)](#x-spring-security-y-jwt-15-preguntas)


### I. Servicios Web y Arquitecturas REST (12 Preguntas)

1.  Un servicio orientado a la conexión, como una llamada FTP, se caracteriza por ofrecer:
    A. Mayor eficiencia para datos pequeños.
    B. Rapidez al no requerir la configuración inicial de una conexión.
    C. Fiabilidad, garantizando que los datos se entregan en el orden correcto y sin errores.
    D. Mayor resiliencia ante problemas de red, ya que no depende de una conexión constante.

2.  El protocolo HTTP (Hypertext Transfer Protocol) es la base de la Web y se define como:
    A. Orientado a la conexión y basado en XML.
    B. Sin estado y basado en texto.
    C. Bidireccional y que mantiene el estado entre solicitudes.
    D. Un protocolo que utiliza WSDL para describir su funcionalidad.

3.  ¿Qué componente de una solicitud HTTP contiene los metadatos sobre la solicitud, como el tipo de contenido o la información de autenticación?
    A. URL.
    B. Método HTTP.
    C. Encabezados (Headers).
    D. Cuerpo (Body).

4.  ¿Cuál de las siguientes características corresponde a la Interfaz Uniforme, uno de los principios clave de REST?
    A. La capacidad de almacenar respuestas en caché.
    B. El uso de un conjunto limitado de métodos bien definidos (GET, POST, etc.) para interactuar con los recursos.
    C. La prohibición de que el servidor almacene el estado del cliente entre peticiones.
    D. La arquitectura compuesta por varias capas de servidores.

5.  ¿Qué método HTTP se utiliza para **reemplazar completamente** la representación actual de un recurso con una nueva en una API REST?
    A. POST.
    B. GET.
    C. DELETE.
    D. PUT.

6.  Si un cliente envía datos JSON mal formados en una solicitud POST, el código de estado HTTP más adecuado que el servidor debe devolver es:
    A. 201 Created.
    B. 404 Not Found.
    C. 400 Bad Request.
    D. 405 Method Not Allowed.

7.  El código de estado HTTP 204 No Content se utiliza para indicar que:
    A. El recurso solicitado no se encontró.
    B. La solicitud fue exitosa, pero no hay contenido para enviar de vuelta.
    C. La solicitud requiere autenticación de usuario.
    D. La solicitud tuvo éxito y resultó en la creación de un nuevo recurso.

8.  Si un usuario autenticado intenta modificar datos a los que **no tiene acceso** debido a sus permisos, el código de estado HTTP apropiado es:
    A. 401 Unauthorized.
    B. 403 Forbidden.
    C. 404 Not Found.
    D. 500 Internal Server Error.

9.  Una de las desventajas de la arquitectura SOAP en comparación con REST es que:
    A. Es un estilo arquitectónico menos extensible.
    B. Puede ser más lento y consumir más recursos debido a su verbosidad basada en XML.
    C. No soporta operaciones de servicio web complejas como transacciones.
    D. No es independiente del lenguaje de programación o del protocolo de transporte.

10. El versionado de una API, a menudo incluyendo el número de versión en la URL (ej. `/v1/users`), es una buena práctica para:
    A. Aumentar la velocidad del procesamiento del *request*.
    B. Simplificar la Inversión de Control.
    C. Garantizar la estabilidad, la evolución controlada y la compatibilidad hacia atrás.
    D. Forzar el uso de un solo formato de intercambio de datos (JSON).

11. El mecanismo que permite a un cliente especificar el formato de los datos que desea recibir del servidor (ej. XML o JSON) se conoce como:
    A. Cacheabilidad.
    B. Negociación de contenido.
    C. HATEOAS.
    D. Interfaz uniforme.

12. ¿Qué característica REST permite que las respuestas del servidor sean almacenadas por el cliente, lo que reduce la necesidad de solicitudes repetitivas?
    A. Sistema en capas.
    B. Sin estado.
    C. Cliente-Servidor.
    D. Cacheable.

### II. Spring Core, IoC y Spring Web (13 Preguntas)

13. ¿Cuál es el principio de diseño de software en Spring que invierte el control del flujo, delegando al *framework* la creación y gestión de los objetos?
    A. Programación Orientada a Aspectos (AOP).
    B. Inyección de Dependencias (DI).
    C. Inversión de Control (IoC).
    D. Patrón Singleton.

14. El módulo central del Spring Framework que proporciona la implementación fundamental de la Inversión de Control (IoC) y la Inyección de Dependencias (DI) es:
    A. Spring AOP.
    B. Spring Core.
    C. Spring Web MVC.
    D. Spring Data.

15. En Spring Boot, la anotación `@Autowired` se utiliza para:
    A. Definir la clase principal de la aplicación.
    B. Marcar una clase como un *bean* administrado por Spring.
    C. Inyectar automáticamente una dependencia (otro *bean*) en una clase.
    D. Definir el alcance de un *bean*.

16. Los "Starters" en Spring Boot son:
    A. Componentes utilizados para la monitorización (Actuator).
    B. La clase principal anotada con `@SpringBootApplication`.
    C. Dependencias preconfiguradas que facilitan la incorporación de tecnologías y funcionalidades específicas.
    D. Anotaciones de seguridad (Spring Security).

17. La anotación `@SpringBootApplication` combina las funcionalidades de `@Configuration`, `@EnableAutoConfiguration` y, ¿cuál más?
    A. `@RestController`.
    B. `@ComponentScan`.
    C. `@Autowired`.
    D. `@Bean`.

18. ¿Cuál es el valor por defecto del alcance (`scope`) de un *bean* gestionado por el contenedor de Spring?
    A. `Request`.
    B. `Prototype`.
    C. `Singleton`.
    D. `Session`.

19. En la arquitectura de capas de Spring Boot, el componente que encapsula la lógica de negocio y se anota con `@Service` tiene el objetivo de cumplir con el principio de:
    A. El patrón DTO.
    B. El patrón Observer.
    C. Responsabilidad única.
    D. Inyección de Dependencias.

20. Para obtener datos enviados serializados en el **cuerpo** de una petición HTTP (como un objeto JSON), el controlador utiliza la anotación:
    A. `@RequestParam`.
    B. `@PathVariable`.
    C. `@RequestBody`.
    D. `@ResponseBody`.

21. Para obtener información de la petición a través de la URL como un parámetro de consulta (Query), se utiliza la anotación:
    A. `@PathVariable`.
    B. `@RequestBody`.
    C. `@RequestParam`.
    D. `@RestController`.

22. Para que un controlador devuelva directamente objetos serializados (ej. JSON) en lugar de una vista (HTML), se anota la clase con:
    A. `@Controller`.
    B. `@Configuration`.
    C. `@Service`.
    D. `@RestController`.

23. ¿Qué clase de Spring Web se utiliza para devolver una respuesta con un código de estado HTTP específico (ej. 201 Created) junto con el cuerpo de la respuesta?
    A. `ResponseEntity`.
    B. `HttpStatus`.
    C. `@ResponseStatus`.
    D. `RequestEntity`.

24. Para crear *endpoints* que manejen peticiones de creación de recursos, se utiliza la anotación:
    A. `@GetMapping`.
    B. `@PutMapping`.
    C. `@PostMapping`.
    D. `@PatchMapping`.

25. ¿Qué interfaz debe implementar una clase para que su método `run` se ejecute por consola o antes de que el contexto de Spring Boot esté completamente cargado?
    A. `UserDetailsService`.
    B. `CommandLineRunner`.
    C. `AuthenticationManager`.
    D. `SecurityFilterChain`.

### III. Servicios, DTOs, Mapeo, Validación y Cache (10 Preguntas)

26. La función principal del patrón **DTO (Data Transfer Object)** es:
    A. Realizar la inyección de dependencias en el servicio.
    B. Encapsular la lógica de negocio compleja.
    C. Transportar datos entre capas, evitando exponer las entidades de la base de datos.
    D. Manejar las excepciones personalizadas del dominio.

27. Las clases que se encargan de convertir un DTO a un modelo de aplicación y viceversa se conocen como:
    A. Repositorios.
    B. Mapeadores.
    C. Controladores.
    D. *Listeners* de Entidad.

28. En Spring, la clase que permite lanzar una excepción y que esta sea automáticamente convertida en un error HTTP con un código de estado adecuado es:
    A. `RuntimeException`.
    B. `ResponseStatusException`.
    C. `DataIntegrityViolationException`.
    D. `SecurityContextHolder`.

29. Si creas una excepción personalizada y quieres que devuelva un código de estado HTTP específico (ej. 404), debes usar la anotación:
    A. `@ExceptionHandler`.
    B. `@Autowired`.
    C. `@ResponseStatus`.
    D. `@Valid`.

30. ¿Qué anotación debe usar un parámetro en un método de controlador para que los datos serializados que recibe sean validados según las restricciones definidas en el DTO?
    A. `@NotNull`.
    B. `@NotBlank`.
    C. `@Valid`.
    D. `@RequestBody`.

31. ¿Qué restricción de Jakarta Bean Validation indica que un campo *String* no debe ser nulo y debe contener al menos un carácter que no sea un espacio en blanco?
    A. `@NotEmpty`.
    B. `@NotNull`.
    C. `@Size`.
    D. `@NotBlank`.

32. Si se desea que un campo de entidad represente una fecha, hora o instante en el pasado o en el presente, la restricción de validación adecuada es:
    A. `@Future`.
    B. `@Past`.
    C. `@PastOrPresent`.
    D. `@NegativeOrZero`.

33. Para habilitar la funcionalidad de *cache* en una aplicación Spring Boot, la clase principal debe estar anotada con:
    A. `@CachePut`.
    B. `@CacheEvict`.
    C. `@CacheConfig`.
    D. `@EnableCaching`.

34. ¿Qué anotación se utiliza en un método de servicio para asegurar que si ya se ejecutó, se devuelve el resultado de la caché, y si no, se ejecuta y se guarda el resultado?
    A. `@CacheEvict`.
    B. `@CachePut`.
    C. `@Cacheable`.
    D. `@Configuration`.

35. Si un método de servicio necesita **eliminar** el resultado de una caché específica, se utiliza la anotación:
    A. `@Cacheable`.
    B. `@CachePut`.
    C. `@CacheEvict`.
    D. `@EnableCaching`.

### IV. Testing en Spring Boot (10 Preguntas)

36. El objetivo principal de los **Tests Unitarios** es:
    A. Probar la integración de diferentes componentes del sistema.
    B. Probar una unidad de código (clase, método) de forma aislada.
    C. Simular las acciones de un usuario final (*End-to-End*).
    D. Verificar el correcto funcionamiento del servidor embebido.

37. Para aislar una unidad de código que se está probando de sus dependencias en tests unitarios, se suelen utilizar:
    A. Bases de datos en memoria.
    B. Objetos *Mocks*.
    C. La anotación `@SpringBootTest`.
    D. `TestEntityManager`.

38. Para realizar **Tests de Integración** cargando el contexto completo de la aplicación Spring Boot, se utiliza la anotación:
    A. `@MockBean`.
    B. `@SpringBootTest`.
    C. `@DataJpaTest`.
    D. `@ExtendWith`.

39. En Mockito, ¿qué anotación se utiliza en la clase que se está testeando para inyectar los objetos *mock* (anotados con `@Mock`) en ella?
    A. `@Autowired`.
    B. `@MockBean`.
    C. `@InjectMocks`.
    D. `@ExtendWith`.

40. Si en un test unitario de un servicio quieres simular el comportamiento de una dependencia (ej. un repositorio) sin ejecutar su código real, ¿qué método de Mockito defines para simular el resultado de esa dependencia?
    A. `verify`.
    B. `when`.
    C. `assertThrows`.
    D. `run`.

41. Para testear repositorios JPA sin cargar todo el contexto de la aplicación, utilizando un contexto de persistencia aislado, se usa la anotación:
    A. `@SpringBootTest`.
    B. `@AutoConfigureMockMvc`.
    C. `@DataJpaTest`.
    D. `@RestController`.

42. Para simular las peticiones HTTP y testear los controladores de forma sencilla, Spring Boot proporciona el objeto `MockMvc`. ¿Qué anotación se necesita usar en la clase de test para configurarlo?
    A. `@MockBean`.
    B. `@AutoConfigureMockMvc`.
    C. `@InjectMocks`.
    D. `@WithMockUser`.

43. Si estás testeando un controlador y quieres mockear sus dependencias (servicios o repositorios) para que el test sea solo unitario y no cargue la lógica de negocio real, se debe usar la anotación:
    A. `@Mock`.
    B. `@InjectMocks`.
    C. `@MockBean`.
    D. `@ExtendWith`.

44. ¿Qué anotación de Spring Security Test se utiliza para simular un usuario logueado en un test, siendo útil para probar la autorización en los controladores?
    A. `@AuthenticationPrincipal`.
    B. `@WithUserDetails`.
    C. `@WithMockUser`.
    D. `@EnableMethodSecurity`.

45. En JUnit 5, los métodos anotados con `@BeforeEach` y `@AfterEach` se utilizan para garantizar:
    A. Que los tests se ejecuten solo una vez.
    B. Que el contexto de Spring se inicialice correctamente.
    C. La independencia de cada test, inicializando y limpiando datos antes y después de cada ejecución.
    D. La carga de todos los *starters* de Spring Boot.

### V. Spring Data JPA y Persistencia (15 Preguntas)

46. Spring Data JPA proporciona una implementación de la API estándar de Java para el mapeo objeto-relacional (ORM) conocida como:
    A. Spring JDBC.
    B. Hibernate.
    C. Java Persistence API (JPA).
    D. Spring WebFlux.

47. ¿Qué valor de la propiedad `spring.jpa.hibernate.ddl-auto` se utiliza para **crear** las tablas al inicio de la aplicación y **borrarlas** cuando se cierra?
    A. `none`.
    B. `update`.
    C. `validate`.
    D. `create-drop`.

48. Para que una clase Java se mapee a una tabla en una base de datos relacional y se considere una entidad en JPA, debe estar anotada con:
    A. `@Table`.
    B. `@Column`.
    C. `@Entity`.
    D. `@Repository`.

49. En JPA, si utilizas un identificador **autoincremental** para una entidad, una de sus principales desventajas es:
    A. Son más difíciles de leer que los UUIDs.
    B. No puedes conocer su valor hasta que la entidad se inserta en la base de datos.
    C. No ayudan en la integridad referencial.
    D. La responsabilidad de generarlos recae en el desarrollador.

50. Los identificadores UUID son útiles en la lógica de negocio y en los tests unitarios porque:
    A. La base de datos siempre se encarga de su generación e índices.
    B. Nos permiten crear objetos con identificadores y relacionarlos sin necesidad de insertarlos en la base de datos.
    C. Son más eficientes y fáciles de indexar que los autoincrementales.
    D. Son fáciles de leer y escribir.

51. Para habilitar la auditoría JPA en toda la aplicación (seguimiento de fechas de creación/modificación) y que se aplique automáticamente a las entidades, se utiliza la anotación:
    A. `@EntityListeners`.
    B. `@EnableCaching`.
    C. `@EnableJpaAuditing`.
    D. `@Transactional`.

52. ¿Qué par de anotaciones se utiliza para definir una relación de **composición** (una entidad se embebe en otra y no necesita una tabla o identificador propio)?
    A. `@OneToOne` y `@Id`.
    B. `@Embedded` y `@Embeddable`.
    C. `@OneToMany` y `@ManyToOne`.
    D. `@JsonManagedReference` y `@JsonBackReference`.

53. ¿Qué estrategia de herencia en JPA mapea todas las clases de la jerarquía de herencia a **una sola tabla**, utilizando una columna de discriminador?
    A. `JOINED`.
    B. `SINGLE_TABLE`.
    C. `TABLE_PER_CLASS`.
    D. `UUID`.

54. La opción de cascada **`CascadeType.ALL`** se utiliza para:
    A. Propagar solo la acción de persistencia.
    B. Propagar solo la acción de eliminación.
    C. Propagar todas las acciones (persistir, actualizar, eliminar, etc.) a las entidades relacionadas.
    D. Propagar solo la acción de fusión (Merge).

55. La opción **`orphanRemoval = true`** en una relación JPA se utiliza para:
    A. Propagar todas las acciones de persistencia.
    B. Garantizar la eliminación física de la entidad.
    C. Eliminar automáticamente las entidades relacionadas que ya no están asociadas con la entidad padre.
    D. Prevenir la recursión infinita.

56. ¿Qué problema potencial surge al establecer una relación **bidireccional** entre dos entidades en Spring Data JPA si no se configura la serialización correctamente?
    A. Exceso de escalabilidad.
    B. El borrado lógico no funciona.
    C. Recursión infinita al serializar las entidades a JSON.
    D. Conflicto de identificadores autoincrementales.

57. Para evitar el problema de recursión infinita en relaciones bidireccionales al serializar a JSON, se puede usar la anotación de Jackson:
    A. `@Embedded`.
    B. `@ResponseStatus`.
    C. `@JsonIgnoreProperties`.
    D. `@GeneratedValue`.

58. La interfaz de repositorio que es la más básica y solo proporciona las operaciones CRUD fundamentales es:
    A. `JpaRepository`.
    B. `PagingAndSortingRepository`.
    C. `CrudRepository`.
    D. `QueryDslPredicateExecutor`.

59. JPQL (Java Persistence Query Language) es un lenguaje de consulta que opera en:
    A. Sentencias SQL nativas.
    B. Tablas y columnas de bases de datos.
    C. Objetos, atributos de objetos y métodos de objetos.
    D. El formato BSON de MongoDB.

60. Para utilizar SQL **nativo** en un método de repositorio, la anotación `@Query` debe especificar el atributo:
    A. `value`.
    B. `jpql` en `true`.
    C. `nativeQuery` en `true`.
    D. `withSql`.

### VI. Spring Data MongoDB y NoSQL (10 Preguntas)

61. Una de las principales ventajas de las bases de datos NoSQL (como MongoDB) sobre SQL es:
    A. Su consistencia estricta (ACID).
    B. Su mayor madurez tecnológica.
    C. La escalabilidad horizontal (expandirse añadiendo más servidores).
    D. El estándar de lenguaje coherente (SQL).

62. MongoDB almacena los datos en documentos utilizando qué formato, que es una representación binaria de JSON:
    A. XML.
    B. WSDL.
    C. BSON.
    D. HTML.

63. En el diseño de datos de MongoDB, la técnica de utilizar **documentos embebidos** puede mejorar el rendimiento al:
    A. Aumentar la necesidad de realizar operaciones de unión (*joins*).
    B. Imponer un esquema de datos fijo y estricto.
    C. Reducir la necesidad de realizar costosas operaciones de unión.
    D. Requerir el uso de claves foráneas.

64. En Spring Data MongoDB, para definir una clase como un documento que se mapea a una colección, se utiliza la anotación:
    A. `@Entity`.
    B. `@Repository`.
    C. `@Document`.
    D. `@Table`.

65. En MongoDB, la función `find().sort()` se utiliza para:
    A. Limitar el número de documentos devueltos.
    B. Eliminar documentos.
    C. Ordenar los documentos devueltos por una consulta.
    D. Contar el número de documentos que coinciden con el filtro.

66. Para **actualizar un documento existente o insertar uno nuevo si no existe** (operación *upsert*) en MongoDB, se utiliza la función:
    A. `insertMany()`.
    B. `updateMany()` con la opción `upsert: true`.
    C. `deleteOne()`.
    D. `aggregate()`.

67. La anotación `@DocumentReference` en Spring Data MongoDB es preferida sobre `@DBRef` porque permite:
    A. Almacenar el documento completo dentro del documento padre.
    B. Establecer referencias manuales de manera más eficiente, almacenando solo el ID del documento vinculado.
    C. Implementar la integridad referencial estricta.
    D. Cargar siempre los datos de la referencia de forma no perezosa (eager).

68. ¿Qué interfaz en Spring Data proporciona soporte para MongoDB, ofreciendo funcionalidad similar a `JpaRepository`?
    A. `JpaSpecificationExecutor`.
    B. `MongoRepository`.
    C. `CrudRepository`.
    D. `PagingAndSortingRepository`.

69. Para realizar pruebas unitarias rápidas de repositorios de MongoDB sin la necesidad de utilizar Docker, se puede emplear:
    A. `TestContainers`.
    B. `Flapdoodle` (MongoDB embebido).
    C. `@DataJpaTest`.
    D. La función `aggregate()`.

70. La función `bulkWrite()` en MongoDB se utiliza para:
    A. Realizar consultas de agregación.
    B. Realizar múltiples operaciones de escritura (inserciones, actualizaciones, eliminaciones) en una sola operación.
    C. Eliminar una colección completa.
    D. Reemplazar un documento existente.

### VII. WebSockets (5 Preguntas)

71. El protocolo WebSocket se diferencia de HTTP en que proporciona:
    A. Un modelo sin estado y *cacheable*.
    B. Un único *endpoint* para todas las solicitudes.
    C. Comunicación **bidireccional y en tiempo real**.
    D. Un modelo estricto de solicitud-respuesta (request-response).

72. Antes de la aparición de WebSockets, ¿qué técnicas se utilizaban para simular la comunicación en tiempo real en la Web?
    A. El protocolo SOAP.
    B. El borrado lógico.
    C. *Polling* o *long polling*.
    D. La negociación de contenido.

73. Una ventaja clave del uso de WebSockets sobre las técnicas tradicionales para el tiempo real es que:
    A. Reduce la necesidad de mantener una conexión persistente.
    B. Reduce la latencia, ya que la conexión se mantiene abierta.
    C. Consume menos recursos porque es un protocolo unidireccional.
    D. Es compatible con todos los navegadores y servidores.

74. Para usar suscripciones GraphQL que permiten recibir notificaciones en tiempo real (similar a WebSockets), es necesario configurar:
    A. Spring Data JPA.
    B. La librería de caché.
    C. WebFlux.
    D. El `AuthenticationManager`.

75. Una vez establecida la conexión WebSocket, ¿quién puede enviar mensajes?
    A. Solo el cliente al servidor.
    B. Solo el servidor al cliente (unidireccional).
    C. Tanto el cliente como el servidor, en cualquier momento.
    D. Solo si el cliente ha enviado primero una solicitud explícita.

### VIII. GraphQL (5 Preguntas)

76. ¿Qué característica fundamental de GraphQL permite a los clientes obtener exactamente lo que necesitan en una sola solicitud?
    A. Múltiples *endpoints*.
    B. Tipado débil.
    C. Consulta flexible, evitando el problema de sobre o sub-solicitud de datos (*overfetching/underfetching*).
    D. Se basa estrictamente en el protocolo HTTP GET.

77. En la sintaxis del esquema GraphQL, el elemento que define las **operaciones de escritura** que modifican los datos del servidor es:
    A. `Query`.
    B. `Subscription`.
    C. `Mutation`.
    D. `type`.

78. ¿Cuál es el principal contraste de GraphQL con REST respecto a la cantidad de *endpoints* que utiliza?
    A. GraphQL utiliza el protocolo TCP.
    B. GraphQL requiere múltiples *endpoints* para cada recurso.
    C. GraphQL utiliza un único *endpoint* (`/graphql`) para manejar todas las consultas.
    D. GraphQL no permite el uso de consultas anidadas.

79. En GraphQL, si se define un campo con un signo de exclamación al final (ejemplo: `stock: Int!`), esto indica que el campo:
    A. Es un tipo escalar opcional.
    B. Es obligatorio y no puede ser nulo.
    C. Es una lista de elementos.
    D. Solo puede usarse en consultas (Query).

80. ¿Qué ventaja de GraphQL permite al cliente explorar la estructura de la API y autocompletar consultas, por ejemplo, en un *playground*?
    A. Mutaciones.
    B. Tipado fuerte.
    C. Evolución sin versionado.
    D. Introspección.

### IX. Temas Avanzados y Utilities (5 Preguntas)

81. ¿Qué información adicional contiene un objeto `Page` de Spring Data (resultado de una operación de paginación) además de la lista de entidades?
    A. La URL del servidor.
    B. El número total de páginas y el número total de entidades.
    C. El token JWT del usuario.
    D. La estrategia de herencia JPA utilizada.

82. Incluir enlaces de paginación (ej. Next, Prev) en el **encabezado** de la respuesta HTTP, en lugar del cuerpo, es una práctica que se ajusta al estándar REST conocido como:
    A. DTO (Data Transfer Object).
    B. CORS (Cross-Origin Resource Sharing).
    C. HATEOAS (Hypermedia as the Engine of Application State).
    D. OpenAPI Specification.

83. Para implementar criterios de búsqueda dinámicos en Spring Data JPA utilizando `Specification`, tu repositorio debe extender de `JpaRepository` y también de la interfaz:
    A. `CrudRepository`.
    B. `PagingAndSortingRepository`.
    C. `JpaSpecificationExecutor`.
    D. `MongoRepository`.

84. CORS (Cross-Origin Resource Sharing) es un mecanismo de seguridad implementado en los navegadores web que permite:
    A. Cifrar la comunicación entre cliente y servidor (SSL/TSL).
    B. Impedir los ataques de falsificación de solicitudes (CSRF).
    C. Controlar a qué dominios o recursos externos se les permite acceder a los recursos del servidor.
    D. Autenticar usuarios con tokens JWT.

85. Para construir imágenes de Spring Boot con múltiples etapas, primero se usa una imagen base (ej. Maven) para la compilación, y luego se copia el archivo JAR a una nueva imagen base más ligera (ej. OpenJDK), para:
    A. Evitar los tests durante la compilación.
    B. Reducir el tamaño final de la imagen Docker.
    C. Usar perfiles de desarrollo en producción.
    D. Simular el contexto de persistencia.

### X. Spring Security y JWT (15 Preguntas)

86. Spring Security proporciona una capa de seguridad a nivel de aplicación que se centra en dos aspectos críticos:
    A. Cache y Auditoría.
    B. Paginación y Ordenamiento.
    C. Autenticación y Autorización.
    D. JSON y XML.

87. El proceso de **Autenticación** en Spring Security implica:
    A. Controlar el acceso a diferentes recursos en función de roles.
    B. Verificar las credenciales del usuario (ej. nombre de usuario y contraseña).
    C. Proteger contra ataques XSS y CSRF.
    D. Generar el token JWT.

88. ¿Qué interfaz en Spring Security se utiliza específicamente para **cargar los detalles de un usuario** (incluyendo roles y permisos) a partir de un origen de datos?
    A. `AuthenticationManager`.
    B. `PasswordEncoder`.
    C. `UserDetailsService`.
    D. `SecurityContextHolder`.

89. El token JWT (JSON Web Token) consta de tres partes separadas por un punto (.): el encabezado, el cuerpo (payload) y la:
    A. URL.
    B. Algoritmo de cifrado.
    C. Firma (Signature).
    D. Clave pública.

90. ¿Qué componente en Spring Security es responsable de tomar un objeto de autenticación (ej. `UsernamePasswordAuthenticationToken`) y **validar** las credenciales para determinar si el usuario es válido?
    A. `UserDetailsService`.
    B. `AuthenticationManager`.
    C. `SecurityContextHolder`.
    D. `JwtService`.

91. Para crear un filtro de seguridad personalizado y asegurar que sea invocado **solo una vez por cada petición** HTTP, se debe extender la clase:
    A. `JwtAuthenticationFilter`.
    B. `UsernamePasswordAuthenticationFilter`.
    C. `OncePerRequestFilter`.
    D. `SecurityFilterChain`.

92. `PasswordEncoder` es una interfaz que se utiliza para codificar y descifrar contraseñas. ¿Cuál es la implementación hash recomendada y utilizada por Spring Security?
    A. SHA-256.
    B. MD5.
    C. BCrypt.
    D. AES-256.

93. ¿Qué componente de Spring Security se utiliza para almacenar los detalles del usuario autenticado y es usado por el sistema de autorización para tomar decisiones de acceso?
    A. `AuthenticationManager`.
    B. `SecurityContextHolder`.
    C. `UserDetails`.
    D. `AuthenticationProvider`.

94. El `DaoAuthenticationProvider` utiliza una instancia de `UserDetailsService` y una implementación de `PasswordEncoder` para:
    A. Cargar la configuración del servidor web.
    B. Autenticar a un usuario en la aplicación.
    C. Definir la cadena de filtros de seguridad.
    D. Gestionar las transacciones de la base de datos.

95. Si quieres proteger el acceso a un método de un controlador basándote en un rol específico (ej. solo administradores), la anotación que se debe usar en el método del controlador es:
    A. `@WithMockUser`.
    B. `@AuthenticationPrincipal`.
    C. `@PreAuthorize("hasRole('ROLE_ADMIN')")`.
    D. `@RestController`.

96. En el contexto de los filtros de seguridad, el `JwtAuthenticationFilter` debe añadirse **antes** del `UsernamePasswordAuthenticationFilter` porque:
    A. Es necesario para que el `AuthenticationManager` funcione.
    B. El `JwtAuthenticationFilter` extrae la información del usuario del token antes de que el otro filtro intente procesar las credenciales estándar de la solicitud.
    C. `UsernamePasswordAuthenticationFilter` es el filtro más genérico.
    D. `JwtAuthenticationFilter` es el único filtro que extiende `OncePerRequestFilter`.

97. Para obtener la información del usuario que está logueado en un momento dado en un método de controlador (basado en el token JWT), se utiliza la anotación:
    A. `@AuthenticationProvider`.
    B. `@AuthenticationPrincipal`.
    C. `@WithUserDetails`.
    D. `@PreAuthorize`.

98. La propiedad `spring.profiles.active={nombre_perfil}` en el archivo `application.properties` se utiliza para:
    A. Definir la versión de la API.
    B. Activar un perfil de Spring Boot específico (ej. `dev` o `prod`).
    C. Configurar la auditoría JPA.
    D. Habilitar la negociación de contenido.

99. El `AuthenticationManager` se utiliza en conjunto con los proveedores de autenticación (ej. `DaoAuthenticationProvider`) que son responsables de:
    A. Proporcionar un *bean* Singleton.
    B. Almacenar y recuperar los detalles de usuario.
    C. Definir las operaciones CRUD.
    D. Activar el servidor embebido.

100. La opción de configuración `spring.security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)` se usa en la configuración de seguridad cuando se trabaja con JWT para:
    A. Almacenar el estado de autenticación de forma segura.
    B. Indicar que el servidor no debe almacenar el estado de autenticación.
    C. Permitir la comunicación bidireccional.
    D. Configurar el puerto del servidor.