package galeria.ventas;

import java.time.LocalDateTime;
import java.util.Date;

import galeria.compradores.Compra;
import galeria.compradores.Comprador;
import galeria.compradores.Propietario;
import galeria.inventario.Inventario;
import galeria.inventario.Pieza;

public class VentaFija extends Venta{

	private int precioFijo;
	private Comprador comprador;
	public static final String TIPO_VENTA = "Fija";

	
	public VentaFija (int fijo, Comprador comprador) {
		this.precioFijo = fijo;
		this.fecha = new Date();
		this.pagoHecho = true;
		this.comprador = comprador;
	}
	
	public int getPrecioFijo () {
		return this.precioFijo;
	}
	
	@Override
	public String getTipo() {
		return this.TIPO_VENTA;
	}
	
	
	public void venta(int monto, Comprador aspirante, Inventario inventario) {
		if (monto == precioFijo) {
			comprador = aspirante;
			this.realizarVenta(inventario);
		}
	}
	
	public void compra () {
		new Compra(comprador.getIdentificador(), LocalDateTime.now(), precioFijo, pieza );
	}
	
	@Override
	public void reclasificarComprador() {
		if(comprador.getCompras().size() == 0) {
			comprador = new Propietario(comprador.isVerificado(), comprador.getNombre(), comprador.getIdentificador(),
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
	public void registrarVenta() {
		String registro = "Comprado por: " + comprador.getNombre() 
		+ ", " + LocalDateTime.now() + ", en $" + precioFijo + ".";
		pieza.ventas.add(registro);
	}
	
	public void realizarVenta(Inventario inventario) {
		if(pagoHecho == true) {
			this.compra();
			this.reclasificarComprador();
			this.agregarHistorialCompra(new Compra(comprador.getIdentificador(), LocalDateTime.now(), precioFijo, pieza ));
			this.sacarPiezaDelInventario(inventario, pieza);
			this.registrarVenta();
		}
	}
}