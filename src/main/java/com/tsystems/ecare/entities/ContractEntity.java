package com.tsystems.ecare.entities;

import com.tsystems.ecare.entities.enums.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contract")
@NamedQueries({
        @NamedQuery(name = "findAllOptionsByRate" ,
                    query = "select c.rate.optionList from ContractEntity c where c.id = :id"),
        @NamedQuery(name = "findAllOptionsByContract" ,
                query = "select c.optionList from ContractEntity c where c.id = :id")
})
public class ContractEntity {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "number")
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id", nullable = false)
    private RateEntity rate;

    @ManyToMany
    @JoinTable(
            name = "contract_option",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<OptionEntity> optionList;

    @Enumerated(EnumType.STRING)
    @Column(name ="status")
    private ContractStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
