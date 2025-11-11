- [Módulos y Fundamentos de Spring (Preguntas 1-15)](#módulos-y-fundamentos-de-spring-preguntas-1-15)
- [Spring Boot y Arquitectura Web (Preguntas 16-30)](#spring-boot-y-arquitectura-web-preguntas-16-30)
- [Procesamiento de Datos, Servicios y Validación (Preguntas 31-45)](#procesamiento-de-datos-servicios-y-validación-preguntas-31-45)
- [Spring Data JPA y Persistencia (Preguntas 46-75)](#spring-data-jpa-y-persistencia-preguntas-46-75)
- [Testing (Preguntas 76-85)](#testing-preguntas-76-85)
- [Almacenamiento de Ficheros y Recursos (Preguntas 86-90)](#almacenamiento-de-ficheros-y-recursos-preguntas-86-90)
- [Comunicación Avanzada: WebSockets y Sockets (Preguntas 91-95)](#comunicación-avanzada-websockets-y-sockets-preguntas-91-95)
- [Resultados Avanzados, Paginación y Criterios (Preguntas 96-105)](#resultados-avanzados-paginación-y-criterios-preguntas-96-105)
- [Protocolo HTTP y JWT (Preguntas 106-115)](#protocolo-http-y-jwt-preguntas-106-115)
- [Spring Security (Preguntas 116-130)](#spring-security-preguntas-116-130)
- [NoSQL, MongoDB y GraphQL (Preguntas 131-140)](#nosql-mongodb-y-graphql-preguntas-131-140)
- [Redis, Email y Despliegue (Preguntas 141-150)](#redis-email-y-despliegue-preguntas-141-150)


### Módulos y Fundamentos de Spring (Preguntas 1-15)

1.  ¿Cuál de las siguientes características *no* es mencionada como un beneficio de Spring Framework?
    a) Programación Orientada a Aspectos (AOP).
    b) Soporte para pruebas unitarias y de integración.
    c) Gestión directa de hilos del sistema operativo.
    d) Inversión de Control (IoC).

2.  ¿Qué principio de diseño de software invierte el control del flujo de la aplicación, dejando que el *framework* se encargue de la creación y gestión de objetos?
    a) Dependency Injection (DI).
    b) Programación Orientada a Aspectos (AOP).
    c) Inversión de Control (IoC).
    d) Modelo-Vista-Controlador (MVC).

3.  En Spring, ¿cuál es el término utilizado para referirse a los objetos que son instanciados, ensamblados y administrados por el contenedor Spring?
    a) Starters.
    b) Beans.
    c) Aspectos.
    d) Controladores.

4.  ¿Cuál es un módulo de Spring Framework que proporciona la implementación fundamental de la Inversión de Control (IoC) y la Inyección de Dependencias (DI)?
    a) Spring AOP.
    b) Spring ORM.
    c) Spring Core.
    d) Spring WebFlux.

5.  Spring Boot simplifica la configuración y ejecución de aplicaciones Spring. ¿Qué característica permite a Spring Boot configurar automáticamente una aplicación basada en las dependencias añadidas?
    a) Standalone.
    b) Opinión predefinida.
    c) Autoconfiguración.
    d) Actuator.

6.  ¿Cuál de los siguientes módulos de Spring proporciona un marco para el desarrollo de aplicaciones web y RESTful utilizando el patrón Modelo-Vista-Controlador?
    a) Spring DAO.
    b) Spring WebFlux.
    c) Spring Web MVC.
    d) Spring Batch.

7.  La Inyección de Dependencias (DI) en Spring se puede lograr a través de:
    a) Constructores.
    b) Métodos setter.
    c) Campos directamente.
    d) Todas las anteriores.

8.  ¿Qué proyecto del ecosistema Spring proporciona herramientas para el desarrollo de aplicaciones en la nube, incluyendo configuración centralizada y descubrimiento de servicios?
    a) Spring Data.
    b) Spring Security.
    c) Spring Cloud.
    d) Spring Batch.

9.  ¿Qué anotación se usa típicamente para inyectar una dependencia en Spring?
    a) `@Service`.
    b) `@Configuration`.
    c) `@Autowired`.
    d) `@Component`.

10. ¿Cuál es el propósito principal de Spring AOP (Programación Orientada a Aspectos)?
    a) Facilitar la integración con tecnologías ORM.
    b) Proporcionar un marco para aplicaciones reactivas.
    c) Aplicar funcionalidades transversales de manera declarativa (ej., registro, seguridad).
    d) Implementar la persistencia de datos de forma sencilla.

11. ¿Qué módulo del Spring Framework está diseñado para la construcción de aplicaciones reactivas y permite aplicaciones no bloqueantes?
    a) Spring Web MVC.
    b) Spring WebFlux.
    c) Spring Core.
    d) Spring JDBC.

12. Spring Data simplifica la persistencia de datos y proporciona soporte para diversas tecnologías de base de datos, incluyendo:
    a) JPA.
    b) MongoDB.
    c) Redis.
    d) Todas las anteriores.

13. ¿Qué herramienta de Spring Boot proporciona funcionalidades de producción listas para usar, como la monitorización y la gestión de la aplicación?
    a) Starters.
    b) Actuator.
    c) Spring Core.
    d) Spring Cloud.

14. En Spring, ¿cómo se puede configurar la creación de Beans en aplicaciones modernas?
    a) Usando archivos de configuración XML.
    b) Usando anotaciones como `@Service` o `@Controller`.
    c) Usando clases de configuración Java con `@Configuration` y `@Bean`.
    d) b y c.

15. ¿Qué proyecto de Spring proporciona funciones robustas para el procesamiento por lotes, incluyendo servicios de transacción y tareas programadas?
    a) Spring Cloud.
    b) Spring Batch.
    c) Spring Integration.
    d) Spring Security.

### Spring Boot y Arquitectura Web (Preguntas 16-30)

16. ¿Qué anotación de Spring Boot es una combinación de `@Configuration`, `@EnableAutoConfiguration` y `@ComponentScan`, marcando la clase como la clase principal de configuración y habilitando la configuración automática?
    a) `@Controller`.
    b) `@SpringBootApplication`.
    c) `@Configuration`.
    d) `@RestController`.

17. ¿Qué es un "starter" en Spring Boot?
    a) Una herramienta para monitorizar la aplicación en producción.
    b) Un conjunto de dependencias preconfiguradas que simplifican la adición de funcionalidades.
    c) El método principal (`main`) de la aplicación.
    d) Un componente que implementa la lógica de negocio.

18. En el patrón Modelo-Vista-Controlador (MVC) implementado por Spring Web MVC, ¿qué componente es responsable de renderizar el modelo en una forma que el usuario pueda entender, como HTML?
    a) El Modelo.
    b) El Controlador.
    c) La Vista.
    d) El Repositorio.

19. ¿Cuál es el fichero de configuración predeterminado que Spring Boot lee al iniciar la aplicación para establecer propiedades como el puerto de ejecución?
    a) `pom.xml`.
    b) `build.gradle.kts`.
    c) `application.properties`.
    d) `schema.sql`.

20. En Spring Boot, ¿qué anotación se utiliza para etiquetar una clase que se encarga de recibir las peticiones de los usuarios y devolver respuestas, devolviendo directamente datos serializados (como JSON) en lugar de una vista?
    a) `@Controller`.
    b) `@Service`.
    c) `@Repository`.
    d) `@RestController`.

21. El módulo Spring Web MVC proporciona soporte para la validación de los datos del modelo utilizando:
    a) La API de Validación de Bean de Java.
    b) El patrón Strategy.
    c) El módulo Spring AOP.
    d) El Actuator.

22. Si deseas que solo se cree una instancia de un componente en el contexto de la aplicación, compartida por todos los hilos y solicitudes, ¿qué valor de la anotación `@Scope` debes usar?
    a) Request.
    b) Prototype.
    c) Singleton.
    d) Session.

23. ¿Qué anotación debe usar un componente si su misión principal es implementar la interfaz y operaciones de persistencia de la información (acceso a base de datos o API externa)?
    a) `@Service`.
    b) `@Repository`.
    c) `@Configuration`.
    d) `@Controller`.

24. ¿Qué interfaz de Spring Boot te permite ejecutar código después de que el contexto de la aplicación se haya cargado y antes de que la aplicación se inicie completamente?
    a) `ApplicationRunner`.
    b) `CommandLineRunner`.
    c) `ContextLoaderListener`.
    d) `InitializingBean`.

25. Para obtener el código de estado HTTP de la respuesta, así como el cuerpo de la misma, en un controlador REST, se recomienda usar la clase:
    a) `HttpStatus`.
    b) `ResponseBody`.
    c) `ResponseEntity`.
    d) `HttpServletResponse`.

26. ¿Qué anotación se utiliza en un método de controlador para obtener información de la petición incluida en la propia ruta (ej. `/funkos/{id}`)?
    a) `@RequestParam`.
    b) `@RequestBody`.
    c) `@PathVariable`.
    d) `@Mapping`.

27. Si se desea enviar datos serializados en el cuerpo de una petición POST o PUT a un controlador REST, ¿qué anotación se debe usar en el parámetro del método del controlador?
    a) `@RequestParam`.
    b) `@RequestBody`.
    c) `@PathVariable`.
    d) `@Header`.

28. ¿Qué verbo HTTP se utiliza para la actualización parcial de un recurso en un servidor?
    a) POST.
    b) PUT.
    c) DELETE.
    d) PATCH.

29. ¿Qué código de respuesta HTTP indica que la solicitud ha tenido éxito y se ha creado un nuevo recurso?
    a) 200 OK.
    b) 204 No Content.
    c) 404 Not Found.
    d) 201 Created.

30. ¿Qué anotación se utiliza para definir beans en clases de configuración Java (etiquetadas con `@Configuration`)?
    a) `@Component`.
    b) `@Autowired`.
    c) `@Bean`.
    d) `@Value`.

### Procesamiento de Datos, Servicios y Validación (Preguntas 31-45)

31. ¿Cuál es la principal ventaja de usar la declaración `try-with-resources` al leer o escribir archivos en Java?
    a) Permite el uso de la API de Streams.
    b) Se asegura de que cada recurso abierto se cierre automáticamente.
    c) Define el formato de intercambio de datos.
    d) Solo funciona con archivos JSON.

32. ¿Qué formato de intercambio de datos utiliza un delimitador (generalmente una coma o punto y coma) para separar campos, representando datos tabulares?
    a) JSON.
    b) XML.
    c) CSV.
    d) BSON.

33. ¿Cuál es el propósito principal del Patrón DTO (Data Transfer Object)?
    a) Encapsular la lógica de negocio en el servicio.
    b) Exponer las entidades de la base de datos directamente al cliente.
    c) Transportar datos entre capas, evitando la exposición de entidades o modelos internos.
    d) Administrar el ciclo de vida de los beans en Spring.

34. Si en un servicio se lanza una excepción personalizada que hereda de `RuntimeException`, ¿qué anotación se puede añadir a esa clase de excepción para que Spring la convierta en un error HTTP con un código de estado específico?
    a) `@ResponseStatus`.
    b) `@RestControllerAdvice`.
    c) `@Controller`.
    d) `@Service`.

35. ¿Qué anotación debe añadirse a la clase principal de una aplicación Spring Boot para habilitar el uso de caché a través de `@Cacheable`, `@CachePut`, y `@CacheEvict`?
    a) `@EnableAutoConfiguration`.
    b) `@EnableCaching`.
    c) `@CacheConfig`.
    d) `@Service`.

36. La anotación `@CachePut` se utiliza para:
    a) Devolver el resultado de la caché si el método ya ha sido ejecutado.
    b) Eliminar el resultado de la caché después de la ejecución del método.
    c) Ejecutar el método y guardar (o sobrescribir) el nuevo resultado en la caché.
    d) Configurar el nombre de la caché.

37. ¿Qué librería de Java/Jakarta se utiliza para la validación de los datos del modelo o DTOs, permitiendo usar anotaciones como `@NotNull` y `@Size`?
    a) Spring Security.
    b) Jackson/GSON.
    c) Jakarta Bean Validation.
    d) Spring WebFlux.

38. Para que Spring valide automáticamente los datos de un DTO que llega como cuerpo de una petición, ¿qué anotación debe usarse en el parámetro del método del controlador?
    a) `@RequestBody`.
    b) `@Valid`.
    c) `@NotNull`.
    d) `@Pattern`.

39. Si un campo en un DTO debe ser un número estrictamente positivo (donde 0 es un valor inválido), ¿qué anotación de Jakarta Bean Validation se debe usar?
    a) `@PositiveOrZero`.
    b) `@Min(1)`.
    c) `@Positive`.
    d) `@Max`.

40. ¿Qué anotación se utiliza en un controlador, junto con el código de error `@ResponseStatus(HttpStatus.BAD_REQUEST)`, para capturar excepciones específicas lanzadas por el validador?
    a) `@ExceptionHandler`.
    b) `@Service`.
    c) `@ControllerAdvice`.
    d) `@ErrorMapping`.

41. ¿Cuál es la función de los mapeadores (Mappers) en una arquitectura de capas que utiliza DTOs?
    a) Almacenar datos en la base de datos.
    b) Convertir un objeto DTO a un modelo de aplicación y viceversa.
    c) Realizar la lógica de negocio.
    d) Configurar la seguridad de la aplicación.

42. ¿Qué anotación de validación garantiza que un elemento anotado no sea nulo ni esté vacío (especialmente útil para colecciones o Strings)?
    a) `@NotNull`.
    b) `@NotEmpty`.
    c) `@NotBlank`.
    d) `@Size(min=1)`.

43. Desde la versión 5 de Spring, ¿qué clase se puede lanzar en un servicio para que Spring la convierta en un error HTTP con el código de estado adecuado?
    a) `RuntimeException`.
    b) `ResponseStatusException`.
    c) `IOException`.
    d) `ErrorServiceException`.

44. ¿Cuál es la principal diferencia funcional entre `@Cacheable` y `@CacheEvict`?
    a) `@Cacheable` ejecuta el método primero y luego almacena; `@CacheEvict` devuelve el resultado de la caché.
    b) `@Cacheable` almacena el resultado si no está en caché; `@CacheEvict` elimina la entrada de la caché.
    c) Ambos actualizan la caché, pero `@CacheEvict` usa una clave diferente.
    d) `@CacheEvict` es solo para errores; `@Cacheable` es para lecturas.

45. ¿Qué anotación de validación garantiza que un elemento anotado no sea nulo y debe contener al menos un carácter que no sea un espacio en blanco?
    a) `@Null`.
    b) `@NotEmpty`.
    c) `@NotBlank`.
    d) `@Size(min=1)`.

### Spring Data JPA y Persistencia (Preguntas 46-75)

46. Spring Data JPA se superpone a qué especificación estándar de Java para el mapeo objeto-relacional (ORM)?
    a) JDBC.
    b) Hibernate.
    c) JPA (Java Persistence API).
    d) Spring Core.

47. ¿Cuál es el valor de la propiedad `spring.jpa.hibernate.ddl-auto` que crea las tablas al inicio de la aplicación y las borra cuando se cierra?
    a) `none`.
    b) `update`.
    c) `create`.
    d) `create-drop`.

48. Para definir una clase Java como una entidad mapeada a una tabla en una base de datos relacional, ¿qué anotación de JPA es obligatoria?
    a) `@Table`.
    b) `@Id`.
    c) `@Entity`.
    d) `@Column`.

49. ¿Qué archivo en el directorio `src/main/resources` Spring Boot puede cargar automáticamente para pre-poblar la base de datos con comandos SQL durante la inicialización de la aplicación?
    a) `application.properties`.
    b) `schema.sql`.
    c) `data.sql`.
    d) `log4j.properties`.

50. ¿Cuál de las siguientes anotaciones se utiliza para especificar cómo se debe generar automáticamente el identificador de una entidad en JPA?
    a) `@Id`.
    b) `@Column`.
    c) `@Table`.
    d) `@GeneratedValue`.

51. En el contexto de identificadores en JPA, ¿cuál de los siguientes es generado utilizando un algoritmo de hash, es más seguro y es perfecto para bases de datos NoSQL, pero potencialmente más difícil de indexar?
    a) Autoincremental.
    b) IDENTITY.
    c) UUID.
    d) SEQUENCE.

52. ¿Cuál es la principal ventaja de usar UUIDs como identificadores, en lugar de autoincrementales, en términos de lógica de negocio y testing?
    a) Son más eficientes en bases de datos relacionales.
    b) La base de datos se encarga de su generación.
    c) Permiten crear objetos relacionados entre sí sin necesidad de insertarlos previamente en la base de datos.
    d) Son más fáciles de leer y escribir.

53. ¿Qué anotación de Spring Data JPA se utiliza en una clase de configuración para habilitar la auditoría JPA en toda la aplicación (ej., para el manejo de `@CreatedDate` y `@LastModifiedDate`)?
    a) `@EnableJpaRepositories`.
    b) `@EntityListeners`.
    c) `@EnableJpaAuditing`.
    d) `@Configuration`.

54. ¿Qué opción de cascada propaga *todas* las acciones (persistir, actualizar, eliminar, etc.) desde una entidad padre a una entidad relacionada?
    a) `CascadeType.MERGE`.
    b) `CascadeType.PERSIST`.
    c) `CascadeType.REMOVE`.
    d) `CascadeType.ALL`.

55. ¿Qué opción de relación en JPA se establece cuando una entidad se asocia con exactamente otra entidad (ej., Persona y Dirección)?
    a) `@OneToMany`.
    b) `@ManyToMany`.
    c) `@ManyToOne`.
    d) `@OneToOne`.

56. ¿Qué anotación de JPA se utiliza en la entidad hija (`Dirección`) para indicar que se va a embeber en otra entidad (`Persona`) y no necesita una tabla propia ni un identificador propio?
    a) `@Entity`.
    b) `@Embedded`.
    c) `@Embeddable`.
    d) `@Table`.

57. ¿Cuál es la estrategia de herencia en JPA donde todas las clases de la jerarquía se mapean a una sola tabla, utilizando una columna de discriminador?
    a) JOINED.
    b) TABLE_PER_CLASS.
    c) SINGLE_TABLE.
    d) MAPPED_SUPERCLASS.

58. En una relación `@OneToMany`, ¿qué opción, al establecerse a `true`, permite eliminar automáticamente las entidades relacionadas (hijas) que ya no están asociadas con la entidad padre?
    a) `CascadeType.REMOVE`.
    b) `orphanRemoval`.
    c) `fetch`.
    d) `mappedBy`.

59. En el contexto de un borrado lógico, ¿cuál es la principal ventaja sobre el borrado físico?
    a) Ahorro de espacio de almacenamiento inmediato.
    b) Mejora el rendimiento de consultas.
    c) Permite la recuperación de datos eliminados y facilita la auditoría.
    d) Cumplimiento de normativas que exigen eliminación total de datos.

60. ¿Qué anotación de Jackson se recomienda usar en el lado de la relación bidireccional que no se desea serializar para evitar el problema de la recursión infinita en JSON?
    a) `@JsonManagedReference`.
    b) `@JsonIdentityInfo`.
    c) `@JsonIgnoreProperties`.
    d) `@DBRef`.

61. Para modificar datos en una consulta JPQL o SQL nativa definida en un repositorio, ¿qué anotación es necesaria en la definición de la consulta?
    a) `@Transactional`.
    b) `@Param`.
    c) `@Modifying`.
    d) `@Query`.

62. ¿Qué anotación de Spring Data JPA se utiliza para definir consultas personalizadas utilizando JPQL o SQL nativo en un método de repositorio?
    a) `@Procedure`.
    b) `@Execute`.
    c) `@Query`.
    d) `@Select`.

63. Si deseas usar SQL nativo en una consulta personalizada de un repositorio JPA, ¿qué atributo de la anotación `@Query` debe establecerse a `true`?
    a) `value`.
    b) `namedQuery`.
    c) `nativeQuery`.
    d) `queryLanguage`.

64. ¿Qué tipo de repositorio básico de Spring Data proporciona las operaciones CRUD fundamentales (guardar, eliminar, actualizar, buscar)?
    a) `JpaRepository`.
    b) `PagingAndSortingRepository`.
    c) `CrudRepository`.
    d) `QueryDslPredicateExecutor`.

65. ¿Qué tipo de repositorio extiende a `CrudRepository` y agrega funcionalidades específicas para JPA, además de soportar paginación y ordenamiento?
    a) `MongoRepository`.
    b) `PagingAndSortingRepository`.
    c) `JpaRepository`.
    d) `RedisRepository`.

66. Para probar repositorios JPA de forma aislada, Spring Boot proporciona la anotación `@DataJpaTest` y una instancia especializada para la persistencia llamada:
    a) `TestEntityManager`.
    b) `MockMvc`.
    c) `JdbcTemplate`.
    d) `TestContainer`.

67. En una relación bidireccional entre dos entidades, ¿qué entidad es la "propietaria" de la relación, utilizada por Hibernate para determinar el estado de la relación en la base de datos?
    a) Ambas entidades son propietarias por igual.
    b) La entidad marcada con `@JsonManagedReference`.
    c) Es crucial configurarlo correctamente para evitar actualizaciones inesperadas.
    d) Solo la entidad que es referenciada por un UUID.

68. ¿Qué estrategia de generación de identificador en JPA delega la responsabilidad de generar el ID a una columna de identidad en la base de datos (común en MySQL)?
    a) AUTO.
    b) SEQUENCE.
    c) TABLE.
    d) IDENTITY.

69. ¿Qué mecanismo utiliza TestContainers para lanzar instancias reales de bases de datos (como MySQL o MongoDB) durante las pruebas de integración?
    a) H2 en memoria.
    b) Mockito.
    c) Docker.
    d) Flapdoodle.

70. ¿Qué significa JPQL?
    a) Java Persistent Query Loader.
    b) JSON Persistence Query Language.
    c) Java Persistence Query Language.
    d) Java Private Query Listener.

71. En el contexto de prueba de repositorios, ¿cuál es la principal limitación de Flapdoodle (MongoDB embebido)?
    a) Es muy lento y requiere Docker.
    b) No replica exactamente el comportamiento de un MongoDB real (ej., transacciones multi-document).
    c) Solo funciona con bases de datos SQL.
    d) Requiere configuración manual compleja.

72. ¿Cuál es el problema principal que surge al serializar a JSON entidades que tienen relaciones bidireccionales en Spring Data JPA?
    a) Pérdida de datos.
    b) Error de conexión a la base de datos.
    c) Recursión infinita.
    d) Fallo de autenticación.

73. En las anotaciones de auditoría de JPA, ¿qué anotación de JPA se utiliza para especificar los listeners de eventos de ciclo de vida de una entidad, como pre-persist o post-update?
    a) `@EnableJpaAuditing`.
    b) `@EntityListeners`.
    c) `@AuditingEntityListener`.
    d) `@CreatedDate`.

74. ¿Qué tipo de estrategia de herencia en JPA mapea cada clase en la jerarquía a su propia tabla, uniéndolas mediante claves foráneas?
    a) SINGLE_TABLE.
    b) TABLE_PER_CLASS.
    c) JOINED.
    d) MAPPED_SUPERCLASS.

75. ¿Qué propiedad se configura en `application.properties` para controlar si se deben mostrar las consultas SQL generadas por Hibernate en la consola?
    a) `spring.datasource.url`.
    b) `spring.jpa.hibernate.ddl-auto`.
    c) `spring.jpa.show-sql`.
    d) `spring.jpa.properties.hibernate.dialect`.

### Testing (Preguntas 76-85)

76. ¿Qué framework de testeo se utiliza en Java para la realización de test unitarios, proporcionando anotaciones para definir y configurar las pruebas?
    a) Mockito.
    b) TestContainers.
    c) JUnit.
    d) Spring Actuator.

77. En JUnit, ¿qué anotación se utiliza para marcar un método que se ejecutará antes de cada test unitario para inicializar las condiciones (Setup)?
    a) `@Test`.
    b) `@BeforeAll`.
    c) `@AfterEach`.
    d) `@BeforeEach`.

78. ¿Qué tipo de "doble de prueba" (test double) en Mockito puede preprogramarse para devolver ciertos valores o lanzar excepciones, y puede registrar cómo se le llama?
    a) Stub.
    b) Spy.
    c) Fake.
    d) Mock.

79. En Mockito, ¿qué anotación se utiliza para crear un sustituto simulado de un componente (ej., un repositorio) para aislar la clase que se está probando?
    a) `@InjectMocks`.
    b) `@Autowired`.
    c) `@Mock`.
    d) `@Test`.

80. Si estás utilizando Mockito para probar un servicio (`MyService`) que tiene un repositorio (`MyRepository`), ¿qué anotación debes usar en `MyService` para inyectar los mocks de sus dependencias?
    a) `@Mock`.
    b) `@InjectMocks`.
    c) `@Autowired`.
    d) `@Service`.

81. ¿Qué aserción de JUnit verifica que un valor esperado y un valor real son iguales?
    a) `assertTrue()`.
    b) `assertNull()`.
    c) `assertNotEquals()`.
    d) `assertEquals()`.

82. ¿Cuál es la principal ventaja de utilizar un *Test Double* (Doble de Prueba) en el testing unitario?
    a) Acelerar el proceso de compilación.
    b) Evitar la necesidad de escribir aserciones.
    c) Sustituir componentes reales que son difíciles de integrar o para simular comportamientos.
    d) Integrar múltiples módulos de la aplicación.

83. En Spring Security testing, ¿qué anotación se utiliza para simular un usuario logueado en el sistema con roles y permisos específicos?
    a) `@WithAnonymousUser`.
    b) `@WithMockUser`.
    c) `@PreAuthorize`.
    d) `@AuthenticationPrincipal`.

84. Cuando se utiliza `@DataJpaTest`, ¿en qué contexto aislado opera el `TestEntityManager`?
    a) En el contexto de producción, afectando la base de datos real.
    b) En un contexto de prueba, sin afectar a la base de datos persistente utilizada por la aplicación.
    c) Solo en el contexto de Spring Security.
    d) En el contenedor Docker de la base de datos.

85. ¿Qué método de Mockito se utiliza para especificar el comportamiento de un mock (es decir, qué valor debe devolver cuando se le llama)?
    a) `verify()`.
    b) `doNothing()`.
    c) `when()`.
    d) `assert()`.

### Almacenamiento de Ficheros y Recursos (Preguntas 86-90)

86. Para que un controlador pueda recibir un fichero enviado en el cuerpo de una petición, se debe usar el tipo de contenido:
    a) `MediaType.APPLICATION_JSON_VALUE`.
    b) `MediaType.APPLICATION_XML_VALUE`.
    c) `MediaType.MULTIPART_FORM_DATA_VALUE`.
    d) `MediaType.TEXT_PLAIN_VALUE`.

87. ¿Qué anotación se utiliza en un parámetro del método de un controlador para que Spring sea capaz de inyectar el fichero subido?
    a) `@RequestParam("file")`.
    b) `@RequestBody`.
    c) `@RequestPart("file")`.
    d) `@PathVariable`.

88. Si se necesita ejecutar código de inicialización del sistema de almacenamiento al comienzo de la aplicación, ¿qué anotación, que es un estándar de Java, se puede usar en un método de un bean para indicar que debe ejecutarse después de que el bean haya sido construido y las dependencias inyectadas?
    a) `@Configuration`.
    b) `@Bean`.
    c) `@PostConstruct`.
    d) `@PreDestroy`.

89. ¿Qué tipo de objeto devuelve el método `loadAsResource` de un servicio de almacenamiento, que permite acceder a las propiedades de un fichero?
    a) `File`.
    b) `String`.
    c) `Resource`.
    d) `Path`.

90. ¿Qué anotación se utiliza para marcar una clase que contiene elementos de configuración y que se carga antes que el resto de componentes por el sistema de Inversión de Control?
    a) `@Service`.
    b) `@Repository`.
    c) `@Configuration`.
    d) `@Controller`.

### Comunicación Avanzada: WebSockets y Sockets (Preguntas 91-95)

91. ¿Qué protocolo de comunicación bidireccional en tiempo real establece una conexión persistente entre un cliente y un servidor a través de TCP, a diferencia del modelo solicitud-respuesta de HTTP?
    a) FTP.
    b) WebSockets.
    c) SOAP.
    d) Long Polling.

92. ¿Cuál es el principal beneficio de usar WebSockets en aplicaciones web?
    a) Reduce la carga inicial de la página web.
    b) Habilita la comunicación en tiempo real y bidireccional.
    c) Reemplaza completamente el uso de APIs REST.
    d) Evita la necesidad de usar SSL/TSL.

93. Para implementar el envío de notificaciones a clientes en tiempo real usando WebSockets, ¿qué patrón de diseño se puede aplicar para transmitir los mensajes a los clientes conectados?
    a) Patrón Factory.
    b) Patrón Singleton.
    c) Patrón Observer.
    d) Patrón Decorator.

94. En la programación distribuida con sockets en Java, ¿qué clase representa un servidor que escucha conexiones entrantes de clientes en un puerto específico?
    a) `Socket`.
    b) `InputStream`.
    c) `OutputStream`.
    d) `ServerSocket`.

95. Si se implementa un servidor Socket multihilo, ¿qué concepto es importante recordar para gestionar el flujo de datos y el acceso a recursos compartidos?
    a) El uso de JSON.
    b) La sincronización del diálogo.
    c) La Inyección de Dependencias.
    d) El uso de DTOs.

### Resultados Avanzados, Paginación y Criterios (Preguntas 96-105)

96. ¿Qué mecanismo permite a un cliente especificar el formato de los datos que desea recibir del servidor (ej. JSON o XML)?
    a) Programación Orientada a Aspectos.
    b) Inversión de Control.
    c) Negociación de Contenido.
    d) Borrado Lógico.

97. Para que un cliente obtenga el contenido en XML en lugar de JSON, puede incluir la clave `Accept` con qué valor en el encabezado de la petición HTTP?
    a) `application/json`.
    b) `text/xml`.
    c) `application/xml`.
    d) `text/plain`.

98. En Spring Data, ¿cuál es el objeto que devuelve una operación de paginación, conteniendo la lista de entidades, así como metadatos como el número total de páginas y si es la primera o la última página?
    a) `List`.
    b) `Set`.
    c) `Page`.
    d) `Stream`.

99. ¿Cuál es una ventaja de incluir los enlaces de paginación (Next, Prev, First, Last) en el encabezado (Header) de una respuesta REST, siguiendo el estándar HATEOAS?
    a) Mejora la eficiencia en la transferencia de datos y la cacheabilidad.
    b) Simplifica el código del repositorio.
    c) Permite el versionado de la API.
    d) Evita la necesidad de autenticación.

100. Para implementar criterios de búsqueda dinámicos en Spring Data JPA (filtrado por nombre o descripción, por ejemplo), tu repositorio debe extender la interfaz:
    a) `JpaRepository`.
    b) `CrudRepository`.
    c) `JpaSpecificationExecutor`.
    d) `PagingAndSortingRepository`.

101. En el objeto `Page` devuelto por Spring Data JPA, ¿qué campo indica el número total de entidades en todas las páginas?
    a) `size`.
    b) `totalPages`.
    c) `pageNumber`.
    d) `totalElements`.

102. ¿Cuál es uno de los principales motivos para implementar paginación y ordenación al tratar con grandes cantidades de datos?
    a) Reducir la complejidad del modelo de datos.
    b) Mejorar la experiencia del usuario y el rendimiento del servidor.
    c) Cumplir con los estándares de JWT.
    d) Habilitar WebSockets.

103. ¿Qué clase de Spring Boot se utiliza para construir URIs de manera programática, útil al generar enlaces de paginación en el encabezado (Link Header)?
    a) `PageRequest`.
    b) `UriComponentsBuilder`.
    c) `StringBuilder`.
    d) `PageImpl`.

104. ¿Cómo puede un cliente solicitar datos en XML usando la URL si la negociación de contenido está configurada para favorecer un parámetro de consulta?
    a) `/api/endpoint?type=xml`.
    b) `/api/endpoint?content=xml`.
    c) `/api/endpoint?accept=xml`.
    d) `/api/endpoint?format=xml`.

105. ¿Qué representa la interfaz `Specification` en el contexto de criterios de búsqueda de Spring Data JPA?
    a) Un objeto que almacena la lista de resultados paginados.
    b) Un patrón de diseño para la creación de repositorios.
    c) Un predicado que determina si una entidad debe incluirse en los resultados de la consulta.
    d) La configuración de la conexión a la base de datos.

### Protocolo HTTP y JWT (Preguntas 106-115)

106. ¿Cuál de los siguientes no es un verbo HTTP utilizado para las operaciones CRUD?
    a) GET.
    b) FETCH.
    c) PUT.
    d) DELETE.

107. ¿Qué código de respuesta HTTP indica un error en el que el servidor no puede encontrar el recurso solicitado?
    a) 400 Bad Request.
    b) 401 Unauthorized.
    c) 403 Forbidden.
    d) 404 Not Found.

108. Una de las importancias de una API REST es que son fácilmente escalables debido a su naturaleza:
    a) Stateful (con estado).
    b) Stateless (sin estado).
    c) Orientada a objetos.
    d) Orientada a aspectos.

109. Un JWT (JSON Web Token) está compuesto por tres partes separadas por un punto (`.`): Header, Payload y...
    a) Secret Key.
    b) Registered Claims.
    c) Signature.
    d) Algorithm.

110. ¿Cuál es el propósito principal de la Signature (Firma) en un JWT?
    a) Contener las reclamaciones del usuario.
    b) Indicar el algoritmo de cifrado.
    c) Asegurar que el mensaje no ha sido alterado (integridad).
    d) Especificar la audiencia (aud).

111. En el Payload (Carga Útil) de un JWT, ¿qué tipo de reclamos (claims) son predeterminados y recomendados, incluyendo campos como `iss` (emisor) y `exp` (expiración)?
    a) Públicos.
    b) Privados.
    c) Restringidos.
    d) Secretos.

112. ¿Qué necesidad de seguridad en HTTP aborda el uso de JWT?
    a) Permitir que solo las solicitudes autenticadas accedan a recursos protegidos.
    b) Cifrar los datos de la base de datos.
    c) Mejorar el rendimiento de la red.
    d) Reemplazar el protocolo TCP/IP.

113. ¿Qué biblioteca cliente HTTP para Java se destaca por facilitar la interacción con servicios web al convertir automáticamente las respuestas HTTP en objetos Java?
    a) Ktor.
    b) Retrofit.
    c) Spring Core.
    d) Jackson.

114. Ktorfit combina la potencia de Ktor con la simplicidad de Retrofit, y se utiliza principalmente en aplicaciones desarrolladas con qué lenguaje?
    a) Java.
    b) Python.
    c) Kotlin.
    d) Groovy.

115. ¿Qué código de respuesta HTTP indica que la solicitud fue correcta, pero el cliente necesita autenticación para acceder al recurso?
    a) 400 Bad Request.
    b) 401 Unauthorized.
    c) 403 Forbidden.
    d) 500 Internal Server Error.

### Spring Security (Preguntas 116-130)

116. Spring Security proporciona una capa de seguridad a nivel de:
    a) Sistema operativo.
    b) Aplicación.
    c) Base de datos.
    d) Red (Firewall).

117. ¿Qué interfaz en Spring Security es responsable de cargar los detalles de un usuario (como roles y permisos) a partir de un origen de datos (ej., base de datos) y devolverlos como un objeto `UserDetails`?
    a) `AuthenticationManager`.
    b) `PasswordEncoder`.
    c) `JwtService`.
    d) `UserDetailsService`.

118. ¿Qué componente de Spring Security se encarga de procesar y validar las credenciales del usuario durante el proceso de autenticación?
    a) `SecurityContextHolder`.
    b) `AuthenticationManager`.
    c) `UserDetailsService`.
    d) `JwtAuthenticationFilter`.

119. ¿Qué anotación debe añadirse a la clase de configuración de seguridad para habilitar la seguridad web de Spring?
    a) `@EnableWebMvc`.
    b) `@EnableMethodSecurity`.
    c) `@EnableWebSecurity`.
    d) `@Configuration`.

120. ¿Qué implementación de `PasswordEncoder` se recomienda típicamente en Spring Security para cifrar y verificar contraseñas de forma segura?
    a) MD5.
    b) SHA-1.
    c) Base64.
    d) BCrypt.

121. En el proceso de autorización, ¿dónde almacena Spring Security los detalles de quién está autenticado para que la aplicación pueda tomar decisiones de autorización?
    a) En la base de datos.
    b) En el JWT.
    c) En el `SecurityContextHolder`.
    d) En el `AuthenticationProvider`.

122. ¿Qué tipo de filtro personalizado de Spring Security se recomienda extender para asegurar que el filtro sea invocado solo una vez por cada petición HTTP?
    a) `OncePerRequestFilter`.
    b) `UsernamePasswordAuthenticationFilter`.
    c) `JwtAuthenticationFilter`.
    d) `SecurityFilterChain`.

123. Para permitir la autorización basada en roles o permisos directamente en los métodos de los controladores usando `@PreAuthorize`, ¿qué anotación es necesaria en la clase de configuración de seguridad?
    a) `@EnableWebSecurity`.
    b) `@EnableAutoConfiguration`.
    c) `@EnableMethodSecurity`.
    d) `@Configuration`.

124. Si se configura la autorización en el `SecurityFilterChain`, ¿qué expresión se utiliza para otorgar acceso a un recurso si el usuario tiene un rol específico (ej., ADMIN)?
    a) `hasAuthority('ADMIN')`.
    b) `hasRole('ADMIN')`.
    c) `permitAll()`.
    d) `authenticated()`.

125. ¿Qué anotación permite a un método de controlador obtener el objeto del usuario logueado que está en el contexto de seguridad (basado en el token)?
    a) `@WithMockUser`.
    b) `@AuthenticationPrincipal`.
    c) `@Autowired`.
    d) `@PreAuthorize`.

126. ¿Qué implementación de `AuthenticationProvider` utiliza una instancia de `UserDetailsService` y una implementación de `PasswordEncoder` para autenticar a un usuario?
    a) `JwtAuthenticationProvider`.
    b) `DaoAuthenticationProvider`.
    c) `OAuth2AuthenticationProvider`.
    d) `ExternalAuthenticationProvider`.

127. ¿Por qué se añade el `JwtAuthenticationFilter` *antes* del `UsernamePasswordAuthenticationFilter` en la cadena de filtros de seguridad?
    a) Porque el `UsernamePasswordAuthenticationFilter` es obsoleto.
    b) Porque el filtro JWT extrae las credenciales del token y las actualiza en el `SecurityContextHolder` primero.
    c) Para manejar el cifrado SSL/TSL.
    d) Porque el filtro JWT solo maneja roles.

128. Si configuras Spring Security para que trabaje sin estado, ¿qué significa esto respecto a la gestión de sesiones?
    a) Que la autenticación se realiza mediante cookies.
    b) Que el estado de autenticación no debe almacenarse en el servidor.
    c) Que se requiere un `SessionManager`.
    d) Que el JWT no es necesario.

129. ¿Qué comando se necesita generalmente para generar un certificado y una clave privada (`keystore`) si se desea configurar el servidor Spring Boot para usar SSL/TSL (HTTPS)?
    a) `java -jar`.
    b) `keytool`.
    c) `gradle build`.
    d) `docker run`.

130. El objeto `UserDetails` en Spring Security es importante porque:
    a) Proporciona una estructura de datos estandarizada para representar los detalles de un usuario autenticado.
    b) Implementa el `AuthenticationManager`.
    c) Es el encargado de la codificación de contraseñas.
    d) Genera el JWT.

### NoSQL, MongoDB y GraphQL (Preguntas 131-140)

131. ¿Cuál es una de las principales ventajas de las bases de datos NoSQL sobre SQL, especialmente en entornos de Big Data?
    a) Fuerte consistencia inmediata (ACID).
    b) Mayor madurez y comunidad.
    c) Escalabilidad horizontal y flexibilidad de esquemas.
    d) Lenguaje de consulta SQL estándar.

132. En MongoDB, ¿en qué formato binario se almacenan los datos?
    a) XML.
    b) CSV.
    c) BSON (Binary JSON).
    d) SQL.

133. ¿Cuál es la opción preferida en Spring Data MongoDB para establecer referencias manuales entre documentos de manera eficiente, que solo almacena el ID del documento vinculado?
    a) `@DBRef`.
    b) `@DocumentReference`.
    c) `@ForeignKey`.
    d) `@JoinColumn`.

134. ¿Qué función de MongoDB se utiliza para buscar documentos en una colección y actualizar el primero que coincide con el filtro de consulta, devolviendo el documento actualizado?
    a) `find()`.
    b) `updateMany()`.
    c) `findOneAndUpdate()`.
    d) `insertOne()`.

135. Para definir un modelo en Spring Data MongoDB y mapearlo a una colección, ¿qué anotaciones son esenciales en la clase Java?
    a) `@Entity` y `@Table`.
    b) `@Document` y `@Id`.
    c) `@Service` y `@Repository`.
    d) `@Configuration` y `@Bean`.

136. En GraphQL, ¿qué elemento del esquema se utiliza para definir operaciones de lectura (equivalentes a GET en REST)?
    a) `Mutation`.
    b) `Subscription`.
    c) `type`.
    d) `Query`.

137. ¿Cuál de las siguientes es una ventaja clave de GraphQL sobre REST?
    a) Utiliza múltiples URLs para cada recurso.
    b) Las respuestas son fijas y predefinidas.
    c) El cliente pide exactamente los datos que necesita, evitando *overfetching*.
    d) Requiere versionado estricto.

138. En la sintaxis de GraphQL, ¿qué símbolo se utiliza para indicar que un campo o una lista de elementos es obligatorio (no nulo)?
    a) `?`.
    b) `*`.
    c) `!`.
    d) `&`.

139. ¿Qué elemento del esquema GraphQL se utiliza para definir tipos de entrada de datos, típicamente usados en las `Mutation`?
    a) `type`.
    b) `interface`.
    c) `input`.
    d) `enum`.

140. Para habilitar las Suscripciones (operaciones en tiempo real) en GraphQL en Spring Boot, es necesario tener configurado el módulo:
    a) Spring Data JPA.
    b) Spring Security.
    c) Spring WebFlux.
    d) Spring Batch.

### Redis, Email y Despliegue (Preguntas 141-150)

141. ¿Qué es Redis?
    a) Un sistema de archivos distribuido.
    b) Una base de datos NoSQL en memoria que funciona como almacén de estructuras de datos clave-valor.
    c) Un marco de trabajo para el desarrollo de APIs REST.
    d) Un gestor de persistencia relacional.

142. ¿Cuál es una ventaja clave de usar Redis como caché distribuida sobre la caché en memoria local?
    a) Mayor limitación por la JVM.
    b) Menor rendimiento en la recuperación de datos.
    c) La caché puede ser compartida entre múltiples instancias de la aplicación (escalabilidad).
    d) No permite el control de TTL (Time To Live).

143. ¿Qué servicio se recomienda utilizar en el entorno de **desarrollo** para probar el servicio de emails, ya que intercepta los correos sin enviarlos realmente?
    a) Gmail SMTP.
    b) AWS SES.
    c) Mailtrap.
    d) SendGrid.

144. ¿Qué mecanismo se recomienda para el envío de correos electrónicos en una aplicación, con el fin de no bloquear la lógica de negocio principal y mejorar el rendimiento?
    a) Envío síncrono.
    b) Uso de `System.out.println`.
    c) Envío asíncrono (ej., usando `Thread`).
    d) Uso de la caché de Redis.

145. La configuración de perfiles de Spring Boot permite definir propiedades específicas para diferentes entornos (ej., desarrollo y producción). ¿Cómo se llama el fichero de propiedades para un perfil llamado `prod`?
    a) `prod.properties`.
    b) `application.properties/prod`.
    c) `application-prod.properties`.
    d) `profile-prod.properties`.

146. Para activar un perfil específico (ej. `dev`) al ejecutar el JAR de una aplicación Spring Boot, ¿qué parámetro se debe pasar a la línea de comandos?
    a) `--spring.profile=dev`.
    b) `--active-profile=dev`.
    c) `--profile.active=dev`.
    d) `--spring.profiles.active=dev`.

147. ¿Qué es CORS (Cross-Origin Resource Sharing)?
    a) Un protocolo para el versionado de APIs.
    b) Un mecanismo de seguridad que controla las solicitudes HTTP entre diferentes dominios (origen cruzado).
    c) Un formato de intercambio de datos.
    d) La configuración de JWT en Spring Security.

148. ¿Qué herramienta o especificación es utilizada en Spring Boot para generar documentación interactiva de APIs REST a partir de la especificación OpenAPI?
    a) Postman.
    b) Swagger.
    c) Retrofit.
    d) Ktorfit.

149. En un `Dockerfile` con una estrategia de múltiples etapas (multi-stage build), ¿cuál es el propósito de la primera etapa basada en una imagen de Maven o Gradle?
    a) Ejecutar la aplicación.
    b) Servir el contenido estático.
    c) Compilar y construir el archivo `.jar`.
    d) Configurar Redis.

150. Para documentar la estructura de una entidad, modelo o DTO en Swagger/OpenAPI, ¿qué anotación se utiliza en la clase?
    a) `@Operation`.
    b) `@ApiResponse`.
    c) `@Schema`.
    d) `@Parameter`.