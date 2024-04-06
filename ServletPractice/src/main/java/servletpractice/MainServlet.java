package servletpractice;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DbConnection.DatabaseConnection;

public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		String rollNumber = request.getParameter("rollNo");

		if (action != null && !action.isEmpty()) {
			if (action.equals("addData")) {
				fetchDetailsFromForm(request, response);		
				response.setContentType("text/html");
				response.getWriter().println("Registration done Successfully");
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
				dispatcher.include(request, response);

				

			} else if (action.equals("fetchData")) {
				try {
					DatabaseConnection.fetchDataFromdb(rollNumber);
				} catch (SQLException e) {

					e.printStackTrace();
				}
			} else {

				response.sendError(HttpServletResponse.SC_BAD_REQUEST, " Invalid action Parameter");
			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action Parameter missing");
		}

		// add method to fetch the details from the form
		// add method to put details in database
		// add method to fetch the details from the db
		// add method to show the input on the main servlet page through table

	}

	public void fetchDetailsFromForm(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String name = request.getParameter("name");
		String rollNo = request.getParameter("rollNo");
		String englishMarks = request.getParameter("EnglishMarks");
		String hindiMarks = request.getParameter("HindiMarks");
		String chemistryMarks = request.getParameter("ChemistryMarks");

		try {
			DatabaseConnection.insertDataIntoDB("Student_Data", name, rollNo, englishMarks, hindiMarks, chemistryMarks);
		} catch (SQLException e) {
			System.err.println("Not able to insert the data into the database ");
			e.printStackTrace();
		}

	}

}
