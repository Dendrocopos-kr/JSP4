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

@WebServlet("/Board/Detail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( !MyUtils.loginCheck(request, response, Const.DEFUALT_PAGE)) {
			return;
		}	
		BoardVO e = new BoardVO();
		e.setI_board(MyUtils.parseStringToInt(request.getParameter("id"),0));
		request.setAttribute("data", BoardDAO.selectBoard(e));
		ViewResolver.forward("board/detail", request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
