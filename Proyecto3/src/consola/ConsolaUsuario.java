package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONException;

import galeria.Galeria;
import galeria.compradores.Comprador;
import galeria.inventario.Inventario;
import galeria.inventario.Pieza;
import galeria.persistencia.Persistencia;
import galeria.usuarios.Artista;
import galeria.ventas.ListaDeSubastas;
import galeria.ventas.Subasta;

public class ConsolaUsuario extends Consola{
	public BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public Persistencia persistencia = new Persistencia();
	private String[] credencialesUsuario;

	public ConsolaUsuario() {}
	
	private boolean verificarCredenciales(String usuario, String psswd) {
		return Galeria.verificarCredencialesUsuario(usuario, psswd);
	}
	
	private void consultarExhibidas() {
		System.out.println("-----PIEZAS EN EXHIBICIÓN-----");
		Inventario inventario = Galeria.getUnInventario();
		System.out.println("Piezas Disponibles: ");
		ArrayList<Pieza> piezasExhibidas = inventario.getPiezasExhibidas();
		for (Pieza piezaexhibida:piezasExhibidas) {
			System.out.println("----------------------------------");
			System.out.println("Tipo de Pieza: "+piezaexhibida.getTipoPieza());
			System.out.println("Título: "+piezaexhibida.getTitulo());
			System.out.println("Autores: ");
			for(Artista autor:piezaexhibida.getAutores()) {
				System.out.println("\t- "+autor.getNombre());
			}
			System.out.println("Año de Creación: "+piezaexhibida.getAnio());
			System.out.println("Lugar de Creación: "+piezaexhibida.getLugarCreacion());
		}
	}
	
	private void consultarArtista() throws IOException {
		System.out.println("Ingrese el nombre del artistaa a consultar:");
		String nombre = reader.readLine();
		Artista elArtista = Galeria.consultarArtista(nombre);
		if (elArtista == null) {System.err.println("Artista no encontrado");return;}
		System.out.println("-----ARTISTA-----");
		System.out.println("Nombre: "+elArtista.getNombre());
		System.out.println("Obras:");
		for (Pieza pieza:elArtista.getObras()) {
			System.out.println("- "+pieza.getTitulo());
		}
		
	}
	
	private void consultarDisponibles() {
		System.out.println("-----PIEZAS DISPONIBLES-----");
		Inventario inventario = Galeria.getUnInventario();
		System.out.println("Piezas Disponibles: ");
		ArrayList<Pieza> piezasDisponibles = inventario.getPiezasDisponibles();
		for (Pieza piezaDisponible:piezasDisponibles) {
			System.out.println("----------------------------------");
			System.out.println("Tipo de Pieza: "+piezaDisponible.getTipoPieza());
			System.out.println("Título: "+piezaDisponible.getTitulo());
			System.out.println("Autores: ");
			for(Artista autor:piezaDisponible.getAutores()) {
				System.out.println("\t- "+autor.getNombre());
			}
			System.out.println("Año de Creación: "+piezaDisponible.getAnio());
			System.out.println("Lugar de Creación: "+piezaDisponible.getLugarCreacion());
			System.out.println("Exhibida: "+piezaDisponible.isExhibida());
		}
	}
	
	private void comprarPieza() throws IOException {
		System.out.println("Ingrese el título de la pieza que desea comprar");
		String titulo = reader.readLine();
		Galeria.comprarPieza(titulo);
	}
	
	private void participarEnSubasta() throws IOException {
		System.out.println("Las subastas disponibles para participar son:");
		ArrayList<ListaDeSubastas> abiertas = Galeria.getSubastasAbiertas();
		for(int i=0 ;i<abiertas.size();i++) {
			ListaDeSubastas lista = abiertas.get(i);
			System.out.println("----------Subasta "+(i+1)+"----------");
			System.out.println("Piezas:");
			for (Subasta subasta:lista.getSubastas()) {
				System.out.println("\t- "+subasta.getPieza().getTitulo());
			}
		}
		System.out.println("Ingrese el número de la subasta en la que desea participar:");
		int index = Integer.valueOf(reader.readLine())-1;
		if (index < 0 || index > abiertas.size()-1) {
			System.err.println("Selección incorrecta");
		}else {
			boolean inscrito = Galeria.participarEnSubasta(index, credencialesUsuario);
			if (inscrito) {
				System.out.println("Inscrito en la subasta con éxito");			
			} else {
				System.err.println("El usuario no se encuentra verificado. No fue posible inscribir en la subasta");
			}
		}
	}
	
	private void consultarPieza() throws IOException {
		System.out.println("Ingrese el título de la pieza a consultar");
		String titulo = reader.readLine();
		ArrayList<String> lista = Galeria.consultarPieza(titulo);
		System.out.println(lista.toString());
	}
	
	private void imprimirMenu() throws IOException {
		int seleccion = 10;
		while (seleccion != 0) {
			System.out.println("------------------------------------------------");
			System.out.println("\t\tMenú Usuario");
			System.out.println("------------------------------------------------");
			System.out.println("1 - Ver piezas exhibidas");
			System.out.println("2 - Ver piezas disponibles");
			System.out.println("3 - Comprar Pieza");
			System.out.println("4 - Participar en subasta");
			System.out.println("5 - Consultar Artista");
			System.out.println("6 - Consultar Pieza");
			System.out.println("0 - Salir");
			try {
    			seleccion = Integer.valueOf(reader.readLine());
    			if (seleccion<0 || seleccion > 6) {throw new Exception();}
    		} catch (Exception e) {
    			System.err.println("Selección incorrecta");
    		}
			if (seleccion == 1) {
				//Consultar Exhibidas
				consultarExhibidas();
			} else if (seleccion == 2) {
				//Consultar Disponibles
				consultarDisponibles();
			} else if (seleccion == 3) {
				//Comprar Pieza
				comprarPieza();
			} else if (seleccion == 4) {
				participarEnSubasta();
			} else if (seleccion == 5) {
				consultarArtista();
			} else if (seleccion == 6) {
				consultarPieza();
			}
			guardarArchivo(persistencia, Galeria.getUnAdmin(), Galeria.getUnInventario(), new ArrayList<Comprador>(Galeria.getCompradores().values()));
		}
	}
	
	public static void main( String[] args ) throws JSONException, Exception
    {
    	ConsolaUsuario consola = new ConsolaUsuario();
    	System.out.println("\t   CONSOLA DE USUARIO");
    	consola.cargarArchivo(consola.persistencia);
    	boolean credencialesCorrectas = false;
    	while (!credencialesCorrectas) {
    		String[] credenciales = consola.credenciales(consola.reader);
    		credencialesCorrectas = consola.verificarCredenciales(credenciales[0], credenciales[1]);
    		if (!credencialesCorrectas) {
    			System.err.println("Usuario o contraseña inválido, intente nuevamente");
    		}else {
    			consola.credencialesUsuario = credenciales;
    		}
    	}
    	consola.imprimirMenu();
    }
}
