package usuarios;

import java.util.ArrayList;
import java.util.List;

import Exceptions.PiezaDuplicadaException;
import compradores.Comprador;
import inventario.Inventario;
import inventario.Pieza;
import ventas.Venta;

public class Admin extends Usuario {
    private String nombre;
    private int id;
    private ArrayList<Empleado> empleados;

    public Admin(String login, String password, String nombre, int id) {
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

    public void agregarEmpleado(String nombre, int identificador, String login, String password, String cargo) {
        Empleado nuevoEmpleado;// = new Empleados(login, password, nombre, identificador);
        if (cargo == "Operador"){
            nuevoEmpleado = new Operador(login, password, nombre, identificador);
        }
        else {
            nuevoEmpleado = new Cajero(login, password, nombre, identificador);
        }
        empleados.add(nuevoEmpleado);
    }

    public Comprador consultarComprador(int id, List<Comprador> compradores) {
        for (Comprador comprador : compradores) {
            if (comprador.getIdentificador()==id) {
                return comprador;
            }
        }
        return null;
    }

    public boolean verificarComprador(Comprador comprador) {
        return comprador.isVerificado();
    }

    public void modificarComprador(Comprador comprador, int nuevoTelefono) {
        comprador.setTelefono(nuevoTelefono);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Empleado> getEmpleados(){
        return empleados;
    }
}


