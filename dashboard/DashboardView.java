package book.system.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class DashboardView {

    public DashboardView(JFrame frame, JPanel loginPanel, String username) {
        JPanel welcomePanel = new JPanel();
        welcomePanel.setBounds(0, 0, 897, 516);
        welcomePanel.setBackground(new Color(50, 129, 186));
        welcomePanel.setLayout(null);

		
        JPanel tabPanel = new JPanel();
        tabPanel.setBounds(0, 470, 897, 516);
        tabPanel.setBackground(Color.WHITE);
        tabPanel.setLayout(null);
		welcomePanel.add(tabPanel);
		
		
        JPanel violetPanel = new JPanel();
        violetPanel.setBounds(100, 109, 700, 300);
        violetPanel.setBackground(new Color(73, 30, 192));
        violetPanel.setLayout(null);
        welcomePanel.add(violetPanel);
		
		
		frame.getContentPane().removeAll();
        frame.getContentPane().add(welcomePanel);
        frame.repaint();
        frame.revalidate();
        
		
		JButton exitButton = new JButton("X");
		exitButton.setBounds(847, 10, 40, 20);
		exitButton.setFont(new Font("Arial", Font.BOLD, 12));
		Insets margin = new Insets(2, 2, 2, 2);
		exitButton.setMargin(margin);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		welcomePanel.add(exitButton);
		
		
		JLabel welcomeUserLabel = new JLabel("Welcome " + username + "!");
		welcomeUserLabel.setBounds(10, 10, 500, 40);
		welcomeUserLabel.setFont(new Font("Arial", Font.BOLD, 30));
		welcomePanel.add(welcomeUserLabel);

        JButton borrowButton = new JButton("Borrow Now");
		borrowButton.setBounds(85, 200, 200, 30);
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton backButton = new JButton("Back");
				JPanel borrowPanel = new JPanel();
				borrowPanel.setBounds(0, 0, 897, 516);
				borrowPanel.setBackground(new Color(50, 129, 186));
				borrowPanel.setLayout(null);
				
				
				backButton.setBounds(350, 450, 100, 30);
				backButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().removeAll();
						frame.getContentPane().add(welcomePanel);
						frame.repaint();
						frame.revalidate();
					}
				});
				borrowPanel.add(backButton);
				new BorrowView(frame, welcomePanel, tabPanel, borrowPanel);
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

        JLabel specialOfferLabel = new JLabel("Special Offer:");
        specialOfferLabel.setBounds(450, 50, 200, 30);
        specialOfferLabel.setFont(new Font("Arial", Font.BOLD + Font.ITALIC, 15));
        specialOfferLabel.setForeground(Color.WHITE);
        violetPanel.add(specialOfferLabel);

        JPanel couponPanel = new JPanel();
        couponPanel.setBounds(0, 0, 897, 516);
        couponPanel.setBackground(new Color(50, 129, 186));
        couponPanel.setLayout(null);

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
				backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
			
			public void mouseEntered(MouseEvent e) {
				couponLabel.setForeground(Color.YELLOW);
            }
            public void mouseExited(MouseEvent e) {
				couponLabel.setForeground(Color.WHITE);
            }
		});
        violetPanel.add(couponLabel);
		
		
		
		JLabel userStatusLabel = new JLabel();
		userStatusLabel.setBounds(110,120,100,30);
		
        JLabel premiumUserLabel = new JLabel("Premium User");
        premiumUserLabel.setBounds(450, 110, 200, 30);
        premiumUserLabel.setForeground(Color.WHITE);
        premiumUserLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
				new PremiumView(frame, welcomePanel, tabPanel, userStatusLabel);
            }

            public void mouseEntered(MouseEvent e) {
                premiumUserLabel.setForeground(Color.YELLOW);
            }

            public void mouseExited(MouseEvent e) {
                premiumUserLabel.setForeground(Color.WHITE);
            }

        });
        violetPanel.add(premiumUserLabel);
		
		
		

        JLabel homeTabLabel = new JLabel("Home");
        homeTabLabel.setBounds(50, 7, 100, 40);
        homeTabLabel.setFont(new Font("Arial", Font.BOLD, 25));
		homeTabLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		meTabLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        meTabLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new MeView(frame, loginPanel, welcomePanel, tabPanel, username, userStatusLabel);
            }
        });
        tabPanel.add(meTabLabel);

    }

}