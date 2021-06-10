package com.assignment.employeeservice.model;

import com.assignment.employeeservice.model.Employee;
import com.assignment.employeeservice.model.EmployeeSkill;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class EmployeeDTO {
    Integer emp_id;
    String emp_name;
    String emp_city;
    String emp_phone;
    Double java_exp;
    Double spring_exp;

    public EmployeeDTO (Employee e, EmployeeSkill es){
        this.setEmp_id(e.getEmp_id());
        this.setEmp_name(e.getEmp_name());
        this.setEmp_city(e.getEmp_city());
        this.setEmp_phone(e.getEmp_phone());
        this.setSpring_exp(es.getSpring_exp());
        this.setJava_exp(es.getJava_exp());
    }

    public Employee getEmployee(){
        Employee employee = new Employee();
        employee.setEmp_id(this.getEmp_id());
        employee.setEmp_name(this.getEmp_name());
        employee.setEmp_city(this.getEmp_city());
        employee.setEmp_phone(this.getEmp_phone());

        return employee;
    }

    public EmployeeSkill getEmployeeSkill(){
        EmployeeSkill employeeSkill = new EmployeeSkill();
        employeeSkill.setEmp_id(this.getEmp_id());
        employeeSkill.setJava_exp(this.getJava_exp());
        employeeSkill.setSpring_exp(this.getSpring_exp());
        return employeeSkill;
    }

    public static EmployeeDTO getEmployeeDTO(Employee e, EmployeeSkill es){
        EmployeeDTO dto = new EmployeeDTO();
        dto.setEmp_id(e.getEmp_id());
        dto.setEmp_name(e.getEmp_name());
        dto.setEmp_city(e.getEmp_city());
        dto.setEmp_phone(e.getEmp_phone());
        dto.setJava_exp(es.getJava_exp());
        dto.setSpring_exp(es.getSpring_exp());

        return dto;
    }


}
