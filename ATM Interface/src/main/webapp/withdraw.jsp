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
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Withdraw</title>
<style>
/* Global Styles */
body {
	margin: 0;
	height: 100vh;
	display: flex;
	align-items: center;
	justify-content: center;
	background: linear-gradient(135deg, #1e1e1e, #121212);
	font-family: 'Segoe UI', Tahoma, sans-serif;
}

.container {
	background: #2d2d2d;
	width: 320px;
	padding: 25px 20px 30px;
	border-radius: 10px;
	box-shadow: 0 6px 20px rgba(0, 0, 0, 0.6);
	text-align: center;
}

h1 {
	margin-bottom: 20px;
	font-size: 1.4em;
	color: #e94e77;
	letter-spacing: 1px;
}

h2 {
	margin-bottom: 15px;
	font-size: 1em;
	color: #ddd;
}

/* Input Field */
input[type="text"] {
	width: 100%;
	padding: 12px;
	font-size: 1.1em;
	text-align: center;
	border: 2px solid #444;
	border-radius: 6px;
	background: #1f1f1f;
	color: #fff;
	outline: none;
	transition: border-color 0.3s, background 0.3s;
	box-sizing: border-box;
}

input[type="text"]:focus {
	border-color: #39d9c3;
	background: #282828;
}

/* Button */
button {
	margin-top: 20px;
	width: 60%;
	padding: 12px;
	font-size: 1em;
	color: #fff;
	background: #39d9c3;
	border: none;
	border-radius: 6px;
	cursor: pointer;
	transition: background 0.3s, transform 0.2s;
}

button:hover {
	background: #2bb8a5;
	transform: translateY(-2px);
}

button:active {
	background: #21a490;
	transform: translateY(0);
}
</style>
</head>
<body>
	<div class="container">
		<%
		String msg = (String) request.getAttribute("message");
		if (msg != null) {
		%>
		<%=msg%>
		<%
		}
		%>
		<h1>WITHDRAW</h1>
		<form action="WithdrawServlet" method="post">
			<input type="hidden" name="userId" value=<%=user.getId()%>>
			<h2>ENTER AMOUNT</h2>
			<input type="text" name="withdrawAmount" placeholder="Enter amount">
			<button type="submit">OK</button>
		</form>
	</div>
</body>
</html>
