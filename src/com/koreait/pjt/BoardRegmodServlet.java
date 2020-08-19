package com.koreait.pjt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/Board/Regmod")
public class BoardRegmodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( !MyUtils.loginCheck(request, response, Const.DEFUALT_PAGE)) {
			return;
		}
		ViewResolver.forward("board/regmod", request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVO e = new BoardVO();
		UserVO v = new UserVO();
		v = (UserVO)request.getSession().getAttribute(Const.LOGIN_USER);
		e.setTitle(request.getParameter("title"));
		e.setCtnt(request.getParameter("ctnt"));
		e.setI_user(v.getI_user());
		int result = BoardDAO.insertBoard(e);
		switch( result ) {
			case 0:
				request.setAttribute("err", "등록 실패했습니다.");
				doGet(request, response);
				break;
			case 1:
				response.sendRedirect("List");		
				break;
		}
	}
}
