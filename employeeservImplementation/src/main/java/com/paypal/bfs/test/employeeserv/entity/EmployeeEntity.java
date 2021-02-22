package com.paypal.bfs.test.employeeserv.entity;
import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="employee")
public class EmployeeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    private long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_fk")
    private AddressEntity addressEntity;
}
