package rgbixtouri.alpha.selector;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class SelectedArea extends JComponent implements AncestorListener, FocusListener, MouseListener {
    
    private static final long serialVersionUID = 2245064748787728791L;
    
    private Vector<Point> points = new Vector<Point>();
    
    public SelectedArea(Vector<Point> points) {
        for (Point p : points) {
            this.points.add((Point) p.clone());
        }
        
        this.addAncestorListener(this);
        this.addMouseListener(this);
        this.addFocusListener(this);
    }
    
    private Area getArea() {
        Polygon poly = new Polygon();
        
        for (Point p : this.points) {
            poly.addPoint(p.x, p.y);
        }
        
        return new Area(poly);
    }
    
    private Area getScaledArea() {
        double ratio = this.getRatio();
        Rectangle bounds = this.getBounds();
        
        AffineTransform at = new AffineTransform();
        at.translate(-bounds.x, -bounds.y);
        at.scale(ratio, ratio);
        
        return this.getArea().createTransformedArea(at);
    }
    
    protected void recalculateBounds() {
        Area area = this.getArea();
        double ratio = this.getRatio();
        int s = ((SelectorPanel) this.getParent()).getHandleSize();
        
        AffineTransform at = new AffineTransform();
        at.scale(ratio, ratio);
        
        Rectangle b = area.createTransformedArea(at).getBounds();
        
        this.setBounds(b.x - s, b.y - s, b.width + 2*s, b.height + 2*s);
        this.repaint();
    }
    
    private double getRatio() {
        return ((SelectorPanel) this.getParent()).getRatio();
    }
    
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        
        Area area = this.getScaledArea();
        
        g2d.setColor(new Color(0, 54, 176));
        g2d.draw(area);
        
        g2d.setColor(new Color(0, 78, 255, 64));
        g2d.fill(area);
        
        if (this.focused) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_OFF);
            
            double[] segment = new double[6];
            
            int s = ((SelectorPanel) this.getParent()).getHandleSize();
            
            PathIterator itr = area.getPathIterator(null);
            
            while (!itr.isDone()) {
                int r = itr.currentSegment(segment);
                if (r == PathIterator.SEG_LINETO || r == PathIterator.SEG_MOVETO) {
                    g2d.setColor(new Color(185, 200, 234));
                    g2d.fillRect((int) segment[0] - s / 2, (int) segment[1] - s / 2,
                        s, s);
                    
                    g2d.setColor(new Color(0, 54, 176));
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
    
    private boolean focused = false;
    
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
