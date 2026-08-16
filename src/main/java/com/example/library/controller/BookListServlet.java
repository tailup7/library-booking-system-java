package com.example.library.controller;

import java.io.IOException;
import java.util.List;

import com.example.library.model.Book;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // 現時点ではDBを使用せず、
        // 動作確認用のダミーデータを作成する
        List<Book> books = List.of(
            new Book(
                1L,
                "9784873119038",
                "Effective Java",
                "Joshua Bloch",
                "O'Reilly",
                2018,
                true
            ),
            new Book(
                2L,
                "9780134685991",
                "Effective Java Third Edition",
                "Joshua Bloch",
                "Addison-Wesley",
                2018,
                false
            ),
            new Book(
                3L,
                "9781492078005",
                "Head First Java",
                "Kathy Sierra",
                "O'Reilly",
                2022,
                true
            )
        );

        // JSPへ渡すデータをrequestに格納する
        request.setAttribute("books", books);

        // JSPへ処理を転送する
        request.getRequestDispatcher(
            "/WEB-INF/views/book-list.jsp"
        ).forward(request, response);
    }
}