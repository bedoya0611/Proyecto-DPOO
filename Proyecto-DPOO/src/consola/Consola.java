package consola;

import java.io.IOException;

import org.json.JSONException;

import Exceptions.FormatoIncorrectoException;
import inventario.Inventario;
import persistencia.Persistencia;
import usuarios.Admin;

public class Consola
{
    private Admin unAdmin;
    private Inventario unInventario;
    private Persistencia persistencia;

    /**
     * Es un método que corre la aplicación y realmente no hace nada interesante: sólo muestra cómo se podría utilizar la clase Aerolínea para hacer pruebas.
     * @throws Exception 
     * @throws JSONException 
     */

    public void correrAplicacion( ) throws JSONException, Exception
    {
        try
        {
            // String archivo = this.pedirCadenaAlUsuario( "Digite el nombre del archivo json con la información de una aerolinea" );
            String archivo = "datos.json"; 
            persistencia.cargarGaleria( "./datos/" + archivo);
        }
        catch( FormatoIncorrectoException e )
        {
            e.printStackTrace( );
        }
    }

    public static void main( String[] args ) throws JSONException, Exception
    {
        Consola consola = new Consola();
        consola.correrAplicacion( );
    }
}
