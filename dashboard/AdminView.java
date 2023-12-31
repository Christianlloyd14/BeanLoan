package book.system.dashboard;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class AdminView {

    private ArrayList < String > pendingRequests;
    public AdminView(JFrame frame, JPanel tabPanel, JPanel welcomePanel, JPanel mePanel, String username, String password, JPanel loginPanel, JPanel bluePanel) {
		
        pendingRequests = new ArrayList < > ();

        JPasswordField passwordField = new JPasswordField();
        Object[] message = {
            "Enter password:",
            passwordField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "ADMIN ONLY!", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION && new String(passwordField.getPassword()).equals("P@ssword!")) {
            JPanel adminPanel = new JPanel();
            adminPanel.setBounds(0, 0, 897, 516);
            adminPanel.setBackground(new Color(50, 129, 186));
            adminPanel.setLayout(null);

            frame.getContentPane().removeAll();
            frame.getContentPane().add(adminPanel);
            frame.repaint();
            frame.revalidate();

            JLabel welcomeAdminLabel = new JLabel("WELCOME ADMIN!");
            welcomeAdminLabel.setBounds(300, 10, 500, 30);
            welcomeAdminLabel.setFont(new Font("Arial", Font.BOLD, 30));
            adminPanel.add(welcomeAdminLabel);

            JButton viewTransactionButton = new JButton("View Transaction");
			viewTransactionButton.setBounds(10, 100, 150, 30);
			viewTransactionButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JPanel transactionPanel = new JPanel();
					transactionPanel.setBounds(0, 0, 897, 516);
					transactionPanel.setBackground(new Color(50, 129, 186));
					transactionPanel.setLayout(new BorderLayout());


					frame.getContentPane().removeAll();
					frame.getContentPane().add(transactionPanel);
					frame.repaint();
					frame.revalidate();

					JButton backButton = new JButton("Back");
					backButton.setBounds(10, 10, 100, 30);
					backButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.getContentPane().removeAll();
							frame.getContentPane().add(adminPanel);
							frame.repaint();
							frame.revalidate();
						}
					});
					transactionPanel.add(backButton);
				

					// Read and display transaction data from the file
					displayTransactionInformation(transactionPanel, frame, username, password, bluePanel);
				}
			});
			adminPanel.add(viewTransactionButton);

            JButton viewUsers = new JButton("View users");
            viewUsers.setBounds(730, 100, 150, 30);
            viewUsers.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JPanel usersPanel = new JPanel();
                    usersPanel.setBounds(0, 0, 897, 516);
                    usersPanel.setLayout(null);
                    usersPanel.setBackground(new Color(50, 129, 186));

                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(usersPanel);
                    frame.repaint();
                    frame.revalidate();

                    JButton backButton = new JButton("Back");
                    backButton.setBounds(10, 10, 100, 30);
                    backButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            frame.getContentPane().removeAll();
                            frame.getContentPane().add(adminPanel);
                            frame.repaint();
                            frame.revalidate();
                        }
                    });
                    usersPanel.add(backButton);
					
				

                    // Read and display user data from the file
                    displayUserInformation(usersPanel, frame);
					
					
                }
            });
            adminPanel.add(viewUsers);

            JButton backButton = new JButton("Back");
            backButton.setBounds(10, 10, 100, 30);
            backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    frame.getContentPane().removeAll();
                    frame.getContentPane().add(tabPanel);
                    frame.getContentPane().add(mePanel);
                    frame.repaint();
                    frame.revalidate();
					
					isValidUser(username, password, frame, loginPanel);
					
                }
            });
            adminPanel.add(backButton);

        } else {
            JOptionPane.showMessageDialog(frame, "Invalid Password");
        }
    }

	private void displayUserInformation(JPanel usersPanel, JFrame frame) {
		try {
			// Read user data from the file and populate an ArrayList
			BufferedReader reader = new BufferedReader(new FileReader("users.dat"));
			String line;
			ArrayList<String> userDataList = new ArrayList<>();

			while ((line = reader.readLine()) != null) {
				userDataList.add(line);
			}

			reader.close();
			

			// Define column names for the JTable
			String[] columnNames = {"Name", "Username", "Password"};

			// Convert the ArrayList to a 2D array for JTable
			String[][] userDataArray = new String[userDataList.size()][];
			for (int i = 0; i < userDataList.size(); i++) {
				userDataArray[i] = userDataList.get(i).split(":");
			}

			// Create a DefaultTableModel for the JTable
			DefaultTableModel tableModel = new DefaultTableModel(userDataArray, columnNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// Make all cells not editable
					return false;
				}
			};

			
			
			// Create a JTable with the DefaultTableModel
			JTable userTable = new JTable(tableModel);

			// Add the JTable to a JScrollPane
			JScrollPane scrollPane = new JScrollPane(userTable);
			scrollPane.setBounds(10, 50, 500, 400);
			usersPanel.add(scrollPane);

			// Add a Delete button
			JButton deleteButton = new JButton("Delete");
			deleteButton.setBounds(530, 50, 100, 30);
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedRow = userTable.getSelectedRow();
					int result = JOptionPane.showConfirmDialog(frame, "Do you really want to delete this use?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if(result== JOptionPane.YES_OPTION){
						if (selectedRow != -1) {
						// Remove the selected row from the DefaultTableModel
						tableModel.removeRow(selectedRow);

						// Update the file with the modified data, excluding the deleted row
						updateUserDataFile(userDataArray, selectedRow, frame);
						
					} else {
						JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
					}
					
					}
					
				}
			});
			usersPanel.add(deleteButton);

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error reading user data from file");
		}
	}

	private void updateUserDataFile(String[][] userDataArray, int rowIndexToDelete, JFrame frame) {
		try {
			// Create a temporary file
			File tempFile = new File("tempUsers.dat");
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			for (int i = 0; i < userDataArray.length; i++) {
				// Skip the row to be deleted
				if (i == rowIndexToDelete) {
					// Delete the corresponding user file in the "Accounts" directory
					String username = userDataArray[i][1];
					String password = userDataArray[i][2];
					File userFile = new File("Accounts/" + username + "-" + password + ".txt");
					if (userFile.exists()) {
						userFile.delete();
					}

					continue;
				}

				// Join the array elements with a colon and write to the temporary file
				writer.write(String.join(":", userDataArray[i]));
				writer.newLine();
			}

			writer.close();

			// Delete the original file
			File originalFile = new File("users.dat");
			if (originalFile.delete()) {
				// Rename the temporary file to the original file
				if (!tempFile.renameTo(originalFile)) {
					JOptionPane.showMessageDialog(frame, "Error renaming temporary file");
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Error deleting original file");
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error updating user data file");
		}
	}

	
	private void displayTransactionInformation(JPanel transactionPanel, JFrame frame, String username, String password, JPanel bluePanel) {
		try {
			// Read transaction data from the file and populate an ArrayList
			BufferedReader reader = new BufferedReader(new FileReader("masterlist.txt"));
			String line;
			ArrayList<String> transactionDataList = new ArrayList<>();

			while ((line = reader.readLine()) != null) {
				transactionDataList.add(line);
			}

			reader.close();

			// Define column names for the JTable
			String[] columnNames = {"Username", "Password", "Fullname", "Contact", "Years", "Purpose", "Profession", "Monthly Income", "Loan Amount"};

			// Convert the ArrayList to a 2D array for JTable
			String[][] transactionDataArray = new String[transactionDataList.size()][];
			for (int i = 0; i < transactionDataList.size(); i++) {
				transactionDataArray[i] = transactionDataList.get(i).split("/");
			}

			// Create a DefaultTableModel for the JTable
			DefaultTableModel tableModel = new DefaultTableModel(transactionDataArray, columnNames) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// Make all cells not editable
					return false;
				}
			};

			// Create a JTable with the DefaultTableModel
			JTable transactionTable = new JTable(tableModel);

			// Update column widths as needed
			TableColumnModel columnModel = transactionTable.getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(100); // Username
			columnModel.getColumn(1).setPreferredWidth(30);  // Password
			columnModel.getColumn(2).setPreferredWidth(100); // Fullname
			columnModel.getColumn(3).setPreferredWidth(30);  // Contact
			columnModel.getColumn(4).setPreferredWidth(15);  // Years
			columnModel.getColumn(5).setPreferredWidth(70); // Purpose
			columnModel.getColumn(6).setPreferredWidth(70); // Profession
			columnModel.getColumn(7).setPreferredWidth(70); // Monthly Income
			columnModel.getColumn(8).setPreferredWidth(10); // Loan Amount

			// Add the JTable to a JScrollPane with both vertical and horizontal scrollbars
			JScrollPane scrollPane = new JScrollPane(transactionTable);
			scrollPane.setBounds(10, 50, 875, 400); // Slightly reduced width to allow space for scrollbar
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			transactionPanel.add(scrollPane);


			JButton deleteButton = new JButton("Delete");
			deleteButton.setBounds(150, 10, 100, 30);
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedRow = transactionTable.getSelectedRow();
					int result = JOptionPane.showConfirmDialog(frame, "Do you really want to delete this transaction?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						if (selectedRow != -1) {
							// Get the username and password from the selected row
							String deletedUsername = transactionDataArray[selectedRow][0];
							String deletedPassword = transactionDataArray[selectedRow][1];

							// Remove the selected row from the DefaultTableModel
							tableModel.removeRow(selectedRow);

							// Notify the user about the declined request
							notifyUsers(deletedUsername, deletedPassword, frame);

							// Update the file with the modified data, excluding the deleted row
							updateTransactionDataFile(transactionDataArray, selectedRow, frame);
						} else {
							JOptionPane.showMessageDialog(frame, "Please select a row to delete.");
						}
					}
				}
			});
			transactionPanel.add(deleteButton);

			JButton approveButton = new JButton("Approve");
    approveButton.setBounds(270, 10, 100, 30);
    approveButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        int selectedRow = transactionTable.getSelectedRow();
        int result = JOptionPane.showConfirmDialog(frame, "Do you really want to approve this transaction?", "Confirmation", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            if (selectedRow != -1) {
                // Get the username, password, and loan amount from the selected row
                String approvedUsername = transactionDataArray[selectedRow][0];
                String approvedPassword = transactionDataArray[selectedRow][1];
                String loanAmountStr = transactionDataArray[selectedRow][8];

                // Convert loan amount string to double
                double loanAmount = Double.parseDouble(loanAmountStr);

                

                // Notify the user about the approved request
                notifyUsers(approvedUsername, approvedPassword, frame, "\nYour loan request has been approved by the admin.\n");

                // Remove the selected row from the DefaultTableModel
                tableModel.removeRow(selectedRow);

                // Update the file with the modified data, excluding the deleted row
                updateTransactionDataFile(transactionDataArray, selectedRow, frame);
				
				
				// Update the user's balance based on the loan amount
                updateBalance(approvedUsername, approvedPassword, selectedRow);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a row to approve.");
            }
        }
    }
});



    transactionPanel.add(approveButton);

		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error reading transaction data from file");
		}
	}

	private void updateTransactionDataFile(String[][] transactionDataArray, int rowIndexToDelete, JFrame frame) {
		try {
			// Create a temporary file
			File tempFile = new File("tempmasterlist.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			for (int i = 0; i < transactionDataArray.length; i++) {
				// Skip the row to be deleted
				if (i == rowIndexToDelete) {
					continue;
				}

				// Join the array elements with a slash and write to the temporary file
				writer.write(String.join("/", transactionDataArray[i]));
				writer.newLine();
			}

			writer.close();

			// Delete the original file
			File originalFile = new File("masterlist.txt");
			if (originalFile.delete()) {
				// Rename the temporary file to the original file
				if (!tempFile.renameTo(originalFile)) {
					JOptionPane.showMessageDialog(frame, "Error renaming temporary file");
				}
			} else {
				JOptionPane.showMessageDialog(frame, "Error deleting original file");
			}
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error updating transaction data file");
		}
	}

	
	public static void notifyUsers(String username, String password, JFrame frame) {
        // Define the Accounts directory path
        String accountsDirectory = "Accounts/";

        // Define the path to the user-specific notification file
        String userNotificationFilePath = accountsDirectory + username + "-" + password + ".txt";

        try (FileWriter writer = new FileWriter(userNotificationFilePath, true)) {
            // Append the notification that the request is declined
            String notification = "\nYour loan request has been declined by the admin.\n";
            writer.write(notification);

            JOptionPane.showMessageDialog(frame, "User notified about declined request.", "Notification", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error notifying user", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	public static void notifyUsers(String username, String password, JFrame frame, String notificationMessage) {
		// Define the Accounts directory path
		String accountsDirectory = "Accounts/";

		// Define the path to the user-specific notification file
		String userNotificationFilePath = accountsDirectory + username + "-" + password + ".txt";

		try (FileWriter writer = new FileWriter(userNotificationFilePath, true)) {
			// Append the custom notification message
			writer.write(notificationMessage);

			JOptionPane.showMessageDialog(frame, "User notified.", "Notification", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Error notifying user", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private boolean isValidUser(String username, String password, JFrame frame, JPanel loginPanel) {
		// Define the Accounts directory path
		String accountsDirectory = "Accounts/";

		// Define the path to the user-specific file
		String userFilePath = accountsDirectory + username + "-" + password + ".txt";

		// Check if the file exists
		File userFile = new File(userFilePath);

		if (userFile.exists()) {
			// The user is valid
			return true;
		} else {
			// The account has been deleted
			JOptionPane.showMessageDialog(frame, "Your account has been deleted by the admin.", "Account Deleted", JOptionPane.INFORMATION_MESSAGE);
			
			frame.getContentPane().removeAll();
			frame.getContentPane().add(loginPanel);
			frame.repaint();
			frame.revalidate();
			
			return false;
		}
	}
	
private void updateBalance(String username, String password, int selectedRow) {
    // Define the Accounts directory path
    String accountsDirectory = "Accounts/";

    // Define the path to the user-specific file
    String userFilePath = accountsDirectory + username + "-" + password + ".txt";

    System.out.println("Updating balance for user: " + username);
    System.out.println("User file path: " + userFilePath);

    try {
        // Read all lines from the masterlist.txt file
        List<String> masterListLines = Files.readAllLines(Paths.get("masterlist.txt"), StandardCharsets.UTF_8);

        // Check if the selected row exists
        if (selectedRow >= 0 && selectedRow < masterListLines.size()) {
            // Get the loan amount from the selected row
            String loanAmountStr = masterListLines.get(selectedRow).split("/")[8];

            // Convert loan amount string to double
            double loanAmount = Double.parseDouble(loanAmountStr);

            // Read all lines from the user's file
            List<String> userFileLines = Files.readAllLines(Paths.get(userFilePath), StandardCharsets.UTF_8);

            // Check if there are at least three lines (assuming the balance is on the third line)
            if (userFileLines.size() >= 3) {
                // Update the balance by setting it to the loan amount
                userFileLines.set(2, "Balance: " + loanAmount);

                // Write the modified lines back to the user's file
                Files.write(Paths.get(userFilePath), userFileLines, StandardCharsets.UTF_8);

                System.out.println("Balance updated successfully.");
            } else {
                System.out.println("Error: User file does not have enough lines.");
            }
        } else {
            System.out.println("Error: Selected row is not valid.");
        }
    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
        System.out.println("Error updating balance.");
    }
}



}