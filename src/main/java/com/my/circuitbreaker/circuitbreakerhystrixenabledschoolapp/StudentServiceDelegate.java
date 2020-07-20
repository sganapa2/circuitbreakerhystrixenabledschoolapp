package com.my.circuitbreaker.circuitbreakerhystrixenabledschoolapp;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class StudentServiceDelegate {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "callStudentServiceToGetData_Fallback")
    public String getStudentDataFromCircuitBreakerAppForSchool(String schoolName) {

        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8098/getStudentsBySchoolName/{schoolName}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                }, schoolName);

        String responseEntityBody = responseEntity.getBody();

        logger.info("RESPONSE RECEIVED FROM STUDENT SERVICE " + responseEntityBody + " - " + new Date());

        return "NORMAL FLOW For School Name: " + schoolName + "  STUDENT DETAILS: " + responseEntityBody + " - " + new  Date();
    }


    private String callStudentServiceToGetData_Fallback(String schoolName) {
        logger.error("ERROR: STUDENT MICROSERVICE IS DOWN! FALLBACK ROUTE ENABLED/ FALLBACK METHOD CALLED");

        return  "CIRCUIT BREAKER ENABLED. No response from Student microservice at this moment. Service will be back soon." + new Date();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
