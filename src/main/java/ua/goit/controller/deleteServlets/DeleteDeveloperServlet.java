package ua.goit.controller.deleteServlets;

import ua.goit.dto.DeveloperDTO;
import ua.goit.service.DeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteDeveloper")
public class DeleteDeveloperServlet extends HttpServlet {
    private final DeveloperService developerService = new DeveloperService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/delete/deleteDeveloper.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeveloperDTO developerDTO = new DeveloperDTO();
        developerDTO.setDeveloper_id(Integer.parseInt(req.getParameter("developerID")));
        String result = developerService.delete(developerDTO);
        req.setAttribute("result", result);
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
