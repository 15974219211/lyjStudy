package com.my.study;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

@SpringBootApplication
@EnableBinding({Source.class})
// 注册到eureka
@EnableEurekaClient
public class StudyProducerApplication2 {

	public static void main(String[] args) {

		SpringApplication.run(StudyProducerApplication2.class, args);
	}

}
