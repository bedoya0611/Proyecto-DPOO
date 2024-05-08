package galeria.inventario;

import java.util.ArrayList;

import galeria.usuarios.Artista;

public class Pintura extends Pieza{
	
	private double ancho;
	private double alto;
	private String tecnica;
	private String estilo;
	
	public Pintura(String titulo, int anio, String lugarCreacion, ArrayList<Artista> autores, boolean exhibida,
			boolean disponible, double ancho, double alto, String tecnica, String estilo) {
		super(titulo, anio, lugarCreacion, disponible, exhibida, autores);
		this.ancho = ancho;
		this.alto = alto;
		this.tecnica = tecnica;
		this.estilo = estilo;
		this.tipoPieza = "Pintura";
	}
	public double getAncho() {
		return ancho;
	}
	public double getAlto() {
		return alto;
	}
	public String getTecnica() {
		return tecnica;
	}
	public String getEstilo() {
		return estilo;
	}
	
	
}
