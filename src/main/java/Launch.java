import java.util.List;
import java.util.Scanner;

import com.tap.Impl.MenuImpl;
import com.tap.Impl.OrderImpl;
import com.tap.Impl.OrderItemImpl;
import com.tap.Impl.RestaurantImpl;
import com.tap.Impl.UserImpl;
import com.tap.pojo.Menu;
import com.tap.pojo.Order;
import com.tap.pojo.OrderItem;
import com.tap.pojo.Restaurant;
import com.tap.pojo.User;

public class Launch {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		MenuImpl m1 = new MenuImpl();
		List<Menu> res = m1.getAllMenu();
		for(Menu m:res) {
			System.out.println(m);
		}
	}
}