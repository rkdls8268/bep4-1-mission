package com.back.boundedContext.payout.app;

import com.back.shared.market.dto.OrderDto;
import com.back.shared.member.dto.MemberDto;
import com.back.shared.payout.dto.PayoutMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PayoutFacade {

  private final PayoutSyncMemberUseCase payoutSyncMemberUseCase;
  private final PayoutCreatePayoutUseCase payoutCreatePayoutUseCase;
  private final PayoutAddPayoutCandidateItemsUseCase payoutAddPayoutCandidateItemsUseCase;

  public void syncMember(MemberDto memberDto) {
    payoutSyncMemberUseCase.syncMember(memberDto);
  }

  public void createPayout(PayoutMemberDto memberDto) {
    payoutCreatePayoutUseCase.createPayout(memberDto);
  }

  public void addPayoutCandidateItems(OrderDto orderDto) {
    payoutAddPayoutCandidateItemsUseCase.addPayoutCandidateItems(orderDto);
  }
}
