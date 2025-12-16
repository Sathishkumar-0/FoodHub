package com.tap.inter;

import java.util.List;

import com.tap.pojo.User;

public interface UserDao {

	public void addUser(User u);
	public User getUser(String email);
	public void updateUser(User u);
	public void deleteUser(String email);
	public List<User> getAllUser();
}
