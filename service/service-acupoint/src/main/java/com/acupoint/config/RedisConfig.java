package com.acupoint.config;

import com.acupoint.serializer.MessageRedisSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.ai.chat.messages.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.List;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Message> messageRedisTemplate(RedisConnectionFactory connectionFactory, Jackson2ObjectMapperBuilder builder) {
        //创建Redis模板对象
        RedisTemplate<String, Message> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // 使用自定义的Message序列化器作为value的序列化方式
        redisTemplate.setValueSerializer(new MessageRedisSerializer(builder.build()));

        // 设置hash类型的key和value序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new MessageRedisSerializer(builder.build()));


        redisTemplate.afterPropertiesSet(); // 手动初始化RedisTemplate
        return redisTemplate;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }
}
