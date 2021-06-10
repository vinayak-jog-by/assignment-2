package com.assignment.employeeservice.repo;

import com.assignment.employeeservice.model.Employee;
import org.reactivestreams.Publisher;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface EmployeeRepository extends ReactiveCassandraRepository<Employee, Integer> {
    public Flux<Employee> findAllById(List<Integer> idList);
}