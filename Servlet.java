package com.community.controller;

import com.community.util.DBConnection;
import com.community.util.PasswordUtil;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (name == null || name.trim().isEmpty()
                || email == null
                || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
                || password == null || password.length() < 6) {

            response.getWriter().println("Invalid registration details.");
            return;
        }

        String sql = "INSERT INTO users(name,email,password_hash) VALUES(?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name.trim());
            ps.setString(2, email.trim().toLowerCase());
            ps.setString(3, PasswordUtil.hash(password));

            ps.executeUpdate();

            response.sendRedirect("login.jsp?registered=1");

        } catch (SQLIntegrityConstraintViolationException e) {

            response.getWriter().println(
                "Email already registered!");

        } catch (SQLException e) {

            throw new ServletException(e);
        }
    }
}