package galeria.ventas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import galeria.compradores.Comprador;

public class ListaDeSubastas {
	
	private ArrayList<Subasta> subastas;
	private ArrayList<Comprador> participantes;		
	
	public ListaDeSubastas () {
		subastas = new ArrayList<Subasta>();
		participantes = new ArrayList<Comprador>();
	}
	
	public void agregarSubasta(Subasta subasta) {
		subastas.add(subasta);
	}
	
	public void agregarParticipante(Comprador participante) {
		if(!participantes.contains(participante)) {
			participantes.add(participante);
		}
	}
	
	public ArrayList<Subasta> getSubastas() {
		return subastas;
	}
	
	public ArrayList<Comprador> getParticipantes() {
		return participantes;
	}
	
	public void setSubastas(ArrayList<Subasta> lista) {
		this.subastas = lista;
	}
	
	public void setParticipantes(ArrayList<Comprador> participantes) {
		this.participantes = participantes;
	}
}
