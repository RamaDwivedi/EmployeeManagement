package com.demo.empmanage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        EmployeeDAO employeeDAO=EmployeeDAO.getInstance();
        request.setAttribute("message", employeeDAO.deleteEmployee(email));
        ArrayList<Employee> employeeList=new ArrayList<>();
        employeeList.addAll(employeeDAO.listEmployees());
        request.setAttribute("employeeList", employeeList);
        try {
            request.getRequestDispatcher("adminView.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
