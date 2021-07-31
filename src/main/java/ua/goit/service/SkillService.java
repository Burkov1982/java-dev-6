package ua.goit.service;

import ua.goit.dao.SkillDAO;
import ua.goit.dao.model.Skill;
import ua.goit.dto.SkillDTO;
import ua.goit.view.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.goit.service.Converter.fromSkill;
import static ua.goit.service.Converter.toSkill;

public class SkillService implements Service<SkillDTO>{
    private final SkillDAO skillDAO = new SkillDAO();
    private final Util util = new Util();

    public List<SkillDTO> getAll(){
        try {
            List<Skill> skills = skillDAO.getAll();
            List<SkillDTO> skillsDTO = new ArrayList<>();
            for (Skill skill:skills) {
                skillsDTO.add(fromSkill(skill));
            }
            return skillsDTO;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public String getAll(SkillDTO entity) {
        try {
            List<Skill> skills = skillDAO.getAll();
            List<SkillDTO> skillsDTO = new ArrayList<>();
            for (Skill skill:skills) {
                skillsDTO.add(fromSkill(skill));
            }
            return util.joinListElements(skillsDTO);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public SkillDTO create(SkillDTO skillDTO) {
        Skill skill = toSkill(skillDTO);
        try {
            return fromSkill(skillDAO.create(skill));
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public String delete(SkillDTO skillDTO) {
        Skill skill = toSkill(skillDTO);
        try {
            skillDAO.delete(skill.getSkill_id());
            return "Your request has been processed successfully";
        } catch (SQLException e){
            e.printStackTrace();
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public SkillDTO getById(int id) {
        try {
            return fromSkill(skillDAO.findById(id));
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public SkillDTO update(SkillDTO skillDTO) {
        Skill skill = toSkill(skillDTO);
        try {
            return fromSkill(skillDAO.update(skill));
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public String update(SkillDTO entity, SkillDTO newEntity) {
        Skill skill = toSkill(newEntity);
        try {
            return skillDAO.update(skill).toString();
        } catch (SQLException e) {
            return "Please delete the entries in the Link section associated with this identifier.";
        }
    }
}
