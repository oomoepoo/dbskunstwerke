package backend;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bestellung {
	private final IntegerProperty abdruckID;
	private final StringProperty bestelldatum;
	private final StringProperty lieferdatum;
	private final IntegerProperty preis;

	public Bestellung(Integer abdruckID, String bestelldatum,
			String lieferdatum, Integer preis) {
		super();
		this.abdruckID = new SimpleIntegerProperty(abdruckID);
		this.bestelldatum = new SimpleStringProperty(bestelldatum);
		this.lieferdatum = new SimpleStringProperty(lieferdatum);
		this.preis = new SimpleIntegerProperty(preis);
	}

	public String getBestelldatum() {
		return bestelldatum.get();
	}

	public void setBestelldatum(String bestelldatum) {
		this.bestelldatum.set(bestelldatum);
	}
	public StringProperty bestelldatumProperty() {
		return bestelldatum;
	}

	public String getLieferdatum() {
		return lieferdatum.get();
	}

	public void setLieferdatum(String lieferdatum) {
		this.lieferdatum.set(lieferdatum);
	}

	public StringProperty lieferdatumProperty() {
		return lieferdatum;
	}

	public int getAbdruckID() {
		return abdruckID.get();
	}

	public void setAbdruckID(int aid){
		abdruckID.set(aid);
	}

	public IntegerProperty abdruckIDProperty(){
		return abdruckID;
	}

	public int getPreis() {
		return preis.get();
	}

	public void setPreis(Integer preis) {
		this.preis.set(preis);
	}

	public IntegerProperty preisProperty(){
		return preis;
	}

}
