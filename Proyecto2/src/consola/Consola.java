package consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import galeria.Galeria;
import galeria.Exceptions.FormatoIncorrectoException;
import galeria.compradores.Comprador;
import galeria.inventario.Inventario;
import galeria.persistencia.Persistencia;
import galeria.usuarios.Admin;

public abstract class Consola {
	
	String archivoGaleria = "datosGaleria.json"; 
	String archivoUsuarios = "datosUsuarios.json"; 
	
	public void cargarArchivo(Persistencia persistencia) throws JSONException, Exception{
		try
        {
			Galeria.cargarCompradores("./datos/" + archivoUsuarios);
            persistencia.cargarGaleria( "./datos/" + archivoGaleria);
        } catch (FormatoIncorrectoException e) {
        	e.printStackTrace( );
        }
	}
	
	public void guardarArchivo(Persistencia persistencia, Admin administrador, Inventario inventario,
				ArrayList<Comprador> compradores) {
		try {
			persistencia.salvarGaleria("./datos/"+archivoGaleria, administrador, inventario);
			persistencia.salvarCompradores("./datos/"+archivoUsuarios, compradores);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String[] credenciales(BufferedReader reader) {
		System.out.println("------------------------------------------------");
		System.out.println("\tIngrese sus credenciales:");
		System.out.println("------------------------------------------------");
		System.out.println("Usuario: ");
		String usuario = "", passwd = "";
		try {
			usuario = reader.readLine();
			System.out.println("Contraseña: ");
			passwd = reader.readLine();
		} catch (IOException e) {
			System.err.println("Error al leer las credenciales");
			e.printStackTrace();
		}
		String[] credenciales = {usuario, passwd};
		return credenciales;
	}
}