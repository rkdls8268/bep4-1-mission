package com.back.shared.dto;

import com.back.boundedContext.member.domain.Member;
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
  private String password;
  private String nickname;
  private int activityScore;

  public MemberDto(Member member) {
    this(
      member.getId(),
      member.getCreateDate(),
      member.getModifyDate(),
      member.getUsername(),
      member.getPassword(),
      member.getNickname(),
      member.getActivityScore()
    );
  }
}
