package interfaz;

import javax.swing.*;

import galeria.Galeria;
import galeria.compradores.Comprador;
import galeria.inventario.Pieza;
import galeria.usuarios.Artista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterfazAdmin extends JFrame implements ActionListener{
	
	private JPanel banner;
	private PanelMenuAdmin menu;
	private Interfaz padre;
	
	public InterfazAdmin(Interfaz padre) {
		
		this.padre=padre;
		
		setLayout(new BorderLayout());
		setFont(new Font("Dubai", Font.BOLD, 20));
		setSize(900,536);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Galeria - Administrador");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(35,51,71));
    	getContentPane().setForeground(new Color(246,248,248));
    	
    	banner = new JPanel();
    	banner.setPreferredSize(new Dimension(300,80));
		banner.setLayout(new GridLayout(2,2));
    	
		JLabel bannerLabel = new JLabel("            Menú de Administrador");
		bannerLabel.setFont(getFont());
		bannerLabel.setForeground(getContentPane().getForeground());
		banner.setBackground(getContentPane().getBackground());
		banner.add(new JLabel());
		banner.add(new JLabel());
		banner.add(bannerLabel);
		
		menu = new PanelMenuAdmin(this);
		
		add(menu, BorderLayout.CENTER);
		add(banner, BorderLayout.NORTH);
		JPanel margenLado1 = new JPanel();
		margenLado1.setPreferredSize(new Dimension(50, 300));
		margenLado1.setBackground(new Color(35,51,71));
		add(margenLado1, BorderLayout.WEST);
		JPanel margenLado2 = new JPanel();
		margenLado2.setPreferredSize(new Dimension(50, 300));
		margenLado2.setBackground(new Color(35,51,71));
		add(margenLado2, BorderLayout.EAST);
		JPanel margenAbajo = new JPanel();
		margenAbajo.setPreferredSize(new Dimension(300, 50));
		margenAbajo.setBackground(new Color(35,51,71));
		add(margenAbajo, BorderLayout.SOUTH);
		setVisible(true);
		
	}

	private void confirmarVenta() {
		String titulo = (String) JOptionPane.showInputDialog(this, "Ingrese el título de la pieza que se venderá", "Confirmar Venta", JOptionPane.QUESTION_MESSAGE);
		boolean confirmada = Galeria.confirmarVenta(titulo);
		if (confirmada) {
			JOptionPane.showMessageDialog(this, "Venta confirmada con éxito", "", JOptionPane.PLAIN_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "No se pudo confirmar la venta", "", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void consultarComprador() {
		String usuario = (String) JOptionPane.showInputDialog(this, "Ingrese el usuario del comprador", "Consultar Comprador", JOptionPane.QUESTION_MESSAGE);
		Comprador comprador = Galeria.consultarComprador(usuario);
		if(comprador == null) {
			JOptionPane.showMessageDialog(this, "No se pudo encontrar el comprador", "", JOptionPane.ERROR_MESSAGE);
		}else{
			JPanel infoComprador = new JPanel();
			infoComprador.setLayout(new GridLayout(4,2));
			JLabel label = new JLabel("Nombre:");
			label.setFont(new Font("Dubai", Font.PLAIN, 15));
			label.setForeground(getContentPane().getBackground());
			infoComprador.add(label);
			label = new JLabel(comprador.getNombre());
			label.setFont(new Font("Dubai", Font.PLAIN, 15));
			label.setForeground(getContentPane().getBackground());
			infoComprador.add(label);
			label = new JLabel("Número de identificación: ");
			label.setFont(new Font("Dubai", Font.PLAIN, 15));
			label.setForeground(getContentPane().getBackground());
			infoComprador.add(label);
			label = new JLabel(String.valueOf(comprador.getIdentificador()));
			label.setFont(new Font("Dubai", Font.PLAIN, 15));
			label.setForeground(getContentPane().getBackground());
			infoComprador.add(label);
			label = new JLabel("Teléfono:");
			label.setFont(new Font("Dubai", Font.PLAIN, 15));
			label.setForeground(getContentPane().getBackground());
			infoComprador.add(label);
			label = new JLabel(String.valueOf(comprador.getTelefono()));
			label.setFont(new Font("Dubai", Font.PLAIN, 15));
			label.setForeground(getContentPane().getBackground());
			infoComprador.add(label);
			label = new JLabel("Verificado:");
			label.setFont(new Font("Dubai", Font.PLAIN, 15));
			label.setForeground(getContentPane().getBackground());
			infoComprador.add(label);
			label = new JLabel(String.valueOf(comprador.isVerificado()));
			label.setFont(new Font("Dubai", Font.PLAIN, 15));
			label.setForeground(getContentPane().getBackground());
			infoComprador.add(label);
			
			JOptionPane.showMessageDialog(this, infoComprador);
		}
	}

	private void consultarArtista() {
		String nombre = (String) JOptionPane.showInputDialog(this, "Ingrese el nombre del artista", "Consultar Artista", JOptionPane.QUESTION_MESSAGE);
		Artista artista = Galeria.consultarArtista(nombre);
		if(artista == null) {
			JOptionPane.showMessageDialog(this, "No se pudo encontrar el artista", "", JOptionPane.ERROR_MESSAGE);
		}else{
			JPanel infoArtista = new JPanel();
			infoArtista.setLayout(new GridLayout(2,2));
			JLabel label = new JLabel("Nombre: ");
			label.setFont(new Font("Dubai", Font.PLAIN, 15));
			label.setForeground(getContentPane().getBackground());
			infoArtista.add(label);
			label = new JLabel(artista.getNombre());
			label.setFont(new Font("Dubai", Font.PLAIN, 15));
			label.setForeground(getContentPane().getBackground());
			infoArtista.add(label);
			label = new JLabel("Obras:");
			label.setFont(new Font("Dubai", Font.PLAIN, 15));
			label.setForeground(getContentPane().getBackground());
			infoArtista.add(label);
			ArrayList<Pieza> obras = (ArrayList<Pieza>) artista.getObras();
			JPanel panelObras = new JPanel();
			panelObras.setLayout(new GridLayout(obras.size(), 1));
			for(Pieza pieza:obras) {
				label = new JLabel(pieza.getTitulo());
				label.setFont(new Font("Dubai", Font.PLAIN, 15));
				label.setForeground(getContentPane().getBackground());
				panelObras.add(label);
			}
			infoArtista.add(panelObras);
			JOptionPane.showMessageDialog(this, infoArtista);
		}
	}
	
	private void consultarPieza() {
		String titulo = (String) JOptionPane.showInputDialog(this, "Ingrese el título de la pieza", "Consultar Pieza", JOptionPane.QUESTION_MESSAGE);
		ArrayList<String> lista = Galeria.consultarPieza(titulo);
		JOptionPane.showMessageDialog(this, lista.toString());
		
	}
	
	private void verificarComprador() {
		String usuario = (String) JOptionPane.showInputDialog(this, "Ingrese el usuario del comprador", "Verificar Comprador", JOptionPane.QUESTION_MESSAGE);
		Comprador comprador = Galeria.consultarComprador(usuario);
		if(comprador == null) {
			JOptionPane.showMessageDialog(this, "No se pudo encontrar el comprador", "", JOptionPane.ERROR_MESSAGE);
		}else{
			Galeria.verificarComprador(comprador);
			JOptionPane.showMessageDialog(this, "Comprador verificado", "", JOptionPane.PLAIN_MESSAGE);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Registrar")) {
			new VentanaRegistrar();
		}else if(e.getActionCommand().equals("Confirmar")) {
			confirmarVenta();
		}else if(e.getActionCommand().equals("Comprador")) {
			consultarComprador();
		}else if(e.getActionCommand().equals("Verificar")) {
			verificarComprador();
		}else if(e.getActionCommand().equals("Artista")) {
			consultarArtista();
		}else if(e.getActionCommand().equals("Pieza")) {
			consultarPieza();
		}
		padre.guardarArchivo(padre.persistencia, Galeria.getUnAdmin(), Galeria.getUnInventario(), new ArrayList<Comprador>(Galeria.getCompradores().values()));
	}

}