package com.deepanshu.servletPracticeInfo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		 RequestDispatcher requestDispatcher =request.getRequestDispatcher("addStudent.html");
//		 requestDispatcher.include(request, response);
		 
		 
		response.sendRedirect("addStudent.html");
		
	}

}
