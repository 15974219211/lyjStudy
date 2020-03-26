package com.my.study.beanDefinition;

import com.my.study.contion.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class test {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext app = new AnnotationConfigApplicationContext(BeanDefinitionTest.class);
        String[] beanDefinitionNames = app.getBeanDefinitionNames();
        for(String name:beanDefinitionNames){
            System.out.println(name);
        }



    }

}
