package application;
import application.Weekdays;

public class OpeningTime {

	private Weekdays weekday;
	private String von;
	private String bis;
	private int id;

	public OpeningTime(Weekdays Wochentag, String von, String bis, int id){
		this.setWeekday(Wochentag);
		this.von = von;
		this.bis = bis;
		this.id = id;

	}

	public String getWeekday() {
		return weekday.toString();
	}

	public void setWeekday(Weekdays weekday) {
		this.weekday = weekday;
	}
	public String getVon() {
		return von;
	}

	public void setVon(String von) {
		this.von = von;
	}

	public String getBis() {
		return bis;
	}

	public void setBis(String bis) {
		this.bis = bis;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



}
