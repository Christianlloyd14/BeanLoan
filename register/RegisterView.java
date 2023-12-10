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

        JPanel termsPanel = new JPanel();
        termsPanel.setBounds(10, 410, 877, 100);
        termsPanel.setBackground(new Color(220, 220, 220));
        termsPanel.setLayout(new BorderLayout());

        JTextArea termsText = new JTextArea(
                "Terms and Privacy Information for Loaning System\n\n" +
                        "1. User Agreement:\n" +
                        "   By using our loaning system, you agree to abide by the terms and conditions outlined in this agreement.\n\n" +
                        "2. Eligibility:\n" +
                        "   To use the loaning system, you must be at least 18 years old and have the legal capacity to enter into a contract.\n\n" +
                        "3. Loan Approval:\n" +
                        "   Loan approval is subject to eligibility criteria, credit assessment, and our internal policies. We reserve the right to deny a loan application without providing a reason.\n\n" +
                        "4. Interest Rates and Fees:\n" +
                        "   The interest rates and fees associated with loans are clearly communicated during the application process. It is the user's responsibility to review and understand the associated costs.\n\n" +
                        "5. Repayment Terms:\n" +
                        "   Users are required to repay the loan amount according to the agreed-upon schedule. Late payments may result in additional fees.\n\n" +
                        "6. Privacy Policy:\n" +
                        "   We are committed to protecting your privacy. Personal information collected during the loan application process is used solely for the purpose of processing the loan and will not be shared with third parties without your consent.\n\n" +
                        "7. Security Measures:\n" +
                        "   We implement industry-standard security measures to protect your personal and financial information. However, users are advised to take necessary precautions, such as maintaining secure login credentials.\n\n" +
                        "8. Termination of Account:\n" +
                        "   We reserve the right to terminate user accounts for any violation of the terms and conditions or suspicious activities.\n\n" +
                        "9. Changes to Terms:\n" +
                        "   We may update our terms and privacy policies periodically. Users will be notified of any changes, and continued use of the loaning system implies acceptance of the updated terms.\n\n" +
                        "10. Contact Information:\n" +
                        "    For questions or concerns regarding our terms and privacy policies, please contact our customer support at [your contact information].\n\n" +
                        "By clicking \"Agree\" or using our loaning system, you acknowledge that you have read, understood, and agree to the terms and privacy information outlined above."
        );

        termsText.setEditable(false);
        termsText.setLineWrap(true);
        termsText.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(termsText);
        scrollPane.setPreferredSize(new Dimension(800, 60));

        termsPanel.add(scrollPane, BorderLayout.CENTER);

       
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                termsPanel.setVisible(false);
            }
        });
        termsPanel.add(closeButton, BorderLayout.SOUTH);

        termsPanel.setVisible(false); 
        registerPanel.add(termsPanel);

        JLabel termsLabel = new JLabel("Terms and privacy");
        termsLabel.setBounds(583, 370, 150, 30);
        termsLabel.setForeground(Color.BLUE);
        termsLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        termsLabel.addMouseListener(new MouseAdapter() {
            
            public void mouseClicked(MouseEvent e) {
                showTermsWindow(
                        "Terms and Privacy Information for Loaning System\n\n" +
                                "1. User Agreement:\n" +
                                "   By using our loaning system, you agree to abide by the terms and conditions outlined in this agreement.\n\n" +
                                "2. Eligibility:\n" +
                                "   To use the loaning system, you must be at least 18 years old and have the legal capacity to enter into a contract.\n\n" +
                                "3. Loan Approval:\n" +
                                "   Loan approval is subject to eligibility criteria, credit assessment, and our internal policies. We reserve the right to deny a loan application without providing a reason.\n\n" +
                                "4. Interest Rates and Fees:\n" +
                                "   The interest rates and fees associated with loans are clearly communicated during the application process. It is the user's responsibility to review and understand the associated costs.\n\n" +
                                "5. Repayment Terms:\n" +
                                "   Users are required to repay the loan amount according to the agreed-upon schedule. Late payments may result in additional fees.\n\n" +
                                "6. Privacy Policy:\n" +
                                "   We are committed to protecting your privacy. Personal information collected during the loan application process is used solely for the purpose of processing the loan and will not be shared with third parties without your consent.\n\n" +
                                "7. Security Measures:\n" +
                                "   We implement industry-standard security measures to protect your personal and financial information. However, users are advised to take necessary precautions, such as maintaining secure login credentials.\n\n" +
                                "8. Termination of Account:\n" +
                                "   We reserve the right to terminate user accounts for any violation of the terms and conditions or suspicious activities.\n\n" +
                                "9. Changes to Terms:\n" +
                                "   We may update our terms and privacy policies periodically. Users will be notified of any changes, and continued use of the loaning system implies acceptance of the updated terms.\n\n" +
                                "10. Contact Information:\n" +
                                "    For questions or concerns regarding our terms and privacy policies, please contact our customer support at [your contact information].\n\n" +
                                "By clicking \"Agree\" or using our loaning system, you acknowledge that you have read, understood, and agree to the terms and privacy information outlined above."
                );
            }
        });
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
                new LineBorder(new Color(255, 255, 255), 2), 
                new EmptyBorder(borderRadius, borderRadius, borderRadius, borderRadius) 
        );
        button.setBorder(roundedBorder);
        button.setToolTipText("Click to Register");
    }

    private void showTermsWindow(String termsInfo) {
        JFrame termsFrame = new JFrame("Terms and Privacy");
        termsFrame.setSize(400, 300);
        termsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
        JTextArea termsText = new JTextArea(termsInfo);
        termsText.setEditable(false);
        termsText.setLineWrap(true);
        termsText.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(termsText);
        termsFrame.add(scrollPane);

        termsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registration Form");
        JPanel loginPanel = new JPanel();

        SwingUtilities.invokeLater(() -> new RegisterView(frame, loginPanel));
    }
}
