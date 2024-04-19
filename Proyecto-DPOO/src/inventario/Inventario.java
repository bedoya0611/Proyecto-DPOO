package inventario;

import java.util.ArrayList;

public class Inventario {
	private ArrayList<Pieza> piezasDisponibles = new ArrayList<Pieza>();
	private ArrayList<Pieza> piezasBloqueadas = new ArrayList<Pieza>();

	public Inventario() {
	}
	
	public void agregarPiezaDisponible(Pieza pieza) {
		this.piezasDisponibles.add(pieza);
	}
	public void bloquearPieza(String tituloPieza) {
		Pieza laPieza = null;
		for (Pieza pieza:piezasDisponibles) {
			String unTitulo = pieza.getTitulo();
			if (unTitulo == tituloPieza) {
				laPieza = pieza;
				break;
			}
		}
		piezasDisponibles.remove(laPieza);
		piezasBloqueadas.add(laPieza);
	}
	public void eliminarPieza(String tituloPieza) {
		Pieza laPieza = null;
		for (Pieza pieza:piezasDisponibles) {
			String unTitulo = pieza.getTitulo();
			if (unTitulo == tituloPieza) {
				laPieza = pieza;
				break;
			}
		}
		if (laPieza != null) {
			piezasDisponibles.remove(laPieza);
		}
		for (Pieza pieza:piezasBloqueadas) {
			String unTitulo = pieza.getTitulo();
			if (unTitulo == tituloPieza) {
				laPieza = pieza;
				break;
			}
		}
		if (laPieza != null) {
			piezasBloqueadas.remove(laPieza);
		}
	}
}
