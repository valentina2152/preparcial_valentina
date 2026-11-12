package parqueadero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reportes {

    private static final String TOTAL_RECAUDADO = "total_recaudado";
    private final CalculadoraTarifas calculadora = new CalculadoraTarifas();

    public Map<String, Integer> resumen(List<Registro> registros) {
        Map<String, Integer> resumen = new HashMap<>(
                Map.of("carro", 0, "moto", 0, TOTAL_RECAUDADO, 0));
        for (Registro registro : registros) {
            resumen.merge(registro.tipo(), 1, Integer::sum);
            int tarifa = calculadora.calcularTarifa(registro.minutos(), registro.tipo());
            resumen.merge(TOTAL_RECAUDADO, tarifa, Integer::sum);
        }
        return resumen;
    }
}
