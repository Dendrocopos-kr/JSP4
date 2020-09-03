package com.koreait.pjt;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardCmtVO;
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

		String searchText = (request.getParameter("searchText") == null ? "" : request.getParameter("searchText"));
		String searchType = (request.getParameter("searchType") == null ? "a" : request.getParameter("searchType"));

		BoardDomain param = new BoardDomain();
		param.setI_board(MyUtils.getIntParamater(request, "id"));
		param.setI_user(MyUtils.getLoginUser(request).getI_user());
		BoardDomain f = BoardDAO.selectBoard(param);
		// -----------------------[중복 조회수 금지 + 자기자신글 조회수 업데이트 금지 start]
		ServletContext application = getServletContext();
		Integer readI_User = (Integer) application.getAttribute("read_" + f.getI_board());
		if (readI_User == null || readI_User != loginUser.getI_user()) {
			if (MyUtils.getLoginUser(request) != null && MyUtils.getLoginUser(request).getI_user() != f.getI_user()) {
				BoardDAO.addHits(f);
			}
			application.setAttribute("read_" + f.getI_board(), loginUser.getI_user());
		}
		// -----------------------[중복 조회수 금지 + 자기자신글 조회수 업데이트 금지 end]

		// ----------------[get방식 start]
		/*
		 * if (request.getParameter("like") != null) { if
		 * (request.getParameter("like").equals("1")) { BoardDAO.insertBoardLike(param);
		 * } else { BoardDAO.deleteBoardLike(param); } }
		 */
		// ----------------[get방식 end]
		/*
		if (!"".equals(searchText)) {
			if ("1".equals(searchType)) {
				String title = f.getTitle();
				title = title.replaceAll(searchText, "<span class=\"highlight\">" + searchText + "</span>");
				f.setTitle(title);
			} else if ("2".equals(searchType)) {
				String ctnt = f.getCtnt();
				ctnt = ctnt.replaceAll(searchText, "<span class=\"highlight\">" + searchText + "</span>");
				f.setCtnt(ctnt);
			} else if ("3".equals(searchType)) {
				String ctnt = f.getCtnt();
				String title = f.getTitle();
				ctnt = ctnt.replaceAll(searchText, "<span class=\"highlight\">" + searchText + "</span>");
				title = title.replaceAll(searchText, "<span class=\"highlight\">" + searchText + "</span>");
				f.setCtnt(ctnt);
				f.setTitle(title);
			} else if ("4".equals(searchType)) {
				String nm = f.getUser_nm();
				nm = nm.replace(searchText, "<span class=\"highlight\">" + searchText + "</span>");
				f.setUser_nm(nm);
			}
		}
		*/
		request.setAttribute("likeList", BoardDAO.selectBoardLiskList(param));
		request.setAttribute("data", f);
		request.setAttribute("cmtData", BoardCmtDAO.selectBoardCmtList(param));
		ViewResolver.forwardLoginCheck("board/detail", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// -----------------[post방식 start]
		/*
		 * if (request.getParameter("like") != null) { BoardDomain e = new
		 * BoardDomain();
		 * e.setI_board(MyUtils.parseStringToInt(request.getParameter("id"), 0));
		 * e.setI_user(MyUtils.getLoginUser(request).getI_user()); if
		 * (request.getParameter("like").equals("1")) { BoardDAO.insertBoardLike(e); }
		 * else { BoardDAO.deleteBoardLike(e); } }
		 */
		// -----------------[post방식 end]
		doGet(request, response);
	}

}
