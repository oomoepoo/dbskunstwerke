package backend;

public class Context {
	private User nutzer = new User();
	private Adresse address = new Adresse();

	public User getNutzer() {
		return nutzer;
	}

	public void setNutzer(User nutzer) {
		this.nutzer = nutzer;
	}

	public Adresse getAddress() {
		return address;
	}

	public void setAddress(Adresse address) {
		this.address = address;
	}


}
