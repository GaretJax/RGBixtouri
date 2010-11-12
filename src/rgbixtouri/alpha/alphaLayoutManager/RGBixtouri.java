package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Dimension;

import javax.swing.JFrame;

import selector.advanced.ImagePanel;

import com.atticlabs.zonelayout.swing.ZoneLayout;
import com.atticlabs.zonelayout.swing.ZoneLayoutFactory;

/**
 * 
 * @author Stéphane Main class of application. Main frame containing other
 *         panels.
 * 
 */
public class RGBixtouri extends JFrame {
	private static final long serialVersionUID = 1L;

	private ImageLibraryPanel imageSelectorPanel;
	public selector.advanced.ImagePanel imagePanel;
	public GraphesPanel graphesPanel;
	private ClustersPanel clustersPanel;

	/**
	 * Constructor
	 */
	public RGBixtouri() {
		this.buildUI();
		this.setVisible(true);
	}

	/**
	 * Build the user interface of the frame
	 */
	private void buildUI() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Zone layout is the layout manager used
		ZoneLayout zLayout = ZoneLayoutFactory.newZoneLayout();
		zLayout.addRow("B+I+.G+G");
		zLayout.addRow(".B..IC+C");
		zLayout.compile();

		zLayout.getZone("B").setTake(0, 100);
		zLayout.getZone("I").setTake(100, 90);
		zLayout.getZone("G").setTake(100, 90);
		zLayout.getZone("C").setTake(100, 10);
		this.setLayout(zLayout);

		setSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(400, 300));

		// creation and setting of the panels
		imagePanel = new ImagePanel();
		imagePanel.setPreferredSize(new Dimension(300, 300));
		graphesPanel = new GraphesPanel();
		graphesPanel.setPreferredSize(new Dimension(300, 300));
		clustersPanel = new ClustersPanel();
		clustersPanel.setPreferredSize(new Dimension(300, 150));
		imageSelectorPanel = new ImageLibraryPanel(this);
		imageSelectorPanel.setPreferredSize(new Dimension(200, 200));

		this.add(imageSelectorPanel, "B");
		this.add(imagePanel, "I");
		this.add(graphesPanel, "G");
		this.add(clustersPanel, "C");

	}

	public static void main(String[] args) {
		new RGBixtouri();
	}
}
