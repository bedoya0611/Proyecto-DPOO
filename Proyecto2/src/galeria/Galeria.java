package galeria;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONException;

import galeria.Exceptions.FormatoIncorrectoException;
import galeria.compradores.Comprador;
import galeria.inventario.Inventario;
import galeria.persistencia.Persistencia;
import galeria.usuarios.Admin;
import galeria.usuarios.Empleado;

public class Galeria
{
    private static Admin unAdmin;
    private static Inventario unInventario;
    private static Empleado unEmpleado;
    private static Comprador unComprador;
    private static Persistencia persistencia;
    private static HashMap<String, Comprador> compradores;

    /**
     * Es un método que corre la aplicación y realmente no hace nada interesante: sólo muestra cómo se podría utilizar la clase Aerolínea para hacer pruebas.
     * @throws IOException 
     * @throws Exception 
     * @throws JSONException 
     */
    
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

    
    public static boolean verificarCredencialesAdmin( String usuario, String psswd ){
    	if (unAdmin == null){
    		return false;
    	}
    	if (!unAdmin.getLogin().equals(usuario)) {return false;}
    	return unAdmin.verificarPassword(psswd);
    }
    
    public static boolean verificarCredencialesUsuario(String usuario, String psswd) {
    	unComprador = compradores.get(usuario);
    	if (unComprador == null) {
    		return false;
    	}
    	return unComprador.verificarPasswd(psswd);
    }
}
