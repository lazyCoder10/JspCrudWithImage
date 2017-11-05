package com.binarydream.test.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.binarydream.test.dbutil.DbUtil;
import com.binarydream.test.users.User;


public class UserDao {
	Connection connection;
	
	public void userDao(){
		connection = DbUtil.getConnection();
	}
	
	public void addUser(User user){
		try{
			/*DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
			Date today = new Date();
			String currentDate = formater.format(today)*/;
			PreparedStatement query = DbUtil.getConnection().prepareStatement("insert into users(firstName,lastName,"
					+ "dob,email,userImage) values(?,?,?,?,?)");
			query.setString(1, user.getFirstName());
			query.setString(2, user.getLastName());
			query.setDate(3, new java.sql.Date(user.getDateOfBirth().getTime()));
			query.setString(4, user.getUserEmail());
			query.setString(5, user.getUserImage());
			query.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	public List<User> getAllUser(){
		List<User> allUser = new ArrayList<User>();
		try{
			//Statement statement = connection.createStatement();
			Statement statement = DbUtil.getConnection().createStatement();
			ResultSet users = statement.executeQuery("select * from users");
			 
			while(users.next()){
				//user.setUserName(users.getString("firstName"));
				User user = new User();
				user.setUserId(users.getInt("userId"));
				user.setFirstName(users.getString("firstName"));
				user.setLastName(users.getString("lastName"));
				user.setDateOfBirth(users.getDate("dob"));
				user.setUserEmail(users.getString("email"));
				user.setUserImage(users.getString("userImage"));
				allUser.add(user);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return allUser;
	}
	
	public void updateUser(User user){
		try {
			PreparedStatement query = DbUtil.getConnection().prepareStatement("UPDATE users SET firstName=?, lastName=?,dob=?,email=? WHERE userId=? ");
			query.setString(1, user.getFirstName());
			query.setString(2, user.getLastName());
			query.setDate(3, new java.sql.Date(user.getDateOfBirth().getTime()) );
			query.setString(4, user.getUserEmail());
			query.setInt(5, user.getUserId());
			query.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		User user = new User();
		try{
			PreparedStatement query = DbUtil.getConnection().prepareStatement("SELECT * FROM users WHERE userId=?");
			query.setInt(1, userId);
			ResultSet users = query.executeQuery(); 
			
			while(users.next()){
				user.setUserId(users.getInt("userId"));
				user.setFirstName(users.getString("firstName"));
				user.setLastName(users.getString("lastName"));
				user.setDateOfBirth(users.getDate("dob"));
				user.setUserEmail(users.getString("email"));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		
		return user;
	}
	
	public void deleteUser(int userId) {
		try {
			PreparedStatement preparedStatement = DbUtil.getConnection()
					.prepareStatement("delete from users where userid=?");
			// Parameters start with 1
			preparedStatement.setInt(1, userId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
