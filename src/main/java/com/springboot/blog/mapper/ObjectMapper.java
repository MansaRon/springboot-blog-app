package com.springboot.blog.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapper {

    public ModelMapper objectMapper() {
        return new ModelMapper();
    }

}
