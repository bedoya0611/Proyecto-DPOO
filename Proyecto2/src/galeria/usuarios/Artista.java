import java.util.ArrayList;
import java.util.List;

public class Artista {
    private String nombre;
    private String identificacion;
    private List<Pieza> obras;

    public Artista(String nombre, String identificacion) {
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
            String descripcion = pieza.getTitulo() + " - Creado en " + pieza.getLugarCreacion() +
                                 ", " + pieza.getAnio() + ". Autores: " + String.join(", ", pieza.getAutores()) +
                                 (pieza.isExhibida() ? " (Exhibida)" : " (No Exhibida)");
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

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public List<Pieza> getObras() {
        return obras;
    }

    public void setObras(List<Pieza> obras) {
        this.obras = obras;
    }
}
