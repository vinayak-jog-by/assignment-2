package com.assignment.employeeservice.repo;

import com.assignment.employeeservice.model.EmployeeDTO;
import com.assignment.employeeservice.model.EmployeeSkill;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import reactor.core.publisher.Flux;

public interface EmployeeSkillRepository  extends ReactiveCassandraRepository<EmployeeSkill, Integer> {

    @Query("select * from emp_skill where java_exp >= ?0 and spring_exp >= ?1 ALLOW FILTERING")
    @AllowFiltering
    public Flux<EmployeeSkill> findBySkills(Double java_exp, Double spring_exp);
}
