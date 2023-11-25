package com.demo.empmanage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/demo";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static EmployeeDAO getInstance(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new EmployeeDAO();
    }

    public Employee getEmployee(String email) {
        Employee employee = null;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM employee WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        employee = getEmployeeFromResultSet(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    public List<Employee> listEmployees() {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT * FROM employee";
            try (Statement statement = connection.createStatement()) {
                try (ResultSet resultSet = statement.executeQuery(query)) {
                    while (resultSet.next()) {
                        employees.add(getEmployeeFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public String addEmployee(Employee employee) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "INSERT INTO employee (firstName, middleName, lastName, mobileNumber, email, password, isAdmin) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getMiddleName());
                statement.setString(3, employee.getLastName());
                statement.setString(4, employee.getMobileNumber());
                statement.setString(5, employee.getEmail());
                statement.setString(6, employee.getPassword());
                statement.setBoolean(7, employee.isAdmin());
                statement.executeUpdate();
                return "Employee With EmailId "+ employee.getEmail()+" Created Successfully";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed To Create Employee With EmailId "+ employee.getEmail();
        }
    }

    public String updateEmployee(Employee employee) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "UPDATE employee SET firstName = ?, middleName = ?, lastName = ?, mobileNumber = ?, password = ?, isAdmin = ? WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, employee.getFirstName());
                statement.setString(2, employee.getMiddleName());
                statement.setString(3, employee.getLastName());
                statement.setString(4, employee.getMobileNumber());
                statement.setString(5, employee.getPassword());
                statement.setBoolean(6, employee.isAdmin());
                statement.setString(7, employee.getEmail());
                statement.executeUpdate();
                return "Employee With EmailId "+ employee.getEmail()+" Updated Successfully";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed To Update Employee With EmailId "+ employee.getEmail();
        }
    }

    public String deleteEmployee(String email) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "DELETE FROM employee WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                statement.executeUpdate();
                return "Employee With EmailId "+ email+" Deleted Successfully";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Failed To Delete Employee With EmailId "+ email;
        }
    }

    public boolean isAdmin(String email) {
        boolean isAdmin = false;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT isAdmin FROM employee WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        isAdmin = resultSet.getBoolean("isAdmin");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isAdmin;
    }


    public boolean hasAdmin() {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            String query = "SELECT COUNT(*) AS count FROM employee WHERE isAdmin = true";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }


    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee=new Employee();
        employee.setFirstName(resultSet.getString("firstName"));
        employee.setMiddleName(resultSet.getString("middleName"));
        employee.setLastName(resultSet.getString("lastName"));
        employee.setMobileNumber(resultSet.getString("mobileNumber"));
        employee.setEmail(resultSet.getString("email"));
        employee.setPassword(resultSet.getString("password"));
        employee.setAdmin(resultSet.getBoolean("isAdmin"));
        return employee;
    }
}

