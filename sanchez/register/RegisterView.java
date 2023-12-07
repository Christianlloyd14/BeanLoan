package book.system.register;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class RegisterView{
	public RegisterView(JFrame frame, JPanel loginPanel){
		JPanel registerPanel = new JPanel();
		registerPanel.setBounds(0, 0, 897, 516);
		registerPanel.setBackground(new Color(50, 129, 186));
		registerPanel.setLayout(null);

		JPanel bluePanel = new JPanel();
		bluePanel.setBounds(0, 0, 312, 516);
		bluePanel.setBackground(new Color(23, 53, 99));
		bluePanel.setLayout(null);		
		registerPanel.add(bluePanel);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(registerPanel);
		frame.repaint();
		frame.revalidate();
		
		JLabel registerLabel = new JLabel("REGISTER");
		registerLabel.setBounds(350, 10, 500, 40);
		registerLabel.setFont(new Font("Arial", Font.BOLD, 25));
		registerPanel.add(registerLabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(460, 70, 100, 40);
		registerPanel.add(nameLabel);
		
		JTextField nameField = new JTextField("");
		nameField.setBounds(460, 110, 350, 30);
		registerPanel.add(nameField);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(460, 140, 100, 40);
		registerPanel.add(userLabel);
		
		JTextField userField = new JTextField("");
		userField.setBounds(460, 180, 350, 30);
		registerPanel.add(userField);
		
		JLabel passLabel = new JLabel("Password");
		passLabel.setBounds(460, 210, 100, 40);
		registerPanel.add(passLabel);
		
		JPasswordField passField = new JPasswordField("");
		passField.setBounds(460, 250, 350, 30);
		registerPanel.add(passField);
		
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(680, 320, 100, 30);
		registerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				RegisterController.toCreateUser(frame, nameField, userField, passField, loginPanel);
			}	
		});
		registerPanel.add(registerButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(495, 320, 100, 30);
		cancelButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.getContentPane().removeAll();
				frame.getContentPane().add(loginPanel);
				frame.repaint();
				frame.revalidate();
			}	
		});
		registerPanel.add(cancelButton);

		JLabel termsLabel = new JLabel("Terms and privacy");
		termsLabel.setBounds(583, 370, 150, 30);
		registerPanel.add(termsLabel);
	}
}