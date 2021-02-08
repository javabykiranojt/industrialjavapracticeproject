/* 
* Copyright (C) Kjnext Technology. 2013. All rights reserved
*/
package com.kjnext.utility;

// Java Imports
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * Common class to transfer the output of the client request from action class to 
 * JSP or any user interface. 
 * 
 * @author Kiran Digrase
 */
public class ResultsBean implements Serializable {
	
	private static final long serialVersionUID = 10222426500001L;
	
	private List results = null;
	private List<String> errors = new ArrayList<String>();
	private List<String> warnings = new ArrayList<String>();
	
	/**
	 * @return the results
	 */
	public List getResults() {
		return results;
	}
	/**
	 * @param results the results to set
	 */
	public void setResults(List results) {
		this.results = results;
	}
	

	
	/**
	 * Return the list of errors.
	 * @return  array of errors
	 */
	public String[] getErrors() {
		int size = errors.size();
		String[] sems = new String[size];
		for (int counter = 0; counter < size; counter++) {
			sems[counter] = (String) errors.get(counter);
		}
		return sems;
	}
	
	/**
	 * Add multiple error messages
	 * @param	object	List of error messages to be added.
	 */
	public void setErrors(String[] errorMsgs) {

		for (int counter = 0; counter < errorMsgs.length; counter++) {
			errors.add(errorMsgs[counter]);
		}
	}
	
	/**
	 * Returns the number of errors.
	 * @return	int 
	 */
	public int errorsCount() {
		return errors.size();
	}

	/**
	 * Checks if any errors were generated during object conversion
	 * @return boolean 		true if there are errors; false otherwise
	 */
	public boolean hasErrors() {

		if (errors.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
	
	/**
	* Add a error messages
	* @param	object	Error messages to be added.
	*/
	public void setErrors(String errorMsg) {
		errors.add(errorMsg);
	}
	
	/**
	 * Returns the number of warnings.
	 * @return	int 
	 */
	public int warningsCount() {
		return warnings.size();
	}

	/**
	 * Checks if any warnings were generated during object conversion
	 * @return boolean 		true if there are warnings; false otherwise
	 */
	public boolean hasWarnings() {

		if (warnings.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}
	
	/**
	 * Return the list of warnings.
	 * @return  array of warnings
	 */
	public String[] getWarnings() {
		int size = warnings.size();
		String[] sems = new String[size];
		for (int counter = 0; counter < size; counter++) {
			sems[counter] = (String) warnings.get(counter);
		}
		return sems;
	}
	
	/**
	* Add a warning messages
	* @param	object	Warning messages to be added.
	*/
	public void setWarnings(String warningMsg) {
		warnings.add(warningMsg);
	}
	
	/**
	 * Add multiple warning messages
	 * object conversion.
	 * @param	object	List of warning messages to be added.
	 */
	public void addWarnings(String[] warningMsgs) {
		for (int counter = 0; counter < warningMsgs.length; counter++) {
			warnings.add(warningMsgs[counter]);
		}
	}


}
