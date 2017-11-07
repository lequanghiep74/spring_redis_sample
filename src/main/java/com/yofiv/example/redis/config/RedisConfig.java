package com.yofiv.example.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@ComponentScan("com.yofiv.example.redis")
public class RedisConfig
{
    @Autowired
    private Environment env;

    @Bean
    JedisConnectionFactory jedisConnectionFactory()
    {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(env.getProperty("spring.redis.host"));
        connectionFactory.setPort(Integer.parseInt(env.getProperty("spring.redis.port")));
        return connectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate()
    {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }
}
