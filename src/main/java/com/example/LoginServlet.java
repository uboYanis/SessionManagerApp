package com.example;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String[] roles = request.getParameterValues("roles");

		/*
		 * Session
		 */

		HttpSession session = request.getSession();

		session.setAttribute("username", username);
		
		session.setAttribute("roles", roles);

		session.setAttribute("sessionId", session.getId());

		Integer sessionCount = (Integer) session.getAttribute("sessionCount");

		sessionCount = (sessionCount == null) ? 1 : sessionCount + 1;

		session.setAttribute("sessionCount", sessionCount);

		/*
		 * Context
		 */

		ServletContext context = getServletContext();

		Integer usageCount = (Integer) context.getAttribute("usageCount");

		usageCount = (usageCount == null) ? 1 : usageCount + 1;

		context.setAttribute("usageCount", usageCount);

		String lastAccessTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

		context.setAttribute("lastAccessTime", lastAccessTime);

		request.getRequestDispatcher("/views/welcome.jsp").forward(request, response);
	}
}