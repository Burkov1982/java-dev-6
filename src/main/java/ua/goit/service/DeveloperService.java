package ua.goit.service;

import ua.goit.dao.DeveloperDAO;
import ua.goit.dao.model.Developer;
import ua.goit.dto.DeveloperDTO;
import ua.goit.view.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.goit.service.Converter.fromDeveloper;
import static ua.goit.service.Converter.toDeveloper;

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
    public List<DeveloperDTO> getAll(){
        try {
            List<Developer> developers = developerDAO.getAll();
            List<DeveloperDTO> developersDTO = new ArrayList<>();
            for (Developer developer:developers) {
                developersDTO.add(fromDeveloper(developer));
            }
            return developersDTO;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<DeveloperDTO> getAll(DeveloperDTO entity) {
        try {
            List<Developer> developers = developerDAO.getAll();
            List<DeveloperDTO> developersDTO = new ArrayList<>();
            for (Developer developer:developers) {
                developersDTO.add(fromDeveloper(developer));
            }
            return developersDTO;
        } catch (SQLException e) {
            return null;
        }
    }

}
