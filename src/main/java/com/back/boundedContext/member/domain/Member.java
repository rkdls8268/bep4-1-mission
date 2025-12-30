package com.back.boundedContext.member.domain;

import com.back.shared.member.dto.MemberDto;
import com.back.shared.member.event.MemberModifiedEvent;
import com.back.shared.member.domain.SourceMember;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEMBER_MEMBER")
@NoArgsConstructor
@Getter
public class Member extends SourceMember {

  public Member(String username, String password, String nickname) {
    super(username, password, nickname);
  }

  public MemberDto toDto() {
    return new MemberDto(
      getId(),
      getCreateDate(),
      getModifyDate(),
      getUsername(),
      getNickname(),
      getActivityScore()
    );
  }

  public void increaseActivityScore(int amount) {
    setActivityScore(getActivityScore() + amount);
    publishEvent(new MemberModifiedEvent(toDto()));
  }
}
