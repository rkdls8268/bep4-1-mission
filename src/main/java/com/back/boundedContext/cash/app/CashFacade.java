package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashFacade {

  private final CashMemberRepository cashMemberRepository;

  public void syncMember(MemberDto memberDto) {
    CashMember cashMember = new CashMember(
      memberDto.getId(),
      memberDto.getCreateDate(),
      memberDto.getModifyDate(),
      memberDto.getUsername(),
      "",
      memberDto.getNickname(),
      memberDto.getActivityScore()
    );

    cashMemberRepository.save(cashMember);
  }
}
