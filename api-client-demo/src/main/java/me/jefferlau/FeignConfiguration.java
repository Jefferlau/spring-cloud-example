package me.jefferlau;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create on 2017-06-08.
 * https://springcloud.cc/spring-cloud-dalston.html#spring-cloud-feign
 *
 * @author Jeffer Lau <jefferlzu@gmail.com>
 */
@Configuration
public class FeignConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(@Autowired GatewayUser gatewayUser) {
        return new BasicAuthRequestInterceptor(gatewayUser.getName(), gatewayUser.getPassword());
    }

    @Bean
    @ConfigurationProperties(prefix = "eureka.client.user")
    public GatewayUser gatewayUser() {
        return new GatewayUser();
    }

    class GatewayUser {
        private String name;
        private String password;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
