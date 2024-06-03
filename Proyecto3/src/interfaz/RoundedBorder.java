package interfaz;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.border.Border;

public class RoundedBorder implements Border {

    private int radius;
    private Color color;
    private int thickness;


    RoundedBorder(int radius, Color color, int thickness) {
        this.radius = radius;
        this.color = color;
        this.thickness = thickness;
    }
            
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }
    
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = this.radius + thickness;
        insets.top = insets.bottom = this.radius + thickness;
        return insets;
    }



    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    	Graphics2D g2d = (Graphics2D) g;
    	g2d.setColor(color);
        g2d.setStroke(new BasicStroke(thickness));
    	g2d.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }
}