# Redis con Spring Boot

![redis](../images/redis-banner.png)

- [Redis con Spring Boot](#redis-con-spring-boot)
  - [Introducción](#introducción)
    - [¿Qué es Redis?](#qué-es-redis)
    - [¿Para qué se usa Redis?](#para-qué-se-usa-redis)
    - [¿Por qué usar Redis como caché?](#por-qué-usar-redis-como-caché)
      - [Ventajas de Redis vs Caché en memoria local:](#ventajas-de-redis-vs-caché-en-memoria-local)
  - [Paso 1: Configuración Inicial del Proyecto](#paso-1-configuración-inicial-del-proyecto)
    - [1.1 Dependencias de Gradle](#11-dependencias-de-gradle)
    - [1.2 Variables de entorno](#12-variables-de-entorno)
  - [Paso 2: Configuración de Docker](#paso-2-configuración-de-docker)
    - [2.1 Docker Compose para Desarrollo](#21-docker-compose-para-desarrollo)
    - [2.2 Docker Compose para Producción](#22-docker-compose-para-producción)
  - [Paso 3: Configuración de Spring Boot](#paso-3-configuración-de-spring-boot)
    - [3.1 Configuración para Desarrollo](#31-configuración-para-desarrollo)
  - [Paso 4: Configuración Avanzada (Opcional)](#paso-4-configuración-avanzada-opcional)
    - [4.1 Clase de Configuración Java](#41-clase-de-configuración-java)
    - [4.2 Uso en los Servicios](#42-uso-en-los-servicios)
  - [Paso 5: Monitoreo y Logs](#paso-5-monitoreo-y-logs)
    - [5.1 Configuración de Logs](#51-configuración-de-logs)
    - [5.2 Ejemplo de Logs](#52-ejemplo-de-logs)
    - [5.3 Herramientas de Monitoreo](#53-herramientas-de-monitoreo)
  - [Paso 6: Ejecución y Pruebas](#paso-6-ejecución-y-pruebas)
    - [6.1 Levantar el entorno](#61-levantar-el-entorno)
    - [6.2 Probar la aplicación](#62-probar-la-aplicación)
    - [6.3 Verificar funcionamiento](#63-verificar-funcionamiento)
  - [Resumen de Beneficios](#resumen-de-beneficios)
    - [✅ **Lo que conseguimos:**](#-lo-que-conseguimos)
    - [⚡ **Mejoras de rendimiento esperadas:**](#-mejoras-de-rendimiento-esperadas)
    - [🎯 **Casos de uso ideales para Redis:**](#-casos-de-uso-ideales-para-redis)
  - [Conclusión](#conclusión)
  - [8. Ejercicio propuesto: Redis para Funkos](#8-ejercicio-propuesto-redis-para-funkos)


## Introducción

### ¿Qué es Redis?

**Redis** (Remote Dictionary Server) es una base de datos NoSQL en memoria de código abierto que funciona como almacén de estructuras de datos clave-valor. Es extremadamente rápida ya que mantiene todos los datos en memoria RAM.

### ¿Para qué se usa Redis?

Redis se utiliza principalmente para:

- **🚀 Caché de aplicaciones**: Almacenar datos frecuentemente accedidos
- **📦 Almacenamiento de datos en memoria**: Para acceso rápido y compartido entre servicios
- **📊 Sesiones de usuario**: Mantener estado de sesiones web
- **📈 Contadores en tiempo real**: Likes, visitas, estadísticas
- **🔔 Pub/Sub**: Sistema de mensajería
- **⏰ Datos temporales**: Con TTL (Time To Live)
- **🎯 Colas de tareas**: Para procesamiento asíncrono

### ¿Por qué usar Redis como caché?

#### Ventajas de Redis vs Caché en memoria local:

| Aspecto           | Caché Local (Simple)     | Redis                         |
| ----------------- | ------------------------ | ----------------------------- |
| **Persistencia**  | ❌ Se pierde al reiniciar | ✅ Persiste datos              |
| **Escalabilidad** | ❌ Una instancia          | ✅ Múltiples instancias        |
| **Memoria**       | ❌ Limitada por JVM       | ✅ Memoria dedicada            |
| **TTL**           | ❌ Básico                 | ✅ Avanzado y flexible         |
| **Distribución**  | ❌ No compartida          | ✅ Compartida entre apps       |
| **Monitoreo**     | ❌ Limitado               | ✅ Herramientas especializadas |

---

## Paso 1: Configuración Inicial del Proyecto

### 1.1 Dependencias de Gradle

Añade las dependencias necesarias en tu `build.gradle.kts`:

```kotlin
dependencies {
    // Caché base de Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-cache")
    
    // Redis para Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
}
```

### 1.2 Variables de entorno

Configura tu archivo `.env` con las variables de Redis:

```bash
# Redis Configuration
REDIS_HOST=redis-db
REDIS_PORT=6379
REDIS_PASSWORD=redisPassword123
REDIS_DATABASE=0
REDIS_SSL=false
REDIS_TIMEOUT=3000
REDIS_POOL_MAX_ACTIVE=20
REDIS_POOL_MAX_IDLE=10
REDIS_POOL_MIN_IDLE=5
```

---

## Paso 2: Configuración de Docker

### 2.1 Docker Compose para Desarrollo

Archivo `docker-compose-dev.yml` con datos temporales:

```yaml
services:
  
  # Redis (sin persistencia para desarrollo)
  redis-db:
    container_name: tienda-db_redis
    image: redis:7-alpine
    restart: always
    env_file: .env
    command: redis-server --requirepass ${REDIS_PASSWORD}
    ports:
      - ${REDIS_PORT}:6379
    networks:
      - tienda-network
    
  # Redis Commander para administración web
  redis-commander:
    container_name: tienda-db_redis-commander
    image: rediscommander/redis-commander:latest
    restart: always
    env_file: .env
    ports:
      - 8082:8081
    environment:
      REDIS_HOSTS: local:redis-db:${REDIS_PORT}:${REDIS_DATABASE}:${REDIS_PASSWORD}
    depends_on:
      - redis-db
    networks:
      - tienda-network

networks:
  tienda-network:
    driver: bridge
```

### 2.2 Docker Compose para Producción

Archivo `docker-compose.yml` con persistencia:

```yaml
# Lo necesario para ejecutar la aplicación en producción
services:
  # Redis (con persistencia para producción)
  redis-db:
    container_name: tienda-db_redis
    image: redis:7-alpine
    restart: always
    env_file: .env
    command: redis-server --requirepass ${REDIS_PASSWORD} --appendonly yes
    ports:
      - ${REDIS_PORT}:6379
    volumes:
      - redis-data:/data
    networks:
      - tienda-network
# Volúmenes para persistencia de datos
volumes:
  redis-data:

# Red para conectar los contenedores
networks:
  tienda-network:
    driver: bridge
```

---

## Paso 3: Configuración de Spring Boot

### 3.1 Configuración para Desarrollo

Archivo `application-dev.properties`:

```properties
# PERFIL DE DESARROLLO
## CACHE
# Configuración de caché en memoria para desarrollo
spring.cache.type=simple
#spring.cache.cache-names=categorias,pedidos,productos,users
# Logging de caché para desarrollo (ver qué se cachea)
logging.level.org.springframework.cache=DEBUG
logging.level.es.joseluisgs.tienda=DEBUG


### 3.2 Configuración para Producción

Archivo `application-prod.properties`:

```properties
# PERFIL DE PRODUCCION
## REDIS CACHE
# Configuración de Redis para producción
spring.cache.type=redis
spring.data.redis.host=${REDIS_HOST:localhost}
spring.data.redis.port=${REDIS_PORT:6379}
spring.data.redis.password=${REDIS_PASSWORD:}
spring.data.redis.database=${REDIS_DATABASE:0}
spring.data.redis.timeout=${REDIS_TIMEOUT:3000ms}
# Pool de conexiones Redis
spring.data.redis.lettuce.pool.max-active=${REDIS_POOL_MAX_ACTIVE:20}
spring.data.redis.lettuce.pool.max-idle=${REDIS_POOL_MAX_IDLE:10}
spring.data.redis.lettuce.pool.min-idle=${REDIS_POOL_MIN_IDLE:5}
spring.data.redis.lettuce.pool.max-wait=2000ms
# Cachés predefinidos
# spring.cache.cache-names=categorias,pedidos,productos,users
# Logging de caché para producción (menos verbose)
logging.level.org.springframework.cache=WARN
```

---

## Paso 4: Configuración Avanzada (Opcional)

### 4.1 Clase de Configuración Java

> **NOTA**: Esta clase es **OPCIONAL** si ya tienes la configuración en `application.properties`. Solo es útil si necesitas TTL específicos por caché.

```java

/**
 * Configuración de caché por perfiles
 * 
 * OPCIONAL: Solo necesaria si quieres TTL específicos por caché
 * Sin esta clase, la configuración de application.properties es suficiente
 * 
 * @author joseluisgs
 * @since 2025-10-21
 */
@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Caché en memoria para desarrollo
     * TTL por defecto (sin expiración)
     * Este bean se activa solo en el perfil "dev"
     * No es necesario configurar TTL aquí ya que es caché simple
     * Todo se maneja en memoria local
     * podríamos suprimir esta clase y usar solo application-dev.properties
     */
    @Bean
    @Profile("dev")
    public CacheManager devCacheManager() {
      // Caché simple en memoria de los que hemos puesto en cache-names de los servicios
        return new ConcurrentMapCacheManager("categorias", "pedidos", "productos", "users");
    }

    /**
     * Redis para producción con TTL específicos por servicio
     */
    @Bean
    @Profile("prod")
    public RedisCacheManager prodCacheManager(RedisConnectionFactory connectionFactory) {
        // Clave en String y valor serializado en JSON
        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer()));

        // TTL específicos para cada servicio
        Map<String, RedisCacheConfiguration> cacheConfigurations = Map.of(
            // Categorías: TTL largo (24 horas) - casi nunca cambian
            "categorias", defaultConfig.entryTtl(Duration.ofHours(24)),
            
            // Usuarios: TTL medio (2 horas) - cambian ocasionalmente
            "users", defaultConfig.entryTtl(Duration.ofHours(2)),
            
            // Productos: TTL medio (1 hora) - cambian regularmente
            "productos", defaultConfig.entryTtl(Duration.ofHours(1)),
            
            // Pedidos: TTL corto (30 minutos) - cambian frecuentemente
            "pedidos", defaultConfig.entryTtl(Duration.ofMinutes(30))
        );

        return RedisCacheManager.builder(connectionFactory)
            .cacheDefaults(defaultConfig.entryTtl(Duration.ofHours(1)))
            .withInitialCacheConfigurations(cacheConfigurations)
            .build();
    }
}
```

### 4.2 Uso en los Servicios

Las anotaciones de caché funcionan igual independientemente del proveedor:

```java
@Service
@CacheConfig(cacheNames = {"users"})
@Slf4j
public class UsuarioService {

    @Cacheable(key = "#id")
    public Usuario findById(Long id) {
        log.info("🔍 Buscando usuario con ID: {} (si ves esto, NO viene de caché)", id);
        return usuarioRepository.findById(id);
    }

    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @CachePut(key = "#usuario.id")
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
```

```java
@Service
@CacheConfig(cacheNames = {"categorias"})
public class CategoriaService {
    // Tus métodos con @Cacheable, @CacheEvict, etc.
}

@Service
@CacheConfig(cacheNames = {"productos"})
public class ProductoService {
    // Tus métodos con @Cacheable, @CacheEvict, etc.
}

@Service
@CacheConfig(cacheNames = {"pedidos"})
public class PedidoService {
    // Tus métodos con @Cacheable, @CacheEvict, etc.
}
```

---

## Paso 5: Monitoreo y Logs

### 5.1 Configuración de Logs

Para ver si los datos vienen de caché, configura el logging en `application.properties`:

```properties
# Para desarrollo - ver hits/miss de caché
logging.level.org.springframework.cache=DEBUG
logging.level.es.joseluisgs.tienda=DEBUG

# Pattern personalizado para mejor visualización
logging.pattern.console=%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
```

### 5.2 Ejemplo de Logs

```bash
# Cache hit (viene de caché)
14:20:18.123 DEBUG o.s.cache.interceptor.CacheInterceptor - Cache hit: key 'users::1' for cache(s) [users]

# Cache miss (va a base de datos)
14:20:25.456 DEBUG o.s.cache.interceptor.CacheInterceptor - Cache miss: key 'users::2' for cache(s) [users] 
14:20:25.457 INFO  es.joseluisgs.tienda.service - 🔍 Buscando usuario con ID: 2 (NO viene de caché)
```

### 5.3 Herramientas de Monitoreo

- **Desarrollo**: Redis Commander en `http://localhost:8082`
- **Producción**: Integrar con herramientas como Redis Insight o monitoring de Spring Boot Actuator

---

## Paso 6: Ejecución y Pruebas

### 6.1 Levantar el entorno

```bash
# Desarrollo
docker-compose -f docker-compose-dev.yml up -d

# Producción  
docker-compose up -d
```

### 6.2 Probar la aplicación

```bash
# Desarrollo con caché en memoria
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Producción con Redis
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```

### 6.3 Verificar funcionamiento

1. **Primera consulta**: Debería ir a BD y guardar en caché
2. **Segunda consulta**: Debería venir de caché (más rápida)
3. **Logs**: Deberían mostrar "Cache hit" vs "Cache miss"

---

## Resumen de Beneficios

### ✅ **Lo que conseguimos:**

- **🔄 Fácil migración**: Solo cambios en configuración
- **🏃‍♂️ Mayor rendimiento**: Datos en memoria ultra-rápidos
- **📈 Escalabilidad**: Redis compartido entre múltiples instancias
- **⏰ Control de TTL**: Datos expiran automáticamente
- **🔧 Flexibilidad**: Diferentes configuraciones por entorno
- **📊 Monitoreo**: Herramientas visuales para gestionar caché

### ⚡ **Mejoras de rendimiento esperadas:**

- **Consultas de BD**: De ~100ms a ~1ms
- **APIs REST**: Respuesta 10-100x más rápida para datos cacheados
- **Carga del servidor**: Reducción significativa de consultas a BD

### 🎯 **Casos de uso ideales para Redis:**

- **Datos que se leen mucho y cambian poco**: Categorías, configuraciones
- **Sesiones de usuario**: Mantener estado entre requests
- **Resultados de consultas complejas**: Reportes, estadísticas
- **APIs externas**: Cachear respuestas de servicios externos

---

## Conclusión

Con esta configuración tienes:

- **Desarrollo**: Caché simple para pruebas rápidas
- **Producción**: Redis robusto y escalable
- **Código**: Sin cambios en tu lógica de negocio
- **Monitoring**: Logs detallados para troubleshooting

La migración de caché en memoria a Redis es transparente para tu aplicación, pero ofrece beneficios significativos en rendimiento, escalabilidad y funcionalidades avanzadas.


## 8. Ejercicio propuesto: Redis para Funkos
Implementa caché con Redis para el servicio de Funkos en tu aplicación solo en el modo de producción. Configura TTL adecuados para los datos de Funkos y verifica mediante logs que las consultas se están sirviendo desde Redis después de la primera carga.