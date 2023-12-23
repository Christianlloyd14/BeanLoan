package book.system.dashboard;

import javax.swing.*;

public class DashboardController{
	
	public DashboardController(JFrame frame, JPanel loginPanel, String username){
		new DashboardView(frame, loginPanel, username);
	}
	
}