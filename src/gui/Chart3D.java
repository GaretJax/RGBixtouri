package gui;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import model.AreaCollection;
import model.ImageModel;

import org.jzy3d.chart.Chart;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;


public class Chart3D extends Chart implements Observer{
	String xAxeName;
	String yAxeName;
	String zAxeName;
	String chartName;
	Scatter scatter;
	private Vector<Coord3d> pixels;
	private Vector<Color> colors;

	public Chart3D(String xName, String yName, String zName){
		this.xAxeName=xName;
		this.yAxeName=yName;
		this.zAxeName=zName;
		pixels=new Vector<Coord3d>();
		colors=new Vector<Color>();
		scatter = new Scatter(pixels.toArray(new Coord3d[0]), colors.toArray(new Color[0])); 
		this.getScene().add(scatter);
		scatter.setWidth(1);
		this.getAxeLayout().setXAxeLabel(xAxeName);
		this.getAxeLayout().setYAxeLabel(yAxeName);
		this.getAxeLayout().setZAxeLabel(zAxeName);
		this.getView().setMaximized(false);
		this.getCanvas().getView().setMaximized(false);
		this.getView().getCamera().setStretchToFill(false);
		
	}
	
	
	
	public void selectedImageChanged(ImageModel is){
		is.addObserver(this);
		//System.out.println("imageChanged");
		update(is, is);
	}

	@Override
	public void update(Observable selection, Object _) {
		this.getScene().remove(scatter);
		//System.out.println("update3d");
		ImageModel imageSelection = (ImageModel) selection;
		AreaCollection wound=imageSelection.getArea(ImageModel.Zone.WOUND);
		Integer[] woundColors=wound.getColors();

		pixels.clear();
		colors.clear();
		
		double red, green, blue;
		for (int color : woundColors) {
			red=(color & 0xff0000)>>16;
			green=(color & 0xff00) >> 8;
			blue=(color & 0xff);
			pixels.add(new Coord3d(red, green, blue));
			colors.add(Color.RED);
		}	

		AreaCollection skin=imageSelection.getArea(ImageModel.Zone.SKIN);
		Integer[] skinColors=skin.getColors();
		
		for (int color : skinColors) {
			red=(color & 0xff0000)>>16;
			green=(color & 0xff00) >> 8;
			blue=(color & 0xff);
			pixels.add(new Coord3d(red, green, blue));
			colors.add(Color.BLUE);
		}
		//System.out.println("end update 3d");
		scatter.setData(pixels.toArray(new Coord3d[0]));
		scatter.setColors(colors.toArray(new Color[0]));
		
		this.getScene().add(scatter);
		
		this.render();
		
	}
}
