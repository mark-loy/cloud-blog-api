package com.mark.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/1/21 15:52
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.mark"})
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
