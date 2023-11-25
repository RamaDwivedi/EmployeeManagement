package com.demo.empmanage;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        EmployeeDAO employeeDAO=EmployeeDAO.getInstance();
        Employee employee=employeeDAO.getEmployee(email);
        if(employee!=null && employee.getPassword() !=null && employee.getPassword().equals(password)){
            if (employee.isAdmin()) {
                ArrayList<Employee> employeeList=new ArrayList<>();
                employeeList.addAll(employeeDAO.listEmployees());
                request.setAttribute("employeeList", employeeList);
                request.getRequestDispatcher("adminView.jsp").forward(request, response);
            }else{
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("employeeView.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Invalid Email Or Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}