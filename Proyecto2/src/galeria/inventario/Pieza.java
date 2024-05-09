package galeria.inventario;

import java.util.ArrayList;
import galeria.compradores.Propietario;
import galeria.usuarios.Artista;

public abstract class Pieza {
    private String titulo;
    private int anio;
    private String lugarCreacion;
    private ArrayList<Artista> autores;
    private boolean exhibida;
    private boolean disponible;
    private Propietario propietario;
    private double precioVenta;
    private String fechaVenta;
    private Artista creador;
    protected String tipoPieza;
    public ArrayList<String> ventas;
    
    public Pieza(String titulo, int anio, String lugarCreacion, boolean exhibida, boolean disponible, ArrayList<Artista> autores) {
        this.titulo = titulo;
        this.anio = anio;
        this.lugarCreacion = lugarCreacion;
        this.autores = autores;
        this.exhibida = exhibida;
        this.disponible = disponible;
    }

    public void venderPieza(double precio, String fecha) {
        this.precioVenta = precio;
        this.fechaVenta = fecha;
        this.disponible = false;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public Artista getCreador() {
        return creador;
    }

    public void setCreador(Artista creador) {
        this.creador = creador;
    }

	public String getTitulo() {
		return titulo;
	}

	public int getAnio() {
		return anio;
	}

	public String getLugarCreacion() {
		return lugarCreacion;
	}
	
	public String getTipoPieza() {
		return tipoPieza;
	}

	public ArrayList<Artista> getAutores() {
		return autores;
	}

	public boolean isExhibida() {
		return exhibida;
	}

	public void setExhibida(boolean exhibida) {
		this.exhibida = exhibida;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Propietario getPropietario() {
		return propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public List<String> obtenerHistoriaPieza(Pieza pieza) {
        List<String> historia = new ArrayList<>();   
        String descripcion = "Autores: ";
        for(Artista autor : pieza.getAutores()) {
        	descripcion +=  autor.getNombre() + ", ";
        }
        descripcion += "titulo: '" + pieza.getTitulo() + "', " + pieza.getAnio() + ", " +
        			   pieza.getLugarCreacion() + ", " + pieza.getTipoPieza() + ".";
        descripcion += (pieza.isExhibida() ? " (Exhibida)" : " (No Exhibida)");
        descripcion += ". El historial de ventas de esta pieza es: " + ventas;
        historia.add(descripcion);
        return historia;
	}
	
}
