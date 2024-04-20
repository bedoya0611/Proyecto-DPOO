package usuarios;

import ventas.ListaDeSubastas;
import ventas.Subasta;

public class Operador extends Empleado{
    private String nombre;
    private int identificador;

    // Constructor
    public Operador(String login, String password, String nombre, int identificador) {
        super(login, password,nombre,identificador, "Operador");
    }

    public void registrarSubasta(Subasta subasta, ListaDeSubastas listaDeSubastas) {
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

    public int getIdentificador() {
        return identificador;
    }
}
