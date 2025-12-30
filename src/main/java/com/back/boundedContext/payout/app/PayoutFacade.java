package com.back.boundedContext.payout.app;

import com.back.boundedContext.payout.domain.PayoutCandidateItem;
import com.back.global.rsData.RsData;
import com.back.shared.market.dto.OrderDto;
import com.back.shared.member.dto.MemberDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PayoutFacade {

  private final PayoutSupport payoutSupport;
  private final PayoutSyncMemberUseCase payoutSyncMemberUseCase;
  private final PayoutCreatePayoutUseCase payoutCreatePayoutUseCase;
  private final PayoutAddPayoutCandidateItemsUseCase payoutAddPayoutCandidateItemsUseCase;
  private final PayoutCollectPayoutItemsMoreUseCase payoutCollectPayoutItemsMoreUseCase;
  private final PayoutCompletePayoutsMoreUseCase payoutCompletePayoutsMoreUseCase;

  @Transactional
  public void syncMember(MemberDto memberDto) {
    payoutSyncMemberUseCase.syncMember(memberDto);
  }

  @Transactional
  public void createPayout(int payeeId) {
    payoutCreatePayoutUseCase.createPayout(payeeId);
  }

  @Transactional
  public void addPayoutCandidateItems(OrderDto orderDto) {
    payoutAddPayoutCandidateItemsUseCase.addPayoutCandidateItems(orderDto);
  }

  @Transactional(readOnly = true)
  public List<PayoutCandidateItem> findPayoutCandidateItems() {
    return payoutSupport.findPayoutCandidateItems();
  }

  @Transactional
  public RsData<Integer> collectPayoutItemsMore(int limit) {
    return payoutCollectPayoutItemsMoreUseCase.collectPayoutItemsMore(limit);
  }

  @Transactional
  public RsData<Integer> completePayoutsMore(int limit) {
    return payoutCompletePayoutsMoreUseCase.completePayoutsMore(limit);
  }
}
