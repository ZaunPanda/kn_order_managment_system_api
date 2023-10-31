package kn.kn_order_managment_system_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registration_code", unique = true)
    private int registrationCode;

    @NotEmpty(message = "Full name cannot be empty")
    @Size(max = 100, message = "Full name can be up to 100 characters")
    @Column(name = "full_name")
    private String fullName;

    @Email(message = "Invalid email address")
    @Column(name = "email")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid telephone number")
    @Column(name = "telephone")
    private String telephone;

    public Customer(String fullName, String email, String telephone) {
        this.fullName = fullName;
        this.email = email;
        this.telephone = telephone;
    }

    public int getRegistrationCode() {
        return registrationCode;
    }

    public void setRegistrationCode(int registrationCode) {
        this.registrationCode = registrationCode;
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
