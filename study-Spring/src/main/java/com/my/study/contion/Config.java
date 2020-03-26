package com.my.study.contion;


import com.my.study.beanDefinition.BeanDefinitionTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	@Bean("person")
	public User person(){
		System.out.println("给容器中添加person.......");
		return new User("person",20);
	}
	
	@Conditional(WinCondition.class)
	@Bean("windows") //bean在容器中的ID为windows
	public User windows(){
		System.out.println("给容器中添加windows.......");
		return new User("windows",58);
	}
	@Conditional(LinCondition.class)
	@Bean("linux")//bean在容器中的ID为linux
	public User linux(){
		System.out.println("给容器中添加linux.......");
		return new User("linux",20);
	}

	@Bean
	public BeanDefinitionTest beanDefinitionTest124(){
		return  new  BeanDefinitionTest();
	}
}
