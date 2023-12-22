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
				
				JLabel welcomeAdminLabel = new JLabel("WELCOME ADMIN!");
				welcomeAdminLabel.setBounds(300, 10, 500, 30);
				welcomeAdminLabel.setFont(new Font("Arial", Font.BOLD, 30));
				adminPanel.add(welcomeAdminLabel);

				JButton viewTransactionButton = new JButton("View Transaction");
				viewTransactionButton.setBounds(10, 100, 150, 30);
				viewTransactionButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JPanel transactionPanel = new JPanel();
						transactionPanel.setBounds(0,0, 897, 516);
						transactionPanel.setBackground(new Color(50, 129, 186));
						transactionPanel.setLayout(null);
						
						frame.getContentPane().removeAll();
						frame.getContentPane().add(transactionPanel);
						frame.repaint();
						frame.revalidate();
						
						JButton backButton = new JButton("Back");
						backButton.setBounds(10,10, 100, 30);
						backButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
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
				
				
				JButton viewUsers = new JButton("View users");
				viewUsers.setBounds(730, 100, 150, 30);
				viewUsers.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						JPanel usersPanel = new JPanel();
						usersPanel.setBounds(0,0, 897, 516);
						usersPanel.setLayout(null);
						usersPanel.setBackground(new Color(50, 129, 186));
						
						frame.getContentPane().removeAll();
						frame.getContentPane().add(usersPanel);
						frame.repaint();
						frame.revalidate();
						
						JButton backButton = new JButton("Back");
						backButton.setBounds(10,10, 100, 30);
						backButton.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								frame.getContentPane().removeAll();
								frame.getContentPane().add(adminPanel);
								frame.repaint();
								frame.revalidate();
							}	
						});
						usersPanel.add(backButton);
						
						
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
}