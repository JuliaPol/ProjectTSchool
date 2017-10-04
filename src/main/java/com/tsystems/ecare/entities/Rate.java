package com.tsystems.ecare.entities;

import com.tsystems.ecare.entities.enums.RateStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rate")
@NamedQueries({
        @NamedQuery(name = "findByName" ,
                query = "select r from Rate r where r.name=:name")
})
public class Rate {
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rate_option",
            joinColumns = @JoinColumn(name = "rate_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> optionList;

    @OneToMany(mappedBy = "rate")
    private List<Contract> contractList;
}
