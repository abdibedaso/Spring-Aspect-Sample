package com.geekcolab.development.SpringAspectSample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringAspectSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAspectSampleApplication.class, args);
	}

}
