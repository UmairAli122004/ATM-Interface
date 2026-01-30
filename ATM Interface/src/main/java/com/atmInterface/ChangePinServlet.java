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

@WebServlet("/ChangePinServlet")
public class ChangePinServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private final UserRepository userRepository;

    public ChangePinServlet() {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        HttpSession session = request.getSession(false); // don’t create new session
        User user = (User) session.getAttribute("user"); // ✅ get logged in user from session
  
        String oldPin = request.getParameter("oldPin").trim();
        String newPin = request.getParameter("newPin").trim();

        if (user == null) {
            // not logged in
            response.sendRedirect("index.jsp");
            return;
        }

        // Verify old pin
        if (!user.getPin().equals(oldPin)) {
            request.setAttribute("message", "<h2>Old Pin Wrong! Please Try Again!<h2>");
            request.getRequestDispatcher("/changePin.jsp").forward(request, response);
            return;
        }

        // Check if newPin contains only digits
        if (!newPin.matches("\\d+")) {
            request.setAttribute("message", "<h2>PIN must contain only digits.<h2>");
            request.getRequestDispatcher("/changePin.jsp").forward(request, response);
            return;
        }

        // Check length
        if (newPin.length() == 4 || newPin.length() == 6) {
            userRepository.changePin(user.getId(),  newPin);
            user.setPin(newPin); // ✅ update session user too
            session.setAttribute("user", user);
            session.setAttribute("message", "<h2>Your PIN has been changed successfully.<h2>");
            response.sendRedirect("index.jsp"); //.forward(request, response);
        } else {
            request.setAttribute("message", "<h2>PIN must be 4 or 6 digits.<h2>");
            request.getRequestDispatcher("/changePin.jsp").forward(request, response);
        }
    }
}
