package book.system.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
		violetPanel.setBounds(50, 109, 360, 300);
		violetPanel.setBackground(new Color(73, 30, 192));
		violetPanel.setLayout(null);
		welcomePanel.add(violetPanel); 

		JButton borrowButton = new JButton("Borrow Now");
		borrowButton.setBounds(85, 200, 200, 30);
		violetPanel.add(borrowButton);

		JLabel amountLabel = new JLabel("Loan Amount()");
		amountLabel.setBounds(150, 50, 100,30);
		amountLabel.setForeground( Color.WHITE);
		violetPanel.add(amountLabel);

		JLabel totalLabel = new JLabel("Total Amount");
		totalLabel.setBounds(150, 150, 100, 30);
		totalLabel.setForeground(Color.WHITE);
		violetPanel.add(totalLabel);
		
		JPanel tabPanel = new JPanel();
		tabPanel.setBounds(0, 470, 897, 516);
		tabPanel.setBackground(Color.WHITE);
		tabPanel.setLayout(null);
		welcomePanel.add(tabPanel);
		
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
				JPanel mePanel = new JPanel();
				mePanel.setBounds(0,0,897,470);
				mePanel.setBackground(Color.BLUE);
				mePanel.setLayout(null);
				
				frame.getContentPane().removeAll();
				frame.getContentPane().add(mePanel);
				frame.getContentPane().add(tabPanel);
				frame.repaint();
				frame.revalidate();
				
				JButton logoutButton = new JButton("Logout");
				logoutButton.setBounds(265, 400, 100, 30);
				logoutButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						frame.getContentPane().removeAll();
						frame.getContentPane().add(loginPanel);
						frame.repaint();
						frame.revalidate(); 
					}	
				});
				mePanel.add(logoutButton);
		    }
		});
		tabPanel.add(meTabLabel);

		
	}
}