package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

public class Chart2D extends JPanel {
	private static final long serialVersionUID = 1L;
	
	
	String xAxeName;
	String yAxeName;
	String chartName;
	Color bgColor;
	
	double xOneUnitSize;
	double yOneUnitSize;
	
	public Chart2D(String chartName, String xAxeName, String yAxeName, Color bgColor){
		this.xAxeName=xAxeName;
		this.yAxeName=yAxeName;
		this.chartName=chartName;
		this.bgColor=bgColor;
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		setBackground(bgColor);
		Graphics2D g2d = (Graphics2D)g;
		
		int width=this.getWidth();
		int height=this.getHeight();
		
		//Chart name
		float xOfChartName=40*width/100;
		float yOfChartName=15*height/100;
		g2d.drawString(chartName, xOfChartName, yOfChartName);
		
		//AXE X name
		float xOfXAxeName=40*width/100;
		float yOfXAxeName=95*height/100;
		g2d.drawString(xAxeName, xOfXAxeName, yOfXAxeName);
		
		//AXE X
		double xOfXAxe=20*width/100;
		double yOfXAxe=85*height/100;
		double xAxeSize=75*width/100;
		Line2D.Double xAxe = new Line2D.Double(xOfXAxe, yOfXAxe, xOfXAxe+xAxeSize, yOfXAxe);
		g2d.draw(xAxe);
			
		//AXE Y
		double xOfYAxe=xOfXAxe;
		double yOfYAxe=20*height/100;
		double yAxeSize=65*height/100;
		Line2D.Double yAxe = new Line2D.Double(xOfYAxe, yOfYAxe, xOfYAxe, yOfYAxe+yAxeSize);
		g2d.draw(yAxe);
		
		//AXE Y name
		g2d.rotate(-Math.PI/2);
		float xOfYAxeName=50*(-1)*height/100;
		float yOfYAxeName=15*width/100;
		g2d.drawString(yAxeName, xOfYAxeName, yOfYAxeName);
		
		//unit settings
		xOneUnitSize=xAxeSize/255;
		yOneUnitSize=yAxeSize/255;
		
		//print points
		g2d.rotate(Math.PI/2);
		Point p=getPanelCoord(new Point(255, 255), new Point((int)xOfXAxe, (int)yOfXAxe));
		Ellipse2D.Double pixel = new Ellipse2D.Double(p.x-2, p.y-2, 4, 4);
		g2d.setPaint(Color.RED);
		g2d.fill(pixel);
		g2d.draw(pixel);
	}
	
	private Point getPanelCoord(Point chartCoord, Point orig){
		int x=(int) (chartCoord.x*xOneUnitSize+orig.x);
		int y=(int) (orig.y-(chartCoord.y*yOneUnitSize));
		return new Point(x, y); 
	}
}
