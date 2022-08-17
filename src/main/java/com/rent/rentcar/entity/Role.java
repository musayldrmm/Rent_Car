package com.rent.rentcar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Role")
@Getter
@Setter
@ToString
public class Role {
    @Id
    private Long id;

    @Column(name = "name", length = 20)
    private String name;

    @ManyToMany(mappedBy = "role", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JsonIgnore
    private List<Customer> customer;
}
