package com.assignment.employeeservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("emp")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @PrimaryKey
    Integer emp_id;
    String emp_name;
    String emp_city;
    String emp_phone;
}
