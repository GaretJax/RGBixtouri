package rgbixtouri.alpha.alphaLayoutManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarListener implements ActionListener{
	private int menuAction;
	private LanguageUpdater languageUpdater;
	
	public static final int EXIT_ACTION = 0;
	
	//LanguageAction
	public static final int ENGLISH_ACTION = 1;
	public static final int FRENCH_ACTION = 2;
	public static final int GERMAN_ACTION = 3;
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(menuAction == EXIT_ACTION){
			System.exit(0);
		}else if(menuAction == ENGLISH_ACTION){
			languageUpdater.updateLanguage("en", "");
		}else if(menuAction == FRENCH_ACTION){
			languageUpdater.updateLanguage("fr", "");
		}else if(menuAction == GERMAN_ACTION){
			languageUpdater.updateLanguage("de", "");
		}
		
	}
	public MenuBarListener(int menuAction){
			this.menuAction = menuAction;
			languageUpdater = LanguageUpdater.getInstanceOfLanguageUpdater();
	}
}
