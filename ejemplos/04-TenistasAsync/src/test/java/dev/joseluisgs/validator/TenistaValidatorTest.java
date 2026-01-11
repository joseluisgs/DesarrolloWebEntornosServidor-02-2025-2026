package dev.joseluisgs.validator;

import dev.joseluisgs.exceptions.TenistaException;
import dev.joseluisgs.models.Tenista;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para TenistaValidator.
 * Verifica que las reglas de validación sobre el modelo Tenista se aplican correctamente.
 * Referencia: dev.joseluisgs.validator.TenistaValidator
 *
 * @author joseluisgs
 * @see dev.joseluisgs.validator.TenistaValidator
 */
@DisplayName("Tests de validación de Tenistas")
public class TenistaValidatorTest {

    private Tenista construirTenistaValido() {
        return Tenista.builder()
                .id(1)
                .nombre("Rafael Nadal")
                .pais("España")
                .altura(185)
                .peso(85)
                .puntos(10000)
                .mano(Tenista.Mano.ZURDO)
                .fechaNacimiento(LocalDate.of(1986, 6, 3))
                .build();
    }

    @Nested
    @DisplayName("Casos positivos")
    class CasosPositivos {
        @Test
        @DisplayName("Validar tenista válido no lanza excepción")
        void validarTenistaValidoNoLanzaExcepcion() {
            Tenista tenista = construirTenistaValido();
            assertDoesNotThrow(() -> TenistaValidator.validate(tenista));
        }
    }

    @Nested
    @DisplayName("Casos negativos")
    class CasosNegativos {
        @Test
        @DisplayName("Validar tenista nulo lanza excepción")
        void validarTenistaNuloLanzaExcepcion() {
            assertThrows(TenistaException.ValidationException.class, () -> TenistaValidator.validate(null));
        }

        @Test
        @DisplayName("Nombre en blanco lanza excepción")
        void nombreEnBlancoLanzaExcepcion() {
            Tenista tenista = construirTenistaValido();
            tenista.setNombre(" ");
            TenistaException.ValidationException ex = assertThrows(TenistaException.ValidationException.class,
                    () -> TenistaValidator.validate(tenista));
            assertTrue(ex.getMessage().contains("nombre"));
        }

        @Test
        @DisplayName("Nombre null lanza excepción")
        void nombreNullLanzaExcepcion() {
            Tenista tenista = construirTenistaValido();
            tenista.setNombre(null);
            TenistaException.ValidationException ex = assertThrows(TenistaException.ValidationException.class,
                    () -> TenistaValidator.validate(tenista));
            assertTrue(ex.getMessage().toLowerCase().contains("nombre"));
        }

        @Test
        @DisplayName("País en blanco lanza excepción")
        void paisEnBlancoLanzaExcepcion() {
            Tenista tenista = construirTenistaValido();
            tenista.setPais("");
            TenistaException.ValidationException ex = assertThrows(TenistaException.ValidationException.class,
                    () -> TenistaValidator.validate(tenista));
            assertTrue(ex.getMessage().contains("país") || ex.getMessage().contains("pais"));
        }

        @Test
        @DisplayName("País null lanza excepción")
        void paisNullLanzaExcepcion() {
            Tenista tenista = construirTenistaValido();
            tenista.setPais(null);
            TenistaException.ValidationException ex = assertThrows(TenistaException.ValidationException.class,
                    () -> TenistaValidator.validate(tenista));
            assertTrue(ex.getMessage().toLowerCase().contains("país") || ex.getMessage().toLowerCase().contains("pais"));
        }

        @Test
        @DisplayName("Altura no positiva lanza excepción")
        void alturaNoPositivaLanzaExcepcion() {
            Tenista tenista = construirTenistaValido();
            tenista.setAltura(0);
            TenistaException.ValidationException ex = assertThrows(TenistaException.ValidationException.class,
                    () -> TenistaValidator.validate(tenista));
            assertTrue(ex.getMessage().contains("altura"));
        }

        @Test
        @DisplayName("Altura negativa lanza excepción")
        void alturaNegativaLanzaExcepcion() {
            Tenista tenista = construirTenistaValido();
            tenista.setAltura(-10);
            TenistaException.ValidationException ex = assertThrows(TenistaException.ValidationException.class,
                    () -> TenistaValidator.validate(tenista));
            assertTrue(ex.getMessage().toLowerCase().contains("altura"));
        }

        @Test
        @DisplayName("Peso no positivo lanza excepción")
        void pesoNoPositivoLanzaExcepcion() {
            Tenista tenista = construirTenistaValido();
            tenista.setPeso(-1);
            TenistaException.ValidationException ex = assertThrows(TenistaException.ValidationException.class,
                    () -> TenistaValidator.validate(tenista));
            assertTrue(ex.getMessage().contains("peso"));
        }

        @Test
        @DisplayName("Peso cero lanza excepción")
        void pesoCeroLanzaExcepcion() {
            Tenista tenista = construirTenistaValido();
            tenista.setPeso(0);
            TenistaException.ValidationException ex = assertThrows(TenistaException.ValidationException.class,
                    () -> TenistaValidator.validate(tenista));
            assertTrue(ex.getMessage().toLowerCase().contains("peso"));
        }

        @Test
        @DisplayName("Puntos negativos lanzan excepción")
        void puntosNegativosLanzanExcepcion() {
            Tenista tenista = construirTenistaValido();
            tenista.setPuntos(-5);
            TenistaException.ValidationException ex = assertThrows(TenistaException.ValidationException.class,
                    () -> TenistaValidator.validate(tenista));
            assertTrue(ex.getMessage().contains("puntos"));
        }

        @Test
        @DisplayName("Mano nula lanza excepción")
        void manoNulaLanzaExcepcion() {
            Tenista tenista = construirTenistaValido();
            tenista.setMano(null);
            TenistaException.ValidationException ex = assertThrows(TenistaException.ValidationException.class,
                    () -> TenistaValidator.validate(tenista));
            assertTrue(ex.getMessage().contains("mano"));
        }

        @Test
        @DisplayName("Fecha de nacimiento nula lanza excepción")
        void fechaNacimientoNulaLanzaExcepcion() {
            Tenista tenista = construirTenistaValido();
            tenista.setFechaNacimiento(null);
            TenistaException.ValidationException ex = assertThrows(TenistaException.ValidationException.class,
                    () -> TenistaValidator.validate(tenista));
            assertTrue(ex.getMessage().contains("fecha de nacimiento"));
        }

        @Test
        @DisplayName("Fecha de nacimiento futura lanza excepción")
        void fechaNacimientoFuturaLanzaExcepcion() {
            Tenista tenista = construirTenistaValido();
            tenista.setFechaNacimiento(LocalDate.now().plusDays(1));
            TenistaException.ValidationException ex = assertThrows(TenistaException.ValidationException.class,
                    () -> TenistaValidator.validate(tenista));
            assertTrue(ex.getMessage().contains("futura"));
        }
    }
}