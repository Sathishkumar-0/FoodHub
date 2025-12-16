<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.tap.pojo.User"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <title>Order Confirmed</title>
    <style>
      body {
        margin: 0;
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(135deg, #ff6a00, #ff3d00);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
      }

      .card {
        background: #ffffff;
        width: 420px;
        padding: 40px 35px;
        border-radius: 18px;
        text-align: center;
        box-shadow: 0 20px 40px rgba(0, 0, 0, 0.2);
      }

      .icon {
        font-size: 60px;
        margin-bottom: 15px;
      }

      h1 {
        margin: 0;
        color: #222;
      }

      .username {
        color: #ff3d00;
        font-weight: 600;
      }

      p {
        margin-top: 15px;
        color: #555;
        line-height: 1.6;
        font-size: 15px;
      }

      .highlight {
        margin: 20px 0;
        font-size: 14px;
        color: #333;
        background: #fff3e0;
        padding: 12px;
        border-radius: 10px;
      }

      .btn {
        display: inline-block;
        margin-top: 25px;
        padding: 14px 30px;
        background: #ff3d00;
        color: #fff;
        text-decoration: none;
        border-radius: 30px;
        font-size: 15px;
        font-weight: 500;
        transition: transform 0.2s, box-shadow 0.2s;
      }

      .btn:hover {
        transform: translateY(-2px);
        box-shadow: 0 8px 18px rgba(255, 61, 0, 0.4);
      }
    </style>
  </head>
  <body>
    <% User user=(User)session.getAttribute("user");%>
    <div class="card">
      <div class="icon">🎉</div>

      <h1>Order Confirmed!</h1>

      <p>
        Hey <span class="username"><%=user.getUsername()%></span> 👋 <br />
        Your order has been successfully placed.
      </p>

      <div class="highlight">
        Our chefs are already at work 🍳 Fresh flavors, hot meals, and happy
        moments are on the way!
      </div>

      <p>
        Thank you for choosing <strong>FoodHub</strong>. We can’t wait to serve
        you again.
      </p>

      <form action="loginServlet" method="post">
      <input type="hidden" name="email" value="<%=user.getEmail()%>">
      <input type="hidden" name="password" value="<%=user.getPassword()%>">
      <button class="btn"> 🍽️ Explore More Restaurants </button>
      </form>
    </div>
  </body>
</html>
