package galeria.ventas;

import galeria.compradores.Comprador;

public class Oferta {

	private Comprador ofertador;
	private int valor;
	
	public void nuevaOferta (int valor, Comprador ofertador, Subasta subasta) {
		if (valor > subasta.getValorInicial() && valor > subasta.getValorInicial() && valor > subasta.getOfertaActual()) {
			subasta.getOfertas().put(valor, ofertador);
			subasta.setOfertaActual(valor);
		}
	}
}
