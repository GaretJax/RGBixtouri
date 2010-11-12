package selector.advanced;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel {
    
    /** Generated serialVersionUID */
    private static final long serialVersionUID = 5285220342636108208L;
    
    public ControlPanel(final ImagePanel imgPanel) {
        
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
            new Color(150, 150, 150)));
        
        JButton skin = new JButton("Skin");
        skin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                imgPanel.setMode(SelectionEditor.Mode.SKIN);
            }
        });
        
        JButton wound = new JButton("Wound");
        wound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                imgPanel.setMode(SelectionEditor.Mode.WOUND);
            }
        });
        
        this.add(new JButton("Modify"));
        this.add(skin);
        this.add(wound);
    }
    
}
