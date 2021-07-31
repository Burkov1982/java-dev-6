package ua.goit.controller.getAllServlets;

import ua.goit.service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/projects")
public class ProjectsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectService service = new ProjectService();
        req.setAttribute("projects", service.getAll());
        req.getRequestDispatcher("/view/getAll/projects.jsp").forward(req, resp);
    }
}
