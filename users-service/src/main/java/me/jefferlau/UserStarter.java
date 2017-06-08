package me.jefferlau;

import me.jefferlau.domian.User;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringCloudApplication
// @EnableZuulProxy
@EnableHystrix
public class UserStarter {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(UserStarter.class, args);

        RepositoryRestConfiguration restConfiguration = ctx.getBean("config", RepositoryRestConfiguration.class);
        restConfiguration.exposeIdsFor(User.class);
    }
}
