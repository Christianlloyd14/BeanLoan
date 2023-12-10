package book.system.register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

class RoundedTextField extends JTextField {
    private int arc;

    public RoundedTextField(int columns, int arc) {
        super(columns);
        this.arc = arc;
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, arc, arc));
        super.paintComponent(g2);
        g2.dispose();
    }
}

public class RegisterView {
    public RegisterView(JFrame frame, JPanel loginPanel) {
        JPanel registerPanel = new JPanel();
        registerPanel.setBounds(0, 0, 897, 516);
        registerPanel.setBackground(new Color(50, 129, 186));
        registerPanel.setLayout(null);

        JPanel bluePanel = new JPanel();
		bluePanel.setBounds(0, 0, 312, 516);
		bluePanel.setBackground(new Color(23, 53, 99));
		bluePanel.setLayout(null);		
		registerPanel.add(bluePanel);
       
        ImageIcon pictureIcon = new ImageIcon("insta.png");

        JLabel logoLabel1 = new JLabel(pictureIcon);
        logoLabel1.setBounds(50, 50, pictureIcon.getIconWidth(), pictureIcon.getIconHeight());
        bluePanel.add(logoLabel1);

        JLabel logoLabel2 = new JLabel(pictureIcon);
        logoLabel2.setBounds(350, 250, 200, 150);
        bluePanel.add(logoLabel2);

		frame.getContentPane().removeAll();
		frame.getContentPane().add(registerPanel);
        frame.setSize(897, 516);
		frame.repaint();
		frame.revalidate();
		
		JLabel registerLabel = new JLabel("REGISTER");
		registerLabel.setBounds(350, 10, 500, 40);
		registerLabel.setFont(new Font("Arial", Font.BOLD, 25));
		registerPanel.add(registerLabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setBounds(460, 70, 100, 40);
		registerPanel.add(nameLabel);

        JTextField nameField = new RoundedTextField(20, 20);
        nameField.setBounds(460, 110, 350, 30);
        registerPanel.add(nameField);

		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(460, 140, 100, 40);
		registerPanel.add(userLabel);

        JTextField userField = new RoundedTextField(20, 20);
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
        frame.getContentPane().add(registerPanel);
        frame.setSize(897, 516);
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