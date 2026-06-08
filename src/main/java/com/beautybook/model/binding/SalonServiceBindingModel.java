package com.beautybook.model.binding;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class SalonServiceBindingModel {

    @NotBlank(message = "Service name is required")
    @Size(min = 2, max = 50, message = "Service name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer durationMinutes;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(Integer durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}