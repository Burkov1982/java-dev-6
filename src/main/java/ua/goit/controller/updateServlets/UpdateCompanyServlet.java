package ua.goit.controller.updateServlets;

import ua.goit.dto.CompanyDTO;
import ua.goit.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateCompany")
public class UpdateCompanyServlet extends HttpServlet {
    private final CompanyService service = new CompanyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/update/updateCompany.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDTO dto = new CompanyDTO();
        dto.setCompany_id(Integer.parseInt(req.getParameter("companyId")));
        dto.setCompany_name(req.getParameter("companyName"));
        dto.setHeadquarters(req.getParameter("headquarters"));
        CompanyDTO companyDTO = service.update(dto);
        req.setAttribute("result", companyDTO);
        req.getRequestDispatcher("/view/print/printCompany.jsp").forward(req, resp);
    }
}
