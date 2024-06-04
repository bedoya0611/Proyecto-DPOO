package interfaz;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.JSONException;

import com.formdev.flatlaf.FlatIntelliJLaf;

import galeria.Galeria;
import galeria.Exceptions.FormatoIncorrectoException;
import galeria.compradores.Comprador;
import galeria.inventario.Inventario;
import galeria.persistencia.Persistencia;
import galeria.usuarios.Admin;

public class Interfaz extends JFrame implements ActionListener{
	
	public final static String ADMIN = "a";
	public final static String OPERADOR = "o";
	public final static String CAJERO = "c";
	public final static String USUARIO = "u";

    private JFrame frame; //main frame
    private JFrame lframe; //login frame
    private Container container;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private RoundJTextField userTextField;
    private RoundJPasswordField passwordField;
    private JButton loginButton;
    private JButton resetButton;
    private JCheckBox showPassword;
    
	public Persistencia persistencia = new Persistencia();
	private String archivoGaleria = "datosGaleria.json"; 
	private String archivoUsuarios = "datosUsuarios.json"; 

    public Interfaz() {
//        container = getContentPane();
    	getContentPane().setBackground(new Color(35,51,71));
    	getContentPane().setForeground(new Color(246,248,248));
    	setSize(380,240);
    	setLayout(null);
    	setLocationRelativeTo(null);
    	setResizable(false);
    	setTitle("Login");
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setFont(new Font("Dubai", Font.PLAIN, 15));

    	userLabel = new JLabel("Usuario:");
        passwordLabel = new JLabel("Contraseña:");
        userTextField = new RoundJTextField(1);
        passwordField = new RoundJPasswordField();
        loginButton = new JButton("Ingresar");
        showPassword = new JCheckBox();
        passwordField.setEchoChar('×');
        
        userLabel.setBounds(50, 30, 100, 30);
        passwordLabel.setBounds(50, 80, 100, 30);
        userTextField.setBounds(150, 30, 150, 30);
        passwordField.setBounds(150, 80, 150, 30);
        showPassword.setBounds(265, 80, 30, 30);
        loginButton.setBounds(125, 140, 100, 30);
        
        userLabel.setForeground(new Color(246,248,248));
        passwordLabel.setForeground(new Color(246,248,248));
        showPassword.setForeground(new Color(246,248,248));
        
        userLabel.setFont(getFont());
        passwordLabel.setFont(getFont());
        userTextField.setFont(getFont());
        passwordField.setFont(getFont());
        showPassword.setFont(getFont());
        
        userTextField.setBackground(getContentPane().getBackground());
        userTextField.setForeground(getForeground());
        passwordField.setBackground(getContentPane().getBackground());
        passwordField.setForeground(getForeground());
        
        loginButton.addActionListener(this);
        showPassword.addActionListener(this);
        
        ImageIcon shown = new ImageIcon("./images/shown.png");
        ImageIcon hidden = new ImageIcon("./images/hidden.png");
        Image shownImg = shown.getImage(); // transform it 
        Image newShown = shownImg.getScaledInstance(20, 20,  java.awt.Image.SCALE_AREA_AVERAGING);
        showPassword.setSelectedIcon(new ImageIcon(newShown));
        Image hiddenImg = hidden.getImage(); // transform it 
        Image newHidden = hiddenImg.getScaledInstance(20, 20,  java.awt.Image.SCALE_AREA_AVERAGING);
        showPassword.setSelectedIcon(new ImageIcon(newShown));
        showPassword.setIcon(new ImageIcon(newHidden));
        showPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        userTextField.setCaretColor(getForeground());
        passwordField.setCaretColor(getForeground());
        userTextField.addActionListener(this);
        passwordField.addActionListener(this);
        
        loginButton.setForeground(getContentPane().getBackground());
        loginButton.setBackground(new Color(246,248,248));
        loginButton.setFont(getFont());
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        add(userLabel);
        add(passwordLabel);
        add(userTextField);
        add(showPassword);
        add(passwordField);
        add(loginButton);
        setVisible(true);
    }
    
    public class RoundJTextField extends JTextField {
        private Shape shape;
        public RoundJTextField(int size) {
            super(size);
            setOpaque(false); // As suggested by @AVD in comment.
        }
        protected void paintComponent(Graphics g) {
             g.setColor(getBackground());
             g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6);
             super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
             g.setColor(getForeground());
             g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6);
        }
        public boolean contains(int x, int y) {
             if (shape == null || !shape.getBounds().equals(getBounds())) {
                 shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 6, 6);
             }
             return shape.contains(x, y);
        }
    }
   
    public class RoundJPasswordField extends JPasswordField {
    	private Shape shape;
    	public RoundJPasswordField() {
    		super();
    		setOpaque(false); // As suggested by @AVD in comment.
    	}
    	protected void paintComponent(Graphics g) {
    		g.setColor(getBackground());
    		g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6);
    		super.paintComponent(g);
    	}
    	protected void paintBorder(Graphics g) {
    		g.setColor(getForeground());
    		g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 6, 6);
    	}
    	public boolean contains(int x, int y) {
    		if (shape == null || !shape.getBounds().equals(getBounds())) {
    			shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 6, 6);
    		}
    		return shape.contains(x, y);
    	}
    }
    
    public String verificarCredenciales(String user, String psswd) {
    	if (Galeria.verificarCredencialesAdmin(user, psswd)) {
    		return ADMIN;
    	}
    	if (Galeria.verificarCredencialesUsuario(user, psswd)) {
    		return USUARIO;
    	}
    	boolean[] empleado = Galeria.verificarCredencialesEmpleado(user, psswd);
    	if (empleado[0]) {
    		if(empleado[1]) { 
    			return OPERADOR;
    		}else {
    			return CAJERO;
    		}
    	}
    	return null;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton || e.getSource() == userTextField || e.getSource() == passwordField) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = new String(passwordField.getPassword());
            String tipo = verificarCredenciales(userText, pwdText);
            if(tipo == null) {
            	JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrecta, inténtelo nuevamente.","", JOptionPane.ERROR_MESSAGE);
            }else {
            	setVisible(false);
            	if (tipo.equals("a")) {
            		new InterfazAdmin(this);
            	} else if (tipo.equals("u")) {
            		String[] cred = {userText, pwdText};
            		new InterfazUsuario(this, cred);
            	}
            }
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('×');
            }
        }        
    }
    
    public void cargarArchivo(Persistencia persistencia) throws JSONException, Exception{
		try
        {
			Galeria.cargarCompradores("./datos/" + archivoUsuarios);
            persistencia.cargarGaleria( "./datos/" + archivoGaleria);
        } catch (FormatoIncorrectoException e) {
        	e.printStackTrace( );
        }
	}
    
    public void guardarArchivo(Persistencia persistencia, Admin administrador, Inventario inventario,
				ArrayList<Comprador> compradores) {
		try {
			persistencia.salvarGaleria("./datos/"+archivoGaleria, administrador, inventario);
			persistencia.salvarCompradores("./datos/"+archivoUsuarios, compradores);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
    	UIManager.setLookAndFeel(new FlatIntelliJLaf());
        EventQueue.invokeLater(() -> {
            Interfaz interfaz = new Interfaz();
            try {
				interfaz.cargarArchivo(interfaz.persistencia);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }
}