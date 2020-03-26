package com.my.study;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan("com.my.study.dao")

//@ServletComponentScan
//@EnableConfigurationProperties(DruidConfig.class)//使用@ConfigurationProperties要手动开启
public class StudySpringboot {

//ConfigurationClassPostProcessor
    public static void main(String[] args) {
        SpringApplication.run(StudySpringboot.class,
                args);
    }

 /*   @Override
    @Transactional(isolation= DB2SelectQueryBlock.Isolation.SERIALIZABLE)
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringBootTest.class);
    }*/
}
