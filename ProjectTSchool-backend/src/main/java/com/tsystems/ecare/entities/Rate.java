package com.tsystems.ecare.entities;


import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rate")
@NamedQueries({
        @NamedQuery(name = Rate.RATE_FIND_BY_NAME,
                query = "select r from Rate r where r.name=:name"),
        @NamedQuery(name = Rate.RATE_FIND_ALL_FOR_CUSTOMER,
                query = "select r from Rate r where r not in " +
                        "(select c.rate from Contract c where c.number = :number)"),
        @NamedQuery(name = Rate.RATE_FIND_FOR_CUSTOMER_BY_NUMBER,
                query = "select c.rate from Contract c where c.number = :number"),

})
public class Rate {
    public static final String RATE_FIND_BY_NAME = "Rate.findByName";
    public static final String RATE_FIND_FOR_CUSTOMER_BY_NUMBER = "Rate.findForCustomerByNumber";
    public static final String RATE_FIND_ALL_FOR_CUSTOMER = "Rate.findAllForCustomer";

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    @NotEmpty
    private String name;

    @Column(name = "cost")
    @NotNull
    @Min(0)
    private Integer cost;

    @Column(name = "description")
    @NotEmpty
    @Size(max = 150)
    private String description;

    @Column(name ="calls")
    @NotNull
    @Min(0)
    private Integer calls;

    @Column(name ="sms")
    @NotNull
    @Min(0)
    private Integer sms;

    @Column(name ="internet")
    @NotNull
    @Min(0)
    private Integer internet;

    @Column(name ="image")
    private String image;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    @JoinTable(
            name = "rate_option",
            joinColumns = @JoinColumn(name = "rate_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> optionList;

    @OneToMany(mappedBy = "rate")
    private List<Contract> contractList;
}
