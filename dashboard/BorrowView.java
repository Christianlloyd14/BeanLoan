package book.system.dashboard;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;

public class BorrowView{
	BorrowView(JFrame frame, JPanel welcomePanel, JPanel tabPanel, JPanel borrowPanel){
		

		frame.getContentPane().removeAll();
		frame.getContentPane().add(borrowPanel);
		frame.repaint();
		frame.revalidate();

		

		JPanel bluePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		bluePanel.setBounds(0, 0, 312, 516);
		bluePanel.setBackground(new Color(23, 53, 99));
		borrowPanel.add(bluePanel);


		JLabel titleLabel = new JLabel("Please fill in the form");
		titleLabel.setBounds(350, 100, 400, 30);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
		borrowPanel.add(titleLabel);

		JTextField loanAmountField = new JTextField("Loan Amount");
		loanAmountField.setBounds(350, 150, 250, 30);
		loanAmountField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				loanAmountField.setText("");
			}
		});
		borrowPanel.add(loanAmountField);

		JTextField yearsField = new JTextField("Input how many years");
		yearsField.setBounds(350, 185, 250, 30);
		yearsField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				yearsField.setText("");
			}
		});
		borrowPanel.add(yearsField);

		JTextField fullNameField = new JTextField("Full Name");
		fullNameField.setBounds(350, 220, 250, 30);
		fullNameField.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				fullNameField.setText("");
			}
		});
		borrowPanel.add(fullNameField);

		JTextField numberField = new JTextField("Phone Number");
		numberField.setBounds(350, 255, 250, 30);
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
						double loanAmount = Double.parseDouble(loanAmountField.getText());
						String selectedPurpose = (String) loanPurpose.getSelectedItem();
						String selectedProfession = (String) profession.getSelectedItem();
						String selectedIncome = (String) monthlyIncome.getSelectedItem();

						// Your existing code for writing to the file goes here...
						try (FileWriter fileWriter = new FileWriter("masterlist.txt", true)) {
							String userInformation = fullNameField.getText() + "/" +
									numberField.getText() + "/" +
									yearsField.getText() + "/" +
									selectedPurpose + "/" +
									selectedProfession + "/" +
									selectedIncome + "/" +
									loanAmount + "\n";
							fileWriter.write(userInformation);
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

						// Additional processing or confirmation dialog can go here...

					} catch (NumberFormatException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(frame, "Error processing information", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					// User canceled the confirmation, return to the form
					continueButton.setEnabled(true);
				}
			}
		});


		borrowPanel.add(continueButton);
	}
}