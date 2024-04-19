package inventario;

import java.util.ArrayList;

public class Pintura extends Pieza{
	
	private double ancho;
	private double alto;
	private String tecnica;
	private String estilo;
	
	public Pintura(String titulo, int anio, String lugarCreacion, ArrayList<String> autores, boolean exhibida,
			boolean disponible, double ancho, double alto, String tecnica, String estilo) {
		super(titulo, anio, lugarCreacion, autores, exhibida, disponible);
		this.ancho = ancho;
		this.alto = alto;
		this.tecnica = tecnica;
		this.estilo = estilo;
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
