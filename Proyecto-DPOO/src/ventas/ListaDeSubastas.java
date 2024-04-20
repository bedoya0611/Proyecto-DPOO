package ventas;

import java.util.LinkedList;
import java.util.List;

public class ListaDeSubastas {
	
	private List<Subasta> subastas;
	private List<Comprador> participantes;		
	
	public ListaDeSubastas () {
		subastas = new LinkedList<Subasta>();
		participantes = new LinkedList<Comprador>();
	}
	
	public void agregarSubasta(Subasta subasta) {
		subastas.add(subasta);
	}
}
