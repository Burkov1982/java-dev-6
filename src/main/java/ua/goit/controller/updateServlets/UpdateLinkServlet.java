package ua.goit.controller.updateServlets;

import ua.goit.dto.LinkDTO;
import ua.goit.service.LinkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateLink")
public class UpdateLinkServlet extends HttpServlet {
    private final LinkService linkService = new LinkService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/update/updateLink.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkDTO newLink = new LinkDTO();
        LinkDTO oldLink = new LinkDTO();
        newLink.setTable(req.getParameter("table"));
        oldLink.setTable(req.getParameter("table"));
        switch (req.getParameter("table")){
            case "customers_companies" -> {
                oldLink.setCustomer_id(Integer.parseInt(req.getParameter("customerId")));
                oldLink.setProject_id(Integer.parseInt(req.getParameter("projectId")));
                oldLink.setCompany_id(Integer.parseInt(req.getParameter("companyId")));
                newLink.setCustomer_id(Integer.parseInt(req.getParameter("newCustomerId")));
                newLink.setProject_id(Integer.parseInt(req.getParameter("newProjectId")));
                newLink.setCompany_id(Integer.parseInt(req.getParameter("newCompanyId")));
            }
            case "project_developers" -> {
                oldLink.setProject_id(Integer.parseInt(req.getParameter("projectId")));
                oldLink.setDeveloper_id(Integer.parseInt(req.getParameter("developerId")));
                newLink.setProject_id(Integer.parseInt(req.getParameter("newProjectId")));
                newLink.setDeveloper_id(Integer.parseInt(req.getParameter("newDeveloperId")));
            }
            case "developer_skills" -> {
                oldLink.setDeveloper_id(Integer.parseInt(req.getParameter("developerId")));
                oldLink.setSkill_id(Integer.parseInt(req.getParameter("skillId")));
                newLink.setDeveloper_id(Integer.parseInt(req.getParameter("newDeveloperId")));
                newLink.setSkill_id(Integer.parseInt(req.getParameter("newSkillId")));
            }
        }
        String result = linkService.update(oldLink, newLink);
        req.setAttribute("result", result);
        req.getRequestDispatcher("/view/print/printMessage.jsp").forward(req, resp);
    }
}
