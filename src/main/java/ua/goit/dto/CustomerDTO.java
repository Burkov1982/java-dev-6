package ua.goit.dto;

public class CustomerDTO {
    private Integer customer_id;
    private String customer_name;

    public CustomerDTO() {
    }

    public CustomerDTO(Integer customer_id, String customer_name) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    @Override
    public String toString() {
        return  "Customer_id: " + customer_id +
                "Customer_name: " + customer_name;
    }
}
