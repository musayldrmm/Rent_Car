package com.rent.rentcar.repository;

import com.rent.rentcar.entity.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
}
