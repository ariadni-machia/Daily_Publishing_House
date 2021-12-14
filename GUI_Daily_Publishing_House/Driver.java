package daily_publishing_house_gui;

import java.sql.*;

public class Driver {

	private String dbname="daily_publishing_house";
	private String logindb="logindb";
	private String user= "root";
	private String password= "CE!Dpadaw@n#10";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Driver driver= new Driver();
	}

	
	public Connection createConnectionDB() {
		Connection myConn=null;
		
		try {
			myConn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+getDbname()+"?useLegacyDatetimeCode=flase&serverTimezone=UTC", getUser(),getPassword());			
			
		}		
		catch (Exception exc){
			exc.printStackTrace();
		}
		return myConn;
	}
	public Connection createConnectionLoginDB() {
		Connection connLogin=null;

		try {
			connLogin= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+getLoginDb()+"?useLegacyDatetimeCode=flase&serverTimezone=UTC", getUser(), getPassword());			
			
		}		
		catch (Exception exc){
			exc.printStackTrace();
		}
		return connLogin;
	}
	
	public String getDbname() {
		return this.dbname;
	}
	
	public String getUser() {
		return this.user;
	}
	public String getPassword() {
		return this.password;
	}
	public String getLoginDb() {
		return this.logindb;
	}
	
	
	

}
