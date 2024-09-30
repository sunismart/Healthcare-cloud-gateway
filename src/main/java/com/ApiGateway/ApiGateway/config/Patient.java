package com.ApiGateway.ApiGateway.config;

import java.time.LocalDate;
import com.ApiGateway.ApiGateway.config.Enum.Role;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Patient extends User {

	@Size(max = 500, message = "Medical history should not exceed 500 characters")
	private String medicalHistory;
	
	@NotBlank(message = "Insurance details are required")
	@Size(max = 255, message = "Insurance details should not exceed 255 characters")
	private String insuranceDetails;
	
    public Patient() {}

    public Patient(String firstName, String lastName, String email, String password, Role role, String phoneNumber, String address, LocalDate dateOfBirth, String medicalHistory, String insuranceDetails) {
        super(firstName, lastName, email, password, role, phoneNumber, address, dateOfBirth);
        this.medicalHistory = medicalHistory;
        this.insuranceDetails = insuranceDetails;
    }

    // Getters and Setters
    public String getMedicalHistory() {
        return medicalHistory;
    }
    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }
    public String getInsuranceDetails() {
        return insuranceDetails;
    }
    public void setInsuranceDetails(String insuranceDetails) {
        this.insuranceDetails = insuranceDetails;
    }
}
