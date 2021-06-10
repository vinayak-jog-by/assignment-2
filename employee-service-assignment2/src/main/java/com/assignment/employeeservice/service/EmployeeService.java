package com.assignment.employeeservice.service;

import com.assignment.employeeservice.model.Employee;
import com.assignment.employeeservice.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Flux<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public Mono<Employee> setEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Flux<Employee> findByEmpId(List idList){ return employeeRepository.findAllById((Iterable<Integer>) idList); }
}
