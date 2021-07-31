package ua.goit.controller.updateServlets;

import ua.goit.dto.SkillDTO;
import ua.goit.service.SkillService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateSkill")
public class UpdateSkillServlet extends HttpServlet {
    SkillService service = new SkillService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/update/updateSkill.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setSkill_id(Integer.parseInt(req.getParameter("skillId")));
        skillDTO.setBranch(req.getParameter("branch"));
        skillDTO.setStage(req.getParameter("stage"));
        SkillDTO skill = service.update(skillDTO);
        req.setAttribute("result", skill);
        req.getRequestDispatcher("/view/print/printSkill.jsp").forward(req, resp);
    }
}
