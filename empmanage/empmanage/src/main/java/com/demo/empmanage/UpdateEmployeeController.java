package com.demo.empmanage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/updateEmployeeController")
public class UpdateEmployeeController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String firstName = request.getParameter("firstName");
            String middleName = request.getParameter("middleName");
            String lastName = request.getParameter("lastName");
            String mobileNumber = request.getParameter("mobileNumber");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String isAdminString= request.getParameter("isAdmin");
            boolean isAdmin= "true".equalsIgnoreCase(isAdminString);
            String role = request.getParameter("role");

            EmployeeDAO employeeDAO=EmployeeDAO.getInstance();
            Employee employee=new Employee();
            employee.setEmail(email);
            employee.setPassword(password);
            employee.setMobileNumber(mobileNumber);
            employee.setFirstName(firstName);
            employee.setMiddleName(middleName);
            employee.setLastName(lastName);
            employee.setAdmin(isAdmin);

            System.out.println("Updating Employee To Database : "+employee);
            request.setAttribute("message", employeeDAO.updateEmployee(employee));
            System.out.println("buttonClicked "+role);
            if (role != null) {
                if (role.equals("admin")) {
                    ArrayList<Employee> employeeList=new ArrayList<>();
                    employeeList.addAll(employeeDAO.listEmployees());
                    request.setAttribute("employeeList", employeeList);
                    request.getRequestDispatcher("adminView.jsp").forward(request, response);
                } else if (role.equals("user")) {
                    request.setAttribute("employee", employee);
                    request.getRequestDispatcher("employeeView.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

