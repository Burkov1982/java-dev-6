package ua.goit.service;

import ua.goit.dao.ProjectDAO;
import ua.goit.dao.model.Project;
import ua.goit.dto.ProjectDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.goit.service.Converter.fromProject;
import static ua.goit.service.Converter.toProject;

public class ProjectService implements Service<ProjectDTO>{
    private final ProjectDAO projectDAO = new ProjectDAO();

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
    public List<ProjectDTO> getAll(){
        try {
            List<Project> projects = projectDAO.getAll();
            List<ProjectDTO> projectsDTO = new ArrayList<>();
            for (Project project:projects) {
                projectsDTO.add(fromProject(project));
            }
            return projectsDTO;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<ProjectDTO> getAll(ProjectDTO entity) {
        try {
            List<Project> projects = projectDAO.getAll();
            List<ProjectDTO> projectsDTO = new ArrayList<>();
            for (Project project:projects) {
                projectsDTO.add(fromProject(project));
            }
            return projectsDTO;
        } catch (SQLException e) {
            return null;
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

}
