package rgbixtouri.alpha.alphaLayoutManager;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import org.jzy3d.chart.Chart;
import org.jzy3d.colors.Color;
import org.jzy3d.maths.Coord3d;
import org.jzy3d.plot3d.primitives.Scatter;
import org.jzy3d.plot3d.rendering.view.modes.CameraMode;

import rgbixtouri.alpha.alphaLayoutManager.ImageSelection;

public class Chart3D extends Chart implements Observer{
	String xAxeName;
	String yAxeName;
	String zAxeName;
	String chartName;

	private Vector<Coord3d> pixels;
	private Vector<Color> colors;

	public Chart3D(String xName, String yName, String zName){
		this.xAxeName=xName;
		this.yAxeName=yName;
		this.zAxeName=zName;
		pixels=new Vector<Coord3d>();
		colors=new Vector<Color>();
		ImageSelection factice= new ImageSelection();
		update(null, factice);
		Scatter scatter = new Scatter(pixels.toArray(new Coord3d[1]), colors.toArray(new Color[1]));
		this.getScene().add(scatter);
		scatter.setWidth(10);
		this.getAxeLayout().setXAxeLabel(xAxeName);
		this.getAxeLayout().setYAxeLabel(yAxeName);
		this.getAxeLayout().setZAxeLabel(zAxeName);

	}

	@Override
	public void update(Observable o, Object selection) {
		ImageSelection imageSelection = (ImageSelection) selection;
		AreaCollection wound=imageSelection.getArea(ImageSelection.WOUND);
		Integer[] woundColors=wound.getColors();

		pixels.clear();

		double red, green, blue;
		for (int color : woundColors) {
			red=(color & 0xff0000)>>16;
			green=(color & 0xff00) >> 8;
			blue=(color & 0xff);
			pixels.add(new Coord3d(red, green, blue));
			colors.add(Color.RED);
		}	

		AreaCollection skin=imageSelection.getArea(ImageSelection.SKIN);
		Integer[] skinColors=skin.getColors();
		
		for (int color : skinColors) {
			red=(color & 0xff0000)>>16;
			green=(color & 0xff00) >> 8;
			blue=(color & 0xff);
			pixels.add(new Coord3d(red, green, blue));
			colors.add(Color.BLUE);
		}
	}
}
