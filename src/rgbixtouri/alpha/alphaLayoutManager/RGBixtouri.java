package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.atticlabs.zonelayout.swing.ZoneLayout;
import com.atticlabs.zonelayout.swing.ZoneLayoutFactory;

public class RGBixtouri extends JFrame{
  
  private ImageLibraryPanel imageSelectorPanel;
  private ImagePanel imagePanel;
  private ParametersPanel parametersPanel;
  private GraphesPanel graphesPanel;
  private ClustersPanel clustersPanel;
  
  public RGBixtouri(){
    this.buildUI();
    this.setVisible(true);
  }
  
  private void buildUI(){
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
    ZoneLayout zLayout = ZoneLayoutFactory.newZoneLayout();
    zLayout.addRow("B+I+IG+G");
    zLayout.addRow(".BP+PC+C");
    zLayout.compile();
    
    zLayout.getZone("B").setTake(0, 100);
    zLayout.getZone("I").setTake(50, 100);
    zLayout.getZone("G").setTake(100, 100);
    zLayout.getZone("C").setTake(100, 100);
    zLayout.getZone("P").setTake(50, 100);
    this.setLayout(zLayout);
    setSize(new Dimension(800, 600));
    setMinimumSize(new Dimension(400,300));
    
    imageSelectorPanel = new ImageLibraryPanel();
    imageSelectorPanel.setBackground(Color.red);
    imageSelectorPanel.setPreferredSize(new Dimension(100,200));
    imagePanel = new ImagePanel();
    imagePanel.setBackground(Color.black);
    parametersPanel = new ParametersPanel();
    parametersPanel.setBackground(Color.green);
    graphesPanel = new GraphesPanel();
    graphesPanel.setBackground(Color.blue);
    clustersPanel = new ClustersPanel();
    clustersPanel.setBackground(Color.yellow);
    
    this.add(imageSelectorPanel, "B");
    this.add(imagePanel, "I");
    this.add(parametersPanel, "P");
    this.add(graphesPanel, "G");
    this.add(clustersPanel, "C");
    
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    new RGBixtouri();
  }

}
