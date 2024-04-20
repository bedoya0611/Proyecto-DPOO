package ventas;

import java.util.List;

import compradores.Compra;
import compradores.Comprador;
import inventario.Inventario;
import inventario.Pieza;

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

	public void setPagoHecho(boolean pagoHecho) {
		this.pagoHecho = pagoHecho;
	}
}

