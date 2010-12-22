package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import language.LanguageUpdate;
import language.LanguageUpdater;
import model.ImageModel;
import model.ImageModel.Zone;
import model.ZoneTableModel;

public class ZoneStatePanel extends JPanel implements Observer, LanguageUpdate{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable zonesTableWound;
	private JTable zonesTableSkin;
	private JScrollPane scrollPaneWound;
	private JScrollPane scrollPaneSkin;
	
	ImageModel is = null;
	 
	public ZoneStatePanel(){
		Dimension screen = new Dimension(this.getWidth(), 50);
		this.setLayout(new BorderLayout());
		zonesTableWound=new JTable(3,3);
		zonesTableWound.setPreferredScrollableViewportSize(screen);
		zonesTableSkin=new JTable(3,3);
		zonesTableSkin.setPreferredScrollableViewportSize(screen);
		scrollPaneWound=new JScrollPane(zonesTableWound, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneSkin= new JScrollPane(zonesTableSkin, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPaneWound, BorderLayout.NORTH);
		this.add(scrollPaneSkin, BorderLayout.SOUTH);
		
		
		
		LanguageUpdater lu = LanguageUpdater.getInstanceOfLanguageUpdater();
		lu.addObject(this);
	}
	
	public void selectedImageChanged(ImageModel is){
		this.is = is;
		is.addObserver(this);
		zonesTableWound.setModel(new ZoneTableModel(is.getArea(Zone.WOUND)));
		zonesTableSkin.setModel(new ZoneTableModel(is.getArea(Zone.SKIN)));

	}

	@Override
	public void update(Observable selection, Object _) { //selection of type ImageModel
	}

	@Override
	public void updateLanguage(ResourceBundle resourceBundle) {
		if(is != null){
			zonesTableWound.setModel(new ZoneTableModel(is.getArea(Zone.WOUND)));
			zonesTableSkin.setModel(new ZoneTableModel(is.getArea(Zone.SKIN)));
		}
	}
	
	
}
