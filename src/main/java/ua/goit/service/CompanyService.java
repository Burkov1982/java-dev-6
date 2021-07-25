package ua.goit.service;

import ua.goit.dao.model.Company;
import ua.goit.dto.CompanyDTO;
import ua.goit.view.ViewMessages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class CompanyService implements Service<CompanyDTO> {
    private final ViewMessages viewMessages = new ViewMessages();

    @Override
    public void create(CompanyDTO companyDTO) throws SQLException {
        Company company = toCompany(companyDTO);
        companyDAO.create(company);
    }

    @Override
    public void delete(CompanyDTO companyDTO) throws SQLException {
        companyDAO.delete(toCompany(companyDTO));
    }

    @Override
    public void update(CompanyDTO companyDTO) throws SQLException {
        Company company = toCompany(companyDTO);
        companyDAO.update(company);
    }

    @Override
    public void update(CompanyDTO entity, CompanyDTO newEnity) throws SQLException {

    }

    @Override
    public String getById(int id) throws SQLException {
        return companyDAO.findById(id).toString();
    }

    @Override
    public String getAll(){
        return viewMessages.joinListCompanies(companyDAO.getAll());
    }

    @Override
    public String getAll(CompanyDTO entity) {
        return null;
    }

    public static Company toCompany(CompanyDTO companyDTO){
        return new Company(companyDTO.getCompany_id(), companyDTO.getCompany_name(), companyDTO.getHeadquarters());
    }

    public static LinkedList<Company> toCompany(ResultSet resultSet){
        try{
            LinkedList<Company> companies = new LinkedList<>();
            while (resultSet.next()){
                Company company = new Company();
                company.setCompany_id(resultSet.getInt("company_id"));
                company.setCompany_name(resultSet.getString("company_name"));
                company.setHeadquarters(resultSet.getString("headquarters"));
                companies.addLast(company);
            }
            return companies;
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    public static CompanyDTO fromCompany(Company company){
        return new CompanyDTO(company.getCompany_id(), company.getCompany_name(), company.getHeadquarters());
    }
}
