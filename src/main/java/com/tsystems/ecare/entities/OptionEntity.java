package com.tsystems.ecare.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`option`")
public class OptionEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "cost_of_connection")
    private Integer costOfConnection;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "optionList")
    private List<ContractEntity> contractList;

    @ManyToMany(mappedBy = "optionList")
    private List<RateEntity> rateList;
}
