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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		
        Integer sessionCount = (Integer) session.getAttribute("sessionCount");
        if (sessionCount == null) {
            sessionCount = 1;
        } else {
            sessionCount++;
        }
        session.setAttribute("sessionCount", sessionCount);
        
        session.setAttribute("sessionId", session.getId());
        
        /*
         * Context
         */
        
         ServletContext context = getServletContext();
        
        // Récupérer ou initialiser le compteur d'appels
        Integer usageCount = (Integer) context.getAttribute("usageCount");
        if (usageCount == null) {
            usageCount = 0; // Premier appel
        }
        usageCount++; // Incrémenter le compteur
        context.setAttribute("usageCount", usageCount); // Mettre à jour le compteur
        
        // Mettre à jour l'horodate du dernier appel
        String lastAccessTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        context.setAttribute("lastAccessTime", lastAccessTime); // Mettre à jour l'horodate

        
		request.getRequestDispatcher("/views/welcome.jsp").forward(request, response);

	}
}