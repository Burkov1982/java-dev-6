package ua.goit.controller.deleteServlets;

import ua.goit.dto.ProjectDTO;
import ua.goit.service.ProjectService;
import ua.goit.view.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteProject")
public class DeleteProjectServlet extends HttpServlet {
    private final ProjectService service = new ProjectService();
    private final Util util = new Util();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/delete/deleteProject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProject_id(Integer.parseInt(req.getParameter("projectID")));
        String result = service.delete(projectDTO);
        req.setAttribute("result", result);
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
