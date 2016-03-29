package application;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User {
	private StringProperty username;
	private StringProperty vorname;
	private StringProperty nachname;
	private StringProperty passwort;
	private IntegerProperty adressID;
	private Adresse adress;
	private StringProperty email;

	public User(String username, String vorname, String nachname, String passwort,
			Integer adressID, String email) {
		super();
		this.username = new SimpleStringProperty(username);
		this.vorname = new SimpleStringProperty(vorname);
		this.nachname = new SimpleStringProperty(nachname);
		this.passwort = new SimpleStringProperty(passwort);
		this.adressID = new SimpleIntegerProperty(adressID);
		this.email = new SimpleStringProperty(email);
	}

	public User(String username, String vorname, String nachname, String email){
		this.username = new SimpleStringProperty(username);
		this.vorname = new SimpleStringProperty(vorname);
		this.nachname = new SimpleStringProperty(nachname);
		this.email = new SimpleStringProperty(email);
	}
	public User() {
	}

	public String getUsername() {
		return username.get();
	}

	public void setUsername(String username) {
		this.username.set(username);
	}

	public StringProperty getusernameProperty() {
		return username;
	}

	public String getVorname() {
		return vorname.get();
	}

	public void setVorname(String vorname) {
		this.vorname.set(vorname);
	}

	public StringProperty getvornameProperty() {
		return vorname;
	}

	public String getNachname() {
		return nachname.get();
	}

	public void setNachname(StringProperty nachname) {
		this.nachname = nachname;
	}

	public StringProperty getnachnameProperty() {
		return nachname;
	}

	public String getPasswort() {
		return passwort.get();
	}

	public void setPasswort(StringProperty passwort) {
		this.passwort = passwort;
	}

	public StringProperty getpasswortProperty() {
		return passwort;
	}

	public int getAdressID() {
		return adressID.get();
	}

	public void setAdressID(IntegerProperty adressID) {
		this.adressID = adressID;
	}

	public IntegerProperty getadressIDProperty() {
		return adressID;
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}

	public StringProperty getemailProperty() {
		return email;
	}


	public Adresse getAdress() {
		return adress;
	}

	public void setAdress(Adresse adress) {
		this.adress = adress;
	}

	public ArrayList<String> getEverything(){
		ArrayList<String> everything = new ArrayList<String>();
		String[] bla = {username.get(), passwort.get(), email.get(), vorname.get(), nachname.get()};
		for(String e : bla){
			everything.add(e);
		}
		return everything;
	}



}
