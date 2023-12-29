package book.system.login;

import javax.swing.*;
import book.system.user.UserController;
import book.system.register.RegisterController;
import book.system.dashboard.DashboardController;

public class LoginController{
	private JFrame frame;
	private JPanel loginPanel;
	public LoginController(JFrame frame, JPanel loginPanel){
		this.frame = frame;
		this.loginPanel = loginPanel;
		new LoginView(this.frame, this.loginPanel);
	}
	
	public static void ifUserExist(JFrame frame, JPanel loginPanel, JTextField usernameField, JPasswordField passwordField){
		UserController.ifUserExist(frame, loginPanel, usernameField, passwordField);
	}
	
	public static void displayWelcomeView(JFrame frame, JPanel loginPanel, String username, String password){
		new DashboardController(frame, loginPanel, username, password);
	}
	
	public static void displayRegisterView(JFrame frame, JPanel loginPanel){
		new RegisterController(frame, loginPanel);
	}
}