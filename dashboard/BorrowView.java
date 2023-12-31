package book.system.dashboard;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.text.*;



public class BorrowView {
	private JLabel notificationLabel1;
    public BorrowView(JFrame frame, JPanel welcomePanel, JPanel tabPanel, JPanel borrowPanel, String username, String password) {	
        frame.getContentPane().removeAll();
        frame.getContentPane().add(borrowPanel);
        frame.repaint();
        frame.revalidate();

        JPanel bluePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        bluePanel.setBounds(0, 0, 312, 516);
        bluePanel.setBackground(new Color(50, 129, 186));
        borrowPanel.add(bluePanel);
		
        JLabel titleLabel = new JLabel("Please fill in the form");
        titleLabel.setBounds(350, 100, 400, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
		titleLabel.setForeground(Color.WHITE);
        borrowPanel.add(titleLabel);
		
		JLabel amountLabel = new JLabel("you can only borrow up to 20,000");
		amountLabel.setBounds(630, 150, 500, 30);
		amountLabel.setForeground(Color.WHITE);
		borrowPanel.add(amountLabel);

        JTextField loanAmountField = new JTextField("Loan Amount");
		loanAmountField.setBounds(350, 150, 250, 30);
		loanAmountField.setDocument(new PlainDocument() {
			public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
				if (str == null) return;

				if ((getLength() + str.length()) <= 5 && str.matches("\\d+")) {
					super.insertString(offset, str, attr);
				}
			}
		});
		loanAmountField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				loanAmountField.setText("");
			}
		});
		borrowPanel.add(loanAmountField);

		
		JLabel yearsLabel = new JLabel("limit of 20 years");
		yearsLabel.setBounds(630, 185, 400, 30);
		yearsLabel.setForeground(Color.WHITE);
		borrowPanel.add(yearsLabel);

		JTextField yearsField = new JTextField("Input how many years");
		yearsField.setBounds(350, 185, 250, 30);
		yearsField.setDocument(new PlainDocument() {
			public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
				if (str == null) return;

				if ((getLength() + str.length()) <= 2 && str.matches("\\d+")) {
					super.insertString(offset, str, attr);
				}
			}
		});
		yearsField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				yearsField.setText("");
			}
		});
		borrowPanel.add(yearsField);
		
		JLabel fullnameLabel = new JLabel("Lastname, Firstname, M.I");
		fullnameLabel.setBounds(630, 220, 500, 30);
		fullnameLabel.setForeground(Color.WHITE);
		borrowPanel.add(fullnameLabel);

		JTextField fullNameField = new JTextField("Full Name");
		fullNameField.setBounds(350, 220, 250, 30);
		fullNameField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				fullNameField.setText("");
			}
		});
		borrowPanel.add(fullNameField);
		
		JLabel numberLabel = new JLabel("XXXX-XXX-XXXX");
		numberLabel.setBounds(630, 255, 300, 30);
		numberLabel.setForeground(Color.WHITE);
		borrowPanel.add(numberLabel);

		JTextField numberField = new JTextField("Phone Number");
		numberField.setBounds(350, 255, 250, 30);
		numberField.setDocument(new PlainDocument() {
			public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
				if (str == null) return;

				if ((getLength() + str.length()) <= 11 && str.matches("\\d+")) {
					super.insertString(offset, str, attr);
				}
			}
		});
		numberField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				numberField.setText("");
			}
		});
		borrowPanel.add(numberField);

		String[] purposeOfLoanChoice = {
			"Purpose of Loan",
			"Business Startup",
			"Medical",
			"Loans",
			"Others"
		};
		JComboBox<String> loanPurpose = new JComboBox<>(purposeOfLoanChoice);
		loanPurpose.setBounds(350, 290, 250, 30);
		borrowPanel.add(loanPurpose);

		String[] professionChoice = {
			"Profession",
			"OFW",
			"Unemployed",
			"Government Employee",
			"Self-employed",
			"Others"
		};
		JComboBox<String> profession = new JComboBox<>(professionChoice);
		profession.setBounds(350, 325, 250, 30);
		borrowPanel.add(profession);

		String[] incomeChoices = {
			"Monthly Income",
			"Less than ₱10,000",
			"₱10,000 - ₱20,000",
			"₱20,000 - ₱30,000",
			"₱30,000 - ₱50,000",
			"More than ₱50,000"
		};
		JComboBox<String> monthlyIncome = new JComboBox<>(incomeChoices);
		monthlyIncome.setBounds(350, 360, 250, 30);
		borrowPanel.add(monthlyIncome);

        JButton continueButton = new JButton("Submit");
        continueButton.setBounds(500, 450, 100, 30);
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                continueButton.setEnabled(false);

                // Validate if the loan amount is a valid numeric value
                try {
                    double loanAmount = Double.parseDouble(loanAmountField.getText());

					// Validate if the loan amount is within the allowed range (up to 20,000)
					if (loanAmount > 20000) {
						JOptionPane.showMessageDialog(frame, "Loan amount cannot exceed 20,000.", "Input Error", JOptionPane.ERROR_MESSAGE);
						continueButton.setEnabled(true);
						return;
					}
					
                    // Validate if any field is empty
                    if (loanAmountField.getText().isEmpty() || fullNameField.getText().isEmpty() ||
                            numberField.getText().isEmpty() || loanPurpose.getSelectedIndex() == 0 ||
                            profession.getSelectedIndex() == 0 || monthlyIncome.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        continueButton.setEnabled(true);
                        return;
                    }

                    // Display a confirmation dialog with user input
                    String confirmationMessage = "Loan Amount: " + loanAmountField.getText() + "\n" +
                            "Full Name: " + fullNameField.getText() + "\n" +
                            "Phone Number: " + numberField.getText() + "\n" +
                            "Purpose of Loan: " + loanPurpose.getSelectedItem() + "\n" +
                            "Profession: " + profession.getSelectedItem() + "\n" +
                            "Monthly Income: " + monthlyIncome.getSelectedItem();

                    int confirmDialogResult = JOptionPane.showConfirmDialog(frame, confirmationMessage, "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (confirmDialogResult == JOptionPane.YES_OPTION) {
                        // Proceed with processing the information

                        try {
							
                            try (FileWriter fileWriter = new FileWriter("masterlist.txt", true)) {
								String userInformation = username + "/" +
										password + "/" +
										fullNameField.getText() + "/" +
										numberField.getText() + "/" +
										yearsField.getText() + "/" +
										loanPurpose.getSelectedItem() + "/" +
										profession.getSelectedItem() + "/" +
										monthlyIncome.getSelectedItem() + "/" +
										loanAmount + "\n";
								fileWriter.write(userInformation);
								JOptionPane.showMessageDialog(frame, "Information submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
							} catch (IOException ex) {
								ex.printStackTrace();
								JOptionPane.showMessageDialog(frame, "Error writing to file", "Error", JOptionPane.ERROR_MESSAGE);
							}


                            // Clear the fields after submission
                            loanAmountField.setText("");
                            fullNameField.setText("");
                            numberField.setText("");
                            yearsField.setText("");
                            loanPurpose.setSelectedIndex(0);
                            profession.setSelectedIndex(0);
                            monthlyIncome.setSelectedIndex(0);
							
							notificationPrompt(username, password, frame);
							
							
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(frame, "Invalid loan amount. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                            continueButton.setEnabled(true); // Enable the button to allow the user to correct the input
                            return; // Exit the actionPerformed method to prevent further processing
                        } finally {
                            continueButton.setEnabled(true); // Ensure the button is re-enabled even if an exception occurs
                        }
                    } else {
                        // User canceled the confirmation, return to the form
                        continueButton.setEnabled(true);
                    }
                } catch (NumberFormatException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Invalid loan amount. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
                    continueButton.setEnabled(true); // Enable the button to allow the user to correct the input
                }
            }
        });

        borrowPanel.add(continueButton);
    }
	
	private void notificationPrompt(String username, String password, JFrame frame) {
        // Define the Accounts directory path
        String accountsDirectory = "Accounts/";

        // Create the directory if it doesn't exist
        File accountsDir = new File(accountsDirectory);
        if (!accountsDir.exists()) {
            accountsDir.mkdir();
        }

        // Define the path to the user-specific notification file
        String notificationFilePath = accountsDirectory + username + "-" + password + ".txt";

        try {
            // Read existing content from the file
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(notificationFilePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }
            }

            // Append the new notification for the user
            String newNotification = "\nYou have a pending request";
            content.append(newNotification);

            // Write the updated content back to the file
            try (FileWriter writer = new FileWriter(notificationFilePath)) {
                writer.write(content.toString());
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error reading/writing to file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
