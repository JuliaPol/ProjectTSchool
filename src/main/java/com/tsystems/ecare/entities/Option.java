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
                query = "select c.optionList from Contract c where c.number = :number"),
        @NamedQuery(name = Option.OPTION_FIND_ALL_OPTIONS_IN_RATE_FOR_CUSTOMER,
                query = "select c.rate.optionList from Contract c where c.number = :number"),
})
public class Option {

    public static final String OPTION_FIND_ALL_OPTIONS_FOR_CUSTOMER = "Option.findAllOptionsForCustomer";
    public static final String OPTION_FIND_ALL_OPTIONS_IN_RATE_FOR_CUSTOMER = "Option.findAllOptionsInRateForCustomer";

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;

        return id.equals(option.id);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Option{" +
                "name='" + name + '\'' +
                '}';
    }
}
