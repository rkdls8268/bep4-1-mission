package com.back.global.jpa.Entity;

import com.back.global.config.GlobalConfig;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// 모든 엔티티들의 조상
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity {
  public String getModelTypeCode() {
    return this.getClass().getSimpleName();
  }

  // GlobalConfig 에서 setter 로 초기화해놓은 것을 나중에 entity 에서 getter로 사용할 수 있도록 BaseEntity 에서 만들어 놓음
  protected void publishEvent(Object event) {
    GlobalConfig.getEventPublisher().publish(event);
  }
  public abstract int getId();
  public abstract LocalDateTime getCreateDate();
  public abstract LocalDateTime getModifyDate();
}
