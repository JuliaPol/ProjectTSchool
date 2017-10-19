package com.tsystems.ecare.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
@NamedQueries({
        @NamedQuery(name = User.USER_FIND_BY_LOGIN ,
                query = "select u from User u where u.login=:login")
})
public class User {

    public static final String USER_FIND_BY_LOGIN = "User.findByLogin";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Date birthDate;

    @Column(name = "passport_number", unique = true)
    private String passportNumber;

    @Column(name = "passport_issued_when")
    private Date passportIssuedWhen;

    @Column(name = "passport_issued_by_whom")
    private String passportIssuedByWhom;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user")
    private List<Contract> contractList;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{id=" + id + "}";
    }
}
