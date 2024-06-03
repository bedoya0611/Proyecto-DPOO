package interfaz;

import javax.swing.*;

import galeria.Galeria;
import galeria.Exceptions.PiezaDuplicadaException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class VentanaRegistrar extends JFrame implements ItemListener, ActionListener{
	
	private JPanel banner;
	private PanelRegistrar menu;
	private JPanel boton;
	private JButton registrar;
	
	public VentanaRegistrar() {
		
		setLayout(new BorderLayout());
		setFont(new Font("Dubai", Font.BOLD, 25));
		setSize(900,700);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Galeria - Administrador");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		getContentPane().setBackground(new Color(35,51,71));
    	getContentPane().setForeground(new Color(246,248,248));
    	
    	banner = new JPanel();
    	banner.setPreferredSize(new Dimension(300,80));
		banner.setLayout(new GridLayout(2,2));
    	
		JLabel bannerLabel = new JLabel("            Registrar Pieza");
		bannerLabel.setFont(getFont());
		bannerLabel.setForeground(getContentPane().getForeground());
		banner.setBackground(getContentPane().getBackground());
		banner.add(new JLabel());
		banner.add(new JLabel());
		banner.add(bannerLabel);
		
		menu = new PanelRegistrar(this);
		
		boton = new JPanel();
		registrar = new JButton("Registrar");
		registrar.setFont(new Font("Dubai", Font.PLAIN, 14));
		registrar.setBackground(new Color(246,248,248));
		registrar.addActionListener(this);
		boton.setPreferredSize(new Dimension(300, 50));
		boton.setBackground(new Color(35,51,71));
		boton.add(registrar);
		
		add(menu, BorderLayout.CENTER);
		add(banner, BorderLayout.NORTH);
		add(boton, BorderLayout.SOUTH);
		
		JPanel margenLado1 = new JPanel();
		margenLado1.setPreferredSize(new Dimension(70, 300));
		margenLado1.setBackground(new Color(35,51,71));
		add(margenLado1, BorderLayout.WEST);
		JPanel margenLado2 = new JPanel();
		margenLado2.setPreferredSize(new Dimension(70, 300));
		margenLado2.setBackground(new Color(35,51,71));
		add(margenLado2, BorderLayout.EAST);
		requestFocus();
		setVisible(true);
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		String tipo = menu.darTipo();
		if("Pintura".equals(tipo)){
			menu.pintura();
		}else if ("Escultura".equals(tipo)){
			menu.escultura();
		}else if ("Fotografía".equals(tipo)){
			menu.fotografia();
		}else if ("Impresión".equals(tipo)){
			menu.impresion();
		}else if ("Video".equals(tipo)){
			menu.video();
		}else{
			menu.limpiar();
		}
	}
		
	private class PanelRegistrar extends JPanel implements ActionListener {
		private JPanel demas;
		private JPanel general;
		
		private JTextField titulo;
		private JTextField anio;
		private JTextField lugar;
		private JCheckBox exhibida;
		private JButton autor;
		private JComboBox<String> tipo;
		
		private JTextField alto;
		private JTextField ancho;
		private JTextField profundidad;
		private JTextField material;
		private JTextField peso;
		private JTextField tecnica;
		private JTextField estilo;
		
		private JLabel autores;
		private ArrayList<String> listaAutores;
		
		public ArrayList<String> getListaAutores(){
			return listaAutores;
		}
		
		public JTextField getTitulo() {
			return titulo;
		}

		public JTextField getAnio() {
			return anio;
		}

		public JTextField getLugar() {
			return lugar;
		}

		public JCheckBox getExhibida() {
			return exhibida;
		}

		public JButton getAutor() {
			return autor;
		}

		public JTextField getAlto() {
			return alto;
		}

		public JTextField getAncho() {
			return ancho;
		}

		public JTextField getProfundidad() {
			return profundidad;
		}

		public JTextField getMaterial() {
			return material;
		}

		public JTextField getPeso() {
			return peso;
		}

		public JTextField getTecnica() {
			return tecnica;
		}

		public JTextField getEstilo() {
			return estilo;
		}

		public JTextField getCamara() {
			return camara;
		}

		public JTextField getDuracion() {
			return duracion;
		}

		public JTextField getIdioma() {
			return idioma;
		}

		public JCheckBox getElectricidad() {
			return electricidad;
		}

		public JTextArea getDetalles() {
			return detalles;
		}

		private JTextField camara;
		private JTextField duracion;
		private JTextField idioma;
		private JCheckBox electricidad;
		private JTextArea detalles;
		
		private VentanaRegistrar padre;
		private Font fuente = new Font("Dubai", Font.PLAIN, 14);
		
		PanelRegistrar(VentanaRegistrar padre){
			this.padre = padre;
			panelBasico();
		}
		
		private void panelBasico() {
			setLayout(new BorderLayout());
			general = new JPanel();
			general.setLayout(new GridLayout(6,2));
			JLabel label = new JLabel("Seleccione el tipo de la Pieza:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			general.add(label);
			String[] tiposPieza = {"","Pintura","Escultura","Fotografía","Impresión","Video"};
			tipo = new JComboBox<String>(tiposPieza);
			tipo.setFont(fuente);
			tipo.addItemListener(padre);
			general.add(tipo);
			label = new JLabel("Ingrese el título de la Pieza:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			general.add(label);
			titulo = new JTextField();
			titulo.setFont(fuente);
			general.add(titulo);
			label = new JLabel("Ingrese el año de creación de la Pieza:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			general.add(label);
			anio = new JTextField();
			anio.setFont(fuente);
			general.add(anio);
			label = new JLabel("Ingrese el lugar de creación de la Pieza:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			general.add(label);
			lugar = new JTextField();
			lugar.setFont(fuente);
			general.add(lugar);
			label = new JLabel("Añada los autores");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			general.add(label);
			JPanel Panelautor = new JPanel();
			Panelautor.setBackground(new Color(35,51,71));
			Panelautor.setLayout(new BorderLayout());
			listaAutores = new ArrayList<String>();
			autores = new JLabel();
			autores.setFont(fuente);
			autores.setForeground(new Color(246,246,248));
			autor = new JButton("Agregar autor");
			Panelautor.add(autores, BorderLayout.WEST);
			Panelautor.add(autor, BorderLayout.EAST);
			autor.setFont(fuente);
			autor.addActionListener(this);
			general.add(Panelautor);
			label = new JLabel("¿La Pieza será exhibida?");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			general.add(label);
			exhibida = new JCheckBox();
			general.add(exhibida);
			demas = new JPanel();
			general.setBackground(new Color(35,51,71));
			general.setForeground(new Color(246,248,248));
			demas.setBackground(new Color(35,51,71));
			demas.setForeground(new Color(246,248,248));
			add(general, BorderLayout.NORTH);
			add(demas, BorderLayout.CENTER);
		}


		private void pintura() {
			demas.setLayout(new GridLayout(6,2));
			demas.removeAll();
			JLabel label = new JLabel("Ingrese el alto de la pintura:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			alto = new JTextField(3);
			alto.setFont(fuente);
			demas.add(alto);
			label = new JLabel("Ingrese el ancho de la pintura:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			ancho = new JTextField(3);
			ancho.setFont(fuente);
			demas.add(ancho);
			label = new JLabel("Ingrese la técnica de la pintura:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			tecnica = new JTextField(3);
			tecnica.setFont(fuente);
			demas.add(tecnica);
			label = new JLabel("Ingrese el estilo de la pintura:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			estilo = new JTextField(3);
			estilo.setFont(fuente);
			demas.add(estilo);
			demas.revalidate();
			demas.repaint();
		}

		private void escultura() {
			demas.setLayout(new GridLayout(7,2));
			demas.removeAll();
			JLabel label = new JLabel("Ingrese el alto de la escultura:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			alto = new JTextField(3);
			alto.setFont(fuente);
			demas.add(alto);
			label = new JLabel("Ingrese el ancho de la escultura:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			ancho = new JTextField(3);
			ancho.setFont(fuente);
			demas.add(ancho);
			label = new JLabel("Ingrese la profundidad de la escultura:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			profundidad = new JTextField(3);
			profundidad.setFont(fuente);
			demas.add(profundidad);
			label = new JLabel("Ingrese el material de la escultura:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			material = new JTextField(3);
			material.setFont(fuente);
			demas.add(material);
			label = new JLabel("Ingrese el peso de la escultura:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			peso = new JTextField(3);
			peso.setFont(fuente);
			demas.add(peso);
			label = new JLabel("¿Necesita electricidad?");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			electricidad = new JCheckBox();
			demas.add(electricidad);
			label = new JLabel("Detalles de instalación");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			detalles = new JTextArea();
			detalles.setFont(fuente);
			demas.add(detalles);
			demas.revalidate();
			demas.repaint();
		}
		
		private void fotografia() {
			demas.setLayout(new GridLayout(6,2));
			demas.removeAll();
			JLabel label = new JLabel("Ingrese el alto de la fotografía:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			alto = new JTextField(3);
			alto.setFont(fuente);
			demas.add(alto);
			label = new JLabel("Ingrese el ancho de la fotografía:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			ancho = new JTextField(3);
			ancho.setFont(fuente);
			demas.add(ancho);
			label = new JLabel("Ingrese la cámara de la fotografía:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			camara = new JTextField(3);
			camara.setFont(fuente);
			demas.add(camara);
			demas.add(new JLabel());
			demas.add(new JLabel());
			demas.add(new JLabel());
			demas.add(new JLabel());
			demas.add(new JLabel());
			demas.revalidate();
			demas.repaint();
		}
		
		private void impresion() {
			demas.setLayout(new GridLayout(6,2));
			demas.removeAll();
			JLabel label = new JLabel("Ingrese el alto de la impresión:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			alto = new JTextField(3);
			alto.setFont(fuente);
			demas.add(alto);
			label = new JLabel("Ingrese el ancho de la impresión:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			ancho = new JTextField(3);
			ancho.setFont(fuente);
			demas.add(ancho);
			label = new JLabel("Ingrese la técnica de la impresión:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			tecnica = new JTextField(3);
			tecnica.setFont(fuente);
			demas.add(tecnica);
			demas.add(new JLabel());
			demas.add(new JLabel());
			demas.add(new JLabel());
			demas.add(new JLabel());
			demas.add(new JLabel());
			demas.revalidate();
			demas.repaint();
		}
		
		private void video() {
			demas.setLayout(new GridLayout(6,2));
			demas.removeAll();
			JLabel label = new JLabel("Ingrese la duración del video:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			duracion = new JTextField(3);
			duracion.setFont(fuente);
			demas.add(duracion);
			label = new JLabel("Ingrese el idioma del video:");
			label.setForeground(new Color(246,246,248));
			label.setFont(fuente);
			demas.add(label);
			idioma = new JTextField(3);
			idioma.setFont(fuente);
			demas.add(idioma);
			demas.add(new JLabel());
			demas.add(new JLabel());
			demas.add(new JLabel());
			demas.add(new JLabel());
			demas.add(new JLabel());
			demas.revalidate();
			demas.repaint();
		}

		private void limpiar() {
			demas.removeAll();
			demas.revalidate();
			demas.repaint();
		}
		
		public String darTipo() {return (String) tipo.getSelectedItem();}

		@Override
		public void actionPerformed(ActionEvent e) {
			String autor = (String) JOptionPane.showInputDialog(this,"Ingrese el nombre del autor");
			listaAutores.add(autor);
			autores.setText(listaAutores.toString());
		}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String tipoPieza = menu.darTipo();
		boolean realizada = true;
		if (tipoPieza.equals("")) {
				JOptionPane.showMessageDialog(this, "Seleccione un tipo de pieza para registrar");
				return;
		}
		String titulo = menu.getTitulo().getText();
		int anio = Integer.valueOf(menu.getAnio().getText());
		String lugar = menu.getLugar().getText();
		boolean exhibida = menu.getExhibida().isSelected();
		ArrayList<String> listaAutores = menu.getListaAutores();
		String[] autores = new String[listaAutores.size()];
		listaAutores.toArray(autores);
		if (tipoPieza == "Pintura") {
			double ancho = Double.valueOf(menu.getAncho().getText());
			double alto = Double.valueOf(menu.getAlto().getText());
			String tecnica = menu.getTecnica().getText();
			String estilo = menu.getEstilo().getText();
			try {
				Galeria.registrarPintura(titulo, anio, lugar, autores, exhibida, ancho, alto, tecnica, estilo);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				realizada = false;
			}
		}else if (tipoPieza == "Escultura") {
			double ancho = Double.valueOf(menu.getAncho().getText());
			double alto = Double.valueOf(menu.getAlto().getText());
			double profundidad = Double.valueOf(menu.getProfundidad().getText());
			double peso = Double.valueOf(menu.getPeso().getText());
			String material = menu.getMaterial().getText();
			boolean electricidad = menu.getElectricidad().isSelected();
			String detalles = menu.getDetalles().getText();
			try {
				Galeria.registrarEscultura(titulo, anio, lugar, autores, exhibida, ancho, alto, profundidad,
						material, peso, electricidad, detalles);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				realizada = false;
			}
		}else if (tipoPieza == "Fotografía") {
			double ancho = Double.valueOf(menu.getAncho().getText());
			double alto = Double.valueOf(menu.getAlto().getText());
			String camara = menu.getCamara().getText();
			try {
				Galeria.registrarFotografia(titulo, anio, lugar, autores, exhibida, ancho, alto, camara);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				realizada = false;
			}
		}else if (tipoPieza == "Impresión") {
			double ancho = Double.valueOf(menu.getAncho().getText());
			double alto = Double.valueOf(menu.getAlto().getText());
			String tecnica = menu.getTecnica().getText();
			try {
				Galeria.registrarImpresion(titulo, anio, lugar, autores, exhibida, ancho, alto, tecnica);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				realizada = false;
			}
		}else if (tipoPieza == "Video") {
			double duracion = Double.valueOf(menu.getDuracion().getText());
			String idioma = menu.getIdioma().getText();
			try {
				Galeria.registrarVideo(titulo, anio, lugar, autores, exhibida, duracion, idioma);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				realizada = false;
			}
		}
		if(realizada) {
			JOptionPane.showMessageDialog(this, "Pieza registrada con éxito");
			setVisible(false);
		}else {
			JOptionPane.showMessageDialog(this, "No se pudo registrar la pieza", "", JOptionPane.ERROR_MESSAGE);
		}
	}
		
//	public static void main(String[] args) {
//		new VentanaRegistrar();
//	}
}