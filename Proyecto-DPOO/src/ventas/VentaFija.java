package ventas;

import java.time.LocalTime;

import compradores.Compra;

public class VentaFija extends Venta{

	private int precioFijo;
	private Comprador comprador;
	
	public VentaFija (int fijo) {
		this.precioFijo = fijo;
	}
	
	public int getPrecioFijo () {
		return this.precioFijo;
	}
	
	public void venta(int monto, Comprador aspirante) {
		if (monto == precioFijo) {
			comprador = aspirante;
			this.realizarVenta();
		}
	}
	
	public void compra () {
		new Compra(comprador.getId(), LocalTime.now(), precioFijo, pieza );
	}
	
	@Override
	public void reclasificarComprador() {
		if(comprador.getCompras().size == 0) {
			new Propietario(comprador.getNombre, comprador.getIdentificador, comprador.getTelefono);
		}
	}
	
	@Override
	public void agregarHistorialCompra(Compra compra) {
		comprador.getCompras.add(new Compra(comprador.getId(), LocalTime.now(), precioFijo, pieza));
	}
	
	@Override
	public void sacarPiezaDelInventario(Pieza pieza) {
		Inventario.SacarDelInventario(pieza);
		Pieza.setPropietario(pieza);
	}
	
	@Override
	public void realizarVenta() {
		if(pagoHecho == true) {
			this.compra();
			this.reclasificarComprador();
			this.agregarHistorialCompra(new Compra(comprador.getId(), LocalTime.now(), precioFijo, pieza ));
			this.sacarPiezaDelInventario(pieza);
		}
}
