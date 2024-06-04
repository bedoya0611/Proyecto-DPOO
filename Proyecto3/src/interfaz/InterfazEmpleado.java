package interfaz;

import javax.swing.*;
import galeria.inventario.Pieza;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazEmpleado extends JFrame implements ActionListener {
	
	private JPanel banner;
	private Interfaz padre;
	
	public InterfazEmpleado(Interfaz padre) {
		this.padre = padre;
		
		setLayout(new BorderLayout());
		setFont(new Font("Dubai", Font.BOLD, 20));
		setSize(900,536);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Galeria - Empleado");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(35,51,71));
    	getContentPane().setForeground(new Color(246,248,248));
    	
    	banner = new JPanel();
    	banner.setPreferredSize(new Dimension(300,80));
		banner.setLayout(new GridLayout(2,2));
    	
		JLabel bannerLabel = new JLabel("            Menú de Empleado");
		bannerLabel.setFont(getFont());
		bannerLabel.setForeground(getContentPane().getForeground());
		banner.setBackground(getContentPane().getBackground());
		banner.add(new JLabel());
		banner.add(new JLabel());
		banner.add(bannerLabel);
		
		
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

	private void cambioEstadoABodega(Pieza pieza) {
		if (pieza.isExhibida()) {
			pieza.setExhibida(false);
			System.out.println("Pieza movida a bodega: " + pieza.getTitulo());
		} else {
			System.out.println("La pieza ya está en la bodega.");
		}
	}
	
	private void cambioEstadoAExhibida(Pieza pieza) {
		if (!pieza.isExhibida()) {
			pieza.setExhibida(true);
			System.out.println("Pieza movida a exhibición: " + pieza.getTitulo());
		} else {
			System.out.println("La pieza ya está en exhibición.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ABodega")) {
			Pieza pieza = obtenerPieza();
			if (pieza != null) {
				cambioEstadoABodega(pieza);
			}
		} else if (e.getActionCommand().equals("AExhibida")) {
			Pieza pieza = obtenerPieza();
			if (pieza != null) {
				cambioEstadoAExhibida(pieza);
			}
		}
	}


}