package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.PathIterator;

import javax.swing.JComponent;

import controller.SelectedArea;

import model.ImageModel;

public class Handle extends JComponent {
    
    Color handleColor;
    Color borderColor;
    
    int x;
    int y;
    int size;
    
    public Handle(int size, ImageModel.Zone zone, int x, int y) {
        this.setBounds(new Rectangle((int) x - size / 2, (int) y - size / 2, size, size));
        this.x = x;
        this.y = y;
        this.size = size;
        
        this.borderColor = SelectedArea.getZoneBorderColor(zone);
        this.handleColor = SelectedArea.getZoneHandleColor(zone);
    }
    
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(handleColor);
        g2d.fillRect(0, 0, size, size);
                             
        g2d.setColor(borderColor);
        g2d.fillRect(0, 0, size, size);  
    }
    
}
