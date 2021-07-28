package ua.goit.controller.getAllServlets;

import ua.goit.dto.LinkDTO;
import ua.goit.service.LinkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/linksDeveloper")
public class LinksDeveloperServlet extends HttpServlet {
    private final LinkService linkService = new LinkService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setTable("developer_skills");
        req.setAttribute("links", linkService.getAll(linkDTO));
        req.getRequestDispatcher("/view/linksPrint.jsp").forward(req, resp);
    }

}
