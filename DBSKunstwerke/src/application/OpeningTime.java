package application;
import java.sql.Timestamp;

import application.Weekdays;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OpeningTime {

	private StringProperty weekday;
	private StringProperty von;
	private StringProperty bis;

	public OpeningTime(Weekdays Wochentag, String von, String bis){
		this.weekday = new SimpleStringProperty(Wochentag.toString());
		this.von = new SimpleStringProperty(von);
		this.bis = new SimpleStringProperty(bis);


	}

	public String getWeekday() {
		return weekday.toString();
	}

	public StringProperty getweekdayProperty(){
		return weekday;
	}

	public String getVon() {
		return von.get();
	}

	public void setVon(String von) {
		this.von.set(von);
	}

	public StringProperty getvonProperty(){
		return von;
	}

	public String getBis() {
		return bis.get();
	}

	public void setBis(String bis) {
		this.bis.set(bis);
	}

	public StringProperty getbisProperty(){
		return bis;
	}

}
