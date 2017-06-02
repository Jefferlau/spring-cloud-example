package me.jefferlau;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardStarter {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HystrixDashboardStarter.class).web(true).run(args);
    }

}
