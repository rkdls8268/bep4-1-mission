package com.back.shared.market.dto;

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
}
