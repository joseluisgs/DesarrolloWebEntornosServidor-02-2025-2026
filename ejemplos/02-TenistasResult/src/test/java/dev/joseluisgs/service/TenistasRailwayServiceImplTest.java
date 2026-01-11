package dev.joseluisgs.service;

import dev.joseluisgs.errors.TenistaError;
import dev.joseluisgs.models.Tenista;
import dev.joseluisgs.repository.TenistasRepository;
import dev.joseluisgs.storage.TenistasStorage;
import io.vavr.control.Either;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Clase de pruebas para TenistasRailwayServiceImpl.
 * Comprueba el correcto funcionamiento de la lógica de negocio y la caché.
 * Referencia: dev.joseluisgs.service.TenistasRailwayServiceImpl
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests del servicio TenistasRailwayServiceImpl")
public class TenistasRailwayServiceImplTest {

    @Mock
    TenistasRepository repository;

    @Mock
    TenistasStorage storage;

    @InjectMocks
    TenistasRailwayServiceImpl service;

    private Tenista construirTenistaValido(long id) {
        return Tenista.builder()
                .id(id)
                .nombre("Carlos Alcaraz")
                .pais("España")
                .altura(183)
                .peso(74)
                .puntos(9000)
                .mano(Tenista.Mano.DIESTRO)
                .fechaNacimiento(LocalDate.of(2003, 5, 5))
                .build();
    }

    private Tenista construirTenistaInvalido() {
        // Fecha futura -> inválido por el validador
        return Tenista.builder()
                .id(10)
                .nombre("Jugador Futuro")
                .pais("España")
                .altura(180)
                .peso(75)
                .puntos(1000)
                .mano(Tenista.Mano.DIESTRO)
                .fechaNacimiento(LocalDate.now().plusDays(1))
                .build();
    }

    @Nested
    @DisplayName("Casos positivos")
    class CasosPositivos {
        @Test
        @DisplayName("findAll devuelve la lista del repositorio")
        void findAllDevuelveListaDelRepositorio() {
            List<Tenista> lista = List.of(construirTenistaValido(1), construirTenistaValido(2));
            when(repository.findAll()).thenReturn(lista);

            List<Tenista> res = service.findAll();

            assertAll("Lista del servicio",
                    () -> assertNotNull(res),
                    () -> assertEquals(2, res.size()),
                    () -> assertEquals(1, res.get(0).getId()),
                    () -> assertEquals("Carlos Alcaraz", res.get(0).getNombre())
            );
            verify(repository, times(1)).findAll();
        }

        @Test
        @DisplayName("findById usa la caché tras la primera consulta")
        void findByIdUsaCacheTrasPrimeraConsulta() throws Exception {
            Tenista t = construirTenistaValido(5);
            when(repository.findById(5L)).thenReturn(Optional.of(t));

            // Usa isRight() para verificar éxito y get() para obtener el valor
            Tenista primero = service.findById(5L).get();
            Tenista segundo = service.findById(5L).get();

            assertAll("Cache findById",
                    () -> assertNotNull(primero),
                    () -> assertNotNull(segundo),
                    () -> assertSame(primero, segundo),
                    () -> assertEquals(5L, segundo.getId())
            );
            // El mock de repository solo es llamado una vez
            verify(repository, times(1)).findById(5L);
        }

        @Test
        @DisplayName("save con tenista válido guarda y lo mete en caché")
        void saveConTenistaValidoGuardaYCachea() throws Exception {
            Tenista nuevo = construirTenistaValido(Tenista.NEW_TENISTA_ID);
            Tenista guardado = construirTenistaValido(100);
            when(repository.save(any(Tenista.class))).thenReturn(guardado);

            // Se espera que sea un Either.right y se extrae el valor con get()
            Tenista res = service.save(nuevo).get();

            assertAll("Guardado",
                    () -> assertNotNull(res),
                    () -> assertEquals(100L, res.getId()),
                    () -> assertEquals(nuevo.getNombre(), res.getNombre())
            );

            // Ahora debe estar en caché, por lo que findById no llama al repositorio
            reset(repository);
            // Asegúrate de que el resultado de findById sea un Either.right antes de usar get()
            var either = service.findById(100L);
            assertTrue(either.isRight());
            Tenista desdeCache = either.get();
            assertAll("Cache tras save",
                    () -> assertNotNull(desdeCache),
                    () -> assertEquals(100L, desdeCache.getId())
            );
            verify(repository, never()).findById(anyLong());
        }

        @Test
        @DisplayName("update existente actualiza y refresca la caché")
        void updateExistenteActualizaYRefrescaCache() throws Exception {
            // Primero cargamos en caché un tenista
            Tenista original = construirTenistaValido(7);
            when(repository.findById(7L)).thenReturn(Optional.of(original));
            Tenista fromRepo = service.findById(7L).get(); // pobla caché
            assertNotNull(fromRepo);
            verify(repository, times(1)).findById(7L);

            // Preparamos actualización
            Tenista actualizado = construirTenistaValido(7);
            actualizado.setPuntos(9500);
            when(repository.update(any(Tenista.class))).thenReturn(Optional.of(actualizado));

            // Se espera que sea un Either.right y se extrae el valor
            Tenista res = service.update(actualizado).get();
            assertAll("Actualizado",
                    () -> assertNotNull(res),
                    () -> assertEquals(9500, res.getPuntos())
            );

            // Al pedirlo de nuevo debe salir de la caché actualizada sin ir al repo
            reset(repository);
            var either = service.findById(7L);
            assertTrue(either.isRight());
            Tenista desdeCache = either.get();
            assertAll("Cache tras update",
                    () -> assertNotNull(desdeCache),
                    () -> assertEquals(9500, desdeCache.getPuntos())
            );
            verify(repository, never()).findById(7L);
        }

        @Test
        @DisplayName("deleteById exitoso devuelve true")
        void deleteByIdExitosoDevuelveTrue() throws Exception {
            when(repository.deleteById(3L)).thenReturn(true);
            // Verifica que el resultado sea un Either.right con el valor true
            Either<TenistaError.NotFound, Boolean> result = service.deleteById(3L);
            assertTrue(result.isRight());
            assertTrue(result.get());
            verify(repository).deleteById(3L);
        }

        @Test
        @DisplayName("importFromCsv carga, valida y guarda todos")
        void importFromCsvCargaValidaYGuardaTodos() throws Exception {
            List<Tenista> lista = List.of(construirTenistaValido(1), construirTenistaValido(2));
            when(storage.loadData("ruta.csv")).thenReturn(lista);
            when(repository.save(any(Tenista.class))).thenAnswer(inv -> inv.getArgument(0));

            // El resultado esperado es un Either.right, se verifica su existencia
            Either<TenistaError, Void> result = service.importFromCsv("ruta.csv");
            assertTrue(result.isRight());

            verify(storage, times(1)).loadData("ruta.csv");
            verify(repository, times(2)).save(any(Tenista.class));
        }

        @Test
        @DisplayName("exportToJson obtiene todos y delega en storage")
        void exportToJsonObtieneTodosYDelegarEnStorage() throws Exception {
            List<Tenista> lista = List.of(construirTenistaValido(1), construirTenistaValido(2));
            when(repository.findAll()).thenReturn(lista);
            when(storage.saveData(eq(lista), eq("salida.json"))).thenReturn(lista.size());

            // El resultado esperado es un Either.right
            Either<TenistaError.StorageError, Void> result = service.exportToJson("salida.json");
            assertTrue(result.isRight());

            verify(repository).findAll();
            verify(storage).saveData(lista, "salida.json");
        }
    }


    @Nested
    @DisplayName("Casos negativos")
    class CasosNegativos {
        @Test
        @DisplayName("findById inexistente devuelve TenistaError.NotFound")
        void findByIdInexistenteDevuelveError() {
            when(repository.findById(99L)).thenReturn(Optional.empty());
            // Verifica que sea un Either.left y que el error sea del tipo correcto
            Either<TenistaError.NotFound, Tenista> result = service.findById(99L);
            assertTrue(result.isLeft());
            assertInstanceOf(TenistaError.NotFound.class, result.getLeft());
        }

        @Test
        @DisplayName("save con tenista inválido devuelve TenistaError.ValidationError y no llama repo")
        void saveInvalidoDevuelveValidationErrorYNoLlamaRepo() {
            Tenista invalido = construirTenistaInvalido();
            // Verifica que sea un Either.left y que el error sea del tipo correcto
            Either<TenistaError.ValidationError, Tenista> result = service.save(invalido);
            assertTrue(result.isLeft());
            assertInstanceOf(TenistaError.ValidationError.class, result.getLeft());
            verify(repository, never()).save(any());
        }

        @Test
        @DisplayName("update con tenista inválido devuelve TenistaError.ValidationError y no llama repo")
        void updateInvalidoDevuelveValidationErrorYNoLlamaRepo() {
            Tenista invalido = construirTenistaInvalido();
            // Verifica que sea un Either.left y que el error sea del tipo correcto
            Either<TenistaError, Tenista> result = service.update(invalido);
            assertTrue(result.isLeft());
            assertInstanceOf(TenistaError.ValidationError.class, result.getLeft());
            verify(repository, never()).update(any());
        }

        @Test
        @DisplayName("update inexistente devuelve TenistaError.NotFound")
        void updateInexistenteDevuelveNotFound() {
            Tenista t = construirTenistaValido(50);
            when(repository.update(any(Tenista.class))).thenReturn(Optional.empty());
            // Verifica que sea un Either.left y que el error sea del tipo correcto
            Either<TenistaError, Tenista> result = service.update(t);
            assertTrue(result.isLeft());
            assertInstanceOf(TenistaError.NotFound.class, result.getLeft());
        }

        @Test
        @DisplayName("deleteById que no borra devuelve TenistaError.NotFound")
        void deleteByIdQueNoBorraDevuelveNotFound() {
            when(repository.deleteById(7L)).thenReturn(false);
            // Verifica que sea un Either.left y que el error sea del tipo correcto
            Either<TenistaError.NotFound, Boolean> result = service.deleteById(7L);
            assertTrue(result.isLeft());
            assertInstanceOf(TenistaError.NotFound.class, result.getLeft());
        }

        @Test
        @DisplayName("importFromCsv con tenista inválido devuelve TenistaError.ValidationError y no guarda")
        void importFromCsvConTenistaInvalidoDevuelveValidation() throws Exception {
            // Lista con SOLO un tenista inválido para asegurar que no se guarda nada
            List<Tenista> lista = List.of(construirTenistaInvalido());
            when(storage.loadData("datos.csv")).thenReturn(lista);

            // Verifica que sea un Either.left y que el error sea del tipo correcto
            Either<TenistaError, Void> result = service.importFromCsv("datos.csv");
            assertTrue(result.isLeft());
            assertInstanceOf(TenistaError.ValidationError.class, result.getLeft());
            verify(repository, never()).save(any());
        }

        @Test
        @DisplayName("exportToJson cuando storage falla devuelve TenistaError.StorageError")
        void exportToJsonStorageFallaDevuelveError() throws Exception {
            List<Tenista> lista = List.of(construirTenistaValido(1));
            when(repository.findAll()).thenReturn(lista);
            when(storage.saveData(eq(lista), eq("salida.json"))).thenThrow(new RuntimeException("error"));

            // Verifica que sea un Either.left y que el error sea del tipo correcto
            Either<TenistaError.StorageError, Void> result = service.exportToJson("salida.json");
            assertTrue(result.isLeft());
            assertInstanceOf(TenistaError.StorageError.class, result.getLeft());
        }
    }
}