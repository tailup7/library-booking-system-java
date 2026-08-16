package com.example.library.controller;

import java.io.IOException;
// @webServlet("/xxx")を使うためのライブラリ。ServletのURLをTomcatに登録するために使う。
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
// HelloServletはHttpServletを継承している。httpServletにはHTTPリクエストを処理するための基本的な機能が用意されている
import jakarta.servlet.http.HttpServlet;
//HTTP通信の リクエスト・レスポンス を扱うためのライブラリ。
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//このServletを /hello というURLで呼び出せるようにする
// 今回はアプリケーション名がlibrary-booking-system-javaなので、
// http://localhost:8080/library-booking-system/hello でアクセスすると、このServletが呼び出される
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        //ServletからJSPにデータを渡すため、requestオブジェクトにデータを保存する
        request.setAttribute(
            "message",                   //key
            "こんにちわ"    //value
        );

        // 処理を渡すJSPを指定する。「現在のrequestとresponseを保持したまま、hello.jspに処理を転送する」
        request.getRequestDispatcher(
            "/WEB-INF/views/hello.jsp"
        ).forward(request, response);
    }
}