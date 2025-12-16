<%@page import="com.tap.Impl.RestaurantImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.tap.pojo.Cart, com.tap.pojo.Restaurant, com.tap.pojo.CartItem, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Foodly | Checkout</title>
    <style>
      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: "Poppins", sans-serif;
      }

      body {
        background: #fafafa;
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
        gap: 30px;
      }

      nav ul li a {
        text-decoration: none;
        color: white;
        font-weight: 500;
        padding: 6px 14px;
        border-radius: 8px;
        transition: 0.3s;
      }

      nav ul li a:hover {
        background: #ff4d4d;
        color: #fff;
      }

      /* Checkout Container */
      .checkout-container {
        max-width: 950px;
        margin: 50px auto;
        background: #fff;
        border-radius: 16px;
        box-shadow: 0 4px 15px rgba(0, 0, 0, 0.08);
        padding: 40px;
      }

      h2 {
        color: #222;
        font-size: 22px;
        margin-bottom: 20px;
        border-left: 4px solid #ff4d4d;
        padding-left: 10px;
      }

      .section {
        margin-bottom: 35px;
      }

      label {
        display: block;
        margin-bottom: 8px;
        font-weight: 500;
      }

      input,
      select,
      textarea {
        width: 100%;
        padding: 10px 12px;
        border: 1px solid #ccc;
        border-radius: 8px;
        outline: none;
        font-size: 14px;
        transition: 0.2s;
      }

      input:focus,
      textarea:focus,
      select:focus {
        border-color: #ff4d4d;
        box-shadow: 0 0 5px rgba(255, 77, 77, 0.3);
      }

      textarea {
        resize: none;
        height: 80px;
      }

      /* Order summary */
      .summary {
        border-top: 1px solid #eee;
        padding-top: 15px;
      }

      .summary-item {
        display: flex;
        justify-content: space-between;
        margin-bottom: 10px;
        font-size: 14px;
      }

      .summary-item.total {
        font-weight: 600;
        color: #000;
        border-top: 1px solid #eee;
        padding-top: 10px;
      }

      /* Payment methods */
      .payment-options {
        display: flex;
        gap: 15px;
        flex-wrap: wrap;
        margin-top: 10px;
      }

      .payment-option {
        border: 1px solid #ccc;
        border-radius: 10px;
        padding: 12px 20px;
        cursor: pointer;
        transition: 0.3s;
      }

      .payment-option:hover {
        border-color: #ff4d4d;
        box-shadow: 0 0 8px rgba(255, 77, 77, 0.3);
      }

      .payment-option input {
        margin-right: 10px;
      }

      /* Place order button */
      .place-order {
        background: #ff4d4d;
        color: #fff;
        border: none;
        width: 100%;
        padding: 14px;
        font-size: 16px;
        border-radius: 10px;
        cursor: pointer;
        transition: 0.3s;
        font-weight: 500;
      }

      .place-order:hover {
        background: #e23c3c;
        box-shadow: 0 0 10px rgba(255, 77, 77, 0.4);
      }

      /* Estimated time */
      .eta {
        font-weight: 600;
        color: #4caf50;
        margin-top: 8px;
      }

      @media (max-width: 768px) {
        nav {
          padding: 12px 25px;
        }

        .checkout-container {
          padding: 25px;
          margin: 30px 15px;
        }
      }
    </style>
  </head>
  <body>
    <!-- Navbar -->
    <nav>
      <div class="logo">Foodly</div>
      <ul>
        <li><a href="#">Home</a></li>
        <li><a href="Cart.jsp">Cart</a></li>
        <li><a href="#" class="active">Checkout</a></li>
        <li><a href="#">Profile</a></li>
      </ul>
    </nav>

    <!-- Checkout Section -->
   <form action="checkoutServlet" method="post">
   	 <div class="checkout-container">
      <div class="section">
        <h2>Delivery Address</h2>
        <label>Full Name</label>
        <input type="text" placeholder="Enter your name" required />

        <label>Phone Number</label>
        <input type="tel" placeholder="Enter your phone number" required />

        <label>Address</label>
        <textarea
          placeholder="House No, Street, Area, City"
          name="address" required="required"
        ></textarea>

        <label>Pincode</label>
        <input type="text" placeholder="Pincode" />
      </div>

      <div class="section">
        <h2>Order Summary</h2>
        <% Cart cart=(Cart)session.getAttribute("cart");
        int restaurantId=(Integer)session.getAttribute("restaurantId");
        RestaurantImpl ri=new RestaurantImpl();
        Restaurant res=ri.getRestaurant(restaurantId);
        	for(CartItem ci : cart.getItems().values()){
        %>
        <div class="summary">
          <div class="summary-item">
          	<span><%=ci.getName()%></span>
          	<span>Quantity:<%=ci.getQuantity()%></span>
            <span>₹ <%=ci.getPrice() %></span>
          </div>
        <%
        }
        %>
          <div class="summary-item total">
            <span>Total</span><span>Amount: ₹<%=cart.getTotelPrice()%></span>
          </div>
        </div>
      </div>

      <div class="section">
        <h2>Payment Method</h2>
        <div class="payment-options">
        	<select class="payment-options" name="payment">
        		<option value="" disabled selected>select payemnt method</option>
        		<option name="payment">UPI</option>
        		<option name="payment">Credit / Debit Card</option>
        		<option name="payment">Net Banking</option>
        		<option name="payment">Cash on Deliverty</option>
        	</select>
          
        </div>
      </div>

      <div class="section">
        <h2>Estimated Delivery Time</h2>
        <p>
          Your delicious food will arrive in
          <span class="eta"><%=res.getEta() %>–<%=res.getEta()+10%> minutes</span>.
        </p>
      </div>

      <button class="place-order">Place Order</button>
    </div>
   </form>
  </body>
</html>
