package selector.advanced;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;

public class ImagePanel extends JLayeredPane implements ComponentListener {

	private static final long serialVersionUID = 2044905103542084967L;

	private BufferedImage image;
	private int width;
	private int height;
	private double ratio;

	public ImagePanel() {
	    this.setLayout(null);
        this.addComponentListener(this);
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
		
		this.width = this.image.getWidth();
		this.height = this.image.getHeight();
		
		this.ratio = this.width * 1. / this.height;
	}
	
	public void setImage(String imagePath) throws IOException {
	    this.setImage(ImageIO.read(new File(imagePath)));
	}
	
	private Rectangle getImageBounds() {
	    Dimension size = this.getSize();
        int h = size.height, w = size.width;
        int iw, ih;
        int ix, iy;
	    
	    if (w / this.ratio < h) {
            iw = w;
            ih = (int) (w / this.ratio);
        } else {
            iw = (int) (h * this.ratio);
            ih = h;
        }
        
        ix = (w - iw) / 2;
        iy = (h - ih) / 2;

        return new Rectangle(ix, iy,iw, ih); 
	}

	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
		Graphics2D g2d = (Graphics2D) g;

		int s = 10;
		int h = this.height, w = this.width;		

		g2d.setColor(new Color(204, 204, 204));

		for (int x = 0; x < w; x += s) {
			for (int y = (x % (2 * s)); y < h; y += 2 * s) {
				g2d.fillRect(x, y, s, s);
			}
		}
		
		Rectangle b = this.getImageBounds();
		g.drawImage(this.image, b.x, b.y, b.width, b.height, null);
	}

    @Override
    public void componentHidden(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {
        //this.editor.setBounds(this.getImageBounds());
    }

    @Override
    public void componentShown(ComponentEvent e) {}

}
