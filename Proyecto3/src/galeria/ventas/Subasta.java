package galeria.ventas;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

import galeria.compradores.Compra;
import galeria.inventario.Inventario;
import galeria.inventario.Pieza;
import galeria.compradores.Comprador;
import galeria.compradores.Propietario;

public class Subasta extends Venta{

	public static final String TIPO_VENTA = "Subasta";
	private int valorInicial;
	private int valorMinimo;
	private int ofertaActual;
	public static HashMap<Integer, Comprador> ofertas;
	private boolean abierta;
	private int ofertaFinal;
	private Comprador ganador;
	
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
	
	@Override
	public Pieza getPieza() {
		return this.pieza;
	}
	
	@Override
	public String getTipo() {
		return this.TIPO_VENTA;
	}
	
	public void setOfertaActual (int valor) {
		this.ofertaActual = valor;
	}
	
	public void iniciarSubasta() {
		abierta = true;
	}
	
	public void cerrarSubasta() {
		abierta = false;
		this.fecha = new Date();
		ofertaFinal = this.ofertaActual;		
		ganador = ofertas.get(ofertaFinal);
	}
	
	
	public void compra () {
		new Compra(ganador.getIdentificador(), LocalDateTime.now(), ofertaFinal, pieza );
	}
	
	public void reclasificarComprador() {
		if(ganador.getCompras().size() == 0) {
			new Propietario(ganador.isVerificado(), ganador.getNombre(), ganador.getIdentificador(), ganador.getTelefono(), ganador.getLogin(), ganador.getPassword());
		}
	}
	
	public void agregarHistorialCompra(Compra compra) {
		ganador.getCompras().add(new Compra(ganador.getIdentificador(), LocalDateTime.now(), ofertaFinal, pieza));
	}
	
	public void sacarPiezaDelInventario(Inventario inventario, Pieza pieza) {
		inventario.sacarDelInventario(pieza.getTitulo());
		pieza.setPropietario((Propietario) ganador);
	}

	public void registrarVenta() {
		String registro = "Comprado por: " + ganador.getNombre() 
		+ ", " + LocalDateTime.now() + ", en $" + ofertaFinal + ".";
		pieza.ventas.add(registro);
	}
	
	public void realizarVenta(Inventario inventario) {
		if(pagoHecho == true) {
			this.compra();
			this.reclasificarComprador();
			this.agregarHistorialCompra(new Compra(ganador.getIdentificador(), LocalDateTime.now(), ofertaFinal, pieza ));
			this.sacarPiezaDelInventario(inventario, pieza);
			this.registrarVenta();
		}
	}

	public boolean isAbierta() {
		return abierta;
	}
	
	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}
}