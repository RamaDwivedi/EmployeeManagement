<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h1>Login</h1>
<% if (request.getAttribute("message") != null) { %>
<p><%= request.getAttribute("message") %></p>
<% } %>

<% if (request.getAttribute("errorMessage") != null) { %>
    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
<% } %>
<form action="loginController" method="post">
    <label>Email:</label>
    <input type="email" name="email" required>
    <br>
    <label>Password:</label>
    <input type="password" name="password" required>
    <br>
    <input type="submit" value="Login">
</form>
<p>New User? <a href="signup.jsp">Sign Up</a></p>
</body>
</html>