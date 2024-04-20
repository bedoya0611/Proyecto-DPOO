package usuarios;

import ventas.Venta;
import ventas.VentaFija;
import ventas.Subasta;


public class Cajero extends Empleado {
    private String nombre;
    private int identificador;

    public Cajero(String login, String password, String nombre, int identificador) {
        super(login, password, nombre, identificador, "Cajero");
    }

    public void registrarPago(Venta venta, double montoPagado) {
        if  (venta.TIPO_VENTA.equals("Fija")){
            if (montoPagado == ((VentaFija) venta).getPrecioFijo()) {
                venta.setPagoHecho(true);
                System.out.println("Pago registrado correctamente para la pieza: " + venta.getPieza().getTitulo());
            } else {
                System.out.println("Monto insuficiente para completar la venta de la pieza: " + venta.getPieza().getTitulo());
            }
        } else {
            if (montoPagado >= ((Subasta) venta).getValorMinimo()) {
                venta.setPagoHecho(true);
                System.out.println("Pago registrado correctamente para la pieza: " + venta.getPieza().getTitulo());
            } else {
                System.out.println("Monto insuficiente para completar la venta de la pieza: " + venta.getPieza().getTitulo());
            }

        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getidentificador() {
        return identificador;
    }

    public void setidentificador(int identificador) {
        this.identificador = identificador;
    }
}


