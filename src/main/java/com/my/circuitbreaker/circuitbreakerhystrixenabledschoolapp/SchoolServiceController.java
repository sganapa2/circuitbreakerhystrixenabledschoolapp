package com.my.circuitbreaker.circuitbreakerhystrixenabledschoolapp;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SchoolServiceController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentServiceDelegate studentServiceDelegate;

    @GetMapping("/getStudentsOfSchool/{schoolName}")
    public String getStudentData(@PathVariable String schoolName) {

        logger.info("CALLING STUDENT MICROSERVICE FOR SCHOOL TO GET DATA");
        return studentServiceDelegate.getStudentDataFromCircuitBreakerAppForSchool(schoolName);
    }


}
