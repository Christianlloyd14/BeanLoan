package book.system.dashboard;

import javax.swing.*;

public class DashboardController{
	public DashboardController(JFrame frame, JPanel loginPanel){
		new DashboardView(frame, loginPanel);
	}
}