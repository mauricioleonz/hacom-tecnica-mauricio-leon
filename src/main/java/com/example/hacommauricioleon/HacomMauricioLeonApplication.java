package com.example.hacommauricioleon;

import com.example.hacommauricioleon.Config.MongoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;

@SpringBootApplication
@EnableConfigurationProperties(MongoConfig.class)
public class HacomMauricioLeonApplication {

    public static void main(String[] args) {
        SpringApplication.run(HacomMauricioLeonApplication.class, args);
    }

    @Bean
    public LoggingEventListener mongoEventListener() {
        return new LoggingEventListener();
    }
}
