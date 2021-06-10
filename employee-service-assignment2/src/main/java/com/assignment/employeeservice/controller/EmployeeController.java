package com.assignment.employeeservice.controller;

import com.assignment.employeeservice.model.*;
import com.assignment.employeeservice.service.EmployeeAndSkillService;
import com.assignment.employeeservice.service.EmployeeService;
import com.assignment.employeeservice.service.EmployeeSkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @Autowired
    EmployeeSkillService skillService;

    @Autowired
    EmployeeAndSkillService employeeAndSkillService;


    @GetMapping("/list")
    public Flux<Employee> getAllEmployees(){
        return service.getAllEmployees();
    }

    @PostMapping("/save")
    public Mono<EmployeeDTOResponse> setEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeAndSkillService.createEmployee(employeeDTO);
    }

    @PostMapping("/findEmpBySkill")
    public Flux<EmployeeDTO> findBySkill(@RequestBody EmployeeFindQueryObject query ){
        return employeeAndSkillService.findBySkill(query);
    }



}
