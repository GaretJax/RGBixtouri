package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.SelectionEditor;
import controller.SelectionEditor.Mode;


import language.Language;
import language.LanguageUpdate;
import language.LanguageUpdater;


public class ControlPanel extends JPanel implements LanguageUpdate{
    private JButton skin;
    private JButton wound;
    private JButton modify;
	
    /** Generated serialVersionUID */
    private static final long serialVersionUID = 5285220342636108208L;
    
    public ControlPanel(final ImagePanel imgPanel) {
        
        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
            new Color(150, 150, 150)));
        
        skin = new JButton(Language.getResourceBundle().getString("controlpanel.button.skin.msg"));
        skin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                imgPanel.setMode(SelectionEditor.Mode.SKIN);
            }
        });
        
        wound = new JButton(Language.getResourceBundle().getString("controlpanel.button.wound.msg"));
        wound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                imgPanel.setMode(SelectionEditor.Mode.WOUND);
            }
        });
        
        modify = new JButton(Language.getResourceBundle().getString("controlpanel.button.modify.msg"));
        this.add(modify);
        this.add(skin);
        this.add(wound);
        
        LanguageUpdater lu = LanguageUpdater.getInstanceOfLanguageUpdater();
        lu.addObject(this);
    }

	@Override
	public void updateLanguage(ResourceBundle resourceBundle) {
		modify.setText(resourceBundle.getString("controlpanel.button.modify.msg"));
		wound.setText(resourceBundle.getString("controlpanel.button.wound.msg"));
		skin.setText(resourceBundle.getString("controlpanel.button.skin.msg"));
	}
    
}
