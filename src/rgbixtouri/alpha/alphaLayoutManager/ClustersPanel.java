package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Color;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import com.atticlabs.zonelayout.swing.ZoneLayout;
import com.atticlabs.zonelayout.swing.ZoneLayoutFactory;

public class ClustersPanel extends JPanel implements LanguageUpdate{
	
	public ClustersPanel(){
		ZoneLayout zLayout = ZoneLayoutFactory.newZoneLayout();
		zLayout.addRow("a+ab+bc+c");
		zLayout.addRow("d+......d");
		zLayout.compile();
		zLayout.getZone("a").setTake(33, 33);
		zLayout.getZone("b").setTake(33, 33);
		zLayout.getZone("c").setTake(33, 33);
		zLayout.getZone("d").setTake(100, 77);
		this.setLayout(zLayout);

		this.add(new Cluster2D("RG", "R", "G", Color.WHITE), "a");
		this.add(new Cluster2D("RB", "R", "B", Color.WHITE), "b");
		this.add(new Cluster2D("GB", "G", "B", Color.WHITE), "c");
		this.add(new Cluster2D("RGB", "3d", "3d", Color.WHITE), "d");
	}

	@Override
	public void updateLanguage(ResourceBundle resourceBundle) {
		// TODO Auto-generated method stub

	}

}