package selector.advanced.models;

import java.awt.Color;
import java.util.Observable;

public class AreaCollection extends Observable {
    private final ImageModel parent;
    
    public AreaCollection(ImageModel parent) {
        this.parent = parent;
    }
    
    
}
