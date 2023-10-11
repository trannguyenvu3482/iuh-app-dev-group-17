package com.nhom17.quanlykaraoke.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author  Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 10-Oct-2023 15:20:00
 */
public class PasswordUtil {
	public static String encrypt(String originalPassword) {
		return BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
	}

	public static boolean check(String checkPW, String hashedPW) {
		return BCrypt.checkpw(checkPW, hashedPW);
	}
}
