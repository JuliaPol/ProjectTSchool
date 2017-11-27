package com.tsystems.ecare.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
        @NamedQuery(name = Option.OPTION_FIND_OPTION_BY_NAME,
                query = "select o from Option o where o.name = :name"),
})
public class Option {

    public static final String OPTION_FIND_ALL_OPTIONS_FOR_CUSTOMER = "Option.findAllOptionsForCustomer";
    public static final String OPTION_FIND_ALL_OPTIONS_IN_RATE_FOR_CUSTOMER = "Option.findAllOptionsInRateForCustomer";
    public static final String OPTION_FIND_OPTION_BY_NAME = "Option.findAllOptionByName";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    @NotEmpty
    private String name;

    @Column(name = "cost")
    @NotNull
    @Min(0)
    private Integer cost;

    @Column(name = "cost_of_connection")
    @NotNull
    @Min(0)
    private Integer costOfConnection;

    @Column(name = "description")
    @Size(max = 150)
    private String description;

    @Column(name = "image")
    private String image;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "optionList")
    private List<Contract> contractList;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(mappedBy = "optionList")
    private List<Rate> rateList;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "incompatible_options",
            joinColumns = @JoinColumn(name = "first_id"),
            inverseJoinColumns = @JoinColumn(name = "second_id")
    )
    private List<Option> incOptions;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "incompatible_options",
            joinColumns = @JoinColumn(name = "second_id"),
            inverseJoinColumns = @JoinColumn(name = "first_id")
    )
    private List<Option> incOptionsOf;

    @JsonIgnore
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "compatible_options",
            joinColumns = @JoinColumn(name = "first_id"),
            inverseJoinColumns = @JoinColumn(name = "second_id")
    )
    private List<Option> compOptions;


    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(name = "compatible_options",
            joinColumns = @JoinColumn(name = "second_id"),
            inverseJoinColumns = @JoinColumn(name = "first_id")
    )
    private List<Option> compOptionsOf;

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
