package dev.joseluisgs.service;

import dev.joseluisgs.exceptions.TenistaException;
import dev.joseluisgs.models.Tenista;
import dev.joseluisgs.repository.TenistasRepository;
import dev.joseluisgs.storage.TenistasStorage;
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
import java.util.concurrent.CompletionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Clase de pruebas para TenistasServiceAsyncImpl.
 * Comprueba el correcto funcionamiento de la lógica de negocio y la caché.
 * Referencia: dev.joseluisgs.service.TenistasServiceAsyncImpl
 *
 * @author joseluisgs
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests del servicio TenistasServiceAsyncImpl")
public class TenistasServiceAsyncImplTest {

    @Mock
    TenistasRepository repository;

    @Mock
    TenistasStorage storage;

    @InjectMocks
    TenistasServiceAsyncImpl service;

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

            List<Tenista> res = service.findAllAsync().join();

            assertAll("Lista del servicio",
                    () -> assertNotNull(res),
                    () -> assertEquals(2, res.size()),
                    () -> assertEquals(1, res.get(0).getId()),
                    () -> assertEquals("Carlos Alcaraz", res.get(0).getNombre())
            );
            verify(repository, times(1)).findAll();
        }

        @Test
        @DisplayName("findById usa la caché si un tenista fue guardado previamente")
        void findByIdUsaCacheTrasGuardar() {
            // ARREGLO: Este test se reescribe para probar la caché correctamente.
            // Dado que findByIdAsync() no guarda en caché, usamos saveAsync() que sí lo hace.

            // 1. Preparamos los datos para guardar un tenista
            Tenista aGuardar = construirTenistaValido(Tenista.NEW_TENISTA_ID);
            Tenista guardado = construirTenistaValido(99L);
            when(repository.save(aGuardar)).thenReturn(guardado);

            // 2. Guardamos el tenista. Esta operación SÍ lo mete en la caché.
            service.saveAsync(aGuardar).join();

            // 3. Ahora, al buscar el tenista recién guardado, debería usar la caché.
            Tenista desdeCache = service.findByIdAsync(99L).join();

            // 4. Verificamos los resultados
            assertAll("Cache tras save",
                    () -> assertNotNull(desdeCache),
                    () -> assertEquals(99L, desdeCache.getId()),
                    () -> assertSame(guardado, desdeCache) // Debe ser la misma instancia
            );

            // La verificación clave: repository.findById() NUNCA debe haber sido llamado.
            verify(repository, never()).findById(99L);
            // repository.save() sí fue llamado una vez.
            verify(repository, times(1)).save(aGuardar);
        }

        @Test
        @DisplayName("save con tenista válido guarda y lo mete en caché")
        void saveConTenistaValidoGuardaYCachea() {
            Tenista nuevo = construirTenistaValido(Tenista.NEW_TENISTA_ID);
            Tenista guardado = construirTenistaValido(100);
            when(repository.save(any(Tenista.class))).thenReturn(guardado);

            Tenista res = service.saveAsync(nuevo).join();

            assertAll("Guardado",
                    () -> assertNotNull(res),
                    () -> assertEquals(100L, res.getId()),
                    () -> assertEquals(nuevo.getNombre(), res.getNombre())
            );

            // Ahora debe estar en caché, por lo que findById no llama al repositorio.
            // No necesitamos resetear el mock si solo queremos verificar que no hay *nuevas* interacciones.
            Tenista desdeCache = service.findByIdAsync(100L).join();
            assertAll("Cache tras save",
                    () -> assertNotNull(desdeCache),
                    () -> assertEquals(100L, desdeCache.getId())
            );
            // Verificamos que findById no se ha invocado NUNCA
            verify(repository, never()).findById(anyLong());
        }

        @Test
        @DisplayName("update existente actualiza y refresca la caché")
        void updateExistenteActualizaYRefrescaCache() {
            // Preparamos actualización
            Tenista actualizado = construirTenistaValido(7);
            actualizado.setPuntos(9500);
            // Cuando el servicio llame a repository.update, le devolveremos el tenista actualizado
            when(repository.update(any(Tenista.class))).thenReturn(Optional.of(actualizado));

            Tenista res = service.updateAsync(actualizado).join();
            assertAll("Actualizado",
                    () -> assertNotNull(res),
                    () -> assertEquals(9500, res.getPuntos())
            );

            // Al pedirlo de nuevo debe salir de la caché actualizada sin ir al repo
            Tenista desdeCache = service.findByIdAsync(7L).join();
            assertAll("Cache tras update",
                    () -> assertNotNull(desdeCache),
                    () -> assertEquals(9500, desdeCache.getPuntos())
            );
            // findById no debería haber sido llamado, ya que el primer get viene de la caché
            verify(repository, never()).findById(7L);
        }

        @Test
        @DisplayName("deleteById exitoso devuelve true")
        void deleteByIdExitosoDevuelveTrue() {
            when(repository.deleteById(3L)).thenReturn(true);
            boolean ok = service.deleteByIdAsync(3L).join();
            assertTrue(ok);
            verify(repository).deleteById(3L);
        }

        @Test
        @DisplayName("importFromCsv carga, valida y guarda todos")
        void importFromCsvCargaValidaYGuardaTodos() throws TenistaException.StorageException {
            List<Tenista> lista = List.of(construirTenistaValido(1), construirTenistaValido(2));
            when(storage.loadData("ruta.csv")).thenReturn(lista);
            when(repository.save(any(Tenista.class))).thenAnswer(inv -> inv.getArgument(0));

            service.importFromCsvAsync("ruta.csv").join();

            verify(storage, times(1)).loadData("ruta.csv");
            verify(repository, times(2)).save(any(Tenista.class));
        }

        @Test
        @DisplayName("exportToJson obtiene todos y delega en storage")
        void exportToJsonObtieneTodosYDelegarEnStorage() throws TenistaException.StorageException {
            List<Tenista> lista = List.of(construirTenistaValido(1), construirTenistaValido(2));
            when(repository.findAll()).thenReturn(lista);

            // ARREGLO: El método saveData devuelve un valor (probablemente int o long),
            // por lo que no se puede usar doNothing(). Usamos when/thenReturn.
            when(storage.saveData(eq(lista), eq("salida.json"))).thenReturn(lista.size());

            service.exportToJsonAsync("salida.json").join();

            verify(repository).findAll();
            verify(storage).saveData(lista, "salida.json");
        }
    }

    @Nested
    @DisplayName("Casos negativos")
    class CasosNegativos {
        @Test
        @DisplayName("findById inexistente lanza TenistaException.NotFoundException")
        void findByIdInexistenteLanzaExcepcion() {
            when(repository.findById(99L)).thenReturn(Optional.empty());

            // CAMBIO: Capturamos CompletionException y verificamos la causa interna.
            var ex = assertThrows(CompletionException.class,
                    () -> service.findByIdAsync(99L).join());
            assertInstanceOf(TenistaException.NotFoundException.class, ex.getCause());
        }

        @Test
        @DisplayName("save con tenista inválido lanza TenistaException.ValidationException y no llama repo")
        void saveInvalidoLanzaValidationYNoLlamaRepo() {
            Tenista invalido = construirTenistaInvalido();

            // CAMBIO: Capturamos CompletionException y verificamos la causa interna.
            var ex = assertThrows(CompletionException.class,
                    () -> service.saveAsync(invalido).join());
            assertInstanceOf(TenistaException.ValidationException.class, ex.getCause());

            verify(repository, never()).save(any());
        }

        @Test
        @DisplayName("update con tenista inválido lanza TenistaException.ValidationException y no llama repo")
        void updateInvalidoLanzaValidationYNoLlamaRepo() {
            Tenista invalido = construirTenistaInvalido();

            // CAMBIO: Capturamos CompletionException y verificamos la causa interna.
            var ex = assertThrows(CompletionException.class,
                    () -> service.updateAsync(invalido).join());
            assertInstanceOf(TenistaException.ValidationException.class, ex.getCause());

            verify(repository, never()).update(any());
        }

        @Test
        @DisplayName("update inexistente lanza TenistaException.NotFoundException")
        void updateInexistenteLanzaNotFound() {
            Tenista t = construirTenistaValido(50);
            when(repository.update(any(Tenista.class))).thenReturn(Optional.empty());

            // CAMBIO: Capturamos CompletionException y verificamos la causa interna.
            var ex = assertThrows(CompletionException.class,
                    () -> service.updateAsync(t).join());
            assertInstanceOf(TenistaException.NotFoundException.class, ex.getCause());
        }

        @Test
        @DisplayName("deleteById que no borra lanza TenistaException.NotFoundException")
        void deleteByIdQueNoBorraLanzaNotFound() {
            when(repository.deleteById(7L)).thenReturn(false);

            // CAMBIO: Capturamos CompletionException y verificamos la causa interna.
            var ex = assertThrows(CompletionException.class,
                    () -> service.deleteByIdAsync(7L).join());
            assertInstanceOf(TenistaException.NotFoundException.class, ex.getCause());
        }

        @Test
        @DisplayName("importFromCsv con tenista inválido no lanza excepción y no lo guarda")
        void importFromCsvConTenistaInvalidoNoLanzaExcepcionYNoGuarda() throws TenistaException.StorageException {
            // CAMBIO: El servicio ahora es resiliente. No lanza excepción, solo loguea el error.
            // Por tanto, el test debe verificar que la operación termina sin error
            // y que el tenista inválido NO se guardó.
            List<Tenista> lista = List.of(construirTenistaInvalido());
            when(storage.loadData("datos.csv")).thenReturn(lista);

            // La llamada debe completarse sin lanzar excepciones
            assertDoesNotThrow(() -> service.importFromCsvAsync("datos.csv").join());

            // Verificamos que el repositorio NUNCA fue llamado para guardar
            verify(repository, never()).save(any());
        }

        @Test
        @DisplayName("exportToJson cuando storage falla propaga TenistaException.StorageException")
        void exportToJsonStorageFallaPropagaExcepcion() throws TenistaException.StorageException {
            List<Tenista> lista = List.of(construirTenistaValido(1));
            when(repository.findAll()).thenReturn(lista);
            // CAMBIO: El servicio envuelve la StorageException en una RuntimeException
            doThrow(new TenistaException.StorageException("error I/O"))
                    .when(storage).saveData(anyList(), anyString());

            var ex = assertThrows(CompletionException.class,
                    () -> service.exportToJsonAsync("salida.json").join());

            // La causa directa es RuntimeException, que a su vez contiene la original
            assertInstanceOf(RuntimeException.class, ex.getCause());
            assertInstanceOf(TenistaException.StorageException.class, ex.getCause().getCause());
        }
    }
}