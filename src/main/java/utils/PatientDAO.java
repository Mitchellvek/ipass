package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Patient;

public class PatientDAO extends BaseDAO {
	
	public List<Integer> checkIDs(List<Patient> allePatienten) {
		List<Integer> patientids = new ArrayList();
		String querryin = null;
		if(!allePatienten.isEmpty()){
			for (Patient user : allePatienten) {
				if(user != null) {
					if (querryin == null) {
						querryin = "" + user.getID();
					}else{
					querryin += "," + user.getID();
					}
				}
			}
		}else{
			querryin = "" + 1;
		}
		
		String query = "SELECT idpatient from patientgegevens WHERE idpatient NOT IN (" + querryin + ")";
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			System.out.println(dbResultSet);

			while(dbResultSet.next()) {
				int ID = dbResultSet.getInt("idpatient");
				
				patientids.add(ID);
			}
			con.close();
			
			return patientids;
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}

	public Patient createPatient(int i) {
		String query2 = "SELECT * from patientgegevens WHERE idpatient = " + i;
		
		try (Connection con = super.getConnection()) {		

			
			Statement stmt2 = con.createStatement();
			ResultSet dbResultSet2 = stmt2.executeQuery(query2);
				if(dbResultSet2.first()) {
				
				int ID = dbResultSet2.getInt("idpatient");
				String wachtwoord = dbResultSet2.getString("wachtwoord");
				String voornaam = dbResultSet2.getString("voornaam");
				String achternaam = dbResultSet2.getString("achternaam");
				String woonplaats = dbResultSet2.getString("woonplaats");
				String postcode = dbResultSet2.getString("postcode");
				int huisnummer = dbResultSet2.getInt("huisnummer");
				String toevoeging = dbResultSet2.getString("toevoeging");
				String inschrijfdatum = dbResultSet2.getString("inschrijfdatum");
				String geboortedatum = dbResultSet2.getString("geboortedatum");
				String email = dbResultSet2.getString("email");
				int telefoonnummer = dbResultSet2.getInt("telefoonnummer");
				
				Patient p = new Patient(ID, wachtwoord, voornaam, achternaam, woonplaats, postcode, huisnummer, toevoeging, inschrijfdatum, geboortedatum, email, telefoonnummer);
				con.close();
				return p;
				}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;

	}

	public boolean wijziggegevens(int id, String klantVoornaam, String klantAchternaam, String klantWoonplaats,
			String klantPostcode, int klantHuisnummer, String klantToevoeging, String klantGeboortedatum, String klantInschrijfdatum, String klantemail, int klanttelefoonnummer) {
		String query = null;
		if(klantToevoeging == null) {
			query = "UPDATE patientgegevens SET voornaam = '" + klantVoornaam + "', achternaam = '" + klantAchternaam + "', woonplaats = '" + klantWoonplaats + "', postcode = '" + klantPostcode + "', huisnummer = " + klantHuisnummer + ", toevoeging = null, inschrijfdatum = '" + klantInschrijfdatum + "', geboortedatum = '" + klantGeboortedatum + "', email = '" + klantemail + "', telefoonnummer = " + klanttelefoonnummer + " WHERE idpatient = " + id;
		}else {
			query = "UPDATE patientgegevens SET voornaam = '" + klantVoornaam + "', achternaam = '" + klantAchternaam + "', woonplaats = '" + klantWoonplaats + "', postcode = '" + klantPostcode + "', huisnummer = " + klantHuisnummer + ", toevoeging = '" + klantToevoeging + "', inschrijfdatum = '" + klantInschrijfdatum + "', geboortedatum = '" + klantGeboortedatum + "', email = '" + klantemail + "', telefoonnummer = " + klanttelefoonnummer + " WHERE idpatient = " + id;
		}
		System.out.println(query);
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
			con.close();
		return true;
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}

	public void verwijder(Patient klant) {
		String query = "DELETE FROM patientgegevens WHERE idpatient = " + klant.getID();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
			con.close();
			
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
	}

	public Patient loginPatient(int username, String pass1) {
		String query2 = "SELECT * from patientgegevens WHERE idpatient = " + username + " AND wachtwoord = '" + pass1 + "'";
		
		try (Connection con = super.getConnection()) {		

			
			Statement stmt2 = con.createStatement();
			ResultSet dbResultSet2 = stmt2.executeQuery(query2);
				if(dbResultSet2.first()) {
				
				int ID = dbResultSet2.getInt("idpatient");
				String wachtwoord = dbResultSet2.getString("wachtwoord");
				String voornaam = dbResultSet2.getString("voornaam");
				String achternaam = dbResultSet2.getString("achternaam");
				String woonplaats = dbResultSet2.getString("woonplaats");
				String postcode = dbResultSet2.getString("postcode");
				int huisnummer = dbResultSet2.getInt("huisnummer");
				String toevoeging = dbResultSet2.getString("toevoeging");
				String inschrijfdatum = dbResultSet2.getString("inschrijfdatum");
				String geboortedatum = dbResultSet2.getString("geboortedatum");
				String email = dbResultSet2.getString("email");
				int telefoonnummer = dbResultSet2.getInt("telefoonnummer");
				
				Patient p = new Patient(ID, wachtwoord, voornaam, achternaam, woonplaats, postcode, huisnummer, toevoeging, inschrijfdatum, geboortedatum, email, telefoonnummer);
				con.close();
				return p;
				}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;

	}
		
}
		

		
	

