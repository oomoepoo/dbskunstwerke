package application;

import java.sql.*;
/**
 * Model Class for the AddUser window
 * handles adding stuff to user and address tables...
 *
 * @author jan
 *
 */
public class AddUserModel {
	Connection conection;

	public AddUserModel () {
		conection = SQLiteConection.Connector();
		if (conection == null) {
			System.out.println("Verbindung nicht erfolgreich!");
			System.exit(1);
		}
	}
	/**
	 * Checks if username is unique (i.e in the database)
	 * @param username
	 * @return true if the username wasn't found, false if it was found in the DB.
	 * @throws SQLException
	 */
	public boolean isUnique (String username) throws SQLException{
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean returnvalue = false;
		String query = "Select Benutzername from Benutzer where Benutzername = ?";
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, username);
			resultSet = preparedStatement.executeQuery();
			returnvalue = !(resultSet.next());
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return returnvalue;

	}

	/**
	 * Adds Users (and artists) to the Database
	 *
	 * @param username Username to add (must be unique in the DB)
	 * @param password Password to the Username
	 * @param email Email of the user
	 * @param vorname first name of the user
	 * @param nachname surname of the user
	 * @param kuenstler if true, adds username to the artist table too.
	 * @return true if the query was successful.
	 */
	public boolean AddUsersData (String username, String password, String email, String vorname, String nachname, Boolean kuenstler){
		PreparedStatement preparedStatement = null;
		String query = "Insert into Benutzer (Benutzername,Vorname, Nachname, Passwort, E-Mail) values (?,?,?,?,?)";
		String query2 = "Insert into Kuenstler (Benutzername) values (?)";
		try {
			preparedStatement = conection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, vorname);
			preparedStatement.setString(3, nachname);
			preparedStatement.setString(4, password);
			preparedStatement.setString(5, email);
			preparedStatement.executeUpdate();
			if(kuenstler){
				preparedStatement = conection.prepareStatement(query2);
				preparedStatement.setString(1,username);
				preparedStatement.executeUpdate();
			}
			conection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
