package ua.goit.controller.getAllServlets;

import ua.goit.dto.DeveloperDTO;
import ua.goit.service.DeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/developers")
public class DevelopersServlet extends HttpServlet {
    private final DeveloperService developerService = new DeveloperService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<DeveloperDTO> developers = developerService.getAll();
        req.setAttribute("devs", developers);
        req.getRequestDispatcher("/view/getAll/developers.jsp").forward(req, resp);
    }


}
