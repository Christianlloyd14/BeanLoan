package book.system.user;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class UserModel {
	private JFrame frame;
	private JPanel loginPanel;

	public UserModel(JFrame frame, JPanel loginPanel) {
		this.frame = frame;
		this.loginPanel = loginPanel;
	}

	public static void toCreateUser(JFrame frame, JTextField nameField, JTextField userField, JPasswordField passField, JPanel loginPanel) {
		String name = nameField.getText();
		String username = userField.getText();
		char[] password = passField.getPassword();

		if (name.isEmpty() || username.isEmpty() || password.length == 0) {
			JOptionPane.showMessageDialog(frame, "Please fill in all the fields");
			return;
		}

		if (userExists(username) || nameExists(name)) {
			JOptionPane.showMessageDialog(frame, "Username or name already exists. Please choose a different one.");
			return;
		}
		
		String databaseDirectory = "database/";
		
		 // Create directory if it doesn't exist
        File directory = new File(databaseDirectory);
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                JOptionPane.showMessageDialog(frame, "Failed to create the database directory");
                return;
            }
        }
		
		try (FileWriter fwrite = new FileWriter(databaseDirectory + "users.dat", true)) {
			fwrite.write(name + ":" + username + ":" + new String(password) + System.lineSeparator());
			JOptionPane.showMessageDialog(frame, "Register successfully");

			frame.getContentPane().removeAll();
			frame.getContentPane().add(loginPanel);
			frame.repaint();
			frame.revalidate();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Failed to register");
			e.printStackTrace();
		}
	}

	public static boolean readUser(String username, String password) {
		String databaseDirectory = "database/";
		try (BufferedReader reader = new BufferedReader(new FileReader(databaseDirectory + "users.dat"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(":");
				String savedUsername = parts[1].trim();
				String savedPassword = parts[2].trim();

				if (savedUsername.equals(username) && savedPassword.equals(password)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void ifUserExist(JFrame frame, JPanel loginPanel, JTextField usernameField, JPasswordField passwordField) {
		String username = usernameField.getText();
		String password = new String(passwordField.getPassword());

		if (readUser(username, password)) {
			UserController.displayWelcomeView(frame, loginPanel, username, password);
			usernameField.setText("");
			passwordField.setText("");

			String fileName = "database/" + username + "-" + password + ".txt";
			createFile(fileName, "User: " + username + "\nPassword: " + password + "\nBalance: 0\nStatus: \nNo Notification");


			
		} else {
			JOptionPane.showMessageDialog(frame, "Invalid username or password");
		}
	}

	private static void createFile(String fileName, String content) {
		File file = new File(fileName);
		
		if (file.exists()) {
			return;
		}

		try (FileWriter fileWriter = new FileWriter(file)) {
			fileWriter.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	private static boolean userExists(String username) {
		return userOrNameExists(username, 1);
	}
	
	

	private static boolean nameExists(String name) {
		return userOrNameExists(name, 0);
	}

	private static boolean userOrNameExists(String value, int index) {
		String databaseDirectory = "database/";
		try (BufferedReader reader = new BufferedReader(new FileReader(databaseDirectory + "users.dat"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(":");
				String savedValue = parts[index].trim();

				if (savedValue.equals(value)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String getUserNotification(String username, String password) {
		String fileName = "database/" + username + "-" + password + ".txt";
		StringBuilder notification = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
			String line;
			boolean notificationStarted = false;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("No Notification")) {
					notificationStarted = true;
					notification.append(line).append("\n");
				} else if (notificationStarted) {
					// Assume that the notification part is after the line starting with "Notification:"
					notification.append(line).append("\n");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return notification.toString();
	}



}
