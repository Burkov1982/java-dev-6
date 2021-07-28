package ua.goit.controller.getAllServlets;

import ua.goit.dto.LinkDTO;
import ua.goit.service.LinkService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/linksCustomers")
public class LinksCustomersServlet extends HttpServlet {
    private final LinkService linkService = new LinkService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setTable("customers_companies");
        req.setAttribute("links", linkService.getAll(linkDTO));
        req.getRequestDispatcher("/view/linksPrint.jsp").forward(req, resp);
    }

}
