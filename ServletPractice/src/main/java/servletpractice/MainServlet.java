package servletpractice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

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
		List<Student> studentDetails = null;

		if (action != null && !action.isEmpty()) {
			if (action.equals("addData")) {
				fetchDetailsFromForm(request, response);
				response.setContentType("text/html");
				response.getWriter().println("Registration done Successfully");
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
				dispatcher.include(request, response);

			} else if (action.equals("fetchData")) {
				try {
					studentDetails = DatabaseConnection.fetchDataFromdb(rollNumber);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				getDynamicTable(request, response, studentDetails);
			} else {

				response.sendError(HttpServletResponse.SC_BAD_REQUEST, " Invalid action Parameter");
			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action Parameter missing");
		}

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

	public void getDynamicTable(HttpServletRequest request, HttpServletResponse response,
			List<Student> studentDetails) {

		response.setContentType("text/html");

		try {
			PrintWriter pw = response.getWriter();
			pw.println("<!DOCTYPE html>");
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<meta charset=\"UTF-8\">");
			pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
			pw.println("<title>Student Data</title>");
			pw.println("<style>");
			pw.println("table {");
			pw.println("  width: 100%;");
			pw.println("  border-collapse: collapse;");
			pw.println("}");
			pw.println("th, td {");
			pw.println("  padding: 8px;");
			pw.println("  text-align: left;");
			pw.println("  border-bottom: 1px solid #ddd;");
			pw.println("}");
			pw.println("</style>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<h1>Student Data</h1>");
			pw.println("<table>");
			pw.println("<thead>");
			pw.println("<tr>");
			pw.println("<th>Student Name</th>");
			pw.println("<th>Student Roll No</th>");
			pw.println("<th>English Marks</th>");
			pw.println("<th>Hindi Marks</th>");
			pw.println("<th>Chemistry Marks</th>");
			pw.println("</tr>");
			pw.println("</thead>");
			pw.println("<tbody>");

			// fetch the detail from the database
//			DatabaseConnection.fetchDataFromdb(rollNumber);
			// add dynamic detail
			for (Student st : studentDetails) {
				pw.println("<tr>");
				pw.println("<td>" + st.getStudentName() + "</td>");
				pw.println("<td>" + st.getStudentRollNumber() + "</td>");
				pw.println("<td>" + st.getEnglishMarks() + "</td>");
				pw.println("<td>" + st.getHindiMarks() + "</td>");
				pw.println("<td>" + st.getChemistryMarks() + "</td>");
				pw.println("</tr>");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
