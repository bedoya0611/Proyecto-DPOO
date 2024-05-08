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
	
	private void imprimirMenu() {
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
			System.out.println("0 - Salir");
			try {
    			seleccion = Integer.valueOf(reader.readLine());
    			if (seleccion<0 || seleccion > 5) {throw new Exception();}
    		} catch (Exception e) {
    			System.out.println("Selección incorrecta");
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
