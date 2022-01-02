package com.msa.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableEurekaClient를 사용한 Application은 Eureka 서버로부터 다른 서버의 주소를 가져오며 자신의 주소를 등록한다.
@EnableEurekaClient
@EnableJpaAuditing
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

}
