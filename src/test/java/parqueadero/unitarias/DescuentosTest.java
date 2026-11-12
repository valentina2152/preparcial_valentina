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

    @Test
    void redondeaHaciaAbajoAlPesoEntero() {
        assertEquals(1799, descuentos.aplicarDescuento(1999, "frecuente"));
    }

    @Test
    void valorCeroNoGeneraCobro() {
        assertEquals(0, descuentos.aplicarDescuento(0, "convenio"));
    }

    @Test
    void rechazaValoresNegativos() {
        assertEquals("El valor no puede ser negativo", assertThrows(IllegalArgumentException.class, () -> descuentos.aplicarDescuento(-1, "ninguno")).getMessage());
    }

    @Test
    void rechazaClientesDesconocidos() {
        assertEquals("Tipo de cliente no válido", assertThrows(IllegalArgumentException.class, () -> descuentos.aplicarDescuento(3000, "vip")).getMessage());
    }
}
