package book.system.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.*;
import java.util.ArrayList;



class DashboardView{
		private ArrayList<String> pendingRequests;
		private JPanel tabPanel;
		public DashboardView(JFrame frame, JPanel loginPanel){
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBounds(0, 0, 897, 516);
		welcomePanel.setBackground(new Color(50, 129, 186));
		welcomePanel.setLayout(null);

		pendingRequests = new ArrayList<>();

		tabPanel = new JPanel();
        tabPanel.setBounds(0, 470, 897, 516);
        tabPanel.setBackground(Color.WHITE);
        tabPanel.setLayout(null);
        welcomePanel.add(tabPanel);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(welcomePanel);
		frame.getContentPane().add(tabPanel);
		frame.repaint();
		frame.revalidate();
		
		JLabel loaningAppLabel = new JLabel("LOANING APP");
		loaningAppLabel.setBounds(10,10, 300,40);
		loaningAppLabel.setFont(new Font("Arial", Font.BOLD, 25));
		welcomePanel.add(loaningAppLabel);




		JPanel violetPanel = new JPanel();
		violetPanel.setBounds(100, 109, 700, 300);
		violetPanel.setBackground(new Color(73, 30, 192));
		violetPanel.setLayout(null);
		welcomePanel.add(violetPanel);



		// Inside the borrowButton.addActionListener block
		JButton borrowButton = new JButton("Borrow Now");
		borrowButton.setBounds(85, 200, 200, 30);
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel borrowPanel = new JPanel();
				borrowPanel.setBounds(0, 0, 897, 516);
				borrowPanel.setBackground(Color.BLACK);
				borrowPanel.setLayout(null);

				frame.getContentPane().removeAll();
				frame.getContentPane().add(borrowPanel);
				frame.repaint();
				frame.revalidate();

				JLabel backLabel = new JLabel("Back");
				backLabel.setBounds(10, 10, 100, 40);
				backLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						frame.getContentPane().removeAll();
						frame.getContentPane().add(welcomePanel);
						frame.repaint();
						frame.revalidate();
					}
				});
				borrowPanel.add(backLabel);

				JButton exitButton = new JButton("X");
				exitButton.setBounds(847, 10, 40, 20); // Adjust the position and size as needed
				exitButton.setFont(new Font("Arial", Font.BOLD, 12)); // Adjust font as needed

				// Adjusting the text inside the button by setting margins
				Insets margin = new Insets(2, 2, 2, 2); // Adjust margins as needed
				exitButton.setMargin(margin);

				exitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0); // Exit the application
					}
				});
				welcomePanel.add(exitButton);

				JTextField loanAmountField = new JTextField("Loan Amount");
				loanAmountField.setBounds(10, 50, 150, 30);
				loanAmountField.addFocusListener(new FocusAdapter() {
					public void focusGained(FocusEvent e) {
						loanAmountField.setText("");
					}
				});
				borrowPanel.add(loanAmountField);

				JTextField fullNameField = new JTextField("Full Name");
				fullNameField.setBounds(10, 90, 150, 30);
				fullNameField.addFocusListener(new FocusAdapter() {
					public void focusGained(FocusEvent e) {
						fullNameField.setText("");
					}
				});
				borrowPanel.add(fullNameField);

				JTextField numberField = new JTextField("Phone Number");
				numberField.setBounds(10, 130, 150, 30);
				numberField.addFocusListener(new FocusAdapter() {
					public void focusGained(FocusEvent e) {
						numberField.setText("");
					}
				});
				borrowPanel.add(numberField);

				String[] purposeOfLoanChoice = {"Purpose of Loan", "Business Startup", "Medical", "Loans", "Others"};
				JComboBox<String> loanPurpose = new JComboBox<>(purposeOfLoanChoice);
				loanPurpose.setBounds(10, 170, 150, 30);
				borrowPanel.add(loanPurpose);

				String[] professionChoice = {"Profession", "OFW", "Unemployed", "Government Employee", "Self-employed", "Others"};
				JComboBox<String> profession = new JComboBox<>(professionChoice);
				profession.setBounds(10, 210, 150, 30);
				borrowPanel.add(profession);

				// Add JComboBox for Monthly Income
				String[] incomeChoices = {"Monthly Income", "Less than ₱10,000", "₱10,000 - ₱20,000", "₱20,000 - ₱30,000", "₱30,000 - ₱50,000", "More than ₱50,000"};
				JComboBox<String> monthlyIncome = new JComboBox<>(incomeChoices);
				monthlyIncome.setBounds(10, 250, 150, 30);
				borrowPanel.add(monthlyIncome);

				JButton continueButton = new JButton("Submit");
				continueButton.setBounds(10, 290, 150, 30);
				continueButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						continueButton.setEnabled(false);
						// Validate if any field is empty
						if (loanAmountField.getText().isEmpty() || fullNameField.getText().isEmpty() ||
								numberField.getText().isEmpty() || loanPurpose.getSelectedIndex() == 0 ||
								profession.getSelectedIndex() == 0 || monthlyIncome.getSelectedIndex() == 0) {
							JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
						} else {
							// Proceed with processing the information
							try {
								double loanAmount = Double.parseDouble(loanAmountField.getText());
								// Save information to a file named after the user's full name
								String fullName = fullNameField.getText();
								String number = numberField.getText();
								String selectedPurpose = (String) loanPurpose.getSelectedItem();
								String selectedProfession = (String) profession.getSelectedItem();
								String selectedIncome = (String) monthlyIncome.getSelectedItem();

								FileWriter fileWriter = new FileWriter(fullName + "_" + number + ".txt");
								fileWriter.write("Loan Amount: " + loanAmount + "\n");
								fileWriter.write("Full Name: " + fullName + "\n");
								fileWriter.write("Phone Number: " + number + "\n");
								fileWriter.write("Purpose of Loan: " + selectedPurpose + "\n");
								fileWriter.write("Profession: " + selectedProfession + "\n");
								fileWriter.write("Monthly Income: " + selectedIncome + "\n");
								fileWriter.close();
								

								// Show a confirmation message
								JOptionPane.showMessageDialog(frame, "Your information has been sent to our management. Please wait for approval.", "Submission Successful", JOptionPane.INFORMATION_MESSAGE);

								// Clear the fields after submission
								loanAmountField.setText("");
								fullNameField.setText("");
								numberField.setText("");
								loanPurpose.setSelectedIndex(0);
								profession.setSelectedIndex(0);
								monthlyIncome.setSelectedIndex(0);

								// Add the user's request to the pendingRequests list
								String requestInfo = "Full Name: " + fullName + ", Loan Amount: " + loanAmount;
								pendingRequests.add(requestInfo);

								// Go back to the home page
								frame.getContentPane().removeAll();
								frame.getContentPane().add(welcomePanel);
								frame.getContentPane().add(tabPanel);
								frame.repaint();
								frame.revalidate();

							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(frame, "Please enter a valid number for the loan amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
							} catch (IOException ex) {
								ex.printStackTrace();
								JOptionPane.showMessageDialog(frame, "Error saving information");
							}
						}
					}
				});
				borrowPanel.add(continueButton);
			}
		});
		violetPanel.add(borrowButton);















		JLabel amountLabel = new JLabel("Loan Amount(₱)");
		amountLabel.setBounds(150, 50, 100, 30);
		amountLabel.setForeground(Color.WHITE);
		violetPanel.add(amountLabel);
		
		JLabel loanArea = new JLabel(" 20,000");
		loanArea.setBounds(150 ,100, 100, 30);
		loanArea.setForeground(Color.WHITE);
		loanArea.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
		violetPanel.add(loanArea);

		JLabel totalLabel = new JLabel("Total Amount(₱)");
		totalLabel.setBounds(150, 150, 100, 30);
		totalLabel.setForeground(Color.WHITE);
		violetPanel.add(totalLabel);

		JLabel commentLabel = new JLabel("Amount will be increased by repeat borrowing");
		commentLabel.setBounds(60, 250, 300, 30);
		commentLabel.setForeground(Color.GRAY);
		violetPanel.add(commentLabel);


		JLabel specialOfferLabel = new JLabel("Special Offer");
		specialOfferLabel.setBounds(450, 50, 200, 30);
		specialOfferLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
		specialOfferLabel.setForeground(Color.WHITE);
		violetPanel.add(specialOfferLabel);

		
				
		JPanel couponPanel = new JPanel();
		couponPanel.setBounds(0, 0, 897,516);
		couponPanel.setBackground(Color.BLACK);
		couponPanel.setLayout(null);
		
		
		
		JPanel tabPanel = new JPanel();
		tabPanel.setBounds(0, 470, 897, 516);
		tabPanel.setBackground(Color.WHITE);
		tabPanel.setLayout(null);
		welcomePanel.add(tabPanel);

		JLabel couponLabel = new JLabel("Coupon Offer");
			couponLabel.setBounds(450, 80, 100, 30);
			couponLabel.setForeground(Color.WHITE);
			couponLabel.addMouseListener(new MouseAdapter() {
    			public void mouseClicked(MouseEvent e) {
        			frame.getContentPane().removeAll();
        			frame.getContentPane().add(couponPanel);
        			frame.repaint();
        			frame.revalidate();

        JLabel backLabel = new JLabel("Back");
    	    backLabel.setBounds(10, 10, 100, 40);
        	backLabel.addMouseListener(new MouseAdapter() {
            	public void mouseClicked(MouseEvent e) {
                	frame.getContentPane().removeAll();
                	frame.getContentPane().add(tabPanel);
	                frame.getContentPane().add(welcomePanel);
    	            frame.repaint();
        	        frame.revalidate();
            }
        });
        couponPanel.add(backLabel);

        JButton useButton = new JButton("Use");
        useButton.setBounds(10, 170, 70, 30);
        couponPanel.add(useButton);
    }
});

couponLabel.addMouseMotionListener(new MouseMotionAdapter() {
    public void mouseMoved(MouseEvent e) {
        couponLabel.setForeground(Color.YELLOW);
    }

    public void mouseDragged(MouseEvent e) {
        // Optional: Handle mouse dragging if needed
    }
});

couponLabel.addMouseListener(new MouseAdapter() {
    public void mouseExited(MouseEvent e) {
        couponLabel.setForeground(Color.WHITE);
    }
});

violetPanel.add(couponLabel);


		JLabel premiumUserLabel = new JLabel("Premium User");
		premiumUserLabel.setBounds(450, 110, 200, 30);
		premiumUserLabel.setForeground(Color.WHITE);
		premiumUserLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(welcomePanel);
				frame.repaint();
				frame.revalidate();
			}

		public void mouseEntered(MouseEvent e) {

			premiumUserLabel.setForeground(Color.YELLOW);	
		}

		public void mouseExited(MouseEvent e) {

			premiumUserLabel.setForeground(Color.WHITE);
		}

	});
		violetPanel.add(premiumUserLabel);



			JLabel notificationLabel = new JLabel("notification");
			notificationLabel.setBounds(750,10,100,40);
			notificationLabel.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					frame.getContentPane().removeAll();
					frame.getContentPane().add(welcomePanel);
					frame.repaint();
					frame.revalidate();
				 }

    public void mouseEntered(MouseEvent e) {
       
        notificationLabel.setForeground(Color.YELLOW); 
    }

    public void mouseExited(MouseEvent e) {

        notificationLabel.setForeground(Color.BLACK);
    }
});
			
				welcomePanel.add(notificationLabel);




		
		JLabel homeTabLabel = new JLabel("Home");
		homeTabLabel.setBounds(50, 7, 100, 40);
		homeTabLabel.setFont(new Font("Arial", Font.BOLD, 25));
		homeTabLabel.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
				
				frame.getContentPane().removeAll();
				frame.getContentPane().add(tabPanel);
				frame.getContentPane().add(welcomePanel);
				frame.repaint();
				frame.revalidate();
		    }
		});
		tabPanel.add(homeTabLabel);
		
	
	
	
	
	
	
	
	
	
	
	
	
		
		JLabel meTabLabel = new JLabel("Me");
		meTabLabel.setBounds(800, 7, 100, 40);
		meTabLabel.setFont(new Font("Arial", Font.BOLD, 25));
		meTabLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
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

				frame.getContentPane().removeAll();
				frame.getContentPane().add(mePanel);
				frame.getContentPane().add(tabPanel);
				frame.repaint();
				frame.revalidate();
				
				JPanel bluePanel = new JPanel();
				bluePanel.setBounds(0, 160, 897, 516);
				bluePanel.setBackground(Color.BLUE);
				bluePanel.setLayout(null);
				mePanel.add(bluePanel);
				
				JLabel adminLabel = new JLabel("Are you admin?");
				adminLabel.setBounds(760, 10, 200, 30);
				adminLabel.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						
						JPasswordField passwordField = new JPasswordField();
						Object[] message = {"Enter password:", passwordField};

						int option = JOptionPane.showConfirmDialog(frame, message, "Enter Password", JOptionPane.OK_CANCEL_OPTION);
						
						if (option == JOptionPane.OK_OPTION && new String(passwordField.getPassword()).equals("P@ssword!")) {
							JPanel adminPanel = new JPanel();
							adminPanel.setBounds(0,0,897,516);
							adminPanel.setBackground(Color.WHITE);
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
								transactionPanel.setBackground(Color.GREEN);
								transactionPanel.setLayout(new BorderLayout());  // Use BorderLayout

								JTextArea requestsTextArea = new JTextArea();
								for (String request : pendingRequests) {
									requestsTextArea.append(request + "\n");
								}
								JScrollPane scrollPane = new JScrollPane(requestsTextArea);

								JButton approveButton = new JButton("Approve");
								approveButton.setBounds(100, 100, 100, 100);
								transactionPanel.add(approveButton);
								JButton rejectButton = new JButton("Reject");
								rejectButton.setBounds(100, 100, 100, 100);
								transactionPanel.add(rejectButton);

								approveButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										// Perform actions when approve button is clicked
										// For example, remove the approved request from pendingRequests
										// Update UI accordingly
										pendingRequests.clear();
										requestsTextArea.setText("");
										JOptionPane.showMessageDialog(frame, "All Requests Approved!");
									}
								});

								rejectButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										// Perform actions when reject button is clicked
										// For example, remove the rejected request from pendingRequests
										// Update UI accordingly
										pendingRequests.clear();
										requestsTextArea.setText("");
										JOptionPane.showMessageDialog(frame, "All Requests Rejected!");
									}
								});

								transactionPanel.add(scrollPane, BorderLayout.CENTER);
								transactionPanel.add(approveButton, BorderLayout.SOUTH);
								transactionPanel.add(rejectButton, BorderLayout.SOUTH);

								frame.getContentPane().removeAll();
								frame.getContentPane().add(transactionPanel);
								frame.repaint();
								frame.revalidate();
							}
						});
						adminPanel.add(viewTransactionButton);


						}
						else{
							JOptionPane.showMessageDialog(frame, "Invalid Password");
						}
					}	
				});
				mePanel.add(adminLabel);

				JButton logoutButton = new JButton("Logout");
				logoutButton.setBounds(10, 50, 100, 30);
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
		});
		tabPanel.add(meTabLabel);

		
	}
}
