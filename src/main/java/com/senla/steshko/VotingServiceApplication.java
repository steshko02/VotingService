package com.senla.steshko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class VotingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(VotingServiceApplication.class, args);
    }
}
//<!--//kafka-->
//<!--//mapstruct, dozer-->
// test container
// gradle
//проекции
// feign

