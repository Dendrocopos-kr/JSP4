package com.koreait.pjt;
import java.io.IOException;
import java.security.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyUtils {
	public static boolean loginCheck(HttpServletRequest request, HttpServletResponse response,String defualt_page) throws IOException {
		HttpSession hs = request.getSession();
		if( hs.getAttribute(Const.LOGIN_USER) == null ) {
			response.sendRedirect(defualt_page);
			return false;
		}
		return true;
	}
	
	public static String encryptSHA256(String str) {

		String sha = "";

		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			sha = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			// e.printStackTrace();
			System.out.println("Encrypt Error - NoSuchAlgorithmException");
			sha = null;
		}

		return sha;
	}
	public static int parseStringToInt(String str, int defualt_num) {
		if(str != null) {
			try {
				return Integer.parseInt(str);
			} catch (Exception e) {
				e.printStackTrace();
				return defualt_num;
			}			
		}else {
			return -1;
		}
	}
}
