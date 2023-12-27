package book.system.dashboard;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.*;
import java.util.ArrayList;

public class AdminView {

    private ArrayList < String > pendingRequests;
    public AdminView(JFrame frame, JPanel tabPanel, JPanel welcomePanel, JPanel mePanel) {

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
					transactionPanel.setLayout(null);

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
					displayTransactionInformation(transactionPanel, frame);
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
					continue;
				}

				// Join the array elements with a comma and write to the temporary file
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
	
	private void displayTransactionInformation(JPanel transactionPanel, JFrame frame) {
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
			String[] columnNames = {"Fullname", "Contact", "Years", "Purpose", "Profession", "Monthly Income", "Loan Amount"};

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
			columnModel.getColumn(0).setPreferredWidth(100); // Fullname
			columnModel.getColumn(1).setPreferredWidth(30); // Contact
			columnModel.getColumn(2).setPreferredWidth(15);  // Years
			columnModel.getColumn(3).setPreferredWidth(70); // Purpose
			columnModel.getColumn(4).setPreferredWidth(70); // Profession
			columnModel.getColumn(5).setPreferredWidth(70); // Monthly Income
			columnModel.getColumn(6).setPreferredWidth(10); // Loan Amount

			// Add the JTable to a JScrollPane with an extended width
			JScrollPane scrollPane = new JScrollPane(transactionTable);
			scrollPane.setBounds(10, 50, 885, 400); // Extend the width
			transactionPanel.add(scrollPane);

			// Add a Delete button (similar to the users' panel)
			JButton deleteButton = new JButton("Delete");
			deleteButton.setBounds(150, 10, 100, 30);
			deleteButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedRow = transactionTable.getSelectedRow();
					int result = JOptionPane.showConfirmDialog(frame, "Do you really want to delete this transaction?", "Confirmation", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.YES_OPTION) {
						if (selectedRow != -1) {
							// Remove the selected row from the DefaultTableModel
							tableModel.removeRow(selectedRow);

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

				// Join the array elements with a comma and write to the temporary file
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

}