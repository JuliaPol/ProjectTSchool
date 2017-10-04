package com.tsystems.ecare.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`option`")
public class Option {
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
    private List<Contract> contractList;

    @ManyToMany(mappedBy = "optionList")
    private List<Rate> rateList;

    @ManyToOne
    @JoinColumn(name="compatible_option")
    private Option compatibleOption;

    @OneToMany(mappedBy="compatibleOption",fetch = FetchType.EAGER)
    private List<Option> compatibleOptionList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="incompatible_option")
    private Option incompatibleOption;

    @OneToMany(mappedBy="incompatibleOption",fetch = FetchType.EAGER)
    private List<Option> incompatibleOptionList = new ArrayList<>();
}
