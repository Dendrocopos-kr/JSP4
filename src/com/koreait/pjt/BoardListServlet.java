package com.koreait.pjt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;

@WebServlet("/Board/List")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//HttpSession hs = request.getSession();
		//request.setAttribute("user", hs.getAttribute(Const.LOGIN_USER));
		
		int page = MyUtils.getIntParamater(request, "page") ;
		page = page==0 ? 1 : page;
		BoardDomain param = new BoardDomain();
		param.setRecode_cnt(Const.RECODE_CNT);
		
		request.setAttribute("currentPage", page);
		request.setAttribute("paging", BoardDAO.selectPagingCnt(param));
		request.setAttribute("data", BoardDAO.selectBoardList_Page(page, Const.RECODE_CNT));
		//request.setAttribute("data", BoardDAO.selectBoardList());
		ViewResolver.forwardLoginCheck("board/list", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
