package edu.winona.cs.app;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LoginScreen {
	private static Text usernameInput;
	private static Text passwordInput;
	private static Text signUpUserInput;
	private static Text signUpPassInput;

	/**
	 * Launch the application.
	 * @param args
	 */
	//TODO: Remove main method
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shlPuzzleSlider = new Shell();
		shlPuzzleSlider.setBackground(SWTResourceManager.getColor(255, 153, 0));
		shlPuzzleSlider.setSize(480, 300);
		shlPuzzleSlider.setText("Puzzle Slider");
		
		setUsernameInput(new Text(shlPuzzleSlider, SWT.BORDER));
		getUsernameInput().setBounds(20, 57, 141, 41);
		
		passwordInput = new Text(shlPuzzleSlider, SWT.PASSWORD | SWT.BORDER);
		passwordInput.setBounds(20, 104, 141, 41);
		
		Button btnLoginButton = new Button(shlPuzzleSlider, SWT.NONE);
		btnLoginButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (usernameInput.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "You need to enter a username!");
				else if (passwordInput.getText().length() == 0)
					JOptionPane.showMessageDialog(null, "You need to enter a password!");
				else {
					String user = usernameInput.getText();
					char [] pass = passwordInput.getTextChars();
					String pwd = String.copyValueOf(pass);
					if(validate_login(user, pwd))
						JOptionPane.showMessageDialog(null, "Successful!");
					else
						JOptionPane.showMessageDialog(null, "Incorrect username and/or password.");
				}
				
			}
		});
		btnLoginButton.setBounds(20, 151, 141, 35);
		btnLoginButton.setText("LOGIN");
		
		Label retUserLabel = new Label(shlPuzzleSlider, SWT.NONE);
		retUserLabel.setAlignment(SWT.CENTER);
		retUserLabel.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 18, SWT.NORMAL));
		retUserLabel.setBounds(20, 10, 141, 41);
		retUserLabel.setText("Returning Users:");
		
		Label label = new Label(shlPuzzleSlider, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(215, 17, 2, 258);
		
		Label lblNewLabel = new Label(shlPuzzleSlider, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 18, SWT.NORMAL));
		lblNewLabel.setBounds(257, 10, 164, 35);
		lblNewLabel.setText("Create an account:");
		
		signUpUserInput = new Text(shlPuzzleSlider, SWT.BORDER);
		signUpUserInput.setBounds(262, 57, 141, 41);
		
		signUpPassInput = new Text(shlPuzzleSlider, SWT.BORDER | SWT.PASSWORD);
		signUpPassInput.setBounds(262, 104, 141, 41);
		
		Button btnSignUp = new Button(shlPuzzleSlider, SWT.NONE);
		btnSignUp.setText("SIGN UP");
		btnSignUp.setBounds(262, 151, 141, 35);
		
		Button guestButton = new Button(shlPuzzleSlider, SWT.NONE);
		guestButton.setBounds(329, 233, 141, 35);
		guestButton.setText("JUST LET ME PLAY!");
		
		Label lblNewLabel_1 = new Label(shlPuzzleSlider, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 15, SWT.NORMAL));
		lblNewLabel_1.setBounds(291, 208, 179, 19);
		lblNewLabel_1.setText("Want to play as a guest?");

		shlPuzzleSlider.open();
		shlPuzzleSlider.layout();
		while (!shlPuzzleSlider.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	private static boolean validate_login(String username, String password) {
		try {
			Class.forName("edu.winona.cs.db");
			String dbURL = "jdbc:derby:sampledb;create=true";
			Connection conn = DriverManager.getConnection(dbURL);
			PreparedStatement pst = conn.prepareStatement("Select * from userdb where username=? and password=?");
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
				return true;
			else
				return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static Text getUsernameInput() {
		return usernameInput;
	}

	public static void setUsernameInput(Text usernameInput) {
		LoginScreen.usernameInput = usernameInput;
	}

	public static Text getPasswordInput() {
		return passwordInput;
	}

	public static void setPasswordInput(Text passwordInput) {
		LoginScreen.passwordInput = passwordInput;
	}

	public static Text getSignUpUserInput() {
		return signUpUserInput;
	}

	public static void setSignUpUserInput(Text signUpUserInput) {
		LoginScreen.signUpUserInput = signUpUserInput;
	}

	public static Text getSignUpPassInput() {
		return signUpPassInput;
	}

	public static void setSignUpPassInput(Text signUpPassInput) {
		LoginScreen.signUpPassInput = signUpPassInput;
	}

	
}
