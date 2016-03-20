package application;
import application.Weekdays;
import javafx.beans.property.SimpleStringProperty;

public class OpeningTime {

	private SimpleStringProperty weekday;
	private SimpleStringProperty von;
	private SimpleStringProperty bis;

	public OpeningTime(Weekdays Wochentag, String von, String bis){
		this.weekday = new SimpleStringProperty(Wochentag.toString());
		this.von = new SimpleStringProperty(von);
		this.bis = new SimpleStringProperty(bis);


	}

	public String getWeekday() {
		return weekday.toString();
	}

	public String getVon() {
		return von.get();
	}

	public void setVon(String von) {
		this.von.set(von);
	}

	public String getBis() {
		return bis.get();
	}

	public void setBis(String bis) {
		this.bis.set(bis);
	}

}
