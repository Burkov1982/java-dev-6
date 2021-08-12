package ua.goit.controller.updateServlets;

import ua.goit.dto.ProjectDTO;
import ua.goit.service.ProjectService;
import ua.goit.view.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateProject")
public class UpdateProjectServlet extends HttpServlet {
    private final ProjectService service = new ProjectService();
    private final Util util = new Util();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/update/updateProject.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (service.getById(Integer.parseInt(req.getParameter("projectId")))!=null){
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setProject_id(Integer.parseInt(req.getParameter("projectId")));
            projectDTO.setProject_name(req.getParameter("projectName"));
            projectDTO.setProject_description(req.getParameter("projectDescription"));
            try {
                projectDTO.setCost(Integer.parseInt(req.getParameter("projectCost")));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            ProjectDTO project = service.update(projectDTO);
            req.setAttribute("result", project);
        } else {
            req.setAttribute("result", "An error has occurred, please resend the request");
        }
        req.getRequestDispatcher("/view/print/printProject.jsp").forward(req, resp);
    }
}
