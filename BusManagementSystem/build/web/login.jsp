<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Dang nhap</title></head>
<body>
    <h3>Dang nhap he thong</h3>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p style="color:red"><%= error %></p>
    <%
        }
    %>

    <form method="post" action="login">
        <p>Ten dang nhap: <input type="text" name="username"></p>
        <p>Mat khau: <input type="password" name="password"></p>
        <input type="submit" value="Dang nhap">
    </form>
</body>
</html>
