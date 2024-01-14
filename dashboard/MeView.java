package book.system.dashboard;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.geom.Ellipse2D;
import book.system.user.UserModel;
import java.nio.file.*;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.util.stream.*;



public class MeView{
	
	public MeView(JFrame frame, JPanel loginPanel, JPanel welcomePanel, JPanel tabPanel, String username, String password){
	
		
		JPanel mePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Shape circle = new Ellipse2D.Double(10, 50, 100, 100);
                g2d.setColor(new Color(73, 30, 192));
                g2d.fill(circle);

                // Add the first letter of the username inside the circle
                g2d.setColor(Color.WHITE); // Set text color to white
                g2d.setFont(new Font("Arial", Font.BOLD, 50)); // Adjust font and size
                FontMetrics fm = g2d.getFontMetrics();
                String firstLetter = username.substring(0, 1).toUpperCase(); // Get the first letter and convert to uppercase
                int x = (int) (circle.getBounds2D().getCenterX() - fm.stringWidth(firstLetter) / 2);
                int y = (int) (circle.getBounds2D().getCenterY() + fm.getAscent() / 2);
                g2d.drawString(firstLetter, x, y);

                g2d.dispose();
            }
        };
		mePanel.setBounds(0, 0, 897, 470);
		mePanel.setBackground(new Color(50, 129, 186));
		mePanel.setLayout(null);
		
		
		
        JPanel bluePanel = new JPanel();
        bluePanel.setBounds(0, 170, 897, 300);
        bluePanel.setBackground(new Color(73, 30, 192));
		mePanel.add(bluePanel);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(mePanel);
		frame.getContentPane().add(tabPanel);
		frame.repaint();
		frame.revalidate();

		
		JLabel welcomeUserLabel = new JLabel(username);
		welcomeUserLabel.setBounds(120, 90, 500, 40);
		welcomeUserLabel.setFont(new Font("Arial", Font.BOLD, 30));
		welcomeUserLabel.setForeground(Color.WHITE);
		mePanel.add(welcomeUserLabel);
		
		displayBalance(username, password, bluePanel);
		
		
		JLabel notificationLabel = new JLabel("Notification");
		notificationLabel.setBounds(600, 20, 100, 30);
		notificationLabel.setForeground(Color.WHITE);
		notificationLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JPanel notificationPanel = new JPanel();
				notificationPanel.setBounds(0, 0, 897, 516);
				notificationPanel.setBackground(new Color(50, 129, 186));
				notificationPanel.setLayout(null);

				frame.getContentPane().removeAll();
				frame.getContentPane().add(notificationPanel);
				frame.repaint();
				frame.revalidate();

				                DefaultListModel<String> listModel = new DefaultListModel<>();
                JList<String> notificationList = new JList<>(listModel);
                JScrollPane scrollPane = new JScrollPane(notificationList);
                scrollPane.setBounds(10, 100, 400, 300);
                notificationPanel.add(scrollPane);

                // Populate the list model with notifications starting from the 5th line
                String fileName = username + "-" + password + ".txt";
                Path filePath = Paths.get(ACCOUNTS_DIRECTORY, fileName);

                try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
                    List<String> notifications = lines.skip(4) // Skip the first four lines
                            .collect(Collectors.toList());

                    for (String notification : notifications) {
                        listModel.addElement(notification);
                    }
                } catch (IOException ex) {
                    // Handle file reading exception
                    ex.printStackTrace();
                }


				JButton deleteButton = new JButton("Delete");
				deleteButton.setBounds(420, 100, 100, 30);
				deleteButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int selectedIndex = notificationList.getSelectedIndex();
						if (selectedIndex != -1) {
							// Call the new method to delete the selected notification
							deleteNotification(username, password, selectedIndex);

							// Update the GUI to reflect the changes
							listModel.remove(selectedIndex);
						}
					}
				});
				notificationPanel.add(deleteButton);

				JButton backButton = new JButton("Back");
				backButton.setBounds(10, 10, 100, 30);
				backButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.getContentPane().removeAll();
						frame.getContentPane().add(mePanel);
						frame.getContentPane().add(tabPanel);
						frame.repaint();
						frame.revalidate();
					}
				});
				notificationPanel.add(backButton);
			}
		});
		mePanel.add(notificationLabel);

		
		JLabel settingsLabel = new JLabel("Settings");
		settingsLabel.setBounds(830, 20, 100, 30);
		settingsLabel.setForeground(Color.WHITE);
		settingsLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				JPanel settingsPanel = new JPanel();
				settingsPanel.setBounds(290, 50, 300, 400);
				settingsPanel.setBackground(new Color(59, 89, 152));
				settingsPanel.setLayout(null);
				settingsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add this line to set a border


				frame.getContentPane().removeAll();
				frame.getContentPane().add(settingsPanel);
				frame.getContentPane().add(tabPanel);
				frame.getContentPane().add(mePanel);
				frame.repaint();
				frame.revalidate();
				
				JLabel welcomeUserLabel = new JLabel("Have a nice day " + username + "!");
				welcomeUserLabel.setBounds(40, 40, 500, 30);
				welcomeUserLabel.setFont(new Font("Arial", Font.BOLD, 25));
				settingsPanel.add(welcomeUserLabel);
				
				
				JButton backButton = new JButton("Back");
				backButton.setBounds(50, 150, 200, 30);
				backButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						frame.getContentPane().removeAll();
						frame.getContentPane().add(tabPanel);
						frame.getContentPane().add(mePanel);
						frame.repaint();
						frame.revalidate();
					}	
				});
				settingsPanel.add(backButton);
				
				JButton optionButton = new JButton("Option");
				optionButton.setBounds(50, 190, 200, 30);
				optionButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						new AdminView(frame, tabPanel, welcomePanel, mePanel, username, password, loginPanel, bluePanel);
					}	
				});
				settingsPanel.add(optionButton);
				
				JButton logoutButton = new JButton("Logout");
				logoutButton.setBounds(50, 230, 200, 30);
				logoutButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int result = JOptionPane.showConfirmDialog(frame, "Do you really want to logout?", "Confirmation", JOptionPane.YES_NO_OPTION);
						if(result == JOptionPane.YES_OPTION){
							frame.getContentPane().removeAll();
							frame.getContentPane().add(loginPanel);
							frame.repaint();
							frame.revalidate();
						}
					}
				});
				settingsPanel.add(logoutButton);
				
				
				JLabel customerSupLabel = new JLabel("Customer Support");
				customerSupLabel.setBounds(10, 350, 100, 30);
				customerSupLabel.setForeground(Color.BLACK);
				customerSupLabel.setFont(new Font("Arial", Font.ITALIC, 12));
				settingsPanel.add(customerSupLabel);
				
				
				JLabel aboutUsLabel = new JLabel("About Us");
				aboutUsLabel.setBounds(240, 350, 100, 30);
				aboutUsLabel.setForeground(Color.BLACK);
				aboutUsLabel.setFont(new Font("Arial", Font.ITALIC, 12));
				aboutUsLabel.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						JPanel aboutUsPanel = new JPanel();
						aboutUsPanel.setBounds(0, 0, 897, 516);
						aboutUsPanel.setBackground(Color.BLUE);
						aboutUsPanel.setLayout(null);

						JFrame frame = new JFrame();

						JOptionPane.showMessageDialog(frame, "BeanLoan is operated by WeFund Lending Corp\n\n" +
							"Company Registration No. CS123456789 Certificate of Authority No.2844\n\n" +
							"BeanLoan is one of the Fintech platforms available in the Philippines providing financial services through the use of our \n" +
							"application available for use by all Filipino citizens.\n\n" +
							"BeanLoan is a safe and secured financial technology product that provides confidentiality of clients' information.\n\n" +
							"We choose BeanLoan as our product name because we believe BeanLoan is one family with one heart that gives Filipinos a \n" +
							"helping hand to their financial needs. Like the Filipino term of 'Bayanihan', we are here to help in times of needs.\n\n" +
							"Our mission is to reach and provide convenient and excellent financial services to help all Filipinos in times of needs.\n\n" +
							"Our vision is to become one of the top Fintech service providers in the Philippines with the use of our application,\n" +
							" which is available to all Filipinos nationwide.");

						frame.getContentPane().removeAll();
						frame.getContentPane().add(aboutUsPanel);
						frame.repaint();
						frame.revalidate();
					}
				});
				settingsPanel.add(aboutUsLabel);
				
				
			}
		});
		mePanel.add(settingsLabel);
		
		displayVIPStatus(username, password, mePanel);
		
	}
	
	private static final String ACCOUNTS_DIRECTORY = "database";

	public static void displayBalance(String username, String password, JPanel bluePanel) {
		String fileName = username + "-" + password + ".txt";
		Path filePath = Paths.get(ACCOUNTS_DIRECTORY, fileName);

		try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
			// Read the third line from the file
			String thirdLine = lines.skip(2) // Skip the first two lines
					.findFirst()
					.orElse(null);

			JTextArea accountTextArea = new JTextArea();
			accountTextArea.setEditable(false);

			// Set the font size to 16 (you can adjust this value)
			Font textAreaFont = new Font("Arial", Font.PLAIN, 16);
			accountTextArea.setFont(textAreaFont);

			// Display the third line if it exists, otherwise display an empty JTextArea
			if (thirdLine != null) {
				accountTextArea.setText(thirdLine);
			}

			JScrollPane scrollPane = new JScrollPane(accountTextArea);
			scrollPane.setBounds(10, 10, 877, 280);

			// Set proper bounds for the JScrollPane and JTextArea
			bluePanel.removeAll();
			bluePanel.setLayout(null);  // Set layout to null for explicit bounds
			scrollPane.setBounds(10, 10, 200, 30);
			accountTextArea.setBounds(0, 0, 877, 280);

			bluePanel.add(scrollPane);
			bluePanel.repaint();
			bluePanel.revalidate();
		} catch (IOException e) {
			// Handle file reading exception
			e.printStackTrace();
		}
	}
	
	public static void displayVIPStatus(String username, String password, JPanel mePanel) {
		String fileName = username + "-" + password + ".txt";
		Path filePath = Paths.get(ACCOUNTS_DIRECTORY, fileName);

		try (Stream<String> lines = Files.lines(filePath, StandardCharsets.UTF_8)) {
			// Read the "Status:" line from the file
			String statusLine = lines.skip(3) // Skip the first three lines to reach the "Status:" line
					.findFirst()
					.orElse("");

			// Extract only the text after "Status:" (excluding "Status:")
			String statusText = statusLine.replace("Status:", "").trim();

			JLabel statusLabel = new JLabel(statusText);
			statusLabel.setBounds(120, 130, 500, 30);
			statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
			statusLabel.setForeground(new Color(255, 215, 0));

			// Add the status label to the mePanel instead of bluePanel
			mePanel.add(statusLabel);
			mePanel.repaint();
			mePanel.revalidate();
		} catch (IOException e) {
			// Handle file reading exception
			e.printStackTrace();
		}
	}
	
	
	
	private static void deleteNotification(String username, String password, int selectedIndex) {
		String fileName = username + "-" + password + ".txt";
		Path filePath = Paths.get(ACCOUNTS_DIRECTORY, fileName);

		try {
			// Read all lines from the original file
			List<String> allLines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

			// Check if the selected index corresponds to the correct line in the file
			int fileIndex = selectedIndex + 4; // Adjust the index to match the file content

			if (fileIndex >= 4 && fileIndex < allLines.size()) {
				// Remove the line at the calculated file index
				allLines.remove(fileIndex);

				// Write the modified lines back to the file
				Files.write(filePath, allLines, StandardCharsets.UTF_8);
			}
		} catch (IOException e) {
			// Handle file operation exception
			e.printStackTrace();
		}
	}



}