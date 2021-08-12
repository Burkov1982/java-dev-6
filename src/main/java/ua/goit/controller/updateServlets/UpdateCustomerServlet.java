package ua.goit.controller.updateServlets;

import ua.goit.dto.CustomerDTO;
import ua.goit.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateCustomer")
public class UpdateCustomerServlet extends HttpServlet {
    private final CustomerService service = new CustomerService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/update/updateCustomer.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (service.getById(Integer.parseInt(req.getParameter("customerId")))!=null){
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setCustomer_id(Integer.parseInt(req.getParameter("customerId")));
            customerDTO.setCustomer_name(req.getParameter("customerName"));
            CustomerDTO customerDTO1 = service.update(customerDTO);
            req.setAttribute("result", customerDTO1);
            req.getRequestDispatcher("/view/print/printCustomer.jsp").forward(req, resp);
        } else {
            req.setAttribute("result", "An error has occurred, please resend the request");
            req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
        }
    }
}
