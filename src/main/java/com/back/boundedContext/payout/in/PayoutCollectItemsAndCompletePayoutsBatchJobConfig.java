package com.back.boundedContext.payout.in;

import com.back.boundedContext.payout.app.PayoutFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class PayoutCollectItemsAndCompletePayoutsBatchJobConfig {
  private static final int CHUNK_SIZE = 10;

  private final PayoutFacade payoutFacade;

  public PayoutCollectItemsAndCompletePayoutsBatchJobConfig(PayoutFacade payoutFacade) {
    this.payoutFacade = payoutFacade;
  }

  @Bean
  public Job payoutCollectItemsAndCompletePayoutsJob(
    JobRepository jobRepository,
    Step payoutCollectItemsStep,
    Step payoutCompletePayoutsStep
  ) {
    return new JobBuilder("payoutCollectItemsAndCompletePayoutsJob", jobRepository)
      .start(payoutCollectItemsStep)
      .next(payoutCompletePayoutsStep)
      .build();
  }

  @Bean
  public Step payoutCollectItemsStep(JobRepository jobRepository) {
    return new StepBuilder("payoutCollectItemsStep", jobRepository)
      // TaskLet 기반 Step -> 한번의 로직을 반복적으로 수행 가능
      .tasklet((contribution, chunkContext) -> {
        int processedCount = payoutFacade.collectPayoutItemsMore(CHUNK_SIZE).getData();

        if (processedCount == 0) {
          return RepeatStatus.FINISHED;
        }

        // StepExecution 에 처리 건수 기록. batch 메타데이터에 저장
        contribution.incrementWriteCount(processedCount);

        return RepeatStatus.CONTINUABLE;
      })
      .build();
  }

  @Bean
  public Step payoutCompletePayoutsStep(JobRepository jobRepository) {
    return new StepBuilder("payoutCompletePayouts", jobRepository)
      .tasklet((contribution, chunkContext) -> {
        int processedCount = payoutFacade.completePayoutsMore(CHUNK_SIZE).getData();

        if (processedCount == 0) {
          return RepeatStatus.FINISHED;
        }

        contribution.incrementWriteCount(processedCount);

        return RepeatStatus.CONTINUABLE;
      })
      .build();
  }
}
