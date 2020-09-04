package com.koreait.pjt.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.MyUtils;
import com.koreait.pjt.ViewResolver;
import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardDomain;
import com.koreait.pjt.vo.BoardVO;

@WebServlet("/Board/Regmod")
public class BoardRegmodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get : " + MyUtils.getIntParamater(request, "id"));
		if (MyUtils.getIntParamater(request, "id") != 0) {
			BoardDomain e = new BoardDomain();
			e.setI_board(MyUtils.getIntParamater(request, "id"));
			request.setAttribute("data", BoardDAO.selectBoard(e));
		}

		ViewResolver.forwardLoginCheck("board/regmod", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("post : " + request.getParameter("id"));
		BoardVO e = new BoardVO();
		e.setTitle(request.getParameter("title"));
		e.setCtnt(MyUtils.scriptFilter(request.getParameter("ctnt")));
		e.setI_user(MyUtils.getLoginUser(request).getI_user());

		if ( MyUtils.getIntParamater(request, "id") != 0) {
			// 수정모드
			System.out.println("mod");
			e.setI_board(MyUtils.parseStringToInt(request.getParameter("id"), 0));
			BoardDAO.updateBoard(e);
			response.sendRedirect("Detail?id=" + e.getI_board());
		} else {
			// 새로쓰기
			System.out.println("write");
			int result = BoardDAO.insertBoard(e);
			switch (result) {
			case 0:
				request.setAttribute("err", "등록 실패했습니다.");
				doGet(request, response);
				break;
			case 1:
				BoardDAO.updateBoardPK(e);
				response.sendRedirect("Detail?id=" + e.getI_board());
				// response.sendRedirect("List");
				break;
			}
		}
	}
}
