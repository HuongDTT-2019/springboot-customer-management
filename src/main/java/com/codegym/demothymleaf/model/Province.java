package com.codegym.demothymleaf.model;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "province3")
@Data
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(targetEntity = Province.class)
    private List<Customer> customers;
}
