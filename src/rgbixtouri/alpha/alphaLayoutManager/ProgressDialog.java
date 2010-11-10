package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FilenameFilter;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;


/** 
 * ProgressDialog: dialog with progress bar. A task is simulated by
 * using a timer.
 */
public class ProgressDialog extends JDialog implements PropertyChangeListener{

	private static final long serialVersionUID = 1L;

	JProgressBar dpb;
	JLabel jl;
	LoadTask task;
	Frame parent;
	ImageListModel listModel;
	File folder;

	public ProgressDialog(Frame parentFrame, ImageListModel model, File folder){
		dpb = new JProgressBar(0, 100);
		jl = new JLabel("Progression: ");
		this.listModel=model;
		this.folder=folder;
		add(BorderLayout.CENTER, dpb);
		add(BorderLayout.NORTH, jl);
		setSize(300, 75);
		setLocationRelativeTo(parentFrame);
		setVisible(true);
		task=new LoadTask();
		task.addPropertyChangeListener(this);
		task.execute();
	}
	
	public void setValue(int progress){
		dpb.setValue(progress);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if ("progress" == evt.getPropertyName()) {
			int progress = (Integer) evt.getNewValue();
			setValue(progress);
		} 
	}
	
	class LoadTask extends SwingWorker<Integer, Integer>{

		@Override
		protected Integer doInBackground() throws Exception {
			String[] imagesList=folder.list(new ImageNameFilter());
			listModel.emptyImage();
			int counter=0;
			for (String imageName : imagesList) {
				listModel.addImage(folder.getAbsolutePath()+"\\"+imageName);
				setProgress(++counter*100/imagesList.length);
			}
			return null;
		}

		@Override
		protected void done() {
			super.done();
			setVisible(false);
		}
	}
	
	class ImageNameFilter implements FilenameFilter{

		@Override
		public boolean accept(File dir, String name) {
			String extension = Utils.getExtension(new File(dir+name));
			if (extension != null) {
				if (extension.equals(Utils.tiff) ||
						extension.equals(Utils.tif) ||
						extension.equals(Utils.gif) ||
						extension.equals(Utils.jpeg) ||
						extension.equals(Utils.jpg) ||
						extension.equals(Utils.bmp) ||
						extension.equals(Utils.png)) {
					return true;
				} else {
					return false;
				}
			}
			return false;
		}
	}

}