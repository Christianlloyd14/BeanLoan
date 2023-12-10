package book.system.register;

import javax.swing.*;
import book.system.user.UserController;

public class RegisterController{
	public RegisterController(JFrame frame, JPanel loginPanel){
		new RegisterView(frame, loginPanel);
	}
	
	public static void toCreateUser(JFrame frame, JTextField nameField, JTextField userField, JPasswordField passField, JPanel loginPanel){
		UserController.toCreateUser(frame, nameField, userField, passField, loginPanel);
	}
}