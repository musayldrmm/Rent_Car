package com.rent.rentcar.repository;

import com.rent.rentcar.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
