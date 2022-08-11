package com.rent.rentcar.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Car_Brand")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarBrand implements Serializable {

    @Id
    private Long id;

    @Column(name = "Car_Brand")
    private String name;
}
