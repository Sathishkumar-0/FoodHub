package com.tap.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.conect.DBConnection;
import com.tap.inter.UserDao;
import com.tap.pojo.User;

public class UserImpl implements UserDao {

	private String INSERT="insert into user (name,username,password,email,"
					+ "phone,address,role,createdDate,lastLoginDate) values(?,?,?,?,?,?,?,?,?)";
	private String GET="select * from user where email=?";
	private String UPDATE="update user set name=?,password=?,email=?,phone=?,role=? where email=?";
	private String DELET="delete from user where email=?";
	private String GET_ALL_USER="select * from user";

	@Override
	public void addUser(User u) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt=con.prepareStatement(INSERT);
			
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getUsername());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getEmail());
			pstmt.setString(5, u.getPhone());
			pstmt.setString(6, u.getAddress());
			pstmt.setString(7, u.getRole());
			pstmt.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			pstmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			
			int re = pstmt.executeUpdate();
			System.out.println(re);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(String email) {

		User u=null;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET);
			
			pstmt.setString(1, email);
			
			ResultSet res = pstmt.executeQuery();
			
			while(res.next()) {
				int userId = res.getInt("userId");
				String name = res.getString("name");
				String userName = res.getString("username");
				String password = res.getString("password");
				String email1 = res.getString("email");
				String phone = res.getString("phone");
				String address = res.getString("address");
				String role = res.getString("role");
				Timestamp createdDate= res.getTimestamp("createdDate");
				Timestamp lastLoginDate= res.getTimestamp("lastLoginDate");
				
				u = new User(userId, name, userName, password, email1, phone, address, role, createdDate, lastLoginDate);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}
	
	@Override
	public void updateUser(User u) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, u.getName());
			pstmt.setString(2, u.getPassword());
			pstmt.setString(3, u.getEmail());
			pstmt.setString(4, u.getPhone());
			pstmt.setString(5, u.getRole());
			pstmt.setString(6, u.getEmail());
			
			int res = pstmt.executeUpdate();
			System.out.println(res);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteUser(String email) {

		Connection con = DBConnection.getConnection();
		try {
			PreparedStatement pstmt = con.prepareStatement(DELET);
			
			pstmt.setString(1, email);
			int res = pstmt.executeUpdate();
			System.out.println(res);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUser() {
			
			List<User> list = new ArrayList<User>();
			User u=null;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_USER);
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int userId = res.getInt("userId");
				String name = res.getString("name");
				String userName = res.getString("username");
				String password = res.getString("password");
				String email = res.getString("email");
				String phone = res.getString("phone");
				String address = res.getString("address");
				String role = res.getString("role");
				Timestamp createdDate= res.getTimestamp("createdDate");
				Timestamp lastLoginDate= res.getTimestamp("lastLoginDate");
				
				u = new User(userId, name, userName, password, email, phone, address, role, createdDate, lastLoginDate);
				
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
