package ua.goit.service;

import ua.goit.dao.model.Project;
import ua.goit.dto.ProjectDTO;
import ua.goit.view.ViewMessages;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class ProjectService implements Service<ProjectDTO>{
    private final ViewMessages viewMessages = new ViewMessages();

    @Override
    public void create(ProjectDTO projectDTO) throws SQLException{
        Project project = toProject(projectDTO);
        projectDAO.create(project);
    }

    @Override
    public void update(ProjectDTO projectDTO) throws SQLException {
        Project project = toProject(projectDTO);
        projectDAO.update(project);
    }

    @Override
    public void update(ProjectDTO entity, ProjectDTO newEnity) throws SQLException {

    }

    @Override
    public String getById(int id) throws SQLException {
        return projectDAO.findById(id).toString();
    }

    @Override
    public String getAll(){
        return viewMessages.joinListProjects(projectDAO.getAll());
    }

    @Override
    public String getAll(ProjectDTO entity) {
        return null;
    }

    @Override
    public void delete(ProjectDTO projectDTO) throws SQLException {
        projectDAO.delete(toProject(projectDTO));
    }

    public LinkedList<Project> getAllInList(){
        return projectDAO.getAll();
    }

    public static Project toProject(ProjectDTO projectDTO){
        return new Project(projectDTO.getProject_id(), projectDTO.getProject_name(), projectDTO.getProject_description(),
                projectDTO.getCost(), (Date) projectDTO.getStart_date());
    }

    public static LinkedList <Project> toProject (ResultSet resultSet){
        try{
            LinkedList<Project> projects  = new LinkedList<>();
            while (resultSet.next()){
                Project project = new Project();
                project.setProject_id(resultSet.getInt("project_id"));
                project.setProject_name(resultSet.getString("project_name"));
                project.setProject_description(resultSet.getString("project_description"));
                project.setCost(resultSet.getInt("cost"));
                project.setStart_date(resultSet.getDate("start_date"));
                projects.addLast(project);
            }
            return projects;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }
}
