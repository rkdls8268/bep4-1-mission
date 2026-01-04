package com.back.boundedContext.payout.in;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import com.back.boundedContext.payout.app.PayoutFacade;
import com.back.shared.market.event.MarketOrderPaymentCompletedEvent;
import com.back.shared.member.event.MemberJoinedEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import com.back.shared.payout.event.PayoutCompletedEvent;
import com.back.shared.payout.event.PayoutMemberCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PayoutEventListener {
  private final PayoutFacade payoutFacade;

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "MemberJoinedEvent", groupId = "PayoutEventListener_handle_1")
  public void handle(MemberJoinedEvent event) {
    payoutFacade.syncMember(event.getMember());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "MemberModifiedEvent", groupId = "PayoutEventListener_handle_2")
  public void handle(MemberModifiedEvent event) {
    payoutFacade.syncMember(event.getMember());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "PayoutMemberCreatedEvent", groupId = "PayoutEventListener_handle_3")
  public void handle(PayoutMemberCreatedEvent event) {
    payoutFacade.createPayout(event.getMember().getId());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "MarketOrderPaymentCompletedEvent", groupId = "PayoutEventListener_handle_4")
  public void handle(MarketOrderPaymentCompletedEvent event) {
    // 주문만 완료된 상태. 구매확정이 되지 않아 아직 정산도 안된 상태
    payoutFacade.addPayoutCandidateItems(event.getOrder());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "PayoutCompletedEvent", groupId = "PayoutEventListener_handle_5")
  public void handle(PayoutCompletedEvent event) {
    payoutFacade.createPayout(event.getPayout().getPayeeId());
  }
}
