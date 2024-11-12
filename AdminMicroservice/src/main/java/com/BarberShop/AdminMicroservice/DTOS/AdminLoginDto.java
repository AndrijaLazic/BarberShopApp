package com.BarberShop.AdminMicroservice.DTOS;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.BarberShop.AdminMicroservice.Models.AdminUser}
 */
@Value
public class AdminLoginDto implements Serializable {
    @NotNull
    @Size(max = 60)
    String email;

    @NotNull
    String password;
}