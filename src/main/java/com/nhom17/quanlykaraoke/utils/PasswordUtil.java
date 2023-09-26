package com.nhom17.quanlykaraoke.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
	public static String encrypt(String originalPassword) {
		String hashed = BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
		return hashed;
	}

	public static boolean check(String checkPW, String hashedPW) {
		return BCrypt.checkpw(checkPW, hashedPW);
	}
}
