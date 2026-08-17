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

    private static final int PAGE_SIZE = 10;
    private final BookDao bookDao = new BookDao();

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        String pageParam = request.getParameter("page");
        int page = 1;

        if (pageParam != null && !pageParam.isBlank()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException ignored) {
                page = 1;
            }
        }

        if (page < 1) {
            page = 1;
        }

        int offset = (page - 1) * PAGE_SIZE;
        var books = bookDao.findByKeyword(keyword, offset, PAGE_SIZE);
        int totalCount = bookDao.countByKeyword(keyword);
        int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);

        request.setAttribute("books", books);
        request.setAttribute("keyword", keyword == null ? "" : keyword);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("hasNextPage", page < totalPages);

        request.getRequestDispatcher(
            "/WEB-INF/views/book-list.jsp"
        ).forward(request, response);
    }
}