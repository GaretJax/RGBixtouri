package gui;


import gui.Chart2D.chartType;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

import com.atticlabs.zonelayout.swing.ZoneLayout;
import com.atticlabs.zonelayout.swing.ZoneLayoutFactory;

import controller.ChartSelectionListener;

public class GraphesPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	ZoneLayout zLayout;
	public Chart2D rgChart;
	public Chart2D rbChart;
	public Chart2D gbChart;	
	private ChartSelectionListener selectionListener;
	Chart2D rgbSlot;
	
	public GraphesPanel(){
		rgbSlot = new Chart2D("RG", "R", "G", Color.white, chartType.RG);
		
		//Zone layout is the layout manager used
		zLayout = ZoneLayoutFactory.newZoneLayout();
		zLayout.addRow("a+ab+bc+c");
		zLayout.addRow("d+......d");
		zLayout.compile();
		zLayout.getZone("a").setTake(33,35);
		zLayout.getZone("b").setTake(33, 35);
		zLayout.getZone("c").setTake(33, 35);
		zLayout.getZone("d").setTake(100, 65);
		
		selectionListener = new ChartSelectionListener(this,rgbSlot);
		
		this.setLayout(zLayout);
		
		rgChart=new Chart2D("RG", "R", "G", Color.WHITE, chartType.RG);
		rgChart.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		rgChart.addMouseListener(selectionListener);
		rbChart=new Chart2D("RB", "R", "B", Color.WHITE, chartType.RB);
		rbChart.addMouseListener(selectionListener);
		gbChart=new Chart2D("GB", "G", "B", Color.WHITE, chartType.GB);
		gbChart.addMouseListener(selectionListener);
		this.add(rgChart, "a");
		this.add(rbChart, "b");
		this.add(gbChart, "c");
		
		this.add(rgbSlot, "d");
	}
	
	public void clearBorder(){
		rgChart.setBorder(null);
		rbChart.setBorder(null);
		gbChart.setBorder(null);
	}
}
