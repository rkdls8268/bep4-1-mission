package com.back.global.config;

import com.back.global.eventPublisher.EventPublisher;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {

  // config 파일로 따로 빼놓으면 한 번만 선언해도 됨. (static 선언)
  @Getter
  private static EventPublisher eventPublisher;

  @Autowired
  public void setEventPublisher(EventPublisher eventPublisher) {
    GlobalConfig.eventPublisher = eventPublisher;
  }
}
