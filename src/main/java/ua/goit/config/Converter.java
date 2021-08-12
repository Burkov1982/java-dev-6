package ua.goit.config;

import ua.goit.dao.model.*;
import ua.goit.dto.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Converter {
    public static Company toCompany(CompanyDTO companyDTO){
        return new Company(companyDTO.getCompany_id(), companyDTO.getCompany_name(), companyDTO.getHeadquarters());
    }

    public static CompanyDTO fromCompany(Company company){
        return new CompanyDTO(company.getCompany_id(), company.getCompany_name(), company.getHeadquarters());
    }

    public static Customer toCustomer(CustomerDTO customerDTO){
        return new Customer(customerDTO.getCustomer_id(), customerDTO.getCustomer_name());
    }

    public static CustomerDTO fromCustomer(Customer customer){
        return new CustomerDTO(customer.getCustomer_id(), customer.getCustomer_name());
    }

    public static Developer toDeveloper(DeveloperDTO developerDTO){
        return new Developer(developerDTO.getDeveloper_id(), developerDTO.getFirst_name(), developerDTO.getLast_name(),
                developerDTO.getGender(), developerDTO.getSalary());
    }

    public static DeveloperDTO fromDeveloper(Developer developer){
        return new DeveloperDTO(developer.getDeveloper_id(), developer.getFirst_name(), developer.getLast_name(),
                developer.getGender(), developer.getSalary());
    }

    public static LinkedList<Link> toLink(ResultSet resultSet, String table){
        try{
            LinkedList<Link> links = new LinkedList<>();
            if (table.equalsIgnoreCase("customers_companies")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(null);
                    link.setSkill_id(null);
                    link.setCompany_id(resultSet.getInt("company_id"));
                    link.setCustomer_id(resultSet.getInt("customer_id"));
                    link.setProject_id(resultSet.getInt("project_id"));

                    links.addLast(link);
                }
            }
            if (table.equalsIgnoreCase("project_developers")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(resultSet.getInt("developer_id"));
                    link.setSkill_id(null);
                    link.setCompany_id(null);
                    link.setCustomer_id(null);
                    link.setProject_id(resultSet.getInt("project_id"));

                    links.addLast(link);
                }
            }
            if (table.equalsIgnoreCase("developer_skills")){
                while (resultSet.next()){
                    Link link = new Link();
                    link.setTable(table);
                    link.setDeveloper_id(resultSet.getInt("developer_id"));
                    link.setSkill_id(resultSet.getInt("skill_id"));
                    link.setCompany_id(null);
                    link.setCustomer_id(null);
                    link.setProject_id(null);
                    links.addLast(link);
                }
            }
            return links;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public static LinkDTO fromLink(Link link){
        return new LinkDTO(link.getTable(), link.getProject_id(), link.getCustomer_id(),
                link.getDeveloper_id(), link.getCompany_id(), link.getSkill_id());
    }

    public static Project toProject(ProjectDTO projectDTO){
        return new Project(projectDTO.getProject_id(), projectDTO.getProject_name(), projectDTO.getProject_description(),
                projectDTO.getCost(), projectDTO.getStart_date());
    }

    public static ProjectDTO fromProject(Project project) {
        return new ProjectDTO(project.getProject_id(), project.getProject_name(), project.getProject_description(),
                project.getCost(), project.getStart_date());
    }

    public static Skill toSkill(SkillDTO skillDTO){
        return new Skill(skillDTO.getSkill_id(), skillDTO.getBranch(), skillDTO.getStage());
    }

    public static SkillDTO fromSkill(Skill skill){
        return new SkillDTO(skill.getSkill_id(), skill.getBranch(), skill.getStage());
    }
}
