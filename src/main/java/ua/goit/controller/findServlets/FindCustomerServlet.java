package ua.goit.controller.findServlets;

import ua.goit.dto.CustomerDTO;
import ua.goit.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer")
public class FindCustomerServlet extends HttpServlet {
    private final CustomerService service = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/findById/findCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDTO customerDTO1 = service.getById(Integer.parseInt(req.getParameter("customerID")));
        req.setAttribute("result", customerDTO1);
        req.getRequestDispatcher("/view/print/printCustomer.jsp").forward(req, resp);
    }

}
