package ua.goit.controller;

import ua.goit.config.ConnectionManager;
import ua.goit.service.DeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/developers")
public class DevelopersServlet extends HttpServlet {
    private final DeveloperService developerService = new DeveloperService(new ConnectionManager());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String developers = developerService.getAll();
        req.setAttribute("devs", developers);
        req.getRequestDispatcher("/view/developers.jsp").forward(req, resp);
    }
}
