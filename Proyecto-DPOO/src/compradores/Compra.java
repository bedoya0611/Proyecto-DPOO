package compradores;

import java.time.LocalDateTime;

import inventario.Pieza;

public class Compra {

	
	private int id;
	private LocalDateTime fecha;
	private int monto;
	private Pieza pieza;
	
	public Compra (int nId, LocalDateTime nFecha, int nMonto, Pieza nPieza) {
		this.id = nId;
		this.fecha = nFecha;
		this.monto = nMonto;
		this.pieza = nPieza;
	}
}
