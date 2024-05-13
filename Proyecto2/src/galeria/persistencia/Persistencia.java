package galeria.persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import galeria.inventario.Escultura;
import galeria.inventario.Fotografia;
import galeria.inventario.Impresion;
import galeria.inventario.Inventario;
import galeria.inventario.Pieza;
import galeria.inventario.Pintura;
import galeria.inventario.Video;
import galeria.usuarios.Usuario;
import galeria.usuarios.Admin;
import galeria.usuarios.Artista;
import galeria.compradores.Comprador;
import galeria.compradores.Propietario;
import galeria.Galeria;
import galeria.usuarios.Empleado;
import galeria.ventas.*;
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
	private static String NOMBRE_AUTOR="nombreAutor";
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
        Galeria.setAdmin(administrador);
        Inventario inventario = cargarInventario(administrador, raiz.getJSONArray("piezas"));
        cargarSubastas(inventario, raiz.getJSONArray("subastasAbiertas"));
	}
	
	public void salvarGaleria( String archivo, Admin administrador, Inventario inventario) throws IOException
    {
        JSONObject jobject = new JSONObject( );
        salvarAdministrador( administrador, jobject );
        salvarEmpleados(administrador, jobject);
        salvarInventario(inventario, jobject);
        salvarSubastas(inventario, jobject);
        PrintWriter pw = new PrintWriter( archivo );
        jobject.write( pw, 2, 0 );
        pw.close( );
    }
	
	public Admin cargarAdministrador(JSONObject jAdmin) {
		String nombreAdmin = jAdmin.getString(NOMBRE_ADMINISTRADOR);
		int idAdmin = jAdmin.getInt(ID_ADMINISTRADOR);
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
		jobject.put("administrador",jAdmin);
	}
	
	public void cargarEmpleados(Admin administrador, JSONArray jEmpleados) {
		int numEmpleados = jEmpleados.length();
		for (int i=0; i<numEmpleados; i++) {
			JSONObject empleado = jEmpleados.getJSONObject(i);
			String nombreEmpleado = empleado.getString(NOMBRE_EMPLEADO);
			int idEmpleado = empleado.getInt(ID_EMPLEADO);
			String loginEmpleado = empleado.getString(LOGIN_EMPLEADO);
			String psswdEmpleado = empleado.getString(PSSWD_EMPLEADO);
			String cargoEmpleado = empleado.getString(CARGO_EMPLEADO);
			administrador.agregarEmpleado(nombreEmpleado, idEmpleado,
						  loginEmpleado,psswdEmpleado, cargoEmpleado);
		}
	}
		
	public void salvarEmpleados(Admin administrador, JSONObject jobject) {
		JSONArray jEmpleados = new JSONArray();
		ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>(administrador.getEmpleados().values());
		for (Empleado empleado : listaEmpleados) {
			JSONObject jEmpleado = new JSONObject();
			jEmpleado.put(NOMBRE_EMPLEADO, empleado.getNombre());
			jEmpleado.put(ID_EMPLEADO, empleado.getIdEmpleado());
			jEmpleado.put(LOGIN_EMPLEADO, ((Usuario) empleado).getLogin());
			jEmpleado.put(PSSWD_EMPLEADO, ((Usuario) empleado).getPassword());
			jEmpleado.put(CARGO_EMPLEADO, empleado.getCargo());
			jEmpleados.put(jEmpleado);
		}
		jobject.put("empleados", jEmpleados);
	}
	
	public Inventario cargarInventario(Admin administrador, JSONArray jPiezas) throws Exception {
		Inventario inventario = new Inventario();
		ArrayList<Artista> artistas = new ArrayList<Artista>();
		int numPiezas = jPiezas.length();
		for(int i=0; i<numPiezas; i++) {
			JSONObject pieza = jPiezas.getJSONObject(i);
			String tipo = pieza.getString(TIPO_PIEZA);
			String titulo = pieza.getString(TITULO_PIEZA);
			int anio = pieza.getInt(ANIO_PIEZA);
			String lugar = pieza.getString(LUGAR_PIEZA);
			ArrayList<Artista> listaAutores = new ArrayList<Artista>();
			JSONArray autores = pieza.getJSONArray(AUTORES_PIEZA);
			int numAutores = autores.length();
			for(int j=0;j<numAutores; j++) {
				JSONObject autor = autores.getJSONObject(j);
				Artista artista = cargarArtista(autor);
				listaAutores.add(artista);
			}
			artistas.addAll(listaAutores);
			boolean exhibida = pieza.getBoolean(EXHIBIDA);
			boolean disponible = pieza.getBoolean(DISPONIBLE);
			String propietario = pieza.getString("propietario");
			Pieza nuevaPieza = null;
			JSONObject jVenta = pieza.getJSONObject("venta");
			String tipoVenta = jVenta.getString("tipoVenta");
			Venta venta = null;
			if (tipoVenta.equals("Subasta")) {
				int vInicial = jVenta.getInt("valorInicial");
				int vMinimo = jVenta.getInt("valorMinimo");
				int ofertaActual = jVenta.getInt("ofertaActual");
				boolean abierta = jVenta.getBoolean("abierta");
				venta = new Subasta(vInicial, vMinimo, ofertaActual,null);
				((Subasta) venta).setAbierta(abierta);
			}else if (tipoVenta.equals("Fija")) {
				int precio = jVenta.getInt("precioFijo");
				String comprador = jVenta.getString("comprador");
				Comprador comprador1 = Galeria.getCompradores().get(comprador);
				venta = new VentaFija(precio, comprador1);
			}
			
			if (tipo.equals("Pintura")) {
				double ancho = pieza.getDouble("ancho");
				double alto = pieza.getDouble("alto");
				String estilo = pieza.getString("estilo");
				String tecnica = pieza.getString("tecnica");
				nuevaPieza = new Pintura(titulo, anio, lugar, listaAutores,
						exhibida, disponible, ancho, alto, tecnica, estilo);				
			}else if (tipo.equals("Escultura")) {
				double ancho = pieza.getDouble("ancho");
				double alto = pieza.getDouble("alto");
				double profundidad = pieza.getDouble("profundidad");
				String material = pieza.getString("material");
				double peso = pieza.getDouble("peso");
				boolean necesitaElectricidad = pieza.getBoolean("necesitaElectricidad");
				String detalles = pieza.getString("detallesInstalacion");
				nuevaPieza = new Escultura(titulo, anio, lugar, listaAutores,
						exhibida, disponible, ancho, alto, profundidad, material,
						peso, necesitaElectricidad, detalles);
			}else if (tipo.equals("Fotografia")) {
				double ancho = pieza.getDouble("ancho");
				double alto = pieza.getDouble("alto");
				String camara = pieza.getString("camara");
				nuevaPieza = new Fotografia(titulo, anio, lugar, listaAutores,
						exhibida, disponible, ancho, alto, camara);
			}else if (tipo.equals("Impresion")) {
				double ancho = pieza.getDouble("ancho");
				double alto = pieza.getDouble("alto");
				String tecnica = pieza.getString("tecnica");
				nuevaPieza = new Impresion(titulo, anio, lugar, listaAutores,
						exhibida, disponible, ancho, alto, tecnica );
			}else if (tipo.equals("Video")) {
				String idioma = pieza.getString("idioma");
				double duracion = pieza.getDouble("duracion");
				nuevaPieza = new Video(titulo, anio, lugar, listaAutores,
						exhibida, disponible, idioma, duracion );
			}
			if (nuevaPieza!=null) {
				if (venta != null) {
					venta.setPieza(nuevaPieza);
					nuevaPieza.setVenta(venta);
				}
				administrador.registrarPieza(nuevaPieza, inventario);
			}else {
				throw new Exception("Tipo de Pieza inválido");
			}
			if(!propietario.equals("")) {
			nuevaPieza.setPropietario((Propietario) Galeria.getCompradores().get(propietario));
			}
		}
		Galeria.setInventario(inventario);
		Galeria.setArtistas(artistas);
		return inventario;
	}


	
	public void salvarInventario(Inventario inventario, JSONObject jobject) {
		JSONArray jInventario = new JSONArray( );
		ArrayList<Pieza> totalPiezas = inventario.getPiezasDisponibles();
		totalPiezas.addAll(inventario.getPiezasBloqueadas());
        for( Pieza pieza : totalPiezas)
        {
        	JSONObject jPieza = new JSONObject( );
        	Venta venta = pieza.getVenta();
        	JSONObject jVenta = new JSONObject();
        	if (venta!=null) {
        		String tipoVenta = venta.getTipo();
        		jVenta.put("tipoVenta", tipoVenta);
        		if(tipoVenta.equals("Subasta")) {
        			jVenta.put("valorInicial", ((Subasta) venta).getValorInicial());
        			jVenta.put("valorMinimo", ((Subasta) venta).getValorMinimo());
        			jVenta.put("ofertaActual", ((Subasta) venta).getOfertaActual());
        			jVenta.put("abierta", ((Subasta) venta).isAbierta());
        		} else {
        			jVenta.put("precioFijo", ((VentaFija) venta).getPrecioFijo());
        		}
        	}else {
        		jVenta.put("tipoVenta", "");
        	}
        	jPieza.put("venta", jVenta);
            if( pieza.getTipoPieza().equals("Escultura") )
            {
            	jPieza.put( TIPO_PIEZA, "Escultura");
            	jPieza.put( TITULO_PIEZA, pieza.getTitulo());
            	jPieza.put( ANIO_PIEZA, pieza.getAnio());
            	jPieza.put( LUGAR_PIEZA, pieza.getLugarCreacion());
            	JSONArray jautores = new JSONArray();
            	for (Artista autor: pieza.getAutores()) {
            		JSONObject jautor = new JSONObject();
            		jautor.put(NOMBRE_AUTOR, autor.getNombre());
            		jautores.put(jautor);
            	}
            	jPieza.put( AUTORES_PIEZA, jautores);
            	jPieza.put( EXHIBIDA, pieza.isExhibida());
            	jPieza.put( DISPONIBLE, pieza.isDisponible());
            	jPieza.put( "ancho", ((Escultura) pieza).getAncho());
            	jPieza.put( "alto", ((Escultura) pieza).getAlto());
            	jPieza.put( "profundidad", ((Escultura) pieza).getProfundidad());
            	jPieza.put( "material", ((Escultura) pieza).getMaterial());
            	jPieza.put( "peso", ((Escultura) pieza).getPeso());
            	jPieza.put( "necesitaElectricidad", ((Escultura) pieza).isNecesitaElectricidad());
            	jPieza.put( "detallesInstalacion", ((Escultura) pieza).getDetallesInstalacion());
            	Propietario propietario = pieza.getPropietario();
            	if(propietario != null) {
            		jPieza.put("propietario", propietario.getLogin());
            	}else {
            		jPieza.put("propietario", "");
            	}
            	if(propietario != null) {
            		jPieza.put("propietario", propietario.getLogin());
            	}else {
            		jPieza.put("propietario", "");
                }
            	jInventario.put( jPieza );
            }
            else if(pieza.getTipoPieza().equals("Fotografia"))
            {
            	jPieza.put( TIPO_PIEZA, "Fotografia");
            	jPieza.put( TITULO_PIEZA, pieza.getTitulo() );
            	jPieza.put( ANIO_PIEZA, pieza.getAnio());
            	jPieza.put( LUGAR_PIEZA, pieza.getLugarCreacion());
            	JSONArray jautores = new JSONArray();
            	for (Artista autor: pieza.getAutores()) {
            		JSONObject jautor = new JSONObject();
            		jautor.put(NOMBRE_AUTOR, autor.getNombre());
            		jautores.put(jautor);
            	}
            	jPieza.put( AUTORES_PIEZA, jautores);
            	jPieza.put( EXHIBIDA, pieza.isExhibida() );
            	jPieza.put( DISPONIBLE, pieza.isDisponible() );
            	jPieza.put( "ancho", ((Fotografia) pieza).getAncho());
            	jPieza.put( "alto", ((Fotografia) pieza).getAlto());
            	jPieza.put( "camara", ((Fotografia)pieza).getCamara());
            	Propietario propietario = pieza.getPropietario();
            	if(propietario != null) {
            		jPieza.put("propietario", propietario.getLogin());
            	}else {
            		jPieza.put("propietario", "");
            	}
            	jInventario.put(jPieza);
            }
            else if(pieza.getTipoPieza().equals("Impresion"))
            {
            	jPieza.put( TIPO_PIEZA, "Impresion");
            	jPieza.put( TITULO_PIEZA, pieza.getTitulo() );
            	jPieza.put( ANIO_PIEZA, pieza.getAnio());
            	jPieza.put( LUGAR_PIEZA, pieza.getLugarCreacion());
            	JSONArray jautores = new JSONArray();
            	for (Artista autor: pieza.getAutores()) {
            		JSONObject jautor = new JSONObject();
            		jautor.put(NOMBRE_AUTOR, autor.getNombre());
            		jautores.put(jautor);
            	}
            	jPieza.put( AUTORES_PIEZA, jautores);
            	jPieza.put( EXHIBIDA, pieza.isExhibida() );
            	jPieza.put( DISPONIBLE, pieza.isDisponible() );
            	jPieza.put( "ancho", ((Impresion) pieza).getAncho());
            	jPieza.put( "alto", ((Impresion) pieza).getAlto());
            	jPieza.put( "tecnica", ((Impresion)pieza).getTecnica());
            	Propietario propietario = pieza.getPropietario();
            	if(propietario != null) {
            		jPieza.put("propietario", propietario.getLogin());
            	}else {
            		jPieza.put("propietario", "");
            	}
            	jInventario.put(jPieza);
            }
            else if(pieza.getTipoPieza().equals("Pintura"))
            {
            	jPieza.put( TIPO_PIEZA, "Pintura");
            	jPieza.put( TITULO_PIEZA, pieza.getTitulo() );
            	jPieza.put( ANIO_PIEZA, pieza.getAnio());
            	jPieza.put( LUGAR_PIEZA, pieza.getLugarCreacion());
            	JSONArray jautores = new JSONArray();
            	for (Artista autor: pieza.getAutores()) {
            		JSONObject jautor = new JSONObject();
            		jautor.put(NOMBRE_AUTOR, autor.getNombre());
            		jautores.put(jautor);
            	}
            	jPieza.put( AUTORES_PIEZA, jautores);
            	jPieza.put( EXHIBIDA, pieza.isExhibida() );
            	jPieza.put( DISPONIBLE, pieza.isDisponible() );
            	jPieza.put( "ancho", ((Pintura) pieza).getAncho());
            	jPieza.put( "alto", ((Pintura) pieza).getAlto());
            	jPieza.put( "estilo", ((Pintura)pieza).getEstilo());
            	jPieza.put( "tecnica", ((Pintura)pieza).getTecnica());
            	Propietario propietario = pieza.getPropietario();
            	if(propietario != null) {
            		jPieza.put("propietario", propietario.getLogin());
            	}else {
            		jPieza.put("propietario", "");
            	}
            	jInventario.put(jPieza);
            }
            else if(pieza.getTipoPieza().equals("Video"))
            {
            	jPieza.put( TIPO_PIEZA, "Video");
            	jPieza.put( TITULO_PIEZA, pieza.getTitulo() );
            	jPieza.put( ANIO_PIEZA, pieza.getAnio());
            	jPieza.put( LUGAR_PIEZA, pieza.getLugarCreacion());
            	JSONArray jautores = new JSONArray();
            	for (Artista autor: pieza.getAutores()) {
            		JSONObject jautor = new JSONObject();
            		jautor.put(NOMBRE_AUTOR, autor.getNombre());
            		jautores.put(jautor);
            	}
            	jPieza.put( AUTORES_PIEZA, jautores);
            	jPieza.put( EXHIBIDA, pieza.isExhibida() );
            	jPieza.put( DISPONIBLE, pieza.isDisponible() );
            	jPieza.put( "duracion", ((Video) pieza).getDuracion());
            	jPieza.put( "idioma", ((Video)pieza).getIdioma());
            	Propietario propietario = pieza.getPropietario();
            	if(propietario != null) {
            		jPieza.put("propietario", propietario.getLogin());
            	}else {
            		jPieza.put("propietario", "");
            	}
            	jInventario.put(jPieza);
            }
        }

        jobject.put( "piezas", jInventario );
	}
	
	public Artista cargarArtista(JSONObject jobject) {
		String nombre = jobject.getString(NOMBRE_AUTOR);
		Artista artista = new Artista(nombre);
		return artista;
	}
	
	public void cargarSubastas(Inventario inventario, JSONArray jSubastas) {
		int numListas = jSubastas.length();
		ArrayList<ListaDeSubastas> abiertas = new ArrayList<ListaDeSubastas>();
		ListaDeSubastas nueva = null;
		for (int i=0; i<numListas; i++) {
			nueva = new ListaDeSubastas();
			JSONObject jLista = jSubastas.getJSONObject(i);
			JSONArray piezas = jLista.getJSONArray("piezas");
			ArrayList<Subasta> subastas = new ArrayList<Subasta>();
			for (int j=0; j<piezas.length(); j++) {
				String titulo = piezas.getString(j);
				Pieza laPieza = inventario.buscarPiezaPorTitulo(titulo,inventario.getPiezasDisponibles());
				subastas.add((Subasta) laPieza.getVenta());
			}
			nueva.setSubastas(subastas);
			
			JSONArray usuarios = jLista.getJSONArray("participantes");
			ArrayList<Comprador> participantes = new ArrayList<Comprador>();
			for (int j=0; j<usuarios.length(); j++) {
				String usuario = usuarios.getString(j);
				Comprador participante = Galeria.consultarComprador(usuario);
				participantes.add(participante);
			}
			nueva.setParticipantes(participantes);
			abiertas.add(nueva);
		}
		Galeria.setSubastasAbiertas(abiertas);
	}
	
	public void salvarSubastas(Inventario inventario, JSONObject jobject) {
		JSONArray abiertas = new JSONArray();
		for (ListaDeSubastas lista:Galeria.getSubastasAbiertas()) {
			JSONObject jLista = new JSONObject();
			ArrayList<String> titulos = new ArrayList<String>();
			for (Subasta subasta:lista.getSubastas()) {
				titulos.add(subasta.getPieza().getTitulo());
			}
			jLista.put("piezas", new JSONArray(titulos));
			ArrayList<String> usuarios = new ArrayList<String>();
			for (Comprador comprador:lista.getParticipantes()) {
				usuarios.add(comprador.getLogin());
			}
			jLista.put("participantes", new JSONArray(usuarios));
			abiertas.put(jLista);
		}
		jobject.put("subastasAbiertas", abiertas);
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
        	boolean verificado = jComprador.getBoolean(VERIFICADO);
        	int id = jComprador.getInt(ID_COMPRADOR);
        	int telefono = jComprador.getInt(TELEFONO_COMPRADOR); 
        	Comprador nuevoComprador = new Comprador(verificado, nombre, id, telefono, login, psswd);
        	listaCompradores.add(nuevoComprador);
        }
        return listaCompradores;
	}
	
	public void salvarCompradores(String archivo, ArrayList<Comprador> listaCompradores) throws FileNotFoundException {
		JSONObject jobject = new JSONObject( );
		JSONArray jCompradores = new JSONArray();
		for(Comprador comprador : listaCompradores) {
			JSONObject jComprador = new JSONObject();
			jComprador.put(NOMBRE_COMPRADOR, comprador.getNombre());
			jComprador.put(ID_COMPRADOR, comprador.getIdentificador());
			jComprador.put(VERIFICADO, comprador.isVerificado());
			jComprador.put(LOGIN_COMPRADOR, comprador.getLogin());
			jComprador.put(PSSWD_COMPRADOR, comprador.getPassword());
			jComprador.put(TELEFONO_COMPRADOR, comprador.getTelefono());
			jCompradores.put(jComprador);
		}
		jobject.put("compradores", jCompradores);
		PrintWriter pw = new PrintWriter( archivo );
		jobject.write( pw, 2, 0 );
		pw.close( );
	}
}
