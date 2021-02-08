/**
 * 
 */
package com.kjnext.utility;

import java.io.InputStream;
import java.util.Properties;

/**
 * Class defined to load all the configurable items defined in under 
 * Infrastructure.properties file.
 * 
 * @author Kiran Digrase
 *
 */
public class InfrastructureProperties {
	
	private static InfrastructureProperties instance = null;
	private static Properties properties = null;
	
	
	private InfrastructureProperties() throws Exception {
		
		 String fileName="/com/Kjnext/framework/resources/Infrastructure.properties";
		 InputStream is = null;
		 
		 try {
			 is=getClass().getResourceAsStream(fileName);
			 properties=new Properties();
			 properties.load(is);
		 }catch (Exception e) {
			 throw new Exception("Error initializing data from property file");
		 } finally {
			 if (is != null){
				 try {
					 is.close();
					 is = null;
				 }
				 catch (Exception e) {
					 
				 }
				 finally {
					 is = null;
				 }
			 }
		 }
         
		
	}
	
	public static InfrastructureProperties getInstance() throws Exception {
		
		if (instance == null ){
			synchronized (InfrastructureProperties.class) {
				if (instance == null){
					instance = new InfrastructureProperties();
				}
			}
		}
		
		return instance;
		
	}
	
	
	public String getProperty(String propertyName){
		if (properties !=null){
			return (String)properties.getProperty(propertyName);
		}
		else {
			return null;
		}
	}

}
