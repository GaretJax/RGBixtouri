package gui;


import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import controller.ImageLibraryController;
import controller.ImageLibraryListener;
import controller.ImageListListener;


import language.Language;
import language.LanguageUpdate;
import language.LanguageUpdater;
import main.RGBixtouri;
import model.ImageListModel;
import net.miginfocom.swing.MigLayout;


/**
 * @author Stéphane
 * Panel where user select the image to treat
 * The user can select an image or a folder to show in panel. 
 */
public class ImageLibraryPanel extends JPanel implements LanguageUpdate {
	private static final long serialVersionUID = 1L;

	private JScrollPane scrollPanel;
	public JList imageList;
	private JButton imageButton;
	private JButton folderButton;
	
	public RGBixtouri mainFrame;
	
	private ImageLibraryController controler;

	private ImageListModel listModel;

	public ImageLibraryPanel(RGBixtouri mainFrame){
		this.mainFrame=mainFrame;
		listModel = new ImageListModel();
		listModel.addPropertyChangeListener(new ImageLibraryListener(this));

		imageList = new JList(listModel);
		imageList.setLayoutOrientation(JList.VERTICAL);
		imageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		ListSelectionModel listSelectionModel = imageList.getSelectionModel();
		ImageListListener selectionListener = new ImageListListener(mainFrame.imagePanel, listModel, mainFrame.graphesPanel.rgChart,
				mainFrame.graphesPanel.rbChart, mainFrame.graphesPanel.gbChart, mainFrame.graphesPanel.rgbSlot, listSelectionModel);
		listSelectionModel.addListSelectionListener(selectionListener);
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPanel = new JScrollPane(imageList);
		
		controler = new ImageLibraryController(this, listModel);
		
		imageButton = new JButton(Language.getResourceBundle().getString("imagelibrarypanel.button.image.msg"));
		imageButton.setActionCommand("image");
		imageButton.addActionListener(controler);
		folderButton = new JButton(Language.getResourceBundle().getString("imagelibrarypanel.button.folder.msg"));
		folderButton.setActionCommand("folder");
		folderButton.addActionListener(controler);

		MigLayout migL = new MigLayout();
		this.setLayout(migL);

		this.add(scrollPanel, "dock center");
		this.add(folderButton, "dock south");
		this.add(imageButton, "dock south");
		
		LanguageUpdater lu = LanguageUpdater.getInstanceOfLanguageUpdater();
		lu.addObject(this);
	}

	@Override
	public void updateLanguage(ResourceBundle resourceBundle) {
		imageButton.setText(resourceBundle.getString("imagelibrarypanel.button.image.msg"));
		folderButton.setText(resourceBundle.getString("imagelibrarypanel.button.folder.msg"));
	}
}
