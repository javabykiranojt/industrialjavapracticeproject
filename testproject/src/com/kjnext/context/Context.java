package com.kjnext.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Context encapsulates environment data relating to a call, 
 * e.g. locale, user and application defined environment entries.
 * 
 * @author		Kiran Digrase    
 */
public class Context implements Serializable {
	
	private static final long serialVersionUID = 109278426473237L;
	
	private Locale locale = new Locale();
	private String user = null;
	private Environment environment [] = null;
	private Long callId = null;
	private String applicationId=null;
	private String sessionId=null;

	/**
	 * Default constructor
	 */
	public Context() {
		super();
	}

	/**
	 * Returns an array of application defined environment entries.	  
	 * @return Environment []
	 */
	public Environment [] getEnvironment() {
		return environment;
	}


	/**
	 * Returns the application defined environment entry indicated by the given key,
	 * or null if no object is found.	 
	 * 
	 * @param     key environment entry key
	 * @return    String
	 */
	public Object getAttribute(String key) {
		List<String> tempArray = new ArrayList<String>();
		
		if(environment != null) { 
			for (int i = 0; i < environment.length; i++ ) {
				tempArray.add(environment[i].getKey());		
			}
			
			int index = tempArray.indexOf(key);

			if(index != -1) {
				return (Object)environment[index].getValue();
			}
			else
				return null;
		} else 
			return null;
	}

	/**
	 * Returns the locale.	 
	 * @return Locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	* Returns the userId
	* @return String
	*/
	public String getUser() {
		return user;
	}

	/**
	 * Sets the array of application defined environment entries.	
	 * @param newEnvironment new environment entries
	 */
	public void setEnvironment(Environment[] newEnvironment) {
		environment = newEnvironment;
	}

	/**
	 * Add an environment entry 	 
	 * @param    key	environment entry key
	 * @param    value	environment entry value
	 */
	public void setAttribute(String key, Object value) {
		
		if(environment != null) {

			// Check if key already exists
			for (int i = 0; i < environment.length; i++) {
				if (environment[i].getKey().equalsIgnoreCase(key)) {
					environment[i].setValue(value);
					return;
				}
			}
			
			// Else add key-value to ecisting environment		
			int lastIndex = environment.length;
			//boolean replace = false;

			Environment newEnv [] = new Environment[lastIndex + 1];
			for (int i = 0; i < environment.length; i++) {
				newEnv[i] = environment[i];
			}
			
			Environment envEntry = new Environment();
			envEntry.setKey(key);
			envEntry.setValue(value);

			newEnv[lastIndex] = envEntry;
			
			environment = newEnv;			
			
			
		} else {
			
			// Create new environment and add key-value pair.
			environment = new Environment[1];

			Environment envEntry = new Environment();
			envEntry.setKey(key);
			envEntry.setValue(value);

			environment[0] = envEntry;
		}
	}

	/**
	 * Sets the locale 
	 * @param newLocale locale
	 */
	public void setLocale(Locale newLocale) {
		locale = newLocale;
	}

	/**
	 * Sets the userId	  
	 * @param newUser userId
	 */
	public void setUser(String newUser) {
		user = newUser;
	}

	/**
	 * Provides debugging info.	 
	 * @return String
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append("\tLocale:");
		sb.append(getLocale());
		sb.append(", ");
		sb.append("\n");
		sb.append("\tUser:");
		sb.append(getUser());
		sb.append("\n");
		sb.append("application Id: ");
		sb.append(getApplicationId());
		sb.append("\n");
		sb.append("session Id: ");
		sb.append(getSessionId());
		sb.append(", ");
		sb.append("\n");
		sb.append("\tKeys:");

		if (environment.length != 0) {
			for(int i = 0; i < environment.length; i++ ) {
				sb.append(environment[i].toString());						
			}
		}

		return sb.toString();
	}

	/**
	 * Gets the callId - a unique server-side generated call identifier
	 * @return Long
	 */
	public Long getCallId() {
		return callId;
	}
	/**
	 * Sets the callId - a unique server-side generated call identifier
	 * @param callId The callId to set
	 */
	public void setCallId(Long callId) {
		this.callId = callId;
	}
	/**
	 * Returns the applicationId.
	 * @return String
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * Returns the sessionId.
	 * @return String
	 */
	public String getSessionId() {
		return sessionId;
	}

	/**
	 * Sets the applicationId.
	 * @param applicationId The applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * Sets the sessionId.
	 * @param sessionId The sessionId to set
	 */
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
