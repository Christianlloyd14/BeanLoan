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
		frame.setSize(897, 516);
		frame.setLayout(null);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);

		loginPanel.setBounds(0,0, 897, 516);
		loginPanel.setBackground(new Color(50, 129, 186));
		loginPanel.setLayout(null);
		frame.add(loginPanel);
		
		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setBounds(10, 10, 500, 40);
		loginLabel.setFont(new Font("Arial", Font.BOLD, 25));
		loginPanel.add(loginLabel);
		
		JLabel usernameLabel = new JLabel("username:");
		usernameLabel.setBounds(469, 70, 100, 40);
		loginPanel.add(usernameLabel);
		
		JTextField usernameField = new JTextField("");
		usernameField.setBounds(469, 110, 332, 46);
		loginPanel.add(usernameField);
		
		JLabel passwordLabel = new JLabel("password:");
		passwordLabel.setBounds(469, 140, 100, 40);
		loginPanel.add(passwordLabel);
		
		JPasswordField passwordField = new JPasswordField("");
		passwordField.setBounds(469, 180, 332, 46);
		loginPanel.add(passwordField);


		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(469, 280, 332, 46);
		loginButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String username = usernameField.getText();
				String password = new String (passwordField.getPassword());
				LoginController.ifUserExist(frame, loginPanel, usernameField, passwordField);
				
			}	
		});
		loginPanel.add(loginButton);

		JLabel signupLabel = new JLabel("Don’t have a Bean account yet?");
		signupLabel.setBounds(515, 330, 200, 30);
		loginPanel.add(signupLabel);
		
		JLabel registerButton = new JLabel("Sign up now!");
		registerButton.setBounds(700, 330, 200, 30);  // Adjusted y-coordinate to be within the visible bounds
		registerButton.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        usernameField.setText("");
		        passwordField.setText("");
		        LoginController.displayRegisterView(frame, loginPanel);
		    }
		});
		loginPanel.add(registerButton);

		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	
}