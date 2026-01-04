package com.back.boundedContext.cash.in;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;
import com.back.boundedContext.cash.app.CashFacade;
import com.back.shared.cash.event.CashMemberCreatedEvent;
import com.back.shared.market.event.MarketOrderRequestPaymentStartedEvent;
import com.back.shared.member.event.MemberJoinedEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import com.back.shared.payout.event.PayoutCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CashEventListener {
  private final CashFacade cashFacade;

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "MemberJoinedEvent", groupId = "CashEventListener__handle__1")
  public void handle(MemberJoinedEvent event) {
    cashFacade.syncMember(event.getMember());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "MemberModifiedEvent", groupId = "CashEventListener__handle__2")
  public void handle(MemberModifiedEvent event) {
    cashFacade.syncMember(event.getMember());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "CashMemberCreatedEvent", groupId = "CashEventListener__handle__3")
  public void handle(CashMemberCreatedEvent event) {
    cashFacade.createWallet(event.getCashMemberDto());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "MarketOrderRequestPaymentStartedEvent", groupId = "CashEventListener__handle__4")
  public void handle(MarketOrderRequestPaymentStartedEvent event) {
    cashFacade.completeOrderPayment(event.getOrder(), event.getPgPaymentAmount());
  }

  @Transactional(propagation = REQUIRES_NEW)
  @KafkaListener(topics = "PayoutCompletedEvent", groupId = "CashEventListener__handle__5")
  public void handle(PayoutCompletedEvent event) {
    cashFacade.completePayout(event.getPayout());
  }
}
