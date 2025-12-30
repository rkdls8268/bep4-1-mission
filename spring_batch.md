## Spring Batch

Config 설정
```
BatchConfig
```

자동 실행
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