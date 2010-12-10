package rgbixtouri.alpha.alphaLayoutManager;

import java.io.IOException;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import selector.advanced.ImagePanel;

public class ImageListListener implements ListSelectionListener {
	ImagePanel panelToSet;
	ImageListModel imageLibrary;
	Chart2D rg, rb, gb;
	Chart3D rgb;
	public ImageListListener(selector.advanced.ImagePanel panelToSet, ImageListModel imageLibrary, Chart2D rg, Chart2D rb, Chart2D gb, Chart3D rgb){
		this.panelToSet=panelToSet;
		this.imageLibrary=imageLibrary;
		this.rg=rg;
		this.rb=rb;
		this.gb=gb;
		this.rgb=rgb;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String imagePath;
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		boolean isAdjusting = e.getValueIsAdjusting();
		if(!isAdjusting){
			int minIndex = lsm.getMinSelectionIndex();
			int maxIndex = lsm.getMaxSelectionIndex();
			for (int i = minIndex; i <= maxIndex; i++) {
				if (lsm.isSelectedIndex(i)) {
					imagePath=imageLibrary.getImagePathAt(i);
					//System.out.println(imagePath + "is selected");
					try {
						panelToSet.setImage(imagePath);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					rg.selectedImageChanged(panelToSet.getModel());
					rb.selectedImageChanged(panelToSet.getModel());
					gb.selectedImageChanged(panelToSet.getModel());
					rgb.selectedImageChanged(panelToSet.getModel());
				}
			}
		}
	}

}
