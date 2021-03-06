package ua.goit.controller.findServlets;

import ua.goit.dto.SkillDTO;
import ua.goit.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/skill")
public class FindSkillServlet extends HttpServlet {
    SkillService service = new SkillService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/findById/findSkill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SkillDTO skill = service.getById(Integer.parseInt(req.getParameter("skillID")));
        req.setAttribute("result", skill);
        req.getRequestDispatcher("/view/print/printSkill.jsp").forward(req, resp);
    }

}
