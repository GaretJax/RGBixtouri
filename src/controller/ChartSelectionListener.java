package controller;

import gui.Chart2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class ChartSelectionListener implements MouseListener{

	private Chart2D target;
	
	public ChartSelectionListener(Chart2D target){
		this.target=target;	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Chart2D selectedChart=(Chart2D) e.getSource();
		target.setChartName(selectedChart.getChartName());
		target.setxAxeName(selectedChart.getxAxeName());
		target.setyAxeName(selectedChart.getyAxeName());
		target.setType(selectedChart.getType());
		target.setBackground(selectedChart.getBackground());
		target.setPixelsInSkin(selectedChart.getPixelsInSkin());
		target.setPixelsInWound(selectedChart.getPixelsInWound());
		target.repaint();
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
		
	}
	
}
