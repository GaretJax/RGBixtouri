package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.BorderLayout;
import java.awt.Component;

import java.awt.Color;

import javax.swing.JPanel;

import org.jzy3d.chart.Chart;
import org.jzy3d.chart.controllers.mouse.ChartMouseController;
import org.jzy3d.plot3d.primitives.Scatter;

import rgbixtouri.alpha.alphaLayoutManager.Chart2D.chartType;

import com.atticlabs.zonelayout.swing.Magnitude;
import com.atticlabs.zonelayout.swing.ZoneLayout;
import com.atticlabs.zonelayout.swing.ZoneLayoutFactory;

public class GraphesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	ZoneLayout zLayout;
	public Chart2D rgChart;
	public Chart2D rbChart;
	public Chart2D gbChart;
	public Chart3D rgbChart;
	
	
	public GraphesPanel(){
		
		//Zone layout is the layout manager used
		zLayout = ZoneLayoutFactory.newZoneLayout();
		zLayout.addRow("a+ab+bc+c");
		zLayout.addRow("d+......d");
		zLayout.compile();
		zLayout.getZone("a").setTake(33,35);
		zLayout.getZone("b").setTake(33, 35);
		zLayout.getZone("c").setTake(33, 35);
		zLayout.getZone("d").setTake(100, 65);
		
		

		
		this.setLayout(zLayout);
		
		rgChart=new Chart2D("RG", "R", "G", Color.WHITE, chartType.RG);
		rbChart=new Chart2D("RB", "R", "B", Color.WHITE, chartType.RB);
		gbChart=new Chart2D("GB", "G", "B", Color.WHITE, chartType.GB);
//		Chart2D rgbChart=new Chart2D("RGB", "3d", "3d", Color.WHITE, chartType.RG);
		rgbChart=new Chart3D("Red", "Green", "Blue");
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
