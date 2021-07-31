package ua.goit.controller.findServlets;

import ua.goit.dto.ProjectDTO;
import ua.goit.service.ProjectService;
import ua.goit.view.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/project")
public class FindProjectServlet extends HttpServlet {
    private final ProjectService service = new ProjectService();
    private final Util util = new Util();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/findById/findProject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDTO project = service.getById(Integer.parseInt(req.getParameter("projectID")));
        req.setAttribute("result", project);
        req.getRequestDispatcher("/view/print/printProject.jsp").forward(req, resp);
    }

}
