package book.system.user;

import javax.swing.*;
import book.system.login.LoginController;

public class UserController{
	private JFrame frame;
	private JPanel loginPanel;
	
	public UserController(JFrame frame, JPanel loginPanel){
		this.frame = frame;
		this.loginPanel = loginPanel;
		new UserModel(this.frame, this.loginPanel);
	}
	
	public static void ifUserExist(JFrame frame, JPanel loginPanel, JTextField usernameField, JPasswordField passwordField){
		UserModel.ifUserExist(frame, loginPanel,usernameField, passwordField);
	}
	
	public static void displayWelcomeView(JFrame frame, JPanel loginPanel, String username){
		LoginController.displayWelcomeView(frame, loginPanel, username);
	}
	
	public static void toCreateUser(JFrame frame, JTextField nameField, JTextField userField, JPasswordField passField, JPanel loginPanel){
		UserModel.toCreateUser(frame, nameField, userField, passField, loginPanel);
	}
}