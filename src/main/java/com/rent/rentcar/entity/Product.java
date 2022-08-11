package com.rent.rentcar.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Product")
@Getter
@Setter
@ToString

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Car_Price")
    private int carPrice;

    @Column(name = "Description", length = 500)
    private String description;

    private String createdDate;

    private String plateNumber;

    private int modelYear;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "Model_name")
    @JsonIgnoreProperties(value = {"brand"})
    private ModelName modelName;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JsonIgnoreProperties(value = {"model_name"})
    @JoinColumn(name = "Brand_Car")
    private CarBrand carBrand;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JsonIgnoreProperties(value = {"product"})
    @JoinTable(name = "Rel_category__product", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}
