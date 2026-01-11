package dev.joseluisgs.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dev.joseluisgs.exceptions.TenistaException;
import dev.joseluisgs.models.Tenista;
import dev.joseluisgs.validator.TenistaValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * Implementación de TenistasStorage que carga datos desde CSV y guarda en JSON usando Jackson.
 * Filtra registros inválidos mediante TenistaValidator.
 * <p>
 * Autor: JoseLuisGS
 *
 * @see dev.joseluisgs.storage.TenistasStorage
 * @see dev.joseluisgs.validator.TenistaValidator
 * @see com.fasterxml.jackson.databind.ObjectMapper
 */
public class TenistasStorageImpl implements TenistasStorage {
    private final Logger logger = LoggerFactory.getLogger(TenistasStorageImpl.class); // Logger

    /**
     * Crea el servicio de almacenamiento e inicializa el logger.
     */
    public TenistasStorageImpl() {
        logger.info("Inicializando TenistasStorageImpl");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Tenista> loadData(String filePath) throws TenistaException.StorageException {
        if (filePath == null || filePath.isEmpty()) {
            logger.error("El path del archivo no puede ser nulo o vacío");
            throw new TenistaException.StorageException("El path del archivo no puede ser nulo o vacío");
        }

        if (!filePath.endsWith(".csv")) {
            logger.error("El archivo no es un CSV: {}", filePath);
            throw new TenistaException.StorageException("El archivo no es un CSV: " + filePath);
        }

        Path myPath = Paths.get(filePath);
        if (!myPath.toFile().exists()) {
            logger.error("El archivo no existe: {}", filePath);
            throw new TenistaException.StorageException("El archivo no existe: " + filePath);
        }

        try (var lines = Files.lines(myPath, StandardCharsets.UTF_8)) {
            var tenistas = lines.skip(1) // Ignorar cabecera
                    .map(line -> {
                        String[] parts = line.split(",");
                        int id = Integer.parseInt(parts[0]);
                        String nombre = parts[1];
                        String pais = parts[2];
                        int altura = Integer.parseInt(parts[3]);
                        int peso = Integer.parseInt(parts[4]);
                        int puntos = Integer.parseInt(parts[5]);
                        Tenista.Mano mano = Tenista.Mano.valueOf(parts[6].toUpperCase());
                        LocalDate fechaNacimiento = LocalDate.parse(parts[7], DateTimeFormatter.ISO_LOCAL_DATE);

                        // Crear el Tenista
                        Tenista tenista = Tenista.builder()
                                .id(id)
                                .nombre(nombre)
                                .pais(pais)
                                .altura(altura)
                                .peso(peso)
                                .puntos(puntos)
                                .mano(mano)
                                .fechaNacimiento(fechaNacimiento)
                                .build();

                        // Validación básica opcional
                        try {
                            TenistaValidator.validate(tenista); // Lanza TenistaException.ValidationException si hay error
                        } catch (TenistaException.ValidationException e) {
                            logger.warn("Tenista inválido en CSV: {}, línea: {}", e.getMessage(), line);
                            return null; // O lanzar StorageException si prefieres fallar todo
                        }

                        return tenista;
                    })
                    .filter(Objects::nonNull) // Filtrar tenistas inválidos
                    .toList();

            logger.info("Datos cargados correctamente desde el archivo: {} con un total {}", filePath, tenistas.size());
            return tenistas;
        } catch (IOException e) {
            logger.error("Error al leer el archivo: {}", filePath, e);
            throw new TenistaException.StorageException("Error al leer el archivo: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error al parsear los datos del archivo: {}", filePath, e);
            throw new TenistaException.StorageException("Error al parsear los datos: " + e.getMessage());
        }
    }

    @Override
    public int saveData(List<Tenista> tenistas, String filePath) throws TenistaException.StorageException {
        if (filePath == null || filePath.isEmpty()) {
            logger.error("El path del archivo no puede ser nulo o vacío");
            throw new TenistaException.StorageException("El path del archivo no puede ser nulo o vacío");
        }

        if (!filePath.endsWith(".json")) {
            logger.error("El archivo no es un JSON: {}", filePath);
            throw new TenistaException.StorageException("El archivo no es un JSON: " + filePath);
        }

        var myPath = Path.of(filePath);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.registerModule(new JavaTimeModule());
            mapper.writeValue(Files.newBufferedWriter(myPath, StandardCharsets.UTF_8), tenistas);
            logger.info("Datos guardados correctamente en el archivo: {} con un total {}", filePath, tenistas.size());
            return tenistas.size();
        } catch (IOException e) {
            throw new TenistaException.StorageException("Error al escribir el archivo: " + e.getMessage());
        }
    }
}