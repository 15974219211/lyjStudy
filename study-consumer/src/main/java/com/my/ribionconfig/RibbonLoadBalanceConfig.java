package com.my.ribionconfig;

import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
* 这个类不要出现在启动类的@ComponentScan扫描范围
* 如果出现在@ComponentScan扫描访问，那么这个配置类就是每个服务共用的配置了
* */
@Configuration
public class RibbonLoadBalanceConfig {

//    @RibbonClientName
    private String name = "study-producer";

    @Bean
    @ConditionalOnClass
    public IClientConfig defaultClientConfigImpl() {
        //这个是Ribbon的配置类
        DefaultClientConfigImpl config = new DefaultClientConfigImpl();
        config.loadProperties(name);
        config.set(CommonClientConfigKey.MaxAutoRetries,2);
        config.set(CommonClientConfigKey.MaxAutoRetriesNextServer,2);
        config.set(CommonClientConfigKey.ConnectTimeout,1000);
        config.set(CommonClientConfigKey.ReadTimeout,3000);
        config.set(CommonClientConfigKey.OkToRetryOnAllOperations,true);
        return config;
    }

    //配置负载策略
    @Bean
    public IRule ribbonRule() {
        //随机
        new RandomRule();
        //线性轮训
        new RoundRobinRule();
        //可以重试的轮训
        new RetryRule();
        //根据运行情况来计算权重
        new WeightedResponseTimeRule();
        //过滤掉故障实例，选择请求数最小的实例
        new BestAvailableRule();
        return new RoundRobinRule();
    }
}
