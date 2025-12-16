package com.tap.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tap.conect.DBConnection;
import com.tap.inter.OrderItemDao;
import com.tap.pojo.OrderItem;

public class OrderItemImpl implements OrderItemDao{

	private String INSERT="insert into orderitem (orderId,menuId,quantity,totalPrice) values(?,?,?,?)";
	private String GET="select * from orderitem where orderItemId=?";
	private String UPDATE="update orderitem set menuId=?,quantity=?,totalPrice=? where orderItemId=?";
	private String DELETE="delete from orderitem where orderItemId=?";
	private String GET_ALL="select * from orderitem";

	@Override
	public void addOrderItem(OrderItem o) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, o.getOrderId());
			pstmt.setInt(2, o.getMenuId());
			pstmt.setInt(3, o.getQuantity());
			pstmt.setFloat(4, o.getTotalPrice());
			int res = pstmt.executeUpdate();
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public OrderItem getOrderItem(int id) {

		OrderItem o=null;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET);
			pstmt.setInt(1, id);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int orderItemId = res.getInt("orderItemId");
				int orderId = res.getInt("orderId");
				int menuId = res.getInt("menuId");
				int quantity = res.getInt("quantity");
				float totalPrice = res.getFloat("totalPrice");
				o = new OrderItem(orderItemId, orderId, menuId, quantity, totalPrice);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}

	@Override
	public void updateOrderItem(OrderItem o) {

		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, o.getMenuId());
			pstmt.setInt(2, o.getQuantity());
			pstmt.setFloat(3, o.getTotalPrice());
			pstmt.setInt(4, o.getOrderItemId());
			
			int res = pstmt.executeUpdate();
			System.out.println(res);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrderItem(int id) {

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
	public List<OrderItem> getAllOrderItem() {

		List<OrderItem> list = new ArrayList<OrderItem>();
		OrderItem o=null;
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement pstmt = con.prepareStatement(GET_ALL);
			ResultSet res = pstmt.executeQuery();
			while(res.next()) {
				int orderItemId = res.getInt("orderItemId");
				int orderId = res.getInt("orderId");
				int menuId = res.getInt("menuId");
				int quantity = res.getInt("quantity");
				float totalPrice = res.getFloat("totalPrice");
				o = new OrderItem(orderItemId, orderId, menuId, quantity, totalPrice);
				list.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
}
