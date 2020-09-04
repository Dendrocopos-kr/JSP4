package com.koreait.pjt.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/Member_confirm")
public class Member_confirm_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (MyUtils.getLoginUser(request) == null) {
			response.sendRedirect(Const.DEFUALT_PAGE);
			return;
		}

		ViewResolver.forwardLoginCheck("user/member_confirm", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pw = request.getParameter("pw");
		String encryptPw = MyUtils.encryptSHA256(pw);
		UserVO loginUser = MyUtils.getLoginUser(request);
		UserVO param = new UserVO();

		param.setUser_id(loginUser.getUser_id());
		param.setUser_pw(encryptPw);

		int result = UserDAO.selectUser(param);

		if (result == 1) {
			MyUtils.setSesstionItem("member_confirm", request, true);
			response.sendRedirect("/Profile");
			return;
		} else {
			request.setAttribute("msg", "비밀번호를 확인해 주세요");
		}
		doGet(request, response);

	}

}
