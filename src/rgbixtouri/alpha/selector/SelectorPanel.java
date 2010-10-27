package rgbixtouri.alpha.selector;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.PathIterator;

import javax.swing.JComponent;

public class SelectorPanel extends JComponent {

	private static final long serialVersionUID = -7906905420333424845L;
	
	private final int width;
	private final int height;
	
	public SelectorPanel(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		Polygon p1 = new Polygon(
				new int[] {100, 100, 800, 800},
				new int[] {100, 800, 800, 100},
				4);
		
		Polygon p2 = new Polygon(
				new int[] {300, 300, 600, 600},
				new int[] {300, 600, 600, 300},
				4);
	
		AffineTransform at = new AffineTransform();
		at.scale(this.getWidth() * 1. / this.width, this.getHeight() * 1. / this.height);
		
		Area s1 = new Area(at.createTransformedShape(p1));
		Area s2 = new Area(at.createTransformedShape(p2));
		
		PathIterator i1 = s1.getPathIterator(null);
		PathIterator i2 = s2.getPathIterator(null);
		
		g2d.setColor(new Color(0, 54, 176));
		g2d.draw(s1);
		
		s1.subtract(s2);
		
		g2d.setColor(new Color(0, 78, 255, 64));
		g2d.fill(s1);
		
		g2d.setColor(new Color(255, 76, 76));
		g2d.draw(s2);
		
		g2d.setColor(new Color(255, 76, 76, 64));
		g2d.fill(s2);
		
		g2d.setStroke(new BasicStroke(2));
		double[] segment = new double[6];
		
		while (!i1.isDone()) {
			int r = i1.currentSegment(segment);
			if (r == PathIterator.SEG_LINETO || r == PathIterator.SEG_MOVETO) {
				g2d.setColor(new Color(185, 200, 234));
				g2d.fillOval((int) segment[0] - 5, (int) segment[1] - 5, 10, 10);
				
				g2d.setColor(new Color(0, 54, 176));
				g2d.drawOval((int) segment[0] - 5, (int) segment[1] - 5, 10, 10);
			}
			
			i1.next();
		}
		
		while (!i2.isDone()) {
			int r = i2.currentSegment(segment);
			if (r == PathIterator.SEG_LINETO || r == PathIterator.SEG_MOVETO) {
				g2d.setColor(new Color(234, 198, 198));
				g2d.fillOval((int) segment[0] - 5, (int) segment[1] - 5, 10, 10);
				
				g2d.setColor(new Color(255, 76, 76));
				g2d.drawOval((int) segment[0] - 5, (int) segment[1] - 5, 10, 10);
			}
			
			i2.next();
		}
	}
	
}
