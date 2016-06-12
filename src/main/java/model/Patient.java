package model;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;



public class Patient implements User{
	private int ID;
	private String wachtwoord;
	private String voornaam;
	private String achternaam;
	private String woonplaats;
	private String postcode;
	private int huisnummer;
	private String toevoeging;
	private String inschrijfdatum;
	private String geboortedatum;
	private String email;
	private int telefoonnummer;
	
	private List<Pijnmeting> mijnpijnmetingen = new ArrayList<Pijnmeting>();



	public Patient(int iD2, String wachtwoord2, String voornaam2, String achternaam2, String woonplaats,
			String postcode2, int huisnummer2, String toevoeging2, String inschrijfdatum2, String geboortedatum2, String email, int telefoonnummer) {
		ID = iD2;
		wachtwoord = wachtwoord2;
		voornaam = voornaam2;
		achternaam = achternaam2;
		this.woonplaats = woonplaats;
		postcode = postcode2;
		huisnummer = huisnummer2;
		toevoeging = toevoeging2;
		inschrijfdatum = inschrijfdatum2;
		geboortedatum = geboortedatum2;
		this.email = email;
		this.telefoonnummer = telefoonnummer;
	}

	public int getID() {
		return ID;
	}

	public String getWachtwoord() {
		return wachtwoord;
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

	public String getInschrijfdatum() {
		return inschrijfdatum;
	}

	public String getGeboortedatum() {
		return geboortedatum;
	}
	
	public String getWoonplaats() {
		return woonplaats;
	}

	public String getEmail() {
		return email;
	}

	public int getTelefoonnummer() {
		return telefoonnummer;
	}

	public List<Pijnmeting> getMijnpijnmetingen() {
		return mijnpijnmetingen;
	}
	
	public JSONArray getjsonmetingen() {
		
		JSONArray jab = new JSONArray();
		
		for (Pijnmeting pm : mijnpijnmetingen) {
			System.out.println(pm.getID());
			JSONObject job = new JSONObject();
			job.put("Date", pm.getDatum());
			job.put("quantity", pm.getHoeveelheid());
			jab.add(job);
		}		
		System.out.println(jab);
		return jab;
	}

	@Override
	public boolean login(int ID, String ww) {
		if (this.ID == ID && this.wachtwoord.equals(ww)) {
			return true;
		}
		return false;
	}

	public void addPijnmeting(Pijnmeting meting) {
		mijnpijnmetingen.add(meting);		
	}

	public void wijzigGegevens(String klantVoornaam, String klantAchternaam, String klantWoonplaats,
			String klantPostcode, int klantHuisnummer, String klantToevoeging, String klantGeboortedatum, String klantInschrijfdatum, String klantemail, int klanttelefoonnummer) {
		voornaam = klantVoornaam;
		achternaam = klantAchternaam;
		woonplaats = klantWoonplaats;
		huisnummer = klantHuisnummer;
		toevoeging = klantToevoeging;
		geboortedatum = klantGeboortedatum;
		inschrijfdatum = klantInschrijfdatum;
		email = klantemail;
		telefoonnummer = klanttelefoonnummer;
	}	
}
