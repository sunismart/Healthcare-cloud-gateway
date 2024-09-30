package com.ApiGateway.ApiGateway.config;

import java.time.LocalDate;
import com.ApiGateway.ApiGateway.config.Enum.Role;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Admin extends User {

	@NotBlank(message = "Admin code is required")
    @Size(min = 5, max = 20, message = "Admin code must be between 5 and 20 characters")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "Admin code must be alphanumeric with no special characters")
    private String adminCode;

    public Admin() {}

    public Admin(String firstName, String lastName, String email, String password, Role role, String phoneNumber, String address, LocalDate dateOfBirth, String adminCode) {
        super(firstName, lastName, email, password, role, phoneNumber, address, dateOfBirth);
        this.adminCode = adminCode;
    }

    // Getters and Setters
    public String getAdminCode() {
        return adminCode;
    }
    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }
}
