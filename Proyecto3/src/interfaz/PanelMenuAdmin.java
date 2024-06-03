package interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class PanelMenuAdmin extends JPanel{
	
	InterfazAdmin padre;
	
    public PanelMenuAdmin(InterfazAdmin padre) {
    	this.padre = padre;
    	
    	setLayout(new GridBagLayout());
        setBackground(new Color(27, 42, 61));
        setPreferredSize(new Dimension(700,300));
        
        JButton btnRegistrarPieza = createButton("Registrar Pieza");
        btnRegistrarPieza.setActionCommand("Registrar");
        JButton btnConfirmarVenta = createButton("Confirmar Venta");
        btnConfirmarVenta.setActionCommand("Confirmar");
        JButton btnConsultarComprador = createButton("Consultar Comprador");
        btnConsultarComprador.setActionCommand("Comprador");
        JButton btnVerificarComprador = createButton("Verificar Comprador");
        btnVerificarComprador.setActionCommand("Verificar");
        JButton btnConsultarArtista = createButton("Consultar Artista");
        btnConsultarArtista.setActionCommand("Artista");
        JButton btnConsultarPieza = createButton("Consultar Pieza");
        btnConsultarPieza.setActionCommand("Pieza");
        Border margin = BorderFactory.createEmptyBorder(0, 0, 10, 10); // Márgenes: top, left, bottom, right
        setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(50, new Color(35, 51, 71), 30), margin));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(btnRegistrarPieza, gbc);

        gbc.gridx = 1;
        add(btnConfirmarVenta, gbc);

        gbc.gridx = 2;
        add(btnConsultarComprador, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(btnVerificarComprador, gbc);

        gbc.gridx = 1;
        add(btnConsultarArtista, gbc);

        gbc.gridx = 2;
        add(btnConsultarPieza, gbc);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(35, 51, 71));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Dubai", Font.PLAIN, 15));
        button.setBorder(new RoundedBorder(30, new Color(27,42,61),18));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(padre);
        return button;
    }
    
    
}