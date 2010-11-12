package selector.advanced;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Area;
import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;

import selector.advanced.models.ImageModel;

public class SelectionPanel extends JLayeredPane implements ComponentListener {

	private static final long serialVersionUID = 2044905103542084967L;

	public final static Integer EDITOR_LAYER = new Integer(2);
	public final static Integer AREA_LAYER = new Integer(1);
	
	final ImageModel model;
	private final SelectionEditor editor;

	public SelectionPanel(ImageModel model) {
	    this.model = model;
	    this.setLayout(null);
        this.addComponentListener(this);
        
        this.editor = new SelectionEditor(this);
        this.add(this.editor, SelectionPanel.EDITOR_LAYER);
	}
	
	int getHandleSize() {
        return Math.min((int) (this.getWidth() / 80.0) + 4, 10);
    }
	
	Rectangle getImageBounds() {
	    return this.model.getBounds(this.getSize());
	}
	
	double getDisplayRatio() {
	    return this.getImageBounds().width * 1. / this.model.getSize().width;
	}
	
	public void addArea(Vector<Point> points) {
	    Polygon poly = new Polygon();
	    
	    for (Point p : points) {
	        poly.addPoint(p.x, p.y);
	    }
	    
	    this.addArea(new Area(poly));
	}
	
	public void addArea(Area area) {
	    SelectedArea a = new SelectedArea(area, this);
	    this.add(a, SelectionPanel.AREA_LAYER);
	    a.requestFocusInWindow();
	}

    @Override
    public void componentHidden(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {
        this.editor.setBounds(this.getBounds());
        
        for (Component area : this.getComponentsInLayer(SelectionPanel.AREA_LAYER)) {
            ((SelectedArea) area).recalculateBounds();
        }
    }

    @Override
    public void componentShown(ComponentEvent e) {}

}
