package com.back.shared.market.dto;

import com.back.boundedContext.market.domain.MarketMember;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MarketMemberDto {

  private int id;
  private LocalDateTime createDate;
  private LocalDateTime modifyDate;
  private String username;
  private String nickname;
  private int activityScore;

  public MarketMemberDto(MarketMember member) {
    this(
      member.getId(),
      member.getCreateDate(),
      member.getModifyDate(),
      member.getUsername(),
      member.getNickname(),
      member.getActivityScore()
    );
  }
}
