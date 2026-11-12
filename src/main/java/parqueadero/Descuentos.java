package parqueadero;

public class Descuentos {
    public int aplicarDescuento(int valor, String tipoCliente) {
        if (tipoCliente.equals("frecuente")) { return valor * 90 / 100; } if (tipoCliente.equals("convenio")) { return valor * 80 / 100; } return valor;
    }
}
