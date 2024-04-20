package ventas;

import java.util.List;

import compradores.Comprador;

public abstract class Venta {
	private String tipoVenta;
	private boolean bloqueada;
	private List<Comprador> compradores;
	public boolean pagoHecho;
	public Pieza pieza;
	
	public void compra() {
	}
	
	public void reclasificarComprador() {
	}
	
	public void agregarHistorialCompra(Compra compra) {
	}
	
	public void sacarPiezaDelInventario(Pieza pieza) {
	}
	
	public void realizarVenta() {
	}
	
	
}

