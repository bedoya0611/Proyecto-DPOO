package galeria;

import java.io.IOException;

import org.json.JSONException;

import galeria.Exceptions.FormatoIncorrectoException;
import galeria.inventario.Inventario;
import galeria.persistencia.Persistencia;
import galeria.usuarios.Admin;

public class Galeria
{
    private static Admin unAdmin;
    private static Inventario unInventario;
    private static Persistencia persistencia;

    /**
     * Es un método que corre la aplicación y realmente no hace nada interesante: sólo muestra cómo se podría utilizar la clase Aerolínea para hacer pruebas.
     * @throws Exception 
     * @throws JSONException 
     */

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

    public static void main( String[] args ) throws JSONException, Exception
    {
        Galeria galeria = new Galeria();
        galeria.correrAplicacion( );
    }
}
