## Spring Batch

### Config 설정
```java
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
```

### 자동 실행
```
// application.yml
spring:
  batch:
    job:
      name: batch_job_name
```

### 실행 흐름 요약
1. Job 시작
2. Step 실행
3. Chunk 단위로 트랜잭션 커밋
4. JobRepository에 실행 이력 저장 (성공/실패/처리 건수 등)


### 구성 요소
* Job: 배치 작업 단위
* Step: Job의 내부 단계
* Reader: 입력 데이터 소스
* Processor: 데이터 가공/검증 
* Writer: 출력 처리

### spring scheduler
* 작업 스케줄러
* 지정된 시간 간격이나 특정 시점에 자동으로 실행되어야 하는 작업을 쉽게 구성
* 멀티스레딩과 비동기 실행을 지원하여 효율적인 백그라운드 작업 처리 제공
* @EnableScheduling 어노테이션 추가해줘야 함

```java
// PayoutScheduler.java
  // 매일 22:00 (KST)
  @Scheduled(cron = "0 0 22 * * *", zone = "Asia/Seoul")
  public void runAt22() throws JobInstanceAlreadyCompleteException, InvalidJobParametersException,
    JobExecutionAlreadyRunningException, JobRestartException {
    runCollectItemsAndCompletePayoutsBatchJob();
  }
```

### cron 표현식
특정 시간 또는 주기적으로 작업을 실행하도록 예약하는 데 사용되는 문자열

| 필드명 | 값의 허용 범위             |
|-----|----------------------|
| 초   | 0 ~ 59               | 
| 분   | 0 ~ 59               |
| 시   | 0 ~ 23               | 
| 일   | 1 ~ 31               | 
| 월   | 1 ~ 12 or JAN ~ DEC  | 
| 요일  | 0 ~ 6 or SUN ~ SAT   | 
| 연도  | empty or 1970 ~ 2099 |

특수문자 의미
- `*` : 모든 값을 뜻합니다.
- `?` : 특정한 값이 없음을 뜻합니다.
- `-` : 범위를 뜻합니다. ex.월요일에서 수요일까지는 MON-WED로 표현
- `,` : 특별한 값일 때만 동작 ex. 월,수,금 MON,WED,FRI
- `/` : 시작시간 / 단위 ex. 0분부터 매 5분 0/5
- `L` : 일에서 사용하면 마지막 일, 요일에서는 마지막 요일(토요일)
- `W` : 가장 가까운 평일 ex. 15W는 15일에서 가장 가까운 평일 (월 ~ 금)을 찾음
- `#` : 몇째주의 무슨 요일을 표현 ex. 3#2 : 2번째주 수요일


표현식 예)
* 매일 자정 실행: `* 0 0 * * *`
* 매주 월요일 오전 9시 30분 `0 30 9 * * 1`
* 5분마다: `0 0/5 * * * *`
* 매일 오후 2시에 시작해서 5분마다 실행하고 오후 2:55에 끝나고, 오후 6시에 시작해서 5분마다 실행되어 오후 6:55에 끝남: `0 0/5 14,18 * * *`
