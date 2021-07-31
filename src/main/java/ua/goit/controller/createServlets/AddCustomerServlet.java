package ua.goit.controller.createServlets;

import ua.goit.dto.CustomerDTO;
import ua.goit.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addCustomer")
public class AddCustomerServlet extends HttpServlet {
    private final CustomerService service = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/create/addCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomer_name(req.getParameter("customerName"));
        CustomerDTO customerDTO1 = service.create(customerDTO);
        req.setAttribute("result", customerDTO1);
        req.getRequestDispatcher("/view/print/printCustomer.jsp").forward(req, resp);
    }
}
