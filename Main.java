
import javax.swing.*;
import book.system.login.LoginController;

public class Main{
	public static void main(String args[]){
		JFrame frame = new JFrame("Loan Application");
		JPanel loginPanel = new JPanel();
		new LoginController(frame, loginPanel);
	}
}