package galeria.inventario;

import java.util.ArrayList;

import galeria.usuarios.Artista;

public class Impresion extends Pieza{
	
	private double ancho;
	private double alto;
	private String tecnica;
	
	public Impresion(String titulo, int anio, String lugarCreacion, ArrayList<Artista> autores, boolean exhibida,
			boolean disponible, double ancho, double alto, String tecnica) {
		super(titulo, anio, lugarCreacion, exhibida, disponible, autores);
		this.ancho = ancho;
		this.alto = alto;
		this.tecnica = tecnica;
		this.tipoPieza = "Impresion";
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
	

}
