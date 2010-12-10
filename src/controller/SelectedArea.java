package controller;

import gui.SelectionPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;

import javax.swing.JComponent;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import model.ImageModel;



public class SelectedArea extends JComponent implements AncestorListener, FocusListener, MouseListener  {
    
    private static final long serialVersionUID = 1193609448831953910L;
    private final Area area; 
    private final ImageModel.Zone zone;
    private final SelectionPanel container;
    
    private boolean focused;
    
    public SelectedArea(Area area, ImageModel.Zone zone, SelectionPanel container) {
        this.area = area;
        this.container = container;
        this.zone = zone;
        
        this.container.model.getArea(zone).addArea(area);
        
        this.addAncestorListener(this);
        this.addMouseListener(this);
        this.addFocusListener(this);
    }
    
    public void recalculateBounds() {
        double ratio = this.container.getDisplayRatio();
        int s = this.container.getHandleSize();
        
        Rectangle cb = this.container.getImageBounds();
        
        Rectangle b = this.area.getBounds();
        b = new Rectangle((int) (b.x * ratio), (int) (b.y * ratio), (int) (b.width * ratio), (int) (b.height * ratio));
        
        this.setBounds(cb.x + b.x - s, cb.y + b.y - s, b.width + 2*s, b.height + 2*s);
        this.repaint();
    }
    
    private Area getScaledArea() {
        double ratio = this.container.getDisplayRatio();
        Rectangle bounds = this.getBounds();
        
        Rectangle cb = this.container.getImageBounds();
        
        AffineTransform at = new AffineTransform();
        at.translate(-bounds.x + cb.x, -bounds.y + cb.y);
        at.scale(ratio, ratio);
        
        return this.area.createTransformedArea(at);
    }
    
    public static Color getZoneFillColor(ImageModel.Zone zone) {
        switch (zone) {
            case SKIN:
                return new Color(0, 78, 255, 64);
            case WOUND:
                return new Color(255, 35, 20, 80);
        }
        
        return null;
    }
    
    public static Color getZoneBorderColor(ImageModel.Zone zone) {
        switch (zone) {
            case SKIN:
                return new Color(0, 54, 176);
            case WOUND:
                return new Color(255, 35, 20);
        }
        
        return null;
    }
    
    public static Color getZoneHandleColor(ImageModel.Zone zone) {
        switch (zone) {
            case SKIN:
                return new Color(185, 200, 234);
            case WOUND:
                return new Color(255, 170, 170);
        }
        
        return null;
    }
    
    public void paintComponent(Graphics g) {
        Color fill = SelectedArea.getZoneFillColor(this.zone);
        Color border = SelectedArea.getZoneBorderColor(this.zone);
        Color handle = SelectedArea.getZoneHandleColor(this.zone);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        
        Area area = this.getScaledArea();
        
        g2d.setColor(fill);
        g2d.fill(area);
        
        g2d.setColor(border);
        g2d.draw(area);
        
        if (this.focused) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
            
            double[] segment = new double[6];
            
            int s = this.container.getHandleSize();
            
            PathIterator itr = area.getPathIterator(null);
            
            while (!itr.isDone()) {
                int r = itr.currentSegment(segment);
                if (r == PathIterator.SEG_LINETO || r == PathIterator.SEG_MOVETO) {
                    g2d.setColor(handle);
                    g2d.fillRect((int) segment[0] - s / 2, (int) segment[1] - s / 2,
                        s, s);
                    
                    g2d.setColor(border);
                    g2d.drawRect((int) segment[0] - s / 2, (int) segment[1] - s / 2,
                        s, s);
                }
                
                itr.next();
            }
        }
    }
    
    @Override
    public void ancestorAdded(AncestorEvent e) {
        this.recalculateBounds();
        this.requestFocusInWindow();
    }
    
    @Override
    public void ancestorMoved(AncestorEvent e) {}
    
    @Override
    public void ancestorRemoved(AncestorEvent e) {}
    
    @Override
    public void focusGained(FocusEvent e) {
        this.focused = true;
        this.repaint();
    }
    
    @Override
    public void focusLost(FocusEvent e) {
        this.focused = false;
        this.repaint();
    }
    
    @Override
    public boolean contains(int x, int y) {
        return this.getScaledArea().contains(x, y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.requestFocusInWindow();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}
}
