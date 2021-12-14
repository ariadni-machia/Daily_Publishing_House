package daily_publishing_house_gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Administrative extends JFrame {

	Connection myConn;
	private JPanel contentPane;
	private JTextField tf_NotSold;
	private JTextField tf_Mon;
	private JTextField tf_Total;
	private JTextField tf_Per;

	public Administrative(String user_email) {
		Driver driver= new Driver();
		myConn=driver.createConnectionDB();
		
		setBackground(new Color(204, 204, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1258, 739);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/* Welcome Begins */		
		JPanel panel = new JPanel();
		panel.setBounds(21, 25, 250, 45);
		contentPane.add(panel);
		panel.setLayout(null);			
		try {			
			Statement myStmt= myConn.createStatement();
			ResultSet myRs= myStmt.executeQuery("select name from employee where email='"+user_email+"';");
			myRs.next();
			String user_name= myRs.getString("name");	
			
			JLabel lblNewLabel = new JLabel("Welcome "+user_name);
			lblNewLabel.setBackground(SystemColor.control);
			lblNewLabel.setBounds(10, 10, 230, 27);
			panel.add(lblNewLabel);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Serif", Font.BOLD, 20));
			
			myStmt.close();
		}
		catch (Exception exc){
			exc.printStackTrace();
		}		
		/* Welcome Ends */
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(21, 139, 214, 63);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_Copies = new JPanel();
		panel_Copies.setBounds(245, 139, 959, 506);		
		panel_Copies.setLayout(null);
		panel_Copies.setVisible(false);
		
		JPanel panel_Fin = new JPanel();
		panel_Fin.setBounds(261, 139, 973, 344);
		panel_Fin.setVisible(false);
		panel_Fin.setLayout(null);
		
		//Panel Log Out Button
		JPanel panel_btnLogOut = new JPanel();
		panel_btnLogOut.setLayout(null);
		panel_btnLogOut.setBackground(Color.LIGHT_GRAY);
		panel_btnLogOut.setBounds(21, 604, 214, 63);
		contentPane.add(panel_btnLogOut);
		
		//Log out Button
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.setBounds(10, 10, 193, 45);
		panel_btnLogOut.add(btnLogOut);
		btnLogOut.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
		btnLogOut.setBackground(SystemColor.control);
		btnLogOut.addActionListener(new CloseListener());
		
		
		JButton btnNewButton = new JButton("Insert No of Copies");
		btnNewButton.setBackground(SystemColor.control);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.add(panel_Copies);
				panel_Copies.setVisible(true);
				panel_Fin.setVisible(false);
				/*Action Button Insert No of Copies ***BEGINS*** */		
				JPanel panel_3 = new JPanel();
				panel_3.setBackground(new Color(255, 222, 173));
				panel_3.setBounds(32, 43, 98, 30);
				panel_Copies.add(panel_3);
				panel_3.setLayout(null);
				
				JLabel lblNewLabel_1 = new JLabel("Choose Issue:");
				lblNewLabel_1.setBounds(0, 0, 99, 30);
				panel_3.add(lblNewLabel_1);
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				JComboBox comboBox_Issue = new JComboBox();
				comboBox_Issue.setBounds(135, 43, 118, 30);
				panel_Copies.add(comboBox_Issue);
				
				JPanel panel_4 = new JPanel();
				panel_4.setBackground(new Color(255, 222, 173));
				panel_4.setBounds(28, 108, 198, 30);
				panel_Copies.add(panel_4);
				panel_4.setLayout(null);
				
				JLabel lblNumberOfNot = new JLabel("Number of Not Sold Copies:");
				lblNumberOfNot.setBounds(0, 0, 198, 30);
				panel_4.add(lblNumberOfNot);
				lblNumberOfNot.setHorizontalAlignment(SwingConstants.CENTER);
				lblNumberOfNot.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				tf_NotSold = new JTextField();
				tf_NotSold.setBounds(235, 107, 144, 30);
				panel_Copies.add(tf_NotSold);
				tf_NotSold.setColumns(10);		
				
				try {
					
					Statement myStmt= myConn.createStatement();
					ResultSet myRs= myStmt.executeQuery("select newspaper_name from works where employee_email='"+user_email+"';");
					myRs.next();
					String newsp= myRs.getString("newspaper_name");
					myRs= myStmt.executeQuery("select number_issue from issue where newspaper_name='"+newsp+"' and not_sold is null;");
					while (myRs.next()) {
						comboBox_Issue.addItem(Integer.parseInt(myRs.getString("number_issue")));
					}
					/*
					int selissue=(Integer) comboBox_Issue.getSelectedItem();
					if(!tf_NotSold.getText().isBlank()) {
						myStmt.executeUpdate("update issue set not_sold='"+tf_NotSold.getText()+"' where newspaper_name='"+newsp+"' and issue='"+selissue+"';");
						JOptionPane.showMessageDialog(btnNewButton, "Your submission was successful");	
					}
					*/
					myStmt.close();
				}
				catch (Exception exc){
					exc.printStackTrace();
				}				
				
				
				
				JButton btnNewButton_1 = new JButton("Insert");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							
							Statement myStmt= myConn.createStatement();
							ResultSet myRs= myStmt.executeQuery("select newspaper_name from works where employee_email='"+user_email+"';");
							myRs.next();
							String newsp= myRs.getString("newspaper_name");
							int selissue=(Integer) comboBox_Issue.getSelectedItem();
							if(!tf_NotSold.getText().isBlank()) {
								myStmt.executeUpdate("update issue set not_sold='"+tf_NotSold.getText()+"' where newspaper_name='"+newsp+"' and number_issue='"+selissue+"';");
								JOptionPane.showMessageDialog(btnNewButton, "Your submission was successful");	
							}
							
							myStmt.close();
						}
						catch (Exception exc){
							exc.printStackTrace();
						}	
					}
				});
				btnNewButton_1.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btnNewButton_1.setBounds(141, 188, 112, 30);
				btnNewButton_1.setForeground(new Color(0, 0, 0));
				btnNewButton_1.setBackground(new Color(255, 160, 122));
				panel_Copies.add(btnNewButton_1);
				
				/*Action Button Insert No of Copies ***ENDS*** */
				
			}
		});
		btnNewButton.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
		btnNewButton.setBounds(10, 10, 194, 43);
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(21, 245, 214, 63);
		contentPane.add(panel_2);
		

		
		JButton btnFinancialData = new JButton("Financial Data");
		btnFinancialData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//STARTS financial 
				panel_Fin.setVisible(true);
				contentPane.add(panel_Fin);
				panel_Copies.setVisible(false);
				
				JLabel lblNewLabel_2 = new JLabel("Month(s):");
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_2.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				lblNewLabel_2.setBounds(41, 38, 76, 21);
				panel_Fin.add(lblNewLabel_2);
				
				tf_Mon = new JTextField();
				tf_Mon.setBounds(125, 38, 96, 22);
				panel_Fin.add(tf_Mon);
				tf_Mon.setColumns(10);
				
				JLabel lblTotal = new JLabel("Total money spend on salary:");
				lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
				lblTotal.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				lblTotal.setBounds(41, 84, 207, 21);
				panel_Fin.add(lblTotal);
				
				tf_Total = new JTextField();
				tf_Total.setHorizontalAlignment(SwingConstants.CENTER);
				tf_Total.setBackground(Color.WHITE);
				tf_Total.setEditable(false);
				tf_Total.setColumns(10);
				tf_Total.setBounds(247, 85, 96, 24);
				panel_Fin.add(tf_Total);
				
				
				
				JButton btnTotal = new JButton("Calculate Total");
				btnTotal.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						float total=0;
						try {
							Statement myStmt= myConn.createStatement();		
							ResultSet myRs= myStmt.executeQuery("select newspaper_name from works where employee_email='"+user_email+"';");
							myRs.next();
							String newsp= myRs.getString("newspaper_name");
							myRs= myStmt.executeQuery("select salary from employee inner join works on email=employee_email where newspaper_name='"+newsp+"';");
							while (myRs.next()) {
								total= total+myRs.getFloat("salary");						
							}				
							if(!tf_Mon.getText().isBlank())
								total=total*Float.parseFloat(tf_Mon.getText()); 
							myStmt.close();
						}
						catch (Exception exc){
								exc.printStackTrace();
						}
						if(!tf_Mon.getText().isBlank())
							tf_Total.setText(String.valueOf(total));
						else
							JOptionPane.showMessageDialog(contentPane, "Fill in the number of months");	
						
					}
				});
				btnTotal.setBackground(new Color(255, 222, 173));
				btnTotal.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btnTotal.setBounds(353, 86, 143, 23);
				panel_Fin.add(btnTotal);
				
				JLabel lblChooseSpecificPerson = new JLabel("Choose specific person:");
				lblChooseSpecificPerson.setHorizontalAlignment(SwingConstants.CENTER);
				lblChooseSpecificPerson.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				lblChooseSpecificPerson.setBounds(41, 140, 186, 21);
				panel_Fin.add(lblChooseSpecificPerson);
				
				
				
				JComboBox comboBox_Empl = new JComboBox();
				comboBox_Empl.setBounds(223, 142, 148, 21);
				panel_Fin.add(comboBox_Empl);
				
				
				try {
					Statement myStmt= myConn.createStatement();	
					ResultSet myRs= myStmt.executeQuery("select newspaper_name from works where employee_email='"+user_email+"';");
					myRs.next();
					String newsp= myRs.getString("newspaper_name");
					myRs= myStmt.executeQuery("select email from employee inner join works on email=employee_email where newspaper_name='"+newsp+"';");
					while (myRs.next()) {
						String email = myRs.getString("email");
						comboBox_Empl.addItem(email);				
					}
					
				}
				
				catch (Exception exc){
					exc.printStackTrace();
				}
				
				JButton btnCalculate = new JButton("Calculate");
				btnCalculate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							
							String empl= (String) comboBox_Empl.getSelectedItem();
							Statement myStmt= myConn.createStatement();	
							ResultSet myRs= myStmt.executeQuery("select salary from employee inner join works on email=employee_email where email='"+empl+"';");
							myRs.next();
							float sal= myRs.getFloat("salary");
							float tot=0;
							if(!tf_Mon.getText().isBlank()) {
								tot=sal*Float.parseFloat(tf_Mon.getText());
								tf_Per.setText(String.valueOf(tot)); 
							}
							else
								JOptionPane.showMessageDialog(contentPane, "Fill in the number of months");	
							}
						
						
						catch (Exception exc){
							exc.printStackTrace();
						}
						
						
						
					}
				});
				btnCalculate.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btnCalculate.setBackground(new Color(255, 222, 173));
				btnCalculate.setBounds(487, 140, 143, 23);
				panel_Fin.add(btnCalculate);
				
				tf_Per = new JTextField();
				tf_Per.setHorizontalAlignment(SwingConstants.CENTER);
				tf_Per.setEditable(false);
				tf_Per.setColumns(10);
				tf_Per.setBackground(Color.WHITE);
				tf_Per.setBounds(381, 141, 96, 24);
				panel_Fin.add(tf_Per);
				btnLogOut.addActionListener(new CloseListener());
				
				
				
				//ENDS financial things that only Polly does because she is really cool
				
			}
		});
		btnFinancialData.setBackground(SystemColor.control);
		btnFinancialData.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
		btnFinancialData.setBounds(10, 10, 194, 43);
		panel_2.add(btnFinancialData);
		
		
		
		
		
		
		
		

		
		
		
		
		
	}//ENDS administrative
	/*********************************************************/

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrative frame = new Administrative(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
