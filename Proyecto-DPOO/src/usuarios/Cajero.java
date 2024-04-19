package usuarios;

public class Cajero extends Usuario {
    private String nombre;
    private String identificacion;

    public Cajero(String login, String password, String nombre, String identificacion) {
        super(login, password);
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public void registrarPago(Venta venta, double montoPagado) {
        if (montoPagado == venta.getPrecioPieza()) {
            venta.setPagoHecho(true);
            System.out.println("Pago registrado correctamente para la pieza: " + venta.getPieza().getTitulo());
        } else {
            System.out.println("Monto insuficiente para completar la venta de la pieza: " + venta.getPieza().getTitulo());
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}


