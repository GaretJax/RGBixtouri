package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Point;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

import selector.advanced.models.ImageModel;

public class AreaCollection extends Observable {
	Vector<Area> areas;
	ImageModel parent;
	BufferedImage image;

	public AreaCollection(ImageModel parent){
		this.parent=parent;
		image=parent.getImage();
		areas=new Vector<Area>();
	}

	public void addArea(Area area){
		areas.add(area);
		setChanged();
		this.notifyObservers();
	}

	public void removeArea(Area area){
		areas.remove(area);
		setChanged();
		this.notifyObservers();
	}

	public Integer[] getColors(){
		Vector<Integer> colors = new Vector<Integer>();
		double[] tab = new double[6];
		int rgb;
		Set<Integer> pixelsVisited = new HashSet<Integer>();
		Point firstPixel;
		Queue<Point> pixelsToVisit = new LinkedList<Point>();
		if(areas!=null){
			for (Area area : areas) {
				area.getPathIterator(null).currentSegment(tab);
				firstPixel = new Point((int)tab[0], (int)tab[1]);

				rgb = image.getRGB(firstPixel.x, firstPixel.y);
				colors.add(rgb);
				pixelsVisited.add(getPixelIndex(firstPixel));

				for (Point neighbor : getNeighbors(firstPixel, area)) {
					pixelsToVisit.add(neighbor);
				}

				//
				Point pixel;
				while(!pixelsToVisit.isEmpty()){
					pixel=pixelsToVisit.poll();
					if(!pixelsVisited.contains(getPixelIndex(pixel))){
						rgb = image.getRGB(pixel.x, pixel.y);
						colors.add(rgb);
						pixelsVisited.add(getPixelIndex(pixel));
						for (Point neighbor : getNeighbors(pixel, area)) {
							if(!pixelsVisited.contains(getPixelIndex(neighbor)))
								pixelsToVisit.add(neighbor);
						}
					}
				}
			}
		}
		return colors.toArray(new Integer[0]);
	}

	private Point[] getNeighbors(Point root, Area area){
		Vector<Point> neighbors=new Vector<Point>();
		for(int x=-1;x<=1;x++){
			for(int y=-1;y<=1;y++){
				if(x==root.x && y==root.y) continue;
				if(area.contains(new Point(root.x+x, root.y+y))){
					neighbors.add(new Point(root.x+x, root.y+y));
				}
			}
		}
		return neighbors.toArray(new Point[0]);
	}

	private int getPixelIndex(Point pixel){
		int x=pixel.x;
		int y=pixel.y;
		int width=image.getWidth();
		return x+(y*width);
	}
}