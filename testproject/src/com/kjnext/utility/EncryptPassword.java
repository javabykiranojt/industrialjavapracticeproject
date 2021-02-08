package com.kjnext.utility;

import org.apache.catalina.realm.RealmBase;

public class EncryptPassword {

	public static String getEncryptedPassword(String credentials) {
		String encryptPassword = RealmBase.Digest(credentials, "MD5", "UTF-8");
		System.out.println(credentials + " -> " + encryptPassword);
		return encryptPassword;
	}

	public static void main(String[] args) {
		getEncryptedPassword("kiran");
	}
}
