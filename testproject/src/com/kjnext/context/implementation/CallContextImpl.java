package com.kjnext.context.implementation;


import java.util.HashMap;

import com.kjnext.context.CallContext;
import com.kjnext.context.Context;


public class CallContextImpl implements CallContext {
	
	private static HashMap<String, Context> contextCollection = new HashMap<String, Context>();
	
	
	/**
	 * Default Constructor
	 */
	public CallContextImpl(){
	
	}
	
	/**
	 * @see CallContext#setContext(Context)
	 */
	public void setContext(Context keyValue) {
		String keyName = Thread.currentThread().getName();
		contextCollection.put(keyName, keyValue);
	}

	/**
	 * @see CallContext#getContext()
	 */
	public Context getContext() {
		String keyName = Thread.currentThread().getName();
		return (Context) contextCollection.get(keyName);
	}
	
//	/**
//	 * Returns a session. It is the resposnibility of the client to cast this correctly.
//	 * 
//	 * @return a Session
//	 */
//	public Object getSession(){
//		
//	}
//	
//	/**
//	 * Sets the session.
//	 * @param session
//	 */
//	public void setSession(Object session){
//		
//	}


}
