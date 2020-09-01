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
		BoardDomain param = new BoardDomain();
		int recordCnt = MyUtils.getIntParamater(request, "record_cnt");
		param.setRecode_cnt(recordCnt = ( recordCnt == 0 ? 10 : recordCnt));
		param.setI_user(MyUtils.getLoginUser(request).getI_user());
		param.setSearchText(request.getParameter("searchText") == null ? '%'+""+'%' : '%'+request.getParameter("searchText")+'%');
		int page = MyUtils.getIntParamater(request, "page") ;
		page = page==0 ? 1 : page;		
		
		param.setMaxRecord(page * recordCnt);
		param.setMinRecord(param.getMaxRecord() - recordCnt);
		
		request.setAttribute("currentPage", page);
		request.setAttribute("paging", BoardDAO.selectPagingCnt(param));
		request.setAttribute("data", BoardDAO.selectBoardList_Page(param));
		ViewResolver.forwardLoginCheck("board/list", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
