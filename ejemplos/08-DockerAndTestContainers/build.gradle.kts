plugins {
    id("java")
    // Lombok
    id("io.freefair.lombok") version "9.0.0"
    
}

group = "dev.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Logger
    implementation("ch.qos.logback:logback-classic:1.5.13")
    implementation("org.slf4j:slf4j-simple:1.7.32")
    
    // Lombok
    implementation("org.projectlombok:lombok:1.18.42") // usa la última versión de Lombok
    annotationProcessor("org.projectlombok:lombok:1.18.42")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.42")
    
    // Vavr para programación funcional
    implementation("io.vavr:vavr:0.10.4")
    
    // PostgreSQL
    implementation("org.postgresql:postgresql:42.7.4")
    
    // JUnit 5 - Versión más reciente para mejor compatibilidad
    testImplementation(platform("org.junit:junit-bom:5.11.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    
    // IMPORTANTE: JUnit Platform Launcher - requerido para Gradle
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    
    // Mockito - versiones actualizadas compatibles con Java 25
    testImplementation("org.mockito:mockito-core:5.20.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.20.0")
    
    // Test Containers - versiones más recientes
    testImplementation("org.testcontainers:testcontainers:1.21.3")
    testImplementation("org.testcontainers:junit-jupiter:1.21.3")
    testImplementation("org.testcontainers:postgresql:1.21.3")
    
    
}

tasks.test {
    useJUnitPlatform()
}

// Hacer un Jar ejecutable
tasks.jar {
    manifest {
        // Clase principal
        attributes["Main-Class"] = "dev.joseluisgs.Main"
    }
    // Incluir dependencias
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    // Excluir duplicados
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}