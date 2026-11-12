package parqueadero.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import parqueadero.CalculadoraTarifas;

/** Pruebas unitarias iniciales de calcularTarifa. Están incompletas a propósito. */
class CalculadoraTarifasTest {

    private final CalculadoraTarifas calculadora = new CalculadoraTarifas();

    @ParameterizedTest(name = "{0} minutos en {1} cuestan {2} pesos")
    @CsvSource({
        "0, carro, 0", "0, moto, 0",
        "1, carro, 0", "1, moto, 0",
        "14, carro, 0", "14, moto, 0",
        "15, carro, 0", "15, moto, 0",
        "16, carro, 3000", "16, moto, 1500",
        "59, carro, 3000", "59, moto, 1500",
        "60, carro, 3000", "60, moto, 1500",
        "61, carro, 6000", "61, moto, 3000",
        "479, carro, 24000", "480, carro, 24000", "481, carro, 25000",
        "419, moto, 10500", "420, moto, 10500", "421, moto, 12000",
        "480, moto, 12000", "481, moto, 12000",
        "540, carro, 25000", "541, carro, 25000",
        "1439, carro, 25000", "1440, carro, 25000",
        "1439, moto, 12000", "1440, moto, 12000"
    })
    void cobraLaTarifaEsperadaEnLosLimites(int minutos, String tipo, int esperado) {
        assertEquals(esperado, calculadora.calcularTarifa(minutos, tipo));
    }

    @ParameterizedTest
    @CsvSource({"-1, carro", "-1, moto", "1441, carro", "1441, moto"})
    void rechazaMinutosFueraDelRango(int minutos, String tipo) {
        assertEquals("Los minutos deben estar entre 0 y 1440",
                assertThrows(IllegalArgumentException.class,
                        () -> calculadora.calcularTarifa(minutos, tipo)).getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"bicicleta", "camion", "", "Carro", " moto "})
    void informaQueElTipoDeVehiculoNoEsValido(String tipo) {
        assertEquals("Tipo de vehículo no válido",
                assertThrows(IllegalArgumentException.class,
                        () -> calculadora.calcularTarifa(30, tipo)).getMessage());
    }

    @Test
    @DisplayName("Un carro con una hora y un minuto paga dos horas")
    void carroConUnaHoraYUnMinutoPagaDosHoras() {
        assertEquals(6000, calculadora.calcularTarifa(61, "carro"));
    }

    @Test
    @DisplayName("Una moto con media hora paga una hora")
    void motoConMediaHoraPagaUnaHora() {
        assertEquals(1500, calculadora.calcularTarifa(30, "moto"));
    }

    @Test
    @DisplayName("Rechaza un tipo de vehículo desconocido")
    void rechazaUnTipoDeVehiculoDesconocido() {
        assertThrows(IllegalArgumentException.class, () -> calculadora.calcularTarifa(30, "bicicleta"));
    }
}
