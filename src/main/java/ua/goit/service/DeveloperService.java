package ua.goit.service;

import ua.goit.dao.DeveloperDAO;
import ua.goit.dao.model.Developer;
import ua.goit.dto.DeveloperDTO;
import ua.goit.view.Util;

import java.sql.SQLException;

public class DeveloperService implements Service<DeveloperDTO>{
    private final DeveloperDAO developerDAO = new DeveloperDAO();
    private final Util util = new Util();
    @Override
    public String create(DeveloperDTO developerDTO){
        Developer developer = toDeveloper(developerDTO);
        try {
            return developerDAO.create(developer).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String update(DeveloperDTO developerDTO){
        Developer developer = toDeveloper(developerDTO);
        try {
            return developerDAO.update(developer).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String update(DeveloperDTO entity, DeveloperDTO newEntity) {
        Developer developer = toDeveloper(newEntity);
        try {
            return developerDAO.update(developer).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String delete(DeveloperDTO developerDTO) {
        Developer developer = toDeveloper(developerDTO);
        try {
            developerDAO.delete(developer.getDeveloper_id());
            return "Your request has been processed successfully";
        } catch (SQLException e){
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String getById(int id) {
        try {
            return developerDAO.findById(id).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String getAll(){
        try {
            return util.joinListElements(developerDAO.getAll());
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String getAll(DeveloperDTO entity) {
        try {
            return util.joinListElements(developerDAO.getAll());
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    public static Developer toDeveloper(DeveloperDTO developerDTO){
        return new Developer(developerDTO.getDeveloper_id(), developerDTO.getFirst_name(), developerDTO.getLast_name(),
                developerDTO.getGender(), developerDTO.getSalary());
    }
}
