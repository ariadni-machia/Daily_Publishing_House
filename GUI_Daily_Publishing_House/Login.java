package daily_publishing_house_gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.border.Border;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setForeground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(146, 10, 168, 53);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME :)");
		lblNewLabel.setBounds(0, 10, 168, 33);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(SystemColor.desktop);
		lblNewLabel.setBackground(SystemColor.desktop);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(179, 193, 95, 21);
		contentPane.add(btnNewButton);
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(208, 103, 106, 19);
		contentPane.add(formattedTextField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(208, 146, 106, 19);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(146, 106, 76, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblPassword.setBounds(146, 149, 76, 13);
		contentPane.add(lblPassword);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(130, 89, 203, 4);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(130, 179, 203, 4);
		contentPane.add(separator_2);
		
		
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
            String userName = formattedTextField.getText();
            String password = passwordField.getText();
            try {
            	Driver driver= new Driver();
            	Connection connLogin=driver.createConnectionLoginDB();
            	
            	Statement st= connLogin.createStatement();	
            	boolean rs0= st.executeQuery("select username from login where username=\""+userName+"\";").next();
            	boolean rs= st.executeQuery("select password from login where username=\""+userName+"\" and password=\""+password+"\";").next();
            	if(!rs0 && !rs)
            		JOptionPane.showMessageDialog(btnNewButton, "Wrong username and password.");            	
            	if( rs0 && !rs)
            		JOptionPane.showMessageDialog(btnNewButton, "Wrong password.");            	
            	        
                Statement st2= connLogin.createStatement();			
                ResultSet rs2= st2.executeQuery("select email from login where username=\""+userName+"\" AND password=\""+password+"\"");
				rs2.next();
				String lgemail= rs2.getString("email");
				
				rs2= st2.executeQuery("select type from login where username=\""+userName+"\" AND password=\""+password+"\"");
				rs2.next();
				String lgtype= rs2.getString("type");
				   
				
				if (rs) {
                    dispose();
                     
					switch(lgtype) {
					  case "Administrative":
						  JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
						  Administrative adm= new Administrative(lgemail); 
						  adm.setTitle("Administrative");
						  adm.setVisible(true);
					    break;
					  case "Publisher":
						  JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
						  Publisher pub= new Publisher(lgemail);
						  pub.setTitle("Publisher");
						  pub.setVisible(true);
					    break;
					  case "Journalist":
						  JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
						  Journalist jour= new Journalist(lgemail);
		                  jour.setTitle("Journalist");
		                  jour.setVisible(true);
						break;
					  default:
						// code block
					}
				}
				else {
                    JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                
                }
                
                
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
            catch (NullPointerException exnull) {
				JOptionPane.showMessageDialog(btnNewButton, "You are not authorized to access this database.");
				exnull.printStackTrace();
            }
        }
		});
		
	}
}
