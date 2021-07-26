package ua.goit.service;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.LinksDAO;
import ua.goit.dao.model.Link;
import ua.goit.dto.LinkDTO;
import ua.goit.view.ViewMessages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class LinkService implements Service<LinkDTO> {
    private final ViewMessages viewMessages = new ViewMessages();
    private final HikariDataSource dataSource = DatabaseConnectionManager.getDataSource();
    private LinksDAO linksDAO = new LinksDAO(dataSource);

    @Override
    public String getAll() {
        return null;
    }

    @Override
    public String getAll(LinkDTO entity) {
        Link link = toLink(entity);
        try {
            return viewMessages.joinListElements(linksDAO.getAllLinks(link));
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
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

    public LinkedList<Link> toLink(ResultSet resultSet, String table){
        try{
            LinkedList<Link> links = new LinkedList<>();
            if (table.equalsIgnoreCase("customers_companies")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(null);
                    link.setSkill_id(null);
                    link.setCompany_id(resultSet.getInt("company_id"));
                    link.setCustomer_id(resultSet.getInt("customer_id"));
                    link.setProject_id(resultSet.getInt("project_id"));

                    links.addLast(link);
                }
            }
            if (table.equalsIgnoreCase("project_developers")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(resultSet.getInt("developer_id"));
                    link.setSkill_id(null);
                    link.setCompany_id(null);
                    link.setCustomer_id(null);
                    link.setProject_id(resultSet.getInt("project_id"));

                    links.addLast(link);
                }
            }
            if (table.equalsIgnoreCase("developer_skills")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(resultSet.getInt("developer_id"));
                    link.setSkill_id(resultSet.getInt("skill_id"));
                    link.setCompany_id(null);
                    link.setCustomer_id(null);
                    link.setProject_id(null);
                    links.addLast(link);
                }
            }
            return links;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public LinkDTO fromLink(Link link){
        return new LinkDTO(link.getTable(), link.getProject_id(), link.getCustomer_id(),
                link.getDeveloper_id(), link.getCompany_id(), link.getSkill_id());
    }
}
