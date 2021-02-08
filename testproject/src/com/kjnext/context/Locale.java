package com.kjnext.context;

import java.io.Serializable;

/**
*  Bean version of java.util.Locale
*
* @author	Kiran Digrase
*/
public class Locale implements Serializable {
	
	private static final long serialVersionUID = 10927888873333L;

	private String language = "en";
	private String country = "US";

	public Locale() {
	}

	public Locale(String language, String country) {
		this.language = language;
		this.country = country;
	}

	/**
	 * Returns the country.
	 * @return String
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Returns the language.
	 * @return String
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the country.
	 * @param country The country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Sets the language.
	 * @param language The language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
}
