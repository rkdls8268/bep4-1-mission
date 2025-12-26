package com.back.shared.cash.dto;

import com.back.boundedContext.cash.domain.CashMember;
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

  public CashMemberDto(CashMember member) {
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
