package com.kjnext.context;

import java.io.Serializable;


/**
 * Environment is a key-value pair that represents an item of environment information,
 * 
 * @author     	Kiran Digrase
 * 
 */
public class Environment implements Serializable {
	
	private static final long serialVersionUID = 10555426474444L;

	private String key = null;
	private Object value = null;
	
	/**
	 * Constructor for Environment.
	 */
	public Environment() {
		super();
	}
	
	/**
	 * Returns the key.
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Returns the value.
	 * @return String
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * Sets the key.
	 * @param key The key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Sets the value.
	 * @param value The value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new String("Key = " + key + "; Value = " + value);
	}

}
