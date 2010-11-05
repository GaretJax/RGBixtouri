package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import rgbixtouri.alpha.selector.ImagePanel;

import com.atticlabs.zonelayout.swing.ZoneLayout;
import com.atticlabs.zonelayout.swing.ZoneLayoutFactory;

/**
 * 
 * @author Stéphane
 * Main class of application. Main frame containing other panels.
 * 
 */
public class RGBixtouri extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private ImageLibraryPanel imageSelectorPanel;
	private rgbixtouri.alpha.selector.ImagePanel imagePanel; 
	private ParametersPanel parametersPanel;
	private GraphesPanel graphesPanel;
	private ClustersPanel clustersPanel;

	/**
	 * Constructor
	 */
	public RGBixtouri(){
		this.buildUI();
		this.setVisible(true);
	}

	/**
	 * Build the user interface of the frame
	 */
	private void buildUI(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//Zone layout is the layout manager used
		ZoneLayout zLayout = ZoneLayoutFactory.newZoneLayout();
		zLayout.addRow("B+I+IG+G");
		zLayout.addRow(".BP+PC+C");
		zLayout.compile();

		zLayout.getZone("B").setTake(0, 100);
		zLayout.getZone("I").setTake(100, 100);
		zLayout.getZone("G").setTake(100, 100);
		zLayout.getZone("C").setTake(100, 100);
		zLayout.getZone("P").setTake(50, 100);
		this.setLayout(zLayout);

		setSize(new Dimension(800, 600));
		setMinimumSize(new Dimension(400,300));

		//creation and setting of the panels
		imageSelectorPanel = new ImageLibraryPanel();
		imageSelectorPanel.setPreferredSize(new Dimension(200,200));
		try {
			imagePanel = new ImagePanel("assets/wound1.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imagePanel.setPreferredSize(new Dimension(300,300));
		parametersPanel = new ParametersPanel();
		parametersPanel.setPreferredSize(new Dimension(300,150));
		graphesPanel = new GraphesPanel();
		graphesPanel.setPreferredSize(new Dimension(300,300));
		clustersPanel = new ClustersPanel();
		clustersPanel.setPreferredSize(new Dimension(300,150));

		this.add(imageSelectorPanel, "B");
		this.add(imagePanel, "I");
		this.add(parametersPanel, "P");
		this.add(graphesPanel, "G");
		this.add(clustersPanel, "C");
	}

	public static void main(String[] args) {
		new RGBixtouri();
	}
}
