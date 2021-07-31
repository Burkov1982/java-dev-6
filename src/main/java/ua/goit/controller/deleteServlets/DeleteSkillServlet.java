package ua.goit.controller.deleteServlets;

import ua.goit.dto.SkillDTO;
import ua.goit.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteSkill")
public class DeleteSkillServlet extends HttpServlet {
    SkillService service = new SkillService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/delete/deleteSkill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setSkill_id(Integer.parseInt(req.getParameter("skillID")));
        String result = service.delete(skillDTO);
        req.setAttribute("result", result);
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
