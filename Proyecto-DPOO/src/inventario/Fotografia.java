package inventario;

import java.util.ArrayList;

public class Fotografia extends Pieza{
	
	private double ancho;
	private double alto;
	private String camara;
	public Fotografia(String titulo, int anio, String lugarCreacion, ArrayList<String> autores, boolean exhibida,
			boolean disponible, double ancho, double alto, String camara) {
		super(titulo, anio, lugarCreacion, autores, exhibida, disponible);
		this.ancho = ancho;
		this.alto = alto;
		this.camara = camara;
		this.tipoPieza = "Fotografia";
	}
	public double getAncho() {
		return ancho;
	}
	public double getAlto() {
		return alto;
	}
	public String getCamara() {
		return camara;
	}
	
	
}
