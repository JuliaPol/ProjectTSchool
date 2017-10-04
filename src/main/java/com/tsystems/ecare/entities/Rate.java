package com.tsystems.ecare.entities;

import com.tsystems.ecare.entities.enums.RateStatus;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "rate")
public class Rate {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name ="calls")
    private RateStatus calls;

    @Enumerated(EnumType.STRING)
    @Column(name ="sms")
    private RateStatus sms;

    @Enumerated(EnumType.STRING)
    @Column(name ="internet")
    private RateStatus internet;

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
