package com.back.boundedContext.member.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class MemberPolicy {
  private static final int PASSWORD_CHANGE_DAYS = 90;

  // 비밀번호 변경이 필요한 기간을 Duration 객체로 반환
  public Duration getNeedToChangePasswordPeriod() {
    return Duration.ofDays(PASSWORD_CHANGE_DAYS);
  }

  // 비밀번호 변경이 필요한 일수를 정수로 반환
  public int getNeedToChangePasswordDays() {
    return PASSWORD_CHANGE_DAYS;
  }

  // 비밀번호 변경이 필요한지 판단하는 메서드
  public boolean isNeedToChangePassword(LocalDateTime lastChangedAt) {
    // 비밀번호 변경이 한번도 일어나지 않은 경우
    if (lastChangedAt == null) return true;

    return lastChangedAt.plusDays(PASSWORD_CHANGE_DAYS).isBefore(LocalDateTime.now());
  }
}
