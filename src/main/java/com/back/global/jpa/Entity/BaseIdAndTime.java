package com.back.global.jpa.Entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseIdAndTime extends BaseEntity {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private int id;
  @CreatedDate
  private LocalDateTime createDate;
  @LastModifiedDate
  private LocalDateTime modifyDate;
}