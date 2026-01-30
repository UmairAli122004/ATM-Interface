<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>ATM Interface</title>
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
	width: 300px; /* reduced */
	padding: 25px 20px 30px;
	border-radius: 10px;
	box-shadow: 0 6px 20px rgba(0, 0, 0, 0.6);
	text-align: center;
}

h1 {
	margin-bottom: 20px;
	font-size: 1.5em; /* reduced */
	color: #e94e77;
	letter-spacing: 1px;
}

h2 {
	margin-bottom: 15px;
	font-size: 1em; /* reduced */
	color: #ddd;
}

/* PIN Input */
input[type="password"] {
	width: 100%;
	padding: 12px;
	font-size: 1.1em; /* reduced */
	letter-spacing: 8px;
	text-align: center;
	border: 2px solid #444;
	border-radius: 6px;
	background: #1f1f1f;
	color: #fff;
	outline: none;
	transition: border-color 0.3s, background 0.3s;
	box-sizing: border-box; /* ✅ Fixes overflow */
}

input[type="password"]:focus {
	border-color: #39d9c3;
	background: #282828;
}

/* Submit Button */
button {
	margin-top: 20px;
	width: 60%;
	padding: 12px;
	font-size: 1em; /* reduced */
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
		<div id="message-box">
			<%
			String msg = (String) session.getAttribute("message");
			if (msg != null) {
				out.println(msg);
				session.removeAttribute("message"); // so it doesn’t show again on refresh
			}
			%>
		</div>
		<h1>ATM INTERFACE</h1>
		<form action="PinServlet" method="post">
			<h2>ENTER PIN</h2>
			<input type="password" name="pin">
			<button type="submit">OK</button>
		</form>
	</div>
	<script>
	   const msg = document.getElementById("message-box");
	   if(msg){
		   setTimeout(() => msg.style.display = "none", 10000);
	   }
	</script>
</body>
</html>