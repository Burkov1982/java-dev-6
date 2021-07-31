package ua.goit.dao.model;

public class Developer {
    private Integer developer_id;
    private String first_name;
    private String last_name;
    private String gender;
    private Integer salary;

    public Developer() {
    }

    public Developer(Integer developer_id, String first_name, String last_name, String gender, Integer salary) {
        this.developer_id = developer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.salary = salary;
    }

    public Integer getDeveloper_id() {
        return developer_id;
    }

    public void setDeveloper_id(Integer developer_id) {
        this.developer_id = developer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString(){
        return String.format("""
                Идентификатор разработчика: %d\s
                Имя разработчика: %s\s
                Фамилия разработчика: %s\s
                Пол разработчика: %s\s
                Зарплата разработчика: %d\s
                """, developer_id, first_name, last_name, gender, salary);
    }
}
