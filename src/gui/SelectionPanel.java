package gui;

import java.awt.Component;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.geom.Area;
import java.util.Vector;

import javax.swing.JLayeredPane;

import model.ImageModel;

import controller.SelectedArea;
import controller.SelectionEditor;
import controller.SelectionEditor.Mode;


public class SelectionPanel extends JLayeredPane implements ComponentListener {
    
    private static final long serialVersionUID = 2044905103542084967L;
    
    public final static Integer EDITOR_LAYER = new Integer(3);
    public final static Integer WOUND_LAYER = new Integer(2);
    public final static Integer SKIN_LAYER = new Integer(1);
    
    public final ImageModel model;
    private final SelectionEditor editor;
    
    public SelectionPanel(ImageModel model) {
        this.model = model;
        this.setLayout(null);
        this.addComponentListener(this);
        
        this.editor = new SelectionEditor(this);
        this.add(this.editor, SelectionPanel.EDITOR_LAYER);
    }
    
    public int getHandleSize() {
        return Math.min((int) (this.getWidth() / 80.0) + 4, 10);
    }
    
    public Rectangle getImageBounds() {
        return this.model.getBounds(this.getSize());
    }
    
    public double getDisplayRatio() {
        return this.getImageBounds().width * 1. / this.model.getSize().width;
    }
    
    public void setMode(SelectionEditor.Mode mode) {
        this.editor.setMode(mode);
    }
    
    public void addArea(Vector<Point> points, ImageModel.Zone zone) {
        Polygon poly = new Polygon();
        
        for (Point p : points) {
            poly.addPoint(p.x, p.y);
        }
        
        this.addArea(new Area(poly), zone);
    }
    
    public void addArea(Area area, ImageModel.Zone zone) {
        Integer layer;
        
        if (zone == ImageModel.Zone.SKIN) {
            layer = SelectionPanel.SKIN_LAYER;
        } else {
            layer = SelectionPanel.WOUND_LAYER;
        }
        
        SelectedArea a = new SelectedArea(area, zone, this);
        this.add(a, layer, 0);
        
        a.requestFocusInWindow();
    }
    
    @Override
    public void componentHidden(ComponentEvent e) {
    }
    
    @Override
    public void componentMoved(ComponentEvent e) {
    }
    
    @Override
    public void componentResized(ComponentEvent e) {
        this.editor.setBounds(this.getBounds());
        
        for (Component area : this.getComponentsInLayer(SelectionPanel.WOUND_LAYER)) {
            ((SelectedArea) area).recalculateBounds();
        }
        
        for (Component area : this.getComponentsInLayer(SelectionPanel.SKIN_LAYER)) {
            ((SelectedArea) area).recalculateBounds();
        }
    }
    
    @Override
    public void componentShown(ComponentEvent e) {
    }
    
}
