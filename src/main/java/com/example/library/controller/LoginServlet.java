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

    // ブラウザのアドレスバーに /login を入力すると、doGetメソッドが実行され、ログイン画面(login.jsp)を表示する
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    // ログイン画面(login.jsp)にて「ログイン」ボタンを押すと、doPostメソッドが実行される。
    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // AuthService.javaのauthenticateメソッドを使って、ユーザ名とパスワードが正しいかどうかを確認する。
        authService.authenticate(username, password).ifPresentOrElse(user -> {
            HttpSession session = request.getSession(true);
            session.setAttribute("loginUser", user);

            //認証に成功したら、ブラウザに、/books というURLにリダイレクトするように指示する。
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