package servletpractice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbConnection.DatabaseConnection;


public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		fetchDetailsFromForm(request, response);
		
		//DatabaseConnection.insertDataIntoDB();
				Connection c = null;
				try {
					c = DatabaseConnection.getConnection();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PrintWriter pw = response.getWriter();
				pw.println(c);
		
		
		// add method to fetch the details from the form 
		// add method to put details in database 
		// add method to fetch the details from the db
		// add method to show the input on the main servlet page through table 

		
	}
	
	public void fetchDetailsFromForm(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String name =request.getParameter("name") ;
		String rollNo = (String) request.getAttribute("rollNo");
		String englishMarks = (String)request.getAttribute("EnglishMarks");
		String hindiMarks = (String)request.getAttribute("HindiMarks");
		String chemistryMarks = (String)request.getAttribute("ChemistryMarks");
		
		
		
		
		
		
	}

}
