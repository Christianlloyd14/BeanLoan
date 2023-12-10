package book.system.register;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;

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

        ImageIcon pictureIcon = new ImageIcon("Testing.png");

        JLabel logoLabel1 = new JLabel(pictureIcon);
        logoLabel1.setBounds(10, 10, pictureIcon.getIconWidth(), pictureIcon.getIconHeight());
        bluePanel.add(logoLabel1);

        JLabel logoLabel2 = new JLabel(pictureIcon);
        logoLabel2.setBounds(280, 80, 10, 10);
        bluePanel.add(logoLabel2);

        frame.getContentPane().removeAll();
        frame.getContentPane().add(registerPanel);
        frame.setSize(897, 516);
        frame.repaint();
        frame.revalidate();

        JLabel registerLabel = new JLabel("REGISTER");
        registerLabel.setBounds(340, 20, 500, 40);
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

        JLabel picture = new JLabel();
        picture.setIcon(new ImageIcon("name.png"));
        Dimension size = picture.getPreferredSize();
        picture.setBounds(440, 70, 50, 50);

        registerPanel.add(picture);
        frame.setVisible(true);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(680, 320, 100, 30);
        styleButton(registerButton); 
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegisterController.toCreateUser(frame, nameField, userField, passField, loginPanel);
            }
        });
        registerPanel.add(registerButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(495, 320, 100, 30);
        styleButton(cancelButton); 
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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

    private void styleButton(JButton button) {
        button.setBackground(new Color(19, 96, 139));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        int borderRadius = 15;
        Border roundedBorder = BorderFactory.createCompoundBorder(
                new LineBorder(new Color(255, 255, 255), 2), // Border color and thickness
                new EmptyBorder(borderRadius, borderRadius, borderRadius, borderRadius) // Empty space inside the border
        );
        button.setBorder(roundedBorder);
        button.setToolTipText("Click to Register");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registration Form");
        JPanel loginPanel = new JPanel();

        SwingUtilities.invokeLater(() -> new RegisterView(frame, loginPanel));
    }
}
