package com.koreait.pjt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewResolver {
	public static void forward(String name, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String jsp = String.format("/WEB-INF/jsp/%s.jsp", name);
		request.getRequestDispatcher(jsp).forward(request, response);
	}

	public static void forwardLoginCheck(String name, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (MyUtils.isLogout(request)) {
			response.sendRedirect(Const.DEFUALT_PAGE);
			return;
		}
		forward(name, request, response);
	}
}
