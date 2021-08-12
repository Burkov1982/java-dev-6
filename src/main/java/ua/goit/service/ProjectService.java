package ua.goit.service;

import ua.goit.dao.ProjectDAO;
import ua.goit.dao.model.Project;
import ua.goit.dto.ProjectDTO;
import ua.goit.view.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.goit.config.Converter.fromProject;
import static ua.goit.config.Converter.toProject;

public class ProjectService implements Service<ProjectDTO>{
    private final ProjectDAO projectDAO = new ProjectDAO();
    private final Util util = new Util();

    @Override
    public ProjectDTO create(ProjectDTO projectDTO){
        Project project = toProject(projectDTO);
        try {
            return fromProject(projectDAO.create(project));
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ProjectDTO update(ProjectDTO projectDTO) {
        Project project = toProject(projectDTO);
        try {
            return fromProject(projectDAO.update(project));
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public String update(ProjectDTO entity, ProjectDTO newEnity) {
        Project project = toProject(newEnity);
        try {
            return projectDAO.update(project).toString();
        } catch (SQLException e) {
            return "Please delete the entries in the Link section associated with this identifier.";
        }
    }

    @Override
    public ProjectDTO getById(int id) {
        try {
            return fromProject(projectDAO.findById(id));
        } catch (SQLException e) {
            return null;
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
    public String getAll(ProjectDTO entity) {
        try {
            List<Project> projects = projectDAO.getAll();
            List<ProjectDTO> projectsDTO = new ArrayList<>();
            for (Project project:projects) {
                projectsDTO.add(fromProject(project));
            }
            return util.joinListElements(projectsDTO);
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
