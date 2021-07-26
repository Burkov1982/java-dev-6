package ua.goit.service;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.SkillDAO;
import ua.goit.dao.model.Skill;
import ua.goit.dto.SkillDTO;
import ua.goit.view.Util;

import java.sql.SQLException;

public class SkillService implements Service<SkillDTO>{
    private final HikariDataSource dataSource = DatabaseConnectionManager.getDataSource();
    private final SkillDAO skillDAO = new SkillDAO();
    private final Util util = new Util();

    @Override
    public String getAll(){
        try {
            return util.joinListElements(skillDAO.getAll());
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String getAll(SkillDTO entity) {
        try {
            return util.joinListElements(skillDAO.getAll());
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String create(SkillDTO skillDTO) {
        Skill skill = toSkill(skillDTO);
        try {
            return skillDAO.create(skill).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String delete(SkillDTO skillDTO) {
        Skill skill = toSkill(skillDTO);
        try {
            skillDAO.delete(skill.getSkill_id());
            return "Your request has been processed successfully";
        } catch (SQLException e){
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String getById(int id) {
        try {
            return skillDAO.findById(id).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String update(SkillDTO skillDTO) {
        Skill skill = toSkill(skillDTO);
        try {
            return skillDAO.update(skill).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String update(SkillDTO entity, SkillDTO newEntity) {
        Skill skill = toSkill(newEntity);
        try {
            return skillDAO.update(skill).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    public static Skill toSkill(SkillDTO skillDTO){
        return new Skill(skillDTO.getSkill_id(), skillDTO.getBranch(), skillDTO.getStage());
    }
}
