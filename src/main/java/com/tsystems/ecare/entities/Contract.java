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
        @NamedQuery(name = Contract.CONTRACT_FIND_ALL_OPTIONS_BY_RATE,
                query = "select c.rate.optionList from Contract c where c.id = :id"),
        @NamedQuery(name = Contract.CONTRACT_FIND_ALL_CONTACTS_BY_USER_ID,
                query = "select c.number from Contract c where c.user.login = :login"),
        @NamedQuery(name = Contract.CONTRACT_FIND_CONTRACT_BY_NUMBER,
                query = "select c from Contract c where c.number = :number")
})
public class Contract {

    public static final String CONTRACT_FIND_ALL_CONTACTS_BY_USER_ID = "ContractController.findAllContactsByUserId";
    public static final String CONTRACT_FIND_ALL_OPTIONS_BY_RATE = "ContractController.findAllOptionsByRate";
    public static final String CONTRACT_FIND_CONTRACT_BY_NUMBER = "Contract.findContractByNumber";

    @Id @GeneratedValue
    private Long id;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "rate_id", nullable = false)
    private Rate rate;

    @ManyToMany(fetch = FetchType.EAGER)
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
