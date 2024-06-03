package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONException;

import galeria.Galeria;
import galeria.compradores.Comprador;
import galeria.inventario.Pieza;
import galeria.persistencia.Persistencia;
import galeria.usuarios.Artista;

public class ConsolaAdmin extends Consola {

	public BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public Persistencia persistencia = new Persistencia();

	public ConsolaAdmin() {}
	
	private boolean verificarCredenciales(String usuario, String psswd) {
		return Galeria.verificarCredencialesAdmin(usuario, psswd);
	}
	
	private void registrarPieza() throws Exception{
		int seleccion = 10;
		System.out.println("Ingrese el tipo de pieza:");
		System.out.println("1 - Pintura");
		System.out.println("2 - Escultura");
		System.out.println("3 - Fotografía");
		System.out.println("4 - Impresión");
		System.out.println("5 - Video");
		try {
			seleccion = Integer.valueOf(reader.readLine());
			if (seleccion<1 || seleccion > 5) {throw new Exception();}
		} catch (Exception e) {
			System.err.println("Selección incorrecta");
			throw new Exception();
		}
		System.out.println("Ingrese el título de la pieza: ");
		String titulo = reader.readLine();
		System.out.println("Ingrese el año de creación de la pieza:");
		int anio = Integer.valueOf(reader.readLine());
		System.out.println("Ingrese el lugar de creación de la pieza: ");
		String lugar = reader.readLine();
		System.out.println("Ingrese el número de autores de la pieza:");
		int numAutores = Integer.valueOf(reader.readLine());
		String[] nombresAutores = new String[numAutores];
		for (int i=0; i<numAutores; i++) {
			System.out.println("Ingrese el nombre del autor "+(i+1));
			nombresAutores[i] = reader.readLine();
		}
		System.out.println("¿La pieza será exhibida? (y/n)");
		boolean exhibida;
		if(reader.readLine().equals("y")) {
			exhibida = true;
		}else {
			exhibida = false;
		}
		if(seleccion == 1) {
			//Pintura
			System.out.println("Ingrese el ancho de la pintura:");
			double ancho = Double.valueOf(reader.readLine());			
			System.out.println("Ingrese el alto de la pintura:");
			double alto = Double.valueOf(reader.readLine());
			System.out.println("Ingrese la técnica de la pintura:");
			String tecnica = reader.readLine();			
			System.out.println("Ingrese el estilo de la pintura:");
			String estilo = reader.readLine();
			Galeria.registrarPintura(titulo, anio, lugar, nombresAutores, exhibida, ancho, alto, tecnica, estilo);
		}else if (seleccion == 2) {
			//Escultura
			System.out.println("Ingrese el ancho de la escultura:");
			double ancho = Double.valueOf(reader.readLine());			
			System.out.println("Ingrese el alto de la escultura:");
			double alto = Double.valueOf(reader.readLine());
			System.out.println("Ingrese la profundidad de la escultura:");
			double profundidad = Double.valueOf(reader.readLine());
			System.out.println("Ingrese el material de la escultura");
			String material = reader.readLine();
			System.out.println("Ingrese el peso de la escultura:");
			double peso = Double.valueOf(reader.readLine());
			System.out.println("¿La escultura necesita electricidad? (y/n)");
			boolean electricidad;
			if(reader.readLine().equals("y")) {
				electricidad = true;
			}else {
				electricidad = false;
			}
			System.out.println("Ingrese los detalles de instalación de la escultura:");
			String detalles = reader.readLine();
			Galeria.registrarEscultura(titulo, anio, lugar, nombresAutores, exhibida, ancho, alto, profundidad,
					material, peso, electricidad, detalles);
		}else if (seleccion == 3) {
			//Fotografía
			System.out.println("Ingrese el ancho de la fotografía:");
			double ancho = Double.valueOf(reader.readLine());			
			System.out.println("Ingrese el alto de la fotografía:");
			double alto = Double.valueOf(reader.readLine());
			System.out.println("Ingrese la cámara de la fotografía:");
			String camara = reader.readLine();
			Galeria.registrarFotografia(titulo, anio, lugar, nombresAutores, exhibida, ancho, alto, camara);
		}else if (seleccion == 4) {
			//Impresión
			System.out.println("Ingrese el ancho de la impresión:");
			double ancho = Double.valueOf(reader.readLine());			
			System.out.println("Ingrese el alto de la impresión:");
			double alto = Double.valueOf(reader.readLine());
			System.out.println("Ingrese la técnica de la impresión:");
			String tecnica = reader.readLine();
			Galeria.registrarImpresion(titulo, anio, lugar, nombresAutores, exhibida, ancho, alto, tecnica);
		}else if (seleccion == 5) {
			//Video
			System.out.println("Ingrese la duración del vídeo en mins:");
			double duracion = Double.valueOf(reader.readLine());
			System.out.println("Ingrese el idioma del vídeo:");
			String idioma = reader.readLine();
			Galeria.registrarVideo(titulo, anio, lugar, nombresAutores, exhibida, duracion, idioma);
		}
		System.out.println("Pieza registrada con éxito");	
	}
	
	private void confirmarVenta() throws IOException {
		System.out.println("Ingrese el título de la pieza que se venderá:");
		String tituloPieza = reader.readLine();
		Galeria.confirmarVenta(tituloPieza);
	}
	
	private void consultarComprador() throws IOException {
		System.out.println("Ingrese el usuario del comprador a consultar:");
		String usuario = reader.readLine();
		Comprador comprador = Galeria.consultarComprador(usuario);
		if (comprador == null) {System.err.println("Usuario no encontrado");return;}
		System.out.println("-----COMPRADOR-----");
		System.out.println("Nombre: "+comprador.getNombre());
		System.out.println("Número de identificación: "+comprador.getIdentificador());
		System.out.println("Teléfono: "+comprador.getTelefono());
		System.out.println("Verificado: "+comprador.isVerificado());
	}
	
	private void verificarComprador() throws IOException {
		System.out.println("Ingrese el usuario del comprador a verificar:");
		String usuario = reader.readLine();
		Galeria.verificarComprador(usuario);
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
	
	private void consultarPieza() throws IOException {
		System.out.println("Ingrese el título de la pieza a consultar");
		String titulo = reader.readLine();
		ArrayList<String> lista = Galeria.consultarPieza(titulo);
		System.out.println(lista.toString());
	}
	
	private void imprimirMenu() throws Exception {
		int seleccion = 10;
		while (seleccion != 0) {
			System.out.println("------------------------------------------------");
			System.out.println("\t\tMenú Administrador");
			System.out.println("------------------------------------------------");
			System.out.println("1 - Registrar Pieza");
			System.out.println("2 - Confirmar Venta");
			System.out.println("3 - Consultar Comprador");
			System.out.println("4 - Verificar Comprador");
			System.out.println("5 - Consultar Artista");
			System.out.println("6 - Consultar Pieza");
			System.out.println("0 - Salir");
			try {
    			seleccion = Integer.valueOf(reader.readLine());
    			if (seleccion<0 || seleccion > 7) {throw new Exception();}
    		} catch (Exception e) {
    			seleccion = 10;
    			System.err.println("Selección incorrecta");
    		}
			if (seleccion == 1) {
				registrarPieza();
			} else if (seleccion == 2){
				confirmarVenta();
			} else if (seleccion == 3) {
				consultarComprador();
			} else if (seleccion == 4) {
				verificarComprador();
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
    	ConsolaAdmin consola = new ConsolaAdmin();
    	System.out.println("\t   CONSOLA DE ADMINISTRADOR");
    	consola.cargarArchivo(consola.persistencia);
    	boolean credencialesCorrectas = false;
    	while (!credencialesCorrectas) {
    		String[] credenciales = consola.credenciales(consola.reader);
    		credencialesCorrectas = consola.verificarCredenciales(credenciales[0], credenciales[1]);
    		if (!credencialesCorrectas) {
    			System.err.println("Usuario o contraseña inválido, intente nuevamente");
    		}
    	}
    	consola.imprimirMenu();
    }
}
