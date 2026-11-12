package parqueadero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reportes {

    private static final String TOTAL_RECAUDADO = "total_recaudado";
    private final CalculadoraTarifas calculadora = new CalculadoraTarifas();

    public Map<String, Integer> resumen(List<Registro> registros) {
        Map<String, Integer> resumen = new HashMap<>();
        resumen.put("carro", 0);
        resumen.put("moto", 0);
        resumen.put(TOTAL_RECAUDADO, 0);

        for (Registro registro : registros) {
            String tipo = registro.tipo();
            int cantidad = resumen.get(tipo);
            resumen.put(tipo, cantidad + 1);

            int tarifa = calculadora.calcularTarifa(registro.minutos(), registro.tipo());
            int total = resumen.get(TOTAL_RECAUDADO);
            resumen.put(TOTAL_RECAUDADO, total + tarifa);
        }
        return resumen;
    }
}
