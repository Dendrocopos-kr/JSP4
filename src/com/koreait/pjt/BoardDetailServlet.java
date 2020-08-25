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

		BoardVO param = new BoardVO();
		param.setI_board(MyUtils.parseStringToInt(request.getParameter("id"), 0));
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

		request.setAttribute("data", f);

		ViewResolver.forwardLoginCheck("board/detail", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getParameter("like") != null) {
			BoardDomain e = new BoardDomain();
			e.setI_board(MyUtils.parseStringToInt(request.getParameter("id"), 0));
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
