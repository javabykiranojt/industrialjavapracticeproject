/* 
* Copyright (C) Kjnext Technology. 2013. All rights reserved
*/
package com.kjnext.utility;

// Java Imports
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import org.junit.runner.notification.Failure;

/**
 * Common class to transfer the output of the client request from action class to 
 * JSP or any user interface. 
 * 
 * @author Kiran Digrase
 */
public class OutCome  {
	
	public enum Status{
		
		SUCCESS,
		
		SUCCESS_WITH_INFO,
		
		PARTIAL_SUCCESS,
		
		FAILURE
	}
	
	public enum FailureResponse{
		
		CLIENT_SIDE_VALIDATIONS_SKIPPED,
		
		SECURITY_VIOLATION,
		
		INVALID_OPERATION
		
	}
	
	
	public static class Message{
		
		private String fieldName=null;
		private String description=null;
		private String messageCode=null;
		private List<Object> parameters=null;
		
		private boolean paramlookUp=false;
		public Message(String messageCode, List<Object> parameters) {
			super();
			this.messageCode = messageCode;
			this.parameters = parameters;
		}
		/**
		 * @return the paramlookUp
		 */
		public boolean isParamlookUp() {
			return paramlookUp;
		}
		/**
		 * @param paramlookUp the paramlookUp to set
		 */
		public void setParamlookUp(boolean paramlookUp) {
			this.paramlookUp = paramlookUp;
		}
		public Message(String messageCode) {
			super();
			this.messageCode = messageCode;
		}
		public Message(String description, String messageCode) {
			super();
			this.description = description;
			this.messageCode = messageCode;
		}
		
		public Message(String description, String messageCode, List<Object> parameters) {
			super();
			this.description = description;
			this.messageCode = messageCode;
			this.parameters = parameters;
		}
		public Message(String fieldName, String description, String messageCode, List<Object> parameters) {
			super();
			this.fieldName = fieldName;
			this.description = description;
			this.messageCode = messageCode;
			this.parameters = parameters;
		}
		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}
		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
		/**
		 * @return the fieldName
		 */
		public String getFieldName() {
			return fieldName;
		}
		/**
		 * @param fieldName the fieldName to set
		 */
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		/**
		 * @return the messageCode
		 */
		public String getMessageCode() {
			return messageCode;
		}
		/**
		 * @param messageCode the messageCode to set
		 */
		public void setMessageCode(String messageCode) {
			this.messageCode = messageCode;
		}
		/**
		 * @return the parameters
		 */
		public List<Object> getParameters() {
			return parameters;
		}
		/**
		 * @param parameters the parameters to set
		 */
		public void setParameters(List<Object> parameters) {
			this.parameters = parameters;
		}
		
		
		public void addParameter(Object parameter){
			if(this.parameters==null) this.parameters=new ArrayList<Object>();
			this.parameters.add(parameter);
		}
	
	}
		
		private Status status=Status.SUCCESS;
		
		private List<Message> messages=new ArrayList<Message>();
		private FailureResponse failureResponse;
		/**
		 * @return the failureResponse
		 */
		public FailureResponse getFailureResponse() {
			return failureResponse;
		}
		/**
		 * @param failureResponse the failureResponse to set
		 */
		public void setFailureResponse(FailureResponse failureResponse) {
			this.failureResponse = failureResponse;
			status=Status.FAILURE;
		}
		
		public boolean isFailureResponse(){
			return (this.failureResponse!=null);
		}
		/**
		 * @return the messages
		 */
		public List<Message> getMessages() {
			return messages;
		}
		/**
		 * @param messages the messages to set
		 */
		public void setMessages(List<Message> messages) {
			this.messages = messages;
		}
		/**
		 * @return the status
		 */
		public Status getStatus() {
			return status;
		}
		/**
		 * @param status the status to set
		 */
		public void setStatus(Status status) {
			this.status = status;
		}
		
		public void addMessages(List<String> messageCodes){
			for(String messageCode:messageCodes){
				Message message=new Message(messageCode);
				this.messages.add(message);
			}
		}

		
		
		public boolean containsMessage(String messageCode){
			for(Message message:this.messages){
				if(message.getMessageCode().equalsIgnoreCase(messageCode)) return true;
			}
			return false;
		}
		
		
		public void addMessage(Message message){
			this.messages.add(message);
		}
		
		
		public void addFailureMessage(Message message){
			this.messages.add(message);
			this.setStatus(Status.FAILURE);
		}
		
		public void addFailureMessage(String messageCode){
			Message message=new Message(messageCode);
			this.messages.add(message);
			this.setStatus(Status.FAILURE);
		}
		
		public boolean isSuccess(){
			return (Status.FAILURE !=this.getStatus() && Status.PARTIAL_SUCCESS !=this.getStatus());
		}
		
		public boolean isSuccessWithInfo(){
			return (Status.SUCCESS_WITH_INFO == this.getStatus());
		}
		
		public boolean hasMessage(){
			return (this.getMessages()!=null && (!this.getMessages().isEmpty()));
		}
	}
	
	
