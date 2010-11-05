package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;

import com.atticlabs.zonelayout.swing.ZoneLayout;
import com.atticlabs.zonelayout.swing.ZoneLayoutFactory;

public class GraphesPanel extends JPanel{
	public GraphesPanel(){
		
		//Zone layout is the layout manager used
		ZoneLayout zLayout = ZoneLayoutFactory.newZoneLayout();
		zLayout.addRow("a+ab+bc+c");
		zLayout.addRow("d+......d");
		zLayout.compile();
		zLayout.getZone("a").setTake(33,33);
		zLayout.getZone("b").setTake(33, 33);
		zLayout.getZone("c").setTake(33, 33);
		zLayout.getZone("d").setTake(100, 77);
		this.setLayout(zLayout);
		
		this.add(new Chart2D("RG", "R", "G", Color.WHITE), "a");
		this.add(new Chart2D("RB", "R", "B", Color.WHITE), "b");
		this.add(new Chart2D("GB", "G", "B", Color.WHITE), "c");
		this.add(new Chart2D("RGB", "3d", "3d", Color.WHITE), "d");
	}
}
