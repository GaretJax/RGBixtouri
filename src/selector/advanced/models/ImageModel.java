package selector.advanced.models;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.Observable;

import rgbixtouri.alpha.alphaLayoutManager.AreaCollection;

public class ImageModel extends Observable {
    
    private final BufferedImage image;
    private final double ratio;
    public final static int SKIN = 0;
    public final static int WOUND = 1;
    
    private AreaCollection wound;
    private AreaCollection skin;
    
    public ImageModel(BufferedImage image) {
        this.image = image;
        this.ratio = image.getWidth() * 1. / image.getHeight();
        wound=new AreaCollection(this);
        skin=new AreaCollection(this);
    }
    
    public BufferedImage getImage() {
        return this.image;
    }
    
    public double getRatio() {
        return this.ratio;
    }
    
    public Dimension getSize() {
        return new Dimension(this.image.getWidth(), this.image.getHeight());
    }
    
    public AreaCollection getArea(int type) {
        switch (type) {
            case SKIN:
                return this.skin;
            case WOUND:
                return this.wound;
            default:
                throw new IllegalArgumentException("Invalid area type " + type + ".");
        }
    }
    
    public Rectangle getBounds(Dimension maxSize) {
        int h = maxSize.height, w = maxSize.width;
        int iw, ih;
        int ix, iy;
        
        if (w / this.ratio < h) {
            iw = w;
            ih = (int) (w / this.ratio);
        } else {
            iw = (int) (h * this.ratio);
            ih = h;
        }
        
        ix = (w - iw) / 2;
        iy = (h - ih) / 2;

        return new Rectangle(ix, iy,iw, ih); 
    }
    
    public void addArea(Area a){
    	wound.addArea(a);
    	skin.addArea(a);
    	setChanged();
    	notifyObservers(this);
    	System.out.println("Area added");
    }
    
}
