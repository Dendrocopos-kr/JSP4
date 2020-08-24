package com.koreait.pjt.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserLoginHistoryVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (MyUtils.getLoginUser(request) != null) {
			response.sendRedirect("/Board/List");
			return;
		}
		ViewResolver.forward("user/login", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserVO param = new UserVO();
		param.setUser_id(request.getParameter("user_id"));
		param.setUser_pw(MyUtils.encryptSHA256(request.getParameter("user_pw")));

		String strMsg = "";
		int result = UserDAO.selectUser(param);
		switch (result) {
		case 1:// 로그인 성공
			strMsg = "로그인 성공";
			MyUtils.setLoginUser(request, param);

			//--------------------[유저 정보 기록 start]
			String agent = request.getHeader("User-Agent");
			//System.out.println("agent:" + agent);
			String ip_addr = request.getRemoteAddr();
			String brower = getClientBrowser(agent);
			String os = getClientOS(agent);
			UserLoginHistoryVO ulhVO = new UserLoginHistoryVO();
			ulhVO.setI_user(param.getI_user());
			ulhVO.setOs(os);
			ulhVO.setIp_addr(ip_addr);
			ulhVO.setBrower(brower);
			//System.out.println("OS: " + ulhVO.getOs());
			//System.out.println("ip:" + ulhVO.getIp_addr());
			//System.out.println("brower:" + ulhVO.getBrower());
			//System.out.println(UserDAO.insUserLoginHistory(ulhVO));
			UserDAO.insUserLoginHistory(ulhVO);
			//-----------------------[유저 정보 기록 end]
			
			response.sendRedirect("/Board/List");
			return;
		case 2:// 비번틀림
			strMsg = "비밀번호를 확인해주세요.";
			break;
		case 3:// 아이디 없음
			strMsg = "아이디를 확인해주세요";
			break;
		default:
			strMsg = "애러발생";
			break;
		}
		request.setAttribute("Msg", strMsg);
		request.setAttribute("id", param.getUser_id());
		param = null;
		doGet(request, response);
	}

	public static String getClientOS(String userAgent) {

		String os = "";

		userAgent = userAgent.toLowerCase();

		if (userAgent.indexOf("windows nt 10.0") > -1) {
			os = "Windows10";
		}else if (userAgent.indexOf("windows nt 6.1") > -1) {
			os = "Windows7";
		} else if (userAgent.indexOf("windows nt 6.2") > -1 || userAgent.indexOf("windows nt 6.3") > -1) {
			os = "Windows8";
		} else if (userAgent.indexOf("windows nt 6.0") > -1) {
			os = "WindowsVista";
		} else if (userAgent.indexOf("windows nt 5.1") > -1) {
			os = "WindowsXP";
		} else if (userAgent.indexOf("windows nt 5.0") > -1) {
			os = "Windows2000";
		} else if (userAgent.indexOf("windows nt 4.0") > -1) {
			os = "WindowsNT";
		} else if (userAgent.indexOf("windows 98") > -1) {
			os = "Windows98";
		} else if (userAgent.indexOf("windows 95") > -1) {
			os = "Windows95";
		}
		// window 외
		else if (userAgent.indexOf("iphone") > -1) {
			os = "iPhone";
		} else if (userAgent.indexOf("ipad") > -1) {
			os = "iPad";
		} else if (userAgent.indexOf("android") > -1) {
			os = "android";
		} else if (userAgent.indexOf("mac") > -1) {
			os = "mac";
		} else if (userAgent.indexOf("linux") > -1) {
			os = "Linux";
		} else {
			os = userAgent;
		}

		return os;
	}

	public static String getClientBrowser(String userAgent) {
		String browser = "";

		if (userAgent.indexOf("Trident/7.0") > -1) {
			browser = "ie11";
		} else if (userAgent.indexOf("MSIE 10") > -1) {
			browser = "ie10";
		} else if (userAgent.indexOf("MSIE 9") > -1) {
			browser = "ie9";
		} else if (userAgent.indexOf("MSIE 8") > -1) {
			browser = "ie8";
		} else if (userAgent.indexOf("Chrome/") > -1) {
			browser = "Chrome";
		} else if (userAgent.indexOf("Chrome/") == -1 && userAgent.indexOf("Safari/") >= -1) {
			browser = "Safari";
		} else if (userAgent.indexOf("Firefox/") >= -1) {
			browser = "Firefox";
		} else {
			browser = userAgent;
		}
		return browser;
	}
}
