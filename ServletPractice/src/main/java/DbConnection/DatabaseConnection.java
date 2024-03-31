package DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

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
				ps.setObject(i+1, values[i]);
			}

			ps.executeUpdate();
			System.out.println("Data Inserted Successfully into the database");

		} catch (ClassNotFoundException e) {
			System.err.println("Error inserting data into database: " + e.getMessage());
		}

	}

}
