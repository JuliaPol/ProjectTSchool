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
        @NamedQuery(name = "findAllUsersByRole" ,
                query = "select r.userList from Role r where r.roleName=:role")
})
public class Role {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(mappedBy = "roleList")
    private List<User> userList;
}
