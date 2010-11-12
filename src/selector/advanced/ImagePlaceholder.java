package selector.advanced;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;

import rgbixtouri.alpha.alphaLayoutManager.LanguageUpdate;


public class ImagePlaceholder extends JPanel implements LanguageUpdate {

    /** Generated serialVersionUID */
    private static final long serialVersionUID = -4276574146318310148L;
    
    public ImagePlaceholder() {
        this.setLayout(new BorderLayout());
        this.add(new JLabel("Select an image from the sidebar on the left to begin to edit it.", JLabel.CENTER));
    }

    @Override
    public void updateLanguage(ResourceBundle resourceBundle) {
        this.add(new JLabel(resourceBundle.getString("selector.imagepanel.placeholder"), JLabel.CENTER));
    }
    
}
