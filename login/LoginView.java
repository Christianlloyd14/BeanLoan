package book.system.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginView{
	private JFrame frame;
	private JPanel loginPanel;
	public LoginView(JFrame frame, JPanel loginPanel){
		this.frame = frame;
		this.loginPanel = loginPanel;
		initView();
	}
	
	public void initView(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(380, 400);
		frame.setLayout(null);
		
		loginPanel.setBounds(0,0, 380, 400);
		loginPanel.setBackground(Color.WHITE);
		loginPanel.setLayout(null);
		frame.add(loginPanel);
		
		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setBounds(10, 10, 500, 40);
		loginLabel.setFont(new Font("Arial", Font.BOLD, 25));
		loginPanel.add(loginLabel);
		
		JLabel usernameLabel = new JLabel("username:");
		usernameLabel.setBounds(10, 70, 100, 40);
		loginPanel.add(usernameLabel);
		
		JTextField usernameField = new JTextField("");
		usernameField.setBounds(10, 110, 350, 30);
		loginPanel.add(usernameField);
		
		JLabel passwordLabel = new JLabel("password:");
		passwordLabel.setBounds(10, 140, 100, 40);
		loginPanel.add(passwordLabel);
		
		JPasswordField passwordField = new JPasswordField("");
		passwordField.setBounds(10, 180, 350, 30);
		loginPanel.add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(10, 280, 350, 30);
		loginButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String username = usernameField.getText();
				String password = new String (passwordField.getPassword());
				LoginController.ifUserExist(frame, loginPanel, usernameField, passwordField);
				
			}	
		});
		loginPanel.add(loginButton);
		
		JButton registerButton = new JButton("Register");
		registerButton.setBounds(10, 315, 350, 30);
		registerButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				usernameField.setText("");
				passwordField.setText("");
				LoginController.displayRegisterView(frame, loginPanel);
			}	
		});
		loginPanel.add(registerButton);
		
	
		frame.setVisible(true);
	}
	
	
}