package gui;

import gui.Chart2D.chartType;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.ImageModel;

import com.atticlabs.zonelayout.swing.ZoneLayout;
import com.atticlabs.zonelayout.swing.ZoneLayoutFactory;

import controller.ChartSelectionListener;

public class GraphesPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	ZoneLayout zLayout;
	public Chart2D rgChart;
	public Chart2D rbChart;
	public Chart2D gbChart;
	private ChartSelectionListener selectionListener;
	public Chart2D rgbSlot;
	public ZoneStatePanel zonePanel;
	private JPanel smallCharts;
	ImageModel imageSelection;

	public GraphesPanel() {
		rgbSlot = new Chart2D("RG", "R", "G", Color.white, chartType.RG);

		smallCharts = new JPanel(new BorderLayout());

		// Zone layout is the layout manager used
		zLayout = ZoneLayoutFactory.newZoneLayout();
		zLayout.addRow("a+ab+bc+c");
		zLayout.compile();
		zLayout.getZone("a").setTake(33, 100);
		zLayout.getZone("b").setTake(33, 100);
		zLayout.getZone("c").setTake(33, 100);
		smallCharts.setLayout(zLayout);
		smallCharts.setMinimumSize(new Dimension(100,100));
		smallCharts.setPreferredSize(new Dimension(100,100));
		selectionListener = new ChartSelectionListener(this, rgbSlot);

		this.setLayout(new BorderLayout());

		rgChart = new Chart2D("RG", "R", "G", Color.WHITE, chartType.RG);
		rgChart.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		rgChart.addMouseListener(selectionListener);
		rbChart = new Chart2D("RB", "R", "B", Color.WHITE, chartType.RB);
		rbChart.addMouseListener(selectionListener);
		gbChart = new Chart2D("GB", "G", "B", Color.WHITE, chartType.GB);
		gbChart.addMouseListener(selectionListener);
		zonePanel = new ZoneStatePanel();
		smallCharts.add(rgChart, "a");
		smallCharts.add(rbChart, "b");
		smallCharts.add(gbChart, "c");
		zonePanel.setMaximumSize(new Dimension(this.getWidth(), 100));
		this.add(smallCharts, BorderLayout.NORTH);
		this.add(rgbSlot, BorderLayout.CENTER);
		this.add(zonePanel, BorderLayout.SOUTH);

	}

	public void clearBorder() {
		rgChart.setBorder(null);
		rbChart.setBorder(null);
		gbChart.setBorder(null);
	}
}
