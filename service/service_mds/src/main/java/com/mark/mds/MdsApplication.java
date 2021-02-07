package com.mark.mds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 木可
 * @version 1.0
 * @date 2021/2/6 16:19
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.mark"})
public class MdsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MdsApplication.class, args);
    }
}
