<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.* , com.tap.pojo.Restaurant "%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>FoodHub</title>
    <style>
      body {
        margin: 0;
        font-family: "Poppins", sans-serif;
        background: linear-gradient(135deg, #fff, #ffe6e6);
        color: #333;
      }
	  *{
        text-decoration: none;
	  }
      /* Navbar */
      nav {
        background-color: #111;
        padding: 15px 50px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        position: sticky;
        top: 0;
        z-index: 100;
        box-shadow: 0 3px 10px rgba(0, 0, 0, 0.3);
      }

      .logo {
        color: red;
        font-size: 24px;
        font-weight: 700;
        padding: 2px 0;
      }

      nav ul {
        list-style: none;
        display: flex;
        gap: 30px;
        margin: 0;
        padding: 0;
      }

      nav ul li a {
        color: white;
        text-decoration: none;
        font-weight: 500;
        transition: 0.3s;
      }

      nav ul li a:hover {
        color: #ff3333;
      }

      /* Page Heading */
      .heading {
        text-align: center;
        margin: 40px 0 20px;
        font-size: 26px;
        color: #a80000;
        font-weight: 700;
      }

      /* Restaurant Grid */
      .restaurant-container {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(270px, 1fr));
        gap: 25px;
        padding: 0 50px 60px;
      }

      /* Card */
      .restaurant-card {
        background: white;
        border-radius: 15px;
        box-shadow: 3px 4px 10px rgba(0, 0, 0, 0.5);
        overflow: hidden;
        transition: all 0.4s ease;
      }

      .restaurant-card:hover {
        transform: translateY(-8px);
        box-shadow: 0 0 25px rgba(255, 0, 0, 0.5);
      }

      .restaurant-card img {
        width: 100%;
        height: 180px;
        object-fit: cover;
        border-bottom: 2px solid #f5f5f5;
      }

      .restaurant-content {
        padding: 15px 18px 20px;
      }

      .restaurant-content h3 {
        color: #800000;
        font-size: 18px;
        margin: 5px 0;
      }

      .rating {
        background-color: #0f9d58;
        color: white;
        display: inline-block;
        padding: 3px 8px;
        border-radius: 12px;
        font-size: 13px;
        margin-bottom: 8px;
      }

      .restaurant-content p {
        margin: 5px 0;
        font-size: 16px;
      }

      .eta {
        font-weight: bold;
        color: #009933;
      }

      @media (max-width: 768px) {
        nav {
          flex-direction: column;
          text-align: center;
        }
        .restaurant-container {
          padding: 0 20px;
        }
      }
    </style>
  </head>
  <body>
    <nav>
      <div class="logo">FoodHub</div>
      <ul>
        <li><a href="index.html">Home</a></li>
        <li><a href="Cart.jsp">Cart</a></li>
        <li><a href="#">Profile</a></li>
        <li><a href="#">About</a></li>
      </ul>
    </nav>

    <h2 class="heading">🍽️ Top Restaurants</h2>

    <div class="restaurant-container">
      <%
        List<Restaurant> ar=(List<Restaurant>)request.getAttribute("ar");
        for(Restaurant allres:ar){
      %>
      <a href="menu?restaurantId=<%=allres.getRestaurantId()%>">
        <div class="restaurant-card">
          <img
            src="<%=allres.getImagePath()%>"
            alt="Restaurant image not found"
          />
          <div class="restaurant-content">
            <h3><%=allres.getName()%></h3>
            <span class="rating"> <%=allres.getRating()%> ⭐</span>
            <p><strong>Cuisine:</strong> <%=allres.getCuisineType()%></p>
            <p><strong>Address:</strong> <%=allres.getAddress()%></p>
            <p><strong>Phone:</strong> <%=allres.getPhone()%></p>
            <p><strong>Status:</strong> <%=allres.getIsActive()%></p>
            <p class="eta">ETA: <%=allres.getEta()%> mins</p>
          </div>
        </div>
      </a>
      <% 
      }
      %>
    </div>
  </body>
</html>
