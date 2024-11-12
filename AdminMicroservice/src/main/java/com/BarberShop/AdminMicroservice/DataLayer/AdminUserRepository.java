package com.BarberShop.AdminMicroservice.DataLayer;

import com.BarberShop.AdminMicroservice.Models.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, String> {
}