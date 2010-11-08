package rgbixtouri.alpha.language;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Class for the configuration of the ResourceBundle
 * Use for I18N
 *
 */
public class Language {
	
	//Object use for Create the resourceBundle
	private ResourceBundle resourceBundle;
	private Locale currentLocale;
	
	//Parameters for choose the language
	private String language;
	private String country;
	
	//Constant for the place of the properties files
	final private String PROPERTIES_FILE_NAME = "rgbixtouri.alpha.language.MessageBundle";
	
	/**
	 * Basic constructor of resourceBundle (Use default language)
	 */
	public Language(){
		//Set language and country (default)
		language = Locale.getDefault().getLanguage();
		country = Locale.getDefault().getCountry();
		//Create the resourceBundle with the default locale
		currentLocale = new Locale(language, country);
		resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE_NAME, currentLocale);
	}
	
	/**
	 * Constructor for choose the language and/or the country of resourceBundle
	 * @param lang	Use for choose the language ("" or null d'ont modify the language)
	 * @param coun	Use for choose the country (null d'ont modify the country)
	 */
	public Language(String lang, String coun){
		//Modify the language and the country with the parameters of user
		if(!lang.equals("") && lang != null){
			this.language = lang;
		}
		if(coun != null){
			this.country = coun;
		}
		
		//Create the resourceBundle with the paramaters choose by the user
		currentLocale = new Locale(language, country);
		resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE_NAME, currentLocale);
	}
	
	/**
	 * Give the resourceBundle to use I18N
	 * @return The ressourceBundle to use
	 */
	public ResourceBundle getResourceBundle(){
		return resourceBundle;
	}

}
