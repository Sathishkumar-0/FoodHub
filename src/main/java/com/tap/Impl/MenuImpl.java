package com.tap.Impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.conect.DBConnection;
import com.tap.inter.MenuDao;
import com.tap.pojo.Menu;

public class MenuImpl implements MenuDao{

	private String INSERT="insert into menu  (restaurantId,itemName,description,"
				+ "price,ratings,isAvailable,imagePath) values(?,?,?,?,?,?,?)";
	private String GET="select * from menu where restaurantId=?";
	private String GETONE="select * from menu where menuId=?";
	private String UPDATE="update Menu set itemName=?,description=?,price=?,"
					+ "ratings=?,isAvailable=?,imagePath=? where menuId=?";
	private String DELETE="delete from menu where menuId=?";
	private String GET_ALL="select * from menu";

	@Override
	public void addMenu(Menu m) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, m.getRestaurantId());
			pstmt.setString(2, m.getName());
			pstmt.setString(3, m.getDescription());
			pstmt.setFloat(4, m.getPrice());
			pstmt.setFloat(5, m.getRating());
			pstmt.setBoolean(6, m.getIsAvailable());
			pstmt.setString(7, m.getImagePath());
			
			int res = pstmt.executeUpdate();
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Menu> getMenu(int id) {
		
		List<Menu> list = new ArrayList<Menu>();
		Menu m=null;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET);
			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int menuId = res.getInt("menuId");
				int restaurantId = res.getInt("restaurantId");
				String itemName = res.getString("itemName");
				String description = res.getString("description");
				float price = res.getFloat("price");
				float ratings = res.getFloat("ratings");
				boolean isAvailable = res.getBoolean("isAvailable");
				String imagePath = res.getString("imagePath");
				m = new Menu(menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath);
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	@Override
	public Menu getOneMenu(int id) {

		Menu m=null;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GETONE);
			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int menuId = res.getInt("menuId");
				int restaurantId = res.getInt("restaurantId");
				String itemName = res.getString("itemName");
				String description = res.getString("description");
				float price = res.getFloat("price");
				float ratings = res.getFloat("ratings");
				boolean isAvailable = res.getBoolean("isAvailable");
				String imagePath = res.getString("imagePath");
				m = new Menu(menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return m;
	}

	@Override
	public void updateMenu(Menu m) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getDescription());
			pstmt.setFloat(3, m.getPrice());
			pstmt.setFloat(4, m.getRating());
			pstmt.setBoolean(5, m.getIsAvailable());
			pstmt.setString(6, m.getImagePath());
			pstmt.setInt(7, m.getMenuId());
			int res = pstmt.executeUpdate();
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteMenu(int id) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, id);
			int res = pstmt.executeUpdate();
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Menu> getAllMenu() {

		List<Menu> list = new ArrayList<Menu>();
		Menu m=null;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ALL);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int menuId = res.getInt("menuId");
				int restaurantId = res.getInt("restaurantId");
				String itemName = res.getString("itemName");
				String description = res.getString("description");
				float price = res.getFloat("price");
				float ratings = res.getFloat("ratings");
				boolean isAvailable = res.getBoolean("isAvailable");
				String imagePath = res.getString("imagePath");
				m = new Menu(menuId, restaurantId, itemName, description, price, ratings, isAvailable, imagePath);
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
}
