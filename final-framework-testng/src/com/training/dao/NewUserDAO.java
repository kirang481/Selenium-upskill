package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import com.training.bean.NewUserBean;
import com.training.connection.GetConnection;
import com.training.utility.LoadDBDetails;

// Data Access Object 
public class NewUserDAO {
	
	Properties properties; 
	
	public NewUserDAO() {
		 try {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/sql.properties");
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<NewUserBean> getUser(){
		String sql = properties.getProperty("get.Users"); 
		
		GetConnection gc  = new GetConnection(); 
		List<NewUserBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<NewUserBean>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				NewUserBean temp = new NewUserBean(); 
				temp.setUserName(gc.rs1.getString(1));
				temp.setEmail(gc.rs1.getString(2));
				temp.setFirstName(gc.rs1.getString(3));
				temp.setLastName(gc.rs1.getString(4));
				temp.setwebSite(gc.rs1.getString(5));
				temp.setPassword(gc.rs1.getString(6));
				temp.setRole(gc.rs1.getString(7));

				list.add(temp); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}
	
	public static void main(String[] args) {
		new NewUserDAO().getUser().forEach(System.out :: println);
	}
	
	
}
