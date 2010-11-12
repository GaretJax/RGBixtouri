package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.image.BufferedImage;
import java.util.Observable;

public class ImageSelection extends Observable {
    
    public final static int SKIN = 0;
    public final static int WOUND = 1;
    
    private BufferedImage image;
    
    private AreaCollection wound;
    private AreaCollection skin;
    
    public ImageSelection()  {
        this.skin = new AreaCollection(null);
        this.wound = new AreaCollection(null);
    }
    
    public BufferedImage getImage() {
        return this.image;
    }
    
    /**
     * Invoke me as selection.getArea(ImageSelection.SKIN)
     * 
     * @todo Consider returning a copy of the AreaCollection.
     * 
     * @param type
     * @return
     */
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
}
