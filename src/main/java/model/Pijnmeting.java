package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import utils.PijnmetingDAO;

public class Pijnmeting {
	private static int index = 1;
	private int ID;
	private int hoeveelheid;
	private String locatie;
	private String opmerking;
	private String datum;
	private String tijd;
	
	
	public Pijnmeting(int PatientID, int hoeveelheid, String locatie, String opmerking) {
		PijnmetingDAO pmDAO = new PijnmetingDAO();
		
		 DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		   //get current date time with Date()
		   Date tijdnu = new Date();
		   String tijd = timeFormat.format(tijdnu);
		
		   DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		   //get current date time with Date()
		   Date datenu = new Date();
		   String nu = dateFormat.format((datenu));
		   
		int ID = pmDAO.createPijnmeting(PatientID, hoeveelheid, locatie, opmerking, nu, tijd);
		
		this.hoeveelheid = hoeveelheid;
		this.locatie = locatie;
		this.opmerking = opmerking;
		
		   this.tijd = timeFormat.format(tijdnu);
		   
		   
		   this.datum = dateFormat.format(datenu);
	}


	public Pijnmeting(int iD2, int hoeveelheid2, String locatie2, String opmerking2, String datum2, String tijd2) {
		ID = iD2;
		hoeveelheid = hoeveelheid2;
		locatie = locatie2;
		opmerking = opmerking2;
		datum = datum2;
		tijd = tijd2;
	}


	public int getID() {
		return ID;
	}


	public int getHoeveelheid() {
		return hoeveelheid;
	}


	public String getLocatie() {
		return locatie;
	}


	public String getOpmerking() {
		return opmerking;
	}


	public String getDatum() {
		return datum;
	}


	public String getTijd() {
		return tijd;
	}
	
	
	
	
}
