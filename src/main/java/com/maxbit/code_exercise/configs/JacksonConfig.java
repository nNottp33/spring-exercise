package com.maxbit.code_exercise.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.maxbit.code_exercise.common.deserializer.EmptyStringToNullDeserializer;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new EmptyStringToNullDeserializer());
        mapper.registerModule(module);

        return mapper;
    }
}
