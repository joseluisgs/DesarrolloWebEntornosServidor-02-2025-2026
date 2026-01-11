package dev.joseluisgs.mappers;

import dev.joseluisgs.dao.TenistaEntity;
import dev.joseluisgs.models.Tenista;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para TenistaMapper.
 * Comprueba las conversiones entre Tenista y TenistaEntity en ambos sentidos.
 * Referencia: dev.joseluisgs.mappers.TenistaMapper
 *
 * @author joseluisgs
 */
@DisplayName("Tests de mapeo de TenistaMapper")
public class TenistaMapperTest {

    private Tenista construirTenistaValido() {
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

    private TenistaEntity construirEntityValida() {
        return TenistaEntity.builder()
                .id(2)
                .nombre("Rafael Nadal")
                .pais("España")
                .altura(185)
                .peso(85)
                .puntos(10000)
                .mano("ZURDO")
                .fechaNacimiento(LocalDate.of(1986, 6, 3))
                .build();
    }

    @Nested
    @DisplayName("Casos positivos")
    class CasosPositivos {
        @Test
        @DisplayName("toEntity con Tenista válido mapea correctamente")
        void toEntityConTenistaValidoMapeaCorrectamente() {
            Tenista tenista = construirTenistaValido();

            TenistaEntity entity = TenistaMapper.toEntity(tenista);

            assertAll("Mapeo a Entity",
                    () -> assertNotNull(entity),
                    () -> assertEquals(tenista.getId(), entity.getId()),
                    () -> assertEquals(tenista.getNombre(), entity.getNombre()),
                    () -> assertEquals(tenista.getPais(), entity.getPais()),
                    () -> assertEquals(tenista.getAltura(), entity.getAltura()),
                    () -> assertEquals(tenista.getPeso(), entity.getPeso()),
                    () -> assertEquals(tenista.getPuntos(), entity.getPuntos()),
                    () -> assertEquals(tenista.getMano().name(), entity.getMano()),
                    () -> assertEquals(tenista.getFechaNacimiento(), entity.getFechaNacimiento())
            );
        }

        @Test
        @DisplayName("fromEntity con Entity válida mapea correctamente")
        void fromEntityConEntityValidaMapeaCorrectamente() {
            TenistaEntity entity = construirEntityValida();

            Tenista tenista = TenistaMapper.fromEntity(entity);

            assertAll("Mapeo desde Entity",
                    () -> assertNotNull(tenista),
                    () -> assertEquals(entity.getId(), tenista.getId()),
                    () -> assertEquals(entity.getNombre(), tenista.getNombre()),
                    () -> assertEquals(entity.getPais(), tenista.getPais()),
                    () -> assertEquals(entity.getAltura(), tenista.getAltura()),
                    () -> assertEquals(entity.getPeso(), tenista.getPeso()),
                    () -> assertEquals(entity.getPuntos(), tenista.getPuntos()),
                    () -> assertEquals(Tenista.Mano.ZURDO, tenista.getMano()),
                    () -> assertEquals(entity.getFechaNacimiento(), tenista.getFechaNacimiento())
            );
        }

        @Test
        @DisplayName("fromEntity con mano en minúsculas mapea enum correctamente")
        void fromEntityConManoEnMinusculasMapeaEnumCorrectamente() {
            TenistaEntity entity = TenistaEntity.builder()
                    .id(3)
                    .nombre("Novak Djokovic")
                    .pais("Serbia")
                    .altura(188)
                    .peso(77)
                    .puntos(11000)
                    .mano("zurdo") // minúsculas para comprobar toUpperCase()
                    .fechaNacimiento(LocalDate.of(1987, 5, 22))
                    .build();

            Tenista tenista = TenistaMapper.fromEntity(entity);

            assertAll("Enum desde minúsculas",
                    () -> assertNotNull(tenista),
                    () -> assertEquals(Tenista.Mano.ZURDO, tenista.getMano())
            );
        }
    }

    @Nested
    @DisplayName("Casos negativos")
    class CasosNegativos {
        @Test
        @DisplayName("toEntity con Tenista null devuelve null")
        void toEntityConTenistaNullDevuelveNull() {
            assertNull(TenistaMapper.toEntity(null));
        }

        @Test
        @DisplayName("fromEntity con Entity null devuelve null")
        void fromEntityConEntityNullDevuelveNull() {
            assertNull(TenistaMapper.fromEntity(null));
        }
    }
}
