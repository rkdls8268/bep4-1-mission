package com.back.shared.member.domain;

import static jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public abstract class SourceMember extends BaseMember {
  @Id
  @GeneratedValue(strategy = IDENTITY)
  private int id;
  @CreatedDate
  private LocalDateTime createDate;
  @LastModifiedDate
  private LocalDateTime modifyDate;

  public SourceMember(String username, String password, String nickname) {
    super(username, password, nickname);
  }}