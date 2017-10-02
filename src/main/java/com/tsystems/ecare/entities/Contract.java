package com.tsystems.ecare.entities;

import com.tsystems.ecare.entities.enums.ContractStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "contract")
@NamedQueries({
        @NamedQuery(name = "findAllOptionsByRate" ,
                    query = "select c.rate.optionList from Contract c where c.id = :id"),
        @NamedQuery(name = "findAllOptionsByContract" ,
                query = "select c.optionList from Contract c where c.id = :id")
})
public class Contract {
    @Id @GeneratedValue
    private Long id;

    @Column(name = "number")
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id", nullable = false)
    private Rate rate;

    @ManyToMany
    @JoinTable(
            name = "contract_option",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> optionList;

    @Enumerated(EnumType.STRING)
    @Column(name ="status")
    private ContractStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{id=" + id + "}";
    }
}
