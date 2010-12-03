package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import org.jzy3d.maths.Scale;
import org.jzy3d.plot3d.transform.Transform;

public class RgbComponentListener implements ComponentListener {
	Chart3D rgbChart;
	public RgbComponentListener(Chart3D rgbChart){
		this.rgbChart=rgbChart;
	}
	
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		System.out.println("resize!!!!");
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
