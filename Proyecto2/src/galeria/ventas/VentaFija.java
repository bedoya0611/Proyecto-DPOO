package galeria.ventas;

import java.time.LocalDateTime;

import galeria.compradores.Compra;
import galeria.compradores.Comprador;
import galeria.compradores.Propietario;
import galeria.inventario.Inventario;
import galeria.inventario.Pieza;

public class VentaFija extends Venta{

	private int precioFijo;
	private Comprador comprador;
	public static final String TIPO_VENTA = "Fija";

	
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
		new Compra(comprador.getIdentificador(), LocalDateTime.now(), precioFijo, pieza );
	}
	
	@Override
	public void reclasificarComprador() {
		if(comprador.getCompras().size() == 0) {
			new Propietario(comprador.isVerificado(), comprador.getNombre(), comprador.getIdentificador(),
			 				comprador.getTelefono(), comprador.getLogin(), comprador.getPassword());
		}
	}
	
	@Override
	public void agregarHistorialCompra(Compra compra) {
		comprador.getCompras().add(new Compra(comprador.getIdentificador(), LocalDateTime.now(), precioFijo, pieza));
	}
	
	@Override
	public void sacarPiezaDelInventario(Inventario inventario, Pieza pieza) {
		inventario.sacarDelInventario(pieza.getTitulo());
		pieza.setPropietario((Propietario) comprador);
	}
	
	@Override
	public void realizarVenta() {
		if(pagoHecho == true) {
			this.compra();
			this.reclasificarComprador();
			this.agregarHistorialCompra(new Compra(comprador.getIdentificador(), LocalDateTime.now(), precioFijo, pieza ));
			this.sacarPiezaDelInventario(inventario, pieza);
		}
	}
}
