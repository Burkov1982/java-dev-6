package ua.goit.controller.getAllServlets;

import ua.goit.dto.CompanyDTO;
import ua.goit.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/companies")
public class CompaniesServlet extends HttpServlet {
    private CompanyService companyService = new CompanyService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CompanyDTO> companies = companyService.getAll();
        req.setAttribute("companies", companies);
        req.getRequestDispatcher("/view/companies.jsp").forward(req, resp);
    }
}
