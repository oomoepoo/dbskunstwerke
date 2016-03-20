package application;

public enum Weekdays {
	MONTAG("Montag"),
	DIENSTAG("Dienstag"),
	MITTWOCH("Mittwoch"),
	DONNERSTAG("Donnerstag"),
	FREITAG("Freitag"),
	SAMSTAG("Samstag"),
	SONNTAG("Sonntag");
	private String text;
	Weekdays(String text){
		this.text = text;
	}
	public static Weekdays from_String(String text){
		if (text != null) {
			for (Weekdays w : Weekdays.values()){
				if(text.equalsIgnoreCase(w.text)){
					return w;
				}
			}
		}
		return null;
	}

}
