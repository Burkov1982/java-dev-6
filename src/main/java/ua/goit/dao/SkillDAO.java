package ua.goit.dao;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.dao.model.Link;
import ua.goit.dao.model.Skill;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillDAO extends AbstractDAO<Skill> {
    protected SkillDAO(HikariDataSource dataSource) {
        super(dataSource);
    }

    @Override
    protected String getCreateQuery() {
        return "INSERT INTO skills (branch, stage) VALUES (?, ?)";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE skills SET branch = ?, stage = ? WHERE skill_id = ?";
    }

    @Override
    protected String getSelectByIdQuery() {
        return "SELECT skill_id, branch, stage FROM skills WHERE skill_id = ?";
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
        return "DELETE FROM skills WHERE id = ?";
    }

    @Override
    protected String getSelectAllQuery() {
        return "SELECT skill_id, branch, stage FROM skills";
    }

    @Override
    protected void sendEntity(PreparedStatement statement, Skill object) throws SQLException {
        if (object.getSkill_id() == null){
            statement.setString(1, object.getBranch());
            statement.setString(2, object.getStage());
        } else {
            statement.setString(1, object.getBranch());
            statement.setString(2, object.getStage());
            statement.setInt(3, object.getSkill_id());
        }
    }

    @Override
    protected Skill getEntity(ResultSet resultSet) throws SQLException {
        Skill skill = new Skill();
        skill.setSkill_id(resultSet.getInt("skill_id"));
        skill.setBranch(resultSet.getString("branch"));
        skill.setStage(resultSet.getString("stage"));
        return skill;
    }
}
