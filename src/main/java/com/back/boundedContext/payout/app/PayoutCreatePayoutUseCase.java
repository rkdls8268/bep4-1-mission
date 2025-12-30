package com.back.boundedContext.payout.app;


import com.back.boundedContext.payout.domain.Payout;
import com.back.boundedContext.payout.domain.PayoutMember;
import com.back.boundedContext.payout.out.PayoutMemberRepository;
import com.back.boundedContext.payout.out.PayoutRepository;
import com.back.shared.payout.dto.PayoutMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutCreatePayoutUseCase {

  private final PayoutRepository payoutRepository;
  private final PayoutMemberRepository payoutMemberRepository;

  public void createPayout(PayoutMemberDto payee) {
    PayoutMember member = payoutMemberRepository.getReferenceById(payee.getId());
    payoutRepository.save(new Payout(member));
  }
}