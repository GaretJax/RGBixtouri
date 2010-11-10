package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Point;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

public class AreaCollection extends Observable {
	Vector<Area> areas;
	ImageSelection parent;
	BufferedImage image;

	public AreaCollection(ImageSelection parent){
		this.parent=parent;
		//image=parent.getImage();
	}
	
	public void addArea(Area area){
		areas.add(area);
	}
	
	public void removeArea(Area area){
		areas.remove(area);
	}
	
	public Integer[] getColors(){
		Vector<Integer> colors = new Vector<Integer>();
		double[] tab = new double[6];
		int rgb;
		Set<Integer> pixelsVisited = new HashSet<Integer>();
		Point firstPoint;
		Queue<Point> pixelsToVisit = new LinkedList<Point>();
		for (Area area : areas) {
			area.getPathIterator(null).currentSegment(tab);
			firstPoint = new Point((int)tab[0], (int)tab[1]);
			
			rgb = image.getRGB(firstPoint.x, firstPoint.y);
			colors.add(rgb);
			pixelsVisited.add(getPixelIndex(firstPoint));
			
			for (Point neighbor : getNeighbors(firstPoint, area)) {
				pixelsToVisit.add(neighbor);
			}
		}
		return colors.toArray(new Integer[1]);
	}
	
	private Point[] getNeighbors(Point root, Area area){
		Point[] neighbors=new Point[8];
		int index=0;
		for(int x=-1;x<=1;x++){
			for(int y=-1;y<=1;y++){
				if(x==root.x && y==root.y) continue;
				if(area.contains(new Point(root.x+x, root.y+y))){
					neighbors[index++]=new Point(root.x+x, root.y+y);
				}
			}
		}
		return neighbors;
	}
	
	private int getPixelIndex(Point pixel){
		int x=pixel.x;
		int y=pixel.y;
		int width=image.getWidth();
		return x+(y*width);
	}
}