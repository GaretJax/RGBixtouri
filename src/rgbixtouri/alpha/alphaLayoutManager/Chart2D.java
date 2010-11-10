package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JPanel;

import rgbixtouri.alpha.alphaLayoutManager.ImageSelection;

public class Chart2D extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	public enum chartType{
		RG, RB, GB
	}

	String xAxeName;
	String yAxeName;
	String chartName;
	Color bgColor;
	chartType type;
	
	private Vector<Point> pixelsInWound;
	private Vector<Point> pixelsInSkin;

	double xOneUnitSize;
	double yOneUnitSize;

	public Chart2D(String chartName, String xAxeName, String yAxeName, Color bgColor, chartType type){
		this.xAxeName=xAxeName;
		this.yAxeName=yAxeName;
		this.chartName=chartName;
		this.bgColor=bgColor;
		this.type=type;
		
		pixelsInWound = new Vector<Point>();
		pixelsInSkin = new Vector<Point>();
		ImageSelection factice= new ImageSelection();
		update(null, factice);
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
		g2d.rotate(Math.PI/2);

		//print points
		Point p;
		g2d.setPaint(Color.RED);
		for (Point point : pixelsInWound) {
			p=getPanelCoord(point, new Point((int)xOfXAxe, (int)yOfXAxe));
			Ellipse2D.Double pixel = new Ellipse2D.Double(p.x-2, p.y-2, 4, 4);
			g2d.fill(pixel);
			g2d.draw(pixel);
		}		
		g2d.setPaint(Color.BLUE);
		for (Point point : pixelsInSkin) {
			p=getPanelCoord(point, new Point((int)xOfXAxe, (int)yOfXAxe));
			Ellipse2D.Double pixel = new Ellipse2D.Double(p.x-2, p.y-2, 4, 4);
			g2d.fill(pixel);
			g2d.draw(pixel);
		}
	}

	private Point getPanelCoord(Point chartCoord, Point orig){
		int x=(int) (chartCoord.x*xOneUnitSize+orig.x);
		int y=(int) (orig.y-(chartCoord.y*yOneUnitSize));
		return new Point(x, y); 
	}


	@Override 
	public void update(Observable arg0, Object selection) {
		ImageSelection imageSelection = (ImageSelection) selection;
		AreaCollection wound=imageSelection.getArea(ImageSelection.WOUND);
		int[] woundColors=wound.getColors();
		
		pixelsInWound.clear();
				
		if(type==chartType.RG){
			int red;
			int green;
			for (int color : woundColors) {			
				red=(color & 0xff0000) >> 16;
				green=(color & 0xff00) >> 8;
				pixelsInWound.add(new Point(red, green));
			}
			//			blue = pixel & 0xff;
		} else if(type==chartType.RB){
			int red;
			int blue;
			for (int color : woundColors) {
				red=(color & 0xff0000) >> 16;
				blue=(color & 0xff);
				pixelsInWound.add(new Point(red, blue));
			}
		} else if(type==chartType.GB){
			int green;
			int blue;
			for (int color : woundColors) {
				green=(color & 0xff00) >> 8;
				blue=(color & 0xff);
				pixelsInWound.add(new Point(green, blue));
			}
		}
		AreaCollection skin=imageSelection.getArea(ImageSelection.SKIN);
		int[] skinColors=skin.getColors();
		
		pixelsInSkin.clear();
		
		if(type==chartType.RG){
			int red;
			int green;
			for(int color : skinColors){
				red=(color & 0xff0000) >> 16;
				green=(color & 0xff00) >> 8;
				pixelsInSkin.add(new Point(red, green));
			}
		} else if(type==chartType.RB){
			int red;
			int blue;
			for(int color : skinColors){
				red=(color & 0xff0000) >> 16;
				blue=(color & 0xff);
				pixelsInSkin.add(new Point(red, blue));
			}
		} else if(type==chartType.GB){
			int green;
			int blue;
			for(int color : skinColors){
				green=(color & 0xff00) >> 8;
				blue=(color & 0xff);
				pixelsInSkin.add(new Point(green, blue));
			}
		}
		
	}
}
