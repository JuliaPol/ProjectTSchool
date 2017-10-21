package com.tsystems.ecare.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "role")
@NamedQueries({
        @NamedQuery(name = "Role.findAllUsersByRole" ,
                query = "select r.userList from Role r where r.roleName = 'ROLE_CUSTOMER'"),
        @NamedQuery(name = "Role.findByName" ,
                query = "select r from Role r where r.roleName = :name")
})
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<User> userList;
}
