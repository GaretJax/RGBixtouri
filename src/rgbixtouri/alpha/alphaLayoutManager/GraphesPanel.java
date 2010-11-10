package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.BorderLayout;
import java.awt.Component;

import java.awt.Color;

import javax.swing.JPanel;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart.controllers.mouse.ChartMouseController;
import org.jzy3d.plot3d.primitives.Scatter;

import rgbixtouri.alpha.alphaLayoutManager.Chart2D.chartType;

import com.atticlabs.zonelayout.swing.ZoneLayout;
import com.atticlabs.zonelayout.swing.ZoneLayoutFactory;

public class GraphesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
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
		
		Chart2D rgChart=new Chart2D("RG", "R", "G", Color.WHITE, chartType.RG);
		Chart2D rbChart=new Chart2D("RB", "R", "B", Color.WHITE, chartType.RB);
		Chart2D gbChart=new Chart2D("GB", "G", "B", Color.WHITE, chartType.GB);
//		Chart2D rgbChart=new Chart2D("RGB", "3d", "3d", Color.WHITE, chartType.RG);
		Chart3D rgbChart=new Chart3D("Red", "Green", "Blue");		
		
		this.add(rgChart, "a");
		this.add(rbChart, "b");
		this.add(gbChart, "c");
		JPanel rgbSlot = new JPanel(new BorderLayout());
		rgbSlot.add((Component) rgbChart.getCanvas());
		this.add(rgbSlot, "d");
		ChartMouseController mouse = new ChartMouseController();
		rgbChart.addController(mouse);
	}
}
