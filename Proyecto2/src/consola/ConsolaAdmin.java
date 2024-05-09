package consola;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONException;

import galeria.Galeria;
import galeria.persistencia.Persistencia;

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
			System.out.println("5 - Modificar Comprador");
			System.out.println("6 - Consultar Artista");
			System.out.println("7 - Consultar Pieza");
			System.out.println("0 - Salir");
			try {
    			seleccion = Integer.valueOf(reader.readLine());
    			if (seleccion<0 || seleccion > 7) {throw new Exception();}
    		} catch (Exception e) {
    			System.err.println("Selección incorrecta");
    		}
			if (seleccion == 1) {
				registrarPieza();
			}
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
