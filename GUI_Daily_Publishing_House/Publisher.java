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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Publisher extends JFrame {
	
	Connection myConn;
	private JPanel contentPane;
	private JTextField tf_Copies;
	private JTextField tf_Curr;
	private JTable tableSales;
	private JTable table_NewspInfo;
	private JTextField textField_Rev;

	public Publisher(String user_email) {
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
			ResultSet myRs= myStmt.executeQuery("select publisher_name from newspaper where publisher_email='"+user_email+"';");
			myRs.next();
			String user_name= myRs.getString("publisher_name");	
			
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
		
		//Panels for Buttons
		//Panel For NEWSPAPER INFO
		JPanel panel_NewspInfo = new JPanel();
		panel_NewspInfo.setBounds(261, 147, 973, 520);		
		panel_NewspInfo.setLayout(null);
		panel_NewspInfo.setVisible(false);
		
		//Panel for SET COPIES
		JPanel panel_Copies = new JPanel();
		panel_Copies.setBounds(261, 147, 973, 520);		
		panel_Copies.setLayout(null);
		panel_Copies.setVisible(false);
				
		//Panel for APPOINT EDITOR
		JPanel panel_Ed = new JPanel();
		panel_Ed.setBounds(261, 147, 973, 520);		
		panel_Ed.setLayout(null);
		panel_Ed.setVisible(false);
		
		//Panel For SALE INFO
		JPanel panel_Sales = new JPanel();
		panel_Sales.setBounds(261, 147, 973, 520);		
		panel_Sales.setLayout(null);
		panel_Sales.setVisible(false);
		
		
			
		//Buttons
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(21, 147, 214, 63);
		contentPane.add(panel_1);
		
		// NEWSPAPER INFO *******************************************************************************************************************************************************************		
		JButton btnNewspaperInfo = new JButton("Newspaper Info");
		btnNewspaperInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*ACTION NEWSPAPER INFO *** ENDS *** */
				panel_Copies.setVisible(false);
				panel_Ed.setVisible(false);
				panel_Sales.setVisible(false);
				contentPane.add(panel_NewspInfo);
				panel_NewspInfo.setVisible(true);
				
				JPanel panel_7 = new JPanel();
				panel_7.setBackground(new Color(255, 222, 173));
				panel_7.setBounds(206, 147, 130, 26);
				panel_NewspInfo.add(panel_7);
				panel_7.setLayout(null);
				
				JLabel lblSelectNewspaper = new JLabel("Select Newspaper:");
				lblSelectNewspaper.setBounds(0, 0, 130, 26);
				panel_7.add(lblSelectNewspaper);
				lblSelectNewspaper.setHorizontalAlignment(SwingConstants.CENTER);
				lblSelectNewspaper.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				JComboBox comboBox_News = new JComboBox();
				comboBox_News.setBounds(346, 147, 182, 26);
				panel_NewspInfo.add(comboBox_News);
				try {
					comboBox_News.removeAllItems();
					Statement myStmt= myConn.createStatement();	
					ResultSet myRs= myStmt.executeQuery("select name from newspaper where publisher_email='"+user_email+"';");
					while (myRs.next()) {
						String newsp = myRs.getString("name");
						comboBox_News.addItem(newsp);				
					}
					
				}		
				catch (Exception exc){
					exc.printStackTrace();
				}
				
				refresh_table(panel_NewspInfo,user_email);// show table
				
				/*
				JButton btnOK = new JButton("OK\r\n");
				btnOK.setBackground(new Color(255, 222, 173));
				btnOK.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btnOK.setBounds(536, 143, 85, 31);
				panel_NewspInfo.add(btnOK);
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						refresh_table(panel_NewspInfo,user_email);
					}
				});
				*/
				
				/*  REFRESH ******************************/
				JButton btnRefresh = new JButton("Refresh");
				btnRefresh.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//refresh table
						refresh_table(panel_NewspInfo,user_email);
						//refresh comboBox of newspaper
						try {
							comboBox_News.removeAllItems();
							Statement myStmt= myConn.createStatement();	
							ResultSet myRs= myStmt.executeQuery("select name from newspaper where publisher_email='"+user_email+"';");
							while (myRs.next()) {
								String newsp = myRs.getString("name");
								comboBox_News.addItem(newsp);				
							}
							
						}		
						catch (Exception exc){
							exc.printStackTrace();
						}
					}
				});
				btnRefresh.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btnRefresh.setBackground(new Color(255, 222, 173));
				btnRefresh.setBounds(783, 283, 98, 30);
				panel_NewspInfo.add(btnRefresh);
				
				JPanel panel_5 = new JPanel();
				panel_5.setBounds(36, 213, 300, 26);
				panel_NewspInfo.add(panel_5);
				panel_5.setLayout(null);
				
				JPanel panel_6 = new JPanel();
				panel_6.setBackground(new Color(255, 222, 173));
				panel_6.setBounds(0, 0, 312, 26);
				panel_5.add(panel_6);
				panel_6.setLayout(null);
				
				JLabel lblChooseWhichColumn = new JLabel("Choose which column you want to change:");
				lblChooseWhichColumn.setBounds(0, 0, 302, 26);
				panel_6.add(lblChooseWhichColumn);
				lblChooseWhichColumn.setHorizontalAlignment(SwingConstants.CENTER);
				lblChooseWhichColumn.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				JComboBox comboBox_Choose = new JComboBox();
				comboBox_Choose.setBounds(346, 213, 170, 26);
				comboBox_Choose.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				panel_NewspInfo.add(comboBox_Choose);
				comboBox_Choose.addItem("Newspaper Name");
				//comboBox_Choose.addItem("Publisher Email");
				comboBox_Choose.addItem("Publication Frequency");		
				
				JButton btn_OK_choose = new JButton("OK\r\n");
				btn_OK_choose.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btn_OK_choose.setBackground(new Color(255, 222, 173));
				btn_OK_choose.setBounds(526, 211, 85, 31);
				panel_NewspInfo.add(btn_OK_choose);		
				
				/* REVISED TEXT FIELD BEGINS */
				JPanel panel_RevTF = new JPanel();
				panel_RevTF.setBounds(26, 262, 643, 69);
				panel_RevTF.setVisible(false);
				//panel_NewspInfo.add(panel_RevTF);
				panel_RevTF.setLayout(null);
				
				JPanel panel_8 = new JPanel();
				panel_8.setBackground(new Color(255, 222, 173));
				panel_8.setBounds(10, 22, 192, 26);
				panel_RevTF.add(panel_8);
				panel_8.setLayout(null);
				
				JLabel lblWriteTheRevised = new JLabel("Write the revised version:");
				lblWriteTheRevised.setBounds(0, 0, 192, 26);
				panel_8.add(lblWriteTheRevised);
				lblWriteTheRevised.setHorizontalAlignment(SwingConstants.CENTER);
				lblWriteTheRevised.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				textField_Rev = new JTextField();
				textField_Rev.setBounds(214, 23, 282, 26);
				panel_RevTF.add(textField_Rev);
				textField_Rev.setColumns(10);
				
				JPanel panel_9 = new JPanel();
				panel_9.setBounds(0, 0, 640, 102);
				panel_RevTF.add(panel_9);
				/* REVISED TEXT FIELD ENDS */
				JButton btnSubmit = new JButton("Submit");		
				btnSubmit.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btnSubmit.setBounds(275, 400, 130, 31);
				btnSubmit.setForeground(new Color(0, 0, 0));
				btnSubmit.setBackground(new Color(255, 160, 122));
				panel_NewspInfo.add(btnSubmit);
				
				/* REVISED COMBOBOX FREQ BEGINS */
				JPanel panel_RevCB = new JPanel();
				panel_RevCB.setBounds(22, 263, 616, 77);
				panel_RevCB.setVisible(false);
				//panel_NewspInfo.add(panel_RevCB);
				panel_RevCB.setLayout(null);
				
				JPanel panel_10 = new JPanel();
				panel_10.setBackground(new Color(255, 222, 173));
				panel_10.setBounds(10, 10, 176, 26);
				panel_RevCB.add(panel_10);
				panel_10.setLayout(null);
				
				JLabel lblChooseNewFrequency = new JLabel("Choose new frequency:");
				lblChooseNewFrequency.setBounds(0, 0, 176, 26);
				panel_10.add(lblChooseNewFrequency);
				lblChooseNewFrequency.setHorizontalAlignment(SwingConstants.CENTER);
				lblChooseNewFrequency.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				JComboBox comboBox_Fr = new JComboBox();
				comboBox_Fr.setBounds(200, 10, 148, 26);
				panel_RevCB.add(comboBox_Fr);
				comboBox_Fr.addItem("DAILY");
				comboBox_Fr.addItem("WEEKLY");
				comboBox_Fr.addItem("MONTHLY");
				
				//Press OK Choose column 
				btn_OK_choose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String revision= (String) comboBox_Choose.getSelectedItem();
						if(revision=="Publication Frequency") {
							panel_RevTF.setVisible(false);
							panel_NewspInfo.add(panel_RevCB);
							panel_RevCB.setVisible(true);
							
						}
						else {
							panel_RevCB.setVisible(false);
							panel_NewspInfo.add(panel_RevTF);
							panel_RevTF.setVisible(true);
							
						}
					}
				});		
				
				
				// SUBMIT ACTION
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String revision= (String) comboBox_Choose.getSelectedItem();
						try {
							Statement myStmt= myConn.createStatement();												
							//Switch
							switch(revision) {
							  case "Newspaper Name":
								  if(!textField_Rev.getText().isBlank()) {
									  myStmt.executeUpdate("update newspaper set name='"+textField_Rev.getText()+"' where name='"+comboBox_News.getSelectedItem()+"';");
									  JOptionPane.showMessageDialog(contentPane, "Your change has been saved");									  
								  }										
							    break;
							/* case "Publisher Email":
								  if(!textField_Rev.getText().isBlank()) {
									  myStmt.executeUpdate("update newspaper set publisher_email='"+textField_Rev.getText()+"' where name='"+comboBox_News.getSelectedItem()+"';");
									  JOptionPane.showMessageDialog(contentPane, "Your change has been saved");									  
								  }
							    break;
							 */case "Publication Frequency":
								  	  myStmt.executeUpdate("update newspaper set publication_frequency='"+comboBox_Fr.getSelectedItem()+"' where name='"+comboBox_News.getSelectedItem()+"';");
									  JOptionPane.showMessageDialog(contentPane, "Your change has been saved");									  
								  
								break;
							  default:
								// code block
							}
																
							myStmt.close();
						}
						catch (Exception exc){
								exc.printStackTrace();
						}
						
					}
				});
				
				/*ACTION NEWSPAPER INFO *** ENDS *** */
			}
		});
		btnNewspaperInfo.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
		btnNewspaperInfo.setBackground(SystemColor.menu);
		btnNewspaperInfo.setBounds(10, 10, 194, 43);
		panel_1.add(btnNewspaperInfo);
		
		//SET COPIES *******************************************************************************************************************************************
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(21, 253, 214, 63);
		contentPane.add(panel_2);		
		
		//SET COPIES BUTTON
		JButton btnSetCopies = new JButton("Set Copies");
		btnSetCopies.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*ACTION SET COPIES ****BEGINS*** */
				panel_NewspInfo.setVisible(false);
				panel_Ed.setVisible(false);
				panel_Sales.setVisible(false);
				contentPane.add(panel_Copies);
				panel_Copies.setVisible(true);
				
				JPanel panel_5 = new JPanel();
				panel_5.setBackground(new Color(255, 222, 173));
				panel_5.setBounds(31, 58, 138, 21);
				panel_Copies.add(panel_5);
				panel_5.setLayout(null);
				
				JLabel lblNewLabel_1 = new JLabel("Select Newspaper:");
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setBounds(0, 0, 138, 21);
				panel_5.add(lblNewLabel_1);
				lblNewLabel_1.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				JComboBox comboBox_News = new JComboBox();
				comboBox_News.setBounds(177, 58, 178, 23);
				panel_Copies.add(comboBox_News);		
				try {
					Statement myStmt= myConn.createStatement();	
					ResultSet myRs= myStmt.executeQuery("select name from newspaper where publisher_email='"+user_email+"';");
					while (myRs.next()) {
						String newsp = myRs.getString("name");
						comboBox_News.addItem(newsp);				
					}
					
				}
				
				catch (Exception exc){
					exc.printStackTrace();
				}
				
				JComboBox comboBox_Issue = new JComboBox();
				comboBox_Issue.setBounds(146, 119, 126, 23);
				panel_Copies.add(comboBox_Issue);
				
				
				JPanel panel_6 = new JPanel();
				panel_6.setBackground(new Color(255, 222, 173));
				panel_6.setBounds(31, 119, 105, 24);
				panel_Copies.add(panel_6);
				panel_6.setLayout(null);
				
				JLabel lblSelectIssue = new JLabel("Select Issue:");
				lblSelectIssue.setBounds(0, 0, 105, 24);
				panel_6.add(lblSelectIssue);
				lblSelectIssue.setHorizontalAlignment(SwingConstants.CENTER);
				lblSelectIssue.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				JButton btnOK_Newsp = new JButton("OK");
				btnOK_Newsp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						comboBox_Issue.removeAllItems();
						try {
							Statement myStmt= myConn.createStatement();	
							ResultSet myRs= myStmt.executeQuery("select number_issue from issue where newspaper_name='"+comboBox_News.getSelectedItem()+"' and release_date>CURRENT_TIMESTAMP() and copies=0;");
							while (myRs.next()) {
								comboBox_Issue.addItem(Integer.parseInt(myRs.getString("number_issue")));				
							}
							
						}
						
						catch (Exception exc){
							exc.printStackTrace();
						}
						
					}
				});
				btnOK_Newsp.setBackground(new Color(255, 222, 173));
				btnOK_Newsp.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btnOK_Newsp.setBounds(365, 58, 71, 29);
				panel_Copies.add(btnOK_Newsp);
				
				JPanel panel_7 = new JPanel();
				panel_7.setBackground(new Color(255, 222, 173));
				panel_7.setBounds(31, 188, 138, 24);
				panel_Copies.add(panel_7);
				panel_7.setLayout(null);
					
				JLabel lblNumberOfCopies = new JLabel("Number Of Copies:");
				lblNumberOfCopies.setBounds(0, 0, 138, 24);
				panel_7.add(lblNumberOfCopies);
				lblNumberOfCopies.setHorizontalAlignment(SwingConstants.CENTER);
				lblNumberOfCopies.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				tf_Copies = new JTextField();
				tf_Copies.setBounds(177, 188, 105, 24);
				panel_Copies.add(tf_Copies);
				tf_Copies.setColumns(10);
				
				
				JButton btnSubRev = new JButton("Submit");
				btnSubRev.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Statement myStmt= myConn.createStatement();	
							if(!tf_Copies.getText().isBlank()) {
								myStmt.executeUpdate("update issue set copies='"+tf_Copies.getText()+"' where newspaper_name='"+comboBox_News.getSelectedItem()+"' and number_issue='"+comboBox_Issue.getSelectedItem()+ "';");
								JOptionPane.showMessageDialog(contentPane, "Your submission was successful");
							}
							else
								JOptionPane.showMessageDialog(contentPane, "Fill in the number of copies");
						}
						
						catch (Exception exc){
							exc.printStackTrace();
						}
						//Refresh comboBox_Issue
						
						comboBox_Issue.removeAllItems();
						try {
							Statement myStmt= myConn.createStatement();	
							ResultSet myRs= myStmt.executeQuery("select number_issue from issue where newspaper_name='"+comboBox_News.getSelectedItem()+"' and release_date>CURRENT_TIMESTAMP() and copies=0;");
							while (myRs.next()) {
								comboBox_Issue.addItem(Integer.parseInt(myRs.getString("number_issue")));				
							}
							
						}
						
						catch (Exception exc){
							exc.printStackTrace();
						}
						
						
					}
				});
				btnSubRev.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btnSubRev.setBounds(121, 249, 130, 31);
				btnSubRev.setForeground(new Color(0, 0, 0));
				btnSubRev.setBackground(new Color(255, 160, 122));
				panel_Copies.add(btnSubRev);
				
				/*ACTION SET COPIES ****ENDS*** */
			}
		});
		btnSetCopies.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
		btnSetCopies.setBackground(SystemColor.menu);
		btnSetCopies.setBounds(10, 10, 194, 43);
		panel_2.add(btnSetCopies);
		
		//APPOINTS EDITOR ****************************************************************************************************************************
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(21, 356, 214, 63);
		contentPane.add(panel_3);
		
		JButton btnAppointsEditor = new JButton("Appoints Editor");
		btnAppointsEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*ACTION FOR APPOINT EDITOR ***BEGINS*** */
				panel_NewspInfo.setVisible(false);
				panel_Copies.setVisible(false);
				panel_Sales.setVisible(false);
				panel_Ed.setVisible(true);
				contentPane.add(panel_Ed);
				
				
				JPanel panel_7 = new JPanel();
				panel_7.setBackground(new Color(255, 222, 173));
				panel_7.setBounds(38, 69, 130, 26);
				panel_Ed.add(panel_7);
				panel_7.setLayout(null);
				
				JLabel lblSelectNewspaper = new JLabel("Select Newspaper:");
				lblSelectNewspaper.setBounds(0, 0, 130, 26);
				panel_7.add(lblSelectNewspaper);
				lblSelectNewspaper.setHorizontalAlignment(SwingConstants.CENTER);
				lblSelectNewspaper.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				JComboBox comboBox_News = new JComboBox();
				comboBox_News.setBounds(182, 71, 182, 26);
				panel_Ed.add(comboBox_News);
				try {
					Statement myStmt= myConn.createStatement();	
					ResultSet myRs= myStmt.executeQuery("select name from newspaper where publisher_email='"+user_email+"';");
					while (myRs.next()) {
						String newsp = myRs.getString("name");
						comboBox_News.addItem(newsp);				
					}
					
				}		
				catch (Exception exc){
					exc.printStackTrace();
				}
				
				JButton btnOK = new JButton("OK\r\n");
				btnOK.setBackground(new Color(255, 222, 173));
				btnOK.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btnOK.setBounds(374, 69, 85, 31);
				panel_Ed.add(btnOK);
				
				JPanel panel_6 = new JPanel();
				panel_6.setBackground(new Color(255, 222, 173));
				panel_6.setBounds(38, 181, 130, 26);
				panel_Ed.add(panel_6);
				panel_6.setLayout(null);
				
				JLabel lblNewLabel_2 = new JLabel("Select new editor:");
				lblNewLabel_2.setBounds(0, 0, 128, 26);
				panel_6.add(lblNewLabel_2);
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_2.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				JComboBox comboBox_Ed = new JComboBox();
				comboBox_Ed.setBounds(176, 181, 201, 26);
				panel_Ed.add(comboBox_Ed);
				
				JPanel panel_5 = new JPanel();
				panel_5.setBackground(new Color(255, 222, 173));
				panel_5.setBounds(38, 122, 115, 26);
				panel_Ed.add(panel_5);
				panel_5.setLayout(null);
				
				JLabel lblCurrentEditor = new JLabel("Current editor:");
				lblCurrentEditor.setBounds(0, 0, 115, 26);
				panel_5.add(lblCurrentEditor);
				lblCurrentEditor.setHorizontalAlignment(SwingConstants.CENTER);
				lblCurrentEditor.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				tf_Curr = new JTextField();
				tf_Curr.setEditable(false);
				tf_Curr.setBounds(163, 122, 201, 26);
				panel_Ed.add(tf_Curr);
				tf_Curr.setColumns(10);
				
				JButton btnSubmit = new JButton("Submit");		
				btnSubmit.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btnSubmit.setBounds(140, 247, 130, 31);
				btnSubmit.setForeground(new Color(0, 0, 0));
				btnSubmit.setBackground(new Color(255, 160, 122));
				panel_Ed.add(btnSubmit);
				
				//ACTION OK BUTTON - KNOWN NEWSPAPER - SET CUURENT EDITOR
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							Statement myStmt= myConn.createStatement();
							ResultSet myRs= myStmt.executeQuery("select journalist_email from editor where appointed_by='"+comboBox_News.getSelectedItem()+"';");					
							String curred="none";
							if(myRs.next() == true)
								curred=myRs.getString("journalist_email");
							tf_Curr.setText(curred);					
						
							comboBox_Ed.removeAllItems();
							myRs= myStmt.executeQuery("select journalist.employee_email from works inner join journalist on works.employee_email=journalist.employee_email where newspaper_name='"+comboBox_News.getSelectedItem()+"';");
							while (myRs.next()) {
								String em = myRs.getString("employee_email");
								if(!em.equals(curred))
									comboBox_Ed.addItem(em);
							}
							myStmt.close();
							
						}		
						catch (Exception exc){
							exc.printStackTrace();
						}
						
					}
				});	
				
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						 try {
							Statement myStmt= myConn.createStatement();
							ResultSet myRs= myStmt.executeQuery("select journalist_email from editor where appointed_by='"+comboBox_News.getSelectedItem()+"';");
							if(myRs.next() == true) {
								myStmt.executeUpdate("update editor set journalist_email='"+comboBox_Ed.getSelectedItem()+"' where appointed_by='"+comboBox_News.getSelectedItem()+"';");
								JOptionPane.showMessageDialog(contentPane, "Your change was successful");
								tf_Curr.setText((String)comboBox_Ed.getSelectedItem());
							}
							else {
								myStmt.executeUpdate("insert into editor values('"+comboBox_Ed.getSelectedItem()+"','"+comboBox_News.getSelectedItem()+"');");
								JOptionPane.showMessageDialog(contentPane, "Your change was successful");
								tf_Curr.setText((String)comboBox_Ed.getSelectedItem());
							}
							myStmt.close();
							
						}		
						catch (Exception exc){
							exc.printStackTrace();
						}
						
						//tf_Curr.setText();
					}
				});
				
				/*ACTION FOR APPOINT EDITOR ***ENDS*** */
				
			}
		});
		btnAppointsEditor.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
		btnAppointsEditor.setBackground(SystemColor.menu);
		btnAppointsEditor.setBounds(10, 10, 194, 43);
		panel_3.add(btnAppointsEditor);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_4.setBounds(21, 453, 214, 63);
		contentPane.add(panel_4);
		
		// SALES INFO *******************************************************************************************************************************************************************
		JButton btnSalesInfo = new JButton("Sales Info");
		btnSalesInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*ACTION FOR SALES INFO ***BEGINS**** */
				panel_NewspInfo.setVisible(false);
				panel_Copies.setVisible(false);
				panel_Ed.setVisible(false);
				panel_Sales.setVisible(true);
				contentPane.add(panel_Sales);
				
				JPanel panel_7 = new JPanel();
				panel_7.setBackground(new Color(255, 222, 173));
				panel_7.setBounds(227, 41, 130, 26);
				panel_Sales.add(panel_7);
				panel_7.setLayout(null);
				
				JLabel lblSelectNewspaper = new JLabel("Select Newspaper:");
				lblSelectNewspaper.setBounds(0, 0, 130, 26);
				panel_7.add(lblSelectNewspaper);
				lblSelectNewspaper.setHorizontalAlignment(SwingConstants.CENTER);
				lblSelectNewspaper.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				JComboBox comboBox_News = new JComboBox();
				comboBox_News.setBounds(367, 41, 182, 26);
				panel_Sales.add(comboBox_News);
				try {
					Statement myStmt= myConn.createStatement();	
					ResultSet myRs= myStmt.executeQuery("select name from newspaper where publisher_email='"+user_email+"';");
					while (myRs.next()) {
						String newsp = myRs.getString("name");
						comboBox_News.addItem(newsp);				
					}
					
				}		
				catch (Exception exc){
					exc.printStackTrace();
				}
				
				JButton btnOK = new JButton("OK\r\n");
				btnOK.setBackground(new Color(255, 222, 173));
				btnOK.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btnOK.setBounds(559, 41, 85, 31);
				panel_Sales.add(btnOK);		
				
				btnOK.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JScrollPane scrollPane_Sales = new JScrollPane();
						scrollPane_Sales.setBounds(99, 94, 798, 316);
						panel_Sales.add(scrollPane_Sales);
						
						tableSales = new JTable();		
						tableSales.setBackground(Color.WHITE);
						tableSales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						tableSales.setSelectionBackground(new Color(135, 206, 235));
						tableSales.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
						tableSales.setName("");		
						
						tableSales.setModel(new DefaultTableModel(
							new Object[][] {

							},
							new String[] {
								"Issue", "Realease Date", "Sold Copies"
							}
						));	
						
						scrollPane_Sales.setViewportView(tableSales);
						JTableUtilities.setCellsAlignment(tableSales, SwingConstants.CENTER);
						DefaultTableModel tableSalesModel= (DefaultTableModel) tableSales.getModel();
						
						try {
							tableSalesModel.setRowCount(0);
							Statement myStmt= myConn.createStatement();
							ResultSet myRs= myStmt.executeQuery("select number_issue, release_date, copies, not_sold from issue where newspaper_name='"+comboBox_News.getSelectedItem()+"' and not_sold is not null;");
							while (myRs.next()) {
								String issue= myRs.getString("number_issue");
								String date= myRs.getString("release_date");				
								int copies=myRs.getInt("copies");
								int not_sold=myRs.getInt("not_sold");
								int sold= copies-not_sold;

								tableSalesModel.addRow(new Object[] {issue,date,sold});
								
							}
							myStmt.close();
						}
						catch (Exception exc){
							exc.printStackTrace();
						}
					}
				});
				/*ACTION FOR SALES INFO ***ENDS**** */
			}
		});
		btnSalesInfo.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
		btnSalesInfo.setBackground(SystemColor.menu);
		btnSalesInfo.setBounds(10, 10, 194, 43);
		panel_4.add(btnSalesInfo);

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
		
		
		
		
		
		
		
	}//ENDS PUBLISHER
//** PUBLISHER ENDS ************************************************************************************************************************************************************************************************************
	
	//Refresh Table
	void refresh_table(JPanel panel_NewspInfo, String user_email) {
		JScrollPane scrollPane_NewspInfo = new JScrollPane();
		scrollPane_NewspInfo.setBounds(167, 10, 502, 114);
		panel_NewspInfo.add(scrollPane_NewspInfo);
		
		table_NewspInfo = new JTable();
		table_NewspInfo.setEnabled(false);
		scrollPane_NewspInfo.setViewportView(table_NewspInfo);		
		table_NewspInfo.setBackground(Color.WHITE);
		table_NewspInfo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_NewspInfo.setSelectionBackground(new Color(135, 206, 235));
		table_NewspInfo.setFont(new Font("Gill Sans Nova", Font.PLAIN, 12));
		table_NewspInfo.setName("");		
		
		table_NewspInfo.setModel(new DefaultTableModel(
			new Object[][] {

			},
			new String[] {
				"Newspaper Name", "Publication Frequency"
			}
		));	
		
		scrollPane_NewspInfo.setViewportView(table_NewspInfo);
		JTableUtilities.setCellsAlignment(table_NewspInfo, SwingConstants.CENTER);
		DefaultTableModel tableNewspInfoModel= (DefaultTableModel) table_NewspInfo.getModel();
		
		try {
			tableNewspInfoModel.setRowCount(0);
			Statement myStmt= myConn.createStatement();
			ResultSet myRs= myStmt.executeQuery("select name, publication_frequency from newspaper where publisher_email='"+user_email+"';");
			while (myRs.next()) {
				String newsp= myRs.getString("name");
				String fr= myRs.getString("publication_frequency");				

				tableNewspInfoModel.addRow(new Object[] {newsp,fr});
				
			}
			myStmt.close();
		}
		catch (Exception exc){
			exc.printStackTrace();
		}
		
	}
	
	
// *** MAIN ******************************************************************************************************
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Publisher frame = new Publisher(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
