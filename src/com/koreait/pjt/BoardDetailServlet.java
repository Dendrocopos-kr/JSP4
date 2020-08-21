package com.koreait.pjt;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/Board/Detail")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserVO loginUser = MyUtils.getLoginUser(request);
		if (loginUser == null) {
			response.sendRedirect(Const.DEFUALT_PAGE);
			return;
		}

		BoardVO e = new BoardDomain();
		e.setI_board(MyUtils.parseStringToInt(request.getParameter("id"), 0));
		e = BoardDAO.selectBoard(e);
		request.setAttribute("data", e);

		ServletContext application = getServletContext();
		Integer readI_User = (Integer) application.getAttribute("read_" + e.getI_board());
		if (readI_User == null || readI_User != loginUser.getI_user()) {
			if (MyUtils.getLoginUser(request) != null && MyUtils.getLoginUser(request).getI_user() != e.getI_user()) {
				BoardDAO.addHits(e);
			}
			application.setAttribute("read_" + e.getI_board(), loginUser.getI_user());
		}

		ViewResolver.forwardLoginCheck("board/detail", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
