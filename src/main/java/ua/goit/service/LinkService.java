package ua.goit.service;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.LinksDAO;
import ua.goit.dao.model.Link;
import ua.goit.dto.LinkDTO;
import ua.goit.view.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.goit.service.Converter.fromLink;

public class LinkService implements Service<LinkDTO> {
    private final Util util = new Util();
    private final HikariDataSource dataSource = DatabaseConnectionManager.getDataSource();

    private final LinksDAO linksDAO = new LinksDAO();

    @Override
    public List<LinkDTO> getAll() {
        return null;
    }

    @Override
    public List<LinkDTO> getAll(LinkDTO entity) {
        try {
            List<Link> links = linksDAO.getAllLinks(toLink(entity));
            List<LinkDTO> linksDTO = new ArrayList<>();
            for (Link link:links) {
                linksDTO.add(fromLink(link));
            }
            return linksDTO;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public String getById(int id) {
        return null;
    }

    @Override
    public String create(LinkDTO entity) {
        Link link = toLink(entity);
        try {
            linksDAO.createLink(link);
            return "Your request has been processed successfully";
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String delete(LinkDTO entity) {
        Link link = toLink(entity);
        try {
            linksDAO.deleteLink(link);
            return "Your request has been processed successfully";
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";

        }
    }

    public String update(LinkDTO linkDTO, LinkDTO newEntity) {
        Link newLink = toLink(newEntity);
        Link oldLink = toLink(linkDTO);
        try {
            linksDAO.updateLink(oldLink, newLink);
            return "Your request has been processed successfully";
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String update(LinkDTO entity) {
        return "An error has occurred, please try to enter data again";
    }

    public Link toLink(LinkDTO linkDTO){
        return new Link(linkDTO.getTable(), linkDTO.getProject_id(), linkDTO.getCustomer_id(),
                linkDTO.getDeveloper_id(), linkDTO.getCompany_id(), linkDTO.getSkill_id());
    }
}
