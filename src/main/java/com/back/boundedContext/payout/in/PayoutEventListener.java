package com.back.boundedContext.payout.in;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;
import com.back.boundedContext.payout.app.PayoutFacade;
import com.back.shared.market.event.MarketOrderPaymentCompletedEvent;
import com.back.shared.member.event.MemberJoinedEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import com.back.shared.payout.event.PayoutMemberCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class PayoutEventListener {
  private final PayoutFacade payoutFacade;

  @TransactionalEventListener(phase = AFTER_COMMIT)
  @Transactional(propagation = REQUIRES_NEW)
  public void handle(MemberJoinedEvent event) {
    payoutFacade.syncMember(event.getMember());
  }

  @TransactionalEventListener(phase = AFTER_COMMIT)
  @Transactional(propagation = REQUIRES_NEW)
  public void handle(MemberModifiedEvent event) {
    payoutFacade.syncMember(event.getMember());
  }

  @TransactionalEventListener(phase = AFTER_COMMIT)
  @Transactional(propagation = REQUIRES_NEW)
  public void handle(PayoutMemberCreatedEvent event) {
    payoutFacade.createPayout(event.getMember());
  }

  @TransactionalEventListener(phase = AFTER_COMMIT)
  @Transactional(propagation = REQUIRES_NEW)
  public void handle(MarketOrderPaymentCompletedEvent event) {
    // 주문만 완료된 상태. 구매확정이 되지 않아 아직 정산도 안된 상태
    payoutFacade.addPayoutCandidateItems(event.getOrder());
  }
}
