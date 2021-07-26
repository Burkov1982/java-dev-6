package ua.goit.service;

import com.zaxxer.hikari.HikariDataSource;
import ua.goit.config.DatabaseConnectionManager;
import ua.goit.dao.CompanyDAO;
import ua.goit.dao.model.Company;
import ua.goit.dto.CompanyDTO;
import ua.goit.view.Util;

import java.sql.SQLException;

public class CompanyService implements Service<CompanyDTO> {
    private final Util util = new Util();
    private final HikariDataSource dataSource = DatabaseConnectionManager.getDataSource();
    CompanyDAO companyDAO = new CompanyDAO(dataSource);
    @Override
    public String create(CompanyDTO companyDTO){
        Company company = toCompany(companyDTO);
        try {
            return companyDAO.create(company).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String delete(CompanyDTO companyDTO) {
        Company company = toCompany(companyDTO);
        try {
            companyDAO.delete(company.getCompany_id());
            return "Your request has been processed successfully";
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String update(CompanyDTO companyDTO) {
        Company company = toCompany(companyDTO);
        try {
            return companyDAO.update(company).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String update(CompanyDTO entity, CompanyDTO newEntity) {
        Company company = toCompany(newEntity);
        try {
            return companyDAO.update(company).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String getById(int id) {
        try {
            return companyDAO.findById(id).toString();
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String getAll() {
        try {
            return util.joinListElements(companyDAO.getAll());
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    @Override
    public String getAll(CompanyDTO entity) {
        try {
            return util.joinListElements(companyDAO.getAll());
        } catch (SQLException e) {
            return "An error has occurred, please try to enter data again";
        }
    }

    public static Company toCompany(CompanyDTO companyDTO){
        return new Company(companyDTO.getCompany_id(), companyDTO.getCompany_name(), companyDTO.getHeadquarters());
    }
}
