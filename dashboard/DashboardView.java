package book.system.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;


class DashboardView{
	public DashboardView(JFrame frame, JPanel loginPanel){
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBounds(0, 0, 897, 516);
		welcomePanel.setBackground(new Color(50, 129, 186));
		welcomePanel.setLayout(null);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(welcomePanel);
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



		JButton borrowButton = new JButton("Borrow Now");
		borrowButton.setBounds(85, 200, 200, 30);
		borrowButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JPanel borrowPanel = new JPanel();
				borrowPanel.setBounds(0,0, 897,516);
				borrowPanel.setBackground(Color.BLACK);
				borrowPanel.setLayout(null);
				
				frame.getContentPane().removeAll();
				frame.getContentPane().add(borrowPanel);
				frame.repaint();
				frame.revalidate();
				
				
				JLabel backLabel = new JLabel("Back");
				backLabel.setBounds(10,10,100,40);
				backLabel.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						frame.getContentPane().removeAll();
						frame.getContentPane().add(welcomePanel);
						frame.repaint();
						frame.revalidate();
					}	
				});
				borrowPanel.add(backLabel);
				
				String[] purposeOfLoanChoice = {"Purpose of Loan", "Business Startup", "Medical", "Loans", "Others"};
				JComboBox<String> loanPurpose = new JComboBox<>(purposeOfLoanChoice);
				loanPurpose.setBounds(10, 50, 150, 30);
				borrowPanel.add(loanPurpose);
				
				String[] professionChoice = {"Profession", "OFW", "Unemployed", "Government Employee", "Self-employed", "Others"};
				JComboBox<String> profession = new JComboBox<>(professionChoice);
				profession.setBounds(10, 90, 150, 30);
				borrowPanel.add(profession);
				
			


			}	
		});
		violetPanel.add(borrowButton);

		JLabel amountLabel = new JLabel("Loan Amount(₱)");
		amountLabel.setBounds(150, 50, 100, 30);
		amountLabel.setForeground(Color.WHITE);
		violetPanel.add(amountLabel);


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
				backLabel.setBounds(10,10,100,40);
				backLabel.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						frame.getContentPane().removeAll();
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
        violetPanel.add(couponLabel);

		JLabel creditScoreLabel = new JLabel("Credit Score Boost");
		creditScoreLabel.setBounds(450, 120, 200, 30);
		creditScoreLabel.setForeground(Color.WHITE);
		violetPanel.add(creditScoreLabel);

		JLabel premiumUserLabel = new JLabel("Premium User");
		premiumUserLabel.setBounds(450, 160, 200, 30);
		premiumUserLabel.setForeground(Color.WHITE);
		violetPanel.add(premiumUserLabel);




		
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
		
		JLabel billTabLabel = new JLabel("Bill");
		billTabLabel.setBounds(430, 7, 100, 40);
		billTabLabel.setFont(new Font("Arial", Font.BOLD, 25));
		billTabLabel.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
				
				JPanel billPanel = new JPanel();
				billPanel.setBounds(0,0, 897,470);
				billPanel.setBackground(Color.BLUE);
				billPanel.setLayout(null);
				
				frame.getContentPane().removeAll();
				frame.getContentPane().add(billPanel);
				frame.getContentPane().add(tabPanel);
				frame.repaint();
				frame.revalidate();
				
				JLabel billLabel = new JLabel("Bill");
				billLabel.setBounds(430, 10, 300, 40);
				billLabel.setFont(new Font("Arial", Font.BOLD, 25));
				billPanel.add(billLabel);
				
				JPanel greenPanel = new JPanel();
				greenPanel.setBounds(0,85, 897, 470);
				greenPanel.setBackground(Color.GREEN);
				billPanel.add(greenPanel);
				

				JLabel currentLabel = new JLabel("Current");
				currentLabel.setBounds(50, 50, 100, 40);
				currentLabel.setFont(new Font("Arial", Font.BOLD, 25));
				currentLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						greenPanel.removeAll();

						JLabel noLoansLabel = new JLabel("No Loans");
						noLoansLabel.setBounds(420, 100, 100, 40);
						greenPanel.add(noLoansLabel);
						
						JButton borrowNowButton = new JButton("Borrow Now");
						borrowNowButton.setBounds(390, 250, 100, 30);
						greenPanel.add(borrowNowButton);

						frame.repaint();
						frame.revalidate();
					}
				});
				billPanel.add(currentLabel);

				JLabel historyLabel = new JLabel("History");
				historyLabel.setBounds(750, 50, 100, 40);
				historyLabel.setFont(new Font("Arial", Font.BOLD, 25));
				historyLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						greenPanel.removeAll();

						JLabel noHistoryLabel = new JLabel("No History");
						noHistoryLabel.setBounds(420, 100, 100, 40);
						greenPanel.add(noHistoryLabel);
						
						JButton borrowNowButton = new JButton("Borrow Now");
						borrowNowButton.setBounds(390, 250, 100, 30);
						greenPanel.add(borrowNowButton);

						frame.repaint();
						frame.revalidate();
					}
				});
				billPanel.add(historyLabel);
				
		    }
		});
		tabPanel.add(billTabLabel);
		
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
				
				JLabel couponLabelInMe = new JLabel("Coupon");
				couponLabelInMe.setBounds(10, 10, 100, 30);
				bluePanel.add(couponLabelInMe);

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