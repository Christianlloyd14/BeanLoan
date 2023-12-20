package book.system.dashboard;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class AdminView{
	
	private ArrayList < String > pendingRequests;
	public AdminView(JFrame frame, JPanel tabPanel, JPanel welcomePanel, JPanel mePanel){
		
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

				JButton viewTransactionButton = new JButton("View Transaction");
				viewTransactionButton.setBounds(100, 100, 150, 30);
				viewTransactionButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JPanel transactionPanel = new JPanel();
						transactionPanel.setBounds(50, 50, 400, 400);
						transactionPanel.setBackground(new Color(50, 129, 186));
						transactionPanel.setLayout(new BorderLayout()); // Use BorderLayout

						JTextArea requestsTextArea = new JTextArea();
						try {
							// Read content from masterlist.txt
							BufferedReader reader = new BufferedReader(new FileReader("masterlist.txt"));
							String line;
							while ((line = reader.readLine()) != null) {
								requestsTextArea.append(line + "\n");
							}
							reader.close();
						} catch (IOException ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(frame, "No Transaction");
						}

						JScrollPane scrollPane = new JScrollPane(requestsTextArea);
						scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // Add a vertical scrollbar

						requestsTextArea.setEditable(false);

						JButton rejectButton = new JButton("Reject");

						JButton approveButton = new JButton("Approve");
						approveButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								// Perform actions when approve button is clicked
								// For example, remove the approved request from pendingRequests
								// Update UI accordingly
								pendingRequests.clear();
								requestsTextArea.setText("");
								if (pendingRequests.isEmpty()) {
									requestsTextArea.setText("No transactions at the moment.");
								} else {
									// Display the pending requests
									for (String request: pendingRequests) {
										requestsTextArea.append(request + "\n");
									}
								}
								JOptionPane.showMessageDialog(frame, "All Requests Approved!");

								// Notify the user in the notificationLabel
								JLabel notificationLabel = new JLabel("Notification");
								notificationLabel.setBounds(750, 10, 100, 40);
								notificationLabel.addMouseListener(new MouseAdapter() {
									public void mouseClicked(MouseEvent e) {
										JPanel notificationPanel = new JPanel();
										notificationPanel.setBounds(0, 0, 897, 516);
										notificationPanel.setBackground(new Color(50, 129, 186));
										notificationPanel.setLayout(null);

										frame.getContentPane().removeAll();
										frame.getContentPane().add(notificationPanel);
										frame.repaint();
										frame.revalidate();

										JLabel notificationText = new JLabel("Your request has been approved!");
										notificationText.setBounds(10, 100, 400, 30);
										notificationPanel.add(notificationText);

										JButton backButton = new JButton("Back");
										backButton.setBounds(10, 10, 100, 30);
										backButton.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												frame.getContentPane().removeAll();
												frame.getContentPane().add(welcomePanel);
												frame.repaint();
												frame.revalidate();
											}
										});
										notificationPanel.add(backButton);
										
										JButton clearButton = new JButton("Clear");
										clearButton.setBounds(10, 200, 100, 30);
										clearButton.addActionListener(new ActionListener(){
											public void actionPerformed(ActionEvent e){
												notificationText.setText("");
											}	
										});
										notificationPanel.add(clearButton);
										
									}
								});
								welcomePanel.add(notificationLabel);
							}
						});

						// Add buttons to the top of the panel
						JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
						buttonPanel.add(approveButton);
						buttonPanel.add(rejectButton);

						transactionPanel.add(buttonPanel, BorderLayout.NORTH);
						transactionPanel.add(scrollPane, BorderLayout.CENTER);

						frame.getContentPane().removeAll();
						frame.getContentPane().add(transactionPanel);
						frame.repaint();
						frame.revalidate();

						JButton backButton = new JButton("Back");
						backButton.setBounds(10, 10, 100, 30);
						backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
						backButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								frame.getContentPane().removeAll();
								frame.getContentPane().add(adminPanel);
								frame.repaint();
								frame.revalidate();
							}
						});
						transactionPanel.add(backButton);

					}
				});

				adminPanel.add(viewTransactionButton);

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
}