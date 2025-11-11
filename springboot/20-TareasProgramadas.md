# Tutorial: Tareas Programadas en Spring Boot

- [Tutorial: Tareas Programadas en Spring Boot](#tutorial-tareas-programadas-en-spring-boot)
  - [1. Activar la planificación en tu aplicación](#1-activar-la-planificación-en-tu-aplicación)
  - [2. Crear una tarea programada](#2-crear-una-tarea-programada)
    - [Explicación de las opciones más habituales:](#explicación-de-las-opciones-más-habituales)
      - [Ejemplo de expresión cron:](#ejemplo-de-expresión-cron)
  - [3. Ejemplo más avanzado: Enviar correos con novedades](#3-ejemplo-más-avanzado-enviar-correos-con-novedades)
  - [4. Buenas prácticas](#4-buenas-prácticas)
  - [5. Recursos útiles](#5-recursos-útiles)
  - [6. Desafío Propuesto](#6-desafío-propuesto)


Las tareas programadas (“scheduled tasks”) permiten ejecutar automáticamente fragmentos de código en momentos concretos, por ejemplo, cada cierto número de segundos, diariamente o periódicamente. Son perfectas para trabajos recurrentes como limpieza de logs, envío de correos, actualizaciones automáticas, etc.

---

## 1. Activar la planificación en tu aplicación

Primero, debes habilitar el soporte para tareas programadas añadiendo la anotación `@EnableScheduling` en tu clase principal.

```java
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling // ¡Es necesario para que funcione!
public class MiAplicacion {
    public static void main(String[] args) {
        SpringApplication.run(MiAplicacion.class, args);
    }
}
```

---

## 2. Crear una tarea programada

Puedes crear una clase de tipo `@Component` con métodos anotados con `@Scheduled`. Ejemplo simple:

```java
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MiTareaProgramada {

    // Se ejecuta cada 10 segundos (10000ms)
    @Scheduled(fixedRate = 10000)
    public void tareaRecurrente() {
        System.out.println("Ejecutando tarea cada 10 segundos: " + java.time.LocalDateTime.now());
    }

    // Se ejecuta todos los días a las 8:30 de la mañana
    @Scheduled(cron = "0 30 8 * * ?")
    public void tareaDiaria() {
        System.out.println("Tarea diaria: " + java.time.LocalDateTime.now());
    }
}
```

---

### Explicación de las opciones más habituales:

- `fixedRate = ms` ⇒ ejecuta cada X milisegundos, sin esperar a que termine el método.
- `fixedDelay = ms` ⇒ ejecuta cada X milisegundos **después** de terminar el anterior.
- `cron = "expresión_cron"` ⇒ permite definir momentos concretos (similar a cron en Linux).

#### Ejemplo de expresión cron:
- `"0 0 12 * * ?"` ⇒ Todos los días a las 12:00.
- `"0 */15 * * * ?"` ⇒ Cada 15 minutos.

Puedes usar [generadores de cron online](https://www.cronmaker.com/) para experimentar.

---

## 3. Ejemplo más avanzado: Enviar correos con novedades

Imagina que quieres mandar cada mañana un email a los usuarios con los productos nuevos de la tienda. Sería así:

```java
@Component
public class NovedadesTask {
    @Autowired
    private ProductosService productosService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UsersService usersService;

    private LocalDateTime ultimaEjecucion = LocalDateTime.now().minusDays(1);

    @Scheduled(cron = "0 30 8 * * ?")
    public void enviarNovedades() {
        LocalDateTime ahora = LocalDateTime.now();
        var nuevosProductos = productosService.findByCreatedAtBetween(ultimaEjecucion, ahora);

        if (!nuevosProductos.isEmpty()) {
            StringBuilder html = new StringBuilder("<h1>Novedades</h1><ul>");
            nuevosProductos.forEach(p -> html.append("<li>").append(p.getNombre()).append("</li>"));
            html.append("</ul>");
            
            usersService.findAllUsers()
                .stream()
                .filter(user -> user.getEmail() != null)
                .forEach(user -> emailService.sendHtmlEmail(
                    user.getEmail(),
                    "Novedades en la tienda",
                    html.toString()
                ));
        }
        ultimaEjecucion = ahora;
    }
}
```

---

## 4. Buenas prácticas

- Piensa bien la frecuencia: evita sobrecargar tu servidor con tareas demasiado frecuentes.
- Ten en cuenta el tiempo de ejecución de la tarea para elegir entre `fixedRate` o `fixedDelay`.
- Para tareas muy importantes (copia de seguridad, facturación...), añade logs y alertas.
- Controla el acceso: no pongas en tareas programadas operaciones peligrosas, delicadas o inseguras.
- Para mandar mails, pon destinatario configurable, añade control de errores y prueba en entorno seguro.

---

## 5. Recursos útiles

- [Documentación oficial Spring Scheduling](https://docs.spring.io/spring-framework/reference/integration/scheduling.html)
- [CronMaker (generador de expresiones cron)](https://www.cronmaker.com/)

---

## 6. Desafío Propuesto

1. Crea una tarea programada que borre productos que lleven más de un año sin venderse.
2. Haz una que mande cada lunes a las 8:00 un resumen semanal de ventas al correo del admin.
3. Añade un log que informe cada vez que una tarea se ejecuta y guarda ese log en la base de datos.

¡Haz pruebas, experimenta y automatiza tareas útiles para tu aplicación! 🚀