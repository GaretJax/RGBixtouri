package selector.advanced.models;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Observable;

import rgbixtouri.alpha.alphaLayoutManager.AreaCollection;

public class ImageModel extends Observable {
    
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
}
