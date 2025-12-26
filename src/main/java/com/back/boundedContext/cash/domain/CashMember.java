package com.back.boundedContext.cash.domain;


import com.back.shared.member.domain.ReplicaMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CASH_MEMBER")
@Getter
@NoArgsConstructor
public class CashMember extends ReplicaMember {
  public CashMember(int id, LocalDateTime createDate, LocalDateTime modifyDate,
    String username, String password, String nickname, int activityScore) {
    super(id, createDate, modifyDate, username, password, nickname, activityScore);
  }
}
