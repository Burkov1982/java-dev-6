package ua.goit.view;

import ua.goit.dto.LinkDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.StringJoiner;

public class Util {

    public <T> String joinListElements(List<T> tList){
        StringJoiner joiner = new StringJoiner("<br>");
        for (T t:tList) {
            joiner.add(t.toString());
        }
        return joiner.toString();
    }

    public static LinkDTO formLink(HttpServletRequest req){
        LinkDTO linkDTO = new LinkDTO();
        linkDTO.setTable(req.getParameter("table"));
        switch (linkDTO.getTable()){
            case "customers_companies" -> {
                linkDTO.setCustomer_id(Integer.parseInt(req.getParameter("customerId")));
                linkDTO.setProject_id(Integer.parseInt(req.getParameter("projectId")));
                linkDTO.setCompany_id(Integer.parseInt(req.getParameter("companyId")));
            }
            case "project_developers" -> {
                linkDTO.setProject_id(Integer.parseInt(req.getParameter("projectId")));
                linkDTO.setDeveloper_id(Integer.parseInt(req.getParameter("developerId")));
            }
            case "developer_skills" -> {
                linkDTO.setDeveloper_id(Integer.parseInt(req.getParameter("developerId")));
                linkDTO.setSkill_id(Integer.parseInt(req.getParameter("skillId")));
            }
        }
        return linkDTO;
    }

}
