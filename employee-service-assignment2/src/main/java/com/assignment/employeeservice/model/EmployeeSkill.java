package com.assignment.employeeservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("emp_skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeSkill {
    @PrimaryKeyColumn(name = "emp_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    Integer emp_id;

    @PrimaryKeyColumn(name = "java_exp", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    Double java_exp;

    @PrimaryKeyColumn(name = "spring_exp", ordinal = 2, type = PrimaryKeyType.PARTITIONED)
    Double spring_exp;
}
