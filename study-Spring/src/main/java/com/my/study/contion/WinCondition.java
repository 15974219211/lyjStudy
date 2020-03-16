package com.my.study.contion;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class WinCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		//能获取到IOC容器正在使用的beanFactory
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		//获取当前环境变量(包括我们操作系统是WIN还是LINUX??)
		Environment environment = context.getEnvironment();
		String os_name = environment.getProperty("os.name");
		if(os_name.contains("Windows")){
			return true;
		}
		return false;
	}

}
