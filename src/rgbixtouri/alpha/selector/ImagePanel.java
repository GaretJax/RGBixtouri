package rgbixtouri.alpha.selector;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 2044905103542084967L;

	private final BufferedImage image;
	private int width;
	private int height;
	private double ratio;
	
	private final SelectorPanel selector;

	public ImagePanel(String imagePath) throws IOException {
		this(ImageIO.read(new File(imagePath)));
	}

	public ImagePanel(BufferedImage image) {
		this.image = image;
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		this.ratio = this.width * 1. / this.height;
		
		this.selector = new SelectorPanel(this.width, this.height);
		this.add(this.selector);
	}

	@Override
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Dimension size = this.getSize();
		int s = 10;
		int h = size.height, w = size.width;
		int iw, ih;
		int ix, iy;

		g2d.setColor(new Color(204, 204, 204));

		for (int x = 0; x < w; x += s) {
			for (int y = (x % (2 * s)); y < h; y += 2 * s) {
				g2d.fillRect(x, y, s, s);
			}
		}

		if (w / this.ratio < h) {
			iw = w;
			ih = (int) (w / this.ratio);
		} else {
			iw = (int) (h * this.ratio);
			ih = h;
		}
		
		ix = (w - iw) / 2;
		iy = (h - ih) / 2;

		g.drawImage(this.image, ix, iy, iw, ih, this);
		
		this.selector.setBounds(ix, iy, iw, ih);
	}

}
