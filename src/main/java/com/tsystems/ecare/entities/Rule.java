package com.tsystems.ecare.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rule")
public class Rule {

    @Id
    private Long id;

    @Column(name = "option_1")
    private Long option1;

    @Column(name = "option_2")
    private Long option2;

    @Column(name = "compatibility")
    private Boolean compatibility;

}
