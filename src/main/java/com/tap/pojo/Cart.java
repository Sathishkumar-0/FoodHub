package com.tap.pojo;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<Integer,CartItem> items;
	
	public Cart() {
		this.items=new HashMap<>();
	}
	public void addItem(CartItem item) {
		int cartId = item.getCartId();
		if(items.containsKey(cartId)) {
			CartItem existingItem = items.get(cartId);
			int newQua = item.getQuantity();
			int oldQua = existingItem.getQuantity();
			int sumqua =newQua+oldQua;
			existingItem.setQuantity(sumqua);
			
		}
		else {
			items.put(cartId, item);
		}
	}
	
	public double getTotelPrice() {
		return items.values().stream().mapToDouble(item->item.getPrice()*item.getQuantity()).sum();
	}
	public void updateItem(int menuId, int quantity) {
		
		if(items.containsKey(menuId)) {
			if (quantity <= 0) {
				items.remove(menuId);
			}else {
				CartItem existingItem = items.get(menuId);
				existingItem.setQuantity(quantity);
			}
		}
	}
	
	public void removeItem(int menuId) {
		 if(items.containsKey(menuId)) {
			 items.remove(menuId);
		 }
	}
	
	public Map<Integer, CartItem> getItems() {
		return items;
	}
	
	public void setItems(Map<Integer, CartItem> items) {
		this.items = items;
	}
	
	
}
