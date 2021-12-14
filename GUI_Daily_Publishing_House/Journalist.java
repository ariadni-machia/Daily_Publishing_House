package daily_publishing_house_gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.mysql.cj.jdbc.Blob;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Canvas;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Journalist extends JFrame {

	Connection myConn;
	
	private JPanel contentPane;
	private JTable table_1;
	private JTable table_2;
	private JTextField tfpath;
	private JTextField tftitle;
	private JTextField tfsummary;
	private JTextField tfpages;
	private JTextField tfcw1;
	private JTextField tfcw2;
	private JTextField tfcw3;
	private JTextField tfkw1;
	private JTextField tfkw2;
	private JTextField tfkw3;
	private JTable table_Rev;
	private JTextField tf_Rev;
	private JTextField tfim1;
	private JTextField tfim2;
	private JTextField tfim3;
	private JTextField tf_kw;
	private JTextField tf_im;


	/**
	 * Create the frame.
	 */
	public Journalist(String user_email) { 
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
		
		/*Panel - Past Article initialization*/
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVisible(false);
		scrollPane.setBounds(262, 139, 972, 245);		
		
		/*Panel - Submit New Article initialization*/
		JPanel panel_btnSubNew = new JPanel();
		panel_btnSubNew.setBounds(245, 105, 969, 562);
		panel_btnSubNew.setVisible(false);		
		panel_btnSubNew.setLayout(null);
		
		JPanel panel_Rev = new JPanel();// 1
		panel_Rev.setBounds(247, 129, 301, 45);
		panel_Rev.setVisible(false);
		panel_Rev.setLayout(null);
		
		JPanel panel_btnSubRev = new JPanel(); //2
		panel_btnSubRev.setBounds(245, 343, 989, 324);
		panel_btnSubRev.setVisible(false);
		panel_btnSubRev.setLayout(null);
		
		JScrollPane scrollPane_Rev = new JScrollPane(); //3
		scrollPane_Rev.setBounds(245, 177, 989, 156);
		scrollPane_Rev.setVisible(false);

		
		/* Buttons */
		
		// SUBMITS NEW ARTICLE ***********************************************************************************************************************************************
		JButton btnNewButton = new JButton("Submit New Article");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setVisible(false);
				panel_Rev.setVisible(false);
				panel_btnSubRev.setVisible(false);
				scrollPane_Rev.setVisible(false);
				contentPane.add(panel_btnSubNew);
				
				panel_btnSubNew.setVisible(true);
				/* Action Button New Article ***BEGINS*** */
				tfpath = new JTextField();
				tfpath.setBounds(81, 31, 286, 29);
				panel_btnSubNew.add(tfpath);
				tfpath.setColumns(10);	
				
				JPanel panel_4 = new JPanel();
				panel_4.setBackground(new Color(255, 222, 173));
				panel_4.setBounds(21, 31, 50, 29);
				panel_btnSubNew.add(panel_4);
				panel_4.setLayout(null);
				
				JLabel lblNewLabel_1 = new JLabel("Path:");
				lblNewLabel_1.setBounds(0, 0, 50, 29);
				panel_4.add(lblNewLabel_1);
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel_1.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				JPanel panel_5 = new JPanel();
				panel_5.setBackground(new Color(255, 222, 173));
				panel_5.setBounds(21, 88, 50, 29);
				panel_btnSubNew.add(panel_5);
				panel_5.setLayout(null);
				
				JLabel lblTitle = new JLabel("Title:");
				lblTitle.setBounds(0, 0, 50, 29);
				panel_5.add(lblTitle);
				lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitle.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				tftitle = new JTextField();
				tftitle.setColumns(10);
				tftitle.setBounds(81, 85, 378, 29);
				panel_btnSubNew.add(tftitle);
				
				JPanel panel_6 = new JPanel();
				panel_6.setBackground(new Color(255, 222, 173));
				panel_6.setBounds(21, 142, 75, 29);
				panel_btnSubNew.add(panel_6);
				panel_6.setLayout(null);
				
				JLabel lblSummary = new JLabel("Summary:");
				lblSummary.setBounds(0, 0, 75, 29);
				panel_6.add(lblSummary);
				lblSummary.setHorizontalAlignment(SwingConstants.CENTER);
				lblSummary.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				tfsummary = new JTextField();
				tfsummary.setColumns(10);
				tfsummary.setBounds(106, 142, 604, 29);
				panel_btnSubNew.add(tfsummary);
				
				JPanel panel_7 = new JPanel();
				panel_7.setBackground(new Color(255, 222, 173));
				panel_7.setBounds(561, 88, 50, 29);
				panel_btnSubNew.add(panel_7);
				panel_7.setLayout(null);
				
				JLabel lblPages = new JLabel("Pages:");
				lblPages.setBounds(0, 0, 50, 29);
				panel_7.add(lblPages);
				lblPages.setHorizontalAlignment(SwingConstants.CENTER);
				lblPages.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				tfpages = new JTextField();
				tfpages.setColumns(10);
				tfpages.setBounds(621, 88, 89, 29);
				panel_btnSubNew.add(tfpages);
				
				JPanel panel_8 = new JPanel();
				panel_8.setBackground(new Color(255, 222, 173));
				panel_8.setBounds(377, 31, 82, 29);
				panel_btnSubNew.add(panel_8);
				panel_8.setLayout(null);
				
				JLabel lblCategory = new JLabel("Category:");
				lblCategory.setBounds(0, 0, 82, 29);
				panel_8.add(lblCategory);
				lblCategory.setHorizontalAlignment(SwingConstants.CENTER);
				lblCategory.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				JPanel panel_9 = new JPanel();
				panel_9.setBackground(new Color(255, 222, 173));
				panel_9.setBounds(21, 196, 82, 29);
				panel_btnSubNew.add(panel_9);
				panel_9.setLayout(null);
				
				JLabel lblCowriters = new JLabel("CoWriters:");
				lblCowriters.setBounds(0, 0, 82, 29);
				panel_9.add(lblCowriters);
				lblCowriters.setHorizontalAlignment(SwingConstants.CENTER);
				lblCowriters.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				tfcw1 = new JTextField();
				tfcw1.setColumns(10);
				tfcw1.setBounds(113, 196, 168, 29);
				panel_btnSubNew.add(tfcw1);
				
				tfcw2 = new JTextField();
				tfcw2.setColumns(10);
				tfcw2.setBounds(291, 196, 168, 29);
				panel_btnSubNew.add(tfcw2);
				
				tfcw3 = new JTextField();
				tfcw3.setColumns(10);
				tfcw3.setBounds(469, 196, 168, 29);
				panel_btnSubNew.add(tfcw3);
				
				JLabel label = new JLabel(",");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				label.setBounds(276, 196, 20, 29);
				panel_btnSubNew.add(label);
				
				JLabel label_1 = new JLabel(",");
				label_1.setHorizontalAlignment(SwingConstants.CENTER);
				label_1.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				label_1.setBounds(454, 196, 20, 29);
				panel_btnSubNew.add(label_1);
				
				JPanel panel_10 = new JPanel();
				panel_10.setBackground(new Color(255, 222, 173));
				panel_10.setBounds(21, 245, 82, 29);
				panel_btnSubNew.add(panel_10);
				panel_10.setLayout(null);
				
				JLabel lblKeywords = new JLabel("Key-Words:");
				lblKeywords.setBounds(0, 0, 82, 29);
				panel_10.add(lblKeywords);
				lblKeywords.setHorizontalAlignment(SwingConstants.CENTER);
				lblKeywords.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				tfkw1 = new JTextField();
				tfkw1.setColumns(10);
				tfkw1.setBounds(113, 245, 168, 29);
				panel_btnSubNew.add(tfkw1);
				
				JLabel label_2 = new JLabel(",");
				label_2.setHorizontalAlignment(SwingConstants.CENTER);
				label_2.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				label_2.setBounds(276, 245, 20, 29);
				panel_btnSubNew.add(label_2);
				
				tfkw2 = new JTextField();
				tfkw2.setColumns(10);
				tfkw2.setBounds(291, 245, 168, 29);
				panel_btnSubNew.add(tfkw2);
				
				JLabel label_3 = new JLabel(",");
				label_3.setHorizontalAlignment(SwingConstants.CENTER);
				label_3.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				label_3.setBounds(454, 245, 20, 29);
				panel_btnSubNew.add(label_3);
				
				tfkw3 = new JTextField();
				tfkw3.setColumns(10);
				tfkw3.setBounds(469, 245, 168, 29);
				panel_btnSubNew.add(tfkw3);
				
				JComboBox comboBox = new JComboBox();
				comboBox.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				comboBox.setBounds(469, 29, 151, 29);
				panel_btnSubNew.add(comboBox);
				
				JPanel panel_11 = new JPanel();
				panel_11.setBackground(new Color(255, 222, 173));
				panel_11.setBounds(646, 31, 64, 29);
				panel_btnSubNew.add(panel_11);
				panel_11.setLayout(null);
				
				JLabel lblIssue = new JLabel("Issue:");
				lblIssue.setBounds(0, 0, 64, 29);
				panel_11.add(lblIssue);
				lblIssue.setHorizontalAlignment(SwingConstants.CENTER);
				lblIssue.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				JComboBox comboBox_1 = new JComboBox();
				comboBox_1.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				comboBox_1.setBounds(720, 29, 151, 29);
				panel_btnSubNew.add(comboBox_1);
				
				//Images
				JPanel panel_13 = new JPanel();
				panel_13.setBackground(new Color(255, 222, 173));
				panel_13.setBounds(21, 305, 75, 29);
				panel_btnSubNew.add(panel_13);
				panel_13.setLayout(null);
				
				JLabel lblImages = new JLabel("Images:");
				lblImages.setBounds(0, 0, 75, 29);
				panel_13.add(lblImages);
				lblImages.setHorizontalAlignment(SwingConstants.CENTER);
				lblImages.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				
				tfim1 = new JTextField();
				tfim1.setColumns(10);
				tfim1.setBounds(106, 304, 261, 29);
				panel_btnSubNew.add(tfim1);
				
				tfim2 = new JTextField();
				tfim2.setColumns(10);
				tfim2.setBounds(377, 304, 261, 29);
				panel_btnSubNew.add(tfim2);
				
				tfim3 = new JTextField();
				tfim3.setColumns(10);
				tfim3.setBounds(646, 304, 261, 29);
				panel_btnSubNew.add(tfim3);
				
				JLabel label_4 = new JLabel(",");
				label_4.setHorizontalAlignment(SwingConstants.CENTER);
				label_4.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				label_4.setBounds(361, 302, 20, 35);
				panel_btnSubNew.add(label_4);
				
				JLabel label_5 = new JLabel(",");
				label_5.setHorizontalAlignment(SwingConstants.CENTER);
				label_5.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				label_5.setBounds(631, 304, 20, 35);
				panel_btnSubNew.add(label_5);
				//Images End
				
				JButton btnNewButton_1 = new JButton("SUBMIT");
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {							
						try {					
							/*INSERT INTO ARTICLE BEGINS*/
							Statement myStmt= myConn.createStatement();
							String cat= (String) comboBox.getSelectedItem();
							ResultSet rs= myStmt.executeQuery("select code from category where name='"+cat+"';");
							rs.next();
							String code= rs.getString("code");
							rs= myStmt.executeQuery("select newspaper_name from works where employee_email='"+user_email+"';");
							rs.next();
							String newsp= rs.getString("newspaper_name");					
							rs= myStmt.executeQuery("select journalist_email from editor inner join works on journalist_email=employee_email where newspaper_name='"+newsp+"';");
							rs.next();
							String auditor= rs.getString("journalist_email");
							if(!tfpath.getText().isBlank() && !tftitle.getText().isBlank() && !tfsummary.getText().isBlank() && !tfpages.getText().isBlank()) {
								myStmt.executeUpdate("insert into article values('"+tfpath.getText()+"','"+tftitle.getText()+"','"+tfsummary.getText()+"','"+Integer.parseInt(tfpages.getText())+"','"+code+"','"+newsp+"','"+comboBox_1.getSelectedItem()+"','"+auditor+"',"+null+","+null+","+null+");");
								JOptionPane.showMessageDialog(contentPane, "Your submission was successful");	
							}
							else {
								JOptionPane.showMessageDialog(contentPane, "Your submission failed. You need to fill in the first six fields.");
							}
							/*INSERT INTO ARTICLE ENDS*/
							
							if(!tfpath.getText().isBlank())
							
							/*INSERT INTO KEY_WORDS BEGINS*/
							
							if(!tfkw1.getText().isBlank())
								myStmt.executeUpdate("insert into key_words values('"+tfpath.getText()+"','"+tfkw1.getText()+"');");
							if(!tfkw2.getText().isBlank())
								myStmt.executeUpdate("insert into key_words values('"+tfpath.getText()+"','"+tfkw2.getText()+"');");
							if(!tfkw3.getText().isBlank())
								myStmt.executeUpdate("insert into key_words values('"+tfpath.getText()+"','"+tfkw3.getText()+"');");
							/*INSERT INTO KEY_WORDS ENDS*/
							
							/*INSERT INTO SUBMITS (& CoWriters) BEGINS*/
							if(!tfpath.getText().isBlank())
								myStmt.executeUpdate("insert into submits values('"+user_email+"','"+tfpath.getText()+"',default);");
							if(!tfcw1.getText().isBlank())
								myStmt.executeUpdate("insert into submits values('"+tfcw1.getText()+"','"+tfpath.getText()+"',default);");
							if(!tfcw2.getText().isBlank())
								myStmt.executeUpdate("insert into submits values('"+tfcw2.getText()+"','"+tfpath.getText()+"',default);");
							if(!tfcw3.getText().isBlank())
								myStmt.executeUpdate("insert into submits values('"+tfcw3.getText()+"','"+tfpath.getText()+"',default);");					
							/*INSERT INTO SUBMITS (& CoWriters) ENDS*/
							
							/*INSERT INTO IMAGES BEGINS*/
							if(!tfim1.getText().isBlank())
								myStmt.executeUpdate("insert into images values('"+tfpath.getText()+"','"+tfim1.getText()+"');");
							if(!tfim2.getText().isBlank())
								myStmt.executeUpdate("insert into images values('"+tfpath.getText()+"','"+tfim2.getText()+"');");
							if(!tfim3.getText().isBlank())
								myStmt.executeUpdate("insert into images values('"+tfpath.getText()+"','"+tfim3.getText()+"');");
							
							/*INSERT INTO IMAGES ENDS*/
							
							myStmt.close();
						}
						catch (Exception exc){
							exc.printStackTrace();
						}
						
					}
				});
				btnNewButton_1.setForeground(new Color(0, 0, 0));
				btnNewButton_1.setBackground(new Color(255, 160, 122));
				btnNewButton_1.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				btnNewButton_1.setBounds(830, 501, 108, 35);
				panel_btnSubNew.add(btnNewButton_1);
				
				


				try {
					
					Statement myStmt= myConn.createStatement();
					//category options
					ResultSet myRs= myStmt.executeQuery("select name from category");
					while (myRs.next()) {
						String cat= myRs.getString("name");
						comboBox.addItem(cat);
					}
					//Issue options
					//select number_issue from issue where newspaper_name='Guardian' and release_date>CURRENT_TIMESTAMP();			
					myRs= myStmt.executeQuery("select newspaper_name from works where employee_email='"+user_email+"';");
					myRs.next();
					myRs= myStmt.executeQuery("select number_issue from issue where newspaper_name='"+myRs.getString("newspaper_name")+"' and release_date>CURRENT_TIMESTAMP();");
					while (myRs.next()) {
						comboBox_1.addItem(Integer.parseInt(myRs.getString("number_issue")));
					}
					
					
					myStmt.close();
				}
				catch (Exception exc){
					exc.printStackTrace();
				}
				
				/*Action Button New Article ****ENDS**** **/
				
				
			}
		});
		btnNewButton.setBounds(10, 10, 193, 45);
		panel_1.add(btnNewButton);
		btnNewButton.setBackground(SystemColor.control);
		btnNewButton.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
		
		// PAST ARTICLES ***********************************************************************************************************************************************
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(21, 230, 214, 63);
		contentPane.add(panel_2);
		
		JButton btnAriadni = new JButton("Past Articles\r\n");
		btnAriadni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_Rev.setVisible(false);
				panel_btnSubRev.setVisible(false);
				scrollPane_Rev.setVisible(false);				
				panel_btnSubNew.setVisible(false);
				contentPane.add(scrollPane); 
				scrollPane.setVisible(true);
				// Action inside Past Articles button ***BEGINS****
				
				table_1 = new JTable();
				
				table_1.setBackground(Color.WHITE);
				//table_1.setEnabled(false);
				table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table_1.setSelectionBackground(new Color(135, 206, 235));
				table_1.setFont(new Font("Arial", Font.PLAIN, 10));
				table_1.setName("");		
				
				table_1.setModel(new DefaultTableModel(
					new Object[][] {

					},
					new String[] {
						"Path", "Title", "Summary", "Pages", "Category", "Newspaper", "Issue", "Auditor", "Judgment", "Comments", "Judgment's Date"
					}
				));		
				table_1.getColumnModel().getColumn(3).setPreferredWidth(30);
				table_1.getColumnModel().getColumn(4).setPreferredWidth(50);
				table_1.getColumnModel().getColumn(6).setPreferredWidth(30);
				table_1.getColumnModel().getColumn(10).setPreferredWidth(90);
				scrollPane.setViewportView(table_1);
				//
				////
				JTableUtilities.setCellsAlignment(table_1, SwingConstants.CENTER);

				
				DefaultTableModel tableModel1= (DefaultTableModel) table_1.getModel();
				
				try {
					
					Statement myStmt= myConn.createStatement();
					//ResultSet myRs= myStmt.executeQuery("select * from article");
					ResultSet myRs= myStmt.executeQuery("select path,title,summary,pages,category,newspaper,issue,auditor,judgment,comments,judgment_date from article inner join submits on path=path_article where journalist_email=\""+user_email+"\";");
					while (myRs.next()) {
						String path= myRs.getString("path");
						String title= myRs.getString("title");
						String summary= myRs.getString("summary");
						int pages=myRs.getInt("pages");
						String category= myRs.getString("category");
						String newspaper= myRs.getString("newspaper");
						int issue=myRs.getInt("issue");
						String auditor= myRs.getString("auditor");
						String judgment= myRs.getString("judgment");
						String comments= myRs.getString("comments");
						String judgment_date= myRs.getString("judgment_date");
						tableModel1.addRow(new Object[] {path,title, summary, pages, category, newspaper, issue, auditor, judgment, comments, judgment_date});
						
					}
					myStmt.close();
				}
				catch (Exception exc){
					exc.printStackTrace();
				}
				
				// Action inside Past Articles button ***ENDS****
				
				
			}
		});
		btnAriadni.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
		btnAriadni.setBackground(SystemColor.control);
		btnAriadni.setBounds(10, 10, 193, 45);
		panel_2.add(btnAriadni);
		
		// SUBMIT REVISED VERSION ***********************************************************************************************************************************************
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_3.setBounds(21, 321, 214, 63);
		contentPane.add(panel_3);
		
		JButton btnSubmitReviseVersion = new JButton("Submit Revised Version");
		btnSubmitReviseVersion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_btnSubRev.setVisible(false);
				scrollPane_Rev.setVisible(true);				
				panel_btnSubNew.setVisible(false);
				scrollPane.setVisible(false);
				contentPane.add(panel_Rev); 
				contentPane.add(panel_btnSubRev); 
				contentPane.add(scrollPane_Rev); 
				panel_Rev.setVisible(true);
				
				/* Action Submit Revised Version ****BEGINS******** */		
				
				JLabel label_btnSubRev = new JLabel("Select the article you want to revise:");  
				label_btnSubRev.setBounds(10, 10, 286, 25);
				panel_Rev.add(label_btnSubRev);
				label_btnSubRev.setVisible(true);
				label_btnSubRev.setFont(new Font("Gill Sans Nova", Font.BOLD, 16));	
				
				
				JLabel lblNewLabel_2 = new JLabel("Choose which column you want to change:");
				lblNewLabel_2.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				lblNewLabel_2.setBounds(10, 10, 292, 31);
				panel_btnSubRev.add(lblNewLabel_2);			
				
				JLabel lblSelectTheRevised = new JLabel("Select the revised version:");
				lblSelectTheRevised.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				lblSelectTheRevised.setBounds(10, 103, 196, 31);
				panel_btnSubRev.add(lblSelectTheRevised);
				
				/* Key word Panel	******************************************************************/
				JPanel panel_KW = new JPanel();
				panel_KW.setLayout(null);
				panel_KW.setBounds(10, 71, 526, 100);
				panel_btnSubRev.add(panel_KW);
				
				JPanel panel_7 = new JPanel();
				panel_7.setLayout(null);
				panel_7.setBackground(new Color(255, 222, 173));
				panel_7.setBounds(33, 21, 53, 21);
				panel_KW.add(panel_7);
				
				JLabel label_2 = new JLabel("Add:");
				label_2.setHorizontalAlignment(SwingConstants.CENTER);
				label_2.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				label_2.setBounds(0, 0, 53, 21);
				panel_7.add(label_2);
				
				JPanel panel_8 = new JPanel();
				panel_8.setLayout(null);
				panel_8.setBackground(new Color(255, 222, 173));
				panel_8.setBounds(21, 62, 65, 21);
				panel_KW.add(panel_8);
				
				JLabel label_3 = new JLabel("Delete:");
				label_3.setHorizontalAlignment(SwingConstants.CENTER);
				label_3.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				label_3.setBounds(0, 0, 65, 21);
				panel_8.add(label_3);
				
				JComboBox comboBox_DelKW = new JComboBox();
				comboBox_DelKW.setBounds(95, 64, 144, 21);
				panel_KW.add(comboBox_DelKW);
				
				tf_kw = new JTextField();
				tf_kw.setColumns(10);
				tf_kw.setBounds(94, 21, 152, 21);
				panel_KW.add(tf_kw);
					
				/* Key word Panel	*****************************************************/
				
				/* Image Panel	*****************************************************/
				JPanel panel_Im = new JPanel();
				panel_Im.setBounds(20, 46, 526, 100);
				panel_btnSubRev.add(panel_Im);
				panel_Im.setLayout(null);
				
				JPanel panel_9 = new JPanel();
				panel_9.setLayout(null);
				panel_9.setBackground(new Color(255, 222, 173));
				panel_9.setBounds(33, 21, 53, 21);
				panel_Im.add(panel_9);
				
				JLabel label_4 = new JLabel("Add:");
				label_4.setHorizontalAlignment(SwingConstants.CENTER);
				label_4.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				label_4.setBounds(0, 0, 53, 21);
				panel_9.add(label_4);
				
				JPanel panel_10 = new JPanel();
				panel_10.setLayout(null);
				panel_10.setBackground(new Color(255, 222, 173));
				panel_10.setBounds(21, 62, 65, 21);
				panel_Im.add(panel_10);
				
				JLabel label_5 = new JLabel("Delete:");
				label_5.setHorizontalAlignment(SwingConstants.CENTER);
				label_5.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				label_5.setBounds(0, 0, 65, 21);
				panel_10.add(label_5);
				
				JComboBox comboBox_DelIm = new JComboBox();
				comboBox_DelIm.setBounds(95, 64, 217, 21);
				panel_Im.add(comboBox_DelIm);
				
				tf_im = new JTextField();
				tf_im.setColumns(10);
				tf_im.setBounds(94, 21, 218, 21);
				panel_Im.add(tf_im);
				/* Image Panel-ends	*****************************************************/
				
				//* PANEL COWRITER ***************************************************
				JPanel panel_CoWr = new JPanel();
				panel_CoWr.setLayout(null);
				panel_CoWr.setBounds(24, 65, 526, 110);
				panel_btnSubRev.add(panel_CoWr);
				
				//panel_btnSubRev.add(panel_CoWr); // FIX LATER///////////////////////////////////////////////////////////////////////////
				//panel_CoWr.setVisible(true);
				
				
				JPanel panel_5 = new JPanel();
				panel_5.setLayout(null);
				panel_5.setBackground(new Color(255, 222, 173));
				panel_5.setBounds(33, 21, 53, 21);
				panel_CoWr.add(panel_5);
				
				JLabel label = new JLabel("Add:");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				label.setBounds(0, 0, 53, 21);
				panel_5.add(label);
				
				//ComboBox Add Writer
				JComboBox comboBox_AddWr = new JComboBox();
				comboBox_AddWr.setBounds(95, 20, 144, 21);
				panel_CoWr.add(comboBox_AddWr);
				
				JPanel panel_6 = new JPanel();
				panel_6.setLayout(null);
				panel_6.setBackground(new Color(255, 222, 173));
				panel_6.setBounds(21, 62, 65, 21);
				panel_CoWr.add(panel_6);
				
				JLabel label_1 = new JLabel("Delete:");
				label_1.setHorizontalAlignment(SwingConstants.CENTER);
				label_1.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				label_1.setBounds(0, 0, 65, 21);
				panel_6.add(label_1);
				
				//ComboBox Delete Writer
				JComboBox comboBox_Del = new JComboBox();
				comboBox_Del.setBounds(95, 64, 144, 21);
				panel_CoWr.add(comboBox_Del);
				
				
				tf_Rev = new JTextField();
				tf_Rev.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
				tf_Rev.setBounds(206, 103, 311, 27);
				//panel_btnSubRev.add(tf_Rev);
				tf_Rev.setColumns(10);
				
				
				table_Rev = new JTable();	
				scrollPane_Rev.setViewportView(table_Rev);		
				table_Rev.setModel(new DefaultTableModel(
					new Object[][] {

					},
					new String[] {
						"Path", "Title", "Summary", "Pages", "Category", "Newspaper", "Issue", "Auditor", "Judgment", "Comments", "Judgment's Date"
					}
				));		
				table_Rev.getColumnModel().getColumn(3).setPreferredWidth(30);
				table_Rev.getColumnModel().getColumn(4).setPreferredWidth(50);
				table_Rev.getColumnModel().getColumn(6).setPreferredWidth(30);
				table_Rev.getColumnModel().getColumn(7).setPreferredWidth(90);
				table_Rev.getColumnModel().getColumn(10).setPreferredWidth(90);
				scrollPane_Rev.setViewportView(table_Rev);

				JTableUtilities.setCellsAlignment(table_Rev, SwingConstants.CENTER);

				DefaultTableModel table_RevModel= (DefaultTableModel) table_Rev.getModel();		
				
				try {
					
					Statement myStmt= myConn.createStatement();
					ResultSet myRs= myStmt.executeQuery("select path,title,summary,pages,category,newspaper,issue,auditor,judgment,comments,judgment_date from article inner join submits on path=path_article where journalist_email='"+user_email+"' and judgment='To_be_revised';");
					while (myRs.next()) {
						String path= myRs.getString("path");
						String title= myRs.getString("title");
						String summary= myRs.getString("summary");
						int pages=myRs.getInt("pages");
						String category= myRs.getString("category");
						String newspaper= myRs.getString("newspaper");
						int issue=myRs.getInt("issue");
						String auditor= myRs.getString("auditor");
						String judgment= myRs.getString("judgment");
						String comments= myRs.getString("comments");
						String judgment_date= myRs.getString("judgment_date");
						table_RevModel.addRow(new Object[] {path,title, summary, pages, category, newspaper, issue, auditor, judgment, comments, judgment_date});
						
					}
					myStmt.close();
				}
				catch (Exception exc){
					exc.printStackTrace();
				}
				/* Table of Revised articles ENDS*/
				
				//Selected Row - Table of Revised articles
				table_Rev.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						
						panel_btnSubRev.setVisible(true);				
						
						//btnRefrsh Initialization
						JButton btnRefresh = new JButton("Refresh table");				
						btnRefresh.setBackground(new Color(255, 222, 173));
						btnRefresh.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
						btnRefresh.setBounds(794, 135, 127, 31);
						panel_btnSubRev.add(btnRefresh);
						
						/* Revise BEGINS*/
						//ComboBox for Revise Column
						JComboBox comboBox_Rev = new JComboBox();
						comboBox_Rev.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
						comboBox_Rev.setBounds(308, 10, 113, 28);
						panel_btnSubRev.add(comboBox_Rev);
						comboBox_Rev.addItem("Path");
						comboBox_Rev.addItem("Title");
						comboBox_Rev.addItem("Summary");
						comboBox_Rev.addItem("Pages");
						comboBox_Rev.addItem("Category");
						comboBox_Rev.addItem("Issue");
						comboBox_Rev.addItem("CoWriter");
						comboBox_Rev.addItem("Key-Word");
						comboBox_Rev.addItem("Image");
						
						JLabel lblWriteTheRevised = new JLabel("Write the revised version:");
						lblWriteTheRevised.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
						lblWriteTheRevised.setBounds(10, 103, 196, 31);
						panel_btnSubRev.add(lblWriteTheRevised);
						
						
						
						//ComboBox Category
						JComboBox comboBox_Cat = new JComboBox();
						comboBox_Cat.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
						comboBox_Cat.setBounds(206, 105, 160, 27);
						panel_btnSubRev.add(comboBox_Cat);
						
						try {
							Statement myStmt= myConn.createStatement();
							ResultSet myRs= myStmt.executeQuery("select name from category");
							while (myRs.next()) {
								String cat= myRs.getString("name");
								comboBox_Cat.addItem(cat);
							}
							
							myStmt.close();
						}
						catch (Exception exc){
								exc.printStackTrace();
						}
						
						//ComboBox for Issue
						JComboBox comboBox_Issue = new JComboBox();
						comboBox_Issue.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
						comboBox_Issue.setBounds(206, 103, 117, 27);
						panel_btnSubRev.add(comboBox_Issue);				
						try {
							Statement myStmt= myConn.createStatement();
							ResultSet myRs= myStmt.executeQuery("select newspaper_name from works where employee_email='"+user_email+"';");
							myRs.next();
							myRs= myStmt.executeQuery("select number_issue from issue where newspaper_name='"+myRs.getString("newspaper_name")+"' and release_date>CURRENT_TIMESTAMP();");
							while (myRs.next()) {
								comboBox_Issue.addItem(Integer.parseInt(myRs.getString("number_issue")));
							}
							myStmt.close();
						}
						catch (Exception exc){
								exc.printStackTrace();
						}
					
						
						//Initialization btnSubRev
						JButton btnSubRev = new JButton("Submit");
						btnSubRev.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
						btnSubRev.setBounds(172, 188, 130, 31);
						btnSubRev.setForeground(new Color(0, 0, 0));
						btnSubRev.setBackground(new Color(255, 160, 122));
						panel_btnSubRev.add(btnSubRev);
						
						//Initial Display
						tf_Rev.setVisible(false);
						lblWriteTheRevised.setVisible(false);
						panel_btnSubRev.add(tf_Rev);
						comboBox_Cat.setVisible(false);
						comboBox_Issue.setVisible(false);
						panel_CoWr.setVisible(false);
						lblSelectTheRevised.setVisible(false);
						btnSubRev.setVisible(false);
						panel_KW.setVisible(false);
						panel_Im.setVisible(false);
						
						
						
						
						JButton btnOK = new JButton("OK");
						btnOK.setBackground(new Color(255, 222, 173));
						btnOK.addActionListener(new ActionListener() {
							/*Display begins */
							public void actionPerformed(ActionEvent e) {
								String revision= (String) comboBox_Rev.getSelectedItem();
								btnSubRev.setVisible(true);
								if(revision=="Category") {
									tf_Rev.setVisible(false);
									lblWriteTheRevised.setVisible(false);
									comboBox_Cat.setVisible(true);
									comboBox_Issue.setVisible(false);
									panel_CoWr.setVisible(false);
									lblSelectTheRevised.setVisible(true);
									panel_KW.setVisible(false);
									panel_Im.setVisible(false);
									
								}
								else if(revision=="Issue") {
									tf_Rev.setVisible(false);
									lblWriteTheRevised.setVisible(false);
									comboBox_Cat.setVisible(false);
									comboBox_Issue.setVisible(true);
									panel_CoWr.setVisible(false);
									lblSelectTheRevised.setVisible(true);
									panel_KW.setVisible(false);
									panel_Im.setVisible(false);

								}
								else if(revision=="CoWriter") {
									tf_Rev.setVisible(false);
									lblWriteTheRevised.setVisible(false);
									comboBox_Cat.setVisible(false);
									comboBox_Issue.setVisible(false);
									lblWriteTheRevised.setVisible(false);
									lblSelectTheRevised.setVisible(false);
									panel_KW.setVisible(false);
									panel_CoWr.setVisible(true);
									panel_Im.setVisible(false);
									

								}
								else if(revision=="Key-Word") {
									tf_Rev.setVisible(false);
									lblWriteTheRevised.setVisible(false);
									comboBox_Cat.setVisible(false);
									comboBox_Issue.setVisible(false);
									panel_CoWr.setVisible(false);
									lblSelectTheRevised.setVisible(false);							
									panel_KW.setVisible(true);
									panel_Im.setVisible(false);
									
								}
								else if(revision=="Image") {
									tf_Rev.setVisible(false);
									lblWriteTheRevised.setVisible(false);
									comboBox_Cat.setVisible(false);
									comboBox_Issue.setVisible(false);
									panel_CoWr.setVisible(false);
									lblSelectTheRevised.setVisible(false);							
									panel_KW.setVisible(false);
									panel_Im.setVisible(true);
								}

								else {
									tf_Rev.setVisible(true);
									lblWriteTheRevised.setVisible(true);
									comboBox_Cat.setVisible(false);
									comboBox_Issue.setVisible(false);
									panel_CoWr.setVisible(false);
									lblSelectTheRevised.setVisible(false);
									panel_KW.setVisible(false);
									panel_Im.setVisible(false);
									

								}
								/*Display ends */
								
								///////////////////////////////
								//ComboBox Image
								try {
									comboBox_DelIm.removeAllItems();
									
									int row = table_Rev.getSelectedRow();
									String selpath = table_Rev.getModel().getValueAt(row, 0).toString();
									comboBox_DelIm.addItem("none");
									Statement myStmt= myConn.createStatement();
									ResultSet myRs2= myStmt.executeQuery("select image from images where path_article='"+selpath+"';");
									while (myRs2.next()) {
										String imag= myRs2.getString("image");	
										comboBox_DelIm.addItem(imag);									
									}
									
									
									
									myStmt.close();
								}
								catch (Exception exc){
										exc.printStackTrace();
								}
								
								//ComboBo Key Word
								try {
									comboBox_DelKW.removeAllItems();
									
									int row = table_Rev.getSelectedRow();
									String selpath = table_Rev.getModel().getValueAt(row, 0).toString();
									comboBox_DelKW.addItem("none");
									Statement myStmt= myConn.createStatement();
									ResultSet myRs2= myStmt.executeQuery("select key_word from key_words where path_article='"+selpath+"';");
									while (myRs2.next()) {
										String word= myRs2.getString("key_word");	
										comboBox_DelKW.addItem(word);									
									}
									
									
									
									myStmt.close();
								}
								catch (Exception exc){
										exc.printStackTrace();
								}						
								
								
								//ComboBox CoWriter							
								
								try {
									comboBox_AddWr.removeAllItems();
									int row = table_Rev.getSelectedRow();
									String selpath = table_Rev.getModel().getValueAt(row, 0).toString();
									String[] cowr= new String[10]; // MAX 10 CoWriter
									int i=0;
									boolean check=false;
									comboBox_AddWr.addItem("none");
									Statement myStmt= myConn.createStatement();
									ResultSet myRs= myStmt.executeQuery("select newspaper_name from works where employee_email='"+user_email+"';");
									myRs.next();
									String newsp= myRs.getString("newspaper_name");
									ResultSet myRs2= myStmt.executeQuery("select journalist.employee_email from journalist inner join works on journalist.employee_email=works.employee_email inner join submits on journalist.employee_email=journalist_email where newspaper_name='"+newsp+"' and path_article='"+selpath+"';");
									
									while (myRs2.next()==true) {
										cowr[i]=myRs2.getString("journalist.employee_email");
										i++;
									}
									
									myRs= myStmt.executeQuery("select journalist.employee_email from journalist inner join works on journalist.employee_email=works.employee_email where newspaper_name='"+newsp+"';");
									
									while (myRs.next()) {
										check=false;
										String em= myRs.getString("journalist.employee_email");
										for(int j=0; j<i; j++){
											if(em.equals(cowr[j])==true)	
												check=true;
										}
										if(check==false)
											comboBox_AddWr.addItem(em);
									}
									
									
									myStmt.close();
								}
								catch (Exception exc){
										exc.printStackTrace();
								}
								
								try {
									comboBox_Del.removeAllItems();
									int row = table_Rev.getSelectedRow();
									String selpath = table_Rev.getModel().getValueAt(row, 0).toString();
									String[] cowr= new String[3];
									int i=0;
									boolean check=false;
									comboBox_Del.addItem("none");
									Statement myStmt= myConn.createStatement();
									ResultSet myRs= myStmt.executeQuery("select newspaper_name from works where employee_email='"+user_email+"';");
									myRs.next();
									String newsp= myRs.getString("newspaper_name");
									
									myRs= myStmt.executeQuery("select journalist.employee_email from journalist inner join works on journalist.employee_email=works.employee_email inner join submits on works.employee_email=journalist_email where newspaper_name='"+newsp+"' and path_article='"+selpath+"';");
									while (myRs.next()) {
										String em= myRs.getString("journalist.employee_email");									
										if(!em.equals(user_email))
											comboBox_Del.addItem(em);
									}
									
									
									myStmt.close();
								}
								catch (Exception exc){
										exc.printStackTrace();
								}
								//ComboBox CoWriter ENDS***********************************************************************						
								
								
						///////////////////////////
								
								
								
							}// ENDS actionPerformed(ActionEvent e)				
						});		
						btnOK.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
						btnOK.setBounds(440, 10, 63, 26);
						panel_btnSubRev.add(btnOK);				
						/*Display ends */
						
						/*btnSubRev - begins */
						
						btnSubRev.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e2) {
								try {
									Statement myStmt= myConn.createStatement();							
									String revision= (String) comboBox_Rev.getSelectedItem();
									int row = table_Rev.getSelectedRow();
									String selpath = table_Rev.getModel().getValueAt(row, 0).toString();
									
									//Switch
									switch(revision) {
									  case "Path":
										  if(!tf_Rev.getText().isBlank()) {
											  myStmt.executeUpdate("update article set path='"+tf_Rev.getText()+"' where path='"+selpath+"';");
											  JOptionPane.showMessageDialog(contentPane, "Your change has been saved");									  
										  }										
									    break;
									  case "Title":
										  if(!tf_Rev.getText().isBlank()) {
											  myStmt.executeUpdate("update article set title='"+tf_Rev.getText()+"' where path='"+selpath+"';");
											  JOptionPane.showMessageDialog(contentPane, "Your change has been saved");									  
										  }
									    break;
									  case "Summary":
										  if(!tf_Rev.getText().isBlank()) {
											  myStmt.executeUpdate("update article set summary='"+tf_Rev.getText()+"' where path='"+selpath+"';");
											  JOptionPane.showMessageDialog(contentPane, "Your change has been saved");									  
										  }
										break;
									  case "Pages":
										  if(!tf_Rev.getText().isBlank()) {
											  myStmt.executeUpdate("update article set pages='"+tf_Rev.getText()+"' where path='"+selpath+"';");
											  JOptionPane.showMessageDialog(contentPane, "Your change has been saved");									  
										  }
										break;
									  case "Category":
										     String cat= (String) comboBox_Cat.getSelectedItem();
										     ResultSet rs= myStmt.executeQuery("select code from category where name='"+cat+"';");
											 rs.next();
											 String code= rs.getString("code");
										  	 myStmt.executeUpdate("update article set category='"+code+"' where path='"+selpath+"';");
											 JOptionPane.showMessageDialog(contentPane, "Your change has been saved");									  
										  
										break;
									  case "Issue":
										     int issue= (Integer) comboBox_Issue.getSelectedItem();
										  	  myStmt.executeUpdate("update article set issue='"+issue+"' where path='"+selpath+"';");
											  JOptionPane.showMessageDialog(contentPane, "Your change has been saved");						  
										  
										break;
									  case "CoWriter":
										   if(comboBox_AddWr.getSelectedItem()!="none") {
												myStmt.executeUpdate("insert into submits values('"+comboBox_AddWr.getSelectedItem()+"','"+selpath+"',default);");
												JOptionPane.showMessageDialog(contentPane, "Your change has been saved");	
											}
											if(comboBox_Del.getSelectedItem()!="none") {
												myStmt.executeUpdate("delete from submits where journalist_email='"+comboBox_Del.getSelectedItem()+"' and path_article='"+selpath+"';");
												JOptionPane.showMessageDialog(contentPane, "Your change has been saved");	
											} 
										break;
									  case "Key-Word":
										// Add key word
											try {

												String[] kw= new String[10]; //key word check max 10
												int i=0;
												boolean check=false;
												ResultSet myRs2= myStmt.executeQuery("select key_word from key_words where path_article='"+selpath+"';");	
												while (myRs2.next()==true) {
													kw[i]=myRs2.getString("key_word");
													i++;
												}																
												check=false;
													
												for(int j=0; j<i; j++){
													if(tf_kw.getText().equals(kw[j])==true)	
														check=true;
												}
												
												if(!tf_kw.getText().isBlank()) {
													if(check==false ) {
														myStmt.executeUpdate("insert into key_words values('"+selpath+"','"+tf_kw.getText()+"');");
														JOptionPane.showMessageDialog(contentPane, "Your change has been saved");
													}
													else {
														JOptionPane.showMessageDialog(contentPane, "This key word already exists");
													}	
												}
												
											}
											catch (Exception exc){
													exc.printStackTrace();
											}
											// Add key word EnDS
											
											//delete key word
											if(comboBox_DelKW.getSelectedItem()!="none") {
												myStmt.executeUpdate("delete from key_words where key_word='"+comboBox_DelKW.getSelectedItem()+"'and path_article='"+selpath+"';");
												JOptionPane.showMessageDialog(contentPane, "Your submission was successful");	
											}			
											//delete key word
										break;
											
									  case "Image":
										// Add image
											try {

												String[] im= new String[10]; //image check max 10
												int i=0;
												boolean check=false;
												ResultSet myRs2= myStmt.executeQuery("select image from images where path_article='"+selpath+"';");	
												while (myRs2.next()==true) {
													im[i]=myRs2.getString("image");
													i++;
												}																
												check=false;
													
												for(int j=0; j<i; j++){
													if(tf_im.getText().equals(im[j])==true)	
														check=true;
												}
												
												if(!tf_im.getText().isBlank()) {
													if(check==false ) {
														myStmt.executeUpdate("insert into images values('"+selpath+"','"+tf_im.getText()+"');");
														JOptionPane.showMessageDialog(contentPane, "Your change has been saved");
													}
													else {
														JOptionPane.showMessageDialog(contentPane, "This key word already exists");
													}	
												}
												
											}
											catch (Exception exc){
													exc.printStackTrace();
											}
											// Add image EnDS
											
											//delete key word
											if(comboBox_DelIm.getSelectedItem()!="none") {
												myStmt.executeUpdate("delete from images where image='"+comboBox_DelIm.getSelectedItem()+"'and path_article='"+selpath+"';");
												JOptionPane.showMessageDialog(contentPane, "Your submission was successful");	
											}					
											//delete image ends
									  
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
						});/*btnSubRev - ends */
						
					// **** REFRESH **************************************************************************************************************************
						btnRefresh.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								try {
									table_RevModel.setRowCount(0);
									Statement myStmt= myConn.createStatement();
									ResultSet myRs= myStmt.executeQuery("select path,title,summary,pages,category,newspaper,issue,auditor,judgment,comments,judgment_date from article inner join submits on path=path_article where journalist_email='"+user_email+"' and judgment='To_be_revised';");
									while (myRs.next()) {
										String path= myRs.getString("path");
										String title= myRs.getString("title");
										String summary= myRs.getString("summary");
										int pages=myRs.getInt("pages");
										String category= myRs.getString("category");
										String newspaper= myRs.getString("newspaper");
										int issue=myRs.getInt("issue");
										String auditor= myRs.getString("auditor");
										String judgment= myRs.getString("judgment");
										String comments= myRs.getString("comments");
										String judgment_date= myRs.getString("judgment_date");
										table_RevModel.addRow(new Object[] {path,title, summary, pages, category, newspaper, issue, auditor, judgment, comments, judgment_date});
										
									}
									myStmt.close();
								}
								catch (Exception exc){
									exc.printStackTrace();
								}
								
								
							}
						});
					}

				});
				
				
			}
		});
		btnSubmitReviseVersion.setFont(new Font("Gill Sans Nova", Font.PLAIN, 16));
		btnSubmitReviseVersion.setBackground(SystemColor.control);
		btnSubmitReviseVersion.setBounds(10, 10, 193, 45);
		panel_3.add(btnSubmitReviseVersion);
		
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
		
		//************************************************************************************************************************
		
		
		
		/* Action Submit Revised Version ****ENDS******** */
		
		
		
		
		
		

		
	
		


	} //Journalist ENDS
/***********************ENDS JOURNALIST************************************************/	
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Journalist frame = new Journalist(null); 
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
