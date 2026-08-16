package com.example.library.controller;

import java.io.IOException;

import com.example.library.dao.BookDao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {

    private final BookDao bookDao = new BookDao();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        var books = bookDao.findAll();

        // JSPへ渡すデータをrequestに格納する
        request.setAttribute("books", books);

        // JSPへ処理を転送する
        request.getRequestDispatcher(
            "/WEB-INF/views/book-list.jsp"
        ).forward(request, response);
    }
}