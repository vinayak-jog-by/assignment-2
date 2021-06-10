package com.assignment.employeeservice.service;

import com.assignment.employeeservice.model.*;
import com.assignment.employeeservice.repo.EmployeeRepository;
import com.assignment.employeeservice.repo.EmployeeSkillRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class EmployeeAndSkillService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeSkillRepository employeeSkillRepository;

    @Autowired
    EmployeeMessageProducer employeeMessageProducer;

    public Mono<EmployeeDTOResponse> createEmployee(EmployeeDTO employeeDTO) {

        Mono<EmployeeDTOResponse> resp = employeeRepository.existsById(employeeDTO.getEmp_id())
                .doOnNext(exists -> {
                            if (exists) {
                                log.info("Employee exists !!!!");
                            }
                        }
                ).flatMap(
                        e -> {
                            if (!e) {
                                return saveEmployeeDetails(employeeDTO);
                            } else {
                                EmployeeDTOResponse employeeDTOResponse = new EmployeeDTOResponse(employeeDTO);
                                employeeDTOResponse.setStatus("Already Exists");
                                try {
                                    employeeMessageProducer.sendMessage(getJsonString(employeeDTOResponse));
                                } catch (JsonProcessingException jsonProcessingException) {
                                    jsonProcessingException.printStackTrace();
                                }
                                final Mono<EmployeeDTOResponse> responseMono = Mono.just(employeeDTOResponse);
                                return responseMono;
                            }
                        })
                .map(saved -> saved);
        return resp;

    }

    public Mono<EmployeeDTOResponse> saveEmployeeDetails(EmployeeDTO employeeDTO) {
        Employee emp = employeeDTO.getEmployee();
        EmployeeSkill employeeSkill = employeeDTO.getEmployeeSkill();

        return employeeRepository
                .save(emp)
                .zipWith(employeeSkillRepository.save(employeeSkill))
                .map(data ->
                        {
                            Employee empResponse = data.getT1();
                            EmployeeSkill empSkillResponse = data.getT2();
                            EmployeeDTOResponse response = new EmployeeDTOResponse(empResponse, empSkillResponse);
                            response.setStatus("Created");
                            try {
                                employeeMessageProducer.sendMessage(getJsonString(response));
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                            }
                            return response;
                        }
                );
    }

    public String getJsonString(EmployeeDTOResponse resp) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(resp);
    }


    public Flux<Employee> findAllById(List<Integer> idList) {
        log.info("Data is : ", idList);
        return employeeRepository.findAllById(idList);
    }

    public Flux<EmployeeDTO> findBySkill(EmployeeFindQueryObject query) {
        Flux<EmployeeSkill> skillFlux = employeeSkillRepository.findBySkills(query.getJava_exp() == null ? 0 : query.getJava_exp(),
                query.getSpring_exp() == null ? 0 : query.getSpring_exp());
        Mono<List<Integer>> empIdList = skillFlux.map(empDto -> empDto.getEmp_id()).collectList();

        List<EmployeeSkill> employeeSkillList = skillFlux.collectList().toProcessor().block();

        List<Integer> idList = empIdList.toProcessor().block();

        Flux<Employee> employeeFlux = employeeRepository.findAllById(idList);
        List<Employee> employeeList = employeeFlux.collectList().toProcessor().block();


        List<EmployeeDTO> dtoList = idList.stream().map(id -> {
                    Employee emp = employeeList.stream().filter(e -> e.getEmp_id().equals(id)).collect(Collectors.toList()).get(0);
                    EmployeeSkill empSkill = employeeSkillList.stream().filter(e -> e.getEmp_id().equals(id)).collect(Collectors.toList()).get(0);
                    EmployeeDTO dto = EmployeeDTO.getEmployeeDTO(emp, empSkill);
                    emp = null;
                    empSkill = null;
                    return dto;
                }
        ).collect(Collectors.toList());

        return Flux.fromIterable(dtoList);


    }


}
