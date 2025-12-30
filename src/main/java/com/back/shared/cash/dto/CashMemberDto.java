package com.back.shared.cash.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CashMemberDto {
  private int id;
  private LocalDateTime createDate;
  private LocalDateTime modifyDate;
  private String username;
  private String nickname;
  private int activityScore;

}
