package edu.winona.cs.app;

import edu.winona.cs.db.UserTable;
import javax.swing.JOptionPane;

public class App {

    public static void main(String[] args) {
        //TODO: Remove sample code and use this main class to launch GUI, database, and other components.

        UserTable userTable = new UserTable();
        userTable.createTable();
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
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
