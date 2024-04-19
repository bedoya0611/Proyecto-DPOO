package usuarios;

public class Operador extends Usuario {
    private String nombre;
    private String identificacion;

    // Constructor
    public Operador(String login, String password, String nombre, String identificacion) {
        super(login, password);
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public void registrarSubasta(Subasta subasta, Subastas listaDeSubastas) {
        listaDeSubastas.agregarSubasta(subasta);
        System.out.println("Subasta registrada con éxito para la pieza: " + subasta.getPieza().getTitulo());
    }

    public void realizarSubasta(Subasta subasta) {
        subasta.iniciarSubasta();
        System.out.println("Subasta iniciada para la pieza: " + subasta.getPieza().getTitulo());
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
