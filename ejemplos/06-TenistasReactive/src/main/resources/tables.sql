CREATE TABLE IF NOT EXISTS tenistas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT, -- BD genera el ID automáticamente
    nombre VARCHAR(255) NOT NULL,
    pais VARCHAR(255),
    altura INT,
    peso INT,
    puntos INT,
    mano VARCHAR(50),
    fecha_nacimiento DATE
);