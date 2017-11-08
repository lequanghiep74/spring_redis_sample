package com.yofiv.example.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ComponentScan("com.yofiv.example.redis")
@PropertySource("classpath:/application.properties")
public class RedisConfig
{
    private @Value("${spring.redis.host}") String redisHost;
    private @Value("${spring.redis.port}") int redisPort;

    @Bean
    JedisConnectionFactory jedisConnectionFactory()
    {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setHostName(redisHost);
        connectionFactory.setPort(redisPort);
        return connectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate()
    {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new StringRedisSerializer());
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }
}
