package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {


    @Autowired
    private EmployeeService employeeService;

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {

        EmployeeEntity employeeEntity = employeeService.getEmployeeById(Integer.valueOf(id));
        Employee employee = new Employee();
        employee.setId(Integer.valueOf(id));
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setLastName(employeeEntity.getLastName());
        employee.setAddressLine1(employeeEntity.getAddressEntity().getLine1());
        employee.setAddressLine2(employeeEntity.getAddressEntity().getLine2());
        employee.setState(employeeEntity.getAddressEntity().getState());
        employee.setCity(employeeEntity.getAddressEntity().getCity());
        employee.setCountry(employeeEntity.getAddressEntity().getCountry());
        employee.setZipCode(employeeEntity.getAddressEntity().getZipCode());
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity createEmployee(@Valid Employee employee) {

        return employeeService.createEmployee(employee);
    }
}
