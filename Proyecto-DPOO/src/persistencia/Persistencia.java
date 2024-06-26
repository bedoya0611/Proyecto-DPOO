package persistencia;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import inventario.Escultura;
import inventario.Fotografia;
import inventario.Impresion;
import inventario.Inventario;
import inventario.Pieza;
import inventario.Pintura;
import inventario.Video;
import usuarios.Usuario;
import usuarios.Admin;
import compradores.Comprador;
import usuarios.Empleado;
public class Persistencia {

	private static String NOMBRE_ADMINISTRADOR="nombreAdmin";
	private static String ID_ADMINISTRADOR="idAdmin";
	private static String LOGIN_ADMINISTRADOR="loginAdmin";
	private static String PSSWD_ADMINISTRADOR="psswdAdmin";
	private static String NOMBRE_EMPLEADO="nombreEmpleado";
	private static String ID_EMPLEADO="idEmpleado";
	private static String LOGIN_EMPLEADO="loginEmpleado";
	private static String PSSWD_EMPLEADO="psswdEmpleado";
	private static String CARGO_EMPLEADO="cargo";
	private static String TIPO_PIEZA="tipo";
	private static String TITULO_PIEZA="titulo";
	private static String ANIO_PIEZA="anio";
	private static String LUGAR_PIEZA="lugar";
	private static String AUTORES_PIEZA="autores";
	private static String EXHIBIDA="exhibida";
	private static String DISPONIBLE="disponible";
	private static String NOMBRE_COMPRADOR="nombreComprador";
	private static String LOGIN_COMPRADOR="loginComprador";
	private static String PSSWD_COMPRADOR="psswdComprador";
	private static String VERIFICADO="verificado";
	private static String ID_COMPRADOR="idComprador";
	private static String TELEFONO_COMPRADOR="telefonoComprador";

	public Persistencia(){}

	public void cargarGaleria(String archivo) throws JSONException, Exception {
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );
        Admin administrador = cargarAdministrador(raiz.getJSONObject( "administrador" ));
        cargarEmpleados(administrador, raiz.getJSONArray("empleados"));
        cargarInventario(administrador, raiz.getJSONArray("piezas"));
	}
	
	public void salvarGaleria( String archivo, Admin administrador, Inventario inventario) throws IOException
    {
        JSONObject jobject = new JSONObject( );
        salvarAdministrador( administrador, jobject );
        salvarEmpleados(administrador, jobject);
        salvarInventario(inventario, jobject);
        PrintWriter pw = new PrintWriter( archivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
    }
	
	public Admin cargarAdministrador(JSONObject jAdmin) {
		String nombreAdmin = jAdmin.getString(NOMBRE_ADMINISTRADOR);
		int idAdmin = Integer.valueOf(jAdmin.getString(ID_ADMINISTRADOR));
		String loginAdmin = jAdmin.getString(LOGIN_ADMINISTRADOR);
		String psswdAdmin = jAdmin.getString(PSSWD_ADMINISTRADOR);
		return new Admin(loginAdmin, psswdAdmin, nombreAdmin, idAdmin);
	}
	
	public void salvarAdministrador(Admin administrador, JSONObject jobject) {
		JSONObject jAdmin = new JSONObject();
		jAdmin.put(NOMBRE_ADMINISTRADOR, administrador.getNombre());
		jAdmin.put(ID_ADMINISTRADOR, administrador.getId());
		jAdmin.put(LOGIN_ADMINISTRADOR, administrador.getLogin());
		jAdmin.put(PSSWD_ADMINISTRADOR, administrador.getPassword());
		jobject.put("Administrador",jAdmin);
	}
	
	public void cargarEmpleados(Admin administrador, JSONArray jEmpleados) {
		int numEmpleados = jEmpleados.length();
		for (int i=0; 0<numEmpleados; i++) {
			JSONObject empleado = jEmpleados.getJSONObject(i);
			String nombreEmpleado = empleado.getString(NOMBRE_EMPLEADO);
			int idEmpleado = Integer.valueOf(empleado.getString(ID_EMPLEADO));
			String loginEmpleado = empleado.getString(LOGIN_EMPLEADO);
			String psswdEmpleado = empleado.getString(PSSWD_EMPLEADO);
			String cargoEmpleado = empleado.getString(CARGO_EMPLEADO);
			administrador.agregarEmpleado(nombreEmpleado, idEmpleado,
						  loginEmpleado,psswdEmpleado, cargoEmpleado);
		}
	}
		
	public void salvarEmpleados(Admin administrador, JSONObject jobject) {
		JSONArray jEmpleados = new JSONArray();
		for (Empleado empleado : administrador.getEmpleados()) {
			JSONObject jEmpleado = new JSONObject();
			jEmpleado.put(NOMBRE_EMPLEADO, empleado.getNombre());
			jEmpleado.put(ID_EMPLEADO, empleado.getIdEmpleado());
			jEmpleado.put(LOGIN_EMPLEADO, ((Usuario) empleado).getLogin());
			jEmpleado.put(PSSWD_EMPLEADO, ((Usuario) empleado).getPassword());
			jEmpleado.put(CARGO_EMPLEADO, empleado.getCargo());
			jEmpleados.put(jEmpleado);
		}
		jobject.put("Empleados", jEmpleados);
	}
	
	public void cargarInventario(Admin administrador, JSONArray jPiezas) throws Exception {
		Inventario inventario = new Inventario();
		int numPiezas = jPiezas.length();
		for(int i=0; i<numPiezas; i++) {
			JSONObject pieza = jPiezas.getJSONObject(i);
			String tipo = pieza.getString(TIPO_PIEZA);
			String titulo = pieza.getString(TITULO_PIEZA);
			int anio = Integer.valueOf(pieza.getString(ANIO_PIEZA));
			String lugar = pieza.getString(LUGAR_PIEZA);
			ArrayList<String> listaAutores = new ArrayList<String>();
			JSONArray autores = pieza.getJSONArray(AUTORES_PIEZA);
			int numAutores = autores.length();
			for(int j=0;j<numAutores; j++) {
				JSONObject autor = autores.getJSONObject(j);
				String nombre = autor.getString("nombre");
				listaAutores.add(nombre);
			}
			boolean exhibida = Boolean.getBoolean(pieza.getString(EXHIBIDA));
			boolean disponible = Boolean.getBoolean(pieza.getString(DISPONIBLE));
			Pieza nuevaPieza = null;
			if (tipo.equals("Pintura")) {
				double ancho = Double.valueOf(pieza.getString("ancho"));
				double alto = Double.valueOf(pieza.getString("alto"));
				String estilo = pieza.getString("estilo");
				String tecnica = pieza.getString("tecnica");
				nuevaPieza = new Pintura(titulo, anio, lugar, listaAutores,
						exhibida, disponible, ancho, alto, tecnica, estilo);				
			}else if (tipo.equals("Escultura")) {
				double ancho = Double.valueOf(pieza.getString("ancho"));
				double alto = Double.valueOf(pieza.getString("alto"));
				double profundidad = Double.valueOf(pieza.getString("profundidad"));
				String material = pieza.getString("material");
				double peso = Double.valueOf(pieza.getString("peso"));
				boolean necesitaElectricidad = Boolean.getBoolean(pieza.getString("necesitaElectricidad"));
				String detalles = pieza.getString("detallesInstalacion");
				nuevaPieza = new Escultura(titulo, anio, lugar, listaAutores,
						exhibida, disponible, ancho, alto, profundidad, material,
						peso, necesitaElectricidad, detalles);
			}else if (tipo.equals("Fotografia")) {
				double ancho = Double.valueOf(pieza.getString("ancho"));
				double alto = Double.valueOf(pieza.getString("alto"));
				String camara = pieza.getString("camara");
				nuevaPieza = new Fotografia(titulo, anio, lugar, listaAutores,
						exhibida, disponible, ancho, alto, camara);
			}else if (tipo.equals("Impresion")) {
				double ancho = Double.valueOf(pieza.getString("ancho"));
				double alto = Double.valueOf(pieza.getString("alto"));
				String tecnica = pieza.getString("tecnica");
				nuevaPieza = new Impresion(titulo, anio, lugar, listaAutores,
						exhibida, disponible, ancho, alto, tecnica );
			}else if (tipo.equals("Video")) {
				String idioma = pieza.getString("idioma");
				double duracion = Double.valueOf(pieza.getString("duracion"));
				nuevaPieza = new Video(titulo, anio, lugar, listaAutores,
						exhibida, disponible, idioma, duracion );
			}
			if (nuevaPieza!=null) {
				administrador.registrarPieza(nuevaPieza, inventario);
			}else {
				throw new Exception("Tipo de Pieza inválido");
			}
		}
	}
	//VAS ACÁ
	public void salvarInventario(Inventario inventario, JSONObject jobject) {
		JSONArray jInventario = new JSONArray( );
        for( Pieza pieza : inventario.getPiezasDisponibles( ) )
        {
        	JSONObject jPieza = new JSONObject( );
            if( pieza.getTipoPieza().equals("Escultura") )
            {
            	jPieza.put( TIPO_PIEZA, "Escultura");
            	jPieza.put( TITULO_PIEZA, pieza.getTitulo() );
            	jPieza.put( ANIO_PIEZA, String.valueOf(pieza.getAnio()) );
            	jPieza.put( LUGAR_PIEZA, pieza.getLugarCreacion());
            	jPieza.put( AUTORES_PIEZA, new JSONArray(pieza.getAutores()) );
            	jPieza.put( EXHIBIDA, pieza.isExhibida() );
            	jPieza.put( DISPONIBLE, pieza.isDisponible() );
            	jPieza.put( "ancho", String.valueOf(((Escultura) pieza).getAncho()) );
            	jPieza.put( "alto", String.valueOf(((Escultura) pieza).getAlto()) );
            	jPieza.put( "profundidad", String.valueOf(((Escultura) pieza).getProfundidad()) );
            	jPieza.put( "material", ((Escultura) pieza).getMaterial());
            	jPieza.put( "peso", String.valueOf(((Escultura) pieza).getPeso()));
            	jPieza.put( "necesitaElectricidad", String.valueOf(((Escultura) pieza).getPeso()));
            	jPieza.put( "detalles", ((Escultura) pieza).getDetallesInstalacion());
                jInventario.put( jPieza );
            }
            else if(pieza.getTipoPieza().equals("Fotografia"))
            {
            	jPieza.put( TIPO_PIEZA, "Fotografia");
            	jPieza.put( TITULO_PIEZA, pieza.getTitulo() );
            	jPieza.put( ANIO_PIEZA, String.valueOf(pieza.getAnio()) );
            	jPieza.put( LUGAR_PIEZA, pieza.getLugarCreacion());
            	jPieza.put( AUTORES_PIEZA, new JSONArray(pieza.getAutores()) );
            	jPieza.put( EXHIBIDA, pieza.isExhibida() );
            	jPieza.put( DISPONIBLE, pieza.isDisponible() );
            	jPieza.put( "ancho", String.valueOf(((Fotografia) pieza).getAncho()) );
            	jPieza.put( "alto", String.valueOf(((Fotografia) pieza).getAlto()) );
            	jPieza.put( "camara", ((Fotografia)pieza).getCamara());
            	jInventario.put(jPieza);
            }
            else if(pieza.getTipoPieza().equals("Impresion"))
            {
            	jPieza.put( TIPO_PIEZA, "Impresion");
            	jPieza.put( TITULO_PIEZA, pieza.getTitulo() );
            	jPieza.put( ANIO_PIEZA, String.valueOf(pieza.getAnio()) );
            	jPieza.put( LUGAR_PIEZA, pieza.getLugarCreacion());
            	jPieza.put( AUTORES_PIEZA, new JSONArray(pieza.getAutores()) );
            	jPieza.put( EXHIBIDA, pieza.isExhibida() );
            	jPieza.put( DISPONIBLE, pieza.isDisponible() );
            	jPieza.put( "ancho", String.valueOf(((Impresion) pieza).getAncho()) );
            	jPieza.put( "alto", String.valueOf(((Impresion) pieza).getAlto()) );
            	jPieza.put( "tecnica", ((Impresion)pieza).getTecnica());
            	jInventario.put(jPieza);
            }
            else if(pieza.getTipoPieza().equals("Pintura"))
            {
            	jPieza.put( TIPO_PIEZA, "Pintura");
            	jPieza.put( TITULO_PIEZA, pieza.getTitulo() );
            	jPieza.put( ANIO_PIEZA, String.valueOf(pieza.getAnio()) );
            	jPieza.put( LUGAR_PIEZA, pieza.getLugarCreacion());
            	jPieza.put( AUTORES_PIEZA, new JSONArray(pieza.getAutores()) );
            	jPieza.put( EXHIBIDA, pieza.isExhibida() );
            	jPieza.put( DISPONIBLE, pieza.isDisponible() );
            	jPieza.put( "ancho", String.valueOf(((Pintura) pieza).getAncho()) );
            	jPieza.put( "alto", String.valueOf(((Pintura) pieza).getAlto()) );
            	jPieza.put( "estilo", ((Pintura)pieza).getEstilo());
            	jPieza.put( "tecnica", ((Pintura)pieza).getTecnica());
            	jInventario.put(jPieza);
            }
            else if(pieza.getTipoPieza().equals("Video"))
            {
            	jPieza.put( TIPO_PIEZA, "Video");
            	jPieza.put( TITULO_PIEZA, pieza.getTitulo() );
            	jPieza.put( ANIO_PIEZA, String.valueOf(pieza.getAnio()) );
            	jPieza.put( LUGAR_PIEZA, pieza.getLugarCreacion());
            	jPieza.put( AUTORES_PIEZA, new JSONArray(pieza.getAutores()) );
            	jPieza.put( EXHIBIDA, pieza.isExhibida() );
            	jPieza.put( DISPONIBLE, pieza.isDisponible() );
            	jPieza.put( "duracion", String.valueOf(((Video) pieza).getDuracion()) );
            	jPieza.put( "idioma", ((Video)pieza).getIdioma());
            	jInventario.put(jPieza);
            }
        }

        jobject.put( "piezas", jInventario );
	}
	
	public ArrayList<Comprador> cargarCompradores(String archivo) throws IOException{
		ArrayList<Comprador> listaCompradores = new ArrayList<Comprador>();
		String jsonCompleto = new String( Files.readAllBytes( new File( archivo ).toPath( ) ) );
        JSONObject raiz = new JSONObject( jsonCompleto );
        JSONArray compradores = raiz.getJSONArray("compradores");
        int numCompradores = compradores.length();
        for(int i=0; i<numCompradores; i++) {
        	JSONObject jComprador = compradores.getJSONObject(i);
        	String nombre = jComprador.getString(NOMBRE_COMPRADOR);
        	String login = jComprador.getString(LOGIN_COMPRADOR);
        	String psswd = jComprador.getString(PSSWD_COMPRADOR);
        	boolean verificado = Boolean.valueOf(jComprador.getString(VERIFICADO));
        	int id = Integer.valueOf(jComprador.getString(ID_COMPRADOR));
        	int telefono = Integer.valueOf(jComprador.getString(TELEFONO_COMPRADOR)); 
        	Comprador nuevoComprador = new Comprador(verificado, nombre, id, telefono, login, psswd);
        	listaCompradores.add(nuevoComprador);
        }
        return listaCompradores;
	}
	
	public void salvarCompradores(ArrayList<Comprador> listaCompradores, JSONObject jobject) {
		JSONArray jCompradores = new JSONArray();
		for(Comprador comprador : listaCompradores) {
			JSONObject jComprador = new JSONObject();
			jComprador.put(NOMBRE_COMPRADOR, comprador.getNombre());
			jComprador.put(ID_COMPRADOR, String.valueOf(comprador.getIdentificador()));
			jComprador.put(VERIFICADO, String.valueOf(comprador.isVerificado()));
			jComprador.put(LOGIN_COMPRADOR, comprador.getLogin());
			jComprador.put(PSSWD_COMPRADOR, comprador.getPassword());
			jComprador.put(TELEFONO_COMPRADOR, String.valueOf(comprador.getTelefono()));
			jCompradores.put(jComprador);
		}
		jobject.put("compradores", jCompradores);
	}
}
