package com.back.boundedContext.market.domain;

import com.back.shared.member.domain.ReplicaMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MARKET_MEMBER")
@Getter
@NoArgsConstructor
public class MarketMember extends ReplicaMember {
  public MarketMember(int id, LocalDateTime createDate, LocalDateTime modifyDate,
    String username, String password, String nickname, int activityScore) {
    super(id, createDate, modifyDate, username, password, nickname, activityScore);
  }
}