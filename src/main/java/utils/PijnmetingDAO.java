package utils;

import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Patient;
import model.Pijnmeting;
import model.Provider;

public class PijnmetingDAO extends BaseDAO{
	
	public void checkpijnmetingenbijID(List<Integer> list) {	
		String querryin = null;
		if (!list.isEmpty()) {
			for (Integer meting : list) {
				if (querryin == null) {
					querryin = "" + meting;
				}else{
				querryin += "," + meting;
				}
			}
		}else{
			querryin = "" + 1;
		}
		
		String query = "SELECT * from pijnmeting WHERE idpijnmeting NOT IN (" + querryin + ")";
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);

			while(dbResultSet.next()) {
				int ID = dbResultSet.getInt("idpijnmeting");
				int PatientID = dbResultSet.getInt("idpatient");
				int hoeveelheid = dbResultSet.getInt("hoeveelheid");
				String locatie = dbResultSet.getString("locatie");
				String opmerking = dbResultSet.getString("opmerking");
				String datum = dbResultSet.getString("datum");
				String tijd = dbResultSet.getString("tijd");
				
				Provider.getService().createPijnmetingForUser(ID, PatientID, hoeveelheid, locatie, opmerking, datum, tijd);
			}
			con.close();
			
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public int createPijnmeting(int PatientID, int hoeveelheid, String locatie, String opmerking, String datum, String tijd) {
		String query = "INSERT INTO pijnmeting (idpatient, hoeveelheid, locatie, opmerking, datum, tijd) VALUES (" + PatientID + ", " + hoeveelheid + ", '" + locatie + "', '" + opmerking + "', '" + datum + "', '" + tijd + "')";
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
			String query2 = "Select idpijnmeting FROM pijnmeting WHERE hoeveelheid = " + hoeveelheid + " AND locatie = '" + locatie + "' AND opmerking = '" + opmerking + "' AND datum = '" + datum + "' AND tijd = '" + tijd + "'";
			
			Statement stmt2 = con.createStatement();
			ResultSet dbResultSet = stmt2.executeQuery(query2);
			
			dbResultSet.first();
			int ID = dbResultSet.getInt("idpijnmeting");
			con.close();
			return ID;
			
	}catch (SQLException sqle) {
		sqle.printStackTrace();
	}
		return 0;
}

	public void verwijder(Pijnmeting p) {
		String query = "DELETE FROM pijnmeting WHERE idpijnmeting = " + p.getID();
		
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(query);
			
		}catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
	}


	public List<Pijnmeting> getPijnmetingforUser(Patient newuser) {
		List<Pijnmeting> list = new ArrayList<>();
		
		String querryin = null;
		if(!newuser.getMijnpijnmetingen().isEmpty()){
			for (Pijnmeting user : newuser.getMijnpijnmetingen()) {
				if (querryin == null) {
					querryin = "" + user.getID();
				}else{
				querryin += "," + user.getID();
				}
			}
		}else{
			querryin = "" + 1;
		}
		
		String query = "SELECT * from pijnmeting WHERE idPatient = " + newuser.getID() + " AND idpijnmeting NOT IN (" + querryin + ")";
				
			try (Connection con = super.getConnection()) {
				Statement stmt = con.createStatement();
				ResultSet dbResultSet = stmt.executeQuery(query);

				while(dbResultSet.next()) {
					int ID = dbResultSet.getInt("idpijnmeting");
					int hoeveelheid = dbResultSet.getInt("hoeveelheid");
					String locatie = dbResultSet.getString("locatie");
					String opmerking = dbResultSet.getString("opmerking");
					String datum = dbResultSet.getString("datum");
					String tijd = dbResultSet.getString("tijd");
					
					Pijnmeting newmeting = new Pijnmeting (ID, hoeveelheid, locatie, opmerking, datum, tijd);
					list.add(newmeting);
				}
				con.close();
				return list;
			}catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			return list;
	}
}