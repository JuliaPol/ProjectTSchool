package com.tsystems.ecare.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    @Size(max = 40)
    private String street;

    @Column(name = "city")
    @Size(max = 40)
    private String city;

    @Column(name = "country")
    @Size(max = 40)
    private String country;

    @Column(name = "zipcode")
    @Size(max = 40)
    private String zipcode;

    @Column(name = "house_number")
    @Min(0)
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
                ", zipcode='" + zipcode +
                '}';
    }
}
