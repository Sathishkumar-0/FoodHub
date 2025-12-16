package com.tap.inter;

import java.util.List;

import com.tap.pojo.OrderItem;

public interface OrderItemDao {

	public void addOrderItem(OrderItem o);
	public OrderItem getOrderItem(int id);
	public void updateOrderItem(OrderItem o);
	public void deleteOrderItem(int id);
	public List<OrderItem> getAllOrderItem();
}
