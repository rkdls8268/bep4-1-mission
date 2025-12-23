package com.back.boundedContext.member.service;

import com.back.boundedContext.member.entity.Member;
import com.back.global.exception.DomainException;
import com.back.boundedContext.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  public long count() {
    return memberRepository.count();
  }

  public Member join(String username, String password, String nickname) {
    memberRepository.findByUsername(username).ifPresent(m -> {
      throw new DomainException("409-1", "이미 존재하는 username 입니다.");
    });

    return memberRepository.save(new Member(username, password, nickname));
  }

  public Member findByUsername(String username) {
    return memberRepository.findByUsername(username)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 username 입니다."));
  }

  public Member findById(int id) {
    return memberRepository.findById(id)
      .orElseThrow(() -> new DomainException("409-4", "존재하지 않는 id 입니다."));
  }

}
