package com.rent.rentcar.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Model_name")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ModelName implements Serializable {
    @Id
    private Long id;
    @Column(name = "Name")
    private String name;

    @ManyToOne
    private CarBrand brand;
}
