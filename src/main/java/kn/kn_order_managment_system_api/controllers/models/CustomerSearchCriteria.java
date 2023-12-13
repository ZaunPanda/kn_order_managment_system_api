package kn.kn_order_managment_system_api.controllers.models;

public class CustomerSearchCriteria {

    private String fullName;
    private String email;
    private String telephone;

    public CustomerSearchCriteria() {
    }

    public CustomerSearchCriteria(String fullName, String email, String telephone) {
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}