package compradores;

import java.util.ArrayList;

public class Comprador{

	
	private boolean verificado;
	private String nombre;
	private int identificador;
	private int telefono;
	private String login;
	private String password;

	private ArrayList<Compra> compras;

	public Comprador(boolean verificacion, String nNombre, int identificacion, int nTelefono, String nLogin, String password) {
		this.verificado = verificacion;
		this.nombre = nNombre;
		this.identificador = identificacion;
		this.telefono = nTelefono;
		this.login = nLogin;
		this.password = password;
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

	public void setTelefono(int telefono){
		this.telefono = telefono;
	}

	public String getLogin() {
		return this.login;
	}

	public String getPassword() {
		return this.password;
	}

	public ArrayList<Compra> getCompras() {
		return this.compras;
	}
}
