package rgbixtouri.alpha.alphaLayoutManager;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ImageLibraryListener implements PropertyChangeListener{
	
	private ImageLibraryPanel imageLibrary;
	
	public ImageLibraryListener(ImageLibraryPanel view){
		this.imageLibrary=view;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		imageLibrary.imageList.updateUI();
		imageLibrary.revalidate();
	}

}
