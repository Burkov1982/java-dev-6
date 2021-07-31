package ua.goit.controller.findServlets;

import ua.goit.dto.CompanyDTO;
import ua.goit.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/company")
public class FindCompanyServlet extends HttpServlet {
    private final CompanyService service = new CompanyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/findById/findCompany.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDTO result = service.getById(Integer.parseInt(req.getParameter("companyID")));
        req.setAttribute("result", result);
        req.getRequestDispatcher("/view/print/printCompany.jsp").forward(req, resp);
    }
}
