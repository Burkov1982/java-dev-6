package ua.goit.command;

import ua.goit.config.ConnectionManager;
import ua.goit.dto.LinkDTO;
import ua.goit.service.*;
import ua.goit.view.ConsoleManager;
import ua.goit.view.ViewMessages;

import java.sql.SQLException;

public class Select implements Command{
    private static final String SELECT_MENU = """
            Enter the number of the necessary table
            1 - developer
            2 - project
            3 - skill
            4 - company
            5 - customer
            6 - link tables
            
            To return to the home page, enter 'exit'""";
    private static final String ERROR = "Please enter your data again \n";


    private final ConsoleManager consoleManager;
    private final ViewMessages viewMessages;
    private final DeveloperService developerService;
    private final SkillService skillService;
    private final ProjectService projectService;
    private final LinkService linkService;
    private final CompanyService companyService;
    private final CustomerService customerService;

    public Select(ConsoleManager consoleManager, ConnectionManager connectionManager) {
        this.consoleManager = consoleManager;
        developerService = new DeveloperService(connectionManager);
        skillService = new SkillService(connectionManager);
        projectService = new ProjectService(connectionManager);
        linkService = new LinkService(connectionManager);
        companyService = new CompanyService(connectionManager);
        customerService = new CustomerService(connectionManager);
        viewMessages = new ViewMessages(connectionManager);
    }

    @Override
    public String commandName() {
        return "read";
    }

    @Override
    public void process() {
        boolean running = true;
        while (running){
            consoleManager.write(SELECT_MENU);
            String obtainedData = consoleManager.read();
            switch (obtainedData){
                case "1" -> developerSelect();
                case "2" -> projectSelect();
                case "3" -> skillSelect();
                case "4" -> companySelect();
                case "5" -> customerSelect();
                case "6" -> linkSelect();
                case "exit" -> running=false;
            }
        }
    }

    private void linkSelect() {
        consoleManager.write("""
                Enter the number of the necessary
                1 - customers_companies
                2 - project_developers
                3 - developer_skills
                """);
        String obtained = consoleManager.read();
        try {
            Integer number;
            LinkDTO linkDTO = new LinkDTO();

            switch (obtained) {
                case "1":
                    linkDTO = new LinkDTO();
                    linkDTO.setTable("customers_companies");
                    consoleManager.write(linkService.getAll(linkDTO));
                    break;
                case "2":
                    linkDTO = new LinkDTO();
                    linkDTO.setTable("project_developers");
                    consoleManager.write(linkService.getAll(linkDTO));
                    break;
                case "3":
                    linkDTO = new LinkDTO();
                    linkDTO.setTable("developer_skills");
                    consoleManager.write(linkService.getAll(linkDTO));
                    break;
            }
        } catch (SQLException e){
            consoleManager.write(ERROR);
        }
    }

    private void customerSelect() {
        consoleManager.write("""
                Enter the number the number of data from table 'customers'
                1 - get all customers
                2 - get by id
                """);
        String obtained = consoleManager.read();
        try {
            switch (obtained) {
                case "1":
                    consoleManager.write(customerService.getAll());
                    break;
                case "2":
                    Integer id;
                    try {
                        consoleManager.write("""
                                Enter the id of customer""");
                        id = Integer.parseInt(consoleManager.read());
                    } catch (NumberFormatException e) {
                        consoleManager.write(ERROR);
                        id = Integer.parseInt(consoleManager.read());
                    }
                    consoleManager.write(customerService.getById(id));
                    break;
            }
        } catch (SQLException e){
            consoleManager.write(ERROR);
        }
    }

    private void companySelect() {
        consoleManager.write("""
                Enter the number the number of data from table 'companies'
                1 - get all companies
                2 - get by id
                """);
        String obtained = consoleManager.read();
        try {
            switch (obtained) {
                case "1":
                    consoleManager.write(companyService.getAll());
                    break;
                case "2":
                    Integer id;
                    try {
                        consoleManager.write("""
                                Enter the id of company""");
                        id = Integer.parseInt(consoleManager.read());
                    } catch (NumberFormatException e) {
                        consoleManager.write(ERROR);
                        id = Integer.parseInt(consoleManager.read());
                    }
                    consoleManager.write(companyService.getById(id));
                    break;
            }
        } catch (SQLException e){
            consoleManager.write(ERROR);
        }
    }

    private void skillSelect() {
        consoleManager.write("""
                Enter the number the number of data from table 'skills'
                1 - get all skills
                2 - get by id
                """);
        String obtained = consoleManager.read();
        try {
            switch (obtained) {
                case "1":
                    consoleManager.write(skillService.getAll());
                    break;
                case "2":
                    Integer id;
                    try {
                        consoleManager.write("""
                                Enter the id of skill""");
                        id = Integer.parseInt(consoleManager.read());
                    } catch (NumberFormatException e) {
                        consoleManager.write(ERROR);
                        id = Integer.parseInt(consoleManager.read());
                    }
                    consoleManager.write(skillService.getById(id));
                    break;
            }
        } catch (SQLException e){
            consoleManager.write(ERROR);
        }
    }

    private void projectSelect() {
        consoleManager.write("""
                Enter the number the number of data from table 'projects'
                1 - get all projects
                2 - get by id
                3 - get all projects (start date - name of project - amount of devs in the project)
                """);
        String obtained = consoleManager.read();
        try {
            switch (obtained) {
                case "1" -> consoleManager.write(projectService.getAll());
                case "2" -> {
                    int id;
                    try {
                        consoleManager.write("Enter the id of project");
                        id = Integer.parseInt(consoleManager.read());
                    } catch (NumberFormatException e) {
                        consoleManager.write(ERROR);
                        id = Integer.parseInt(consoleManager.read());
                    }
                    consoleManager.write(projectService.getById(id));
                }
            }
        } catch (SQLException e){
            consoleManager.write(ERROR);
        }
    }

    private void developerSelect() {
        consoleManager.write("""
                Enter the number the number of data from table 'developers'
                1 - get all devs
                2 - get bt id
                3 - get devs by branch
                4 - get devs by skill level
                5 - get sum of salaries by project id 
                """);
        String obtained = consoleManager.read();
        try {
            switch (obtained) {
                case "1" -> consoleManager.write(developerService.getAll());
                case "2" -> {
                    int id;
                    try {
                        consoleManager.write("""
                                Enter the id of developer""");
                        id = Integer.parseInt(consoleManager.read());
                    } catch (NumberFormatException e) {
                        consoleManager.write(ERROR);
                        id = Integer.parseInt(consoleManager.read());
                    }
                    consoleManager.write(developerService.getById(id));
                }
            }
        } catch (SQLException e){
            consoleManager.write(ERROR);
        }
    }
}
