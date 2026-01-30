<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.atmInterface.entity.User"%>

<%
User user = (User) session.getAttribute("user");
if (user == null) {
	response.sendRedirect("index.jsp");
	return; // stop rendering this page
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ATM Menu</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #1e1e1e;
	color: #fff;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.container {
	width: 500px;
	height: 300px; /* Increased height to fit new option */
	background-color: #2c2c2c;
	border-radius: 15px;
	display: grid;
	grid-template-columns: 1fr 1fr;
	padding: 20px;
	box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.7);
	column-gap: 40px;
}

.option {
	display: flex;
	flex-direction: column;
	justify-content: space-around;
}

.option a {
	text-decoration: none;
	color: white;
	font-size: 15px;
	font-weight: bold;
	background-color: #444;
	padding: 10px;
	border-radius: 8px;
	text-align: center;
	transition: 0.3s;
}

.option a:hover {
	background-color: #008CBA;
}

h2 {
	margin: 0;
}
</style>
</head>
<body>

	<div class="container">
		<%
		String msg = (String) request.getAttribute("message");
		if (msg != null) {
		%>
		<div class="alert alert-info"><%=msg%></div>
		<%
		}
		%>
		<!-- Left Side Options -->
		<div class="option">
			<a href="index.jsp">Cancel ❌</a> <a href="changePin.jsp">Change
				PIN 🔑</a> <a href="deposit.jsp">Deposit 💰</a>
		</div>

		<!-- Right Side Options -->
		<div class="option">
			<a href="withdraw.jsp">Withdraw 💵</a> <a href="balanceEnquiry.jsp">Balance
				Enquiry 💳</a>
		</div>
	</div>
</body>
</html>
