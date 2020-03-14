package com.my.study.config;

import com.my.ribionconfig.RibbonLoadBalanceConfig;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

/*
* 这个是针对具体服务的 ribbon配置
* */
@Configuration
@RibbonClients(value = {
        @RibbonClient(name = "study-producer",configuration = RibbonLoadBalanceConfig.class)
})
public class LoadBalanceConfig {

}
