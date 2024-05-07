package galeria.usuarios;

import galeria.ventas.ListaDeSubastas;
import galeria.ventas.Subasta;

public class Operador extends Empleado{

    // Constructor
    public Operador(String login, String password, String nombre, int identificador) {
        super(login, password, nombre ,identificador, "Operador");
    }

    public void registrarSubasta(Subasta subasta, ListaDeSubastas listaDeSubastas) {
        listaDeSubastas.agregarSubasta(subasta);
        System.out.println("Subasta registrada con éxito para la pieza: " + subasta.getPieza().getTitulo());
    }

    public void realizarSubasta(Subasta subasta) {
        subasta.iniciarSubasta();
        System.out.println("Subasta iniciada para la pieza: " + subasta.getPieza().getTitulo());
    }
}
