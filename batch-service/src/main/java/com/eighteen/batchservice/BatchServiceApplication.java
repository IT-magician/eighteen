package com.eighteen.batchservice;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
@EnableBatchProcessing
public class BatchServiceApplication extends DefaultBatchConfigurer {

	@Override
	public void setDataSource(DataSource dataSource) {
		// 여기를 비워놓는다
	}

	public static void main(String[] args) {
		SpringApplication.run(BatchServiceApplication.class, args);
	}


}
