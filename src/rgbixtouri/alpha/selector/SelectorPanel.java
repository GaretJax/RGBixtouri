package rgbixtouri.alpha.selector;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;
import java.util.Vector;

import javax.swing.JComponent;

public class SelectorPanel extends JComponent implements MouseListener, MouseMotionListener, ComponentListener {

	private static final long serialVersionUID = -7906905420333424845L;
	
	private final int imgWidth;
	private final int imgHeight;
	
	
	public SelectorPanel(int width, int height) {
		this.imgWidth = width;
		this.imgHeight = height;
		
		System.out.println("selector");
		
		this.addComponentListener(this);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	protected double getRatio() {
	    return this.getWidth() * 1. / this.imgWidth;
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
	
		double ratio = this.getRatio();
		
		AffineTransform at = new AffineTransform();
		at.scale(ratio, ratio);
		
		Area s1 = new Area(at.createTransformedShape(p1));
		
		PathIterator i1 = s1.getPathIterator(null);
		
		g2d.setColor(new Color(0, 54, 176));
		g2d.draw(s1);
		
		
		g2d.setColor(new Color(0, 78, 255, 64));
		g2d.fill(s1);
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		
		double[] segment = new double[6];
		int s = this.getHandleSize();
		
		while (!i1.isDone()) {
			int r = i1.currentSegment(segment);
			if (r == PathIterator.SEG_LINETO || r == PathIterator.SEG_MOVETO) {
				g2d.setColor(new Color(185, 200, 234));
				g2d.fillRect((int) segment[0] - s/2, (int) segment[1] - s/2, s, s);
				
				g2d.setColor(new Color(0, 54, 176));
				g2d.drawRect((int) segment[0] - s/2, (int) segment[1] - s/2, s, s);
			}
			
			i1.next();
		}
	}
	
	int getHandleSize() {
	    return Math.min((int) (this.getWidth() / 80.0) + 4, 10);
	}
	
	
	/*
	 * Selection areas creation code
	 */
	
	Vector<Point> areaStub = null;
	Point currentLocation = null;
	
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            this.add(new SelectedArea(this.areaStub));
            this.areaStub = null;
        } else {
            if (this.areaStub == null) {
                // Create a new stub
                this.areaStub = new Vector<Point>();
            }
            
            Point p = e.getPoint();
            
            p.x /= this.getRatio();
            p.y /= this.getRatio();
            
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
            p.x /= this.getRatio();
            p.y /= this.getRatio();
            this.currentLocation = p;        
            
            this.repaint();
        }
    }

    @Override
    public void componentHidden(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {
        for (Component a : this.getComponents()) {
            ((SelectedArea) a).recalculateBounds();
        }
    }

    @Override
    public void componentShown(ComponentEvent e) {}
	
}
