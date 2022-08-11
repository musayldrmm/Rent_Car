package com.rent.rentcar.controller;

import com.rent.rentcar.entity.ModelName;
import com.rent.rentcar.exceptions.GetByIdErrorMessage;
import com.rent.rentcar.exceptions.RemoveByIdErrorMessage;
import com.rent.rentcar.repository.ModelNameRepository;
import com.rent.rentcar.service.ModelNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/model-name")
public class ModelNameController {
    @Autowired
    private ModelNameService modelNameService;

    @Autowired
    private ModelNameRepository modelNameRepository;

    private Long id;

    @PostMapping("/save")
    public ResponseEntity<ModelName> add(@RequestBody ModelName modelName) {
        return ResponseEntity.ok(modelNameService.add(modelName));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ModelName>> findAll() {
        return ResponseEntity.ok(modelNameService.getAll());
    }

    @GetMapping("/find-model-name/{id}")
    public ResponseEntity<ModelName> getModelName(@PathVariable Long id) throws GetByIdErrorMessage {

        if (!modelNameRepository.existsById(id)) {
            throw new GetByIdErrorMessage("Böyle bir araba modeli bulunmamakta");
        }

        return ResponseEntity.ok(modelNameService.getById(id));
    }

    @DeleteMapping("/remove-model-name/{id}")
    public ResponseEntity<ModelName> remove(@PathVariable Long id) throws RemoveByIdErrorMessage {
        if (!modelNameRepository.existsById(id)) {
            throw new RemoveByIdErrorMessage("Silmek istediğiniz  araba modeli  kayıdı bulunmamakta");
        }

        modelNameService.removeModelName(id);
        return new ResponseEntity<ModelName>(HttpStatus.ACCEPTED);
    }

    @PostConstruct
    public ResponseEntity<ModelName> updateRecord() {
        modelNameService.saveModelName();
        return new ResponseEntity<ModelName>(HttpStatus.ACCEPTED);

    }

}
