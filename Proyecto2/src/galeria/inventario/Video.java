package galeria.inventario;

import java.util.ArrayList;

import galeria.usuarios.Artista;

public class Video extends Pieza{
	
	private String idioma;
	private double duracion;
	
	public Video(String titulo, int anio, String lugarCreacion, ArrayList<Artista> autores, boolean exhibida,
			boolean disponible, String idioma, double duracion) {
		super(titulo, anio, lugarCreacion, disponible, exhibida, autores);
		this.idioma = idioma;
		this.duracion = duracion;
		this.tipoPieza = "Video";
	}

	public String getIdioma() {
		return idioma;
	}

	public double getDuracion() {
		return duracion;
	}
	
}
