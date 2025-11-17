package com.asus.tikra;

import dev.langchain4j.community.store.embedding.redis.spring.RedisEmbeddingStoreAutoConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {RedisEmbeddingStoreAutoConfiguration.class})
@MapperScan("com.asus.tikra.mapper")
public class TikraApplication {

    public static void main(String[] args) {
        SpringApplication.run(TikraApplication.class, args);
        System.out.println("http://localhost:8080/api/doc.html#/home");
    }

}
