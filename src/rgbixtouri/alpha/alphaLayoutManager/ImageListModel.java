package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Vector;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;

/**
 * @author Stéphane
 * Model containing images that must be shown in ImageLibraryPanel's list.
 */
public class ImageListModel extends AbstractListModel{
	private static final long serialVersionUID = 1L;

	private PropertyChangeSupport pcs;
	
	private Vector<ImageIcon> imageList;

	public ImageListModel(){
		pcs = new PropertyChangeSupport(this);
		imageList = new Vector<ImageIcon>();
		Image image=Toolkit.getDefaultToolkit().getImage("assets/wound1.jpg").getScaledInstance(100, 100, Image.SCALE_DEFAULT) ;
		imageList.add(new ImageIcon(image));
	}
	
	public void emptyAndAddImage(String filename){
		imageList.clear();
		Image image=Toolkit.getDefaultToolkit().getImage(filename).getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		imageList.add(new ImageIcon(image));
		pcs.firePropertyChange("imageList", this.getSize()-1, this.getSize());
		System.out.println("add "+filename);
	}
	
	public void emptyImage(){
		imageList.clear();
	}
	
	public void addImage(String filename){
		Image image = Toolkit.getDefaultToolkit().getImage(filename);
		image.getWidth(null);
		image.getHeight(null);
		double ratio=image.getHeight(null)/100;
		image=image.getScaledInstance((int) (image.getWidth(null)/ratio), 100, Image.SCALE_DEFAULT);
		imageList.add(new ImageIcon(image));
		pcs.firePropertyChange("imageList", this.getSize()-1, this.getSize());
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl){
		pcs.addPropertyChangeListener("imageList", pcl);
	}

	@Override
	public Object getElementAt(int arg0) {
		return imageList.get(arg0);
	}

	@Override
	public int getSize() {
		return imageList.size();
	}
}


