package com.back.boundedContext.member.app;

import com.back.boundedContext.member.domain.Member;
import com.back.global.exception.DomainException;
import com.back.boundedContext.member.out.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberFacade {

  private final MemberRepository memberRepository;
  private final MemberJoinUseCase memberJoinUseCase;

  @Transactional(readOnly = true)
  public long count() {
    return memberRepository.count();
  }

  @Transactional(readOnly = true)
  public Member join(String username, String password, String nickname) {
    return memberJoinUseCase.join(username, password, nickname);
  }

  @Transactional(readOnly = true)
  public Member findByUsername(String username) {
    return memberRepository.findByUsername(username)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 username 입니다."));
  }

  @Transactional(readOnly = true)
  public Member findById(int id) {
    return memberRepository.findById(id)
      .orElseThrow(() -> new DomainException("409-4", "존재하지 않는 id 입니다."));
  }

}
