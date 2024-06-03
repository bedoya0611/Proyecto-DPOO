package galeria.usuarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import galeria.Exceptions.PiezaDuplicadaException;
import galeria.compradores.Comprador;
import galeria.inventario.Inventario;
import galeria.inventario.Pieza;
import galeria.ventas.Venta;
import galeria.ventas.VentaFija;

public class Admin extends Usuario {
    private String nombre;
    private int id;
    private HashMap<String, Empleado> empleados = new HashMap<String, Empleado>();

    public Admin(String login, String password, String nombre, int id) {
        super(login, password);
        this.nombre = nombre;
        this.id = id;
    }


    public void registrarPieza(Pieza pieza, Inventario inventario) throws PiezaDuplicadaException {
		inventario.agregarPieza(pieza);
    }

    public boolean confirmarVenta(VentaFija venta, Inventario inventario) {
        if (venta.pagoHecho) {
            venta.realizarVenta(inventario);
            return true;
        } else {
            return false;
        }
    }

    public void agregarEmpleado(String nombre, int identificador, String login, String password, String cargo) {
        Empleado nuevoEmpleado;// = new Empleados(login, password, nombre, identificador);
        if (cargo.equals("Operador")){
            nuevoEmpleado = new Operador(login, password, nombre, identificador);
        }
        else {
            nuevoEmpleado = new Cajero(login, password, nombre, identificador);
        }
        empleados.put(nuevoEmpleado.getLogin(), nuevoEmpleado);
    }

    public Comprador consultarComprador(int id, List<Comprador> compradores) {
        for (Comprador comprador : compradores) {
            if (comprador.getIdentificador()==id) {
                return comprador;
            }
        }
        return null;
    }

    public void verificarComprador(Comprador comprador) {
        comprador.verificar();
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

    public HashMap<String, Empleado> getEmpleados(){
        return empleados;
    }
}


