package application;

import java.sql.*;
import java.util.ArrayList;
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
	 * Add an address with the given parameters to the database.
	 *
	 * @param city
	 * @param country
	 * @param number
	 * @param street
	 */
	public void addAddressData (String User, ArrayList<String> addressData) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query1 = "Insert into Adresse (Land, Stadt, Strasse, Hausnummer) values (?,?,?,?)";
		String query2 = "Select AdressenID from Adresse where Land = ? and Stadt = ? and Strasse = ? and Hausnummer = ?";
		String query3 = "Update Benutzer set Adresse=? where Benutzername=?";

	}

	/**
	 * Adds Users (and artists) to the Database
	 *
	 * @param userData an Arraylist containing the String userdata.
	 * @param kuenstler denotes whether the user should be an artist(thus getting the username added into the artists table too.)
	 * @return
	 */
	// TODO: Add Adress Stuff smh.
	public boolean AddUsersData (ArrayList<String> userData, Boolean kuenstler){
		PreparedStatement preparedStatement = null;
		String query = "Insert into Benutzer (Benutzername,Vorname, Nachname, Passwort, E-Mail) values (?,?,?,?,?)";
		String query2 = "Insert into Kuenstler (Benutzername) values (?)";
		//TODO: Refractor the sql crap into a method.
		try {
			preparedStatement = conection.prepareStatement(query);
			for(int i=1; i<6;i++){
				preparedStatement.setString(i, userData.get(i));
			}
			preparedStatement.executeUpdate();
			if(kuenstler){
				preparedStatement = conection.prepareStatement(query2);
				preparedStatement.setString(1,userData.get(1));
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
