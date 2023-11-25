<!DOCTYPE html>
<html>
<head>
  <title>Employee View</title>
</head>
<body>
<h1>Employee Details</h1>
<form action="updateEmployeeController" method="post">
  <label for="firstName">First Name:</label>
  <input type="text" name="firstName" id="firstName" value="${employee.firstName}" required>
  <br>

  <label for="middleName">Middle Name:</label>
  <input type="text" name="middleName" id="middleName" value="${employee.middleName}" required>
  <br>

  <label for="lastName">Last Name:</label>
  <input type="text" name="lastName" id="lastName" value="${employee.lastName}" required>
  <br>

  <label for="mobileNumber">Mobile Number:</label>
  <input type="text" name="mobileNumber" id="mobileNumber" value="${employee.mobileNumber}" required>
  <br>

  <label for="email">Email:</label>
  <input type="email" name="email" id="email" value="${employee.email}" readonly>
  <br>

  <label for="password">Password:</label>
  <input type="password" name="password" id="password" value="${employee.password}"required>
  <br>

  <label for="isAdmin">Is Admin:</label>
  <input type="text" name="isAdmin" id="isAdmin" value="${employee.isAdmin}">
  <br>

  <input type="hidden" name="role" id="role" value="admin">
  <input type="submit" value="Update">
</form>
</body>
</html>
