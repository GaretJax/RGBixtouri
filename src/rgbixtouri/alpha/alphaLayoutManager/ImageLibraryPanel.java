package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import net.miginfocom.swing.MigLayout;


/**
 * @author Stéphane
 * Panel where user select the image to treat
 * The user can select an image or a folder to show in panel. 
 */
public class ImageLibraryPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JScrollPane scrollPanel;
	protected JList imageList;
	private JButton imageButton;
	private JButton folderButton;
	
	public Frame mainFrame;
	
	private ImageLibraryController controler;

	private ImageListModel listModel;

	public ImageLibraryPanel(Frame mainFrame){
		this.mainFrame=mainFrame;
		listModel = new ImageListModel();
		listModel.addPropertyChangeListener(new ImageLibraryListener(this));

		imageList = new JList(listModel);
		imageList.setLayoutOrientation(JList.VERTICAL);
		imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPanel = new JScrollPane(imageList);
		
		controler = new ImageLibraryController(this, listModel);
		
		imageButton = new JButton("Image");
		imageButton.setActionCommand("image");
		imageButton.addActionListener(controler);
		folderButton = new JButton("Folder");
		folderButton.setActionCommand("folder");
		folderButton.addActionListener(controler);

		MigLayout migL = new MigLayout();
		this.setLayout(migL);

		this.add(scrollPanel, "dock center");
		this.add(folderButton, "dock south");
		this.add(imageButton, "dock south");
	}
}
