package com.codegym.demothymleaf.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
@Entity
@Table(name = "customer4")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 2,max = 30)
    private String name;
    private LocalDate birthDate;
    private String avatar;
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
}
