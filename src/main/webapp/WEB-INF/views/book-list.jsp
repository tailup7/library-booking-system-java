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

<%
    List<Book> books =
        (List<Book>) request.getAttribute("books");
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

</body>

</html>