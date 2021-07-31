package ua.goit.controller.findServlets;

import ua.goit.dto.DeveloperDTO;
import ua.goit.service.DeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developer")
public class FindDeveloperServlet extends HttpServlet {
    private final DeveloperService developerService = new DeveloperService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/findById/findDeveloper.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDTO result = developerService.getById(Integer.parseInt(req.getParameter("developerID")));
        req.setAttribute("result", result);
        req.getRequestDispatcher("/view/print/printDeveloper.jsp").forward(req, resp);
    }

}
