package com.tap.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.conect.DBConnection;
import com.tap.inter.RestaurantDao;
import com.tap.pojo.Restaurant;

public class RestaurantImpl implements RestaurantDao{

	private String ADD="insert into restaurant (name,address,phone,rating,cuisineType,"
				+ "isActive,eta,adminUserId,imagePath) values(?,?,?,?,?,?,?,?,?)";
	private String GET="select * from restaurant where restaurantId=?";
	private String UPDATE="update restaurant set address=?,phone=?,rating=?,"
				+ "cuisineType=?,isActive=?,eta=?,adminUserId=? where restaurantId=?";
	private String DELET="delete from restaurant where restaurantId=?";
	private String GET_ALL="select * from restaurant";

	@Override
	public void addRestaurant(Restaurant r) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(ADD);
			pstmt.setString(1, r.getName());
			pstmt.setString(2, r.getAddress());
			pstmt.setString(3, r.getPhone());
			pstmt.setFloat(4, r.getRating());
			pstmt.setString(5, r.getCuisineType());
			pstmt.setBoolean(6, r.getIsActive());
			pstmt.setInt(7, r.getEta());
			pstmt.setInt(8, r.getAdminUserId());
			pstmt.setString(9, r.getImagePath());
			
			int res = pstmt.executeUpdate();
			System.out.println(res);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Restaurant getRestaurant(int id) {

		Restaurant r=null;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET);
			pstmt.setInt(1, id);
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int restaurantId = res.getInt("restaurantId");
				String name = res.getString("name");
				String address = res.getString("address");
				String phone = res.getString("phone");
				float rating = res.getFloat("rating");
				String cuisineType = res.getString("cuisineType");
				boolean isActive = res.getBoolean("isActive");
				int eta = res.getInt("eta");
				int adminUserId = res.getInt("adminUserId");
				String imagePath = res.getString("imagePath");
				
				r = new Restaurant(restaurantId, name, address, phone, rating, cuisineType, isActive, eta, adminUserId, imagePath);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public void updateRestaurant(Restaurant r) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, r.getAddress());
			pstmt.setString(2, r.getPhone());
			pstmt.setFloat(3, r.getRating());
			pstmt.setString(4, r.getCuisineType());
			pstmt.setBoolean(5, r.getIsActive());
			pstmt.setInt(6, r.getEta());
			pstmt.setInt(7, r.getAdminUserId());
			pstmt.setInt(8, r.getRestaurantId());
			
			int res = pstmt.executeUpdate();
			System.out.println(res);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRestaurant(int id) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(DELET);
			pstmt.setInt(1, id);
			int res = pstmt.executeUpdate();
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Restaurant> getAllRestaurant() {
		
		List<Restaurant> list = new ArrayList<Restaurant>();
		Restaurant r=null;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ALL);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int restaurantId = res.getInt("restaurantId");
				String name = res.getString("name");
				String address = res.getString("address");
				String phone = res.getString("phone");
				float rating = res.getFloat("rating");
				String cuisineType = res.getString("cuisineType");
				boolean isActive = res.getBoolean("isActive");
				int eta = res.getInt("eta");
				int adminUserId = res.getInt("adminUserId");
				String imagePath = res.getString("imagePath");
				
				r = new Restaurant(restaurantId, name, address, phone, rating, cuisineType, isActive, eta, adminUserId, imagePath);
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
}
