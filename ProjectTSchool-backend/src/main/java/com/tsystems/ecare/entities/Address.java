package com.tsystems.ecare.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    @Max(30)
    private String street;

    @Column(name = "city")
    @Max(30)
    private String city;

    @Column(name = "country")
    @Max(30)
    private String country;

    @Column(name = "zipcode")
    @Max(10)
    private String zipcode;

    @Column(name = "house_number")
    @Max(10)
    private Integer houseNumber;

    @OneToOne(mappedBy = "address", cascade = CascadeType.ALL)
    private User user;

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", zipcode='" + zipcode  +
                '}';
    }
}
