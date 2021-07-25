package ua.goit.view;

import ua.goit.config.ConnectionManager;
import ua.goit.dao.model.*;
import ua.goit.service.*;

import java.util.LinkedList;
import java.util.StringJoiner;

public class ViewMessages {
    private ProjectService projectService;
    private CompanyService companyService;
    private CustomerService customerService;
    private SkillService skillService;
    private DeveloperService developerService;
    private ConnectionManager connectionManager;

    public ViewMessages(){

    }

    public ViewMessages(ConnectionManager connectionManager){
        this.connectionManager = connectionManager;
        projectService = new ProjectService(this.connectionManager);
        companyService = new CompanyService(this.connectionManager);
        customerService = new CustomerService(this.connectionManager);
        skillService = new SkillService(this.connectionManager);
        developerService = new DeveloperService(this.connectionManager);
    }


    public String sumSelectQueryResult(Integer result){
        return String.format("Сумма зарплат всех разработчиков, выбраного Вами, проекта: %d", result);
    }

    public String joinListDevelopers(LinkedList<Developer> developers){
        StringJoiner joiner = new StringJoiner("\n");
        for (Developer developer:developers) {
            joiner.add(developer.toString());
        }
        return joiner.toString();
    }

    public String joinListProjects(LinkedList<Project> projects){
        StringJoiner joiner = new StringJoiner("\n");
        for (Project project:projects){
            joiner.add(project.toString());
        }
        return joiner.toString();
    }

    public String joinListSkills(LinkedList<Skill> skills){
        StringJoiner joiner = new StringJoiner("\n");
        for (Skill skill:skills){
            joiner.add(skill.toString());
        }
        return joiner.toString();
    }

    public String joinListCompanies(LinkedList<Company> companies){
        StringJoiner joiner = new StringJoiner("\n");
        for (Company company:companies){
            joiner.add(company.toString());
        }
        return joiner.toString();
    }

    public String joinListLinks(LinkedList<Link> links){
        StringJoiner joiner = new StringJoiner("\n");
        for (Link link:links){
            joiner.add(link.toString());
        }
        return joiner.toString();
    }

    public String joinListCustomers(LinkedList<Customer> customers){
        StringJoiner joiner = new StringJoiner("\n");
        for (Customer customer:customers){
            joiner.add(customer.toString());
        }
        return joiner.toString();
    }
}
