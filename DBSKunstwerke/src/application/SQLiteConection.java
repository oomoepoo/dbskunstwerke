package application;

import java.sql.*;

/**
 * Stuff to handle the connection to the SQLite Database
 */

public class SQLiteConection {

	/**
	 * Sets up the JDBC stuff so we can query the SQLite Database through Java.
	 * @return a {@link Connection} object if everything was successful, else null.
	 */
	public static Connection Connector() {

		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:DBSKunstwerke.sqlite");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
}
