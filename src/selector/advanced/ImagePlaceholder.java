package selector.advanced;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;

import rgbixtouri.alpha.alphaLayoutManager.LanguageUpdate;
import rgbixtouri.alpha.alphaLayoutManager.LanguageUpdater;
import rgbixtouri.alpha.language.Language;


public class ImagePlaceholder extends JPanel implements LanguageUpdate {

    /** Generated serialVersionUID */
    private static final long serialVersionUID = -4276574146318310148L;
    
    private JLabel label;
    
    public ImagePlaceholder() {
        this.setLayout(new BorderLayout());
        label = new JLabel(Language.getResourceBundle().getString("selector.imagepanel.placeholder.msg"), JLabel.CENTER);
        this.add(label);
        
        LanguageUpdater lu = LanguageUpdater.getInstanceOfLanguageUpdater();
        lu.addObject(this);
    }

    @Override
    public void updateLanguage(ResourceBundle resourceBundle) {
        label.setText(resourceBundle.getString("selector.imagepanel.placeholder.msg"));
    }
    
}
