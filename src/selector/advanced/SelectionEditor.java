package selector.advanced;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.Vector;

import javax.swing.JPanel;

public class SelectionEditor extends JPanel implements MouseListener, MouseMotionListener {

    private static final long serialVersionUID = -5611160066051383215L;
    private final SelectionPanel container;
    
    public SelectionEditor(SelectionPanel container) {
        this.container = container;
        
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        this.setOpaque(false);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Polygon p1 = new Polygon();
        
        if (this.areaStub != null) {
            for (Point p : this.areaStub) {
                p1.addPoint(p.x, p.y);
            }
        }
        
        if (this.currentLocation != null) {
            p1.addPoint(this.currentLocation.x, this.currentLocation.y);
        }
    
        double ratio = this.container.getDisplayRatio();
        Rectangle b = this.container.getImageBounds();
        
        AffineTransform at = new AffineTransform();
        at.scale(ratio, ratio);
        at.translate(b.x/ratio, b.y/ratio);
        
        Area s1 = new Area(at.createTransformedShape(p1));
        g2d.setColor(new Color(0, 78, 255, 64));
        g2d.fill(s1);
        
        int s = this.container.getHandleSize();
        
        g2d.setColor(new Color(0, 54, 176));
        
        if (this.areaStub != null) {
            Point prev = (Point) this.areaStub.get(0).clone();
            prev.x *= ratio;
            prev.y *= ratio;
            prev.x += b.x;
            prev.y += b.y;
            
            if (this.currentLocation != null) {
                this.areaStub.add(this.currentLocation);
            }
            
            for (Point p : this.areaStub) {
                p = (Point) p.clone();
                p.x *= ratio;
                p.y *= ratio;
                p.x += b.x;
                p.y += b.y;
                
                g2d.drawLine(prev.x, prev.y, p.x, p.y);
                
                g2d.setColor(new Color(185, 200, 234));
                g2d.fillRect(prev.x - s/2, prev.y - s/2, s, s);
                
                g2d.setColor(new Color(0, 54, 176));
                g2d.drawRect(prev.x - s/2, prev.y - s/2, s, s);
                
                prev = p;
            }
            
            if (this.currentLocation != null) {
                this.areaStub.remove(this.currentLocation);
            }
        }
    }
    
    /*
     * Selection areas creation code
     */
    
    Vector<Point> areaStub = null;
    Point currentLocation = null;
    
    @Override
    public void mouseClicked(MouseEvent e) {
        this.requestFocusInWindow();
        
        if (e.getClickCount() == 2) {
            this.container.addArea(this.areaStub);
            this.areaStub = null;
            this.currentLocation = null;
        } else {
            if (this.areaStub == null) {
                // Create a new stub
                this.areaStub = new Vector<Point>();
            }
            
            Point p = e.getPoint();
            Rectangle b = this.container.getImageBounds();
            
            p.x -= b.x;
            p.y -= b.y;
            
            p.x /= this.container.getDisplayRatio();
            p.y /= this.container.getDisplayRatio();
            
            this.areaStub.add(p);               
        }
        
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        if (this.areaStub != null) {
            Point p = e.getPoint();
            
            Rectangle b = this.container.getImageBounds();
            
            p.x -= b.x;
            p.y -= b.y;
            
            p.x /= this.container.getDisplayRatio();
            p.y /= this.container.getDisplayRatio();
            this.currentLocation = p;        
            
            this.repaint();
        }
    }
    
}
