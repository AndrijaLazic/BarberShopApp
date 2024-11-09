package com.BarberShop.AdminMicroservice.DTOS;

import com.BarberShop.AdminMicroservice.Models.AdminUser;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link AdminUser}
 */
@Value
public class AdminUserDto implements Serializable {
    @NotNull
    @Size(max = 20)
    String firstName;
    @NotNull
    @Size(max = 20)
    String lastName;
    @NotNull
    @Size(max = 60)
    String email;
    @NotNull
    @Size(max = 30,min = 8)
    String password;
}