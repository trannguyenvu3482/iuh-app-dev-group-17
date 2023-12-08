package com.nhom17.quanlykaraoke.utils;

import java.util.HashMap;
import java.util.Random;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 11-Oct-2023 21:31:00
 */
public class OTPUtil {
	public static String accountSID = "ACXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	public static String authToken = "your_auth_token";
	private static HashMap<String, String> OTPMap = new HashMap<>();

	static {
		Dotenv dotenv = Dotenv.configure().directory(".").load();

		accountSID = dotenv.get("TWILIO_ACCOUNT_SID");
		authToken = dotenv.get("TWILIO_AUTH_TOKEN");

		OTPMap = new HashMap<>();
	}

	public static String createRandomOTP() {
		Random random = new Random();
		return String.format("%06d", random.nextInt(1000000));
	}

	public static void addToOTPMap(String phoneNumber, String otp) {
		if (OTPMap.containsKey(phoneNumber)) {
			OTPMap.put(phoneNumber, otp);
			return;
		}

		OTPMap.put(phoneNumber, otp);
	}

	public static String sendSMS(String toPhoneNumber) {
		// Create OTP
		String otp = createRandomOTP();
		addToOTPMap(toPhoneNumber.trim(), otp);

		// Send SMS
		try {
			Twilio.init(accountSID, authToken);
			String messageBody = "OTP để reset mật khẩu của bạn là: " + otp;
			Message message = Message.creator(new com.twilio.type.PhoneNumber(toPhoneNumber),
					new com.twilio.type.PhoneNumber("+13137513492"), messageBody).create();

			// TODO: Delete this after done
			System.out.println(message.getSid());
			System.out.println("OTP: " + otp);
			System.out.println("Gửi OTP thành công");

			return otp;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean checkOTP(String phoneNumber, String otp) {
		System.out.println("OTP MAP: " + OTPMap.toString());

		if (!OTPMap.containsKey(phoneNumber.trim())) {
			// OTP not found for number
			return false;
		}

		String storedOtp = OTPMap.get(phoneNumber);

		System.out.println("Correct OTP:" + storedOtp);

		if (storedOtp == null) {
			// OTP is null
			return false;
		}

		if (storedOtp.equals(otp)) {
			// OTP matched
			OTPMap.remove(phoneNumber);
			return true;
		}

		return false;
	}
}
