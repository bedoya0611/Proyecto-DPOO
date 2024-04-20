package ventas;

import java.time.LocalTime;
import java.util.HashMap;

import compradores.Compra;

public class Subasta extends Venta{

	private int valorInicial;
	private int valorMinimo;
	private int ofertaActual;
	private Pieza pieza;
	public static HashMap<Integer, Comprador> ofertas;
	private boolean abierta;
	
	public Subasta(int inicial, int minimo, int actual, Pieza nPieza) {
		this.valorInicial = inicial;
		this.valorMinimo = minimo;
		this.ofertaActual = actual;
		this.pieza = nPieza;
		ofertas = new HashMap<Integer, Comprador>();
		this.abierta = false;
	}
	
	public HashMap<Integer, Comprador> getOfertas(){
		return this.ofertas;
	}
	
	public int getValorInicial (){
		return this.valorInicial;
	}
	
	public int getValorMinimo (){
		return this.valorMinimo;
	}
	
	public int getOfertaActual (){
		return this.ofertaActual;
	}
	
	public void setOfertaActual (int valor) {
		this.ofertaActual = valor;
	}
	
	public void iniciarSubasta() {
		abierta = true;
	}
	
	public void cerrarSubasta() {
		abierta = false;
	}
	
	private int ofertaFinal = this.ofertaActual;
	
	private Comprador ganador = ofertas.get(ofertaFinal);
	
	@Override
	public void compra () {
		new Compra(ganador.getId(), LocalTime.now(), ofertaFinal, pieza );
	}
	
	@Override
	public void reclasificarComprador() {
		if(ganador.getCompras().size == 0) {
			new Propietario(ganador.getNombre, ganador.getIdentificador, ganador.getTelefono);
		}
	}
	
	@Override
	public void agregarHistorialCompra(Compra compra) {
		ganador.getCompras.add(new Compra(ganador.getId(), LocalTime.now(), ofertaFinal, pieza));
	}
	
	@Override
	public void sacarPiezaDelInventario(Pieza pieza) {
		Inventario.SacarDelInventario(pieza);
		Pieza.setPropietario();
	}
	
	@Override
	public void realizarVenta() {
		if(pagoHecho == true) {
			this.compra();
			this.reclasificarComprador();
			this.agregarHistorialCompra(new Compra(ganador.getId(), LocalTime.now(), ofertaFinal, pieza ));
			this.sacarPiezaDelInventario(pieza);
		}
	}
}
