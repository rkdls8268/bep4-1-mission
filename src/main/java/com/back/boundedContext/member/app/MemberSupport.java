package com.back.boundedContext.member.app;

import com.back.boundedContext.member.domain.Member;
import com.back.boundedContext.member.out.MemberRepository;
import com.back.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberSupport {

  private final MemberRepository memberRepository;

  public long count() {
    return memberRepository.count();
  }

  public Member findByUsername(String username) {
    return memberRepository.findByUsername(username)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 username 입니다."));
  }

  public Member findById(int id) {
    return memberRepository.findById(id)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 id 입니다."));
  }
}
