package utils;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDAO {
	 protected final Connection getConnection() {
		 Connection result = null;
		
	 try {
	System.out.println("TEXT1"); 
	 InitialContext ic = new InitialContext();
	 DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/MySQLDS");
	 
	 System.out.println("TEXT2");

	 result = ds.getConnection();
	 System.out.println("TEXT3");
	 } catch (Exception ex) {
		 System.out.println("TEXT4");
		 throw new RuntimeException(ex);
	 }
	 return result;
	 }
}