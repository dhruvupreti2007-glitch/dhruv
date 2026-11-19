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
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (name == null || name.trim().length() < 2 || email == null
                || !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")
                || password == null || password.length() < 8) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Check your name, email, and password (minimum 8 characters).");
            return;
        }
        String sql = "INSERT INTO users (name, email, password_hash) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name.trim());
            statement.setString(2, email.trim().toLowerCase());
            statement.setString(3, PasswordUtil.hash(password));
            statement.executeUpdate();
            response.sendRedirect(request.getContextPath() + "/login?registered=1");
        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) {
                response.sendError(HttpServletResponse.SC_CONFLICT, "That email is already registered.");
            } else {
                throw new ServletException("Could not register user", e);
            }
        }
    }
}
