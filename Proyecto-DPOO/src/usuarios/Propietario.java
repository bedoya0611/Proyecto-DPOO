package usuarios;

import java.util.ArrayList;
import java.util.List;

import inventario.Pieza;

public class Propietario extends Comprador {
    private List<Pieza> piezasActuales;
    private List<Pieza> piezasVendidas;

    public Propietario(String login, String password, String nombre, String identificador, String telefono, boolean verificado) {
        super(login, password, nombre, identificador, telefono, verificado);
        this.piezasActuales = new ArrayList<>();
        this.piezasVendidas = new ArrayList<>();
    }

    public void adquirirPieza(Pieza pieza) {
        if (!piezasActuales.contains(pieza)) {
            piezasActuales.add(pieza);
            System.out.println("Pieza adquirida y añadida al inventario del propietario: " + pieza.getTitulo());
        }
    }

    public void venderPieza(Pieza pieza) {
        if (piezasActuales.remove(pieza)) {
            piezasVendidas.add(pieza);
            System.out.println("Pieza vendida y movida al inventario de piezas vendidas: " + pieza.getTitulo());
        } else {
            System.out.println("La pieza no se encuentra en el inventario actual.");
        }
    }

    public List<Pieza> getPiezasActuales() {
        return piezasActuales;
    }

    public void setPiezasActuales(List<Pieza> piezasActuales) {
        this.piezasActuales = piezasActuales;
    }

    public List<Pieza> getPiezasVendidas() {
        return piezasVendidas;
    }

    public void setPiezasVendidas(List<Pieza> piezasVendidas) {
        this.piezasVendidas = piezasVendidas;
    }
}

