/**
 * Kiran D
 */
package com.kjnext.utility;

import org.apache.struts2.dispatcher.StreamResult;
import com.opensymphony.xwork2.ActionInvocation;

/** 
 * This class for result-type="myStream" 
 * 
 * <result-types> <result-type name="myStream" default="false" 
 * class="kd.DynamicStreamResult"/> 
 * 
 * </result-types> 
 * 
 * It extends StreamResult Used to download file as a stream. 
 * 
 * @author Kiran 
 * 
 */
public class DynamicStreamResult extends StreamResult{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override   
	protected void doExecute(String finalLocation, ActionInvocation invocation)   throws Exception 
	{        
		//get name of downloaded file  
		String downloadedFileName = (String)invocation.getStack().findValue(conditionalParse("name", invocation));   
		contentDisposition = "filename=\""    +downloadedFileName + "\"";   
		
		//get file size 
		contentLength = ""+ invocation.getStack().findValue(conditionalParse("size", invocation));
		
		// get type of file
		contentType = ""+ invocation.getStack().findValue(conditionalParse("description", invocation));        
		
		/*          Executes the result given a final location          
		 * 			(jsp page, action, etc) and          
		 * 			the action invocation (the state in which          
		 * 			the action was executed).        
		 */ 
		super.doExecute(finalLocation, invocation); 
	}
}
