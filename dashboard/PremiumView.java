package book.system.dashboard;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PremiumView{
	PremiumView(JFrame frame, JPanel welcomePanel, JPanel tabPanel){
		JPanel subscriptionPanel = new JPanel();
		subscriptionPanel.setBounds(0, 0, 897, 516);
		subscriptionPanel.setBackground(Color.WHITE);
		subscriptionPanel.setLayout(null);
		
		JPanel yellowPanel = new JPanel();
		yellowPanel.setBounds(0, 0, 442, 516);
		yellowPanel.setBackground(Color.YELLOW);
		yellowPanel.setLayout(null);
		subscriptionPanel.add(yellowPanel);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(subscriptionPanel);
		frame.repaint();
		frame.revalidate();
		
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

		JButton backButton = new JButton("Back");
		backButton.setBounds(50, 260, 150, 30);
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getContentPane().removeAll();
				frame.getContentPane().add(tabPanel);
				frame.getContentPane().add(welcomePanel);
				frame.repaint();
				frame.revalidate();
			}
		});
		yellowPanel.add(backButton);
	}
}