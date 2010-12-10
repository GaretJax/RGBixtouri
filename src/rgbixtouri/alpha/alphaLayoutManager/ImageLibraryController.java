package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * @author Stéphane
 */
public class ImageLibraryController implements ActionListener {

	private ImageLibraryPanel parent;
	private ImageListModel listModel;
	private ProgressDialog progressDialog;
	private File f;

	public ImageLibraryController(ImageLibraryPanel parent, ImageListModel listModel){
		this.parent=parent;
		this.listModel=listModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String source = e.getActionCommand();
		// Image chooser
		if (source.equals("image")) {
			JFileChooser fc = new JFileChooser("C:/Users/Stéphane/Documents/My Dropbox/ProjetSemestreIWound/Photos/plaies");
			fc.addChoosableFileFilter(new ImageFilter());
			int action = fc.showOpenDialog(parent);      

			if (action == JFileChooser.APPROVE_OPTION) {        
				String filename = fc.getSelectedFile().getAbsolutePath();
				File f = new File(filename);
				if(!f.exists()){    
					String errorMessage="Le fichier n'est pas existant ("+filename+")";
					JOptionPane.showMessageDialog(parent, errorMessage, "Problem", JOptionPane.ERROR_MESSAGE);
				}

				String extension = Utils.getExtension(f);
				if (extension != null) {
					if (!(extension.equals(Utils.tiff) ||
							extension.equals(Utils.tif) ||
							extension.equals(Utils.gif) ||
							extension.equals(Utils.jpeg) ||
							extension.equals(Utils.jpg) ||
							extension.equals(Utils.bmp) ||
							extension.equals(Utils.png))) {
						String errorMessage="Le fichier choisi n'est pas pris en charge." +
						" Fichiers pris en charge: tiff, tif, gif, jpeg, jpg, bmp, png";
						JOptionPane.showMessageDialog(parent, errorMessage, "Problem", JOptionPane.ERROR_MESSAGE);
					} 
				}
				listModel.emptyAndAddImage(filename);
			}
		} else if (source.equals("folder")) {
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int action = fc.showOpenDialog(parent);
			if(action == JFileChooser.APPROVE_OPTION){
				String folderName = fc.getSelectedFile().getAbsolutePath();
				f = new File(folderName);
				if(f.exists()){
					//folder loading		
					progressDialog=new ProgressDialog(parent.mainFrame, listModel, f);
				} else{
					String errorMessage="Le dossier choisi n'est pas existant ("+f+")";
					JOptionPane.showMessageDialog(parent, errorMessage, "Problem", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		
	}

	class ImageFilter extends FileFilter{
		@Override
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return true;
			}
			String extension = Utils.getExtension(f);
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

		@Override
		public String getDescription() {
			return "Images";
		}
	}

	
}
