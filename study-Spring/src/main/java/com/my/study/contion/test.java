package com.my.study.contion;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
        String[] beanDefinitionNames = app.getBeanDefinitionNames();
        for(String name:beanDefinitionNames){
            System.out.println(name);
        }



    }

}
