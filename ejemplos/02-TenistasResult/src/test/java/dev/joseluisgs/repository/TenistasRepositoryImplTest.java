package dev.joseluisgs.repository;

import dev.joseluisgs.dao.TenistaEntity;
import dev.joseluisgs.dao.TenistasDao;
import dev.joseluisgs.models.Tenista;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Clase de pruebas para TenistasRepositoryImpl.
 * Valida la delegación al DAO y el mapeo entre entidades y modelo.
 * Referencia: dev.joseluisgs.repository.TenistasRepositoryImpl
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests del repositorio TenistasRepositoryImpl")
public class TenistasRepositoryImplTest {

    @Mock
    TenistasDao tenistasDao;

    @InjectMocks
    TenistasRepositoryImpl repository;

    private Tenista construirTenistaDominio() {
        return Tenista.builder()
                .id(1)
                .nombre("Carlos Alcaraz")
                .pais("España")
                .altura(183)
                .peso(74)
                .puntos(9000)
                .mano(Tenista.Mano.DIESTRO)
                .fechaNacimiento(LocalDate.of(2003, 5, 5))
                .build();
    }

    private TenistaEntity construirEntity(long id) {
        return TenistaEntity.builder()
                .id(id)
                .nombre("Carlos Alcaraz")
                .pais("España")
                .altura(183)
                .peso(74)
                .puntos(9000)
                .mano("DIESTRO")
                .fechaNacimiento(LocalDate.of(2003, 5, 5))
                .build();
    }

    @Nested
    @DisplayName("Casos positivos")
    class CasosPositivos {
        @Test
        @DisplayName("findAll devuelve la lista mapeada correctamente")
        void findAllDevuelveListaMapeadaCorrectamente() {
            when(tenistasDao.findAll()).thenReturn(List.of(
                    construirEntity(1),
                    construirEntity(2)
            ));

            List<Tenista> res = repository.findAll();

            assertAll("Lista mapeada",
                    () -> assertNotNull(res),
                    () -> assertEquals(2, res.size()),
                    () -> assertEquals(1, res.get(0).getId()),
                    () -> assertEquals("Carlos Alcaraz", res.get(0).getNombre()),
                    () -> assertEquals(Tenista.Mano.DIESTRO, res.get(0).getMano())
            );
            verify(tenistasDao, times(1)).findAll();
        }

        @Test
        @DisplayName("findById existente devuelve Optional con tenista mapeado")
        void findByIdExistenteDevuelveOptionalConTenistaMapeado() {
            when(tenistasDao.findById(1L)).thenReturn(Optional.of(construirEntity(1)));

            Optional<Tenista> res = repository.findById(1L);

            assertAll("Optional con tenista",
                    () -> assertTrue(res.isPresent()),
                    () -> assertEquals(1L, res.get().getId()),
                    () -> assertEquals("España", res.get().getPais())
            );
            verify(tenistasDao).findById(1L);
        }

        @Test
        @DisplayName("save delega en el DAO y devuelve el tenista con id generado")
        void saveDelegayDevuelveTenistaConIdGenerado() {
            Tenista nuevo = construirTenistaDominio();
            nuevo.setId(Tenista.NEW_TENISTA_ID);
            when(tenistasDao.save(any(TenistaEntity.class))).thenReturn(100L);

            Tenista guardado = repository.save(nuevo);

            ArgumentCaptor<TenistaEntity> captor = ArgumentCaptor.forClass(TenistaEntity.class);
            verify(tenistasDao, times(1)).save(captor.capture());
            TenistaEntity enviado = captor.getValue();

            assertAll("Guardar y mapear",
                    () -> assertNotNull(guardado),
                    () -> assertEquals(100L, guardado.getId()),
                    () -> assertEquals(nuevo.getNombre(), guardado.getNombre()),
                    () -> assertEquals(nuevo.getMano(), guardado.getMano()),
                    () -> assertEquals("Carlos Alcaraz", enviado.getNombre()),
                    () -> assertEquals("DIESTRO", enviado.getMano())
            );
        }

        @Test
        @DisplayName("update con filas actualizadas devuelve Optional con el mismo tenista")
        void updateConFilasActualizadasDevuelveOptionalConTenista() {
            when(tenistasDao.update(any(TenistaEntity.class))).thenReturn(1);
            Tenista tenista = construirTenistaDominio();

            Optional<Tenista> res = repository.update(tenista);

            assertAll("Update con éxito",
                    () -> assertTrue(res.isPresent()),
                    () -> assertEquals(tenista, res.get())
            );
            verify(tenistasDao).update(any(TenistaEntity.class));
        }

        @Test
        @DisplayName("deleteById con borrado exitoso devuelve true")
        void deleteByIdExitosoDevuelveTrue() {
            when(tenistasDao.delete(5L)).thenReturn(1);

            boolean ok = repository.deleteById(5L);

            assertTrue(ok);
            verify(tenistasDao).delete(5L);
        }
    }

    @Nested
    @DisplayName("Casos negativos")
    class CasosNegativos {
        @Test
        @DisplayName("findAll vacío devuelve lista vacía")
        void findAllVacioDevuelveListaVacia() {
            when(tenistasDao.findAll()).thenReturn(List.of());

            List<Tenista> res = repository.findAll();

            assertAll("Lista vacía",
                    () -> assertNotNull(res),
                    () -> assertTrue(res.isEmpty())
            );
        }

        @Test
        @DisplayName("findById inexistente devuelve Optional.empty")
        void findByIdInexistenteDevuelveEmpty() {
            when(tenistasDao.findById(99L)).thenReturn(Optional.empty());

            Optional<Tenista> res = repository.findById(99L);

            assertTrue(res.isEmpty());
        }

        @Test
        @DisplayName("update sin filas actualizadas devuelve Optional.empty")
        void updateSinFilasActualizadasDevuelveEmpty() {
            when(tenistasDao.update(any(TenistaEntity.class))).thenReturn(0);
            Tenista tenista = construirTenistaDominio();

            Optional<Tenista> res = repository.update(tenista);

            assertTrue(res.isEmpty());
        }

        @Test
        @DisplayName("deleteById sin borrado devuelve false")
        void deleteByIdSinBorradoDevuelveFalse() {
            when(tenistasDao.delete(7L)).thenReturn(0);

            boolean ok = repository.deleteById(7L);

            assertFalse(ok);
        }
    }
}
