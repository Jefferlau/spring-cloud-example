package me.jefferlau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class DiscoveryStarter {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryStarter.class, args);
    }
}
