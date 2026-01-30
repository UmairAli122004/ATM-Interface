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

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
	private final UserRepository userRepository;

	public DepositServlet() {
		this.userRepository = new UserRepositoryImpl();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String usertoAcc = request.getParameter("toAcc").trim();
		
		HttpSession session = request.getSession(false); // don’t create new session
		User user = (User) session.getAttribute("user"); // ✅ get logged in user from session
		
		if (!(request.getParameter("amount").trim()).matches("\\d+")) {
			request.setAttribute("message", "<h2>Please, Enter Amount in digits(0-9).<h2>");
			request.getRequestDispatcher("/deposit.jsp").forward(request, response);
			return;
		}

		double amount = Double.parseDouble(request.getParameter("amount").trim());
		try {
			// ✅ Input validation
			if (amount <= 0) {
				throw new IllegalArgumentException("Amount must be greater than 0");
			}
			userRepository.transfer(user.getId(), usertoAcc, amount);
			request.getSession().setAttribute("message", "<h2>Transfer Successful!</h2>");
			response.sendRedirect("index.jsp");

		} catch (Exception e) {
			request.setAttribute("message", "<h2>Error: " + e.getMessage() + "</h2>");
			request.getRequestDispatcher("/deposit.jsp").forward(request, response);
		}

	}

}
