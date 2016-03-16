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
			for (int i=0; i< data.size();i++){
				preparedStatement.setString(i+1, data.get(i));
			}
		} catch (SQLException e) {
			System.out.println(e);
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
		ResultSet results = null;
		int AID = 0;
		try {
			String s_query1 = "Select AdressenID from Adresse where Land = ? and Stadt = ? and Strasse = ? and Hausnummer = ?";
			PreparedStatement query1 = conection.prepareStatement(s_query1);
			for (int i=0; i< addressData.size();i++){
				query1.setString(i+1, addressData.get(i));
			}
			results = query1.executeQuery();
			//check if Address already exists in the DB.
			//If it isn't: Insert it.
			if (!(results.next())){
				String s_query2 = "Insert into Adresse (Land, Stadt, Strasse, Hausnummer) values (?,?,?,?)";
				PreparedStatement query2=conection.prepareStatement(s_query2);
				for(int i = 0; i < addressData.size();i++){
					query2.setString(i+1, addressData.get(i));
				}
				query2.executeUpdate();
				//I can't think of anything better, so we execute the first query again, to get the AddressID.
				ResultSet results2 = query1.executeQuery();
				while(results2.next()){
					AID = results2.getInt("AdressenID");
				}

			} else {
				//if the Address already is in the DB, get the AID.
				AID = results.getInt("AdressenID");
			}

			//Update the user with the new Address.
			PreparedStatement query3 = conection.prepareStatement("Update Benutzer set Adresse = ? where Benutzername = ?");
			query3.setInt(1, AID);
			query3.setString(2, User);
			query3.executeUpdate();
			query3.close();
		} catch (SQLException e) {
			//TODO: think about a proper exception thingy.
			e.printStackTrace();
		}
	}

	/**
	 * Adds Users (and artists) to the Database
	 *
	 * @param userData an Arraylist containing the String userdata.
	 * @param kuenstler denotes whether the user should be an artist(thus getting the username added into the artists table too.)
	 */

	public void AddUsersData (ArrayList<String> userData, Boolean kuenstler) {
		PreparedStatement prepStatement = null;
		PreparedStatement u_query = buildQuery("Insert into Benutzer (Benutzername, Passwort, `E-Mail`, Vorname, Nachname) values (?,?,?,?,?)", userData);
		String u_query2 = "Insert into Kuenstler (Benutzername) values (?)";
		try {
			u_query.executeUpdate();
		} catch (SQLException e) {
			//TODO: think about a proper exception thingy.
			System.out.println("Fehler bei Nutzereintragung!");
			e.printStackTrace();
		}

		if(kuenstler){
			try {
				prepStatement = conection.prepareStatement(u_query2);
				prepStatement.setString(1, userData.get(1));
				prepStatement.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Fehler bei Kuenstlereintrag!");
				e.printStackTrace();
			}
		}

	}


}
