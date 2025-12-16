package com.tap.inter;

import java.util.List;

import com.tap.pojo.Menu;

public interface MenuDao {

	public void addMenu(Menu m);
	public List<Menu> getMenu(int id);
	public Menu getOneMenu(int id);
	public void updateMenu(Menu m);
	public void deleteMenu(int id);
	public List<Menu> getAllMenu();
}
