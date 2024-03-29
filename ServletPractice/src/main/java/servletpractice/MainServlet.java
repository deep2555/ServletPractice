package servletpractice;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		fetchDetailsFromForm(request);
		
		
		// add method to fetch the details from the form 
		// add method to put details in database 
		// add method to fetch the details from the db
		// add method to show the input on the main servlet page through table 
		
		

		
	}
	
	public void fetchDetailsFromForm(HttpServletRequest request) {
		
		String name =request.getParameter("name") ;
		String rollNo = (String) request.getAttribute("rollNo");
	}

}
