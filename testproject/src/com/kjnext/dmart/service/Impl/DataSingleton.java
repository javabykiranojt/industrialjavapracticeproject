package com.kjnext.dmart.service.Impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataSingleton {
	static private DataSingleton instance = null;
	public String sendMailToAdminAddress;
	public String AdminChangePassword;
	public String SetBody;
	public String SetSubject;
	public String SetSMTPHost;
	public String  SetFrom;
	public String SetFromName;
	public String sendMailToAdminNmame;

	
	static public DataSingleton getDataSingleton() {
		if (instance == null) {
			instance = new DataSingleton();
		}
		return instance;
	}
	Properties prop =null;
	private DataSingleton() {
		try {
			File file = new File("http://localhost:8080/DMALL1/properties/Email.properties");
			//File file =new File("Email.properties");
			file.getAbsolutePath();
			file.getCanonicalPath();
			file.getPath();
			file.getAbsolutePath();
			BufferedReader br = new BufferedReader(new FileReader(new File("Email.properties")));
			 prop = new Properties();
		//	 prop.load(br);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	Properties getProperty(){
		return prop;
	}
	public static void main(String[] args) {
		 DataSingleton.getDataSingleton();
		
		
	}
}
