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
	private PreparedStatement buildQuery (String query, ArrayList<String> data){
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conection.prepareStatement(query);
			for (int i=1; i< data.size();i++){
				preparedStatement.setString(i, data.get(i));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return preparedStatement;


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
	 * Also checks if the Address is already in the database, fetches its ID and Updates the user column.
	 *
	 * @param User Username the Address belongs to.
	 * @param addressData ArrayList containing the Data (country, city, street, number)
	 */
	public void addAddressData (String User, ArrayList<String> addressData) {
		ResultSet resultSet = null;
		try {
			int AID;
			PreparedStatement query1 = buildQuery("Select AdressenID from Adresse where Land = ? and Stadt = ? and Strasse = ? and Hausnummer = ?", addressData);
			PreparedStatement query2 = buildQuery("Insert into Adresse (Land, Stadt, Strasse, Hausnummer) values (?,?,?,?)", addressData);
			PreparedStatement query3 = conection.prepareStatement("Update Benutzer set Adresse=? where Benutzername=?");
			resultSet = query1.executeQuery();
			if(resultSet.next()){
				 AID = resultSet.getInt(1);
			} else {
				query2.executeUpdate();
			}
			AID = query1.executeQuery().getInt(1);
			query3.setInt(1, AID);
			query3.setString(2, User);
			query3.executeUpdate();
			query1.close();
			query2.close();
			query3.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Adds Users (and artists) to the Database
	 *
	 * @param userData an Arraylist containing the String userdata.
	 * @param kuenstler denotes whether the user should be an artist(thus getting the username added into the artists table too.)
	 */

	public void AddUsersData (ArrayList<String> userData, Boolean kuenstler){
		PreparedStatement preparedStatement = null;
		String query = "Insert into Benutzer (Benutzername,Vorname, Nachname, Passwort, E-Mail) values (?,?,?,?,?)";
		String query2 = "Insert into Kuenstler (Benutzername) values (?)";
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
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


}
