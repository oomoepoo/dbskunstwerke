package application;

import javafx.beans.property.SimpleStringProperty;

public class collection_view extends Collection {
	private SimpleStringProperty von = new SimpleStringProperty();
	private SimpleStringProperty bis = new SimpleStringProperty();

	public collection_view(Integer sammlungID, String name, String von, String bis) {
		super(sammlungID, name);
		this.von = new SimpleStringProperty(von);
		this.bis = new SimpleStringProperty(bis);
		// TODO Auto-generated constructor stub
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

	public void setBis(SimpleStringProperty bis) {
		this.bis = bis;
	}


}
