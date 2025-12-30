package com.back.global.config;

import javax.sql.DataSource;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.EnableJdbcJobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@EnableBatchProcessing // optional
@EnableJdbcJobRepository
/**
 * JobRepository 를 JDBC 기반으로 구성
 * 배치 실행 이력, Step/JobExecution 등 메타데이터를 RDBMS 테이블에 저장
 * 이때 SpringBatch 메타데이터 테이블이 DB에 반드시 존재해야 함
 */
public class BatchConfig {

  @Bean
  @Profile("!prod")
  public DataSourceInitializer notProdDataSourceInitializer(DataSource dataSource) {
    // Spring Batch 가 제공하는 H2용 스키마
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    // h2용 스크립트 사용
    populator.addScript(new ClassPathResource("/org/springframework/batch/core/schema-h2.sql"));
    // 이미 테이블 있거나 일부 문이 실패해도 계속 진행
    populator.setContinueOnError(true);

    // DataSourceInitializer가 Application 시작 시 해당 스크립트 실행
    DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource);
    initializer.setDatabasePopulator(populator);
    return initializer;
  }
}