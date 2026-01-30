package com.atmInterface;

import java.io.IOException;
import java.io.Serial;

import com.atmInterface.repository.UserRepository;
import com.atmInterface.repositoryImpl.UserRepositoryImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/WithdrawServlet")
public class WithdrawServlet extends HttpServlet{
	@Serial
	private static final long serialVersionUID = 1L;
	private final UserRepository userRepository;

	public WithdrawServlet() {
		this.userRepository = new UserRepositoryImpl();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		if(!(request.getParameter("withdrawAmount").trim()).matches("\\d+")) {
			request.setAttribute("message", "<h2>Please, Enter Amount in digits(0-9).<h2>");
			request.getRequestDispatcher("/withdraw.jsp").forward(request, response);
			return;
		}
		int userId = Integer.parseInt(request.getParameter("userId"));
		int amount = Integer.parseInt(request.getParameter("withdrawAmount"));
		
		// ✅ Input validation
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
		
		try {
			userRepository.withdraw(userId, amount);
			request.getSession().setAttribute("message", "<h2>Withdraw Successfully!</h2>");
			response.sendRedirect("index.jsp");
		}catch(Exception e) {
			request.setAttribute("message", "<h2>Error: " + e.getMessage() + "</h2>");
			request.getRequestDispatcher("/withdraw.jsp").forward(request, response);
		}
		
	}
}
