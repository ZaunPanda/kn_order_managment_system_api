package kn.kn_order_managment_system_api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_code")
    private int registration_code;
    @Column(name = "full_name")
    private String full_name;
    @Column(name = "email")
    private String email;
    @Column(name = "telephone")
    private String telephone;

    public Customer() {
    }

    public Customer(String full_name, String email, String telephone) {
        this.full_name = full_name;
        this.email = email;
        this.telephone = telephone;
    }

    public int getRegistration_code() {
        return registration_code;
    }

    public void setRegistration_code(int registration_code) {
        this.registration_code = registration_code;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
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
