package application;

import java.util.ArrayList;
import java.util.Iterator;

public class Collection {

	private final Integer sammlungID;
	private final String name;
	private ArrayList<Integer> Kunstwerke;



	public Collection(Integer sammlungID, String name, ArrayList<Integer> kunstwerke) {
		super();
		this.sammlungID = sammlungID;
		this.name = name;
		Kunstwerke = kunstwerke;
	}
	public Collection(Integer sammlungID, String name){
		super();
		this.sammlungID = sammlungID;
		this.name = name;
		Kunstwerke = null;
	}

	public Iterator<Integer> getKunstwerke() {
		return Kunstwerke.iterator();
	}
	public void setKunstwerke(Integer[] kunstwerke) {
		for (Integer k : kunstwerke){
			this.Kunstwerke.add(k);
		}
	}
	public Integer getSammlungID() {
		return sammlungID;
	}
	public String getName() {
		return name;
	}

}
