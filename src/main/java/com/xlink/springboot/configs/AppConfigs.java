package com.xlink.springboot.configs;


import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;


@ConfigurationProperties(prefix = "app")
public class AppConfigs {

    Logger logger = LoggerFactory.getLogger(AppConfigs.class);

    @Getter
    @Setter
    private String apiName;
    @Setter
    @Getter
    private String apiVersion;


    private final Environment env;

    public AppConfigs(Environment env) {
        this.env = env;
    }

    public String getRunMode() {

        return env.getProperty("app.run.mode", "ERROR MODE");
    }

    @Bean
    @Profile("dev")
    public String devConfig() {
        logger.info("Development configuration active");
        // Development specific configuration goes here
        return "dev";
    }

    @Bean
    @Profile("prod")
    public String prodConfig() {
        logger.info("Production configuration active");
        // Production specific configuration goes here
        return "prod";
    }
}
