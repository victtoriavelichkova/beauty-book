package com.beautybook.model.binding;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClientBindingModel {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    private String lastName;

    @NotBlank(message = "Phone is required")
    @Size(min = 6, max = 20, message = "Phone must be between 6 and 20 characters")
    private String phone;

    public String getFirstName() {
        return firstName;
    }

    public ClientBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ClientBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public ClientBindingModel setPhone(String phone) {
        this.phone = phone;
        return this;
    }
}