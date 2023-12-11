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

		JLabel img = new JLabel(); //JLabel Creation
        img.setIcon(new ImageIcon("img.jpg")); //Sets the image to be displayed as an icon
        Dimension size = img.getPreferredSize(); //Gets the size of the image
        img.setBounds(0, 0, 390, 515); //Sets the location of the image
 
   		loginPanel.add(img); //Adds objects to the container
        frame.setVisible(true);	

        JTextField usernameField = new JTextField("Username");
        usernameField.setBounds(469, 218, 332, 46);
        usernameField.setForeground(Color.GRAY);
        usernameField.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }
        });
        usernameField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setText("Username");
                    usernameField.setForeground(Color.GRAY);
                }
            }
        });
        loginPanel.add(usernameField);

        JPasswordField passwordField = new JPasswordField("Enter your password");
        passwordField.setBounds(469, 275, 332, 46);
        passwordField.setForeground(Color.GRAY);
        passwordField.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                char[] password = passwordField.getPassword();
                if (new String(password).equals("Enter your password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }
        });
        passwordField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
                char[] password = passwordField.getPassword();
                if (password.length == 0) {
                    passwordField.setText("Enter your password");
                    passwordField.setForeground(Color.GRAY);
                }
            }
        });
        passwordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                // Clear default text when user starts typing
                char[] password = passwordField.getPassword();
                if (new String(password).equals("Enter your password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);


                }
            }
        });
        loginPanel.add(passwordField);


		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(469, 345, 332, 46);
		loginButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String username = usernameField.getText();
				String password = new String (passwordField.getPassword());
				LoginController.ifUserExist(frame, loginPanel, usernameField, passwordField);
				
			}	
		});
		loginPanel.add(loginButton);

		JLabel signupLabel = new JLabel("Don’t have a Bean account yet?");
		signupLabel.setBounds(515, 390, 200, 30);
		loginPanel.add(signupLabel);
		
		JLabel registerButton = new JLabel("Sign up now!");
		registerButton.setBounds(700, 390, 200, 30);
		registerButton.setForeground(Color.BLUE);  // Initial foreground color
		registerButton.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        usernameField.setText("");
		        passwordField.setText("");
		        LoginController.displayRegisterView(frame, loginPanel);
		    }

		    public void mouseEntered(MouseEvent e) {
		        registerButton.setForeground(Color.RED);  // Highlighted foreground color
		    }

		    public void mouseExited(MouseEvent e) {
		        registerButton.setForeground(Color.WHITE);  // Restore the initial foreground color
		    }
		});
		loginPanel.add(registerButton);

		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	
}