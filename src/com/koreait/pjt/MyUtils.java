package com.koreait.pjt;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.vo.UserVO;

public class MyUtils {
	public static UserVO getLoginUser(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		return (UserVO) hs.getAttribute(Const.LOGIN_USER);
	}

	public static boolean isLogout(HttpServletRequest request) throws IOException {
		if (getLoginUser(request) == null) {
			return true;
		}
		return false;
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
	public static int parseStringToInt(String str) {
		return parseStringToInt(str,0);
	}

	public static int parseStringToInt(String str, int defualt_num) {
		if (str != null) {
			try {
				return Integer.parseInt(str);
			} catch (Exception e) {
				e.printStackTrace();
				return defualt_num;
			}
		} else {
			return -1;
		}
	}

	public static void setLoginUser(HttpServletRequest request, UserVO e) {
		HttpSession hs = request.getSession();
		hs.setAttribute(Const.LOGIN_USER, e);
	}
}
