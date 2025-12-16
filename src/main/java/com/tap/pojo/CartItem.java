package com.tap.pojo;

public class CartItem {
	
	private int cartId;
	private int retaurantId;
	private String name;
	private int quantity;
	private float price;
	
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CartItem(int menuId, int retaurantId, String name, int quantity, float price) {
		super();
		this.cartId = menuId;
		this.retaurantId = retaurantId;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getRetaurantId() {
		return retaurantId;
	}

	public void setRetaurantId(int retaurantId) {
		this.retaurantId = retaurantId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "CartItem [menuId=" + cartId + ", retaurantId=" + retaurantId + ", name=" + name + ", quantity="
				+ quantity + ", price=" + price + "]";
	}
	
	
}
