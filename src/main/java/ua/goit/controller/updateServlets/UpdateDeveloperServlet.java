package ua.goit.controller.updateServlets;

import ua.goit.dto.DeveloperDTO;
import ua.goit.service.DeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateDeveloper")
public class UpdateDeveloperServlet extends HttpServlet {
    private final DeveloperService developerService = new DeveloperService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/update/updateDeveloper.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (developerService.getById(Integer.parseInt(req.getParameter("developerId")))!=null){
            DeveloperDTO developerDTO = new DeveloperDTO();
            developerDTO.setDeveloper_id(Integer.parseInt(req.getParameter("developerId")));
            developerDTO.setFirst_name(req.getParameter("firstname"));
            developerDTO.setLast_name(req.getParameter("lastname"));
            developerDTO.setGender(req.getParameter("gender"));
            developerDTO.setSalary(Integer.parseInt(req.getParameter("salary")));
            DeveloperDTO result = developerService.update(developerDTO);
            req.setAttribute("result", result);
        } else {
            req.setAttribute("result", "An error has occurred, please resend the request");
        }
        req.getRequestDispatcher("/view/print/printDeveloper.jsp").forward(req, resp);
    }
}
