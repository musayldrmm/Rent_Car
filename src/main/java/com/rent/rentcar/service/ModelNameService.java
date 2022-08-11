package com.rent.rentcar.service;

import com.rent.rentcar.entity.CarBrand;
import com.rent.rentcar.entity.ModelName;
import com.rent.rentcar.repository.ModelNameRepository;
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

public class ModelNameService {
    @Autowired
    private ModelNameRepository modelNameRepository;

    public ModelName add(ModelName modelName) {
        return modelNameRepository.save(modelName);
    }

    public List<ModelName> getAll() {
        return modelNameRepository.findAll();
    }

    public ModelName getById(Long Id) {
        return modelNameRepository.findById(Id).get();
    }

    public void removeModelName(Long modelId) {
        modelNameRepository.deleteById(modelId);
    }

    public void saveModelName() {
        String line = "";
        try {

            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/modelname.csv"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                ModelName c = new ModelName();
                c.setId(Long.parseLong(data[0]));
                c.setName(data[1]);
                c.setBrand(new CarBrand(Long.parseLong(data[2]), ""));
                modelNameRepository.save(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public ResponseEntity<ModelName> UpdateRecord() {
        saveModelName();
        return new ResponseEntity<ModelName>(HttpStatus.ACCEPTED);

    }

}
