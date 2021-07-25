package ua.goit.dao;


import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.Link;
import ua.goit.dao.model.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectDAO extends AbstractDAO<Project> {
    protected ProjectDAO(HikariDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO projects (project_name, project_description, cost, start_date) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE projects SET project_name = ?, project_description = ?, cost = ?, start_date = ? " +
                "WHERE project_id = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT project_id, project_name, project_description, cost, start_date FROM projects " +
                "WHERE project_id = ?";
    }

    @Override
    protected PreparedStatement enrichPreparedStatement(HikariDataSource dataSource, Link entity, String command) {
        return null;
    }

    @Override
    protected PreparedStatement enrichUpdatePreparedStatement(HikariDataSource dataSource, Link entity, Link oldEntity) {
        return null;
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM projects WHERE project_id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT project_id, project_name, project_description, cost, start_date FROM projects";
    }

    @Override
    protected void sendEntity(PreparedStatement statement, Project object) throws SQLException {
        if (object.getProject_id() == null){
            statement.setString(1, object.getProject_name());
            statement.setString(2, object.getProject_description());
            statement.setInt(3, object.getCost());
            statement.setDate(4, object.getStart_date());
        } else {
            statement.setString(1, object.getProject_name());
            statement.setString(2, object.getProject_description());
            statement.setInt(3, object.getCost());
            statement.setDate(4, object.getStart_date());
            statement.setInt(5, object.getProject_id());
        }
    }

    @Override
    protected Project getEntity(ResultSet resultSet) throws SQLException {
        Project project = new Project();
        project.setProject_id(resultSet.getInt("project_id"));
        project.setProject_name(resultSet.getString("project_name"));
        project.setProject_description(resultSet.getString("project_description"));
        project.setCost(resultSet.getInt("cost"));
        project.setStart_date(resultSet.getDate("start_date"));
        return project;
    }
}
