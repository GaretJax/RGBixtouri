package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import model.AreaCollection;
import model.ImageModel;


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

	private Set<Point> pixelsInWound;
	private Set<Point> pixelsInSkin;

	double xOneUnitSize;
	double yOneUnitSize;

	public Chart2D(String chartName, String xAxeName, String yAxeName, Color bgColor, chartType type){
		this.xAxeName=xAxeName;
		this.yAxeName=yAxeName;
		this.chartName=chartName;
		this.bgColor=bgColor;
		this.type=type;

		pixelsInWound = new HashSet<Point>();
		pixelsInSkin = new HashSet<Point>();
	}

	public void paintComponent(Graphics g) {
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
		
		//AXE X graduation
		int nbPas=10;
		int pas = (int) Math.round(Math.ceil((xAxeSize-10) / (nbPas - 1)));
		int crtPosOnAxe=(int) xOfXAxe;
		for(int i=0; i<nbPas; i++){
			crtPosOnAxe+=pas;
			g2d.draw(new Line2D.Double(crtPosOnAxe,yOfXAxe,crtPosOnAxe, yOfXAxe+5));
		}

		//AXE Y
		double xOfYAxe=xOfXAxe;
		double yOfYAxe=20*height/100;
		double yAxeSize=65*height/100;
		Line2D.Double yAxe = new Line2D.Double(xOfYAxe, yOfYAxe, xOfYAxe, yOfYAxe+yAxeSize);
		g2d.draw(yAxe);

		//AXE Y graduation
		nbPas=10;
		pas = (int) Math.round(Math.ceil((yAxeSize-27) / (nbPas - 1)));
		crtPosOnAxe=(int) yOfXAxe;
		for(int i=0; i<nbPas; i++){
			crtPosOnAxe-=pas;
			g2d.draw(new Line2D.Double(xOfYAxe,crtPosOnAxe,xOfYAxe-5, crtPosOnAxe));
		}
		
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
			Ellipse2D.Double pixel = new Ellipse2D.Double(p.x-2, p.y-2, 1, 1);
			g2d.fill(pixel);
			g2d.draw(pixel);
		}		
		g2d.setPaint(Color.BLUE);
		for (Point point : pixelsInSkin) {
			p=getPanelCoord(point, new Point((int)xOfXAxe, (int)yOfXAxe));
			Ellipse2D.Double pixel = new Ellipse2D.Double(p.x-2, p.y-2, 1, 1);
			g2d.fill(pixel);
			g2d.draw(pixel);
		}
	}

	private Point getPanelCoord(Point chartCoord, Point orig){
		int x=(int) (chartCoord.x*xOneUnitSize+orig.x);
		int y=(int) (orig.y-(chartCoord.y*yOneUnitSize));
		return new Point(x, y); 
	}

	public void selectedImageChanged(ImageModel is){
		is.addObserver(this);
		update(is, is);
	}

	public String getxAxeName() {
		return xAxeName;
	}

	public void setxAxeName(String xAxeName) {
		this.xAxeName = xAxeName;
	}

	public String getyAxeName() {
		return yAxeName;
	}

	public void setyAxeName(String yAxeName) {
		this.yAxeName = yAxeName;
	}

	public chartType getType() {
		return type;
	}

	public void setType(chartType type) {
		this.type = type;
	}

	public String getChartName() {
		return chartName;
	}

	public void setChartName(String chartName) {
		this.chartName = chartName;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	public Set<Point> getPixelsInWound() {
		return pixelsInWound;
	}

	public void setPixelsInWound(Set<Point> pixelsInWound) {
		this.pixelsInWound = pixelsInWound;
	}

	public Set<Point> getPixelsInSkin() {
		return pixelsInSkin;
	}

	public void setPixelsInSkin(Set<Point> pixelsInSkin) {
		this.pixelsInSkin = pixelsInSkin;
	}
	
	@Override 
	public void update(Observable selection, Object _) {
		LoadPixel lp = new LoadPixel(this, selection);
		lp.execute();
	}

	private class LoadPixel extends SwingWorker<Void, Void>{
		Observable selection;
		Chart2D parentChart;
		public LoadPixel(Chart2D parentChart, Observable selection){
			this.selection=selection;
			this.parentChart=parentChart;
		}
		
		protected Void doInBackground() throws Exception {
			ImageModel imageSelection = (ImageModel) selection;
			AreaCollection wound=imageSelection.getArea(ImageModel.Zone.WOUND);
			Integer[] woundColors=wound.getColors();
		
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
			AreaCollection skin=imageSelection.getArea(ImageModel.Zone.SKIN);
			Integer[] skinColors=skin.getColors();
		
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
			
			return null;
		}
		
		protected void done(){
			parentChart.repaint();
		}
	}
}
