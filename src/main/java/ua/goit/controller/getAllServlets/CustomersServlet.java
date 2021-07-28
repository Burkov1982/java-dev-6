package ua.goit.controller.getAllServlets;

import ua.goit.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customers")
public class CustomersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerService service = new CustomerService();
        req.setAttribute("customers", service.getAll());
        req.getRequestDispatcher("/view/customers.jsp").forward(req, resp);
    }
}
