package com.koreait.pjt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;
import com.sun.corba.se.spi.resolver.Resolver;

@WebServlet("/Board/Like")
public class BoardLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String src = String.format("Detail?id=%s"
				+ "&page=%d"
				+ "&record_cnt=%d"
				+ "&searchText=%s"
				+ "&searchType=%s",
				MyUtils.getIntParamater(request, "id"),
				MyUtils.getIntParamater(request, "page"),
				MyUtils.getIntParamater(request, "record_cnt"),
				request.getParameter("searchText"),
				request.getParameter("searchType"));
		response.sendRedirect(src);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("like") != null) {
			BoardVO e = new BoardVO();
			e.setI_board(MyUtils.getIntParamater(request, "id"));
			e.setI_user(MyUtils.getLoginUser(request).getI_user());
			if (request.getParameter("like").equals("1")) {
				BoardDAO.insertBoardLike(e);
			} else {
				BoardDAO.deleteBoardLike(e);
			}
		}
		doGet(request, response);
	}

}
