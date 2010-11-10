package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Color;

import javax.swing.JPanel;

public class Cluster2D extends JPanel{
	
	String xAxeName;
	String yAxeName;
	String clusterName;
	Color bgColor;

	public Cluster2D(String clusterName, String xAxeName, String yAxeName,
			Color bgColor) {
		this.xAxeName = xAxeName;
		this.yAxeName = yAxeName;
		this.clusterName = clusterName;
		this.bgColor = bgColor;
	}

}