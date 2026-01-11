# Proyecto Tenistas

Este proyecto es una aplicación Java que gestiona información sobre tenistas, utilizando una base de datos SQL y diversas tecnologías modernas de desarrollo.

## Características principales

- **Gestión de tenistas:** Operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para manejar datos de tenistas.
- **Almacenamiento flexible:** Soporte para base de datos SQL (H2 en memoria por defecto) y operaciones de importación/exportación con archivos CSV y JSON.
- **Caché:** Implementación de un sistema de caché para optimizar las consultas frecuentes.
- **Validación:** Validación de datos de tenistas antes de su almacenamiento.
- **Logging:** Uso de Logback para un registro detallado de las operaciones.
- **Configuración:** Uso de archivos de propiedades para configurar la aplicación.
- **Tests:** Amplia cobertura de pruebas unitarias y de integración.

## Estructura del proyecto

- `src/main/java/dev/joseluisgs/`
  - `config/`: Configuración de la aplicación.
  - `service/`: Implementación de la lógica de negocio.
  - `storage/`: Manejo de operaciones de almacenamiento (CSV, JSON).
  - `repository/`: Interacción con la base de datos.
  - `model/`: Definición de entidades (Tenista).
  - `validator/`: Validación de datos.
- `src/test/java/dev/joseluisgs/`: Tests unitarios y de integración.
- `data/`: Archivos de datos de ejemplo.

## Tecnologías utilizadas

- Java 17+
- Gradle
- H2 Database (configurable para otras bases de datos SQL)
- Logback
- JUnit 5

## Configuración

La configuración de la aplicación se realiza a través del archivo `application.properties`. Algunas propiedades clave incluyen:

- `database.url`: URL de conexión a la base de datos.
- `cache.size`: Tamaño de la caché.
- `database.init.tables`: Inicialización de tablas.
- `database.init.data`: Carga inicial de datos.

## Instalación y ejecución

1. Clona el repositorio:
   ```
git clone https://github.com/tu-usuario/Tenistas.git
   ```
2. Navega al directorio del proyecto:
   ```
cd Tenistas
   ```
3. Compila el proyecto:
   ```
gradle build
   ```
4. Ejecuta la aplicación:
   ```
gradle run
   ```

## Ejecución de pruebas

Para ejecutar los tests:
```
gradle test
```

## Funcionalidades destacadas

- Importación de datos desde CSV.
- Exportación de datos a JSON.
- Validación de datos de tenistas.
- Uso de caché para optimizar consultas repetidas.
- Manejo de excepciones personalizadas.

## Contribución

Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request para sugerencias o mejoras.

## Licencia

Este proyecto está bajo la licencia MIT.
