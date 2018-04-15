package com.ganster.cms.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableElasticsearchRepositories
public class CmsSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsSearchApplication.class, args);
	}
}
