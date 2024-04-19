package usuarios;

import inventario.Pieza;

public class Empleados extends Usuario {
    private String nombre;
    private String idEmpleado;

    public Empleados(String login, String password, String nombre, String idEmpleado) {
        super(login, password);
        this.nombre = nombre;
        this.idEmpleado = idEmpleado;
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

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}

