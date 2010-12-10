package model;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;



public class ImageModel extends Observable implements Observer {
    
    private final BufferedImage image;
    private final double ratio;
    
    public static enum Zone {
        SKIN,
        WOUND
    };
    
    private AreaCollection[] areas = new AreaCollection[2];
    
    public ImageModel(BufferedImage image) {
        this.image = image;
        this.ratio = image.getWidth() * 1. / image.getHeight();
        
        for (int i = this.areas.length-1; i>=0; i--) {
            this.areas[i] = new AreaCollection(this);
            this.areas[i].addObserver(this);
        }

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
    
    public AreaCollection getArea(ImageModel.Zone area) {
        return this.areas[area.ordinal()];
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

    @Override
    public void update(Observable arg0, Object arg1) {
    	setChanged();
        this.notifyObservers();
    }
}
