package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {
	
	private Connection myConn = database.dbConnect();
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	public LoginController()
	{
		
	}
	public boolean doLogin(String name,String password){
		boolean valid = false;
		try{
			sql = "SELECT password FROM user WHERE name = ? ";
			ps = myConn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				if(password.equals(rs.getString(1))){
					valid = true;
				}
			}
			rs.close();
			ps.close();
			myConn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return valid;
	}
}