package com.tsystems.ecare.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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
    @NotEmpty
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    private String lastName;

    @Column(name = "email")
    @Email
    @NotEmpty
    private String email;

    @Column(name = "comment")
    private String comment;

    @Column(name = "login", unique = true)
    @Min(4)
    @Max(50)
    private String login;

    @Past
    @Column(name = "birth_date")
    @Type(type="date")
    private Date birthDate;

    @Column(name = "registration_date")
    @Type(type="date")
    private Date registrationDate;

    @Column(name = "passport_number", unique = true)
    @NotEmpty
    private String passportNumber;

    @Column(name = "passport_issued_when")
    @Type(type="date")
    private Date passportIssuedWhen;

    @Column(name = "passport_issued_by_whom")
    private String passportIssuedByWhom;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "user")
    private List<Contract> contractList;

    @Column(name = "password", nullable = false)
    @Min(6)
    @NotEmpty
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
