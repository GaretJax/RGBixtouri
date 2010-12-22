package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.TableColumn;

import model.ImageModel;
import model.ImageModel.Zone;
import model.ZoneTableModel;

public class ZoneStatePanel extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable zonesTableWound;
	private JTable zonesTableSkin;
	private JScrollPane scrollPaneWound;
	private JScrollPane scrollPaneSkin;
	 
	public ZoneStatePanel(){
		Dimension screen = new Dimension(this.getWidth(), 50);
		this.setLayout(new BorderLayout());
		zonesTableWound=new JTable();
		zonesTableWound.setModel(new ZoneTableModel(null));
		zonesTableWound.setPreferredScrollableViewportSize(screen);
		zonesTableSkin=new JTable();
		zonesTableSkin.setModel(new ZoneTableModel(null));
		zonesTableSkin.setPreferredScrollableViewportSize(screen);
		scrollPaneWound=new JScrollPane(zonesTableWound, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneSkin= new JScrollPane(zonesTableSkin, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(scrollPaneWound, BorderLayout.NORTH);
		this.add(scrollPaneSkin, BorderLayout.SOUTH);
	}
	
	public void selectedImageChanged(ImageModel is){
		is.addObserver(this);
		zonesTableWound.setModel(new ZoneTableModel(is.getArea(Zone.WOUND)));
		zonesTableSkin.setModel(new ZoneTableModel(is.getArea(Zone.SKIN)));
	}

	@Override
	public void update(Observable selection, Object _) { //selection of type ImageModel
	}
	
	
}
