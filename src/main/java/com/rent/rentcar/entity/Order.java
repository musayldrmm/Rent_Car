package com.rent.rentcar.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Order")
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderDate;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "Customer_id")
    @JsonIgnoreProperties(value = {"password","accountNonExpired","accountNonLocked","authorities","credentialsNonExpired","enabled","role",})
    private Customer customer;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Product> product;

    private int orderPrice;

    private int quantity;

}
