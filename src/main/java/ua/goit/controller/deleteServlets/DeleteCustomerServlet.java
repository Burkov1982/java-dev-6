package ua.goit.controller.deleteServlets;

import ua.goit.dto.CustomerDTO;
import ua.goit.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteCustomer")
public class DeleteCustomerServlet extends HttpServlet {
    private final CustomerService service = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/delete/deleteCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomer_id(Integer.parseInt(req.getParameter("customerID")));
        String result = service.delete(customerDTO);
        req.setAttribute("result", result);
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
