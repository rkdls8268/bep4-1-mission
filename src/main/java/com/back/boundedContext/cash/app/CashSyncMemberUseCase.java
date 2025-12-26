package com.back.boundedContext.cash.app;

import com.back.boundedContext.cash.domain.CashMember;
import com.back.boundedContext.cash.out.CashMemberRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.shared.cash.dto.CashMemberDto;
import com.back.shared.cash.event.CashMemberCreatedEvent;
import com.back.shared.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CashSyncMemberUseCase {

  private final CashMemberRepository cashMemberRepository;
  private final EventPublisher eventPublisher;

  public CashMember syncMember(MemberDto memberDto) {
    CashMember cashMember = new CashMember(
      memberDto.getId(),
      memberDto.getCreateDate(),
      memberDto.getModifyDate(),
      memberDto.getUsername(),
      "",
      memberDto.getNickname(),
      memberDto.getActivityScore()
    );
    cashMember = cashMemberRepository.save(cashMember);

    boolean isNew = !cashMemberRepository.existsById(memberDto.getId());
    // wallet 생성
    if (isNew) {
      eventPublisher.publish(new CashMemberCreatedEvent(new CashMemberDto(cashMember)));
    }

    return cashMember;
  }
}
