package com.xlink.springboot;

import com.xlink.springboot.configs.AppConfigs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppConfigs.class)
public class RemoteTask {

    public static void main(String[] args) {
        SpringApplication.run(RemoteTask.class, args);
    }

}
