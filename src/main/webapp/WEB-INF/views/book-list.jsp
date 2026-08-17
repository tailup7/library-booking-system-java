<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.example.library.model.Book" %>

<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <title>図書一覧</title>
</head>

<body>

<h1>図書一覧</h1>

<form method="get" action="<%= request.getContextPath() %>/books">
    <label for="keyword">検索</label>
    <input type="text" id="keyword" name="keyword"
           value="<%= request.getAttribute("keyword") != null ? request.getAttribute("keyword") : "" %>">
    <button type="submit">検索</button>
</form>

<%
    List<Book> books =
        (List<Book>) request.getAttribute("books");
    Integer currentPage = (Integer) request.getAttribute("currentPage");
    Integer totalPages = (Integer) request.getAttribute("totalPages");
    Boolean hasNextPage = (Boolean) request.getAttribute("hasNextPage");
    String keyword = request.getAttribute("keyword") == null ? "" : (String) request.getAttribute("keyword");
%>

<table border="1">

    <thead>
        <tr>
            <th>ID</th>
            <th>ISBN</th>
            <th>タイトル</th>
            <th>著者</th>
            <th>出版社</th>
            <th>出版年</th>
            <th>状態</th>
        </tr>
    </thead>

    <tbody>

    <% for (Book book : books) { %>

        <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getIsbn() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getPublisher() %></td>
            <td><%= book.getPublicationYear() %></td>

            <td>
                <% if (book.isAvailable()) { %>
                    貸出可能
                <% } else { %>
                    貸出中
                <% } %>
            </td>
        </tr>

    <% } %>

    </tbody>

</table>

<%
    if (currentPage != null && currentPage > 1) {
        String prevPageUrl = request.getContextPath() + "/books?page=" + (currentPage - 1);
        if (keyword != null && !keyword.isEmpty()) {
            prevPageUrl += "&keyword=" + java.net.URLEncoder.encode(keyword, "UTF-8");
        }
%>
    <p>
        <a href="<%= prevPageUrl %>">前ページへ</a>
    </p>
<%
    }

    if (Boolean.TRUE.equals(hasNextPage)) {
        String nextPageUrl = request.getContextPath() + "/books?page=" + (currentPage + 1);
        if (keyword != null && !keyword.isEmpty()) {
            nextPageUrl += "&keyword=" + java.net.URLEncoder.encode(keyword, "UTF-8");
        }
%>
    <p>
        <a href="<%= nextPageUrl %>">次ページへ</a>
    </p>
<%
    }
%>

</body>

</html>