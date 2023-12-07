package book.system.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class UserModel{
	private JFrame frame;
	private JPanel loginPanel;
	
	public UserModel(JFrame frame, JPanel loginPanel){
		this.frame = frame;
		this.loginPanel = loginPanel;
	}
	
	public static void toCreateUser(JFrame frame, JTextField nameField, JTextField userField, JPasswordField passField, JPanel loginPanel){
		String name = nameField.getText();
		String username = userField.getText();
		char [] password = passField.getPassword();
		
		if(name.isEmpty() || username.isEmpty() || password.length==0){
			JOptionPane.showMessageDialog(frame, "Please fill in all the fields");
			return;
		}
		
		try(FileWriter fwrite = new FileWriter("users.dat", true)){
			fwrite.write(name + ":" + username + ":" + new String(password) + System.lineSeparator());
			JOptionPane.showMessageDialog(frame, "Register successfully");
			
			frame.getContentPane().removeAll();
			frame.getContentPane().add(loginPanel);
			frame.repaint();
			frame.revalidate();
		}
		catch(IOException e){
			JOptionPane.showMessageDialog(frame, "Failed to register");
			e.printStackTrace();
		}
	}
	
	public static boolean readUser(String username, String password){
		try(BufferedReader reader = new BufferedReader(new FileReader("users.dat"))){
			String line;
			while((line = reader.readLine()) != null){
				String [] parts = line.split(":");
				String savedUsername = parts[1].trim();
				String savedPassword = parts[2].trim();
				
				if(savedUsername.equals(username) && savedPassword.equals(password)){
					return true;
				}
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		return false;
	}
	
	public static void ifUserExist(JFrame frame, JPanel loginPanel, JTextField usernameField,JPasswordField passwordField){
		String username = usernameField.getText();
		String password = new String(passwordField.getPassword());
		
		if(readUser(username, password)){
			UserController.displayWelcomeView(frame, loginPanel);
			usernameField.setText("");
			passwordField.setText("");
		}
		else{
			JOptionPane.showMessageDialog(frame, "Invalid username or password");
		}
	}
}