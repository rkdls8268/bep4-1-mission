package com.back.boundedContext.payout.app;

import com.back.boundedContext.payout.domain.PayoutMember;
import com.back.boundedContext.payout.out.PayoutMemberRepository;
import com.back.global.exception.DomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutSupport {

  private final PayoutMemberRepository payoutMemberRepository;

  public PayoutMember findHoldingMember() {
    return payoutMemberRepository.findByUsername("holding")
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 payoutMember 입니다."));
  }

  public PayoutMember findMemberById(int id) {
    return payoutMemberRepository.findById(id)
      .orElseThrow(() -> new DomainException("409-2", "존재하지 않는 payoutMember 입니다."));
  }

}
