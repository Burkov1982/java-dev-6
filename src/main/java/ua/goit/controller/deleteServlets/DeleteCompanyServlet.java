package ua.goit.controller.deleteServlets;

import ua.goit.dto.CompanyDTO;
import ua.goit.service.CompanyService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteCompany")
public class DeleteCompanyServlet extends HttpServlet {
    private final CompanyService service = new CompanyService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/delete/deleteCompany.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (service.getById(Integer.parseInt(req.getParameter("companyID")))!=null){
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setCompany_id(Integer.parseInt(req.getParameter("companyID")));
            String result = service.delete(companyDTO);
            req.setAttribute("result", result);
        } else {
            req.setAttribute("result", "An error has occurred, please resend the request");
        }
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
