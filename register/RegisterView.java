package book.system.register;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RegisterView{
	public RegisterView(JFrame frame, JPanel loginPanel){
		JPanel registerPanel = new JPanel();
		registerPanel.setBounds(0, 0, 380, 400);
		registerPanel.setBackground(Color.WHITE);
		registerPanel.setLayout(null);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(registerPanel);
		frame.repaint();
		frame.revalidate();
		
		JLabel registerLabel = new JLabel("REGISTER");
		registerLabel.setBounds(10, 10, 500, 40);
		registerLabel.setFont(new Font("Arial", Font.BOLD, 25));
		registerPanel.add(registerLabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(10, 70, 100, 40);
		registerPanel.add(nameLabel);
		
		JTextField nameField = new JTextField("");
		nameField.setBounds(10, 110, 350, 30);
		registerPanel.add(nameField);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(10, 140, 100, 40);
		registerPanel.add(userLabel);
		
		JTextField userField = new JTextField("");
		userField.setBounds(10, 180, 350, 30);
		registerPanel.add(userField);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds(10, 210, 100, 40);
		registerPanel.add(passLabel);
		
		JPasswordField passField = new JPasswordField("");
		passField.setBounds(10, 250, 350, 30);
		registerPanel.add(passField);
		
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(265, 320, 100, 30);
		registerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				RegisterController.toCreateUser(frame, nameField, userField, passField, loginPanel);
			}	
		});
		registerPanel.add(registerButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(160, 320, 100, 30);
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.getContentPane().removeAll();
				frame.getContentPane().add(loginPanel);
				frame.repaint();
				frame.revalidate();
			}	
		});
		registerPanel.add(cancelButton);
	}
}