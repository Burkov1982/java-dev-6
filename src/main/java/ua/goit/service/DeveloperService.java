package ua.goit.service;

import ua.goit.dao.model.Developer;
import ua.goit.dto.DeveloperDTO;
import ua.goit.view.ViewMessages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DeveloperService implements Service<DeveloperDTO>{
    private final ViewMessages viewMessages = new ViewMessages();

    @Override
    public void create(DeveloperDTO developerDTO){
        Developer developer = toDeveloper(developerDTO);
        developerDAO.create(developer);
    }

    @Override
    public void update(DeveloperDTO developerDTO) throws SQLException {
        Developer developer = toDeveloper(developerDTO);
        developerDAO.update(developer);
    }

    @Override
    public void update(DeveloperDTO entity, DeveloperDTO newEnity) throws SQLException {

    }

    @Override
    public void delete(DeveloperDTO developerDTO) throws SQLException {
        developerDAO.delete(toDeveloper(developerDTO));
    }

    @Override
    public String getById(int id) throws SQLException {
        return developerDAO.findById(id).toString();
    }

    @Override
    public String getAll(){
        return viewMessages.joinListDevelopers(developerDAO.getAll());
    }

    @Override
    public String getAll(DeveloperDTO entity) {
        return null;
    }

    public static Developer toDeveloper(DeveloperDTO developerDTO){
        return new Developer(developerDTO.getDeveloper_id(), developerDTO.getFirst_name(), developerDTO.getLast_name(),
                developerDTO.getGender(), developerDTO.getSalary());
    }

    public static DeveloperDTO fromDeveloper(Developer developer){
        return new DeveloperDTO(developer.getDeveloper_id(), developer.getFirst_name(), developer.getLast_name(),
                developer.getGender(), developer.getSalary());
    }

    public static LinkedList <Developer> toDeveloper (ResultSet resultSet){
        try{
            LinkedList<Developer> developers = new LinkedList<>();
            while (resultSet.next()){
                Developer developer = new Developer();
                developer.setDeveloper_id(resultSet.getInt("developer_id"));
                developer.setFirst_name(resultSet.getString("first_name"));
                developer.setLast_name(resultSet.getString("last_name"));
                developer.setGender(resultSet.getString("gender"));
                developer.setSalary(resultSet.getInt("salary"));
                developers.addLast(developer);
            }
            return developers;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
}
