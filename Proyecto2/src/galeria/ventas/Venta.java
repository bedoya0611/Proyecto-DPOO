package galeria.ventas;

import java.util.List;

import galeria.compradores.Compra;
import galeria.compradores.Comprador;
import galeria.inventario.Inventario;
import galeria.inventario.Pieza;

public abstract class Venta {
	private boolean bloqueada;
	private List<Comprador> compradores;
	public boolean pagoHecho;
	public Pieza pieza;
	public Inventario inventario;
	public static final String TIPO_VENTA = "";
	
	public void compra() {
	}
	
	public void reclasificarComprador() {
	}
	
	public void agregarHistorialCompra(Compra compra) {
	}
	
	public void sacarPiezaDelInventario(Inventario inventario, Pieza pieza) {
	}
	
	public void realizarVenta() {
	}
	
	public Pieza getPieza() {
		return this.pieza;
	}
	
	public boolean getPagoHecho() {
		return this.pagoHecho;
	}

	public void setPagoHecho(boolean pagoHecho) {
		this.pagoHecho = pagoHecho;
	}
}

