package com.rent.rentcar.service;

import com.rent.rentcar.entity.CarBrand;
import com.rent.rentcar.entity.Role;
import com.rent.rentcar.repository.RoleRepository;
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
public class RoleService {
    @Autowired
    private RoleRepository  roleRepository;

    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    public Role getByIdRole(Long role) {
        return roleRepository.findById(role).get();
    }

    public void removeRole(Long role) {
        roleRepository.deleteById(role);
    }

    public void saveRole() {
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/role.csv"));
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Role c = new Role();
                c.setId(Long.parseLong(data[0]));
                c.setName(data[1]);
                roleRepository.save(c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public ResponseEntity<Role> UpdateRecord() {
        saveRole();
        return new ResponseEntity<Role>(HttpStatus.ACCEPTED);

    }
}


