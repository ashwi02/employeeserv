package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.entity.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.repository.AddressRepository;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    public EmployeeEntity getEmployeeById(long id) {
        EmployeeEntity obj = employeeRepository.findById(id).get();
        return obj;
    }

    public ResponseEntity createEmployee(Employee employee){

        if(StringUtils.isEmpty(employee.getId())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee data is incomplete.");
        }
        if(employeeRepository.findById(Long.valueOf(employee.getId())).isPresent()){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Employee with id: "+ employee.getId()+" is already present.");
        }

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(employee.getId());
        addressEntity.setLine1(employee.getAddressLine1());
        addressEntity.setLine2(employee.getAddressLine2());
        addressEntity.setCity(employee.getCity());
        addressEntity.setState(employee.getState());
        addressEntity.setCountry(employee.getCountry());
        addressEntity.setZipCode(employee.getZipCode());
        addressRepository.save(addressEntity);

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(employee.getId());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setAddressEntity(addressEntity);
        employeeRepository.save(employeeEntity);
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Employee created successfully.");
    }
}
