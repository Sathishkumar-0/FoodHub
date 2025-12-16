<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.* , com.tap.pojo.Menu , com.tap.pojo.User"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>FoodHub</title>
    <style>
      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: "Poppins", sans-serif;
      }

      body {
        background-color: #fafafa;
        color: #333;
      }

      /* Navbar */
      nav {
        background: black;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 12px 60px;
        position: sticky;
        top: 0;
        z-index: 100;
      }

      .logo {
        font-size: 24px;
        font-weight: 700;
        color: red;
        padding: 2px 0;
      }

      nav ul {
        list-style: none;
        display: flex;
        align-items: center;
        gap: 30px;
      }
      nav ul button {
        text-decoration: none;
        background-color: black;
        border: none;
        color: white;
        font-size: 16px;
        margin: 0 0;
        padding: 6px 14px;
        border-radius: 8px;
        transition: 0.3s;
      }
      nav ul button:hover {
        background: #ff4d4d;
        color: #fff;
        cursor: pointer;
      }
      nav ul li a {
        text-decoration: none;
        color: white;
        font-weight: 500;
        padding: 6px 14px;
        border-radius: 8px;
        transition: 0.3s;
      }

      nav ul li a:hover,
      nav ul li a.active {
        background: #ff4d4d;
        color: #fff;
      }

      /* Menu container */
      .menu-container {
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
        gap: 40px;
        padding: 50px 40px;
      }

      .card {
        background: #fff;
        width: 250px;
        border-radius: 16px;
        overflow: hidden;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.5);
        transition: all 0.3s ease;
        display: flex;
        flex-direction: column;
        height: 100%;
      }

      .card:hover {
        transform: translateY(-5px);
        box-shadow: 0 6px 20px rgba(255, 77, 77, 0.25);
      }

      .card img {
        width: 100%;
        height: 180px;
        object-fit: cover;
        display: block;
      }

      .card-content {
        padding: 15px;
        flex: 1; /* makes the content stretch to fill available space */
        display: flex;
        flex-direction: column;
        justify-content: space-between;
      }

      .card-content h3 {
        font-size: 16px;
        color: #222;
        margin-bottom: 5px;
        font-weight: 600;
      }

      .card-content p {
        font-size: 13px;
        color: #777;
        margin-bottom: 10px;
      }

      .card-details {
        display: flex;
        justify-content: space-between;
        align-items: center;
        font-size: 14px;
        margin-bottom: 10px;
      }

      .price {
        font-weight: 600;
        color: #000;
      }

      .rating {
        background: #4caf50;
        color: #fff;
        font-size: 12px;
        padding: 2px 6px;
        border-radius: 6px;
      }

      .availability {
        font-size: 11px;
        background: #2ecc71;
        color: #fff;
        padding: 2px 6px;
        border-radius: 6px;
        width: fit-content;
      }

      .btn {
        background: #ff4d4d;
        border: none;
        color: #fff;
        width: 100%;
        padding: 12px 0;
        border-radius: 0 0 16px 16px;
        font-size: 14px;
        font-weight: 500;
        cursor: pointer;
        transition: all 0.3s ease;
        margin-top: auto;
      }

      .btn:hover {
        background: #e43d3d;
        box-shadow: 0 0 10px rgba(255, 77, 77, 0.4);
      }

      @media (max-width: 768px) {
        nav {
          padding: 12px 25px;
        }

        .menu-container {
          gap: 25px;
          padding: 30px 10px;
        }

        .card {
          width: 90%;
          max-width: 320px;
        }
      }
    </style>
  </head>
  <body>
    <nav>
      <div class="logo">FoodHub</div>
      <ul>
        <% User u=(User)session.getAttribute("user");%>
        <form action="loginServlet" method="post">
          <input type="hidden" name="email" value="<%=u.getEmail()%>" />
          <input type="hidden" name="password" value="<%=u.getPassword()%>" />
          <button>Home</button>
        </form>
        <li><a href="Cart.jsp">Cart</a></li>
        <li><a href="#">Profile</a></li>
        <li><a href="#">About</a></li>
      </ul>
    </nav>

    <div class="menu-container">
      <%
      List<Menu> am=(List<Menu>)request.getAttribute("allmenu");
      for(Menu allmenu:am){
      %>
      <div class="card">
        <img src="<%=allmenu.getImagePath()%>" alt="Menu image not found" />
        <div class="card-content">
          <div>
            <h3><%=allmenu.getName()%></h3>
            <p><%=allmenu.getDescription()%></p>
            <div class="card-details">
              <span class="price">₹<%=allmenu.getPrice()%></span>
              <span class="rating">★ <%=allmenu.getRating()%></span>
            </div>
            <span class="availability"><%=allmenu.getIsAvailable()%></span>
          </div>
        </div>
        <form action="CartServlet" method="post">
        	<input type="hidden" name="restaurantId" value="<%= allmenu.getRestaurantId()%>">
        	<input type="hidden" name="menuId" value="<%= allmenu.getMenuId()%>">
        	<input type="hidden" name="price" value="<%=allmenu.getPrice()%>">
        	<input type="hidden" name="quantity" value="1">
        	<input type="hidden" name="action" value="add">
          <button class="btn">Add to Cart</button>
        </form>
      </div>
      <%
	      }
	    %>
    </div>
  </body>
</html>
