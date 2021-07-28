package ua.goit.controller.getAllServlets;

import ua.goit.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/skills")
public class SkillsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SkillService service = new SkillService();
        req.setAttribute("skills", service.getAll());
        req.getRequestDispatcher("/view/skills.jsp").forward(req, resp);
    }
}
