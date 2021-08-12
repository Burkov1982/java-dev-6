package ua.goit.service;

import ua.goit.dao.CompanyDAO;
import ua.goit.dao.model.Company;
import ua.goit.dto.CompanyDTO;
import ua.goit.view.Util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ua.goit.config.Converter.fromCompany;
import static ua.goit.config.Converter.toCompany;

public class CompanyService implements Service<CompanyDTO> {
    private final Util util = new Util();
    CompanyDAO companyDAO = new CompanyDAO();
    @Override
    public CompanyDTO create(CompanyDTO companyDTO){
        Company company = toCompany(companyDTO);
        try {
            return fromCompany(companyDAO.create(company));
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public String delete(CompanyDTO companyDTO) {
        Company company = toCompany(companyDTO);
        try {
            companyDAO.delete(company.getCompany_id());
            return "Your request has been processed successfully";
        } catch (SQLException e) {
            return "Please delete the entries in the Link section associated with this identifier.";
        }
    }

    @Override
    public CompanyDTO update(CompanyDTO companyDTO) {
        Company company = toCompany(companyDTO);
        try {
            return fromCompany(companyDAO.update(company));
        } catch (SQLException e) {
            return null;
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
    public CompanyDTO getById(int id) {
        try {
            return fromCompany(companyDAO.findById(id));
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<CompanyDTO> getAll() {
        try {
            List<Company> companies = companyDAO.getAll();
            List<CompanyDTO> companiesDTO = new ArrayList<>();
            for (Company company:companies) {
                companiesDTO.add(fromCompany(company));
            }
            return companiesDTO;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public String getAll(CompanyDTO entity) {
        try {
            List<Company> companies = companyDAO.getAll();
            List<CompanyDTO> companiesDTO = new ArrayList<>();
            for (Company company:companies) {
                companiesDTO.add(fromCompany(company));
            }
            return util.joinListElements(companiesDTO);
        } catch (SQLException e) {
            return null;
        }
    }

}
