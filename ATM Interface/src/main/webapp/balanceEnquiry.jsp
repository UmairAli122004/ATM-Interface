<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.atmInterface.entity.User"%>

<%
    // Prefer request attributes (fresh values from servlet).
    User user = (User) request.getAttribute("user");
    if (user == null) {
        user = (User) session.getAttribute("user"); // fallback for non-authenticated flows
    }
    if (user == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    // The servlet always sets a 'balance' request attribute (Double).
    Double balanceObj = (Double) request.getAttribute("balance");
    double balance;
    if (balanceObj != null) {
        balance = balanceObj.doubleValue();
    } else {
        // fallback only if servlet didn't set it (shouldn't happen if flow is correct)
        balance = user.getAmount();
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ATM - Balance Enquiry</title>
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
        margin-bottom: 20px;
        font-size: 1.4em;
        color: #e94e77;
        letter-spacing: 1px;
    }

    .amount-box {
        font-size: 22px;
        font-weight: bold;
        color: #39d9c3;
        margin: 20px 0;
        padding: 12px;
        border: 2px solid #39d9c3;
        border-radius: 8px;
        background: #1f1f1f;
    }

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

    @keyframes fadeIn {
        from { opacity: 0; transform: translateY(-20px); }
        to { opacity: 1; transform: translateY(0); }
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Your Balance</h2>
        <div class="amount-box">
            ₹ <%= String.format("%.2f", balance) %>
        </div>

        <form action="index.jsp" method="get">
            <button type="submit">OK</button>
        </form>
    </div>
</body>
</html>
