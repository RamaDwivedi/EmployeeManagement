<!DOCTYPE html>
<html>
<head>
  <title>Employee Signup</title>
</head>
<body>
<h1>Employee Signup</h1>
<% if (request.getAttribute("errorMessage") != null) { %>
<p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
<% } %>
<form action="signupController" method="post">
  <label>First Name: <span style="color: red;">*</span></label>
  <input type="text" name="firstName" required>
  <br>
  <label>Middle Name:</label>
  <input type="text" name="middleName">
  <br>
  <label>Last Name: <span style="color: red;">*</span></label>
  <input type="text" name="lastName" required>
  <br>
  <label>Mobile Number (10 digits): <span style="color: red;">*</span></label>
  <input type="text" name="mobileNumber" required pattern="[0-9]{10}" title="Please enter a 10-digit mobile number">
  <br>
  <label>Email Id: <span style="color: red;">*</span></label>
  <input type="email" name="email" required>
  <br>
  <label>Password: <span style="color: red;">*</span></label>
  <input type="password" name="password" required>
  <br>
  <input type="submit" value="Signup">
</form>
<p>Already have an account? <a href="login.jsp">Login</a></p>
</body>
</html>
