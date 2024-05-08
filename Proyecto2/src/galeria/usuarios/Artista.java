package galeria.usuarios;
import java.util.ArrayList;
import java.util.List;

import galeria.inventario.Pieza;

public class Artista {
    private String nombre;
    private int identificacion;
    private List<Pieza> obras;

    public Artista(String nombre, int identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.obras = new ArrayList<>();
    }

    public void añadirObra(Pieza pieza) {
        this.obras.add(pieza);
    }

    public List<String> obtenerHistoria() {
        List<String> historia = new ArrayList<>();
        for (Pieza pieza : obras) {
            String descripcion = pieza.getTitulo() + " - Creado en " + pieza.getLugarCreacion() +", "+ pieza.getAnio()+". Autores: ";
            for(Artista autor : pieza.getAutores()) {
            	descripcion +=  autor.getNombre();
            }
            descripcion += (pieza.isExhibida() ? " (Exhibida)" : " (No Exhibida)");
            if (!pieza.isDisponible()) {
                descripcion += ". Vendida por: $" + pieza.getPrecioVenta() +
                               " el " + pieza.getFechaVenta() + ".";
            }
            historia.add(descripcion);
        }
        return historia;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public List<Pieza> getObras() {
        return obras;
    }

    public void setObras(List<Pieza> obras) {
        this.obras = obras;
    }
}
