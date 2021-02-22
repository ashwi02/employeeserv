package com.paypal.bfs.test.employeeserv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="address")
public class AddressEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    private long id;
    @Column(name="line1")
    private String line1;
    @Column(name="line2")
    private String line2;
    @Column(name="city")
    private String city;
    @Column(name="state")
    private String state;
    @Column(name="country")
    private String country;
    @Column(name="zip_code")
    private String zipCode;
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "addressEntity", cascade = CascadeType.ALL)
    private EmployeeEntity employeeEntity;
}
