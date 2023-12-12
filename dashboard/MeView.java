package book.system.dashboard;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class MeView{
    private ArrayList < String > pendingRequests;
	public MeView(JFrame frame, JPanel loginPanel, JPanel welcomePanel, JPanel tabPanel, String username, JLabel userStatusLabel){
		
        pendingRequests = new ArrayList < > ();
		
		JPanel mePanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g.create();
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				Shape circle = new Ellipse2D.Double(10, 50, 100, 100); // Adjusted position and size
				g2d.setColor(Color.BLUE);
				g2d.fill(circle);
				g2d.dispose();
			}
		};
		mePanel.setBounds(0, 0, 897, 470);
		mePanel.setLayout(null);
		
		
		JPanel bluePanel = new JPanel();
		bluePanel.setBounds(0, 160, 897, 516);
		bluePanel.setBackground(new Color(50, 129, 186));
		bluePanel.setLayout(null);
		mePanel.add(bluePanel);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(mePanel);
		frame.getContentPane().add(tabPanel);
		frame.repaint();
		frame.revalidate();

		
		JLabel welcomeUserLabel = new JLabel(username);
		welcomeUserLabel.setBounds(120, 90, 500, 40);
		welcomeUserLabel.setFont(new Font("Arial", Font.BOLD, 30));
		mePanel.add(welcomeUserLabel);
		mePanel.add(userStatusLabel);

		JLabel settingsLabel = new JLabel("Settings");
		settingsLabel.setBounds(765, 100, 100, 30);
		settingsLabel.setForeground(Color.BLACK);
		settingsLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				JPanel settingsPanel = new JPanel();
				settingsPanel.setBounds(0, 0, 897, 516);
				settingsPanel.setBackground(new Color(50, 129, 186));
				settingsPanel.setLayout(null);

				frame.getContentPane().removeAll();
				frame.getContentPane().add(settingsPanel);
				frame.repaint();
				frame.revalidate();

				JLabel aboutUsLabel = new JLabel("About Us");
				aboutUsLabel.setBounds(10, 70, 100, 30);
				aboutUsLabel.setForeground(Color.BLACK);
				aboutUsLabel.setFont(new Font("Arial", Font.ITALIC, 12));
				aboutUsLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						JPanel aboutUsPanel = new JPanel();
						aboutUsPanel.setBounds(0, 0, 897, 516);
						aboutUsPanel.setBackground(Color.BLUE);
						aboutUsPanel.setLayout(null);

						JFrame frame = new JFrame();

						JOptionPane.showMessageDialog(frame, "BeanLoan is operated by WeFund Lending Corp\n\n" +
							"Company Registration No. CS123456789 Certificate of Authority No.2844\n\n" +
							"BeanLoan is one of the Fintech platforms available in the Philippines providing financial services through the use of our \n" +
							"application available for use by all Filipino citizens.\n\n" +
							"BeanLoan is a safe and secured financial technology product that provides confidentiality of clients' information.\n\n" +
							"We choose BeanLoan as our product name because we believe BeanLoan is one family with one heart that gives Filipinos a \n" +
							"helping hand to their financial needs. Like the Filipino term of 'Bayanihan', we are here to help in times of needs.\n\n" +
							"Our mission is to reach and provide convenient and excellent financial services to help all Filipinos in times of needs.\n\n" +
							"Our vision is to become one of the top Fintech service providers in the Philippines with the use of our application,\n" +
							" which is available to all Filipinos nationwide.");

						frame.getContentPane().removeAll();
						frame.getContentPane().add(aboutUsPanel);
						frame.repaint();
						frame.revalidate();
					}
				});

				JLabel customerSupLabel = new JLabel("Customer Support");
				customerSupLabel.setBounds(10, 100, 100, 30);
				customerSupLabel.setForeground(Color.BLACK);
				customerSupLabel.setFont(new Font("Arial", Font.ITALIC, 12));

				settingsPanel.add(aboutUsLabel);
				settingsPanel.add(customerSupLabel);

				JLabel backLabel1 = new JLabel("Back");
				backLabel1.setBounds(10, 10, 100, 40);
				backLabel1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						frame.getContentPane().removeAll();
						frame.getContentPane().add(tabPanel);
						frame.getContentPane().add(mePanel);
						frame.repaint();
						frame.revalidate();
					}
				});
				settingsPanel.add(backLabel1);

			}
		});

		settingsLabel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				settingsLabel.setForeground(Color.BLACK);
			}

			public void mouseDragged(MouseEvent e) {
				// Optional: Handle mouse dragging if needed
			}
		});
		settingsLabel.addMouseListener(new MouseAdapter() {
			public void mouseExited(MouseEvent e) {
				settingsLabel.setForeground(Color.BLACK);
			}
		});
		mePanel.add(settingsLabel);
		

		JLabel adminLabel = new JLabel("Are you admin?");
		adminLabel.setBounds(760, 10, 200, 30);
		adminLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				JPasswordField passwordField = new JPasswordField();
				Object[] message = {
					"Enter password:",
					passwordField
				};

				int option = JOptionPane.showConfirmDialog(frame, message, "Enter Password", JOptionPane.OK_CANCEL_OPTION);

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
		});
		mePanel.add(adminLabel);

		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(765, 250, 100, 30);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(loginPanel);
				frame.repaint();
				frame.revalidate();
			}
		});
		bluePanel.add(logoutButton);
		
		
	}
}