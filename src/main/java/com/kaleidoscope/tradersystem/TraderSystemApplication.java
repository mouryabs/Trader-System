package com.kaleidoscope.tradersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@ComponentScan({"com.kaleidoscope.tradersystem"})
@EnableAutoConfiguration
@EnableJpaAuditing
@SpringBootConfiguration
public class TraderSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TraderSystemApplication.class, args);
	}

}
