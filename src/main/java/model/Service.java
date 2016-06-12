package model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Vraagstelling;
import utils.MedewerkerDAO;
import utils.PatientDAO;
import utils.PijnmetingDAO;

public class Service {
	private static List<Patient> allePatienten = new ArrayList<Patient>();
	private static List<Patient> listPatienten = new ArrayList<Patient>();
	private static List<Medewerker> alleMedewerkers = new ArrayList<Medewerker>();
	
	
	public List<Patient> getAllePatienten() {
		return allePatienten;
	}
	
	public List<Patient> getKlantenlijst() {
		return listPatienten;
	}
	
	public Patient getPatientforlist(int ID) {
		for(Patient user : listPatienten) {
			if (user.getID() == ID) {
				System.out.println("found " + user.getVoornaam());
				return user;
			}
		}
		System.out.println("found nobody");
		return null;
	}
	
	public Patient getPatient(int ID) {
		for(Patient user : allePatienten) {
			if (user.getID() == ID) {
				System.out.println("found " + user.getVoornaam());
				return user;
			}
		}
		System.out.println("found nobody");
		return null;
	}
	
	public List<Medewerker> getAlleMedewerkers() {
		return alleMedewerkers;
	}
	
	public Patient loginPatient(int username, String pass1) {
		PatientDAO pDAO = new PatientDAO();
		Patient newuser = null;
		boolean alreadyin = false;
		if(!allePatienten.isEmpty()){
			if(!allePatienten.contains(null)) {
				for (Patient p : allePatienten) {
					if(p.login(username, pass1)) {
						newuser = p;
						alreadyin = true;
					}
				}
			}else {
				allePatienten.clear();
			}
		}
		
		if (newuser == null && !listPatienten.isEmpty()) {
			for (Patient p : listPatienten) {
				if (p.login(username, pass1)) {
					newuser = p;
				}
			}
		}				
				
		if(newuser == null) {		
			newuser = pDAO.loginPatient(username, pass1);
		}
		if (alreadyin == false) {allePatienten.add(newuser);}
		
		if(newuser != null) {
			System.out.println("Found a match!");
			List<Pijnmeting> list = this.getPijnmetingforUser(newuser);
			for (Pijnmeting pm : list) {
				newuser.addPijnmeting(pm);
			}
			return newuser;
		}else {
			return null;
		}
		
	}
	private List<Pijnmeting> getPijnmetingforUser(Patient newuser) {
		PijnmetingDAO pmDAO = new PijnmetingDAO();
		List<Pijnmeting> list = pmDAO.getPijnmetingforUser(newuser);
		return list;
	}

	public Medewerker loginMedewerker(int username, String pass1) {
		MedewerkerDAO mDAO = new MedewerkerDAO();
		Medewerker newuser = null;
		boolean alreadyin = false;
		if(alleMedewerkers.contains(null)) {
			alleMedewerkers.clear();
		}
		if (!alleMedewerkers.isEmpty()) {
			for(Medewerker m : alleMedewerkers) {
				System.out.println(m.getVoornaam());
				if(m.login(username, pass1)) {
					newuser = m;
					alreadyin = true;
				}
			}
		}
		if (newuser == null) {
			newuser = mDAO.loginMedewerker(username, pass1);
		}
		if(alreadyin == false) {alleMedewerkers.add(newuser);}
		
		if(newuser != null) {
			System.out.println("Found a match!");
			this.retrieveAllPatienten();
			return newuser;
		}else{
			return null;
		}		
	}
	
	private void retrieveAllPatienten() {
		if(listPatienten.contains(null)) {listPatienten.clear();}
		if (allePatienten.contains(null)) {
			allePatienten.clear();
		}
		listPatienten.addAll(allePatienten);
		
		PatientDAO pDAO = new PatientDAO();
		List<Integer> IDListp = pDAO.checkIDs(listPatienten);
		System.out.println("");
		
		if(IDListp == null || IDListp.isEmpty()) {
			IDListp.clear();
		}else{
			for (int i : IDListp) {
				Patient p = pDAO.createPatient(i);
				if(p != null){
					listPatienten.add(p);
					System.out.println(p.getVoornaam());
					List<Pijnmeting> list = this.getPijnmetingforUser(p);
					for(Pijnmeting pm : list) {
						p.addPijnmeting(pm);
					}
					System.out.println("got everything for: " + p.getVoornaam() + " " + p.getAchternaam());
				}
			}
		}
	}

	public void addPatient(Patient dummy) {
		allePatienten.add(dummy);
	}
	
	public void addMedewerker(Medewerker dummy) {
		alleMedewerkers.add(dummy);
	}
	
	public void addPijnmetingforPatient(int hoeveelheid, String locatie, String opmerking, Patient user) {
		for (Patient p : allePatienten) {
			if (p.equals(user)) {
				Pijnmeting meting = new Pijnmeting(user.getID(), hoeveelheid, locatie, opmerking);
				p.addPijnmeting(meting);
			}
		}
		
	}
	
	public void verstuurvraag(String vraag, Patient user) {
		String forname = user.getVoornaam();
		System.out.println(forname);
		String lastname = user.getAchternaam();
		System.out.println(user.getAchternaam());
		int ID = user.getID();
		Vraagstelling stelling = new Vraagstelling(ID, forname, lastname, vraag);
		stelling.verstuurvraag();
		System.out.println("Vraag verstuurd");
	}	

	public void createPijnmetingForUser(int iD, int patientID, int hoeveelheid, String locatie, String opmerking, String datum,
			String tijd) {
		Patient p = this.getPatient(patientID);
		System.out.println("Pijnmeting toegevoegd voor " + patientID);
		Pijnmeting pm = new Pijnmeting(iD, hoeveelheid, locatie, opmerking, datum, tijd);
		p.addPijnmeting(pm);
	}


	public void wijzigklantgegevens(Patient klant, String klantVoornaam, String klantAchternaam, String klantWoonplaats,
			String klantPostcode, int klantHuisnummer, String klantToevoeging, String klantGeboortedatum,
			String klantInschrijfdatum, String klantemail, int klanttelefoonnummer) {
		PatientDAO pmDAO = new PatientDAO();
		boolean wijziging = pmDAO.wijziggegevens(klant.getID(), klantVoornaam, klantAchternaam, klantWoonplaats, klantPostcode, klantHuisnummer, klantToevoeging, klantGeboortedatum, klantInschrijfdatum, klantemail, klanttelefoonnummer);
		if (wijziging) {
			klant.wijzigGegevens(klantVoornaam, klantAchternaam, klantWoonplaats, klantPostcode, klantHuisnummer, klantToevoeging, klantGeboortedatum, klantInschrijfdatum, klantemail, klanttelefoonnummer);
			System.out.println("Gegevens gewijzigd");
		}
		
	}

	public void verwijderPatient(Patient klant) {
		System.out.println("Pijnmetingen uit de database verwijderen...");
		for (Pijnmeting p : klant.getMijnpijnmetingen()) {
			PijnmetingDAO pmDAO = new PijnmetingDAO();
			pmDAO.verwijder(p);
		}
		System.out.println("Pijnmetingen verwijderd!");
		System.out.println("Pati�nt verwijderen...");
		PatientDAO pDAO = new PatientDAO();
		pDAO.verwijder(klant);
		
		allePatienten.remove(klant);
		listPatienten.remove(klant);
		System.out.println("Pati�nt is verwijderd!");
	}

	public void goodbyePatient(Patient p) {
		allePatienten.remove(p);
		if (listPatienten.isEmpty()) {
			System.out.println("Tot ziens, " + p.getVoornaam() + "!");
			p = null;
		}
		
	}

	public void goodbyeMedewerker(Medewerker m) {
		alleMedewerkers.remove(m);

		if (alleMedewerkers.isEmpty()) {
			for (Patient p : listPatienten) {
				if(allePatienten.contains(p)){
				}else {
					p = null;
				}
			}
			System.out.println("Geen personeel meer aanwezig, leeg klantenoverzicht...");
			listPatienten.clear();
			System.out.println("Klantenoverzicht geleegd");
		}
		System.out.println("Tot ziens, " + m.getVoornaam() + "!");
		m = null;
	}
}
