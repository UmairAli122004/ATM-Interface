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
<title>ATM - Change PIN</title>
<style>
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
	animation: fadeIn 1s ease-in-out;
}

h2 {
	margin: 15px 0 10px;
	font-size: 1.1em;
	color: #e94e77;
	letter-spacing: 1px;
}

input[type="password"], input[type="text"] {
	width: 100%;
	padding: 12px;
	margin-bottom: 15px;
	font-size: 1.1em;
	letter-spacing: 6px;
	text-align: center;
	border: 2px solid #444;
	border-radius: 6px;
	background: #1f1f1f;
	color: #fff;
	outline: none;
	transition: border-color 0.3s, background 0.3s;
	box-sizing: border-box;
}

input:focus {
	border-color: #39d9c3;
	background: #282828;
}

button {
	margin-top: 10px;
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

@
keyframes fadeIn {from { opacity:0;
	transform: translateY(-20px);
}

to {
	opacity: 1;
	transform: translateY(0);
}
}
</style>
</head>
<body>
	<div class="container">
		<%
		String msg =(String) request.getAttribute("message");
		if (msg != null) {
		%>
		<%=msg%>
		<%
		}
		%>

		<form action="ChangePinServlet" method="post">
			<h2>Enter Old PIN</h2>
			<input name="oldPin" type="password" required>

			<h2>Enter New PIN</h2>
			<input name="newPin" type="password" required>

			<button type="submit">OK</button>
		</form>
	</div>
</body>
</html>
