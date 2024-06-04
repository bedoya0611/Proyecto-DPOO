package galeria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import galeria.Exceptions.FormatoIncorrectoException;
import galeria.Exceptions.PiezaDuplicadaException;
import galeria.compradores.Comprador;
import galeria.inventario.Inventario;
import galeria.inventario.*;
import galeria.persistencia.Persistencia;
import galeria.usuarios.Admin;
import galeria.usuarios.Artista;
import galeria.usuarios.Empleado;
import galeria.ventas.ListaDeSubastas;
import galeria.ventas.VentaFija;

public class Galeria
{
    private static Admin unAdmin;
    private static Inventario unInventario;
    private static Empleado unEmpleado;
    private static Comprador unComprador;
    private static Persistencia persistencia = new Persistencia();
    private static HashMap<String, Comprador> compradores = new HashMap<String, Comprador>();
    private static HashMap<String, Artista> artistas = new HashMap<String, Artista>();
    private static ArrayList<ListaDeSubastas> subastasAbiertas = new ArrayList<ListaDeSubastas>();

    
    public static void cargarCompradores(String archivo) throws IOException {
    	ArrayList<Comprador> listaCompradores= persistencia.cargarCompradores(archivo); 
    	for(Comprador comprador:listaCompradores) {
    		compradores.put(comprador.getLogin(), comprador);
    	}
    	
    }
    
    public void correrAplicacion( ) throws JSONException, Exception
    {
        try
        {
        	Persistencia persistencia = new Persistencia();
            // String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo json con la información de una aerolinea" );
            String archivoOrigen = "datos.json"; 
            persistencia.cargarGaleria( "./datos/" + archivoOrigen);
            String archivoDestino = "datosGuardados.json";
            persistencia.salvarGaleria("./datos/"+archivoDestino, Galeria.unAdmin, Galeria.unInventario);
        }
        catch( FormatoIncorrectoException e )
        {
            e.printStackTrace( );
        }
    }
    
    public static void setAdmin(Admin admin) {
    	unAdmin = admin;
    }
    
    public static void setInventario(Inventario inventario) {
    	unInventario = inventario;
    }
    
    
    
    public static Admin getUnAdmin() {
		return unAdmin;
	}

	public static Inventario getUnInventario() {
		return unInventario;
	}

	public static Empleado getUnEmpleado() {
		return unEmpleado;
	}

	public static Comprador getUnComprador() {
		return unComprador;
	}

	public static Persistencia getPersistencia() {
		return persistencia;
	}

	public static HashMap<String, Artista> getArtistas() {
		return artistas;
	}

	public static HashMap<String, Comprador> getCompradores(){
    	return compradores;
    }
	
	public static void setArtistas(ArrayList<Artista> listaArtistas) {
		for(Artista artista:listaArtistas) {
			artistas.put(artista.getNombre(), artista);
		}
	}
    
    //Verifica credenciales y devuelve el tipo de empleado
    //La primera posición indica si las credenciales son correctas
    //La segunda posición indica true si es Operador, false si es Cajero
    public static boolean[] verificarCredencialesEmpleado( String usuario, String psswd ){
    	unEmpleado = unAdmin.getEmpleados().get(usuario);
    	boolean[] retorno = {false, false};
    	if (unEmpleado == null){
    		return retorno;
    		}
    	retorno[0] =  unEmpleado.verificarPassword(psswd);
    	boolean cargoEmpleado;
    	if (unEmpleado.getCargo()=="Cajero") {
    		cargoEmpleado = false;
    	} else {
    		cargoEmpleado = true;
    	}
    	retorno[1] = cargoEmpleado;
    	return retorno;
    }

    public static ArrayList<ListaDeSubastas> getSubastasAbiertas(){
    	return subastasAbiertas;
    }
    
    public static void setSubastasAbiertas(ArrayList<ListaDeSubastas> abiertas) {
    	subastasAbiertas = abiertas;
    }
    
    public static boolean verificarCredencialesUsuario(String usuario, String psswd) {
    	unComprador = compradores.get(usuario);
    	if (unComprador == null) {
    		return false;
    	}
    	return unComprador.verificarPasswd(psswd);
    }
    
    //ADMIN

    public static boolean verificarCredencialesAdmin( String usuario, String psswd ){
    	if (unAdmin == null){
    		return false;
    	}
    	if (!unAdmin.getLogin().equals(usuario)) {return false;}
    	return unAdmin.verificarPassword(psswd);
    }
    
    public static void registrarPintura(String titulo,int anio,String lugar,String[] nombresAutores,
    		boolean exhibida,double ancho,double alto,String tecnica,String estilo) throws PiezaDuplicadaException {
    	ArrayList<Artista> listaAutores = new ArrayList<Artista>();
    	for (String nombre:nombresAutores) {
    		if(artistas.containsKey(nombre)) {
    			listaAutores.add(artistas.get(nombre));
    		}else {
    			Artista nuevoArtista = new Artista(nombre);
    			artistas.put(nombre, nuevoArtista);
    			listaAutores.add(nuevoArtista);
    		}
    	}
    	Pieza pintura = new Pintura(titulo, anio, lugar, listaAutores, exhibida, true, ancho, alto, tecnica, estilo);
    	unAdmin.registrarPieza(pintura, unInventario);
    }
    
    public static void registrarEscultura(String titulo,int anio,String lugar,String[] nombresAutores, 
    		boolean exhibida,double ancho,double alto,double profundidad,String material,double peso,
    		boolean electricidad,String detalles) throws PiezaDuplicadaException {
    	ArrayList<Artista> listaAutores = new ArrayList<Artista>();
    	for (String nombre:nombresAutores) {
    		if(artistas.containsKey(nombre)) {
    			listaAutores.add(artistas.get(nombre));
    		}else {
    			Artista nuevoArtista = new Artista(nombre);
    			artistas.put(nombre, nuevoArtista);
    			listaAutores.add(nuevoArtista);
    		}
    	}
    	Pieza escultura = new Escultura(titulo, anio, lugar, listaAutores, exhibida, true, ancho, alto, profundidad, material, peso, electricidad, detalles);
    	unAdmin.registrarPieza(escultura, unInventario);
    }
    
    public static void registrarFotografia(String titulo,int anio,String lugar,String[] nombresAutores,
    		boolean exhibida,double ancho,double alto,String camara) throws PiezaDuplicadaException {
    	ArrayList<Artista> listaAutores = new ArrayList<Artista>();
    	for (String nombre:nombresAutores) {
    		if(artistas.containsKey(nombre)) {
    			listaAutores.add(artistas.get(nombre));
    		}else {
    			Artista nuevoArtista = new Artista(nombre);
    			artistas.put(nombre, nuevoArtista);
    			listaAutores.add(nuevoArtista);
    		}
    	}
    	Pieza fotografia = new Fotografia(titulo, anio, lugar, listaAutores, exhibida, true, ancho, alto, camara);
    	unAdmin.registrarPieza(fotografia, unInventario);
    }
    
    public static void registrarImpresion(String titulo,int anio,String lugar,String[] nombresAutores,
    		boolean exhibida,double ancho,double alto,String tecnica) throws PiezaDuplicadaException {
    	ArrayList<Artista> listaAutores = new ArrayList<Artista>();
    	for (String nombre:nombresAutores) {
    		if(artistas.containsKey(nombre)) {
    			listaAutores.add(artistas.get(nombre));
    		}else {
    			Artista nuevoArtista = new Artista(nombre);
    			artistas.put(nombre, nuevoArtista);
    			listaAutores.add(nuevoArtista);
    		}
    	}
    	Pieza impresion = new Impresion(titulo, anio, lugar, listaAutores, exhibida, true, ancho, alto, tecnica);
    	unAdmin.registrarPieza(impresion, unInventario);
    }
    
    public static void registrarVideo(String titulo,int anio,String lugar,String[] nombresAutores,
    		boolean exhibida,double duracion,String idioma) throws PiezaDuplicadaException {
    	ArrayList<Artista> listaAutores = new ArrayList<Artista>();
    	for (String nombre:nombresAutores) {
    		if(artistas.containsKey(nombre)) {
    			listaAutores.add(artistas.get(nombre));
    		}else {
    			Artista nuevoArtista = new Artista(nombre);
    			artistas.put(nombre, nuevoArtista);
    			listaAutores.add(nuevoArtista);
    		}
    	}
    	Pieza video = new Video(titulo, anio, lugar, listaAutores, exhibida, true, idioma, duracion);
    	unAdmin.registrarPieza(video, unInventario);
    }
    
    public static boolean confirmarVenta(String titulo) {
    	if(titulo == null) {return false;}
    	for(Pieza pieza:unInventario.getPiezasBloqueadas()) {
    		if(pieza.getTitulo().equals(titulo)) {
    			return unAdmin.confirmarVenta((VentaFija) pieza.getVenta(), unInventario);
    		}
    	}
    	return false;
    }
    
    public static Comprador consultarComprador(String usuario) {
    	Comprador elComprador = compradores.get(usuario);
    	return elComprador;
    }
    
    public static void verificarComprador(Comprador elComprador) {
    	unAdmin.verificarComprador(elComprador);
    }
    
    public static Artista consultarArtista(String nombre) {
    	Artista elArtista = artistas.get(nombre);
    	return elArtista;
    }
    
    public static ArrayList<String> consultarPieza(String titulo) {
    	Pieza pieza = unInventario.buscarPiezaPorTitulo(titulo, unInventario.getPiezasBloqueadas());
    	if (pieza == null) {
    		pieza = unInventario.buscarPiezaPorTitulo(titulo,unInventario.getPiezasDisponibles());
    	}
    	ArrayList<String> lista = pieza.obtenerHistoriaPieza(pieza);
    	return lista;
    }
    
    //USUARIO
    
    public static boolean comprarPieza(String titulo) {
    	Pieza laPieza = unInventario.buscarPiezaPorTitulo(titulo, unInventario.getPiezasDisponibles());
    	if (laPieza == null) {
    		return false;
    	}
    	unComprador.comprarPieza(laPieza, unInventario);
    	return true;
    }
    
    public static boolean participarEnSubasta(int index, String[] credenciales) {
    	Comprador comprador = compradores.get(credenciales[0]);
    	boolean inscrito;
    	if(comprador.isVerificado()) {
    		ListaDeSubastas lista = subastasAbiertas.get(index);
    		lista.agregarParticipante(comprador);
    		inscrito = true;
    	} else {
    		inscrito = false;
    	}
    	return inscrito;
    }
}
