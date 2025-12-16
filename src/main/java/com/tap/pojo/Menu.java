package com.tap.pojo;

public class Menu {

	private int menuId;
	private int restaurantId;
	private String name;
	private String description;
	private float price;
	private float rating;
	private boolean isAvailable;
	private String imagePath;
	
	public Menu() {

	}

	
	public Menu(int restaurantId, String name, String description, float price, float rating, boolean isAvailable,
			String imagePath) {
		super();
		this.restaurantId = restaurantId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.isAvailable = isAvailable;
		this.imagePath = imagePath;
	}


	public Menu(int menuId, int restaurantId, String name, String description, float price, float rating,
			boolean isAvailable, String imagePath) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.name = name;
		this.description = description;
		this.price = price;
		this.rating = rating;
		this.isAvailable = isAvailable;
		this.imagePath = imagePath;
	}

	
	public int getMenuId() {
		return menuId;
	}


	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}


	public int getRestaurantId() {
		return restaurantId;
	}


	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public float getRating() {
		return rating;
	}


	public void setRating(float rating) {
		this.rating = rating;
	}


	public boolean getIsAvailable() {
		return isAvailable;
	}


	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", restaurantId=" + restaurantId + ", name=" + name + ", description="
				+ description + ", price=" + price + ", rating=" + rating + ", isAvailable=" + isAvailable
				+ ", imagePath=" + imagePath + "]";
	}
	
	
}
