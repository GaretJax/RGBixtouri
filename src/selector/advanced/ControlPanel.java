package selector.advanced;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class ControlPanel extends JPanel {
    
    /** Generated serialVersionUID */
    private static final long serialVersionUID = 5285220342636108208L;

    public ControlPanel(ImagePanel imgPanel) {
        this.setBackground(Color.RED);
        this.setPreferredSize(new Dimension(100, 150));
    }
    
}
