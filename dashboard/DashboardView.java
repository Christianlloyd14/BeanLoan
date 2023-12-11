package book.system.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.io.*;
import java.util.ArrayList;

class DashboardView {
    private ArrayList < String > pendingRequests;
    private JPanel tabPanel;

    public DashboardView(JFrame frame, JPanel loginPanel, String username) {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBounds(0, 0, 897, 516);
        welcomePanel.setBackground(new Color(50, 129, 186));
        welcomePanel.setLayout(null);

        pendingRequests = new ArrayList < > ();

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
        loaningAppLabel.setBounds(10, 10, 300, 40);
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
                        frame.getContentPane().add(tabPanel);
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

                String[] purposeOfLoanChoice = {
                    "Purpose of Loan",
                    "Business Startup",
                    "Medical",
                    "Loans",
                    "Others"
                };
                JComboBox < String > loanPurpose = new JComboBox < > (purposeOfLoanChoice);
                loanPurpose.setBounds(10, 170, 150, 30);
                borrowPanel.add(loanPurpose);

                String[] professionChoice = {
                    "Profession",
                    "OFW",
                    "Unemployed",
                    "Government Employee",
                    "Self-employed",
                    "Others"
                };
                JComboBox < String > profession = new JComboBox < > (professionChoice);
                profession.setBounds(10, 210, 150, 30);
                borrowPanel.add(profession);

                // Add JComboBox for Monthly Income
                String[] incomeChoices = {
                    "Monthly Income",
                    "Less than ₱10,000",
                    "₱10,000 - ₱20,000",
                    "₱20,000 - ₱30,000",
                    "₱30,000 - ₱50,000",
                    "More than ₱50,000"
                };
                JComboBox < String > monthlyIncome = new JComboBox < > (incomeChoices);
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
                                // Append information to the masterlist.txt file
                                String selectedPurpose = (String) loanPurpose.getSelectedItem();
                                String selectedProfession = (String) profession.getSelectedItem();
                                String selectedIncome = (String) monthlyIncome.getSelectedItem();

                                FileWriter fileWriter = new FileWriter("masterlist.txt", true); // Append to the file
                                // Arrange user information in a single line with separators
                                String userInformation = fullNameField.getText() + "/" +
                                    numberField.getText() + "/" +
                                    selectedPurpose + "/" +
                                    selectedProfession + "/" +
                                    selectedIncome + "/" +
                                    loanAmount + "\n";
                                fileWriter.write(userInformation);
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
                                String requestInfo = "Full Name: " + fullNameField.getText() + ", Loan Amount: " + loanAmount;
                                pendingRequests.add(requestInfo);

                                // Notify the user in the notificationLabel
                                JLabel notificationLabel = new JLabel("Notification");
                                notificationLabel.setBounds(750, 10, 100, 40);
                                notificationLabel.addMouseListener(new MouseAdapter() {
                                    public void mouseClicked(MouseEvent e) {
                                        JPanel notificationPanel = new JPanel();
                                        notificationPanel.setBounds(0, 0, 897, 516);
                                        notificationPanel.setBackground(Color.GREEN);
                                        notificationPanel.setLayout(null);

                                        frame.getContentPane().removeAll();
                                        frame.getContentPane().add(notificationPanel);
                                        frame.repaint();
                                        frame.revalidate();

                                        JLabel notificationText;
                                        if (pendingRequests.isEmpty()) {
                                            notificationText = new JLabel("No pending requests.");
                                        } else {
                                            notificationText = new JLabel("You have a pending request.");
                                        }
                                        notificationText.setBounds(10, 100, 400, 30);
                                        notificationPanel.add(notificationText);

                                        JButton backButton = new JButton("Back");
                                        backButton.setBounds(10, 10, 100, 30);
                                        backButton.addActionListener(new ActionListener() {
                                            public void actionPerformed(ActionEvent e) {
                                                frame.getContentPane().removeAll();
                                                frame.getContentPane().add(tabPanel);
                                                frame.getContentPane().add(welcomePanel);
                                                frame.repaint();
                                                frame.revalidate();
                                            }
                                        });
                                        notificationPanel.add(backButton);
                                    }
                                });
                                welcomePanel.add(notificationLabel);

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
		

        JLabel loanArea = new JLabel("Borrow up to 20,000");
        loanArea.setBounds(70, 80, 400, 30);
        loanArea.setForeground(Color.WHITE);
        loanArea.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 25));
        violetPanel.add(loanArea);

        JLabel totalLabel = new JLabel("So what are you waiting for?");
        totalLabel.setBounds(110, 150, 500, 30);
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
        couponPanel.setBounds(0, 0, 897, 516);
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

				JPanel voucherPanel = new JPanel();
				voucherPanel.setBounds(10, 100, 200, 100);
				voucherPanel.setBackground(Color.GRAY);
				voucherPanel.setLayout(null);
				couponPanel.add(voucherPanel);
				
				JLabel couponLabel = new JLabel("20% off");
				couponLabel.setBounds(5, 55, 100, 30);
				voucherPanel.add(couponLabel);

				JButton useButton = new JButton("Use");
				useButton.setBounds(10, 10, 70, 30);
				useButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(frame, "Obtained 20% off voucher");
						voucherPanel.setVisible(false);
						frame.revalidate(); // Revalidate the container
						frame.repaint();    // Repaint the container
					}
				});
				voucherPanel.add(useButton);

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
		
		
        JPanel subscriptionPanel = new JPanel();
        subscriptionPanel.setBounds(0, 0, 897, 516);
        subscriptionPanel.setBackground(Color.WHITE);
        subscriptionPanel.setLayout(null);
		
		JPanel yellowPanel = new JPanel();
        yellowPanel.setBounds(0, 0, 442, 516);
        yellowPanel.setBackground(Color.YELLOW);
        yellowPanel.setLayout(null);
		subscriptionPanel.add(yellowPanel);

        

        JLabel premiumUserLabel = new JLabel("Premium User");
        premiumUserLabel.setBounds(450, 110, 200, 30);
        premiumUserLabel.setForeground(Color.WHITE);
        premiumUserLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(subscriptionPanel);
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


        // Add subscription details to the subscription panel
        JLabel subscriptionLabel = new JLabel("Available Subscriptions:");
        subscriptionLabel.setFont(new Font("CALIBRE", Font.BOLD, 25));
        subscriptionLabel.setBounds(30, 30, 300, 30);
        yellowPanel.add(subscriptionLabel);

        // Radio buttons for subscription options
        JRadioButton oneMonthSubscriptionRadio = new JRadioButton("1 Month Subscription: ₱999");
        oneMonthSubscriptionRadio.setBounds(50, 80, 300, 30);
        yellowPanel.add(oneMonthSubscriptionRadio);

        JRadioButton threeMonthsSubscriptionRadio = new JRadioButton("3 Months Subscription: ₱2,499");
        threeMonthsSubscriptionRadio.setBounds(50, 110, 300, 30);
        yellowPanel.add(threeMonthsSubscriptionRadio);

        JRadioButton sixMonthsSubscriptionRadio = new JRadioButton("6 Months Subscription: ₱4,499");
        sixMonthsSubscriptionRadio.setBounds(50, 140, 300, 30);
        yellowPanel.add(sixMonthsSubscriptionRadio);

        JRadioButton oneYearSubscriptionRadio = new JRadioButton("1 Year Subscription: ₱8,999");
        oneYearSubscriptionRadio.setBounds(50, 170, 300, 30);
        yellowPanel.add(oneYearSubscriptionRadio);

        // Button group to ensure only one radio button is selected at a time
        ButtonGroup subscriptionButtonGroup = new ButtonGroup();
        subscriptionButtonGroup.add(oneMonthSubscriptionRadio);
        subscriptionButtonGroup.add(threeMonthsSubscriptionRadio);
        subscriptionButtonGroup.add(sixMonthsSubscriptionRadio);
        subscriptionButtonGroup.add(oneYearSubscriptionRadio);

        JLabel userStatusLabel = new JLabel();
        userStatusLabel.setBounds(120, 130, 500, 30);
        userStatusLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton subscribeButton = new JButton("Subscribe");
        subscribeButton.setBounds(50, 220, 150, 30);
        subscribeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (oneMonthSubscriptionRadio.isSelected()) {
                    // Handle 1 Month Subscription
                    JOptionPane.showMessageDialog(frame, "Subscribed to 1 Month Subscription");
                    userStatusLabel.setText("VIP User");
                } else if (threeMonthsSubscriptionRadio.isSelected()) {
                    // Handle 3 Months Subscription
                    JOptionPane.showMessageDialog(frame, "Subscribed to 3 Months Subscription");
                    userStatusLabel.setText("VIP User");
                } else if (sixMonthsSubscriptionRadio.isSelected()) {
                    // Handle 6 Months Subscription
                    JOptionPane.showMessageDialog(frame, "Subscribed to 6 Months Subscription");
                    userStatusLabel.setText("VIP User");
                } else if (oneYearSubscriptionRadio.isSelected()) {
                    // Handle 1 Year Subscription
                    JOptionPane.showMessageDialog(frame, "Subscribed to 1 Year Subscription");
                    userStatusLabel.setText("VIP User");
                } else {
                    // No subscription selected
                    JOptionPane.showMessageDialog(frame, "Please select a subscription");
                    userStatusLabel.setText("Regular User");
                }
            }
        });
        yellowPanel.add(subscribeButton);



        // Back button to go back to the home page
        JButton backButton = new JButton("Back");
        backButton.setBounds(50, 260, 150, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Navigate back to the home page
                frame.getContentPane().removeAll();
                frame.getContentPane().add(tabPanel);
                frame.getContentPane().add(welcomePanel);
                frame.repaint();
                frame.revalidate();
            }
        });
        yellowPanel.add(backButton);

        // You can add multiple subscription options here
        JLabel oneMonthSubscriptionLabel = new JLabel("1 Month Subscription: ₱999");
        oneMonthSubscriptionLabel.setBounds(50, 80, 200, 30);
        yellowPanel.add(oneMonthSubscriptionLabel);

        JLabel threeMonthsSubscriptionLabel = new JLabel("3 Months Subscription: ₱2,499");
        threeMonthsSubscriptionLabel.setBounds(50, 110, 200, 30);
        yellowPanel.add(threeMonthsSubscriptionLabel);

        JLabel sixMonthsSubscriptionLabel = new JLabel("6 Months Subscription: ₱4,499");
        sixMonthsSubscriptionLabel.setBounds(50, 140, 200, 30);
        yellowPanel.add(sixMonthsSubscriptionLabel);

        JLabel oneYearSubscriptionLabel = new JLabel("1 Year Subscription: ₱8,999");
        oneYearSubscriptionLabel.setBounds(50, 170, 200, 30);
        yellowPanel.add(oneYearSubscriptionLabel);

        JLabel benefitsPremiumLabel = new JLabel("Benefits of Premium Account:");
        benefitsPremiumLabel.setFont(new Font("CALIBRE", Font.BOLD, 25));
        benefitsPremiumLabel.setBounds(470, 30, 400, 30);
        subscriptionPanel.add(benefitsPremiumLabel);

        JLabel fasterLoanApprovalLabel = new JLabel("Faster Loan Approval:");
        fasterLoanApprovalLabel.setFont(new Font("CALIBRE", Font.BOLD, 15));
        fasterLoanApprovalLabel.setBounds(470, 80, 200, 30);
        subscriptionPanel.add(fasterLoanApprovalLabel);

        JLabel fasterLoanApprovalText = new JLabel("• Premium users may enjoy expedited processing of their loan applications, leading to quicker approval times.");
        fasterLoanApprovalText.setBounds(475, 100, 700, 30);
        subscriptionPanel.add(fasterLoanApprovalText);

        JLabel lowerInterestRatesLabel = new JLabel("Lower Interest Rates:");
        lowerInterestRatesLabel.setFont(new Font("CALIBRE", Font.BOLD, 15));
        lowerInterestRatesLabel.setBounds(470, 130, 200, 30);
        subscriptionPanel.add(lowerInterestRatesLabel);

        JLabel lowerInterestRatesText = new JLabel("• Premium users could be eligible for reduced interest rates on their loans, making borrowing more cost-effective for them.");
        lowerInterestRatesText.setBounds(475, 150, 700, 30);
        subscriptionPanel.add(lowerInterestRatesText);

        JLabel priorityCustomerSupportLabel = new JLabel("Priority Customer Support:");
        priorityCustomerSupportLabel.setFont(new Font("CALIBRE", Font.BOLD, 15));
        priorityCustomerSupportLabel.setBounds(470, 180, 200, 30);
        subscriptionPanel.add(priorityCustomerSupportLabel);

        JLabel priorityCustomerSupportText = new JLabel("• Premium users could receive priority customer support, ensuring that their queries and concerns are addressed promptly and efficiently.");
        priorityCustomerSupportText.setBounds(475, 200, 700, 30);
        subscriptionPanel.add(priorityCustomerSupportText);

        JLabel exclusiveLoanProductsLabel = new JLabel("Exclusive Loan Products:");
        exclusiveLoanProductsLabel.setFont(new Font("CALIBRE", Font.BOLD, 15));
        exclusiveLoanProductsLabel.setBounds(470, 230, 200, 30);
        subscriptionPanel.add(exclusiveLoanProductsLabel);

        JLabel exclusiveLoanProductsText = new JLabel("• Premium users may have access to exclusive loan products or special financial instruments that are not available to standard users.");
        exclusiveLoanProductsText.setBounds(475, 250, 700, 30);
        subscriptionPanel.add(exclusiveLoanProductsText);

        JLabel flexibleRepaymentTermsLabel = new JLabel("Flexible Repayment Terms:");
        flexibleRepaymentTermsLabel.setFont(new Font("CALIBRE", Font.BOLD, 15));
        flexibleRepaymentTermsLabel.setBounds(470, 280, 200, 30);
        subscriptionPanel.add(flexibleRepaymentTermsLabel);

        JLabel flexibleRepaymentTermsText = new JLabel("• Premium users might be offered more flexible repayment terms, allowing them to customize their loan repayment schedules to better suit their financial situation.");
        flexibleRepaymentTermsText.setBounds(475, 300, 700, 30);
        subscriptionPanel.add(flexibleRepaymentTermsText);

        JLabel financialEducationLabel = new JLabel("Financial Education Resources:");
        financialEducationLabel.setFont(new Font("CALIBRE", Font.BOLD, 15));
        financialEducationLabel.setBounds(470, 330, 250, 30);
        subscriptionPanel.add(financialEducationLabel);

        JLabel financialEducationText = new JLabel("• Premium users could gain access to educational resources and financial planning tools to help them make informed decisions about their loans and personal finances.");
        financialEducationText.setBounds(475, 350, 700, 30);
        subscriptionPanel.add(financialEducationText);

        JLabel specialPromotionsLabel = new JLabel("Special Promotions and Rewards:");
        specialPromotionsLabel.setFont(new Font("CALIBRE", Font.BOLD, 15));
        specialPromotionsLabel.setBounds(470, 380, 250, 30);
        subscriptionPanel.add(specialPromotionsLabel);

        JLabel specialPromotionsText = new JLabel("• Premium users could be eligible for exclusive promotions, discounts, or rewards programs as a token of appreciation for their loyalty.");
        specialPromotionsText.setBounds(475, 400, 700, 30);
        subscriptionPanel.add(specialPromotionsText);

        JLabel financialHealthCheckupsLabel = new JLabel("Financial Health Check-ups:");
        financialHealthCheckupsLabel.setFont(new Font("CALIBRE", Font.BOLD, 15));
        financialHealthCheckupsLabel.setBounds(470, 430, 250, 30);
        subscriptionPanel.add(financialHealthCheckupsLabel);

        JLabel financialHealthCheckupsText = new JLabel("• Premium users may receive periodic financial health assessments or consultations to ensure that their financial goals align with their borrowing activities.");
        financialHealthCheckupsText.setBounds(475, 450, 700, 30);
        subscriptionPanel.add(financialHealthCheckupsText);


        premiumUserLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                frame.getContentPane().removeAll();
                frame.getContentPane().add(subscriptionPanel);
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
				
				JLabel welcomeUserLabel = new JLabel("Welcome " + username + "!");
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
                                                            frame.getContentPane().add(tabPanel);
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
        });
        tabPanel.add(meTabLabel);

    }

}