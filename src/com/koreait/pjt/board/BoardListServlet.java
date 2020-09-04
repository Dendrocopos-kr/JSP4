package com.koreait.pjt.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.Const;
import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/Board/List")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserVO loginUser = MyUtils.getLoginUser(request);
		if(loginUser == null) {
			response.sendRedirect(Const.DEFUALT_PAGE);
			return;
		}
		
		BoardDomain param = new BoardDomain();
		int recordCnt = MyUtils.getIntParamater(request, "record_cnt");
		param.setRecode_cnt(recordCnt = ( recordCnt == 0 ? 10 : recordCnt));
		param.setI_user(loginUser.getI_user());
		
		String searchText = request.getParameter("searchText");
		param.setSearchText(searchText == null ? '%'+""+'%' : '%'+searchText+'%');
		int page = MyUtils.getIntParamater(request, "page") ;

		page = page==0 ? 1 : page;		

		String searchType = request.getParameter("searchType");
		param.setSearchType(searchType == null ? "1" : searchType);
		
		param.setMaxRecord(page * recordCnt);
		param.setMinRecord(param.getMaxRecord() - recordCnt);
		List<BoardDomain> list = BoardDAO.selectBoardList_Page(param);
		
		if(!"".equals(searchText)) {
			if("1".equals(searchType) || "3".equals(searchType)){
				for(BoardDomain item : list) {
					String title = item.getTitle();
					title = title.replace(searchText, "<span class=\"highlight\">"+searchText+"</span>");
					item.setTitle(title);
				}
			}else if ("4".equals(searchType)) {
				for(BoardDomain item : list) {
					String nm = item.getUser_nm();
					nm = nm.replace(searchText, "<span class=\"highlight\">"+searchText+"</span>");
					item.setUser_nm(nm);
				}		
			}
		}
		request.setAttribute("currentPage", page);
		request.setAttribute("searchType", searchType);
		request.setAttribute("paging", BoardDAO.selectPagingCnt(param));
		request.setAttribute("data", list);
		ViewResolver.forwardLoginCheck("board/list", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
