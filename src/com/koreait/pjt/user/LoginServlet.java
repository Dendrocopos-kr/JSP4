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
import com.koreait.pjt.vo.UserVO;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
			HttpSession hs = request.getSession();
			param.setIpAddr(request.getRemoteAddr());
			hs.setAttribute(Const.LOGIN_USER, param);
			response.sendRedirect("Board/List");
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
		request.setAttribute("Msg",strMsg);
		request.setAttribute("id", param.getUser_id());
		param = null;
		doGet(request, response);
	}

}
