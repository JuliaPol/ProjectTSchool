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
@NamedQueries({
        @NamedQuery(name = Option.OPTION_FIND_ALL_OPTIONS_FOR_CUSTOMER,
                query = "select o from Option o where o not in (select c.optionList from Contract c where c.number = :number)")
})
public class Option {

    public static final String OPTION_FIND_ALL_OPTIONS_FOR_CUSTOMER = "Option.findAllOptionsForCustomer";

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

    @OneToMany(mappedBy="compatibleOption")
    private List<Option> compatibleOptionList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="incompatible_option")
    private Option incompatibleOption;

    @OneToMany(mappedBy="incompatibleOption")
    private List<Option> incompatibleOptionList = new ArrayList<>();
}
