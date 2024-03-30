package DbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}
		return con;

	}

	public static void insertDataIntoDB() {
//		getConnection();

	}

}
