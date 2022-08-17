package com.rent.rentcar.controller;

import com.rent.rentcar.entity.Role;
import com.rent.rentcar.exceptions.GetByIdErrorMessage;
import com.rent.rentcar.exceptions.RemoveByIdErrorMessage;
import com.rent.rentcar.repository.RoleRepository;
import com.rent.rentcar.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;


    @PostMapping("/save")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.addRole(role));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Role>> findAllRole() {
        return ResponseEntity.ok(roleService.getAllRole());
    }

    @GetMapping("/find-category/{id}")
    public ResponseEntity<Role> getRole(@PathVariable Long id) throws GetByIdErrorMessage {
        if (!roleRepository.existsById(id)) {
            throw new GetByIdErrorMessage("Böyle bir rol bulunmamakta");
        }
        return ResponseEntity.ok(roleService.getByIdRole(id));
    }

    @DeleteMapping("/remove-category/{id}")
    public ResponseEntity<Role> removeRole(@PathVariable Long id) throws RemoveByIdErrorMessage {
        if (!roleRepository.existsById(id)) {
            throw new RemoveByIdErrorMessage("Silmek istediğiniz rol kayıdı bulunmamakta");
        }
        roleService.removeRole(id);
        return new ResponseEntity<Role>(HttpStatus.ACCEPTED);
    }

}
