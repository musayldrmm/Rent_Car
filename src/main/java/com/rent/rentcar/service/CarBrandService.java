package com.rent.rentcar.service;

import com.rent.rentcar.entity.CarBrand;
import com.rent.rentcar.repository.CarBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class CarBrandService {
    @Autowired
    private CarBrandRepository carBrandModelRepository;

    public CarBrand add(CarBrand carmodel) {
        return carBrandModelRepository.save(carmodel);
    }

    public List<CarBrand> getAll() {
        return carBrandModelRepository.findAll();
    }

    public CarBrand getById(Long carId) {
        return carBrandModelRepository.findById(carId).get();
    }

    public void delete(Long id) {
        carBrandModelRepository.deleteById(id);
    }

    public void SaveBrandCar() {
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/CarBrand.csv"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                CarBrand c = new CarBrand();
                c.setId(Long.parseLong(data[0]));
                c.setName(data[1]);
                carBrandModelRepository.save(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public ResponseEntity<CarBrand> UpdateRecord() {
        SaveBrandCar();
        return new ResponseEntity<CarBrand>(HttpStatus.ACCEPTED);

    }

}
