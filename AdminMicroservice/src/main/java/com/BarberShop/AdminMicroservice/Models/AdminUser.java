package com.BarberShop.AdminMicroservice.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "admin_users")
public class AdminUser {
    @Id
    @Size(max = 16)
    @Column(name = "id", nullable = false, length = 16)
    private String id;

    @Size(max = 20)
    @NotNull
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Size(max = 20)
    @NotNull
    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Size(max = 16)
    @NotNull
    @Column(name = "password_salt", nullable = false, length = 16)
    private String passwordSalt;

    @Size(max = 64)
    @NotNull
    @Column(name = "password_hash", nullable = false, length = 64)
    private String passwordHash;

    @Size(max = 60)
    @NotNull
    @Column(name = "email", nullable = false, length = 60)
    private String email;

}