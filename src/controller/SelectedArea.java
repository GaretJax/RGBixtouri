package controller;

import gui.SelectionPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import model.ImageModel;



public class SelectedArea extends JComponent implements AncestorListener, FocusListener, MouseListener, MouseMotionListener, KeyListener  {
    
    private static final long serialVersionUID = 1193609448831953910L;
    private Area area; 
    private final ImageModel.Zone zone;
    private final SelectionPanel container;
    
    private boolean focused = false;
    
    public SelectedArea(Area area, ImageModel.Zone zone, SelectionPanel container) {
        this.area = area;
        this.container = container;
        this.zone = zone;
        
        this.container.model.getArea(zone).addArea(area);
        
        this.addAncestorListener(this);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addFocusListener(this);
        this.addKeyListener(this);
    }
    
    public Vector<Point> getVertices(Area a) {
        PathIterator itr = a.getPathIterator(null);

        Vector<Point> vs = new Vector<Point>();
        
        double[] segment = new double[6];
            
        while (!itr.isDone()) {
            int r = itr.currentSegment(segment);
            if (r == PathIterator.SEG_LINETO || r == PathIterator.SEG_MOVETO) {
                vs.add(new Point((int) segment[0], (int) segment[1]));
            }
            
            itr.next();
        }
        
        vs.remove(vs.size()-1);
        
        return vs;
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
    
    private Point scalePointBack(final Point p) {
        double ratio = this.container.getDisplayRatio();
        Rectangle bounds = this.getBounds();
        Rectangle cb = this.container.getImageBounds();
        
        p.translate(bounds.x - cb.x, bounds.y - cb.y);
        p.x /= ratio;
        p.y /= ratio;
        
        return p;
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
            
            int s = this.container.getHandleSize();
            
            for (Point p : this.getVertices(area)) {
                g2d.setColor(handle);
                g2d.fillRect(p.x - s / 2, p.y - s / 2, s, s);
                
                g2d.setColor(border);
                g2d.drawRect(p.x - s / 2, p.y - s / 2, s, s);
            }
            
            /*if (this.handle >= 0) {
                Point p = this.getVertices(area).get(this.handle);
                
                g2d.setColor(border);
                g2d.fillRect(p.x - s / 2, p.y - s / 2, s, s);
            }*/
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
        Area a = this.getScaledArea();
        
        if (a.contains(x, y)) {
            return true;
        }
        
        /*int s = this.container.getHandleSize();
        
        for (Point p : this.getVertices(area)) {
            if (new Rectangle(p.x - s, p.y - s, 2*s, 2*s).contains(x, y)) {
                return true;
            }
        }*/
        
        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    private int handle = -1;
    
    @Override
    public void mousePressed(MouseEvent e) {
        this.requestFocusInWindow();
        
        ratio = this.container.getDisplayRatio();
        
        Rectangle bounds = this.getBounds();
        Rectangle cb = this.container.getImageBounds();
        
        start = e.getPoint();
        
        start.translate(bounds.x - cb.x, bounds.y - cb.y);
        
        start.x /= ratio;
        start.y /= ratio;
        
        
        /*Point p = e.getPoint();
        
        int s = this.container.getHandleSize();
        
        Vector<Point> vs = this.getVertices(this.getScaledArea());
        
        for (int i = 0; i<vs.size(); i++) {
            Point v = vs.get(i);
            
            if (new Rectangle(v.x - s, v.y - s, s*2, s*2).contains(p)) {
                this.handle = i;
                this.repaint();
                return;
            }
        }
        
        this.handle = -1;*/
        this.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        /*if (this.handle >= 0) {
            Vector<Point> vs = this.getVertices(area);
            
            vs.get(this.handle).setLocation(this.scalePointBack(e.getPoint()));
            
            Polygon poly = new Polygon();
            
            for (Point p : vs) {
                poly.addPoint(p.x, p.y);
            }

            this.area = new Area(poly);
        }*/
        
        this.handle = -1;
        this.start = null;
        
        this.recalculateBounds();
        this.repaint();
        
        this.notifyEdit();
    }
    
    private void notifyEdit() {
        this.recalculateBounds();
        this.repaint();
        this.container.model.getArea(this.zone).areaEdited();
    }

    private Point start = null;
    private double ratio;
    
    @Override
    public void mouseDragged(MouseEvent e) {
        Point end = e.getPoint();
        
        Rectangle bounds = this.getBounds();
        Rectangle cb = this.container.getImageBounds();
        
        end.translate(bounds.x - cb.x, bounds.y - cb.y);
        
        end.x /= ratio;
        end.y /= ratio;
        
        
        if (this.handle >= 0) {
            /*Vector<Point> vs = this.getVertices(area);
            
            vs.get(this.handle).setLocation(this.scalePointBack(e.getPoint()));
            
            Polygon poly = new Polygon();
            
            for (Point p : vs) {
                poly.addPoint(p.x, p.y);
            }

            this.area = new Area(poly);
            
            this.recalculateBounds();
            this.repaint();*/
        }
        
        if (start != null) {
            AffineTransform at = new AffineTransform();
            
            at.translate(end.x - start.x, end.y - start.y);
            
            this.area.transform(at);
            
            this.recalculateBounds();
            this.repaint();
        }
        
        start = end;
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        AffineTransform at = new AffineTransform();
        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                this.container.requestFocusInWindow();
                break;
            
            case KeyEvent.VK_UP:
                
                if (e.isShiftDown()) {
                    at.translate(0, -9);    
                }
                
                at.translate(0, -1);
                this.area.transform(at);
                this.notifyEdit();
                break;
            case KeyEvent.VK_DOWN:
                if (e.isShiftDown()) {
                    at.translate(0, 9);    
                }
                
                at.translate(0, 1);
                this.area.transform(at);
                this.notifyEdit();
                break;
            case KeyEvent.VK_LEFT:
                if (e.isShiftDown()) {
                    at.translate(-9, 0);    
                }
                
                at.translate(-1, 0);
                this.area.transform(at);
                this.notifyEdit();
                break;
            case KeyEvent.VK_RIGHT:
                if (e.isShiftDown()) {
                    at.translate(9, 0);    
                }
                at.translate(1, 0);
                this.area.transform(at);
                this.notifyEdit();
                break;
            
            case KeyEvent.VK_BACK_SPACE:
                this.container.remove(this);
                this.container.repaint();
                this.container.model.getArea(this.zone).removeArea(this.area);
                break;       
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}
