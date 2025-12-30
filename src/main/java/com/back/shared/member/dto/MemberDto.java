package com.back.shared.member.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberDto {
  private int id;
  private LocalDateTime createDate;
  private LocalDateTime modifyDate;
  private String username;
  private String nickname;
  private int activityScore;
}
