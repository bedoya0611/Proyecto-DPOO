package galeria.inventario;

import java.util.ArrayList;

import galeria.compradores.Propietario;

public abstract class Pieza {
	private String titulo;
	private int anio;
	private String lugarCreacion;
	private ArrayList<String> autores;
	private boolean exhibida;
	private boolean disponible;
	private Propietario propietario;
	protected String tipoPieza;
	
	public Pieza (String titulo, int anio, String lugarCreacion, ArrayList<String> autores, boolean exhibida, boolean disponible) {
		this.titulo = titulo;
		this.anio = anio;
		this.lugarCreacion = lugarCreacion;
		this.autores = autores;
		this.exhibida = exhibida;
		this.disponible = disponible;
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

	public ArrayList<String> getAutores() {
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
	
}
