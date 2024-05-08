package consola;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.json.JSONException;

import galeria.Galeria;
import galeria.persistencia.Persistencia;

public class ConsolaEmpleado extends Consola{
	
	public BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	public Persistencia persistencia = new Persistencia();
	
	public ConsolaEmpleado() {
		
	}
	
	private boolean[] verificarCredenciales(String usuario, String psswd) {
		return Galeria.verificarCredencialesEmpleado(usuario, psswd);
	}
	
	private void imprimirMenu(boolean cargo) {
		int seleccion = 10;
		while (seleccion != 0) {
			System.out.println("------------------------------------------------");
			System.out.println("\t\t   Menú Empleado");
			System.out.println("------------------------------------------------");
			if (cargo) {
				//Operador
				while (seleccion !=10) {
					System.out.println("1 - Registrar Subasta");
					System.out.println("2 - Realizar Subasta");
					System.out.println("0 - Salir");
					
		    		try {
		    			seleccion = Integer.valueOf(reader.readLine());
		    			if (seleccion<0 || seleccion > 2) {throw new Exception();}
		    		} catch (Exception e) {
		    			System.out.println("Selección incorrecta");
		    		}
				}
	    		if (seleccion == 1) {
	    			
	    		}else if (seleccion == 2) {
	    			
	    		}
			}else {
				//Cajero
				while (seleccion !=10) {
					System.out.println("1 - Registrar Pago");
					System.out.println("0 - Salir");
					
		    		try {
		    			seleccion = Integer.valueOf(reader.readLine());
		    			if (seleccion < 0 || seleccion > 1) {throw new Exception();}
		    		} catch (Exception e) {
		    			System.out.println("Selección incorrecta");
		    		}
				}
	    		if (seleccion == 1) {
	    			
	    		}while (seleccion !=10) {
					System.out.println("1 - Realizar Subasta");
					System.out.println("2 - Registrar Subasta");
					System.out.println("0 - Salir");
					
		    		try {
		    			seleccion = Integer.valueOf(reader.readLine());
		    			if (seleccion<0 || seleccion > 2) {throw new Exception();}
		    		} catch (Exception e) {
		    			System.out.println("Selección incorrecta");
		    		}
				}
	    		if (seleccion == 1) {
	    			
	    		}else if (seleccion == 2) {
	    			
	    		}
			}
    	}
	}
	
    public static void main( String[] args ) throws JSONException, Exception
    {
    	ConsolaEmpleado consola = new ConsolaEmpleado();
    	System.out.println("\t\tCONSOLA DE EMPLEADO");
    	consola.cargarArchivo(consola.persistencia);
    	boolean credencialesCorrectas = false;
    	boolean[] infoEmpleado = null;
    	while (!credencialesCorrectas) {
    		String[] credenciales = consola.credenciales(consola.reader);
    		infoEmpleado = consola.verificarCredenciales(credenciales[0], credenciales[1]);
    		credencialesCorrectas = infoEmpleado[0];
    		if (!credencialesCorrectas) {
    			System.err.println("Usuario o contraseña inválido, intente nuevamente");
    		}
    	}
    	boolean cargoEmpleado =  infoEmpleado[1];
    	consola.imprimirMenu(cargoEmpleado);
    }

}
