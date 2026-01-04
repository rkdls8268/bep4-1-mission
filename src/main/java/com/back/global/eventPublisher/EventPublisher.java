package com.back.global.eventPublisher;

import com.back.standard.event.HaveEventName;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventPublisher {

//  private final ApplicationEventPublisher applicationEventPublisher;
  private final KafkaTemplate<String, HaveEventName> kafkaTemplate;

  // 이벤트 발행
//  public void publish(Object event) {
//    applicationEventPublisher.publishEvent(event);
//  }

  public void publish(HaveEventName event) {
    kafkaTemplate.send(event.getEventName(), event);
  }

}
