package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Medewerker;

public class MedewerkerDAO extends BaseDAO {

	public Medewerker createMedewerker(int i) {
		String query2 = "SELECT * from medewerkergegevens WHERE idmedewerker = " + i;
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			
			ResultSet dbResultSet2 = stmt.executeQuery(query2);
			if(dbResultSet2.first()) {
				
				int ID = dbResultSet2.getInt("idmedewerker");
				String wachtwoord = dbResultSet2.getString("wachtwoord");
				String voornaam = dbResultSet2.getString("voornaam");
				String achternaam = dbResultSet2.getString("achternaam");
				String woonplaats = dbResultSet2.getString("woonplaats");
				String postcode = dbResultSet2.getString("postcode");
				int huisnummer = dbResultSet2.getInt("huisnummer");
				String toevoeging = dbResultSet2.getString("toevoeging");
				String geboortedatum = dbResultSet2.getString("geboortedatum");
				String email = dbResultSet2.getString("email");
				int telefoonnummer = dbResultSet2.getInt("telefoonnummer");
				
				Medewerker p = new Medewerker(ID, wachtwoord, voornaam, achternaam, woonplaats, postcode, huisnummer, toevoeging, geboortedatum, email, telefoonnummer);
				con.close();
				return p;
			}
			
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;

	}

	public List<Integer> checkIDs(List<Medewerker> alleMedewerkers) {
		List<Integer> medewerkerids = new ArrayList<Integer>();
		String querryin = null;
		if (!alleMedewerkers.isEmpty()) {
			for (Medewerker user : alleMedewerkers) {
				if (querryin == null) {
					querryin = "" + user.getID();
				}else{
				querryin += "," + user.getID();
				}
			}
		}else{
			querryin = "1";
		}
		
		
		String query = "SELECT idmedewerker from medewerkergegevens WHERE idmedewerker NOT IN (" + querryin + ")";
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while(dbResultSet.next()) {
				int ID = dbResultSet.getInt("idmedewerker");
				medewerkerids.add(ID);
			}
			con.close();
			
			return medewerkerids;
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}

	public Medewerker loginMedewerker(int username, String pass1) {
		String query2 = "SELECT * from medewerkergegevens WHERE idmedewerker = " + username + " AND wachtwoord = '" + pass1 + "'";
		
		try (Connection con = super.getConnection()) {		
			Statement stmt2 = con.createStatement();
			ResultSet dbResultSet2 = stmt2.executeQuery(query2);
				
			if(dbResultSet2.first()) {
				
				int ID = dbResultSet2.getInt("idmedewerker");
				String wachtwoord = dbResultSet2.getString("wachtwoord");
				String voornaam = dbResultSet2.getString("voornaam");
				String achternaam = dbResultSet2.getString("achternaam");
				String woonplaats = dbResultSet2.getString("woonplaats");
				String postcode = dbResultSet2.getString("postcode");
				int huisnummer = dbResultSet2.getInt("huisnummer");
				String toevoeging = dbResultSet2.getString("toevoeging");
				String geboortedatum = dbResultSet2.getString("geboortedatum");
				String email = dbResultSet2.getString("email");
				int telefoonnummer = dbResultSet2.getInt("telefoonnummer");
				
				Medewerker p = new Medewerker(ID, wachtwoord, voornaam, achternaam, woonplaats, postcode, huisnummer, toevoeging, geboortedatum, email, telefoonnummer);
				con.close();
				return p;
				}
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;

	}
	
}
