package model;

public class Medewerker implements User {
	private int ID;
	private String wachtwoord;
	private String voornaam;
	private String achternaam;
	private String postcode;
	private int huisnummer;
	private String toevoeging;
	private String geboortedatum;
	
	
	
	public Medewerker(int iD, String wachtwoord, String voornaam, String achternaam, String woonplaats, String postcode2, int huisnummer2, String toevoeging2, String geboortedatum2, String email, int telefoonnummer) {
		super();
		ID = iD;
		this.wachtwoord = wachtwoord;
		this.voornaam = voornaam;
		this.achternaam = achternaam;
	}
	
	public Medewerker(int i, String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}

	public int getID() {
		return ID;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public String getAchternaam() {
		return achternaam;
	}
	public String getPostcode() {
		return postcode;
	}
	public int getHuisnummer() {
		return huisnummer;
	}
	public String getToevoeging() {
		return toevoeging;
	}
	public String getGeboortedatum() {
		return geboortedatum;
	}
	@Override
	public boolean login(int ID, String ww) {
		if (this.ID == ID && this.wachtwoord.equals(ww)) {
			return true;
		}
		return false;
	}
	

}
