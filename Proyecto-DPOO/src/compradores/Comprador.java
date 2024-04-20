package compradores;

public class Comprador{

	
	private boolean verificado;
	private String nombre;
	private int identificador;
	private int telefono;
	private String login;
	private String password;

	public Comprador(boolean verificacion, String nNombre, int identificacion, int nTelefono, String nLogin, String contraseña) {
		this.verificado = verificacion;
		this.nombre = nNombre;
		this.identificador = identificacion;
		this.telefono = nTelefono;
		this.login = nLogin;
		this.password = contraseña;
	}
	
	public boolean isVerificado() {
		return this.verificado;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public int getIdentificador() {
		return this.identificador;
	}
	
	public int getTelefono() {
		return this.telefono;
	}
}
