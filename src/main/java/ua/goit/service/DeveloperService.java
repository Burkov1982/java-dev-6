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
    public DeveloperDTO create(DeveloperDTO developerDTO){
        Developer developer = toDeveloper(developerDTO);
        try {
            return fromDeveloper(developerDAO.create(developer));
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public DeveloperDTO update(DeveloperDTO developerDTO){
        Developer developer = toDeveloper(developerDTO);
        try {
            return fromDeveloper(developerDAO.update(developer));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
            e.printStackTrace();
            return "Please delete the entries in the Link section associated with this identifier.";
        }
    }

    @Override
    public DeveloperDTO getById(int id) {
        try {
            return fromDeveloper(developerDAO.findById(id));
        } catch (SQLException e) {
            return null;
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
    public String getAll(DeveloperDTO entity) {
        try {
            List<Developer> developers = developerDAO.getAll();
            List<DeveloperDTO> developersDTO = new ArrayList<>();
            for (Developer developer:developers) {
                developersDTO.add(fromDeveloper(developer));
            }
            return util.joinListElements(developersDTO);
        } catch (SQLException e) {
            return null;
        }
    }

}
