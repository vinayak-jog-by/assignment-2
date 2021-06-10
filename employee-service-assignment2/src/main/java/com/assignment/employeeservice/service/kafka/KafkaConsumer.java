package com.assignment.employeeservice.service.kafka;

import com.assignment.employeeservice.model.EmployeeDTOResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    KafkaProducer producer;

    @Autowired
    KafkaProducerDLQ dlq;

    @KafkaListener(topics = "app_updates", groupId = "group_id")
    public void consume(String message) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        EmployeeDTOResponse employeeDTOResponse = null;
        if(message.length() > 0) {
            employeeDTOResponse = objectMapper.readValue(message, EmployeeDTOResponse.class);
        }

        if(employeeDTOResponse != null) {
            if (employeeDTOResponse.getStatus().equals("Created")) {
                producer.sendMessage(message);
            } else {
                dlq.sendMessage(message);
            }
        }
    }
}
