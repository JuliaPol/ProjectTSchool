package com.tsystems.ecare.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = "findByLogin" ,
                query = "select u from User u where u.login=:login")
})
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "birth_date")
    private Date birth_date;

    @Column(name = "passport_number", unique = true)
    private String passportNumber;

    @Column(name = "passport_issued_when")
    private String passportIssuedWhen;

    @Column(name = "passport_issued_by_whom")
    private String passportIssuedByWhom;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<Contract> contractList;

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{id=" + id + "}";
    }
}
