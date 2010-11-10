package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Image;
import java.util.Observable;

public class ImageSelection extends Observable{
	
	public enum areaCollectionType {
	    WOUND, SKIN 
	}
	
	private Image image;
	private AreaCollection woundAreas;
	private AreaCollection skinAreas;
	public ImageSelection()  {
		woundAreas=new AreaCollection();
		skinAreas=new AreaCollection();
	}
	
	public AreaCollection getAreaCollection(areaCollectionType type){
		if(type==areaCollectionType.WOUND)
			return woundAreas;
		return skinAreas;
	}
}
