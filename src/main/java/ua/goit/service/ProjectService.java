package ua.goit.service;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.ProjectDAO;
import ua.goit.dao.model.Project;
import ua.goit.dto.ProjectDTO;
import ua.goit.view.Util;

import java.sql.Date;
import java.sql.SQLException;

public class ProjectService implements Service<ProjectDTO>{
    private final Util util = new Util();
    private final HikariDataSource dataSource = DatabaseConnectionManager.getDataSource();
    private final ProjectDAO projectDAO = new ProjectDAO(dataSource);

    @Override
    public String create(ProjectDTO projectDTO){
        Project project = toProject(projectDTO);
        try {
            return projectDAO.create(project).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String update(ProjectDTO projectDTO) {
        Project project = toProject(projectDTO);
        try {
            return projectDAO.update(project).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String update(ProjectDTO entity, ProjectDTO newEnity) {
        Project project = toProject(newEnity);
        try {
            return projectDAO.update(project).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String getById(int id) {
        try {
            return projectDAO.findById(id).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String getAll(){
        try {
            return util.joinListElements(projectDAO.getAll());
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String getAll(ProjectDTO entity) {
        try {
            return util.joinListElements(projectDAO.getAll());
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String delete(ProjectDTO projectDTO) {
        Project project = toProject(projectDTO);
        try {
            projectDAO.delete(project.getProject_id());
            return "Your request has been processed successfully";
        } catch (SQLException e){
            return "An error has occurred, please try to enter data again";
        }
    }

    public static Project toProject(ProjectDTO projectDTO){
        return new Project(projectDTO.getProject_id(), projectDTO.getProject_name(), projectDTO.getProject_description(),
                projectDTO.getCost(), (Date) projectDTO.getStart_date());
    }
}
