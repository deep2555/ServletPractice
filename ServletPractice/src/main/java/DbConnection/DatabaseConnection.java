package DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import servletpractice.Student;

public final class DatabaseConnection {

	/*
	 * this class is the singleton class which used to make the connection with SQL
	 * database and insert the value as per the table
	 */

	static Connection con = null;

	private DatabaseConnection() {
		/* Private Constructor */
	}

	public static Connection getConnection() throws ClassNotFoundException {

		if (con == null) {
			try {

				con = DriverManager.getConnection(ConnectionProvider.DB_URL, ConnectionProvider.DB_USERNAME,
						ConnectionProvider.DB_USERPASS);
				System.out.println("Coonected to the SQL server Database");

			} catch (SQLException e) {
				System.err.println("Failed to eastablish the connection : " + e.getMessage());
				e.printStackTrace();
			}

		}
		return con;

	}

	public static void insertDataIntoDB(String tableName, Object... values) throws SQLException {

		if (values.length == 0) {
			System.err.println("Invalid Parameters ...");
			return;
		}

		System.out.println(values.length);
		StringBuilder strBuilder = new StringBuilder("Insert Into ").append(tableName);
		StringBuilder placeholder = new StringBuilder();

		for (int i = 0; i < values.length; i++) {
			if (i > 0) {

				placeholder.append(",");
			}

			placeholder.append("?");
		}

		strBuilder.append(" Values (").append(placeholder).append(")");

		System.out.println(strBuilder.toString());

		try (Connection getconn = getConnection();

				PreparedStatement ps = getconn.prepareStatement(strBuilder.toString())) {

			for (int i = 0; i < values.length; i++) {
				ps.setObject(i + 1, values[i]);
			}

			ps.executeUpdate();
			System.out.println("Data Inserted Successfully into the database");

		} catch (ClassNotFoundException e) {
			System.err.println("Error inserting data into database: " + e.getMessage());
		}

	}

	public static List<Student> fetchDataFromdb(String rollNumber) throws SQLException {

		System.out.println("inside the fetch data from db method : " + rollNumber);
		String query = "Select * from Student_Data where rollNo = ?";
		
		List<Student> student = new ArrayList<>();
		
		

		try (Connection con = getConnection(); PreparedStatement statement = con.prepareStatement(query)) {

			statement.setString(1, rollNumber);

			ResultSet fetchData = statement.executeQuery();

			while (fetchData.next()) {
				String name = fetchData.getString("name");
				String rollNo = fetchData.getString("rollNo");
				String englishMarks = fetchData.getString("EnglishMarks");
				String hindiMarks = fetchData.getString("HindiMarks");
				String chemistryMarks = fetchData.getString("ChemistryMarks");
				
				student.add(new Student(name, rollNumber, englishMarks, hindiMarks, chemistryMarks));


				// Do something with the retrieved data
//				System.out.println("Name: " + name);
//				System.out.println("Roll Number: " + rollNo);
//				System.out.println("English Marks: " + englishMarks);
//				System.out.println("Hindi Marks: " + hindiMarks);
//				System.out.println("Chemistry Marks: " + chemistryMarks);
//				System.out.println("---------------------------");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return student;

	}

}
