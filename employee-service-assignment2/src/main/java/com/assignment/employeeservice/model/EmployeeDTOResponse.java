package com.assignment.employeeservice.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeDTOResponse {
    Integer emp_id;
    String emp_name;
    String emp_city;
    String emp_phone;
    Double java_exp;
    Double spring_exp;
    String status;

    public EmployeeDTOResponse (EmployeeDTO employeeDTO){
        this.setEmp_id(employeeDTO.getEmp_id());
        this.setEmp_name(employeeDTO.getEmp_name());
        this.setEmp_city(employeeDTO.getEmp_city());
        this.setEmp_phone(employeeDTO.getEmp_phone());
        this.setJava_exp(employeeDTO.getJava_exp());
        this.setSpring_exp(employeeDTO.getSpring_exp());
    }

    public EmployeeDTOResponse (Employee e, EmployeeSkill es){
        this.setEmp_id(e.getEmp_id());
        this.setEmp_name(e.getEmp_name());
        this.setEmp_city(e.getEmp_city());
        this.setEmp_phone(e.getEmp_phone());
        this.setSpring_exp(es.getSpring_exp());
        this.setJava_exp(es.getJava_exp());
    }


}
