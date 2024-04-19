package usuarios;

public class Comprador extends Usuario {
    private String nombre;
    private String identificador;
    private String telefono;
    private boolean verificado;

    public Comprador(String login, String password, String nombre, String identificador, String telefono, boolean verificado) {
        super(login, password);
        this.nombre = nombre;
        this.identificador = identificador;
        this.telefono = telefono;
        this.verificado = verificado;
    }

    public void ofertarCompraDirecta(VentaFija venta) {
        if (verificado) {
            venta.realizarVenta(this);
        } else {
            System.out.println("Comprador no verificado. No se puede realizar la oferta.");
        }
    }

    public void ofertarCompraSubasta(Subasta subasta, double monto) {
        if (verificado) {
            subasta.hacerOferta(this, monto);
        } else {
            System.out.println("Comprador no verificado. No se puede realizar la oferta.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }
}


