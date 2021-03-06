package ua.goit.controller.createServlets;

import ua.goit.dto.ProjectDTO;
import ua.goit.service.ProjectService;
import ua.goit.view.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/addProject")
public class AddProjectServlet extends HttpServlet {
    private final ProjectService service = new ProjectService();
    private final Util util = new Util();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/create/addProject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setProject_name(req.getParameter("projectName"));
        projectDTO.setProject_description(req.getParameter("projectDescription"));
        LocalDate date = LocalDate.now();
        projectDTO.setStart_date(date);
        try {
            projectDTO.setCost(Integer.parseInt(req.getParameter("projectCost")));
        } catch (NumberFormatException e){
            e.printStackTrace();
        }
        ProjectDTO project = service.create(projectDTO);
        req.setAttribute("result", project);
        req.getRequestDispatcher("/view/print/printProject.jsp").forward(req, resp);
    }
}
