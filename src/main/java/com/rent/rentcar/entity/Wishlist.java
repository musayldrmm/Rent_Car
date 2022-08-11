package com.rent.rentcar.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Wishlist")
@Getter
@Setter
@ToString
public class Wishlist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Customer_id")
    @JsonIgnoreProperties(value = {"password"})
    private Customer customer;

    @ManyToOne
    private Product product;
}
