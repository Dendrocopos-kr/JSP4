package com.koreait.pjt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.pjt.db.BoardCmtDAO;
import com.koreait.pjt.vo.BoardCmtVO;

@WebServlet("/Board/cmt")
public class BoardCmtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 댓글(삭제)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(MyUtils.getIntParamater(request, "cmt_id") != 0 ) {
			BoardCmtVO param = new BoardCmtVO();
			param.setI_user(MyUtils.getLoginUser(request).getI_user());
			param.setI_cmt(MyUtils.getIntParamater(request, "cmt_id"));
			BoardCmtDAO.deleteCmt(param);
		}
		
		response.sendRedirect("Detail?id="+ MyUtils.getIntParamater(request, "id"));
	}

	// (등록/수정)
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		switch (MyUtils.getIntParamater(request, "i_cmt")) {
		case 0:// 등록
			BoardCmtVO param = new BoardCmtVO();
			param.setI_user(MyUtils.getLoginUser(request).getI_user());
			param.setI_board(MyUtils.getIntParamater(request, "id"));
			param.setCmt(request.getParameter("cmt"));
			BoardCmtDAO.insertCmt(param);
			break;
		default:// 수정
			break;
		}
		doGet(request, response);
	}

}
