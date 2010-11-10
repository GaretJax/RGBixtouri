package selector.advanced;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Vector;

import javax.imageio.ImageIO;

import rgbixtouri.alpha.selector.SelectedArea;

public class ImageModel extends Observable {
    
    public void setImage(BufferedImage image) {
    }
    
    public void setImage(String imagePath) throws IOException {
    }
    
    public BufferedImage getImage() {
        return null;
    }
    
    public double getRatio() {
        return 0;
    }
    
    public int getWidth() {
        return 0;
    }
    
    public int getHeight() {
        return 0;
    }
    
    public Vector<SelectedArea> getSelectedAreaCollection(String name) {
        return null;
    }
    
    
    
}
