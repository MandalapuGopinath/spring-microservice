package com.spring.microservice;

import com.spring.microservice.mapper.AccountsMapper;
import com.spring.microservice.mapper.CustomerMapper;
import com.spring.microservice.mapper.OrganizationMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SpringMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroserviceApplication.class, args);
	}

	@Bean
	public AccountsMapper accountsMapper(){
		return new AccountsMapper();
	}
	@Bean
	public CustomerMapper customerMapper(){
		return new CustomerMapper();
	}
	@Bean
	public OrganizationMapper organizationMapper(){
		return new OrganizationMapper();
	}

}
