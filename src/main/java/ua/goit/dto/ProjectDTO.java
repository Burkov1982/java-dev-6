package ua.goit.dto;

import java.time.LocalDate;

public class ProjectDTO {
    private Integer project_id;
    private String project_name;
    private String project_description;
    private Integer cost;
    private LocalDate start_date;

    public ProjectDTO() {
    }

    public ProjectDTO(Integer project_id, String project_name, String project_description, Integer cost, LocalDate start_date) {
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
        return "Project id: " + project_id +
                "Project name: " + project_name +
                "Project description: " + project_description +
                "Project cost: " + cost +
                "Project start date: " + start_date.toString();
    }
}
