package com.demo.empmanage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signupController")
public class SignupController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String mobileNumber = request.getParameter("mobileNumber");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if (firstName.isEmpty() || lastName.isEmpty() || mobileNumber.isEmpty() || email.isEmpty() || password.isEmpty()) {
            request.setAttribute("errorMessage", "Please Fill All The Required Fields");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
            return;
        }

        EmployeeDAO employeeDAO=EmployeeDAO.getInstance();
        Employee employee=new Employee();
        employee.setEmail(email);
        employee.setPassword(password);
        employee.setMobileNumber(mobileNumber);
        employee.setFirstName(firstName);
        employee.setMiddleName(middleName);
        employee.setLastName(lastName);
        employee.setAdmin(!employeeDAO.hasAdmin());
        System.out.println("adding Employee To Database : "+employee);

        request.setAttribute("message", employeeDAO.addEmployee(employee));
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}

