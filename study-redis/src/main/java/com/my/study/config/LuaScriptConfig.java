package com.my.study.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;


@Configuration
public class LuaScriptConfig {

    @Bean
    public  DefaultRedisScript<Long> script1234() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<Long>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("unlock.lua")));
        return script;
    }
}
