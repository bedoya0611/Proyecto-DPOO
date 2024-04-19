package inventario;

import java.util.ArrayList;

public class Escultura extends Pieza{
	
	private double ancho;
	private double alto;
	private double profundidad;
	private String material;
	private double peso;
	private boolean necesitaElectricidad;
	private String detallesInstalacion;
	
	public Escultura(String titulo, int anio, String lugarCreacion, ArrayList<String> autores, boolean exhibida,
			boolean disponible, double ancho, double alto, double profundidad, String material, double peso,
			boolean necesitaElectricidad, String detallesInstalacion) {
		super(titulo, anio, lugarCreacion, autores, exhibida, disponible);
		this.ancho = ancho;
		this.alto = alto;
		this.profundidad = profundidad;
		this.material = material;
		this.peso = peso;
		this.necesitaElectricidad = necesitaElectricidad;
		this.detallesInstalacion = detallesInstalacion;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}

	public double getProfundidad() {
		return profundidad;
	}

	public String getMaterial() {
		return material;
	}

	public double getPeso() {
		return peso;
	}

	public boolean isNecesitaElectricidad() {
		return necesitaElectricidad;
	}

	public String getDetallesInstalacion() {
		return detallesInstalacion;
	}
	
	

}
