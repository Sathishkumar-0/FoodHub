package com.tap.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tap.conect.DBConnection;
import com.tap.inter.OrderDao;
import com.tap.pojo.Order;

public class OrderImpl implements OrderDao{

	private String INSERT="insert into `order` (userId,restaurantId,orderDate,"
				+ "totalAmount,status,paymentMode,address) values(?,?,?,?,?,?,?)";
	private String GET="select * from `order` where orderId=?";
	private String UPDATE="update `order` set restaurantId=?, status=?, paymentMode=?, address=? where orderId=?";
	private String DELETE="delete from `order` where orderId=?";
	private String GET_ALL="select * from `order`";

	@Override
	public int addOrder(Order o) {
			
			int orderId=0;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, o.getUserId());
			pstmt.setInt(2, o.getRestaurantId());
			pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pstmt.setFloat(4, o.getTotalAmount());
			pstmt.setString(5, o.getStatus());
			pstmt.setString(6, o.getPaymentMode());
			pstmt.setString(7, o.getAddress());
			
			int res = pstmt.executeUpdate();
			
			ResultSet ids = pstmt.getGeneratedKeys();
			while(ids.next()) {
				orderId=ids.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderId;
	}

	@Override
	public Order getOrder(int id) {
		
			Order o=null;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET);
			pstmt.setInt(1, id);
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int orderId = res.getInt("orderId");
				int userId = res.getInt("userId");
				int restaurantId = res.getInt("restaurantId");
				Timestamp orderDate = res.getTimestamp("orderDate");
				float totalAmount = res.getFloat("totalAmount");
				String status = res.getString("status");
				String paymentMode = res.getString("paymentMode");
				String address= res.getString("address");
				
				o = new Order(orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMode,address);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public void updateOrder(Order o) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, o.getRestaurantId());
			pstmt.setString(2, o.getStatus());
			pstmt.setString(3, o.getPaymentMode());
			pstmt.setInt(4, o.getOrderId());
			
			int res = pstmt.executeUpdate();
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrder(int id) {

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
	public List<Order> getAllOrder() {

		List<Order> list = new ArrayList<Order>();
		
		Order o=null;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ALL);
			
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int orderId = res.getInt("orderId");
				int userId = res.getInt("userId");
				int restaurantId = res.getInt("restaurantId");
				Timestamp orderDate = res.getTimestamp("orderDate");
				float totalAmount = res.getFloat("totalAmount");
				String status = res.getString("status");
				String paymentMode = res.getString("paymentMode");
				String address = res.getString("address");
				
				o = new Order(orderId, userId, restaurantId, orderDate, totalAmount, status, paymentMode,address);
				list.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
}
