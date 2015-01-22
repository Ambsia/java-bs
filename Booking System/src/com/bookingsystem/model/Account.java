package com.bookingsystem.model;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.digest.*;
public class Account {

	private int userID;
	private String userLogonName;
	private String hashedPassword;
	private int userLevel;
	private String userSalt;

	public static String validationMsg = "";

	public Account(int userID, int userLevel, String username,
			String hashedPassword) {
		
		try {
			userSalt = GenerateSalt();	
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		this.userID = userID;
		this.userLevel = userLevel;
		this.userLogonName = username;
		this.hashedPassword = SHA1_HASH(hashedPassword);
		
		
		if (!Validation()) {

		} else {
			
			
			Login();
		}
	}

	public boolean Validation() {
		return !this.userLogonName.isEmpty() || !this.hashedPassword.isEmpty();
	}

	public String GetUsername() {
		return this.userLogonName;
	}

	public String GetHashedPassword() {
		return this.hashedPassword;
	}

	public void CreateAccount() {

	}
	
	public void Login() {
		
	}

	public String SHA1_HASH(String unHashedString) {
		
		String hashedString = DigestUtils.sha1Hex(unHashedString + userSalt);
	
		return hashedString;
	}

	public String GenerateSalt() throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		int salt = random.nextInt(random.nextInt(1000000) + (Integer.MAX_VALUE - 1000000));	

		
		return new String(Integer.toString(salt));
	} 

	public Account ReturnAccount() {

		return null;
	}

	@Override
	public String toString() {
		return "UserID: " + "\t" + userID + "\n" + "Username:" + "\t"
				+ userLogonName + "\n" + "Password: " + "\t" + hashedPassword
				+ "\n" + "User Level: " + "\t" + userLevel + "\n" + "Salt: "
				+ userSalt;
	}

}
