package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private HashMap<String, String> userAccounts;

    @Override
    public void init() throws ServletException {
        // Initialize some pre-built accounts
        userAccounts = new HashMap<>();
        userAccounts.put("user1", "password1");
        userAccounts.put("user2", "password2");
        userAccounts.put("admin", "admin123");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect back to the index.jsp page
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the content type to HTML
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve username and password from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Check if the username exists and the password matches
        PrintWriter out = response.getWriter();
        if (userAccounts.containsKey(username) && userAccounts.get(username).equals(password)) {
            out.println("<html><body>");
            out.println("<h3>Name/Password match</h3>");
            out.println("</body></html>");
        } else {
            out.println("<html><body>");
            out.println("<h3>Name/Password does not match</h3>");
            out.println("</body></html>");
        }
    }
}
