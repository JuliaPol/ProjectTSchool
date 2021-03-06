package com.tsystems.ecare.entities;

import com.tsystems.ecare.entities.enums.ContractStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contract")
@NamedQueries({
        @NamedQuery(name = Contract.CONTRACT_FIND_ALL_OPTIONS_BY_RATE,
                query = "select c.rate.optionList from Contract c where c.id = :id"),
        @NamedQuery(name = Contract.CONTRACT_FIND_ALL_CONTACTS_BY_USER_ID,
                query = "select c.number from Contract c where c.user.login = :login"),
        @NamedQuery(name = Contract.CONTRACT_FIND_CONTRACT_BY_NUMBER,
                query = "select c from Contract c where c.number = :number"),
        @NamedQuery(name = Contract.CONTRACT_FIND_USER_BY_NUMBER,
                query = "select c.user from Contract c where c.number = :number"),
        @NamedQuery(name = Contract.CONTRACT_SEARCH_BY_NUMBER,
                query = "select c.user from Contract c where c.number like :number"),
        @NamedQuery(name = Contract.CONTRACT_SEARCH_BY_NAME,
                query = "select c.user from Contract c where c.user.lastName like :name"),
        @NamedQuery(name = Contract.CONTRACT_UPDATE_WITH_DELETED_RATE,
                query = "update Contract c set c.rate = null where c.rate.id = :id"),
})
public class Contract {

    public static final String CONTRACT_FIND_ALL_CONTACTS_BY_USER_ID = "Contract.findAllContactsByUserId";
    public static final String CONTRACT_FIND_ALL_OPTIONS_BY_RATE = "Contract.findAllOptionsByRate";
    public static final String CONTRACT_FIND_CONTRACT_BY_NUMBER = "Contract.findContractByNumber";
    public static final String CONTRACT_FIND_USER_BY_NUMBER = "Contract.findUserByNumber";
    public static final String CONTRACT_SEARCH_BY_NUMBER = "Contract.searchByNumber";
    public static final String CONTRACT_SEARCH_BY_NAME = "Contract.searchByName";
    public static final String CONTRACT_UPDATE_WITH_DELETED_RATE = "Contract.updateWithDeletedRate";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", unique = true)
    @Size(min = 10, max = 20)
    private String number;

    @Column(name = "creation_date")
    @Type(type = "date")
    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = "rate_id")
    private Rate rate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "contract_option",
            joinColumns = @JoinColumn(name = "contract_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> optionList;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ContractStatus status;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Contract that = (Contract) o;

        return number != null ? number.equals(that.number) : that.number == null;

    }

    @Override
    public String toString() {
        return getClass().getName() + "{id=" + id + "}";
    }
}
