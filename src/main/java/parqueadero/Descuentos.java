package parqueadero;

import java.util.Map;

/** Descuentos por tipo de cliente, redondeados hacia abajo al peso entero. */
public class Descuentos {
    private static final int PORCENTAJE_TOTAL = 100;
    private static final Map<String, Integer> PORCENTAJES = Map.of(
            "ninguno", 0, "frecuente", 10, "convenio", 20);

    public int aplicarDescuento(int valor, String tipoCliente) {
        if (!PORCENTAJES.containsKey(tipoCliente)) {
            throw new IllegalArgumentException("Tipo de cliente no válido");
        }
        if (valor < 0) {
            throw new IllegalArgumentException("El valor no puede ser negativo");
        }
        int porcentajeAPagar = PORCENTAJE_TOTAL - PORCENTAJES.get(tipoCliente);
        return (int) ((long) valor * porcentajeAPagar / PORCENTAJE_TOTAL);
    }
}
