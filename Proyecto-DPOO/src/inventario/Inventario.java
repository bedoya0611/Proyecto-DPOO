package inventario;

import java.util.ArrayList;

import Exceptions.PiezaDuplicadaException;
import Exceptions.PiezaInexistenteException;

public class Inventario {
	private ArrayList<Pieza> piezasDisponibles = new ArrayList<Pieza>();
	private ArrayList<Pieza> piezasBloqueadas = new ArrayList<Pieza>();
	private ArrayList<Pieza> piezasExhibidas = new ArrayList<Pieza>();	

	public Inventario() {
	}
	
	public ArrayList<Pieza> getPiezasDisponibles() {
		return piezasDisponibles;
	}

	public ArrayList<Pieza> getPiezasBloqueadas() {
		return piezasBloqueadas;
	}

	public ArrayList<Pieza> getPiezasExhibidas() {
		return piezasExhibidas;
	}

	public void agregarPieza(Pieza pieza) throws PiezaDuplicadaException{
		if (piezasDisponibles.contains(pieza) || piezasBloqueadas.contains(pieza)) {
			throw new PiezaDuplicadaException("La pieza "+pieza.getTitulo()+" ya existe en el inventario.");
		}
		this.piezasDisponibles.add(pieza);
	}
	public void bloquearPieza(String titulo) {
		Pieza laPieza = null;
		for (Pieza pieza:piezasDisponibles) {
			String unTitulo = pieza.getTitulo();
			if (unTitulo == titulo) {
				laPieza = pieza;
				break;
			}
		}
		piezasDisponibles.remove(laPieza);
		piezasBloqueadas.add(laPieza);
	}
	public void eliminarPieza(String titulo) {
		Pieza laPieza = null;
		for (Pieza pieza:piezasDisponibles) {
			String unTitulo = pieza.getTitulo();
			if (unTitulo == titulo) {
				laPieza = pieza;
				break;
			}
		}
		if (laPieza != null) {
			piezasDisponibles.remove(laPieza);
		}
		for (Pieza pieza:piezasBloqueadas) {
			String unTitulo = pieza.getTitulo();
			if (unTitulo == titulo) {
				laPieza = pieza;
				break;
			}
		}
		if (laPieza != null) {
			piezasBloqueadas.remove(laPieza);
		}
	}
	public void exhibirPieza(String titulo) throws PiezaInexistenteException{
		Pieza laPieza = null;
		for (Pieza pieza:piezasDisponibles) {
			String unTitulo = pieza.getTitulo();
			if (unTitulo == titulo) {
				laPieza = pieza;
				break;
			}
		}
		if (laPieza == null) {
			throw new PiezaInexistenteException("No existe la pieza "+titulo);
		}
		this.piezasExhibidas.add(laPieza);
		
	}
	public void guardarPiezaEnBodega(String titulo) throws PiezaInexistenteException{
		Pieza laPieza = null;
		for (Pieza pieza:piezasDisponibles) {
			String unTitulo = pieza.getTitulo();
			if (unTitulo == titulo) {
				laPieza = pieza;
				break;
			}
		}
		if (laPieza == null) {
			throw new PiezaInexistenteException("No existe la pieza "+titulo);
		}
		this.piezasExhibidas.remove(laPieza);
	}
}
