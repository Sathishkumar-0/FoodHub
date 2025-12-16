package com.tap.inter;

import java.util.List;

import com.tap.pojo.Order;

public interface OrderDao {

	public int addOrder(Order o);
	public Order getOrder(int id);
	public void updateOrder(Order o);
	public void deleteOrder(int id);
	public List<Order> getAllOrder();
}
