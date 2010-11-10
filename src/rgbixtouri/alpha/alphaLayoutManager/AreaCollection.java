package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.geom.Area;
import java.util.Vector;

public class AreaCollection {
	Vector<Area> areas;

	public AreaCollection(){
		
	}
	
	public int[] getColors(){
		int[] colors = {0, 0xff0000, 0x00ff00, 0x0000ff};
		return colors;
	}
}