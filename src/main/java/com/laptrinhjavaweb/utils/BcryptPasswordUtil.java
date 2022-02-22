package com.laptrinhjavaweb.utils;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptPasswordUtil {
	public static String hashingPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(10));
	}
	
	public static boolean checkPassword(String password, String hashingPassword) {
		if(BCrypt.checkpw(password, hashingPassword))
			return true;
		return false;
	}
	
	
}
