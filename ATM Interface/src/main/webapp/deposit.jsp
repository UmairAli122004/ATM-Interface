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
<title>Fund Transfer</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background: linear-gradient(135deg, #1e1e1e, #121212);
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        color: #fff;
    }
    .container {
        background: #2b2b2b;
        padding: 30px;
        border-radius: 12px;
        box-shadow: 0px 8px 16px rgba(0,0,0,0.8);
        width: 400px;
        text-align: center;
    }
    h2 {
        margin-bottom: 20px;
        color: #f5f5f5;
    }
    h3 {
        text-align: left;
        margin-bottom: 8px;
        font-size: 14px;
        color: #bbb;
    }
    input[type="text"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #444;
        border-radius: 8px;
        font-size: 14px;
        background: #1e1e1e;
        color: #f5f5f5;
    }
    input[type="text"]::placeholder {
        color: #888;
    }
    input[type="text"]:focus {
        border-color: #6a11cb;
        outline: none;
        box-shadow: 0px 0px 8px rgba(106,17,203,0.6);
    }
    button {
        background: linear-gradient(135deg, #6a11cb, #2575fc);
        border: none;
        color: white;
        padding: 12px 20px;
        border-radius: 8px;
        cursor: pointer;
        font-size: 16px;
        font-weight: bold;
        width: 100%;
        transition: 0.3s;
    }
    button:hover {
        background: linear-gradient(135deg, #2575fc, #6a11cb);
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
        <h2>Fund Transfer</h2>
        <form action="DepositServlet" method="post"> 
            <h3>From: Account number</h3>
            <input type="text" name="fromAcc" placeholder="Enter sender account no" value="<%=user.getAccountNumber() %>">

            <h3>To: Account number</h3>
            <input type="text" name="toAcc" placeholder="Enter receiver account no">

            <h3>Enter Amount</h3>
            <input type="text" name="amount" placeholder="Enter amount">

            <button type="submit">Transfer</button>
        </form>
    </div>
</body>
</html>
