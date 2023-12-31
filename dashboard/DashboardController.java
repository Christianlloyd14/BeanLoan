package book.system.dashboard;

import javax.swing.*;

public class DashboardController{
	
	public DashboardController(JFrame frame, JPanel loginPanel, String username, String password){
		new DashboardView(frame, loginPanel, username, password);
	}
}