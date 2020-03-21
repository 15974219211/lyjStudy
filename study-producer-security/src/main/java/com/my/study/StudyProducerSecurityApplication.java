package com.my.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@SpringBootApplication
// 注册到eureka
@EnableEurekaClient
public class StudyProducerSecurityApplication {

	public static void main(String[] args) {

		SpringApplication.run(StudyProducerSecurityApplication.class, args);
	}

}
