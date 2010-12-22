package model;

import java.util.Observable;
import java.util.Observer;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import language.Language;
import language.LanguageUpdate;
import model.ImageModel.Zone;

public class ZoneTableModel extends AbstractTableModel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AreaCollection myCollection;

	public ZoneTableModel(AreaCollection collection) {
			this.myCollection = collection;
			myCollection.addObserver(this);
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return Language.getResourceBundle().getString("zonetablemodel.columnname.num.msg");
		case 1:
			return Language.getResourceBundle().getString("zonetablemodel.columnname.type.msg");
		default:
			return Language.getResourceBundle().getString("zonetablemodel.columnname.surface.msg");
		}
	}

	@Override
	public int getRowCount() {
		return myCollection.areas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			if (myCollection.type == Zone.SKIN)
				return Language.getResourceBundle().getString("zonetablemodel.typename.skin.msg");
			return Language.getResourceBundle().getString("zonetablemodel.typename.wound.msg");
		default:
			return myCollection.getAreaSurface(myCollection.areas.get(rowIndex));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		fireTableChanged(new TableModelEvent(this));
	}
}
