/**
 * 
 */
package com.kjnext.utility;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Class defined to load all the configurable items defined in under 
 * messageResourse.properties file.
 * 
 *@author Kiran Digrase
 *
 *
 */
public class ResourcesProperties {
	
	//private static Locale currentLocale;
	//private static ResourceBundle messages;
	//private static Map<String,String> props=null;
	//private static String value="";
	
	private ResourcesProperties() throws Exception {}
	
	public static Map getInstance(String language)  {
		
		Map<String,String> props=null;
		ResourceBundle messages = null;
		
		if (props == null ){
		synchronized (ResourcesProperties.class) {	
		Locale currentLocale = new Locale (language);
	      messages =  ResourceBundle.getBundle("MessageResources",currentLocale);
	      
	      String key="";
	      props=new HashMap<String,String>();
	      Enumeration en= messages.getKeys();
	      while(en.hasMoreElements()){
	    	  key= (String)en.nextElement();
	    	  props.put(key, messages.getString(key));
	      }
	      
		}}
		return props;
		
	}
	
public static  String getProperty(String language,String key)  {

	String value= (String)(getInstance(language).get(key));
	
		return value;
		
	}
	
	

}
