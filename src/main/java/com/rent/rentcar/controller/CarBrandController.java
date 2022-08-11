package com.rent.rentcar.controller;

import com.rent.rentcar.entity.CarBrand;
import com.rent.rentcar.exceptions.GetByIdErrorMessage;
import com.rent.rentcar.exceptions.RemoveByIdErrorMessage;
import com.rent.rentcar.repository.CarBrandRepository;
import com.rent.rentcar.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car-brand")

public class CarBrandController {
    @Autowired
    private CarBrandService carBrandService;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @PostMapping("/save")
    public ResponseEntity<CarBrand> add(@RequestBody CarBrand carBrand_) {
        return ResponseEntity.ok(carBrandService.add(carBrand_));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CarBrand>> findAll() {
        return ResponseEntity.ok(carBrandService.getAll());
    }

    @DeleteMapping("/remove-car-brand/{id}")
    public ResponseEntity<CarBrand> remove(@PathVariable Long id) throws RemoveByIdErrorMessage {
        if (!carBrandRepository.existsById(id)) {
            throw new RemoveByIdErrorMessage("Silmek istediğiniz  araba markası bulunmamakta");
        }
        carBrandService.delete(id);
        return new ResponseEntity<CarBrand>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/find-car-brand/{id}")
    public ResponseEntity<CarBrand> getById(@PathVariable Long id) throws GetByIdErrorMessage {
        if (!carBrandRepository.existsById(id)) {
            throw new GetByIdErrorMessage("Böyle bir araba markası bulunmamakta");
        }
        return ResponseEntity.ok(carBrandService.getById(id));
    }


}
