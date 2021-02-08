package com.kjnext.context;

import com.kjnext.context.implementation.CallContextImpl;

/**
 * Class which acts as a wrapper to the CallContext Service. 
 *  
 * @author: Kiran Digrase
 */
public class CallContextService implements CallContext {
	
	// Service instance
	private static CallContext callContextService = new CallContextImpl();
	
	// Singleton instance
	private static CallContextService instance = null;
	
	/**
	 * Default Constructor
	 */
	protected CallContextService() {
		
	}

	/**
	 * Actual construction mechanism for Singleton instance.
	 * 
	 * @return  Singleton instance of CallContextService class
	 */
	public static CallContextService getInstance() {

		if (instance == null) {
			instance = new CallContextService();
		}

		return instance;
	}
	
	/**
	 * @see CallContext#setContext(Context)
	 */
	public void setContext(Context keyValue) {
		callContextService.setContext(keyValue);
	}

	/**
	 * @see CallContext#getContext()
	 */
	public Context getContext() {
		return callContextService.getContext();
	}


}
