package com.koreait.pjt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.db.UserDAO;
import com.koreait.pjt.vo.BoardVO;

@WebServlet("/BoardList")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession hs = request.getSession();
		
		if( hs.getAttribute(Const.LOGIN_USER) == null ) {
			response.sendRedirect("Login");
			return;
		}
		List<BoardVO> e = (List<BoardVO>) UserDAO.selectBoardList();
		request.setAttribute("data", e);
		ViewResolver.forward("board/list", request, response);			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
