package parqueadero.unitarias;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import parqueadero.Descuentos;

class DescuentosTest {
    private final Descuentos descuentos = new Descuentos();

    @Test
    void clienteSinDescuentoPagaElValorCompleto() {
        assertEquals(3000, descuentos.aplicarDescuento(3000, "ninguno"));
    }

    @Test
    void clienteFrecuenteRecibeDiezPorCiento() {
        assertEquals(2700, descuentos.aplicarDescuento(3000, "frecuente"));
    }

    @Test
    void clienteConConvenioRecibeVeintePorCiento() {
        assertEquals(20000, descuentos.aplicarDescuento(25000, "convenio"));
    }
}
