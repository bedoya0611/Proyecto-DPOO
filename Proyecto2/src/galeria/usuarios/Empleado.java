package galeria.usuarios;

import galeria.inventario.Pieza;

public abstract class Empleado extends Usuario {
    private String nombre;
    private int idEmpleado;
    private String cargo;

    public Empleado(String login, String password, String nombreEmpleado, int idEmpleado, String cargo) {
        super(login, password);
        this.nombre = nombreEmpleado;
        this.idEmpleado = idEmpleado;
        this.cargo = cargo;
    }

    public void cambioEstadoABodega(Pieza pieza) {
        if (pieza.isExhibida()) {
            pieza.setExhibida(false);
            System.out.println("Pieza movida a bodega: " + pieza.getTitulo());
        } else {
            System.out.println("La pieza ya está en la bodega.");
        }
    }

    public void cambioEstadoAExhibida(Pieza pieza) {
        if (!pieza.isExhibida()) {
            pieza.setExhibida(true);
            System.out.println("Pieza movida a exhibición: " + pieza.getTitulo());
        } else {
            System.out.println("La pieza ya está en exhibición.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public String getCargo() {
        return cargo;
    }
}

