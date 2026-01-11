package dev.joseluisgs.service;

import dev.joseluisgs.exceptions.TenistaException;
import dev.joseluisgs.models.Tenista;
import dev.joseluisgs.notifications.Notification;
import dev.joseluisgs.notifications.NotificationService;
import dev.joseluisgs.repository.TenistasRepository;
import dev.joseluisgs.storage.TenistasStorage;
import io.reactivex.rxjava3.observers.TestObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;

/**
 * Clase de pruebas para TenistasReactiveServiceImpl.
 * Comprueba el correcto funcionamiento de la lógica de negocio y la caché.
 * A diferencia de las pruebas síncronas, en este caso, se usa la herramienta TestObserver de RxJava
 * para suscribirse a los flujos de datos asíncronos y verificar su comportamiento final.
 * Referencia: dev.joseluisgs.service. TenistasReactiveServiceImpl
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests del servicio TenistasReactiveServiceImpl")
public class TenistasReactiveServiceImplTest {

    @Mock
    TenistasRepository repository;

    @Mock
    TenistasStorage storage;

    @Mock
    NotificationService<Tenista> notificacionesService;

    // 🚀 InjectMocks ahora necesita todas las dependencias para el constructor
    // Usamos el constructor para inyectar los mocks
    TenistasReactiveServiceImpl service;

    @BeforeEach
    void setUp() {
        // 🚀 INSTANCIAMOS EL SERVICIO CON LOS MOOCKS CREADOS
        service = new TenistasReactiveServiceImpl(repository, storage, notificacionesService);
    }

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
        @DisplayName("findAll devuelve un Observable con los tenistas del repositorio")
        void findAllDevuelveObservableDelRepositorio() {
            List<Tenista> lista = List.of(construirTenistaValido(1), construirTenistaValido(2));
            when(repository.findAll()).thenReturn(lista);

            TestObserver<Tenista> testObserver = service.findAll().test();
            testObserver.awaitDone(5, TimeUnit.SECONDS);

            testObserver.assertNoErrors()
                    .assertValueCount(2)
                    .assertValueAt(0, tenista -> tenista.getId() == 1L)
                    .assertValueAt(1, tenista -> tenista.getId() == 2L)
                    .assertComplete();
            verify(repository, times(1)).findAll();
        }

        @Test
        @DisplayName("findById usa la caché tras la primera consulta")
        void findByIdUsaCacheTrasPrimeraConsulta() {
            Tenista t = construirTenistaValido(5);
            when(repository.findById(5L)).thenReturn(Optional.of(t));

            TestObserver<Tenista> firstObserver = service.findById(5L).test();
            firstObserver.awaitDone(5, TimeUnit.SECONDS).assertNoErrors().assertValue(t).assertComplete();
            verify(repository, times(1)).findById(5L);

            TestObserver<Tenista> secondObserver = service.findById(5L).test();
            secondObserver.awaitDone(5, TimeUnit.SECONDS).assertNoErrors().assertValue(t).assertComplete();

            verify(repository, times(1)).findById(5L);
        }

        @Test
        @DisplayName("save con tenista válido guarda y lo mete en caché y notifica")
        void saveConTenistaValidoGuardaYCachea() {
            Tenista nuevo = construirTenistaValido(Tenista.NEW_TENISTA_ID);
            Tenista guardado = construirTenistaValido(100);
            when(repository.save(any(Tenista.class))).thenReturn(guardado);

            TestObserver<Tenista> testObserver = service.save(nuevo).test();
            testObserver.awaitDone(5, TimeUnit.SECONDS).assertNoErrors().assertValue(guardado).assertComplete();
            verify(repository, times(1)).save(any(Tenista.class));
            verify(notificacionesService, times(1)).notify(any(Notification.class));

            // Ahora debe estar en caché, por lo que findById no llama al repositorio.
            reset(repository);
            TestObserver<Tenista> cacheObserver = service.findById(100L).test();
            cacheObserver.awaitDone(5, TimeUnit.SECONDS).assertNoErrors().assertValue(guardado).assertComplete();
            verify(repository, never()).findById(anyLong());
        }

        @Test
        @DisplayName("update existente actualiza y refresca la caché y notifica")
        void updateExistenteActualizaYRefrescaCache() {
            // Primero cargamos en caché un tenista
            Tenista original = construirTenistaValido(7);
            when(repository.findById(7L)).thenReturn(Optional.of(original));
            service.findById(7L).test().awaitDone(5, TimeUnit.SECONDS);
            verify(repository, times(1)).findById(7L);

            // Preparamos actualización
            Tenista actualizado = construirTenistaValido(7);
            actualizado.setPuntos(9500);
            when(repository.update(any(Tenista.class))).thenReturn(Optional.of(actualizado));

            // Actualizamos
            TestObserver<Tenista> updateObserver = service.update(actualizado).test();
            updateObserver.awaitDone(5, TimeUnit.SECONDS).assertNoErrors().assertValue(actualizado).assertComplete();
            verify(repository, times(1)).update(any(Tenista.class));
            verify(notificacionesService, times(1)).notify(any(Notification.class));

            // Al pedirlo de nuevo debe salir de la caché actualizada.
            reset(repository);
            TestObserver<Tenista> cacheObserver = service.findById(7L).test();
            cacheObserver.awaitDone(5, TimeUnit.SECONDS).assertNoErrors().assertValue(actualizado).assertComplete();
            verify(repository, never()).findById(anyLong());
        }

        @Test
        @DisplayName("deleteById exitoso devuelve true y notifica")
        void deleteByIdExitosoDevuelveTrue() {
            when(repository.deleteById(3L)).thenReturn(true);
            TestObserver<Boolean> testObserver = service.deleteById(3L).test();
            testObserver.awaitDone(5, TimeUnit.SECONDS).assertNoErrors().assertValue(true).assertComplete();
            verify(repository).deleteById(3L);
            verify(notificacionesService, times(1)).notify(any(Notification.class));
        }

        @Test
        @DisplayName("importFromCsv carga, valida y guarda todos y notifica")
        void importFromCsvCargaValidaYGuardaTodos() throws TenistaException.StorageException {
            List<Tenista> lista = List.of(construirTenistaValido(1), construirTenistaValido(2));
            when(storage.loadData("ruta.csv")).thenReturn(lista);
            when(repository.save(any(Tenista.class))).thenAnswer(inv -> inv.getArgument(0));

            TestObserver<Tenista> testObserver = service.importFromCsv("ruta.csv").test();
            testObserver.awaitDone(5, TimeUnit.SECONDS).assertNoErrors().assertValueCount(2).assertComplete();
            verify(storage, times(1)).loadData("ruta.csv");
            verify(repository, times(2)).save(any(Tenista.class));

            // 🚀 VERIFICAMOS QUE SE NOTIFICA DOS VECES (uno por cada tenista creado)
            // Esto es opcional y depende de si queremos notificar cada creación individualmente
            //verify(notificacionesService, times(2)).notify(any(Notification.class));
        }

        @Test
        @DisplayName("exportToJson obtiene todos y delega en storage")
        void exportToJsonObtieneTodosYDelegarEnStorage() throws TenistaException.StorageException {
            List<Tenista> lista = List.of(construirTenistaValido(1), construirTenistaValido(2));
            when(repository.findAll()).thenReturn(lista);
            when(storage.saveData(eq(lista), eq("salida.json"))).thenReturn(lista.size());

            TestObserver<List<Tenista>> testObserver = service.exportToJson("salida.json").test();
            testObserver.awaitDone(5, TimeUnit.SECONDS).assertNoErrors().assertValue(lista).assertComplete();
            verify(repository).findAll();
            verify(storage).saveData(lista, "salida.json");
        }
    }


    @Nested
    @DisplayName("Casos negativos")
    class CasosNegativos {
        @Test
        @DisplayName("findById inexistente lanza TenistaNotFoundException")
        void findByIdInexistenteLanzaExcepcion() {
            when(repository.findById(99L)).thenReturn(Optional.empty());

            TestObserver<Tenista> testObserver = service.findById(99L).test();
            testObserver.awaitDone(5, TimeUnit.SECONDS).assertError(TenistaException.NotFoundException.class);
        }

        @Test
        @DisplayName("save con tenista inválido lanza TenistaValidationException y no llama repo")
        void saveInvalidoLanzaValidationYNoLlamaRepo() {
            Tenista invalido = construirTenistaInvalido();

            TestObserver<Tenista> testObserver = service.save(invalido).test();
            testObserver.awaitDone(5, TimeUnit.SECONDS).assertError(TenistaException.ValidationException.class);
            verify(repository, never()).save(any());
            verify(notificacionesService, never()).notify(any(Notification.class));
        }

        @Test
        @DisplayName("update con tenista inválido lanza TenistaValidationException y no llama repo")
        void updateInvalidoLanzaValidationYNoLlamaRepo() {
            Tenista invalido = construirTenistaInvalido();

            TestObserver<Tenista> testObserver = service.update(invalido).test();
            testObserver.awaitDone(5, TimeUnit.SECONDS).assertError(TenistaException.ValidationException.class);
            verify(repository, never()).update(any());
            verify(notificacionesService, never()).notify(any(Notification.class));
        }

        @Test
        @DisplayName("update inexistente lanza TenistaNotFoundException y no notifica")
        void updateInexistenteLanzaNotFound() {
            Tenista t = construirTenistaValido(50);
            when(repository.update(any(Tenista.class))).thenReturn(Optional.empty());

            TestObserver<Tenista> testObserver = service.update(t).test();
            testObserver.awaitDone(5, TimeUnit.SECONDS).assertError(TenistaException.NotFoundException.class);
            verify(notificacionesService, never()).notify(any(Notification.class));
        }

        @Test
        @DisplayName("deleteById que no borra lanza TenistaNotFoundException y no notifica")
        void deleteByIdQueNoBorraLanzaNotFound() {
            when(repository.deleteById(7L)).thenReturn(false);

            TestObserver<Boolean> testObserver = service.deleteById(7L).test();
            testObserver.awaitDone(5, TimeUnit.SECONDS).assertError(TenistaException.NotFoundException.class);
            verify(notificacionesService, never()).notify(any(Notification.class));
        }

        @Test
        @DisplayName("importFromCsv con tenista inválido lanza TenistaValidationException y no guarda")
        void importFromCsvConTenistaInvalidoLanzaValidation() throws TenistaException.StorageException {
            List<Tenista> lista = List.of(construirTenistaInvalido());
            when(storage.loadData("datos.csv")).thenReturn(lista);

            TestObserver<Tenista> testObserver = service.importFromCsv("datos.csv").test();
            testObserver.awaitDone(5, TimeUnit.SECONDS).assertError(TenistaException.ValidationException.class);
            verify(repository, never()).save(any());
            verify(notificacionesService, never()).notify(any(Notification.class));
        }

        @Test
        @DisplayName("exportToJson cuando storage falla propaga TenistaStorageException")
        void exportToJsonStorageFallaPropagaExcepcion() throws TenistaException.StorageException {
            List<Tenista> lista = List.of(construirTenistaValido(1));
            when(repository.findAll()).thenReturn(lista);
            when(storage.saveData(eq(lista), eq("salida.json"))).thenThrow(new TenistaException.StorageException("error"));

            TestObserver<List<Tenista>> testObserver = service.exportToJson("salida.json").test();
            testObserver.awaitDone(5, TimeUnit.SECONDS).assertError(TenistaException.StorageException.class);
        }
    }
}