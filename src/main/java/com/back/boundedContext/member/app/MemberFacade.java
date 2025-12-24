package com.back.boundedContext.member.app;

import com.back.boundedContext.member.domain.Member;
import com.back.global.exception.DomainException;
import com.back.boundedContext.member.out.MemberRepository;
import com.back.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberFacade {

  private final MemberRepository memberRepository;
  private final MemberJoinUseCase memberJoinUseCase;

  @Transactional(readOnly = true)
  public long count() {
    return memberRepository.count();
  }

  @Transactional(readOnly = true)
  public RsData<Member> join(String username, String password, String nickname) {
    RsData<Member> member = memberJoinUseCase.join(username, password, nickname);
    log.info("[msg] : {}", member.getMsg());
    return member;
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
