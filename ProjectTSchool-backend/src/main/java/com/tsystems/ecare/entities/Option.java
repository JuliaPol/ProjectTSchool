package com.tsystems.ecare.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CollectionType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        @NamedQuery(name = Option.OPTION_FIND_OPTION_BY_NAME,
                query = "select o from Option o where o.name = :name"),
})
public class Option {

    public static final String OPTION_FIND_ALL_OPTIONS_FOR_CUSTOMER = "Option.findAllOptionsForCustomer";
    public static final String OPTION_FIND_ALL_OPTIONS_IN_RATE_FOR_CUSTOMER = "Option.findAllOptionsInRateForCustomer";
    public static final String OPTION_FIND_OPTION_BY_NAME = "Option.findAllOptionByName";

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

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "optionList")
    private List<Contract> contractList;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "optionList")
    private List<Rate> rateList;


    @ManyToOne(cascade={CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name="compatible_option")
    @JsonIgnore
    public Option compatibleOption;


    @OneToMany(mappedBy="compatibleOption", fetch = FetchType.EAGER)
    public Set<Option> compatibleOptionList = new HashSet<>();

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="incompatible_option")
    private Option incompatibleOption;

    @LazyCollection(LazyCollectionOption.FALSE)
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
