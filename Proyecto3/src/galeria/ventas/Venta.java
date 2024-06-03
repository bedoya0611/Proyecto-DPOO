package galeria.ventas;

import java.util.Date;
import java.util.List;

import galeria.compradores.Compra;
import galeria.compradores.Comprador;
import galeria.inventario.Inventario;
import galeria.inventario.Pieza;

public abstract class Venta {
	private List<Comprador> compradores;
	protected Date fecha;
	public boolean pagoHecho;
	public Pieza pieza;
	public static final String TIPO_VENTA = "";
	
	public void compra() {
	}
	
	public void reclasificarComprador() {
	}
	
	public void agregarHistorialCompra(Compra compra) {
	}
	
	public void sacarPiezaDelInventario(Inventario inventario, Pieza pieza) {
	}

	public void registrarVenta() {
	}
	
	public Pieza getPieza() {
		return this.pieza;
	}
	
	public void setPieza(Pieza pieza) {
		this.pieza = pieza;
	}
	
	public boolean getPagoHecho() {
		return this.pagoHecho;
	}

	public void setPagoHecho(boolean pagoHecho) {
		this.pagoHecho = pagoHecho;
	}
	
	public String getTipo() {
		return TIPO_VENTA;
	}
}
