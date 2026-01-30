package com.atmInterface;

import java.io.IOException;
import java.io.Serial;

import com.atmInterface.entity.User;
import com.atmInterface.repository.UserRepository;
import com.atmInterface.repositoryImpl.UserRepositoryImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/BalanceEnquiry")
public class BalanceEnquiryServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private final UserRepository userRepository = new UserRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // --- 1) Auth check (session must exist and contain user) ---
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        User sessionUser = (User) session.getAttribute("user");
        if (sessionUser == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        // --- 2) obtain PIN (from session user) and call repository to fetch fresh balance ---
        String pinStr = sessionUser.getPin();
        int pin;
        try {
            pin = Integer.parseInt(pinStr);
        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid user PIN. Please login again.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }

        double balance;
        try {
            // BalanceEnquiry(int pin) should always fetch from DB (setCacheable(false) inside impl)
            balance = userRepository.BalanceEnquiry(pin);
        } catch (Exception ex) {
            // handle DB errors gracefully
            request.setAttribute("message", "Unable to fetch balance: " + ex.getMessage());
            request.getRequestDispatcher("/menu.jsp").forward(request, response);
            return;
        }

        // --- 3) (Optional but helpful) keep session user's amount in sync with DB ---
        sessionUser.setAmount(balance);
        session.setAttribute("user", sessionUser);

        // --- 4) pass data to JSP (IMPORTANT: JSP must use 'balance' request attribute) ---
        request.setAttribute("balance", balance);
        request.setAttribute("user", sessionUser); // can use for name/account number etc.
        request.getRequestDispatcher("/balanceEnquiry.jsp").forward(request, response);
    }
}
