package com.tsystems.ecare.entities;


import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
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
    @Id @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "description")
    private String description;

    @Column(name ="calls")
    private Integer calls;

    @Column(name ="sms")
    private Integer sms;

    @Column(name ="internet")
    private Integer internet;

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
