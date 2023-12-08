package book.system.login;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class LoginView {
    private JFrame frame;
    private JPanel loginPanel;

    public LoginView(JFrame frame, JPanel loginPanel) {
        this.frame = frame;
        this.loginPanel = loginPanel;
        initView();
    }

    public void initView() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(897, 516);
        frame.setLayout(null);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);

        loginPanel.setBounds(0, 0, 897, 516);
        loginPanel.setBackground(new Color(50, 129, 186));
        loginPanel.setLayout(null);
        frame.add(loginPanel);

        JLabel pic = new JLabel(); // JLabel Creation
        ImageIcon picIcon = new ImageIcon("pic.jpg"); // Load the image
        pic.setIcon(picIcon);
        pic.setBounds(0, 0, 390, 515); // Set the location and size of the image

        loginPanel.add(pic); // Adds objects to the container
        frame.setVisible(true);

        JLabel img = new JLabel();
        ImageIcon icon = new ImageIcon("img.jpg");
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        img.setIcon(scaledIcon);
        Dimension imgSize = img.getPreferredSize(); // Use a different variable name
        img.setBounds(570, 50, 150, 150);
        applyCircularMask(img);

        loginPanel.add(img);
        
        JTextField usernameField = new JTextField("Username");
        usernameField.setBounds(469, 218, 332, 46);
        usernameField.setForeground(Color.GRAY);
        
        // Create a rounded border for the JTextField
        Border roundedBorder = new RoundedBorder(10); // Adjust the corner radius as needed
        usernameField.setBorder(roundedBorder);

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
    private void applyCircularMask(JLabel label) {
        int diameter = Math.min(label.getWidth(), label.getHeight());
        BufferedImage image = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        Ellipse2D.Double ellipse = new Ellipse2D.Double(0, 0, diameter, diameter);
        g2d.setClip(ellipse);
        g2d.drawImage(((ImageIcon) label.getIcon()).getImage(), 0, 0, diameter, diameter, null);
        g2d.dispose();

        label.setIcon(new ImageIcon(image));
	
    }
        // Custom rounded border class
    private static class RoundedBorder implements Border {
        private int radius;

        public RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(c.getBackground());
            g.fillRoundRect(x, y, width, height, radius, radius);
        }
    }

}