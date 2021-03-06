package ua.goit.dao.model;

import java.time.LocalDate;

public class Project {
    private Integer project_id;
    private String project_name;
    private String project_description;
    private Integer cost;
    private LocalDate start_date;

    public Project() {
    }

    public Project(Integer project_id, String project_name, String project_description, Integer cost, LocalDate start_date) {
        this.project_id = project_id;
        this.project_name = project_name;
        this.project_description = project_description;
        this.cost = cost;
        this.start_date = start_date;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    @Override
    public String toString(){
        return String.format("""
                Идентификатор проекта: %s\s
                Название проета: %s\s
                Описание проекта: %s\s
                Стоимость проекта: %s\s
                Дата начала проекта: %s\s
                """, project_id, project_name, project_description, cost, start_date);
    }
}
