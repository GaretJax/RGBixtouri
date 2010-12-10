package main;


import gui.GraphesPanel;
import gui.ImageLibraryPanel;
import gui.ImagePanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import language.Language;
import language.LanguageUpdate;
import language.LanguageUpdater;


import com.atticlabs.zonelayout.swing.ZoneLayout;
import com.atticlabs.zonelayout.swing.ZoneLayoutFactory;

import controller.MenuBarListener;

/**
 * 
 * @author Stéphane Main class of application. Main frame containing other
 *         panels.
 * 
 */
public class RGBixtouri extends JFrame implements LanguageUpdate{
	private static final long serialVersionUID = 1L;

	private ImageLibraryPanel imageSelectorPanel;
	public gui.ImagePanel imagePanel;
	public GraphesPanel graphesPanel;
	
	private JMenuBar jMenuBar;
	private JMenu menuMenu, submenuLang;
	private JMenuItem menuItemExit;
	private JMenuItem menuItemEnglish, menuItemFrench, menuItemGerman;
	
	private LanguageUpdater languageUpdater;


	/**
	 * Constructor
	 */
	public RGBixtouri() {
		languageUpdater = LanguageUpdater.getInstanceOfLanguageUpdater();
		languageUpdater.addObject(this);
		this.buildUI();
		this.setVisible(true);
	}

	/**
	 * Build the user interface of the frame
	 */
	private void buildUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Zone layout is the layout manager used
		ZoneLayout zLayout = ZoneLayoutFactory.newZoneLayout();
		zLayout.addRow("B+I+.G+.");
		zLayout.addRow(".B..I..G");
		zLayout.compile();

		zLayout.getZone("B").setTake(0, 100);
		zLayout.getZone("I").setTake(100, 90);
		zLayout.getZone("G").setTake(100, 90);
		//zLayout.getZone("C").setTake(100, 10);
		this.setLayout(zLayout);

		setSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(400, 300));

		// creation and setting of the panels
		imagePanel = new ImagePanel();
		imagePanel.setPreferredSize(new Dimension(300, 300));
		graphesPanel = new GraphesPanel();
		graphesPanel.setPreferredSize(new Dimension(300, 300));
		imageSelectorPanel = new ImageLibraryPanel(this);
		imageSelectorPanel.setPreferredSize(new Dimension(200, 200));
		
		
		buildMenu();
		
		this.add(imageSelectorPanel, "B");
		this.add(imagePanel, "I");
		this.add(graphesPanel, "G");
		//this.add(clustersPanel, "C");
		

	}
	
	private void buildMenu(){
		//Menu creation
		jMenuBar = new JMenuBar();
		//Build the menuBar.
		menuMenu = new JMenu(Language.getResourceBundle().getString("frame.menu.menu.msg"));
		menuMenu.setMnemonic(KeyEvent.VK_M);
		jMenuBar.add(menuMenu);

		//Build the menu
		//SubMenu
		submenuLang = new JMenu(Language.getResourceBundle().getString("frame.menu.language.msg"));
		submenuLang.setMnemonic(KeyEvent.VK_L);
		//English
		menuItemEnglish = new JMenuItem(Language.getResourceBundle().getString("frame.menu.english.msg"));
		menuItemEnglish.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menuItemEnglish.addActionListener(new MenuBarListener(MenuBarListener.ENGLISH_ACTION));
		submenuLang.add(menuItemEnglish);
		//French
		menuItemFrench = new JMenuItem(Language.getResourceBundle().getString("frame.menu.french.msg"));
		menuItemFrench.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_F, ActionEvent.ALT_MASK));
		menuItemFrench.addActionListener(new MenuBarListener(MenuBarListener.FRENCH_ACTION));
		submenuLang.add(menuItemFrench);
		//German
		menuItemGerman = new JMenuItem(Language.getResourceBundle().getString("frame.menu.german.msg"));
		menuItemGerman.setAccelerator(KeyStroke.getKeyStroke(
		        KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menuItemGerman.addActionListener(new MenuBarListener(MenuBarListener.GERMAN_ACTION));
		submenuLang.add(menuItemGerman);
		
		//Add submenu for language
		menuMenu.add(submenuLang);
		

		//Quitter
		menuMenu.addSeparator();
		menuItemExit = new JMenuItem(Language.getResourceBundle().getString("frame.menu.exit.msg"), KeyEvent.VK_Q);
		menuItemExit.getAccessibleContext().setAccessibleDescription("Quitter le programme");
		menuItemExit.addActionListener(new MenuBarListener(MenuBarListener.EXIT_ACTION));
		menuItemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
		menuMenu.add(menuItemExit);
		
		


		
		this.setJMenuBar(jMenuBar);
	}

	public static void main(String[] args) {
		new RGBixtouri();
	}

	@Override
	public void updateLanguage(ResourceBundle resourceBundle) {
		menuMenu.setText(resourceBundle.getString("frame.menu.menu.msg"));
		submenuLang.setText(resourceBundle.getString("frame.menu.language.msg"));
		menuItemExit.setText(resourceBundle.getString("frame.menu.exit.msg"));
	}
}
