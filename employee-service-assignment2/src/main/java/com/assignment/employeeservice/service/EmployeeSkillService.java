package com.assignment.employeeservice.service;

import com.assignment.employeeservice.model.EmployeeFindQueryObject;
import com.assignment.employeeservice.model.EmployeeSkill;
import com.assignment.employeeservice.repo.EmployeeSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeSkillService {
    @Autowired
    EmployeeSkillRepository employeeSkillRepository;

    public Mono<EmployeeSkill> saveEmployeeSkill(EmployeeSkill employeeSkill){
        return employeeSkillRepository.save(employeeSkill);
    }

    public Flux<EmployeeSkill> findByEmpSkill(EmployeeFindQueryObject query){
        return employeeSkillRepository.findBySkills(query.getJava_exp()  == null? 0: query.getJava_exp(), query.getSpring_exp() == null ? 0 : query.getSpring_exp() );
        //return employeeSkillRepository.findBySkills(query.getJava_exp(), query.getSpring_exp() );
    }
}
