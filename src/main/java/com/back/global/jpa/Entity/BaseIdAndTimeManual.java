package com.back.global.jpa.Entity;


import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class BaseIdAndTimeManual extends BaseEntity {
  @Id
  private int id;
  private LocalDateTime createDate;
  private LocalDateTime modifyDate;
}