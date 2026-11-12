package parqueadero.bdd;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;

import parqueadero.CalculadoraTarifas;
import parqueadero.Descuentos;

public class TarifasSteps {
    private String tipoVehiculo;
    private int minutos;
    private int valorAPagar;

    @Dado("que un {string} permanece {int} minutos en el parqueadero")
    public void registrarEstadia(String tipo, int duracion) {
        tipoVehiculo = tipo;
        minutos = duracion;
    }

    @Cuando("se calcula el cobro de la estadía")
    public void calcularCobro() {
        valorAPagar = new CalculadoraTarifas().calcularTarifa(minutos, tipoVehiculo);
    }

    @Cuando("se aplica el descuento de cliente {string}")
    public void aplicarDescuento(String cliente) {
        valorAPagar = new Descuentos().aplicarDescuento(valorAPagar, cliente);
    }

    @Entonces("debe pagar {int} pesos")
    public void verificarCobro(int esperado) {
        assertEquals(esperado, valorAPagar);
    }
}
