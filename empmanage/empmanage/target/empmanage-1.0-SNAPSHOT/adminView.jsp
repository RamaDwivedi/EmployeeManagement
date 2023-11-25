<%@ page import="com.demo.empmanage.Employee" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin View</title>
</head>
<body>
<h1>Employee List</h1>
<% if (request.getAttribute("message") != null) { %>
<p><%= request.getAttribute("message") %></p>
<% } %>
<table border="1">
    <tr>
        <th>First Name</th>
        <th>Middle Name</th>
        <th>Last Name</th>
        <th>Mobile Number</th>
        <th>Email</th>
        <th>Password</th>
        <th>Is Admin</th>
        <th>Actions</th>
    </tr>
    <%
        List<Employee> employeeList = (List<Employee>) request.getAttribute("employeeList");
        if (employeeList != null) {
            for (Employee employee : employeeList) {
    %>
        <tr>
            <td><%= employee.getFirstName() %></td>
            <td><%= employee.getMiddleName() %></td>
            <td><%= employee.getLastName() %></td>
            <td><%= employee.getMobileNumber() %></td>
            <td><%= employee.getEmail() %></td>
            <td><%= employee.getPassword() %></td>
            <td><%= employee.isAdmin() ? "Yes" : "No" %></td>
            <td>
                <form action="deleteEmployee" method="post">
                    <input type="hidden" name="email" value="<%= employee.getEmail() %>">
                    <input type="submit" value="Delete">
                </form>

                <form action="updateEmployee" method="post">
                    <input type="hidden" name="email" value="<%= employee.getEmail() %>">
                    <input type="submit" value="Update">
                </form>
            </td>
        </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>