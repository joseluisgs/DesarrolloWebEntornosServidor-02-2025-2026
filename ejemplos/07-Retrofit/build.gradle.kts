plugins {
    id("java")
    // Lombok
    id("io.freefair.lombok") version "9.0.0" // Plugin para integrar Lombok con Gradle
}

group = "dev.joseluisgs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //Logger
    implementation("ch.qos.logback:logback-classic:1.5.13")
    implementation("org.slf4j:slf4j-api:2.0.12")
    
    // Lombok
    compileOnly("org.projectlombok:lombok:1.18.42")
    annotationProcessor("org.projectlombok:lombok:1.18.42")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.42")
    
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-jackson:3.0.0") // Jackson con Retrofit
    implementation("com.squareup.retrofit2:adapter-rxjava3:3.0.0") // RxJava3 con Retrofit
    
    // Vavr para programación funcional
    implementation("io.vavr:vavr:0.10.7")
    
    
    // RxJava3 para programación reactiva
    implementation("io.reactivex.rxjava3:rxjava:3.1.12")
    
    
    testImplementation(platform("org.junit:junit-bom:5.14.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-launcher")
    
    // Mockito para nuestros test con JUnit 5
    testImplementation("org.mockito:mockito-junit-jupiter:5.20.0")
    testImplementation("org.mockito:mockito-core:5.20.0")
    testImplementation("io.projectreactor:reactor-test:3.7.11")
    
    
}

tasks.test {
    useJUnitPlatform()
}

// Hacer un Jar ejecutable
tasks.jar {
    manifest {
        // Clase principal
        attributes["Main-Class"] = "dev.joseluisgs.MainKt"
    }
    // Incluir dependencias
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    // Excluir duplicados
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}