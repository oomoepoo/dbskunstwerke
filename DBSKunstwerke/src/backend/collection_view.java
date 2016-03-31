package backend;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class collection_view extends Collection {
	private StringProperty von;
	private StringProperty bis;

	public collection_view(Integer sammlungID, String name, String von, String bis) {
		super(sammlungID, name);
		this.von = new SimpleStringProperty(von);
		this.bis = new SimpleStringProperty(bis);
	}

	public String getVon() {
		return von.get();
	}

	public void setVon(String von) {
		this.von.set(von);
	}
	public StringProperty vonProperty(){
		return von;
	}

	public String getBis() {
		return bis.get();
	}

	public void setBis(SimpleStringProperty bis) {
		this.bis = bis;
	}

	public StringProperty bisProperty(){
		return bis;
	}


}
