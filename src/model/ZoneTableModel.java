package model;

import java.util.Observable;
import java.util.Observer;

import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

import model.ImageModel.Zone;

public class ZoneTableModel extends AbstractTableModel implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AreaCollection myCollection;

	public ZoneTableModel(AreaCollection collection) {
		if(collection!=null){
			this.myCollection = collection;
			myCollection.addObserver(this);
		} else{
			this.myCollection=null;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		switch (column) {
		case 0:
			return "N°";
		case 1:
			return "Type";
		default:
			return "Surface";
		}
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if(myCollection==null)
			return 0;
		return myCollection.areas.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch (columnIndex) {
		case 0:
			return rowIndex + 1;
		case 1:
			if (myCollection.type == Zone.SKIN)
				return "Skin";
			return "Wound";
		default:
			return myCollection.getAreaSurface(myCollection.areas.get(rowIndex));
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		fireTableChanged(new TableModelEvent(this));
	}
}
