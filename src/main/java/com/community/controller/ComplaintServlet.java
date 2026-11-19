package com.community.controller;

import com.community.util.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/complaint")
public class ComplaintServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession(false) == null || request.getSession(false).getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT id, title, description, status, created_at FROM complaints WHERE user_id = ? ORDER BY created_at DESC")) {
            statement.setLong(1, (Long) request.getSession(false).getAttribute("userId"));
            try (ResultSet result = statement.executeQuery()) {
                request.setAttribute("complaints", result);
                request.getRequestDispatcher("/complaint.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Could not load complaints", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession(false) == null || request.getSession(false).getAttribute("userId") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        if (title == null || title.trim().length() < 3 || description == null || description.trim().length() < 15) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Enter a title (3+ characters) and description (15+ characters).");
            return;
        }
        String sql = "INSERT INTO complaints (user_id, title, description) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, (Long) request.getSession(false).getAttribute("userId"));
            statement.setString(2, title.trim());
            statement.setString(3, description.trim());
            statement.executeUpdate();
            response.sendRedirect(request.getContextPath() + "/complaint?submitted=1");
        } catch (Exception e) {
            throw new ServletException("Could not submit complaint", e);
        }
    }
}
