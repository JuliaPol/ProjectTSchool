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
@Table(name = "option")
public class Option {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "cost")
    private Integer cost;
    @Column(name = "cost_of_connection")
    private Integer costOfConnection;
    @Column(name = "description")
    private String description;
}
