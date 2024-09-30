package com.ApiGateway.ApiGateway.config;


import java.time.LocalDate;

import java.util.List;
import com.ApiGateway.ApiGateway.config.Enum.Role;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Provider extends User {

	@NotBlank(message = "Specialization is required")
    @Size(min = 2, max = 100, message = "Specialization must be between 2 and 100 characters")
    private String specialization;

    @NotBlank(message = "Clinic address is required")
    @Size(max = 255, message = "Clinic address should not exceed 255 characters")
    private String clinicAddress;

    @ElementCollection
    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Slot> availableSlots;

    @Min(value = 0, message = "Experience must be a positive number")
    private int experience;

    public Provider() {}

    public Provider(String firstName, String lastName, String email, String password, Role role, String phoneNumber, String address, LocalDate dateOfBirth, String specialization, String clinicAddress, List<Slot> availableSlots, int experience) {
        super(firstName, lastName, email, password, role, phoneNumber, address, dateOfBirth);
        this.specialization = specialization;
        this.clinicAddress = clinicAddress;
        this.availableSlots = availableSlots;
        this.experience = experience;
    }

    // Getters and Setters
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public String getClinicAddress() {
        return clinicAddress;
    }
    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }
    public List<Slot> getAvailableSlots() {
        return availableSlots;
    }
    public void setAvailableSlots(List<Slot> availableSlots) {
        this.availableSlots = availableSlots;
    }
    public int getExperience() {
        return experience;
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
}
