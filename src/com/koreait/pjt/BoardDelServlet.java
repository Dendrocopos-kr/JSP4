package com.koreait.pjt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.db.BoardDAO;
import com.koreait.pjt.vo.BoardVO;
import com.koreait.pjt.vo.UserVO;

@WebServlet("/Board/Del")
public class BoardDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("del-post:("+request.getParameter("id")+")");
		if (MyUtils.getIntParamater(request, "id") == 0) {
			response.sendRedirect("List");
		} else {
			UserVO loginUser = MyUtils.getLoginUser(request);
			if (loginUser == null) {
				response.sendRedirect(Const.DEFUALT_PAGE);
				return;
			}
			BoardVO e = new BoardVO();
			e.setI_board(MyUtils.getIntParamater(request, "id"));
			e.setI_user(loginUser.getI_user());
			int result = BoardDAO.deleteBoard(e);
			// System.out.println("삭제 결과 :"+result);
			if (result != 1) {
				ViewResolver.forwardLoginCheck("Detail?id=" + e.getI_board(), request, response);
			} else {
				response.sendRedirect("List");
			}
		}
	}

}
