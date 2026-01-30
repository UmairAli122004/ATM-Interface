package com.atmInterface;

import java.io.IOException;
import java.io.Serial;
import java.util.Objects;

import com.atmInterface.entity.User;
import com.atmInterface.repository.UserRepository;
import com.atmInterface.repositoryImpl.UserRepositoryImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PinServlet")
public class PinServlet extends HttpServlet {
	@Serial
	private static final long serialVersionUID = 1L;
	private final UserRepository userRepository;

	public PinServlet() {
		this.userRepository = new UserRepositoryImpl();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	        throws IOException, ServletException {
		
		// ✅ Step 1: Fetch the PIN entered by the user
		String enteredPin = request.getParameter("pin").trim();
		
		// ✅ Step 2: Find the user in the database using entered PIN
		User user = userRepository.findUserByPin(enteredPin);
		
		HttpSession session = request.getSession(); // Create a new session or get existing

		// ✅ Step 3: If user exists, create session and redirect to menu
		if (Objects.nonNull(user)) {
			
			session.setAttribute("user", user);         // Store user object in session
			session.setMaxInactiveInterval(300);        // Add session timeout (5 minutes)
			response.sendRedirect("menu.jsp");          // Redirect to ATM Menu page

		} else {
			// ✅ Step 4: If user does not exist, show error message and forward back to login
			session.setAttribute("message", "<h2>Invalid PIN! Please try again.<h2>");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}
}


/*
package com.atmInterface.servlets;

import java.io.IOException;
import java.util.Objects;

import com.atmInterface.dto.UserDTO;
import com.atmInterface.entity.User;
import com.atmInterface.repository.UserRepository;
import com.atmInterface.repositoryImpl.UserRepositoryImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/PinServlet")
public class PinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserRepository userRepository;

    public PinServlet() {
        this.userRepository = new UserRepositoryImpl();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // 1️⃣ Get the PIN entered by the user
        String enteredPin = request.getParameter("pin").trim();

        // 2️⃣ Find the user in the database
        User user = userRepository.findUserByPin(enteredPin);

        if (Objects.nonNull(user)) {
            // 3️⃣ Convert entity to DTO (omit sensitive info like pin)
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setAccountNumber(user.getAccountNumber());
            userDTO.setAmount(user.getAmount());

            // 4️⃣ Store DTO in session
            HttpSession session = request.getSession();
            session.setAttribute("user", userDTO);

            // 5️⃣ Redirect to menu
            response.sendRedirect("menu.jsp");
        } else {
            // Invalid PIN
            request.setAttribute("message", "<h2>Invalid PIN! Please try again.</h2>");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
*/