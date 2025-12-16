package com.tap.inter;

import java.util.List;

import com.tap.pojo.Restaurant;

public interface RestaurantDao {

	public void addRestaurant(Restaurant r);
	public Restaurant getRestaurant(int id);
	public void updateRestaurant(Restaurant r);
	public void deleteRestaurant(int id);
	public List<Restaurant> getAllRestaurant();
	
}
