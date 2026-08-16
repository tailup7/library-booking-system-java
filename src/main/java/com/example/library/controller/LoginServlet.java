package com.example.library.controller;

import java.io.IOException;

import com.example.library.model.User;
import com.example.library.service.AuthService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        authService.authenticate(username, password).ifPresentOrElse(user -> {
            HttpSession session = request.getSession(true);
            session.setAttribute("loginUser", user);

            try {
                response.sendRedirect(request.getContextPath() + "/books");
            } catch (IOException exception) {
                throw new RuntimeException(exception);
            }
        }, () -> {
            request.setAttribute("errorMessage", "ユーザー名またはパスワードが正しくありません。");
            request.setAttribute("username", username);

            try {
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
            } catch (ServletException | IOException exception) {
                throw new RuntimeException(exception);
            }
        });
    }
}