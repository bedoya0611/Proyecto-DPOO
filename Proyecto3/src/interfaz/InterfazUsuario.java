package interfaz;

import javax.swing.*;

import galeria.Galeria;
import galeria.compradores.Comprador;
import galeria.inventario.Inventario;
import galeria.inventario.Pieza;
import galeria.usuarios.Artista;
import galeria.ventas.ListaDeSubastas;
import galeria.ventas.Subasta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InterfazUsuario extends JFrame implements ActionListener{
	
	private JPanel banner;
	private PanelMenuUsuario menu;
	private Interfaz padre;
	private String[] credenciales;
	
	public InterfazUsuario(Interfaz padre, String[] credenciales) {
		
		this.padre=padre;
		this.credenciales = credenciales;
		
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
    	
		JLabel bannerLabel = new JLabel("            Menú de Comprador");
		bannerLabel.setFont(getFont());
		bannerLabel.setForeground(getContentPane().getForeground());
		banner.setBackground(getContentPane().getBackground());
		banner.add(new JLabel());
		banner.add(new JLabel());
		banner.add(bannerLabel);
		
		menu = new PanelMenuUsuario(this);
		
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
	
	private void mostrarPiezas(int tipo) {
		Inventario inventario = Galeria.getUnInventario();
		ArrayList<Pieza> listaPiezas = null;
		if(tipo == 0) {listaPiezas = inventario.getPiezasExhibidas();}
		else if (tipo == 1) {listaPiezas = inventario.getPiezasDisponibles();}
		else {return;}
		JPanel obras = new JPanel();
		obras.setLayout(new GridLayout(listaPiezas.size(),1));
		for (Pieza pieza: listaPiezas) {
			JPanel grilla = new JPanel();
			grilla.setLayout(new GridLayout(5,2));
			JLabel label = new JLabel("Tipo: ");
			label.setFont(new Font("Dubai", Font.PLAIN, 18));
			label.setForeground(new Color(35,51,71));
			grilla.add(label);
			label = new JLabel(pieza.getTipoPieza());
			label.setFont(new Font("Dubai", Font.PLAIN, 18));
			label.setForeground(new Color(35,51,71));
			grilla.add(label);
			label = new JLabel("Título: ");
			label.setFont(new Font("Dubai", Font.PLAIN, 18));
			label.setForeground(new Color(35,51,71));
			grilla.add(label);
			label = new JLabel(pieza.getTitulo());
			label.setFont(new Font("Dubai", Font.PLAIN, 18));
			label.setForeground(new Color(35,51,71));
			grilla.add(label);
			label = new JLabel("Autores: ");
			label.setFont(new Font("Dubai", Font.PLAIN, 18));
			label.setForeground(new Color(35,51,71));
			grilla.add(label);
			String autores = "";
			for(Artista autor:pieza.getAutores()) {
				autores+=autor.getNombre()+", ";
			}
			label = new JLabel(autores.substring(0, autores.length()-2));
			label.setFont(new Font("Dubai", Font.PLAIN, 18));
			label.setForeground(new Color(35,51,71));
			grilla.add(label);
			label = new JLabel("Año de creación: ");
			label.setFont(new Font("Dubai", Font.PLAIN, 18));
			label.setForeground(new Color(35,51,71));
			grilla.add(label);
			label = new JLabel(String.valueOf(pieza.getAnio()));
			label.setFont(new Font("Dubai", Font.PLAIN, 18));
			label.setForeground(new Color(35,51,71));
			grilla.add(label);
			label = new JLabel("Lugar de creación: ");
			label.setFont(new Font("Dubai", Font.PLAIN, 18));
			label.setForeground(new Color(35,51,71));
			grilla.add(label);
			label = new JLabel(pieza.getLugarCreacion());
			label.setFont(new Font("Dubai", Font.PLAIN, 18));
			label.setForeground(new Color(35,51,71));
			grilla.add(label);
			grilla.setBorder(BorderFactory.createLineBorder(new Color(35,51,71)));
			grilla.setOpaque(false);
			obras.add(grilla);
		}
		JScrollPane scrollPane = new JScrollPane(obras);  
		scrollPane.setPreferredSize( new Dimension( 400, 500 ) );
		String title = "";
		if (tipo == 0) {title = "Mostrar piezas exhibidas";}
		else {title = "Mostrar piezas disponibles";}
		JOptionPane.showMessageDialog(null, scrollPane, title,  
		                                       JOptionPane.PLAIN_MESSAGE);
	}

	private void comprarPieza() {
		String titulo = (String) JOptionPane.showInputDialog(this, "Ingrese el título de la pieza que desea comprar", "Comprar pieza", JOptionPane.QUESTION_MESSAGE);
		boolean realizada = Galeria.comprarPieza(titulo);
		if (realizada) {
			JOptionPane.showMessageDialog(this, "Operación realizada con éxito, esperando confirmación del administrador", "", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "No se pudo comprar la pieza", "", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void participarEnSubasta() {
		ArrayList<ListaDeSubastas> abiertas = Galeria.getSubastasAbiertas();
		JPanel subastas = new JPanel();
		subastas.setLayout(new GridLayout(abiertas.size()*2,1));
		for (int i=0 ;i<abiertas.size();i++) {
			ListaDeSubastas lista = abiertas.get(i);
			JPanel grilla = new JPanel();
			grilla.setLayout(new GridLayout(2,2));
			JLabel label = new JLabel("Piezas:");
			label.setFont(new Font("Dubai", Font.PLAIN, 18));
			label.setForeground(new Color(35,51,71));
			grilla.add(label);
			grilla.add(new JLabel());
			String piezas = "";
			for (Subasta subasta:lista.getSubastas()) {
				piezas += subasta.getPieza().getTitulo() + ", ";
			}
			label = new JLabel(piezas.substring(0, piezas.length()-2));
			label.setFont(new Font("Dubai", Font.PLAIN, 18));
			label.setForeground(new Color(35,51,71));
			grilla.add(label);
			JButton participar = new JButton("Participar");
			participar.setActionCommand("subasta"+(i+1));
			participar.setForeground(new Color(35,51,71));
			participar.setFont(new Font("Dubai", Font.PLAIN, 18));
			participar.addActionListener(this);
			grilla.add(participar);
			grilla.setBorder(BorderFactory.createLineBorder(new Color(35,51,71)));
			grilla.setOpaque(false);
			grilla.setPreferredSize(new Dimension(380,100*abiertas.size()));
			label = new JLabel("                                  Subasta "+(i+1));
			label.setFont(new Font("Dubai", Font.PLAIN, 20));
			label.setForeground(new Color(35,51,71));
			subastas.add(label);
			subastas.add(grilla);
		}
		JScrollPane scrollPane = new JScrollPane(subastas);  
		scrollPane.setPreferredSize( new Dimension(400, 200));
		String title = "";
		JOptionPane.showMessageDialog(null, scrollPane, title,  
				JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Exhibidas")) {
			mostrarPiezas(0);
		}else if(command.equals("Disponibles")) {
			mostrarPiezas(1);
		}else if(command.equals("Comprar")) {
			comprarPieza();
		}else if(command.equals("Participar")) {
			participarEnSubasta();
		}else if(command.equals("Artista")) {
			consultarArtista();
		}else if(command.equals("Pieza")) {
			consultarPieza();
		}
		if (command.substring(0, 7).equals("subasta")) {
			int index = Integer.valueOf(command.substring(7))-1;
			boolean inscrito = Galeria.participarEnSubasta(index,credenciales);
			if (inscrito) {
				JOptionPane.showMessageDialog(this, "Inscrito en la subasta", "", JOptionPane.PLAIN_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(this, "Usuario no verificado", "", JOptionPane.ERROR_MESSAGE);				
			}
		}
		padre.guardarArchivo(padre.persistencia, Galeria.getUnAdmin(), Galeria.getUnInventario(), new ArrayList<Comprador>(Galeria.getCompradores().values()));
	}
}