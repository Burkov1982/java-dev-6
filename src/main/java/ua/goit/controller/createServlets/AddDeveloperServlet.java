package ua.goit.controller.createServlets;

import ua.goit.dto.DeveloperDTO;
import ua.goit.service.DeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addDeveloper")
public class AddDeveloperServlet extends HttpServlet {
    private final DeveloperService developerService = new DeveloperService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/create/addDeveloper.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setFirst_name(req.getParameter("firstname"));
        developerDTO.setLast_name(req.getParameter("lastname"));
        developerDTO.setGender(req.getParameter("gender"));
        developerDTO.setSalary(Integer.parseInt(req.getParameter("salary")));
        DeveloperDTO result = developerService.create(developerDTO);
        req.setAttribute("result", result);
        req.getRequestDispatcher("/view/print/printDeveloper.jsp").forward(req, resp);
    }
}
