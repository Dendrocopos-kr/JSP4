package com.koreait.pjt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ViewResolver {
	public static void forward(String name,ServletRequest request,ServletResponse response)throws ServletException,IOException{
		String jsp = String.format("/WEB-INF/jsp/%s.jsp",name);
		request.getRequestDispatcher(jsp).forward(request, response);
    }
}
