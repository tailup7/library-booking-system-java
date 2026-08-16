<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
</head>

<body>

<h1>ログイン</h1>

<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null) { %>
    <p style="color: red;"><%= errorMessage %></p>
<% } %>

<form method="post" action="<%= request.getContextPath() %>/login">
    <div>
        <label for="username">ユーザー名</label>
        <input type="text" id="username" name="username"
               value="<%= request.getAttribute("username") != null ? request.getAttribute("username") : "" %>">
    </div>

    <div>
        <label for="password">パスワード</label>
        <input type="password" id="password" name="password">
    </div>

    <div>
        <button type="submit">ログイン</button>
    </div>
</form>

</body>

</html>