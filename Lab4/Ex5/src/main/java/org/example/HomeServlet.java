package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;

@WebServlet(name = "HomeServlet", urlPatterns = {""})
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");

        if (page == null || (!page.equals("about") && !page.equals("contact") && !page.equals("help"))) {
            response.getWriter().println("Welcome to our website");
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(page + ".jsp");
        dispatcher.forward(request, response);
    }
}


