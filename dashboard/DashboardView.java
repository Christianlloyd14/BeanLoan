package book.system.dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DashboardView{
	public DashboardView(JFrame frame, JPanel loginPanel){
		JPanel welcomePanel = new JPanel();
		welcomePanel.setBounds(0, 0, 897, 516);
		welcomePanel.setBackground(Color.WHITE);
		welcomePanel.setLayout(null);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(welcomePanel);
		frame.repaint();
		frame.revalidate();
		
		JButton logoutButton = new JButton("Logout");
		logoutButton.setBounds(265, 330, 100, 30);
		logoutButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.getContentPane().removeAll();
				frame.getContentPane().add(loginPanel);
				frame.repaint();
				frame.revalidate(); 
			}	
		});
		welcomePanel.add(logoutButton);
	}
}