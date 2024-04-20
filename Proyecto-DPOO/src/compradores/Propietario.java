package compradores;

import java.util.List;

public class Propietario extends Comprador{
	
	private List<Compra> compras;

	public Propietario(boolean verificacion, String nNombre, int identificacion, int nTelefono, String nLogin, String contraseña) {
		super(verificacion, nNombre, identificacion, nTelefono, nLogin, contraseña);
		// TODO Auto-generated constructor stub
	}
	
	public List<Compra> getCompras() {
		return this.compras;
	}
	
}
