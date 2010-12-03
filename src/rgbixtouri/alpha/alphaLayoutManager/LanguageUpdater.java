package rgbixtouri.alpha.alphaLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JComponent;

import rgbixtouri.alpha.language.Language;

public class LanguageUpdater {
	private static LanguageUpdater languageUpdater;
	private List<LanguageUpdate> objectList;
	ResourceBundle resourceBundle;
	
	private LanguageUpdater(){
		objectList = new ArrayList<LanguageUpdate>();
		resourceBundle = Language.setLanguage(null, null);
	}
	
	public static LanguageUpdater getInstanceOfLanguageUpdater(){
		if(languageUpdater == null){
			languageUpdater = new LanguageUpdater();
		}
		return languageUpdater;
	}
	
	public void addObject(LanguageUpdate languageUpdateObject){
		objectList.add(languageUpdateObject);
	}
	
	public void updateLanguage(String lang, String count){
		resourceBundle = Language.setLanguage(lang, count);
		
		for (int i = 0; i < objectList.size(); i++) {
			objectList.get(i).updateLanguage(resourceBundle);
		}
		
	}
	
}
