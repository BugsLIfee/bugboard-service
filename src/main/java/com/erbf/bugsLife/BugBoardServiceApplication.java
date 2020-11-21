package com.erbf.bugsLife;

import com.erbf.bugsLife.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class BugBoardServiceApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(BugBoardServiceApplication.class, args);
	}

}