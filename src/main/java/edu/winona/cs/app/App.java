
package edu.winona.cs.app;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import edu.winona.cs.db.SampleDB;

public class App {

	public static void main(String[] args) {
		//TODO: Remove sample code and use this main class to launch GUI, database, and other components.
		JOptionPane.showMessageDialog(null, "Hello World.");
	
		SampleDB sdb = new SampleDB();
		String output = "";
		
		try {
			sdb.connectionToDerby();
			output = sdb.sampleDBUsabe();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, output);
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
