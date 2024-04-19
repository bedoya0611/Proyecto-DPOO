package usuarios;

import java.util.List;

import Exceptions.PiezaDuplicadaException;
import inventario.Inventario;
import inventario.Pieza;

public class Admin extends Usuario {
    private String nombre;
    private String id;

    public Admin(String login, String password, String nombre, String id) {
        super(login, password);
        this.nombre = nombre;
        this.id = id;
    }


    public void registrarPieza(Pieza pieza, Inventario inventario) {
        try {
			inventario.agregarPieza(pieza);
		} catch (PiezaDuplicadaException e) {
			e.printStackTrace();
		}
    }

    public void confirmarVenta(Venta venta) {
        if (venta.pagoHecho) {
            venta.realizarVenta();
        } else {
            System.out.println("El pago aún no se ha realizado.");
        }
    }

    public void confirmarDevolucion(Pieza pieza, Inventario inventario) {
        try {
			inventario.agregarPieza(pieza);
		} catch (PiezaDuplicadaException e) {
			e.printStackTrace();
		}
    }

    public Comprador consultarComprador(String id, List<Comprador> compradores) {
        for (Comprador comprador : compradores) {
            if (comprador.getIdentificador().equals(id)) {
                return comprador;
            }
        }
        return null;
    }

    public boolean verificarComprador(Comprador comprador) {
        return comprador.isVerificado();
    }

    public void modificarComprador(Comprador comprador, String nuevoTelefono) {
        comprador.setTelefono(nuevoTelefono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


