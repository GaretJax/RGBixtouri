package rgbixtouri.alpha.selector;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SelectorTest extends JFrame {

	private static final long serialVersionUID = 2168646722445553846L;

	public SelectorTest() {
		this.buildUI(false);
		this.setVisible(true);
	}

	private void buildUI(boolean fullscreen) {
		JComponent imagePanel = null;

		try {
			imagePanel = new ImagePanel("assets/wound1.jpg");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}

		this.add(imagePanel);
		Dimension screen = this.getToolkit().getScreenSize();
		Dimension window = new Dimension(1100, 847);
		Point location = new Point((screen.width - window.width) / 2,
				(screen.height - window.height) / 2);

		
		this.setSize(window);
		this.setLocation(location);
	
		if (fullscreen) {
			GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice device = e.getScreenDevices()[1];
			
			this.setUndecorated(true);
			this.setResizable(false);
			
			device.setFullScreenWindow(this);
		}
	}

	public static void main(String[] args) {
		new SelectorTest();
	}

}
