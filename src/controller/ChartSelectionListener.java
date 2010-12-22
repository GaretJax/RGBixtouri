package controller;

import gui.Chart2D;
import gui.GraphesPanel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;

public class ChartSelectionListener implements MouseListener{

	private Chart2D target;
	private GraphesPanel parentPanel;
	
	public ChartSelectionListener(GraphesPanel parentPanel, Chart2D target){
		this.target=target;	
		this.parentPanel=parentPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		Chart2D selectedChart=(Chart2D) e.getSource();
		parentPanel.clearBorder();
		selectedChart.setBorder(BorderFactory.createLineBorder(Color.black));
		target.setChartName(selectedChart.getChartName());
		target.setxAxeName(selectedChart.getxAxeName());
		target.setyAxeName(selectedChart.getyAxeName());
		target.setType(selectedChart.getType());
		target.setBackground(selectedChart.getBackground());
		target.setPixelsInSkin(selectedChart.getPixelsInSkin());
		target.setPixelsInWound(selectedChart.getPixelsInWound());
		target.repaint();
	}
	
}
