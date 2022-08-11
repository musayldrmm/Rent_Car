package com.rent.rentcar.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Category")
@Getter
@Setter
@ToString

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Category_name", length = 20, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @JsonIgnore
    private List<Product> product;
}
