
package edu.winona.cs.app;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import edu.winona.cs.db.DatabaseManager;
import edu.winona.cs.db.UserTable;

public class App {

	public static void main(String[] args) throws SQLException {
		//TODO: Remove sample code and use this main class to launch GUI, database, and other components.
		JOptionPane.showMessageDialog(null, "Hello World.");
	
		DatabaseManager dm = new DatabaseManager();
		Connection conn = dm.connectionToDerby();
		UserTable ut = new UserTable(conn);
		ut.addUser("erika", "hello1234");
		ut.dropTable();
		
	}
	
	public static String stringValidation() {
		return "TEST";
	}
	
	public static int intValidation() {
		return 0;
	}
	
	public static boolean boolValidation() {
		return true;
	}

}
