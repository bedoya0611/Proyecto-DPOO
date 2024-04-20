package compradores;

import java.time.chrono.ChronoLocalDate;

public class Compra {

	
	private int id;
	private ChronoLocalDate fecha;
	private int monto;
	private Pieza pieza;
	
	public Compra (int nId, ChronoLocalDate nFecha, int nMonto, Pieza nPieza) {
		this.id = nId;
		this.fecha = nFecha;
		this.monto = nMonto;
		this.pieza = nPieza;
	
}
