package me.jefferlau;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.sidecar.EnableSidecar;

@SpringBootApplication
@EnableSidecar
public class GatewayStarter {
	public static void main(String[] args) {
		SpringApplication.run(GatewayStarter.class, args);
	}
}
