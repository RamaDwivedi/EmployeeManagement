package com.demo.empmanage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateEmployee")
public class UpdateEmployeeAdminRedirect extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        request.setAttribute("employee", EmployeeDAO.getInstance().getEmployee(email));
        request.getRequestDispatcher("adminUpdate.jsp").forward(request, response);
    }
}

