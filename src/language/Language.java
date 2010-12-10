package language;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Class for the configuration of the ResourceBundle
 * Use for I18N
 *
 */
public class Language {
	
	//Object use for Create the resourceBundle
	private static ResourceBundle resourceBundle;
	
	//Parameters for choose the language
	private static String language;
	private static String country;
	
	private static Language instanceOfLanguage;
	
	//Constant for the place of the properties files
	final private String PROPERTIES_FILE_NAME = "rgbixtouri.alpha.language.MessageBundle";
	
	/**
	 * Basic constructor of resourceBundle (Use default language)
	 */
	private Language(){
		if(language == null && country == null){
			//Set language and country (default)
			language = Locale.getDefault().getLanguage();
			country = Locale.getDefault().getCountry();
		}
		//Create the resourceBundle with the default locale
		Locale currentLocale = new Locale(language, country);
		resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE_NAME, currentLocale);
	}
	
	/**
	 * Function for set the Language of the application
	 * @param lang Languague to set
	 * @param count Country to set
	 */
	public static ResourceBundle setLanguage(String lang, String count){
		language = lang;
		country = count;
		instanceOfLanguage = new Language();
		return resourceBundle;
	}
	
	/**
	 * Give the resourceBundle to use I18N
	 * @return The ressourceBundle to use
	 */
	public static ResourceBundle getResourceBundle(){
		if(instanceOfLanguage == null){
			instanceOfLanguage = new Language();
		}
		return resourceBundle;
	}

}
