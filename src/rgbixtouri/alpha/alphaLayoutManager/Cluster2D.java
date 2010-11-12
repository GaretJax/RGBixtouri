package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Color;

import javax.swing.JPanel;

public class Cluster2D extends JPanel{
	
    private static final long serialVersionUID = 2521877046744374462L;
    
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