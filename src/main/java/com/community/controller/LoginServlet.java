package com.community.controller;

import com.community.util.DBConnection;
import com.community.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email == null || password == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email and password are required.");
            return;
        }
        String sql = "SELECT id, name, password_hash FROM users WHERE email = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email.trim().toLowerCase());
            try (ResultSet result = statement.executeQuery()) {
                if (result.next() && PasswordUtil.verify(password, result.getString("password_hash"))) {
                    request.getSession(true).setAttribute("userId", result.getLong("id"));
                    request.getSession().setAttribute("userName", result.getString("name"));
                    response.sendRedirect(request.getContextPath() + "/complaint");
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Email or password is incorrect.");
                }
            }
        } catch (Exception e) {
            throw new ServletException("Could not log in", e);
        }
    }
}
