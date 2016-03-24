package application;

import java.sql.*;

/**
 * Handles Login Stuff
 */

public class LogInModel {
	Connection conection;

	/**
	 * Constructs a new LogInModel, connects to the Database.
	 */

	public LogInModel () {
		conection = SQLiteConection.Connector();
		if (conection == null) {
			System.out.println("Verbindung nicht erfolgreich!");
			System.exit(1);
		}
	}

	/**
	 * Used to check the status of the connection to the Database.
	 *
	 * @return true if the connection is successful
	 */

	public boolean isConnected() {
		try {
			return !conection.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Checks if a given username and password combination is a valid login.
	 * Connects to the Database and queries it about the given user/password combination.
	 * If an exception occurs, the database or the connection are wrong/borked :v
	 *
	 * @param user Username that should be checked. (String)
	 * @param pass Password that should be checked together with user
	 * @return true if the given combination is valid. false if anything else is the case-
	 * @throws SQLException
	 */

	public boolean isLogIn (String user, String pass) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Benutzer where Benutzername = ? and Passwort = ?";

		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()){
				return true;
			}
			else {
				return false;
			}

		} catch (Exception e){
			System.out.println("Fehler bei Login!");
			System.out.println(e);
			return false;

		} finally {
			preparedStatement.close();
			resultSet.close();
		}
	}

	public User getUserfromsql(String user, String pass) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "select * from Benutzer where Benutzername = ? and Passwort = ?";
		User u_object = null;
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, pass);

			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				String vorname = resultSet.getString("Vorname");
				String nachname = resultSet.getString("Nachname");
				int adressID = resultSet.getInt("Adresse");
				String email = resultSet.getString("E-Mail");
				u_object = new User(user, vorname, nachname, pass, adressID, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u_object;
	}

}
