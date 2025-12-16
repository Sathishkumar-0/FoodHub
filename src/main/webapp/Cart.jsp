<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.tap.pojo.Cart,com.tap.pojo.CartItem,com.tap.pojo.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>FoodHub</title>
  <style>
    body {
      font-family: "Poppins", sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f6f6f6;
    }

    /* Navbar */
    .navbar {
      background-color: black;
      padding: 12px 20px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
      display: flex;
      justify-content: space-between;
      align-items: center;
      position: sticky;
      top: 0;
      z-index: 100;
    }

    .logo {
      font-weight: 700;
      font-size: 24px;
      color: red;
      padding: 2px 0;
    }

    .nav-links {
      display: flex;
      align-items:center;
      gap: 25px;
    }
	.hom>button{
	 text-decoration: none;
	 border:none;
	 background-color:black;
      color: white;
      font-size:17px;
      text-align:center;
      cursor:pointer;
	  padding:6px;
      transition: 0.3s;
	  border-radius: 10px;
	}
	.hom>button:hover{
	  color: white;
	  background-color: red;
	}
    .nav-links a {
      text-decoration: none;
      color: white;
	  border-radius: 10px;
	  padding:6px;
      font-weight: 500;
      transition: 0.3s;
    }

    .nav-links a:hover {
      color: white;
      background-color: red;
    }

    /* Cart Container */
    .cart-container {
      max-width: 700px;
      margin: 30px auto;
      background-color: white;
      border-radius: 16px;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
      padding: 20px;
    }

    .cart-title {
      font-size: 22px;
      font-weight: 600;
      color: #ff3c00;
      text-align: center;
      margin-bottom: 20px;
    }

    .cart-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-bottom: 1px solid #eee;
      padding: 15px 0;
    }

    .item-info {
      display: flex;
      flex-direction: column;
      gap: 4px;
    }

    .item-name {
      font-size: 15px;
      font-weight: 600;
      color: #333;
    }

    .item-price {
      font-size: 14px;
      color: #666;
    }

    .quantity-controls {
      display: flex;
      align-items: center;
      gap: 10px;
    }

    .btn {
      width: 28px;
      height: 28px;
      border-radius: 6px;
      border: none;
      background-color: #ff3c00;
      color: white;
      font-size: 18px;
      font-weight: bold;
      cursor: pointer;
      transition: 0.3s;
    }
    .btm {
      border-radius: 6px;
      border: none;
      background-color: #ff3c00;
      color: white;
      font-size: 18px;
      font-weight: bold;
      cursor: pointer;
      transition: 0.3s;
      margin-top:5px;
      text-decoration: none;
    }
    .btm a{
      color: white;
      text-decoration: none;
    }
	.btm:hover {
      background-color: #e63600;
    }
    .btn:hover {
      background-color: #e63600;
    }
	.empty{
		display: flex;
		flex-direction:column;
		justify-content: center;
		align-items: center;
		color: gray;
	}
    .quantity {
      font-weight: 600;
      color: #333;
    }

    /* Total section */
    .total {
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-weight: 600;
      font-size: 16px;
      padding-top: 20px;
      color: #333;
    }

    .checkout-btn {
      margin-top: 20px;
      width: 100%;
      padding: 10px 0;
      background-color: #ff3c00;
      color: white;
      border: none;
      border-radius: 10px;
      font-weight: 600;
      font-size: 15px;
      cursor: pointer;
      transition: 0.3s;
    }

    .checkout-btn:hover {
      background-color: #e63600;
    }
  </style>
</head>
<body>
  <!-- Navbar -->
  <div class="navbar">
    <div class="logo">FoodHub</div>
    <div class="nav-links">
    <% User u=(User)session.getAttribute("user");%>
      <form action="loginServlet" method="post" class="hom">
      <input type="hidden" name="email" value="<%=u.getEmail()%>">
      <input type="hidden" name="password" value="<%=u.getPassword()%>">
      <button>Home</button>
      </form>
      <a href="#">Profile</a>
    </div>
  </div>

  <!-- Cart Container -->
  <div class="cart-container">
    <div class="cart-title">Your Cart</div>
	<%
		Cart cart=(Cart)session.getAttribute("cart");
		int restaurantId =(Integer)session.getAttribute("restaurantId");
		if(cart !=null && !cart.getItems().isEmpty()){
			for(CartItem item:cart.getItems().values()){	
		%>
    <!-- Cart Item 1 -->
    <div class="cart-item">
      <div class="item-info">
        <div class="item-name"><%=item.getName()%></div>
        <div class="item-price">₹<%=item.getPrice()%></div>
      </div>
      <div class="quantity-controls">
      	 <form action="CartServlet" method="post">
        	<input type="hidden" name="restaurantId" value="<%= item.getRetaurantId()%>">
        	<input type="hidden" name="menuId" value="<%= item.getCartId()%>">
        	<input type="hidden" name="quantity" value="<%=item.getQuantity()-1%>">
        	<input type="hidden" name="action" value="update">
      		<button class="btn">−</button>
        </form>
       <span class="quantity"><%=item.getQuantity()%></span>
         <form action="CartServlet" method="post">
        	<input type="hidden" name="menuId" value="<%= item.getCartId()%>">
        	<input type="hidden" name="restaurantId" value="<%= item.getRetaurantId()%>">
        	<input type="hidden" name="quantity" value="<%= item.getQuantity()+1%>">
        	<input type="hidden" name="action" value="update">
	        <button class="btn">+</button>
        </form>
      </div>
      	
    </div>
       	<form action="CartServlet" method="post">
        	<input type="hidden" name="restaurantId" value="<%= item.getRetaurantId()%>">
        	<input type="hidden" name="menuId" value="<%= item.getCartId()%>">
        	<input type="hidden" name="action" value="remove">
	        <button class="btm">Remove</button>
       </form> 
	
	<%
		}	
	%>
	<a href="menu?restaurantId=<%=session.getAttribute("restaurantId")%>"><button class="btm">add more items</button> </a>
    <!-- Total -->
    <div class="total">
      <span>Total</span>
      <span>₹<%=cart.getTotelPrice()%><% session.setAttribute("totalAmount", cart.getTotelPrice());%></span>
    </div>
    <a href="Checkout.jsp"><button class="checkout-btn">Proceed to Checkout</button></a>
    	<%
		}	
		else {
			%>
			<div class="empty">
				<p>Your cart is empty</p>
				<form action="loginServlet" method="post">
				<input type="hidden" name="email" value="<%=u.getEmail()%>">
				<input type="hidden" name="password" value="<%=u.getPassword()%>">
				<button class="btm">Go on purches</button> 
				</form>
			</div>
		<%
		}
	%>
  </div>
</body>
</html>
